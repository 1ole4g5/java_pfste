package ru.stqa.jpfste.addressbook.appmanager;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ru.stqa.jpfste.addressbook.model.GroupData;
import ru.stqa.jpfste.addressbook.model.Groups;

public class GroupHelper extends HelperBase {

	public GroupHelper(ApplicationManager app) throws MalformedURLException {
		super(app);
	}

	public void fillGroupForm(GroupData groupData) {
		type(By.name("group_name"), groupData.getName());
		type(By.name("group_header"), groupData.getHeader());
		type(By.name("group_footer"), groupData.getFooter());
	}

	public void returnToGroupCreation() {
		click(By.linkText("group page"));
	}

	public void submitGroupCreation() {
		click(By.name("submit"));
	}

	public void initGroupCreation() {
		click(By.name("new"));
	}

	public void editGroup() {
		click(By.name("edit"));
	}

	public void deletionGroup() {
		click(By.name("delete"));
	}

	public boolean isThereGroup() {
		return isElementPresent(By.name("selected[]"));
	}

	public void selectGroupById(int id) {
		wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
	}

	public void updateItem() {
		click(By.name("update"));
	}

	public void create(GroupData groupData) {
		groupPage();
		initGroupCreation();
		fillGroupForm(groupData);
		submitGroupCreation();
		groupCache = null;
		groupPage();
	}

	public void modify(GroupData group) {
		selectGroupById(group.getId());
		editGroup();
		fillGroupForm(group);
		updateItem();
		groupCache = null;
		groupPage();
	}

	public void delete(GroupData group) {
		groupPage();
		selectGroupById(group.getId());
		deletionGroup();
		groupCache = null;
		groupPage();
	}

	public int count() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public void groupPage() {
		if (isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText().equals("Groups")
		        && isElementPresent(By.name("new"))) {
			return;
		}
		click(By.linkText("groups"));
	}

	private Groups groupCache = null;

	public Groups all() {
		if (groupCache != null) {
			return new Groups(groupCache);
		}

		groupCache = new Groups();
		List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
		for (WebElement element : elements) {
			String name = element.getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			groupCache.add(new GroupData().withId(id).withName(name));
		}
		return new Groups(groupCache);
	}
}