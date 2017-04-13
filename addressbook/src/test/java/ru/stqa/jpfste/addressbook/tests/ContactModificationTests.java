package ru.stqa.jpfste.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;
import ru.stqa.jpfste.addressbook.model.Contacts;

public class ContactModificationTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		if (app.contact().all().size() == 0) {
			app.contact().create(
			        new ContactData().withFirstName("First2 name").withLastName("Last name2").withGroup("new group"),
			        true);
		}
	}

	@Test
	public void testContactModification() {
		Contacts before = app.contact().all();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("First name2")
		        .withLastName("Last name2");
		app.contact().modify(contact);
		app.goTo().returnToHomePage();
		Contacts after = app.contact().all();
		assertThat(after.size(), equalTo(before.size()));
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
	}
}