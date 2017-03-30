package ru.stqa.jpfste.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
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
}