package ru.stqa.jpfste.mantis.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.jpfste.mantis.appmanager.ApplicationManager;


public class TestBase {
	
	Logger logger = LoggerFactory.getLogger(TestBase.class);
	
	protected static final ApplicationManager app 
	= new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

	@BeforeSuite
	public void setUp() throws Exception {
		app.init();
		app.ftp().upload(new File("mantis-tests/src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");
	}
	
	@BeforeMethod
	public void logTestStart(Method m, Object[] p) {
		logger.info("Start test: " + m.getName() + " with parameters " + Arrays.asList(p));
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws IOException {
		app.ftp().restore("config_inc.php.bak", "config_inc.php");
		app.stop();
	}
	
	@AfterMethod(alwaysRun = true)
	public void logTestStop(Method m) {
		logger.info("Stop test: " + m.getName());		
	}
}