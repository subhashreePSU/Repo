package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;

public class CreatingNewContactPage {
	WebDriverUtility wutil = new WebDriverUtility();
	WebDriver driver;

	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lastNameEdit;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement SaveButton;

	@FindBy(xpath = "//img[@src='themes/softed/images/select.gif']")
	private WebElement organizationNameButton;

	@FindBy(name = "support_start_date")
	private WebElement StartDate;

	@FindBy(name = "support_end_date")
	private WebElement EndDate;

	public WebElement getStartDate() {
		return StartDate;
	}

	public WebElement getEndDate() {
		return EndDate;
	}

	public WebElement getorganizationNameButton() {
		return organizationNameButton;
	}

	public WebElement getlastNameEdit() {
		return lastNameEdit;
	}

	public WebElement getSaveButton() {
		return SaveButton;

	}
	
	public void createContact(String lastN,String org,String choice) {
		lastNameEdit.clear();
		lastNameEdit.sendKeys(lastN);
		organizationNameButton.click();
		
		wutil.switchToByIndex(driver, 2);
		SelectOrgPage sel = new SelectOrgPage(driver);
		sel.searchOrg(org, choice);
		driver.findElement(By.xpath("//a[text()='"+org+"']")).click();
	}

}
