package mock;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Automation_Exercise {

	public static void main(String[] args) throws InterruptedException {
	
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//navigate to url
		driver.get("http://automationexercise.com");
		
		//verifiy the home page is visible successfully
		String expectedTitle = "Automation Exercise";
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		if(expectedTitle.equals(actualTitle)) {
			System.out.println("Home Page is Visible Successfully");
		}else {
			System.out.println("Home Page is not Visible Successfully");
		}
	
		//scroll down page to the buttom
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(2000);
		//Verify 'SUBSCRIPTION' is visible
		boolean visibleText = driver.findElement(By.xpath("//h2[text()='Subscription']")).isDisplayed();
		if(visibleText == true) {
			System.out.println("Subscription is visible");
		}else {
			System.out.println("Subscription is not visible");
		}
		
		//Click on arrow at bottom right side to move upward
		driver.findElement(By.id("scrollUp")).click();
		
		//Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
		String actualmsg = "Full-Fledged practice website for Automation Engineers";
		WebElement headermsg = driver.findElement(By.xpath("//h2[text()='Full-Fledged practice website for Automation Engineers']"));

		if(headermsg.equals("actualmsg")) {
			System.out.println("Header Message is visible");
	}else {
		System.out.println("Header Message is not Visible");
	}
	

	}

}
