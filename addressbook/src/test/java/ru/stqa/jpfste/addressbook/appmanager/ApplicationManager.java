package ru.stqa.jpfste.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    public WebDriver wd;
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

        dbHelper = new DbHelper();

        if ("".equals(properties.getProperty("selenium.server"))) {
            if (browser.equals(BrowserType.FIREFOX)) {
                FirefoxBinary binary = new FirefoxBinary(new File(properties.getProperty("pathToFirefoxBrowser")));
                wd = new FirefoxDriver(binary, new FirefoxProfile());
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(this);
        contactHelper = new ContactHelper(this);
        helperBase = new HelperBase(this);
        sessionHelper = new SessionHelper(this);
        navigationHelper = new NavigationHelper(this);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public DbHelper db() {
        return dbHelper;
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