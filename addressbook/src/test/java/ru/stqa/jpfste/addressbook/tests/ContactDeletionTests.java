package ru.stqa.jpfste.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

	@Test
	public void testContactDeletion() {
		if (! app.modificationHelper.isThereGroup()) {
			app.creationHelper.createContact(new ContactData("First2 name", "Last name2", null, null, null, "new group"), true);
		}
		app.modificationHelper.selectionItem();
		app.modificationHelper.deletionContact();
		app.modificationHelper.acceptAlert();
		app.navigationHelper.returnToHomePage();
	}
}