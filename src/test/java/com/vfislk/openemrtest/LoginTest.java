package com.vfislk.openemrtest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vfislk.openemrbase.WebDriverWrapper;
import com.vfislk.openemrpages.DashboardPage;
import com.vfislk.openemrpages.LoginPage;
	
public class LoginTest extends WebDriverWrapper {
	
	@Test
	public void invalidCredentialTest()
	{		
		driver.findElement(By.id("authUser")).sendKeys("admin123");
		driver.findElement(By.id("clearPass")).sendKeys("pass");	
		Select selectLanguage=new Select(driver.findElement(By.name("languageChoice")));
		selectLanguage.selectByVisibleText("English (Indian)");		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
			
		String actualValue= driver.findElement(By.xpath("//div[contains(text(),'Invalid')]")).getText().trim();
		
		Assert.assertEquals(actualValue, "Invalid username or password");
	}
	
	@Test(description = "Valid Credential Test")
	public void validCredentialTest()
	{		
		LoginPage login=new LoginPage(driver);
		
		login.enterUsername("admin");
		login.enterPassword("pass");
		login.selectLanguageByText("English (Indian)");	
		login.clickOnLogin();
		
		DashboardPage dashboard=new DashboardPage(driver);
		dashboard.waitForPresenceOfCalendarText();
		
		String actualValue=driver.getTitle();	
		
		
		Assert.assertEquals(actualValue,"OpenEMR");		
			
	}	
	//will start by 12 PM IST
	
	
	
	
}
