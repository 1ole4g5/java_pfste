package ru.stqa.jpfste.mantis.tests;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.jpfste.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

    @BeforeTest
    public void ensurePreconditions() throws IOException, ServiceException {
        skipIfNotFixed(10);
    }

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = app.rest().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId = app.rest().createIssue(newIssue);
        Set<Issue> newIssues = app.rest().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}