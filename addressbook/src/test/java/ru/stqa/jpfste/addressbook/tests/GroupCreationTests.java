package ru.stqa.jpfste.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() {
		app.getNavigationHelper().goToGroupPage();
		app.getCreationHelper().createGroup(new GroupData("new group", "Group header", "Group footer"));
	}
}