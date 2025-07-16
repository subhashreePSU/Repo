package practice_contactTest;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.ExcelUtility.ExcelUtility;
import com.comcast.crm.generic.Javautility.JavaUtility;
import com.comcast.crm.generic.fileutility.FileUtility;


public class CreateContactTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//create object for particular utility
		FileUtility Flib = new FileUtility();
		ExcelUtility Elib = new ExcelUtility();
		JavaUtility Jalib = new JavaUtility();
//		JsonUtility Jslib = new JsonUtility();
//		WebDriverUtility wlib = new WebDriverUtility();
//		DatabaseUtility Dlib = new DatabaseUtility();
//		
		String BROWSER = Flib.getDataFromPropertiesFile("browser");
		String URL = Flib.getDataFromPropertiesFile("url");
		String USERNAME = Flib.getDataFromPropertiesFile("username");
		String PASSWORD = Flib.getDataFromPropertiesFile("password");
		
		
		//read testscript data from excel file 
		
		String lastName =Elib.getDataFromExcel("contact",1 , 2)+Jalib.getRandomNumber();
		
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
		
		Thread.sleep(3000);
		//navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		//click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//enter all details for contact information
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify header msg expected result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		
		if(actLastName.equals(lastName)) {
			System.out.println(lastName +"information is verified == PASS");
		}else {
			System.out.println(lastName +"information is not verified == FAIL");
		}
		
	}
}



