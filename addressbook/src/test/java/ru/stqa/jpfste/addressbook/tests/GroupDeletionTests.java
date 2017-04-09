package ru.stqa.jpfste.addressbook.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

	@Test
	public void testGroupDeletion() {
		app.getNavigationHelper().goToGroupPage();
		if (! app.getModificationHelper().isThereGroup()) {
			app.getCreationHelper().createGroup(new GroupData("new group", null, null));
		}
		List<GroupData> before = app.getCreationHelper().getGroupList();
		app.getNavigationHelper().goToGroupPage();
		app.getModificationHelper().selectionItem(before.size() - 1);
		app.getModificationHelper().deletionGroup();
		app.getNavigationHelper().goToGroupPage();
		List<GroupData> after = app.getCreationHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size() - 1);
		
		before.remove(before.size() - 1);
		Assert.assertEquals(before, after);
	}
}