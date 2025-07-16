package Practice;

import org.junit.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class Baseclass_practice {

	@BeforeSuite
	public void configBS() {
		System.out.println("execute Before Suite");
	}

	@BeforeClass
	public void configBC() {
		System.out.println("execute Before Class");
	}

	@BeforeMethod()
	public void configBM() {
		System.out.println("execute Before Method");
	}

	@BeforeTest()
	public void configBT() {
		System.out.println("execute Before Test");
	}

	@AfterTest()
	public void configAT() {
		System.out.println("execute After Test");
	}

	@AfterMethod()
	public void configAM() {
		System.out.println("execute After Method");
	}

	@AfterClass()
	public void configAC() {
		System.out.println("execute After Class");
	}

	@AfterSuite()
	public void configAS() {
		System.out.println("execute After Suite");
	}
}

