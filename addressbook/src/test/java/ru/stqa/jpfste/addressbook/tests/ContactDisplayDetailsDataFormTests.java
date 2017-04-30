package ru.stqa.jpfste.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.ContactData;

public class ContactDisplayDetailsDataFormTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		if (app.contact().all().size() == 0) {
			app.contact().create(new ContactData().withFirstName("FirstName").withLastName("LastName")
			        .withAddress("789-)01").withHomePhone("+7(111)").withMobilePhone("2-2-2").withWorkPhone("3 3 3"));
		}
	}

	@Test
	public void testContactDetails() {
		app.goTo().returnToHomePage();
		ContactData contact = app.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
		ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);		
		assertThat(cleaned(contactInfoFromEditForm.getAllDataEditForm()), equalTo(mergeDetails(contactInfoFromDetailsForm)));
	}

	private String mergeDetails(ContactData contact) {
		return Arrays.asList(contact.getAllDataDetailsForm()).stream().filter((s) -> !(s == null || s.equals("")))
		        .map(ContactDisplayDetailsDataFormTests::cleaned)
		        .collect(Collectors.joining("\n"));
	}

	public static String cleaned(String data) {
		return data.replaceAll("\\s", "").replaceAll("[-()]", "");
	}
}