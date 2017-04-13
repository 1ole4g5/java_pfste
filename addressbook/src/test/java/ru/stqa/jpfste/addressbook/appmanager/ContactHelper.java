package ru.stqa.jpfste.addressbook.appmanager;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import ru.stqa.jpfste.addressbook.model.ContactData;
import ru.stqa.jpfste.addressbook.model.Contacts;

public class ContactHelper extends HelperBase {

	public ContactHelper(ApplicationManager app) {
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

	public void submitAddNewContactForm() {
		click(By.xpath("//div[@id='content']/form/input[21]"));
	}

	public void goToAddNewContactPage() {
		click(By.linkText("add new"));
	}

	public void create(ContactData contactData, boolean creation2) {
		goToAddNewContactPage();
		fillAddNewContactForm(contactData, creation2);
		submitAddNewContactForm();
	}

	public Contacts all() {
		Contacts contacts = new Contacts();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			String firstName = element.findElement(By.xpath(".//td[3]")).getText();
			String lastName = element.findElement(By.xpath(".//td[2]")).getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
			        .withGroup("new group"));
		}
		return contacts;
	}

	public void editContact() {
		click(By.cssSelector("img[title=Edit]"));
	}

	public void selectContactById(int id) {
		wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
	}

	public void updateItem() {
		click(By.name("update"));
	}

	public void deletionContact() {
		click(By.cssSelector("input[value=Delete]"));
	}

	public void acceptAlert() {
		wd.switchTo().alert().accept();
	}

	public void modify(ContactData contact) {
		selectContactById(contact.getId());
		editContact();
		fillAddNewContactForm(contact, false);
		updateItem();
	}

	public void delete(ContactData contact) {
		selectContactById(contact.getId());
		deletionContact();
		acceptAlert();
	}
}