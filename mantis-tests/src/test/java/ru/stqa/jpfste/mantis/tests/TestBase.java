package ru.stqa.jpfste.mantis.tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import ru.stqa.jpfste.mantis.appmanager.ApplicationManager;


public class TestBase {
	
	protected static final ApplicationManager app 
	= new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

	@BeforeSuite
	public void setUp() throws Exception {
		app.init();
		app.ftp().upload(new File("mantis-tests/src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws IOException {
		app.ftp().restore("config_inc.php.bak", "config_inc.php");
		app.stop();
	}
}