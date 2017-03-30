package ru.stqa.jpfste.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

	@Test
	public void testContactModification() {
		if (! app.modificationHelper.isThereGroup()) {
			app.creationHelper.createContact(new ContactData("First2 name", "Last name2", null, null, null, "new group"), true);
		}
		app.modificationHelper.selectionItem();
		app.modificationHelper.editContact();
		app.creationHelper.fillAddNewContactForm(new ContactData("First name2", "Last name2", "Nick name2", null, "E-mail2", null), false);
		app.modificationHelper.updateItem();
		app.navigationHelper.returnToHomePage();
	}
}