package ru.stqa.jpfste.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;
import ru.stqa.jpfste.addressbook.model.Groups;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() {
		app.group().groupPage();
		Groups before = app.group().all();
		GroupData group = new GroupData().withName("new group");
		app.group().create(group);
		Groups after = app.group().all();
		assertThat(app.group().count(), equalTo(before.size() + 1));
		assertThat(after,
		        equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
	}

	@Test
	public void testBadGroupCreation() {
		app.group().groupPage();
		Groups before = app.group().all();
		GroupData group = new GroupData().withName("new group'");
		app.group().create(group);
		assertThat(app.group().count(), equalTo(before.size()));
		Groups after = app.group().all();
		assertThat(after, equalTo(before));
	}
}