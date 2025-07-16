package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {

	WebDriver driver;
	public ContactInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}
	
	
	@FindBy(name="lastname")
	private WebElement lastnameTextField;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveButton;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement AlastnameTextfield ;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement AheaderText;
	
	@FindBy(xpath="//input[@name='account_name']/..//img[@src='themes/softed/images/select.gif']")
    private WebElement OrgnameCheckBtn;
	
	@FindBy(id="mouseArea_Organization Name")
    private WebElement AConOrgNameTextfield;
	
	@FindBy(name="support_start_date")
	private WebElement StartDateTextField;
	
	@FindBy(name="support_end_date")
	private WebElement EndDateTextField;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement AStartDateTextField;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement AEndDateTextField;
	
	public WebElement getAStartDateTextField() {
		return AStartDateTextField;
	}

	public WebElement getAEndDateTextField() {
		return AEndDateTextField;
	}

	public WebElement getStartDateTextField() {
		return StartDateTextField;
	}

	public WebElement getEndDateTextField() {
		return EndDateTextField;
	}

	public WebElement getAConOrgNameTextfield() {
		return AConOrgNameTextfield;
	}

	public WebElement getOrgnameCheckBtn() {
		return OrgnameCheckBtn;
	}

	public WebElement getLastnameTextField() {
		return lastnameTextField;
	}

	public WebElement getSaveButton() {
		return SaveButton;
	}

	public WebElement getAlastnameTextfield() {
		return AlastnameTextfield;
	}

	public WebElement getAheaderText() {
		return AheaderText;
	}

	public WebElement getHeaderMessage() {
		// TODO Auto-generated method stub
		return null;
	}


}
