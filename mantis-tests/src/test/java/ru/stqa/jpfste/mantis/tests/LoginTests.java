package ru.stqa.jpfste.mantis.tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import ru.stqa.jpfste.mantis.appmanager.HttpSession;

public class LoginTests extends TestBase{
	@Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}