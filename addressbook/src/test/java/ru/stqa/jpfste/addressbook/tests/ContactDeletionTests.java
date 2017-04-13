package ru.stqa.jpfste.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;
import ru.stqa.jpfste.addressbook.model.Contacts;

public class ContactDeletionTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		if (app.contact().all().size() == 0) {
			app.contact().create(
			        new ContactData().withFirstName("First2 name").withLastName("Last name2").withGroup("new group"),
			        true);
		}
	}

	@Test
	public void testContactDeletion() {
		Contacts before = app.contact().all();
		ContactData deletedContact = before.iterator().next();
		app.contact().delete(deletedContact);
		app.goTo().returnToHomePage();
		Contacts after = app.contact().all();
		assertThat(after.size(), equalTo(before.size() - 1));
		assertThat(after, equalTo(before.without(deletedContact)));
	}
}