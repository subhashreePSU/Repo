package practice_contactTest;

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
import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateContactWithSupportDateTest {

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		
		//navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		//click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//enter all details for contact information
		Date dateobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String startdate = sim.format(dateobj);
		System.out.println("s:"+startdate);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String enddate = sim.format(cal.getTime());
	
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		WebElement startdate1 = driver.findElement(By.name("support_start_date"));
		startdate1.clear();
		startdate1.sendKeys(startdate);
		WebElement enddate1 = driver.findElement(By.name("support_end_date"));
		enddate1.clear();
		enddate1.sendKeys(enddate);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify header msg expected result
		String actstartdate = driver.findElement(By.id("mouseArea_Support Start Date")).getText();
		
		if(actstartdate.contains(startdate)) {
			System.out.println(startdate +"is create == PASS");
		}else {
			System.out.println(startdate +"is not create == FAIL");
		}
		//////
		String actenddate = driver.findElement(By.id("mouseArea_Support End Date")).getText();
		if(actenddate.contains(enddate)) {
			System.out.println(enddate +"is create == PASS");
		}else {
			System.out.println(enddate +"is not create == FAIL");
		}
		driver.quit();
		}

}
