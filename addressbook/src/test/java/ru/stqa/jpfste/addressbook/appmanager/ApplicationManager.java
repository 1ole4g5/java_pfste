package ru.stqa.jpfste.addressbook.appmanager;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class ApplicationManager {
	FirefoxDriver wd;

	public CreationHelper creationHelper;
	public NavigationHelper navigationHelper;
	public SessionHelper sessionHelper;

	public void init() {
		FirefoxBinary binary = new FirefoxBinary(new File("C:/Program Files/Mozilla Firefox/firefox.exe"));
		wd = new FirefoxDriver(binary, new FirefoxProfile());
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/");
		creationHelper = new CreationHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		sessionHelper = new SessionHelper(wd);
		sessionHelper.login("admin", "secret");
	}

	public void stop() {
		wd.quit();
	}
}