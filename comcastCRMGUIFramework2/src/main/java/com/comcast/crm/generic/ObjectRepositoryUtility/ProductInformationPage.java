package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInformationPage {

	WebDriver driver;
	public ProductInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}
	
	@FindBy(id = "dtlview_Product Name")
	private WebElement HeaderMessage;
	
	@FindBy(id ="mouseArea_Product Name")
	private WebElement HeaderConfirmation;
	
	public WebElement getHeaderMessage() {
		return HeaderMessage;
	}
	
	public WebElement getHeaderConfirmatio() {
		return HeaderConfirmation;
	}
}
