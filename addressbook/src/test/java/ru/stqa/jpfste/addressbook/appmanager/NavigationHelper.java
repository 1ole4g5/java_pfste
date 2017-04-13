package ru.stqa.jpfste.addressbook.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

	public NavigationHelper(ApplicationManager app) {
		super(app);
	}

	public void returnToHomePage() {
		if (isElementPresent(By.id("maintable"))) {
			return;
		}
		click(By.linkText("home"));
	}
}