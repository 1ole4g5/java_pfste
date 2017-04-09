package ru.stqa.jpfste.addressbook.tests;

import java.util.Comparator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.jpfste.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() {
		app.getNavigationHelper().goToGroupPage();
		List<GroupData> before = app.getCreationHelper().getGroupList();
		GroupData group = new GroupData("new group", null, null);
		app.getCreationHelper().createGroup(group);
		app.getNavigationHelper().goToGroupPage();
		List<GroupData> after = app.getCreationHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size() + 1);

		before.add(group);
		Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g1.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}
}