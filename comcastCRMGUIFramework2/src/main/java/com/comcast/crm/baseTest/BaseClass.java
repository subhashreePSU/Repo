package com.comcast.crm.baseTest;

import java.io.IOException;
import java.sql.Connection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.Databaseutility.DatabaseUtility;
import com.comcast.crm.generic.ExcelUtility.ExcelUtility;
import com.comcast.crm.generic.Javautility.JavaUtility;
import com.comcast.crm.generic.JsonUtility.JsonUtility;
import com.comcast.crm.generic.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.generic.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.WebDriverUtility.UtilityClassObject;
import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class BaseClass {

	 public   FileUtility Flib = new FileUtility();
	 public   ExcelUtility Elib = new ExcelUtility();
	 public   JavaUtility Jalib = new JavaUtility();
	 public   JsonUtility Jslib = new JsonUtility();
	 public	  WebDriverUtility wlib = new WebDriverUtility();
	 public   DatabaseUtility Dlib = new DatabaseUtility();
	 public   WebDriver driver = null;
	 public  static WebDriver sdriver=null;
	
	  @BeforeSuite(groups={"smokeTest","regressionTest"})
	 public void configBS() {
		 System.out.println("=======connect to DB, Report Config=========");
		Dlib.getConnection("url", "username", "password");
		
	 }
	  
	@BeforeClass(groups={"smokeTest","regressionTest"})
	public void configBC() throws IOException {
		System.out.println("========Launch the browser============");
		String BROWSER = Flib.getDataFromPropertiesFile("browser");
		String URL = Flib.getDataFromPropertiesFile("url");
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.equals("firefox")){
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")){
			driver = new EdgeDriver();
		}else {
			driver = new ChromeDriver();
		}
		driver.get(URL);
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
	}
	
	@BeforeMethod(groups={"smokeTest","regressionTest"})
	public void configBM() throws IOException {
		System.out.println("===========Login=============");
		String USERNAME = Flib.getDataFromPropertiesFile("username");
		String PASSWORD = Flib.getDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(USERNAME, PASSWORD);	
	}
	@AfterMethod(groups={"smokeTest","regressionTest"})
	public void configAM() throws Exception {
	System.out.println("===============logout==============");
	HomePage hp = new HomePage(driver);
	hp.logout();
	}
	@AfterClass(groups={"smokeTest","regressionTest"})
	public void configAC() {
		System.out.println("==============close the browser===============");
		driver.quit();
	}
	@AfterSuite(groups={"smokeTest","regressionTest"})
	public void configAS() {
		System.out.println("===================close DB, Report backup==============");
		Dlib.closeDbconnection();
		
	}
	

	 
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
