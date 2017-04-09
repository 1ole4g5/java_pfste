package ru.stqa.jpfste.addressbook.appmanager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import ru.stqa.jpfste.addressbook.model.ContactData;
import ru.stqa.jpfste.addressbook.model.GroupData;

public class CreationHelper extends HelperBase {

	public CreationHelper(ApplicationManager app) {
		super(app);
	}

	public void fillAddNewContactForm(ContactData contactData, boolean creation) {
		type(By.name("firstname"), contactData.getFirstName());
		type(By.name("lastname"), contactData.getLastName());
		type(By.name("nickname"), contactData.getNickName());
		type(By.name("address"), contactData.getAddress());
		type(By.name("email"), contactData.getEMail());
		
		if (creation) {
			new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}
	}

	public void fillGroupForm(GroupData groupData) {
		type(By.name("group_name"), groupData.getName());
		type(By.name("group_header"), groupData.getHeader());
		type(By.name("group_footer"), groupData.getFooter());
	}

	public void submitAddNewContactForm() {
		click(By.xpath("//div[@id='content']/form/input[21]"));
	}

	public void goToAddNewContactPage() {
		click(By.linkText("add new"));
	}

	public void returnToGroupCreation() {
		click(By.linkText("group page"));
	}

	public void submitGroupCreation() {
		click(By.name("submit"));
	}

	public void initGroupCreation() {
		click(By.name("new"));
	}

	public void createGroup(GroupData groupData) {
		initGroupCreation();
		fillGroupForm(groupData);
		submitGroupCreation();
		app.navigationHelper.returnToHomePage();
	}
	
	public void createContact(ContactData contactData, boolean creation2) {
		goToAddNewContactPage();
		fillAddNewContactForm(contactData, creation2);
		submitAddNewContactForm();
		app.navigationHelper.returnToHomePage();
	}	
	
	public List<GroupData> getGroupList() {
		List<GroupData> groups = new ArrayList<GroupData>();
		List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
		for (WebElement element : elements) {
			String name = element.getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			GroupData group = new GroupData(id, name, null, null);
			groups.add(group);
		}
		return groups;
	}

	public int getGroupCount() {
		return wd.findElements(By.name("selected[]")).size();
	}
	
	public List<ContactData> getContactList() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			String firstName = element.findElement(By.xpath(".//td[3]")).getText();
			String lastName = element.findElement(By.xpath(".//td[2]")).getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			ContactData contact = new ContactData(id, firstName, lastName, null, null, null, "new group");
			contacts.add(contact);
		}
		return contacts;
	}
}