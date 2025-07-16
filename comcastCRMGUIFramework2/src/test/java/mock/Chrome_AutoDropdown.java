package mock;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome_AutoDropdown {

	public static void main(String[] args) throws InterruptedException {
		//Go to Chrome
		//search java but don't click enter then few suggestions will come and all that suggestions should print/appear in console
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.google.com/");
		
		driver.findElement(By.id("APjFqb")).sendKeys("java");
		
		Thread.sleep(2000);
		List<WebElement> suggestions = driver.findElements(By.xpath("//ul[@role='listbox']//li//span"));
		
		for(WebElement suggestion : suggestions ) {
			System.out.println(suggestion.getText());
		}
	}

}
