package ru.stqa.jpfste.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ru.stqa.jpfste.addressbook.model.ContactData;
import ru.stqa.jpfste.addressbook.model.Contacts;
import ru.stqa.jpfste.addressbook.model.GroupData;
import ru.stqa.jpfste.addressbook.model.Groups;


public class ContactInGroupTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> validContactsFromJson() throws IOException {
		try (BufferedReader reader = new BufferedReader(
		        new FileReader(new File("addressbook/src/test/resources/contacts.json")))) {
			String json = "";
			String line = reader.readLine();
			while (line != null) {
				json += line;
				line = reader.readLine();
			}
			Gson gson = new Gson();
			List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
			}.getType());
			return contacts.stream().map((g) -> new Object[] { g }).collect(Collectors.toList()).iterator();
		}
	}
	
	@BeforeMethod
	public void ensurePreconditions() {
		app.contact().selectGroupFilter();
		
		if (app.contact().all().size() == 0) {
			Groups groups = app.db().groups();
			app.contact().create(new ContactData().withFirstName("First name").withLastName("Last name")
			        .withAddress("address").inGroup(groups.iterator().next()));
		}
		
		if (app.db().groups().size() == 0) {
			app.group().create(new GroupData().withName("new group"));
			app.goTo().returnToHomePage();
		}
		
//		if (app.db().contacts().size() == 0) {
//			Groups groups = app.db().groups();
//			app.contact().create(new ContactData().withFirstName("First name").withLastName("Last name")
//			        .withAddress("address").inGroup(groups.iterator().next()));
//		}
		

	}
	
	@Test(dataProvider = "validContactsFromJson", enabled = false)
	public void testContactAddToGroup(ContactData contact) {
		app.contact().selectGroupFilter();
		app.contact().all();		
		Contacts before = app.db().contacts();
		Groups groups = app.db().groups();
		Groups beforeGroupInContact = app.db().groupInContact();
		app.contact().create(new ContactData().withFirstName("First name").withLastName("Last name")
		        .withAddress("address").inGroup(groups.iterator().next()));
		app.goTo().returnToHomePage();
		assertThat(app.db().contacts().size(), equalTo(before.size() + 1));
		Groups afterGroupInContact = app.db().groupInContact();
		assertThat(afterGroupInContact, equalTo(beforeGroupInContact));
	}
	
	@Test(dataProvider = "validContactsFromJson")
	public void testContactDeleteFromGroup(ContactData contact) {

		Groups beforeInGroup = app.db().groupInContact();
		
		System.out.println("beforeInGroup size: " + beforeInGroup.size());
		System.out.println("beforeInGroup: " + beforeInGroup);
		
		Contacts before = app.db().contacts();
		// GroupData deletedGr = app.db().groups().iterator().next();
		
		System.out.println("groupInContact1: " + app.db().groupInContact());
		
		app.contact().deleteGroup();
		app.goTo().returnToHomePage();
		
		Contacts after = app.db().contacts();
		
		assertThat(after.size(), equalTo(before.size()));	
		
		Groups afterInGroup = app.db().groupInContact();
		
		System.out.println("groups: " + app.db().groups());
		System.out.println("afterInGroup size: " + afterInGroup.size());
		System.out.println("afterInGroup: " + afterInGroup);
		
		assertThat(afterInGroup.size(), equalTo(beforeInGroup.size() - 1));
	}
}
