package ru.stqa.jpfste.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import ru.stqa.jpfste.addressbook.model.ContactData;
import ru.stqa.jpfste.addressbook.model.GroupData;

public class CreationHelper extends HelperBase {

	public CreationHelper(FirefoxDriver wd) {
		super(wd);
	}

	public void fillAddNewContactForm(ContactData contactData) {
		type(By.name("firstname"), contactData.getFirstName());
		type(By.name("lastname"), contactData.getLastName());
		type(By.name("nickname"), contactData.getNickName());
		type(By.name("address"), contactData.getAddress());
		type(By.name("email"), contactData.getEMail());
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
}