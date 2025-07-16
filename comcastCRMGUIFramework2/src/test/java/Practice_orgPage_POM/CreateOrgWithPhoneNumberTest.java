package Practice_orgPage_POM;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.Databaseutility.DatabaseUtility;
import com.comcast.crm.generic.ExcelUtility.ExcelUtility;
import com.comcast.crm.generic.Javautility.JavaUtility;
import com.comcast.crm.generic.JsonUtility.JsonUtility;
import com.comcast.crm.generic.ObjectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.generic.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.OrganizationInformationPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.OrganizationsPage;
import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateOrgWithPhoneNumberTest {

	public static void main(String[] args) throws IOException {
	
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
		
		
		String orgName =Elib.getDataFromExcel("org",6 , 2)+Jalib.getRandomNumber();
		String phno =Elib.getDataFromExcel("org",6 , 3)+Jalib.getRandomNumber();


		
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
		
		//navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		
		//click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgbtn().click();
		
		//enter all details and create organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
	    cnop.CreateOrg(orgName);
	    cnop.getphoneNoTextField().sendKeys(phno);
		cnop.getSaveButton().click();
		//verify header msg expected result
		OrganizationInformationPage Oip = new OrganizationInformationPage(driver);
		String actOrgName = Oip.getHeaderMessage().getText();
		
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName +"header verified == PASS");
		}else {
			System.out.println(orgName +"header is verified == FAIL");
		}
		
		String actualorgName = Oip.getHeaderConfirmatio().getText();		
		if(actualorgName.trim().equals(orgName)) {
			System.out.println(orgName +"information is created == PASS");
		}else {
			System.out.println(orgName +"information is not created  == FAIL");
		}
      driver.quit();
	}

}
