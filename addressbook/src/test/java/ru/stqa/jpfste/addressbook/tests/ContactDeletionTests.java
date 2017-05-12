package ru.stqa.jpfste.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.MalformedURLException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;
import ru.stqa.jpfste.addressbook.model.Contacts;

public class ContactDeletionTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() throws MalformedURLException {
		if (app.db().contacts().size() == 0) {
			app.contact().create(
			        new ContactData().withFirstName("First name").withLastName("Last name").withAddress("address"));
		}
	}

	@Test
	public void testContactDeletion() throws MalformedURLException {
		Contacts before = app.db().contacts();
		ContactData deletedContact = before.iterator().next();
		app.contact().delete(deletedContact);
		app.goTo().returnToHomePage();
		assertThat(app.contact().count(), equalTo(before.size() - 1));
		Contacts after = app.db().contacts();
		assertThat(after, equalTo(before.without(deletedContact)));
		verifyContactListUI();
	}
}