package mock;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cricbuzz {

	public static void main(String[] args) {
	
//		My scenario was 
//		Go to cricbuzz website 
//		Go to rankings
//		Go to batting 
//		Click on odi
//		Pass the cricketer name you should get his all details 
//		Same way go to batting there pass one cricketers name you should get all details in console
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.cricbuzz.com/");
		
		driver.findElement(By.linkText("Rankings")).click();
		
		driver.findElement(By.id("batsmen-tab")).click();
	
		List<WebElement> Batting = driver.findElements(By.xpath("(//a[contains(text(),'Yashasvi Jaiswal')])[1]/../../..//div[text()='858']"));
		for(WebElement ele:Batting) {
			System.out.println(ele.getText());
		}
		driver.findElement(By.id("bowlers-tab")).click();
		
		

	}

}
