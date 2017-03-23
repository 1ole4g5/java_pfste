package ru.stqa.jpfste.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() {
		app.navigationHelper.goToGroupPage();
		app.creationHelper.initGroupCreation();
		app.creationHelper.fillGroupForm(new GroupData("new group", "Group header", "Group footer"));
		app.creationHelper.submitGroupCreation();
		app.navigationHelper.returnToHomePage();
	}
}