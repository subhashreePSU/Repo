package practice_testng;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {

	public ExtentReports report;

	@BeforeSuite
	public void configBS() {
		// spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReporter/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add env information & create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("os", "windows-10");
		report.setSystemInfo("Browser", "CHROME-100");
	}

	@AfterSuite
	public void configAS() {
		report.flush();
	}

	@Test
	public void CreateContactTest() {
	    WebDriver driver = new ChromeDriver();
	    driver.get("http://49.249.28.218:8888/");
	    TakesScreenshot ts = (TakesScreenshot)driver;
	    String filepath = ts.getScreenshotAs(OutputType.BASE64);
	    
	    ExtentTest test = report.createTest("CreateContactTest");
	    
	    test.log(Status.INFO,"login to app");
	    test.log(Status.INFO,"Navigate to contact page");
	    test.log(Status.INFO,"create contact");
	    if("HDFDD".equals("HDFC")) {
	    	test.log(Status.PASS,"Contact is created");
	    }else {
	    	test.addScreenCaptureFromBase64String(filepath, "ErrorFile");

	    }
	    driver.close();
	}
}
