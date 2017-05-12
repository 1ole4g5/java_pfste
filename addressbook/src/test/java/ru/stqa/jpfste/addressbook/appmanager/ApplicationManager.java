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
	private WebDriver wd;
	private GroupHelper groupHelper;
	private ContactHelper contactHelper;
	private NavigationHelper navigationHelper;
	private SessionHelper sessionHelper;
	private HelperBase helperBase;
	private String browser;
	private DbHelper dbHelper;

	public ApplicationManager(String browser) {
		this.browser = browser;		
		properties = new Properties();
	}

	public void init() throws FileNotFoundException, IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
	}

	public void stop() {
		if (wd != null) {
			wd.quit();
		}
	}

	public WebDriver getDriver() {
		if (wd == null) {
			if (browser.equals(BrowserType.FIREFOX)) {
				FirefoxBinary binary = new FirefoxBinary(new File(properties.getProperty("pathToFirefoxBrowser")));
				wd = new FirefoxDriver(binary, new FirefoxProfile());
			} else if (browser.equals(BrowserType.CHROME)) {
				wd = new ChromeDriver();
			}
			wd.manage().window().maximize();
			wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			wd.get(properties.getProperty("web.baseUrl"));
		}
		return wd;
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public DbHelper db() {
		if (dbHelper == null) {
			dbHelper = new DbHelper();
		}
		return dbHelper;
	}
	
	public GroupHelper group() {
		if (groupHelper == null) {
			groupHelper = new GroupHelper(this);
		}
		return groupHelper;
	}

	public ContactHelper contact() {
		if (contactHelper == null) {
			contactHelper = new ContactHelper(this);
		}
		return contactHelper;
	}

	public HelperBase getHelperBase() {
		if (helperBase == null) {
			helperBase = new HelperBase(this);
		}
		return helperBase;
	}

	public SessionHelper getSessionHelper() {
		if (sessionHelper == null) {
			sessionHelper = new SessionHelper(this);
		}
		return sessionHelper;
	}

	public NavigationHelper goTo() {
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}
}