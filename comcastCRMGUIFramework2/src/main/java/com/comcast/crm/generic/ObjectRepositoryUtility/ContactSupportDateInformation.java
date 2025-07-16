package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactSupportDateInformation {

	
	WebDriver driver;
	public ContactSupportDateInformation(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}
	
	@FindBy(id = "mouseArea_Support End Date")
	private WebElement ActualEndDate;
	
	@FindBy(id ="mouseArea_Support Start Date")
	private WebElement ActualStartDate;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement startdate;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement enddate;
	
	public WebElement getStartdate() {
		return startdate;
	}

	public WebElement getEnddate() {
		return enddate;
	}

	public WebElement getActualEndDate() {
		return ActualEndDate;
	}
	
	public WebElement getActualStartDate() {
		return ActualStartDate;
	}
}
