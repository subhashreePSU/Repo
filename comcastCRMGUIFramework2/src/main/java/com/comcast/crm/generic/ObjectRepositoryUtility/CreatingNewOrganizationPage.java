package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name = "accountname")
	private WebElement OrgNameEdit;
	
	@FindBy(xpath= "//input[@title='Save [Alt+S]']")
	private WebElement SaveButton;
	
	@FindBy(name ="industry")
	private WebElement IndustryDropDown;
	
	@FindBy(name = "accounttype")
	private WebElement TypeDropDown;
	
	@FindBy(id ="phone")
	private WebElement phoneNoTextField;
	
	
	public WebElement getOrgNameEdit() {
		return OrgNameEdit;
	}
	
	public WebElement getphoneNoTextField() {
		return phoneNoTextField;
	}

	public WebElement getSaveButton() {
		return SaveButton;
	}
	
	
	public void CreateOrg(String orgName) {
		OrgNameEdit.sendKeys(orgName);
		SaveButton.click();
	}
	
	public void CreateOrg(String orgName, String industry , String type) {
		System.out.println("Orgname:"+orgName+" Industry:"+industry+" Type:"+type);
		OrgNameEdit.sendKeys(orgName);
		Select sel = new Select(IndustryDropDown);
		sel.selectByVisibleText(industry);
	    Select sel1 = new Select(TypeDropDown);
	    sel1.selectByVisibleText(type);
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}
