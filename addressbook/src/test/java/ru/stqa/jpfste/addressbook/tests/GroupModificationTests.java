package ru.stqa.jpfste.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.MalformedURLException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;
import ru.stqa.jpfste.addressbook.model.Groups;

public class GroupModificationTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() throws MalformedURLException {
		if (app.db().groups().size() == 0) {
			if (app.group().all().size() == 0) {
				app.group().create(new GroupData().withName("new group"));
			}
		}
	}

	@Test
	public void testGroupModification() throws MalformedURLException {
		Groups before = app.db().groups();
		GroupData modifiedGroup = before.iterator().next();
		GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("new group").withHeader("new header2")
		        .withFooter("new footer2");
		app.group().groupPage();
		app.group().modify(group);
		assertThat(app.group().count(), equalTo(before.size()));
		Groups after = app.db().groups();
		assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
		verifyGroupListUI();
	}
}