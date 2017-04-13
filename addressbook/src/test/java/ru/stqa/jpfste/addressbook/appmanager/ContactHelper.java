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
		type(By.name("email"), contactData.getFirstEMail());
		type(By.name("email2"), contactData.getSecondEMail());
		type(By.name("email3"), contactData.getThirdEMail());
		type(By.name("home"), contactData.getHomePhone());
		type(By.name("mobile"), contactData.getMobilePhone());
		type(By.name("work"), contactData.getWorkPhone());

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

	public int count() {
		return wd.findElements(By.name("selected[]")).size();
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

	public void create(ContactData contactData, boolean creation2) {
		goToAddNewContactPage();
		fillAddNewContactForm(contactData, creation2);
		submitAddNewContactForm();
		contactCache = null;
	}

	public void modify(ContactData contact) {
		selectContactById(contact.getId());
		editContact();
		fillAddNewContactForm(contact, false);
		updateItem();
		contactCache = null;
	}

	public void delete(ContactData contact) {
		selectContactById(contact.getId());
		deletionContact();
		acceptAlert();
		contactCache = null;
	}

	private Contacts contactCache = null;

	public Contacts all() {
		if (contactCache != null) {
			return new Contacts(contactCache);
		}

		contactCache = new Contacts();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			List<WebElement> cells = element.findElements(By.tagName("td"));
			int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
			String firstName = cells.get(1).getText();
			String lastName = cells.get(2).getText();
			String allPhones = cells.get(5).getText();
			String allEMails = cells.get(4).getText();
			String allAddress = cells.get(3).getText();
			contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
			        .withGroup("new group").withAllPhones(allPhones).withAllEMails(allEMails).withAddress(allAddress));
		}
		return new Contacts(contactCache);
	}

	public ContactData infoFromEditForm(ContactData contact) {
		initContactModificationById(contact.getId());
		String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
		String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
		String home = wd.findElement(By.name("home")).getAttribute("value");
		String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
		String work = wd.findElement(By.name("work")).getAttribute("value");
		String address = wd.findElement(By.name("address")).getAttribute("value");
		String firstEMail = wd.findElement(By.name("email")).getAttribute("value");
		String secondEMail = wd.findElement(By.name("email2")).getAttribute("value");
		String thirdEMail = wd.findElement(By.name("email3")).getAttribute("value");
		wd.navigate().back();
		return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
		        .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
		        .withFirstEMail(firstEMail).withSecondEMail(secondEMail).withThirdEMail(thirdEMail);
	}

	private void initContactModificationById(int id) {
		WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
		WebElement row = checkbox.findElement(By.xpath("./../.."));
		List<WebElement> cells = row.findElements(By.tagName("td"));
		cells.get(7).findElement(By.tagName("a")).click();
	}
}