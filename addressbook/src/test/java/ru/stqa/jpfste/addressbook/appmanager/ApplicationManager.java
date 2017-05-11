package ru.stqa.jpfste.addressbook.appmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.BrowserType;

public class ApplicationManager {
	private final Properties properties;
	WebDriver wd;

	public GroupHelper groupHelper;
	public ContactHelper contactHelper;
	public NavigationHelper navigationHelper;
	public SessionHelper sessionHelper;
	public HelperBase helperBase;
	private String browser;
	private DbHelper dbHelper;

	public ApplicationManager(String browser) {
		this.browser = browser;		
		properties = new Properties();
	}

	public void init() throws FileNotFoundException, IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("addressbook/src/test/resources/%s.properties", target))));
		
		FirefoxBinary binary = new FirefoxBinary(new File(properties.getProperty("pathToFirefoxBrowser")));
		
		dbHelper = new DbHelper();
		
		if (browser.equals(BrowserType.FIREFOX)) {
			wd = new FirefoxDriver(binary, new FirefoxProfile());
		} else if (browser.equals(BrowserType.CHROME)) {
			wd = new ChromeDriver();
		}
		wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		wd.get(properties.getProperty("web.baseUrl"));
		groupHelper = new GroupHelper(this);
		contactHelper = new ContactHelper(this);
		navigationHelper = new NavigationHelper(this);
		sessionHelper = new SessionHelper(this);
		helperBase = new HelperBase(this);
		sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
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
	
	public DbHelper db() {
		return dbHelper;
	}
}