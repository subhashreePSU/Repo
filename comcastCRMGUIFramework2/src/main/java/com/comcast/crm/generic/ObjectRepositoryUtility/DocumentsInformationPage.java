package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentsInformationPage {

	WebDriver driver;
	public DocumentsInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}
	
	@FindBy(id = "dtlview_Title")
	private WebElement HeaderMessage;
	
	@FindBy(id ="mouseArea_Title")
	private WebElement HeaderConfirmation;
	
	public WebElement getHeaderMessage() {
		return HeaderMessage;
	}
	
	public WebElement getHeaderConfirmatio() {
		return HeaderConfirmation;
	}
}
