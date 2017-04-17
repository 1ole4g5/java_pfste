package ru.stqa.jpfste.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;

public class ContactDisplayDataFormTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		if (app.contact().all().size() == 0) {
			app.contact().create(new ContactData().withFirstName("First name").withLastName("Last name")
			        .withAddress("789-)01").withFirstEMail("test@test.com").withSecondEMail("1@1").withThirdEMail("1+1")
			        .withHomePhone("+7(111)").withMobilePhone("2-2-2").withWorkPhone("3 3 3").withGroup("new group"));
		}
	}

	@Test
	public void testContactPhones() {
		app.goTo().returnToHomePage();
		ContactData contact = app.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
		assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
	}
	
	@Test
	public void testContactAddress() {
		app.goTo().returnToHomePage();
		ContactData contact = app.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
		assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
	}

	@Test
	public void testContactEMails() {
		app.goTo().returnToHomePage();
		ContactData contact = app.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
		assertThat(contact.getAllEMails(), equalTo(mergeEMails(contactInfoFromEditForm)));
	}
	
	private String mergePhones(ContactData contact) {
		return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
				.stream().filter((s) -> !s.equals(""))
				.map(ContactDisplayDataFormTests::cleaned).collect(Collectors.joining("\n"));
	}
	
	public static String cleaned(String phone) {
		return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
	}
	
	private String mergeAddress(ContactData contact) {
		return Arrays.asList(contact.getAddress())
				.stream().filter((s) -> !s.equals(""))
				.collect(Collectors.joining("\n"));
	}
	
	private String mergeEMails(ContactData contact) {
		return Arrays.asList(contact.getFirstEMail(), contact.getSecondEMail(), contact.getThirdEMail())
				.stream().filter((s) -> !s.equals(""))
				.collect(Collectors.joining("\n"));
	}
}
