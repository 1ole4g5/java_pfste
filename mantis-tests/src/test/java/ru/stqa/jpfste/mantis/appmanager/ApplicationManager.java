package ru.stqa.jpfste.mantis.appmanager;

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
	private String browser;
	private RegistrationHelper registrationHelper;
	private FtpHelper ftp;
	private MailHelper mailHelper;
	private LoginHelper loginHelper;
	private NavigationHelper navigationHelper;
	private DbHelper dbHelper;
	private UserHelper userHelper;

	public ApplicationManager(String browser) {
		this.browser = browser;
		properties = new Properties();
	}

	public void init() throws FileNotFoundException, IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("mantis-tests/src/test/resources/%s.properties", target))));
	}

	public void stop() {
		if (wd != null) {
			wd.quit();
		}
	}

	public HttpSession newSession() {
		return new HttpSession(this);
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public RegistrationHelper registration() {
		if (registrationHelper == null) {
			registrationHelper = new RegistrationHelper(this);
		}
		return registrationHelper;
	}
	
	public FtpHelper ftp() {
		if (ftp == null) {
			ftp = new FtpHelper(this);
		}
		return ftp;
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
	
	public MailHelper mail() {
		if (mailHelper == null) {
			mailHelper = new MailHelper(this);
		}
		return mailHelper;
	}
	
	public LoginHelper loginHelper() {
		if (loginHelper == null) {
			loginHelper = new LoginHelper(this);
		}
		return loginHelper;
	}
	
	public NavigationHelper goTo() {
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}

	public DbHelper db() {
		if (dbHelper == null) {
			dbHelper = new DbHelper();
		}
		return dbHelper;
	}

	public UserHelper user() {
		if (userHelper == null) {
			userHelper = new UserHelper(this);
		}
		return userHelper;
	}
}