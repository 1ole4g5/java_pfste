package ru.stqa.jpfste.addressbook.tests;

import java.util.Comparator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

	@Test
	public void testContactModification() {
		if (! app.getModificationHelper().isThereGroup()) {
			app.getCreationHelper().createContact(new ContactData("First name", "Last name", null, null, null, "new group"), true);
		}
		List<ContactData> before = app.getCreationHelper().getContactList();
		app.getModificationHelper().selectionItem(before.size() - 1);
		app.getModificationHelper().editContact();
		ContactData contact = new ContactData(before.get(before.size() -1).getId(), "First name2", "Last name2", null, null, null, null);
		app.getCreationHelper().fillAddNewContactForm(contact, false);
		app.getModificationHelper().updateItem();
		app.getNavigationHelper().returnToHomePage();
		List<ContactData> after = app.getCreationHelper().getContactList();
		Assert.assertEquals(after.size(), before.size());
				
		before.remove(before.size() - 1);
		before.add(contact);
		Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c1.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}
}