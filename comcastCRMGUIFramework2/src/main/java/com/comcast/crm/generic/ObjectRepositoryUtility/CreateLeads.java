package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateLeads {

	WebDriver driver;
	public CreateLeads(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(name = "lastname")
	private WebElement LastNameEdit;
	
	@FindBy(name = "company")
	private WebElement CompanyEdit;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement savebutton;
	
	public WebElement getLastNameEdit() {
		return LastNameEdit;
	}

	public WebElement getCompanyEdit() {
		return CompanyEdit;
	}

	public WebElement getSavebutton() {
		return savebutton;
	}
	

}
