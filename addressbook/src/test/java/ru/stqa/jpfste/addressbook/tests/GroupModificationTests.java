package ru.stqa.jpfste.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

	@Test
	public void testGroupModification() {
		app.navigationHelper.goToGroupPage();
		if (! app.modificationHelper.isThereGroup()) {
			app.creationHelper.createGroup(new GroupData("new group", "Group header", "Group footer"));
		}
		app.navigationHelper.goToGroupPage();
		app.modificationHelper.selectionItem();
		app.modificationHelper.editGroup();
		app.creationHelper.fillGroupForm(new GroupData("new group2", "Group header2", "Group footer2"));
		app.modificationHelper.updateItem();
		app.navigationHelper.returnToHomePage();
	}
}