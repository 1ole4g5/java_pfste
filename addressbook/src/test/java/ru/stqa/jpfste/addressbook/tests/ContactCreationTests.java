package ru.stqa.jpfste.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreation() {
		app.creationHelper.goToAddNewContactPage();
		app.creationHelper.fillAddNewContactForm(new ContactData("First name", "Last name", "Nick name", "Address", "E-mail"));
		app.creationHelper.submitAddNewContactForm();
		app.navigationHelper.returnToHomePage();
	}
}