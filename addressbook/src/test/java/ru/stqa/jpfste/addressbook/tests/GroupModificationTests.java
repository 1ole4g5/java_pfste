package ru.stqa.jpfste.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;
import ru.stqa.jpfste.addressbook.model.Groups;

public class GroupModificationTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		if (app.group().all().size() == 0) {
			app.group().create(new GroupData().withName("new group"));
		}
	}

	@Test
	public void testGroupModification() {
		Groups before = app.group().all();
		GroupData modifiedGroup = before.iterator().next();
		GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("new group").withHeader("new header2")
		        .withFooter("new footer2");
		app.group().modify(group);
		Groups after = app.group().all();
		assertThat(after.size(), equalTo(before.size()));
		assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
	}
}