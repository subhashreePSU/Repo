package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * author Subhashree_Panigrahi
 * contains HomePage Element & business lib like navigateToCampaignPage() and logout()
 * 
 */
public class HomePage {
      WebDriver driver ;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Organizations")
	private WebElement orglink;
	
	@FindBy(linkText = "Contacts")
	private WebElement Contactlink;
	

	@FindBy(linkText = "More")
	private WebElement morelink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignlink;
	
	@FindBy(xpath= "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signoutLink;
	
	@FindBy(linkText ="Products")
	private WebElement Productlink;
	
	@FindBy(linkText = "Documents")
	private WebElement Documentslink;
	
	@FindBy(linkText = "Leads")
	private WebElement Leadslink;
	
	public WebElement getDocumentslink() {
		return Documentslink;
	}

	public void setDocumentslink(WebElement documentslink) {
		Documentslink = documentslink;
	}

	public WebElement getLeadslink() {
		return Leadslink;
	}

	public void setLeadslink(WebElement leadslink) {
		Leadslink = leadslink;
	}

	public WebElement getProductlink() {
		return Productlink;
	}

	public WebElement getCampaignlink() {
		return campaignlink;
	}

	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getContactlink() {
		return Contactlink;
	}

	public WebElement getMorelink() {
		return morelink;
	}
	/**
	 * navigateToCampaignPage()
	 */
	public void navigateToCampaignPage() {
		Actions action = new Actions(driver);
		action.moveToElement(morelink).perform();
		campaignlink.click();
	}
	/**
	 * logout()
	 * @throws Exception
	 */
	public void logout() throws Exception {
		Actions action = new Actions(driver);
		Thread.sleep(3000);
		action.moveToElement(adminImg).perform();
		signoutLink.click();
	}
	
	
	
	
	
	
	
	
	
	
	
}
