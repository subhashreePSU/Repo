package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewProductPage {

	WebDriver driver;
	public CreatingNewProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name = "productname")
	private WebElement productnameEdit;
	
	@FindBy(xpath= "//input[@title='Save [Alt+S]']")
	private WebElement SaveButton;
	
	public WebElement getProductnameEdit() {
		return productnameEdit;
	}

	public WebElement getSaveButton() {
		return SaveButton;
	}
}
