package ru.stqa.jpfste.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import ru.stqa.jpfste.addressbook.model.ContactData;
import ru.stqa.jpfste.addressbook.model.Contacts;
import ru.stqa.jpfste.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> validContactsFromJson() throws IOException {
		BufferedReader reader = new BufferedReader(
		        new FileReader(new File("addressbook/src/test/resources/contacts.json")));
		String json = "";
		String line = reader.readLine();
		while (line != null) {
			json += line;
			line = reader.readLine();
		}
		Gson gson = new Gson();
		List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
		}.getType());
		reader.close();
		return contacts.stream().map((g) -> new Object[] { g }).collect(Collectors.toList()).iterator();
	}

	@DataProvider
	public Iterator<Object[]> validContactsFromXml() throws IOException {
		BufferedReader reader = new BufferedReader(
		        new FileReader(new File("addressbook/src/test/resources/contacts.xml")));
		String xml = "";
		String line = reader.readLine();
		while (line != null) {
			xml += line;
			line = reader.readLine();
		}
		XStream xstream = new XStream(new StaxDriver());
		xstream.processAnnotations(GroupData.class);
		List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
		reader.close();
		return contacts.stream().map((g) -> new Object[] { g }).collect(Collectors.toList()).iterator();
	}

	@DataProvider
	public Iterator<Object[]> validContactsFromCsv() throws IOException {
		List<Object[]> list = new ArrayList<Object[]>();
		BufferedReader reader = new BufferedReader(
		        new FileReader(new File("addressbook/src/test/resources/contacts.csv")));
		String line = reader.readLine();
		while (line != null) {
			String[] split = line.split(";");
			list.add(new Object[] { new ContactData().withFirstName(split[0]).withLastName(split[1])
			        .withAddress(split[2]).withMobilePhone(split[3]) });
			line = reader.readLine();
		}
		reader.close();
		return list.iterator();
	}

	@Test(dataProvider = "validContactsFromJson")
	public void testContactCreation(ContactData contact) {
		Contacts before = app.contact().all();
		app.contact().goToAddNewContactPage();
		app.contact().create(contact);
		app.goTo().returnToHomePage();
		assertThat(app.contact().count(), equalTo(before.size() + 1));
		Contacts after = app.contact().all();
		assertThat(after,
		        equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
	}
}