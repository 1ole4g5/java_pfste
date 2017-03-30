package ru.stqa.jpfste.addressbook.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {
	
	public NavigationHelper(ApplicationManager app) {
		super(app);
	}

	public void goToGroupPage() {
		if (isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText().equals("Groups")
				&& isElementPresent(By.name("new"))) {
			return;
		}
		click(By.linkText("groups"));
	}

	public void returnToHomePage() {
		if (isElementPresent(By.id("maintable"))) {
			return;
		}
		click(By.linkText("home"));
	}
}