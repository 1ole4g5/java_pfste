package ru.stqa.jpfste.mantis.tests;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.jpfste.mantis.model.Project;
import ru.stqa.jpfste.mantis.model.SoapIssue;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @BeforeTest
    public void ensurePreconditions() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixedSoap(0000001);
    }

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue()  throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        SoapIssue soapIssue = new SoapIssue().withSummary("Test soapIssue")
                .withDescription("Test soapIssue description").withProject(projects.iterator().next());
        SoapIssue created = app.soap().addIssue(soapIssue);
        assertEquals(soapIssue.getSummary(), created.getSummary());
    }
}
