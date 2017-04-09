package ru.stqa.jpfste.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

	@Test
	public void testContactDeletion() {
		if (! app.getModificationHelper().isThereGroup()) {
			app.getCreationHelper().createContact(new ContactData("First2 name", "Last name2", null, null, null, "new group"), true);
		}
		app.getModificationHelper().selectionItem();
		app.getModificationHelper().deletionContact();
		app.getModificationHelper().acceptAlert();
		app.getNavigationHelper().returnToHomePage();
	}
}