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

import ru.stqa.jpfste.addressbook.model.GroupData;
import ru.stqa.jpfste.addressbook.model.Groups;

public class GroupCreationTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> validGroupsFromJson() throws IOException {
		try (BufferedReader reader = new BufferedReader(
		        new FileReader(new File("addressbook/src/test/resources/groups.json")))) {
			String json = "";
			String line = reader.readLine();
			while (line != null) {
				json += line;
				line = reader.readLine();
			}
			Gson gson = new Gson();
			List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
			}.getType());
			return groups.stream().map((g) -> new Object[] { g }).collect(Collectors.toList()).iterator();
		}
	}

	@DataProvider
	public Iterator<Object[]> validGroupsFromXml() throws IOException {
		try (BufferedReader reader = new BufferedReader(
		        new FileReader(new File("addressbook/src/test/resources/groups.xml")))) {
			String xml = "";
			String line = reader.readLine();
			while (line != null) {
				xml += line;
				line = reader.readLine();
			}
			XStream xstream = new XStream(new StaxDriver());
			xstream.processAnnotations(GroupData.class);
			List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
			return groups.stream().map((g) -> new Object[] { g }).collect(Collectors.toList()).iterator();
		}
	}

	@DataProvider
	public Iterator<Object[]> inValidGroups() throws IOException {
		List<Object[]> list = new ArrayList<Object[]>();
		try (BufferedReader reader = new BufferedReader(
		        new FileReader(new File("addressbook/src/test/resources/groups.csv")))) {
			String line = reader.readLine();
			while (line != null) {
				String[] split = line.split(";");
				list.add(new Object[] { new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2]) });
				line = reader.readLine();
			}
			return list.iterator();
		}
	}

	@Test(dataProvider = "validGroupsFromJson")
	public void testGroupCreation(GroupData group) {
		app.group().groupPage();
		Groups before = app.db().groups();
		app.group().create(group);
		Groups after = app.db().groups();
		assertThat(app.group().count(), equalTo(before.size() + 1));
		assertThat(after,
		        equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
		verifyGroupListUI();
	}

	@Test(dataProvider = "inValidGroups", enabled = false)
	public void testBadGroupCreation(GroupData group) {
		app.group().groupPage();
		Groups before = app.db().groups();
		app.group().create(group);
		assertThat(app.group().count(), equalTo(before.size()));
		Groups after = app.db().groups();
		assertThat(after, equalTo(before));
		verifyGroupListUI();
	}
}