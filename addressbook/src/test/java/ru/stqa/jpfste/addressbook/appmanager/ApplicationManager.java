package ru.stqa.jpfste.addressbook.appmanager;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.BrowserType;

public class ApplicationManager {

	WebDriver wd;

	public GroupHelper groupHelper;
	public ContactHelper contactHelper;
	public NavigationHelper navigationHelper;
	public SessionHelper sessionHelper;
	public HelperBase helperBase;
	private String browser;

	public ApplicationManager(String browser) {
		this.browser = browser;
	}

	public void init() {
		FirefoxBinary binary = new FirefoxBinary(new File("C:/Program Files/Mozilla Firefox/firefox.exe"));
		if (browser.equals(BrowserType.FIREFOX)) {
			wd = new FirefoxDriver(binary, new FirefoxProfile());
		} else if (browser.equals(BrowserType.CHROME)) {
			wd = new ChromeDriver();
		}
		wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/");
		groupHelper = new GroupHelper(this);
		contactHelper = new ContactHelper(this);
		navigationHelper = new NavigationHelper(this);
		sessionHelper = new SessionHelper(this);
		helperBase = new HelperBase(this);
		sessionHelper.login("admin", "secret");
	}

	public void stop() {
		wd.quit();
	}

	public GroupHelper group() {
		return groupHelper;
	}

	public ContactHelper contact() {
		return contactHelper;
	}

	public HelperBase getHelperBase() {
		return helperBase;
	}

	public SessionHelper getSessionHelper() {
		return sessionHelper;
	}

	public NavigationHelper goTo() {
		return navigationHelper;
	}
}