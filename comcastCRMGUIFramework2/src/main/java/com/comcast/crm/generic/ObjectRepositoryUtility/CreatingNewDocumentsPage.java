package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewDocumentsPage {

	WebDriver driver;
	public CreatingNewDocumentsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name = "notes_title")
	private WebElement TitleEdit;
	
	@FindBy(xpath= "//input[@title='Save [Alt+S]']")
	private WebElement SaveButton;
	
	public WebElement getTitleEdit() {
		return TitleEdit;
	}

	public WebElement getSaveButton() {
		return SaveButton;
	}
}
