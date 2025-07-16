package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
	WebDriver driver;
	public ContactPage(WebDriver driver) {
		System.out.println("hi");
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath =  "//a[text()='Contacts']")
	private WebElement Contactbutton;
	
	@FindBy(xpath ="//img[@title='Create Contact...']")
	private WebElement contactplusSign;
	
	@FindBy(xpath ="//span[@class='dvHeaderText']")
	private WebElement HeaderMsg;
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement actLastName;
	
	
	public WebElement getHeaderMsg() {
		return HeaderMsg;
	}


	public WebElement getActLastName() {
		return actLastName;
	}


	public WebElement getContactplusSign() {
		return contactplusSign;
	}


	public WebElement getContactbutton() {
		return Contactbutton;
	}
	
	
}
