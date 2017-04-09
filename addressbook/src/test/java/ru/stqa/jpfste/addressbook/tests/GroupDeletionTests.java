package ru.stqa.jpfste.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

	@Test
	public void testGroupDeletion() {
		app.getNavigationHelper().goToGroupPage();
		if (! app.getModificationHelper().isThereGroup()) {
			app.getCreationHelper().createGroup(new GroupData("new group", "Group header", "Group footer"));
		}
		app.getNavigationHelper().goToGroupPage();
		app.getModificationHelper().selectionItem();
		app.getModificationHelper().deletionGroup();
		app.getNavigationHelper().returnToHomePage();
	}
}