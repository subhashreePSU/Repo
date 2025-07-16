package com.comcast.crm.generic.ObjectRepositoryUtility;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactModule_OrganizationPopupWindowPage {
	
	WebDriver driver;
	public ContactModule_OrganizationPopupWindowPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="search_text")
    private WebElement ConOrgNameTextField;
	
    @FindBy(name="search")
    private WebElement ConOrg_searchbutton;
    
    @FindBy(xpath="//a[text()='\"+orgName+\"']")
    private WebElement ConOrg_resultSelectionlink;
    
    
	public WebElement getConOrgNameTextField() {
		return ConOrgNameTextField;
	}
	public WebElement getConOrg_searchbutton() {
		return ConOrg_searchbutton;
	}
	public WebElement getConOrg_resultSelectionlink() {
		return ConOrg_resultSelectionlink;
	}

}

