package ru.stqa.jpfste.addressbook.appmanager;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ru.stqa.jpfste.addressbook.model.ContactData;
import ru.stqa.jpfste.addressbook.model.Contacts;

public class ContactHelper extends HelperBase {

	public ContactHelper(WebDriver wd) throws MalformedURLException {
		super(wd);
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
			if (contactData.getGroups().size() > 0) {
				Assert.assertTrue(contactData.getGroups().size() == 1);
				new Select(wd.findElement(By.name("new_group")))
				        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
			}
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

	public void editContact(int id) {
		click(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));
	}

	public void selectContactById(int id) {
		click(By.cssSelector("input[value='" + id + "']"));
	}
	
	public void selectContact() {
		click(By.name("selected[]"));
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

	public void create(ContactData contactData) {
		goToAddNewContactPage();
		fillAddNewContactForm(contactData, true);
		submitAddNewContactForm();
		contactCache = null;
	}

	public void modify(ContactData contact) {
		selectContactById(contact.getId());
		editContact(contact.getId());
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
	
	public void deleteGroup() {
		selectGroupFilter();		
		// selectContactById(group.getId());
		selectContact();
		removeFromGroup();
		contactCache = null;
	}

	public void removeFromGroup() {
		click(By.name("remove"));		
	}

	public void selectGroupFilter() {
		new Select(wd.findElement(By.name("group")))
        .selectByVisibleText("new group"); // (contactData.getGroups().iterator().next().getName());		
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
			String firstName = cells.get(2).getText();
			String lastName = cells.get(1).getText();
			String allPhones = cells.get(5).getText();
			String allEMails = cells.get(4).getText();
			String address = cells.get(3).getText();			
			// String allDetails = firstName + "\n" + lastName + "\n" + address + allPhones;
			contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
			        .withAddress(address).withAllPhones(allPhones).withAllEMails(allEMails));
			// .withAllDetails(allDetails));
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
		
		if (! home.equals("")) {
			home = "H: " + home;
		}
		if (! mobile.equals("")) {
			mobile = "M: " + mobile;
		}
		if (! work.equals("")) {
			work = "W: " + work;
		}
		
		String allDataEditForm = firstName + lastName + address + home + mobile + work;		
		wd.navigate().back();
		return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
		        .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
		        .withFirstEMail(firstEMail).withSecondEMail(secondEMail).withThirdEMail(thirdEMail)
		        .withAllDataEditForm(allDataEditForm);
	}

	private void initContactModificationById(int id) {
		WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
		WebElement row = checkbox.findElement(By.xpath("./../.."));
		List<WebElement> cells = row.findElements(By.tagName("td"));
		cells.get(7).findElement(By.tagName("a")).click();
	}

	private void initContactModificationDetailsById(int id) {
		WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
		WebElement row = checkbox.findElement(By.xpath("./../.."));
		List<WebElement> cells = row.findElements(By.tagName("td"));
		cells.get(6).findElement(By.tagName("a")).click();
	}

	public ContactData infoFromDetailsForm(ContactData contact) {
		initContactModificationDetailsById(contact.getId());
		String allDataDetailForm = wd.findElement(By.cssSelector("#content")).getText();
		wd.navigate().back();
		return new ContactData().withId(contact.getId()).withAllDataDetailsForm(allDataDetailForm);
	}
}