package ru.stqa.jpfste.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class ContactCreationTests {
	FirefoxDriver wd;

	@BeforeMethod
	public void setUp() throws Exception {
		FirefoxBinary binary = new FirefoxBinary(new File("C:/Program Files/Mozilla Firefox/firefox.exe"));
		wd = new FirefoxDriver(binary, new FirefoxProfile());
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/");
		login("admin", "secret");
	}

	private void login(String username, String password) {
		wd.findElement(By.name("user")).click();
		wd.findElement(By.name("user")).clear();
		wd.findElement(By.name("user")).sendKeys(username);
		wd.findElement(By.name("pass")).click();
		wd.findElement(By.name("pass")).clear();
		wd.findElement(By.name("pass")).sendKeys(password);
		wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
	}

	@Test
	public void testContactCreation() {
		goToAddNewContactPage();
		fillAddNewContactForm(new ContactData("First name", "Last name", "Nick name", "Address", "E-mail"));
		submitAddNewContactForm();
		returnToHomePage();
	}

	private void fillAddNewContactForm(ContactData paramObject) {
		wd.findElement(By.name("firstname")).click();
		wd.findElement(By.name("firstname")).clear();
		wd.findElement(By.name("firstname")).sendKeys(paramObject.getFirstName());
		wd.findElement(By.name("lastname")).click();
		wd.findElement(By.name("lastname")).clear();
		wd.findElement(By.name("lastname")).sendKeys(paramObject.getLastName());
		wd.findElement(By.name("nickname")).click();
		wd.findElement(By.name("nickname")).clear();
		wd.findElement(By.name("nickname")).sendKeys(paramObject.getNickName());
		wd.findElement(By.name("address")).click();
		wd.findElement(By.name("address")).clear();
		wd.findElement(By.name("address")).sendKeys(paramObject.getAddress());
		wd.findElement(By.name("email")).click();
		wd.findElement(By.name("email")).clear();
		wd.findElement(By.name("email")).sendKeys(paramObject.getEMail());
	}

	private void returnToHomePage() {
		wd.findElement(By.linkText("home")).click();
	}

	private void submitAddNewContactForm() {
		wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
	}

	private void goToAddNewContactPage() {
		wd.findElement(By.linkText("add new")).click();
	}

	@AfterMethod
	public void tearDown() {
		wd.quit();
	}

	public static boolean isAlertPresent(FirefoxDriver wd) {
		try {
			wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
}