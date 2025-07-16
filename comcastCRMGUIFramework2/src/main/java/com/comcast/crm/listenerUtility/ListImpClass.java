package com.comcast.crm.listenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.WebDriverUtility.UtilityClassObject;

public class ListImpClass implements ITestListener, ISuiteListener {

	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;

	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");

		String time = new Date().toString().replace(" ","_").replace(":","_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReporter/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("os", "windows-10");
		report.setSystemInfo("Browser", "CHROME-100");
	}

	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println("----" + result.getMethod().getMethodName() + "----start----");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO,result.getMethod().getMethodName()+"-----start-----");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("----" + result.getMethod().getMethodName() + "----end----");
		test.log(Status.PASS,result.getMethod().getMethodName()+"------completed------");
		
	}

	public void onTestFailure(ITestResult result) {

		String testName = result.getMethod().getMethodName();
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		try {
			File src = new File("./src/screenshot/" + testName + "image");
			FileHandler.copy(temp, src);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(Status.FAIL,result.getMethod().getMethodName()+"------FAILED-------");
		

	}

}
