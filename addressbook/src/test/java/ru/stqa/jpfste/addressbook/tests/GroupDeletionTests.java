package ru.stqa.jpfste.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;
import ru.stqa.jpfste.addressbook.model.Groups;

public class GroupDeletionTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		if (app.db().groups().size() == 0) {
			app.group().create(new GroupData().withName("new group"));
			app.goTo().returnToHomePage();
		}
	}

	@Test
	public void testGroupDeletion() {
		Groups before = app.db().groups();
		GroupData deletedGroup = before.iterator().next();
		app.group().delete(deletedGroup);
		assertThat(app.group().count(), equalTo(before.size() - 1));
		Groups after = app.db().groups();
		assertThat(after, equalTo(before.without(deletedGroup)));
	}
}