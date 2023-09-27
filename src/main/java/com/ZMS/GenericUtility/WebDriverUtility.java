package com.ZMS.GenericUtility;

import java.io.File;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class WebDriverUtility {
	
	//1
	public void maximizeTheBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	//2
	public void minimizeTheBrowser(WebDriver driver)
	{
		driver.manage().window().minimize();
	}

	//3
	public void implicitWait(WebDriver driver,int duration)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	//4
	public void explicitWait(WebDriver driver, int duration)
	{
		
	}
	
	//5
	public void waitUntilUrlLoads(WebDriver driver, int duration, String expectedurl)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.urlContains(expectedurl));
	}
	
	//6
	public void waitUntilTitleLoads(WebDriver driver, int duration, String expectedtitle)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.urlContains(expectedtitle));
	}
	
	//7
	public void waitUntilElementToBeVisible(WebDriver driver, int duration,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	//8
	public void noSuchElementException(WebDriver driver, int duration,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.ignoring(NoSuchElementException.class);
	}
	
	//9
	public void customWait(WebElement element) throws Throwable
	{
		int count=0;
		while (count<20)
		{
			try
			{
				element.click();
				break;
			}
			catch(Exception e)
			{
				Thread.sleep(1000);
				count++;
			}
		}
	}
	
	//10
	public void selectDropdownByIndex(WebElement element, int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
	//11
	public void selectDropdownByInd(WebElement element, String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	//12
	public void selectDropdownByText(WebElement element, String text)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
	//13
	public void mouseOverOnElement(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	//14
	public void rightClick(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	
	//15
	public void clickOnEnterKey(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
	}
	
	//16
	public void switchFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	//17
	public void switchToMainFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	//18
	public void switchWindowBasedOnTitle(WebDriver driver, String expectedTitle)
	{
		Set<String> set = driver.getWindowHandles();
		for(String str:set)
		{
			driver.switchTo().window(str);
			String title = driver.getTitle();
			
			if(title.contains(expectedTitle))
			{
				break;
			}
		}
	}
	
	//19
	public void acceptAlertPopup(WebDriver driver, String expectedMsg)
	{
		Alert alt = driver.switchTo().alert();
		if(alt.getText().equalsIgnoreCase(expectedMsg)) 
		{
			System.out.println("alert msg is verified");
		}
		else
		{
		System.out.println("alert msg is not verified");	
		}
		alt.accept();
	}
	
	public void alertPopUpAccept(WebDriver driver)
	{
		Alert alt = driver.switchTo().alert();
		alt.accept();
	}
	
	//20
	public void dismissAlertPopup(WebDriver driver, String expectedMsg)
	{
		Alert alt = driver.switchTo().alert();
		if(alt.getText().equalsIgnoreCase(expectedMsg)) 
		{
			System.out.println("alert msg is verified");
		}
		else
		{
		System.out.println("alert msg is not verified");	
		}
		alt.dismiss();
	}
	
	//21
	public String takeScreenShot(WebDriver driver, String screenShotName)
	{
		TakesScreenshot tss = (TakesScreenshot)driver;
		File src = tss.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenShots/"+screenShotName+".png");
		try
		{
			Files.copy(src, dst);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return screenShotName;
	}
	
	//22
	public void scrollToParticularElement(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scollBy", element);
	}
}
