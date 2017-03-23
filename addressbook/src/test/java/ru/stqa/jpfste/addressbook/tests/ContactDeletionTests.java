package ru.stqa.jpfste.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

	@Test
	public void testContactDeletion() {
		app.modificationHelper.selectionItem();
		app.modificationHelper.deletionContact();
		app.modificationHelper.acceptAlert();
	}
}