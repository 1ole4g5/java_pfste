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

	public CreationHelper creationHelper;
	public NavigationHelper navigationHelper;
	public SessionHelper sessionHelper;
	public ModificationHelper modificationHelper;
	public HelperBase helperBase;
	private String browser;
	
	public ApplicationManager (String browser) {
		this.browser = browser;
	}

	public void init() {
		FirefoxBinary binary = new FirefoxBinary(new File("C:/Program Files/Mozilla Firefox/firefox.exe"));
		if (browser.equals(BrowserType.FIREFOX)) {
			wd = new FirefoxDriver(binary, new FirefoxProfile());;
		} else if (browser.equals(BrowserType.CHROME)) {
			wd = new ChromeDriver();
		}		
		wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/");
		creationHelper = new CreationHelper(this);
		navigationHelper = new NavigationHelper(this);
		sessionHelper = new SessionHelper(this);
		modificationHelper = new ModificationHelper(this);
		helperBase = new HelperBase(this);
		sessionHelper.login("admin", "secret");
	}

	public void stop() {
		wd.quit();
	}
}