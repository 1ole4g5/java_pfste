package ru.stqa.jpfste.addressbook.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

	@Test
	public void testContactDeletion() {
		if (! app.getModificationHelper().isThereGroup()) {
			app.getCreationHelper().createContact(new ContactData("First2 name", "Last name2", null, null, null, "new group"), true);
		}
		List<ContactData> before = app.getCreationHelper().getContactList();
		app.getModificationHelper().selectionItem(before.size() - 1);
		app.getModificationHelper().deletionContact();
		app.getModificationHelper().acceptAlert();
		app.getNavigationHelper().returnToHomePage();
		List<ContactData> after = app.getCreationHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() - 1);
		
		before.remove(before.size() - 1);
		Assert.assertEquals(before, after);
	}
}