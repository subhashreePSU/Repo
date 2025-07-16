package Practice_contactPage_POM;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import java.util.Date;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.Databaseutility.DatabaseUtility;
import com.comcast.crm.generic.ExcelUtility.ExcelUtility;
import com.comcast.crm.generic.Javautility.JavaUtility;
import com.comcast.crm.generic.JsonUtility.JsonUtility;
import com.comcast.crm.generic.ObjectRepositoryUtility.ContactPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.ContactSupportDateInformation;
import com.comcast.crm.generic.ObjectRepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.generic.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateContactWithSupportDateTest {

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
		
		
		String lastName =Elib.getDataFromExcel("contact",4 , 2)+Jalib.getRandomNumber();
		
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
		hp.getContactlink().click();

		//click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getContactbutton().click();
		
		//enter all details for contact information
		String startdate = Jalib.getSystemDateYYYYDDMM();
		String enddate = Jalib.getSystemDateYYYYDDMM(30);
		

		//enter all details for contact information
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getlastNameEdit().sendKeys(lastName);
		cncp.getStartDate().sendKeys(startdate);
		cncp.getEndDate().sendKeys(enddate);
		cncp.getSaveButton().click();
		
		//verify header msg expected result
		ContactSupportDateInformation csdi =new ContactSupportDateInformation(driver);
		WebElement actualstartdate = csdi.getActualStartDate();
		
		if(actualstartdate.equals(startdate)) {
			System.out.println(startdate +"is create == PASS");
		}else {
			System.out.println(startdate +"is not create == FAIL");
		}
		//////
		WebElement actalenddate = csdi.getActualEndDate();
		if(actalenddate.equals(enddate)) {
			System.out.println(enddate +"is create == PASS");
		}else {
			System.out.println(enddate +"is not create == FAIL");
		}
		driver.quit();
		}

}
