package ru.stqa.jpfste.addressbook.tests;

import java.util.Comparator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

	@Test
	public void testGroupModification() {
		app.getNavigationHelper().goToGroupPage();
		if (! app.getModificationHelper().isThereGroup()) {
			app.getCreationHelper().createGroup(new GroupData("new group", null, null));
		}
		List<GroupData> before = app.getCreationHelper().getGroupList();
		app.getModificationHelper().selectionItem(before.size() - 1);
		app.getModificationHelper().editGroup();
		GroupData group = new GroupData(before.get(before.size() -1).getId(), "new group", "new header2", "new footer2");
		app.getCreationHelper().fillGroupForm(group);
		app.getModificationHelper().updateItem();
		app.getNavigationHelper().goToGroupPage();
		List<GroupData> after = app.getCreationHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size());
				
		before.remove(before.size() - 1);
		before.add(group);
		Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g1.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}
}