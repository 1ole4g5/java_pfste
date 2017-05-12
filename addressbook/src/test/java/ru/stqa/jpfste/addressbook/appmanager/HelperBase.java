package ru.stqa.jpfste.addressbook.appmanager;

import java.io.File;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {

	protected WebDriver wd;
	protected ApplicationManager app;

	public HelperBase(ApplicationManager app) throws MalformedURLException {
		this.app = app;
		this.wd = app.getDriver();
	}

	protected void click(By locator) {
		wd.findElement(locator).click();
	}

	protected void type(By locator, String text) {
		click(locator);
		if (text != null) {
			String existingText = wd.findElement(locator).getAttribute("value");
			if (!text.equals(existingText)) {
				wd.findElement(locator).clear();
				wd.findElement(locator).sendKeys(text);
			}
		}
	}

	protected void attach(By locator, File file) {
		if (file != null) {
			wd.findElement(locator).sendKeys(file.getAbsolutePath());
		}
	}

	protected boolean isAlertPresent() {
		try {
			wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	protected boolean isElementPresent(By locator) {
		try {
			wd.findElement(locator);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}
}