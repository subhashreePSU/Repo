package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	WebDriver driver;
	public ProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath= "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement productbutton;
	
	
	public WebElement getproductbutton() {
		return productbutton;
	}
	
}
