package practice_contactTest;

import java.io.IOException;
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
import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws IOException {
		

		//create object for particular utility
	    FileUtility Flib = new FileUtility();
	    ExcelUtility Elib = new ExcelUtility();
		JavaUtility Jalib = new JavaUtility();
//		JsonUtility Jslib = new JsonUtility();
//		WebDriverUtility wlib = new WebDriverUtility();
//		DatabaseUtility Dlib = new DatabaseUtility();
		
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
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(headerInfo.contains(orgName)) {
			System.out.println(orgName +"header verified == PASS");
		}else {
			System.out.println(orgName +"header is verified == FAIL");
		}
		
		//step 5 : navigate to contact module
		
		driver.findElement(By.linkText("Contacts")).click();
				
		//step 6 :click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
		//step 7 :enter all details for contact information
		driver.findElement(By.name("lastname")).sendKeys(ContactLastName);
		// + sign lookup windown beside organization textfield
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//have to handle the child window 
		Set<String> set = driver.getWindowHandles();
		Iterator <String> it = set.iterator(); //iterator return type is iterator object //string type we have to create because 
		//url and title we are going to validate and do iterator
		//iterator will pointing to  set collection and if we want to capture data from set collection then we need to use next(). 
		//next()=string
		//hasNext()= boolean
		while(it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);
			
			@Nullable
			String acturl = driver.getCurrentUrl();
			if(acturl.contains("module=Accounts")) { //if my child window contains module=Accounts in url then switch to this window
				break;//after switching break the loop
			}
		}
		
		//search for organization inside lookup window
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		//search button
		driver.findElement(By.name("search")).click();
		//inspect organization name link which is cretaed //since it is dynamic in nature so we have to use dynamic organization
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		//switch to parent window
		Set<String> set1 = driver.getWindowHandles();
		Iterator <String> it1 = set.iterator();
		while(it1.hasNext()) {
			String windowID = it1.next();
			driver.switchTo().window(windowID);
			
			@Nullable
			String acturl = driver.getCurrentUrl();
			if(acturl.contains("Contacts&action")) { 
				break;
			}
		}
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		//step 8 :verify header msg expected result
		
        String HeaderInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(HeaderInfo.contains(ContactLastName)) {
			System.out.println(ContactLastName +"header verified == PASS");
		}else {
			System.out.println(ContactLastName +"header is verified == FAIL");
		}
		
        String actorgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actorgName);
		if(actorgName.trim().equals(orgName)) {
			System.out.println(orgName +"information is created == PASS");
		}else {
			System.out.println(orgName +"information is not created == FAIL");
		}
		
		driver.quit();
		
}
}