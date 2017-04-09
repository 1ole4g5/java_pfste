package ru.stqa.jpfste.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

	@Test
	public void testGroupModification() {
		app.getNavigationHelper().goToGroupPage();
		if (! app.getModificationHelper().isThereGroup()) {
			app.getCreationHelper().createGroup(new GroupData("new group", "Group header", "Group footer"));
		}
		app.getNavigationHelper().goToGroupPage();
		app.getModificationHelper().selectionItem();
		app.getModificationHelper().editGroup();
		app.getCreationHelper().fillGroupForm(new GroupData("new group2", "Group header2", "Group footer2"));
		app.getModificationHelper().updateItem();
		app.getNavigationHelper().returnToHomePage();
	}
}