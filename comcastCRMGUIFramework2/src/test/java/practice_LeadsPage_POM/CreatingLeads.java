package practice_LeadsPage_POM;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.Databaseutility.DatabaseUtility;
import com.comcast.crm.generic.ExcelUtility.ExcelUtility;
import com.comcast.crm.generic.Javautility.JavaUtility;
import com.comcast.crm.generic.JsonUtility.JsonUtility;
import com.comcast.crm.generic.ObjectRepositoryUtility.CreateLeads;
import com.comcast.crm.generic.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.generic.ObjectRepositoryUtility.LeadsInformationPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreatingLeads {

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
		
		
		//read testscript data from excel file 
		
		String lastName =Elib.getDataFromExcel("product",7 , 2)+Jalib.getRandomNumber();
		String CompanyName =Elib.getDataFromExcel("product",7 , 3)+Jalib.getRandomNumber();
		
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
		HomePage hp = new HomePage(driver);
		hp.getLeadslink().click();
	
		//click on create contact button
		CreateLeads cl = new CreateLeads(driver);
		cl.getLastNameEdit().sendKeys(lastName);
		cl.getCompanyEdit().sendKeys(CompanyName);
		cl.getSavebutton().click();
		
		//verify header msg expected result
		LeadsInformationPage lip = new LeadsInformationPage(driver);
		String actleads = lip.getHeaderMessage().getText();
		
		if(actleads.equals(lastName)) {
			System.out.println(lastName +"information is verified == PASS");
		}else {
			System.out.println(lastName +"information is not verified == FAIL");
		}
        String actleadsinfo = lip.getHeaderConfirmatio().getText();
		
		if(actleadsinfo.equals(CompanyName)) {
			System.out.println(CompanyName +"information is verified == PASS");
		}else {
			System.out.println(CompanyName +"information is not verified == FAIL");
		}
		
	

	}

}
