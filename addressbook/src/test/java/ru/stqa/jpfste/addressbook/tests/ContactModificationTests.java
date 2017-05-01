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
		if (app.db().contacts().size() == 0) {
			app.contact().create(
			        new ContactData().withFirstName("First name").withLastName("Last name").withAddress("address")
			        .withMobilePhone("mobilePhone"));
		}
	}

	@Test
	public void testContactModification() {
		Contacts before = app.db().contacts();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("First name_")
		        .withLastName("Last name_").withAddress("address_").withMobilePhone("mobilePhone_");
		app.contact().modify(contact);
		app.goTo().returnToHomePage();
		assertThat(app.contact().count(), equalTo(before.size()));
		Contacts after = app.db().contacts();
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
		verifyContactListUI();
	}
}