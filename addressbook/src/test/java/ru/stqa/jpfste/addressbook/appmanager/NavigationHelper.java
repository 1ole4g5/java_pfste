package ru.stqa.jpfste.addressbook.appmanager;

import java.net.MalformedURLException;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

	public NavigationHelper(ApplicationManager app) throws MalformedURLException {
		super(app);
	}

	public void returnToHomePage() {
		if (isElementPresent(By.id("maintable"))) {
			return;
		}
		click(By.linkText("home"));
	}
}