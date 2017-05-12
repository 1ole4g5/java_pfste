package ru.stqa.jpfste.addressbook.appmanager;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

	public SessionHelper(WebDriver wd) throws MalformedURLException {
		super(wd);
	}

	public void login(String username, String password) {
		type(By.name("user"), username);
		type(By.name("pass"), password);
		click(By.xpath("//form[@id='LoginForm']/input[3]"));
	}
}