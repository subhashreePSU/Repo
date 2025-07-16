package com.comcast.crm.generic.WebDriverUtility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;


public class WebDriverUtility {

	public void waitForPageToLoad(WebDriver driver) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
    public void waitForElementPresent(WebDriver driver,WebElement element) {
		
		WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
    
    public void SwitchToTabOnURL(WebDriver driver, String partialURL) {
		
        Set<String> set = driver.getWindowHandles();
        Iterator<String> it = set.iterator();
        while(it.hasNext()) {
        	String WindowID = it.next();
        	driver.switchTo().window(WindowID);
        	@Nullable
			String acturl = driver.getCurrentUrl();
        	if(acturl.contains(partialURL)) {
        		break;
        	}
        }
		
	}

    public void SwitchToTabOnTitle(WebDriver driver, String partialTitle) {
		
        Set<String> set = driver.getWindowHandles();
        Iterator<String> it = set.iterator();
        while(it.hasNext()) {
        	String WindowID = it.next();
        	driver.switchTo().window(WindowID);
        	@Nullable
			String acturl = driver.getCurrentUrl();
        	if(acturl.contains(partialTitle)) {
        		break;
        	}
        }
		
	}
    
    public void switchToByIndex(WebDriver driver,int index) {
    	Set<String> wins = driver.getWindowHandles();
    	int ct=0;
    	for(String s:wins) {
    		driver.switchTo().window(s);
    		ct++;
    		if(ct==index)
    			break;
    	}
    }
    
    public void switchtoFrame(WebDriver driver,int index) {
    	driver.switchTo().frame(index);
    }
    public void switchtoFrame(WebDriver driver,String nameID) {
    	driver.switchTo().frame(nameID);
    }
    public void switchtoFrame(WebDriver driver,WebElement element) {
    	driver.switchTo().frame(element);
    }
    
    
    
    public void switchtoAlertAndAccept(WebDriver driver) {
    	driver.switchTo().alert().accept();
    }
    public void switchtoAlertAndCancel(WebDriver driver) {
    	driver.switchTo().alert().dismiss();
    }
    
    
    public void Select(WebElement element,String text) {
    	Select sel = new Select(element);
    	sel.selectByVisibleText(text);
    }
    public void Select(WebElement element,int index) {
    	Select sel = new Select(element);
    	sel.selectByIndex(index);
    }
    
    public void mouseMoveOnElement(WebDriver driver, WebElement element) {
    	
    	Actions action = new Actions(driver);
    	action.moveToElement(element).perform();
    }
    public void doubleClick(WebDriver driver, WebElement element) {
    	
    	Actions action = new Actions(driver);
    	action.doubleClick(element).perform();
    }
    public void rightClick(WebDriver driver, WebElement element) {
    	
    	Actions action = new Actions(driver);
    	action.contextClick(element).perform();
    }
    public void mousedragAndDrop(WebDriver driver, WebElement source, WebElement target) {
    	
    	Actions action = new Actions(driver);
		action.dragAndDrop(source, target).perform();
    }
     public void clickAndHoldAction(WebDriver driver, WebElement element) {
    	
    	Actions action = new Actions(driver);
    	action.clickAndHold(element).perform();
    }
     public void release(WebDriver driver, WebElement element) {
     	
     	Actions action = new Actions(driver);
     	action.release(element).perform();
     }

	public void verificationOfCreatedEntityheader(WebElement aheaderText, String contactLastName) {
		// TODO Auto-generated method stub
		
	}
   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
