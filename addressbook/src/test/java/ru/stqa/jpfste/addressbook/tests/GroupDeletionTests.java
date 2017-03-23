package ru.stqa.jpfste.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

	@Test
	public void testGroupDeletion() {
		app.navigationHelper.goToGroupPage();
		app.modificationHelper.selectionItem();
		app.modificationHelper.deletionGroup();
	}
}