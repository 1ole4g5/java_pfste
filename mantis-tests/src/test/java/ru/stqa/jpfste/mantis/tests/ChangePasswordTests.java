package ru.stqa.jpfste.mantis.tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.jpfste.mantis.model.MailMessage;
import ru.stqa.jpfste.mantis.model.UserData;
import ru.stqa.jpfste.mantis.model.Users;

public class ChangePasswordTests extends TestBase {
	
	@BeforeMethod
	public void startMailServer() {
		app.mail().start();
	}
	
	@Test
	public void testChangePassword() throws MessagingException, IOException, InterruptedException {
		long now = System.currentTimeMillis();
		String userName = String.format("user%s", now);
		String newPassword = "newPassword";
		app.loginHelper().asUser(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
		app.goTo().manageUserPage();
		Users before = app.db().users();
		UserData modifiedUser = before.iterator().next();
		UserData user = new UserData().withId(modifiedUser.getId());
		app.user().changePass(user);
		List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
		String confirmationLink = findConfirmationLink(mailMessages);
		System.out.println(confirmationLink);
		//app.user().logout();
		//app.newSession().login(userName, newPassword);
		//Thread.sleep(1000);
//		app.newSession().login(userName, newPassword);
//		Thread.sleep(10000);
//		assertTrue(app.newSession().login(userName, newPassword));
		
		app.registration().finish(confirmationLink, newPassword);
		assertTrue(app.newSession().login(userName, newPassword));
		Thread.sleep(1000);
		app.newSession().login(userName, newPassword);
		Thread.sleep(10000);
		assertTrue(app.newSession().isLoggedInAs(userName));
	}
	
	private String findConfirmationLink(List<MailMessage> mailMessages) {
		MailMessage mailMessage = mailMessages.stream().findFirst().get();
		VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
		return regex.getText(mailMessage.text);
	}

	@AfterMethod(alwaysRun = true)
	public void stopMailServer() {
		app.mail().stop();
	}
}
