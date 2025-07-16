package mock;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Myntra {

	public static void main(String[] args) {
	
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.myntra.com/");
		
		WebElement Men = driver.findElement(By.xpath("(//a[@href='/shop/men'])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(Men).perform();
		
		List<WebElement> Topwear = driver.findElements(By.xpath("//a[@href='men-topwear']/li/a/div"));
		System.out.println(Topwear);
		for (WebElement ele : Topwear ) {
			System.out.println(ele.getText());
		}
		
		List<WebElement> 
		IndianAndFestiveWear=driver.findElements(By.xpath("//a[@href='Indian & Festive Wear']/li/a"));
		System.out.println(IndianAndFestiveWear);

	}

}
