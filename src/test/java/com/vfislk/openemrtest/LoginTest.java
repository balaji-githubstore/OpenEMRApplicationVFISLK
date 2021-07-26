package com.vfislk.openemrtest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
	
	@Test(description = "Valid Credential Test")
	public void validCredentialTest()
	{
		System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.openemr.io/a/openemr/index.php");
		
		driver.findElement(By.id("authUser")).sendKeys("admin");
		driver.findElement(By.id("clearPass")).sendKeys("pass");	
		Select selectLanguage=new Select(driver.findElement(By.name("languageChoice")));
		selectLanguage.selectByVisibleText("English (Indian)");		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Calendar']")));
		
		String actualValue=driver.getTitle();	
		Assert.assertEquals(actualValue,"OpenEMR");		
		
		driver.quit();
	}	
	
	
	
	
}
