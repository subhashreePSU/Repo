package Practice_contactPage_POM;

import java.time.Duration;
import java.util.Iterator;

import java.util.Set;


import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.Databaseutility.DatabaseUtility;
import com.comcast.crm.generic.ExcelUtility.ExcelUtility;
import com.comcast.crm.generic.Javautility.JavaUtility;
import com.comcast.crm.generic.JsonUtility.JsonUtility;
import com.comcast.crm.generic.ObjectRepositoryUtility.ContactInformationPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.ContactModule_OrganizationPopupWindowPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.ContactPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.generic.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.OrganizationsPage;
import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws Exception {
		

		//create object for particular utility
	    FileUtility Flib = new FileUtility();
	    ExcelUtility Elib = new ExcelUtility();
		JavaUtility Jalib = new JavaUtility();
		JsonUtility Jslib = new JsonUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		DatabaseUtility Dlib = new DatabaseUtility();
		
		String BROWSER = Flib.getDataFromPropertiesFile("browser");
		String URL = Flib.getDataFromPropertiesFile("url");
		String USERNAME = Flib.getDataFromPropertiesFile("username");
		String PASSWORD = Flib.getDataFromPropertiesFile("password");
		
		//read testscript data from excel file 
		
		String orgName =Elib.getDataFromExcel("contact",7 , 2)+Jalib.getRandomNumber();
		String ContactLastName = Elib.getDataFromExcel("contact",7 , 3)+Jalib.getRandomNumber();
		
		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.equals("firefox")){
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")){
			driver = new EdgeDriver();
		}else {
			driver = new ChromeDriver();
		}
		//login to application
		wlib.waitForPageToLoad(driver);
		driver.get(URL);
		LoginPage lp =  new LoginPage(driver);
		lp.loginToapp(USERNAME, PASSWORD);
		lp.getloginbtn();
				

		//navigate to contact module
		OrganizationsPage op= new OrganizationsPage(driver);
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		//click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getContactbutton().click();
		

		//enter all details for contact information
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getlastNameEdit().sendKeys(ContactLastName);
		cncp.getorganizationNameButton().click();
		cncp.getSaveButton().click();
		
		 //verify Header msg Expected Result
		wlib.waitForPageToLoad(driver);
        ContactInformationPage cip= new ContactInformationPage(driver);
        wlib.verificationOfCreatedEntityheader(cip.getAheaderText(), orgName);
        //step5: navigate to contact module
        hp.getContactlink().click();
        
        //step6: click on "create organization" Button
        ContactPage cp1= new ContactPage(driver);
        cp1.getContactbutton().click();
        
        //step7: enter all the details & create new organization
        cip.getLastnameTextField().sendKeys(ContactLastName);
        cip.getOrgnameCheckBtn().click();
       
        wlib.SwitchToTabOnURL(driver, "module=Accounts");
        
        
        ContactModule_OrganizationPopupWindowPage cmop= new ContactModule_OrganizationPopupWindowPage(driver);
        cmop.getConOrgNameTextField().sendKeys(orgName);
        cmop.getConOrg_searchbutton().click();
        driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
        
        //switch to parent window
        wlib.SwitchToTabOnTitle(driver, "module=Contacts");
        cip.getSaveButton().click();

      //verify Header msg Expected Result
//        wlib.waitForPageToLoad(driver);
//        wlib.verificationOfCreatedEntityheader(cip.getAheaderText(), ContactLastName);
//       
//        //verify Header orgName info Expected Result
//        wlib.verificationOfCreatedEntityheader(cip.getAConOrgNameTextfield(), orgName);
        hp.logout();
        driver.quit();
		}


		
				
}
