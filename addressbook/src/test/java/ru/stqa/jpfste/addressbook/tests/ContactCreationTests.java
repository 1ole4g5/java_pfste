package ru.stqa.jpfste.addressbook.tests;

import java.util.Comparator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreation() {
		List<ContactData> before = app.getCreationHelper().getContactList();
		app.getCreationHelper().goToAddNewContactPage();
		ContactData contact = new ContactData("First2 name", "Last name2", null, null, null, "new group");
		app.getCreationHelper().createContact(contact, true);
		List<ContactData> after = app.getCreationHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() + 1);
		
		before.add(contact);
		Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c1.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}
}