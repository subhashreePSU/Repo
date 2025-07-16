package practice_DocumentsTest_POM;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.Databaseutility.DatabaseUtility;
import com.comcast.crm.generic.ExcelUtility.ExcelUtility;
import com.comcast.crm.generic.Javautility.JavaUtility;
import com.comcast.crm.generic.JsonUtility.JsonUtility;
import com.comcast.crm.generic.ObjectRepositoryUtility.CreatingNewDocumentsPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.CreatingNewProductPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.DocumentsInformationPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.DocumentsPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.generic.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.ProductInformationPage;
import com.comcast.crm.generic.ObjectRepositoryUtility.ProductPage;
import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateDoumentsTest {
	
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

		String Title =Elib.getDataFromExcel("product",4 , 2)+Jalib.getRandomNumber();
		
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
		
		HomePage hp = new HomePage(driver);
		hp.getDocumentslink().click();
		
		DocumentsPage dp = new DocumentsPage(driver);
		dp.getDocumentsbutton().click();
		
		CreatingNewDocumentsPage cndp = new CreatingNewDocumentsPage(driver);
		cndp.getTitleEdit().sendKeys(Title);
		cndp.getSaveButton().click();
		
		//verify header msg expected result
		DocumentsInformationPage di = new DocumentsInformationPage(driver);
		String acttitle = di.getHeaderMessage().getText();
		if(acttitle.contains(Title)) {
		System.out.println(Title +"is create == PASS");
		}else {
		System.out.println(Title +"is not create == FAIL");
		}
				
	    HomePage hpg = new HomePage(driver);
		hpg.logout();
	}

}
