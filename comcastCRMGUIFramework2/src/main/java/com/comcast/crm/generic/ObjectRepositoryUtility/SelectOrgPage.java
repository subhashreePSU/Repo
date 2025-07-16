package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;

public class SelectOrgPage {
	WebDriverUtility wUtil=new WebDriverUtility();
	
	public SelectOrgPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "search_txt")
	private WebElement searchTf;
	
	@FindBy(name = "search_field")
	private WebElement searchField;
	
	@FindBy(name = "search")
	private WebElement searchButton;

	public WebElement getSearchTf() {
		return searchTf;
	}

	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}
	
	public void searchOrg(String org,String choice) {
		searchTf.clear();
		searchTf.sendKeys(org);
		wUtil.Select(searchField, choice);
		searchButton.click();
	}
	
}
