package practice_orgtest;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.Databaseutility.DatabaseUtility;
import com.comcast.crm.generic.ExcelUtility.ExcelUtility;
import com.comcast.crm.generic.Javautility.JavaUtility;
import com.comcast.crm.generic.JsonUtility.JsonUtility;
import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateOrganizationWithIndustriesTest {

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
		
		String orgName =Elib.getDataFromExcel("org",4 , 2)+Jalib.getRandomNumber();
		String industry =Elib.getDataFromExcel("org",4 , 3)+Jalib.getRandomNumber();
		String type =Elib.getDataFromExcel("org",4 , 4)+Jalib.getRandomNumber();
		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		
		//navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();
		
		//click on create organization button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//enter all details and create organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		//we have to select from dropdown
	    WebElement wbsele1 = driver.findElement(By.name("industry"));
		Select dropdown1 = new Select(wbsele1);
		dropdown1.selectByVisibleText(industry);
		
		//one more dropdown for type 
		WebElement wbsele2 = driver.findElement(By.name("accounttype"));
		Select dropdown2 = new Select(wbsele2);
		dropdown2.selectByVisibleText(type);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(headerInfo.contains(orgName)) {
			System.out.println(orgName +"header is verified == PASS");
		}else {
			System.out.println(orgName +"header is not verified == FAIL");
		}
		
		String actorgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actorgName);
		if(actorgName.trim().equals(orgName)) {
			System.out.println(orgName +"information is created == PASS");
		}else {
			System.out.println(orgName +"information is not created  == FAIL");
		}
		driver.quit();
		
	}

}
