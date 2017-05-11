package ru.stqa.jpfste.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {
	
	public LoginHelper(ApplicationManager app) {
		super(app);
	}

	public void asUser(String username, String password) {
		wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
		type(By.name("username"), username);
		type(By.name("password"), password);
		click(By.cssSelector("input[value='Login']"));
	}

	public void changePassword(String confirmationLink, String newPassword) {
		wd.get(confirmationLink);
		type(By.name("password"), newPassword);
		type(By.name("password_confirm"), newPassword);
		click(By.cssSelector("button[type=submit]"));		
	}
	
	public void logout() {
		click(By.cssSelector("button[type=submit]"));		
	}
}
