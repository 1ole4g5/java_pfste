package ru.stqa.jpfste.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;
import ru.stqa.jpfste.addressbook.model.Contacts;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreation() {
		Contacts before = app.contact().all();
		app.contact().goToAddNewContactPage();
		ContactData contact = new ContactData().withFirstName("First name").withLastName("Last name")
		        .withGroup("new group");
		app.contact().create(contact);
		app.goTo().returnToHomePage();
		assertThat(app.contact().count(), equalTo(before.size() + 1));
		Contacts after = app.contact().all();
		assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
	}
}