package com.comcast.crm.generic.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;
/**
 * author Subhashree_Panigrahi
 * contains LoginPage Element & business lib like login()
 * 
 */
public class LoginPage extends WebDriverUtility{

	WebDriver driver ;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name ="user_name")
	private WebElement usernameEdt;
	

	@FindBy(name ="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;
	
	public WebElement getusernameEdt() {
		return usernameEdt;
	}
	
    public WebElement getpasswordEdt() {
			return passwordEdt;
	}
    
    public WebElement getloginbtn() {
		return loginbtn;
}
    /**
     * Login to application
     * @param username
     * @param password
     */
    public void loginToapp(String username, String password) {
    	waitForPageToLoad(driver);
    	driver.manage().window().maximize();
    	usernameEdt.sendKeys(username);
    	passwordEdt.sendKeys(password);
    	loginbtn.click();
    }


    
	
	

}
