package ru.stqa.jpfste.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

	@Test
	public void testGroupDeletion() {
		app.navigationHelper.goToGroupPage();
		if (! app.modificationHelper.isThereGroup()) {
			app.creationHelper.createGroup(new GroupData("new group", "Group header", "Group footer"));
		}
		app.navigationHelper.goToGroupPage();
		app.modificationHelper.selectionItem();
		app.modificationHelper.deletionGroup();
		app.navigationHelper.returnToHomePage();
	}
}