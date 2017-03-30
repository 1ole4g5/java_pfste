package ru.stqa.jpfste.addressbook.appmanager;

import org.openqa.selenium.By;

public class ModificationHelper extends HelperBase {

	public ModificationHelper(ApplicationManager app) {
		super(app);
	}
		
	public void editGroup() {
		click(By.name("edit"));	
	}
	
	public void editContact() {
		click(By.cssSelector("img[title=Edit]"));	
	}
		
	public void selectionItem() {
		click(By.name("selected[]"));	
	}
	
	public void updateItem() {
		click(By.name("update"));	
	}
	
	public void deletionGroup() {
		click(By.name("delete"));	
	}
	
	public void deletionContact() {
		click(By.cssSelector("input[value=Delete]"));	
	}
	
	public void acceptAlert() {
		wd.switchTo().alert().accept();
	}
	
	public boolean isThereGroup() {
		return isElementPresent(By.name("selected[]"));
	}
}