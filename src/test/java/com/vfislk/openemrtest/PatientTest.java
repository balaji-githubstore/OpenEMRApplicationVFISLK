package com.vfislk.openemrtest;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.vfislk.openemrbase.WebDriverWrapper;
	
public class PatientTest extends WebDriverWrapper {

	@Test
	public void addPatientTest()
	{
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[text()='Patient/Client']"))).perform();
		
		driver.findElement(By.xpath("//div[text()='Patients']")).click();
		
//		driver.switchTo().frame("fin");
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'dynamic_finder')]")));
		
		driver.findElement(By.xpath("//button[normalize-space()='Add New Patient']")).click();
		
		driver.switchTo().defaultContent();
		
		
		driver.switchTo().frame("pat");
		
		driver.findElement(By.id("form_fname")).sendKeys("Sat");
		driver.findElement(By.id("form_lname")).sendKeys("Dinakaran");	
		driver.findElement(By.id("form_DOB")).sendKeys("2021-07-20");		
		Select selectGender=new Select(driver.findElement(By.id("form_sex")));
		selectGender.selectByVisibleText("Male");
		driver.findElement(By.id("create")).click();
		
		driver.switchTo().defaultContent();
		
	
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='modalframe']")));
		
		driver.findElement(By.xpath("//input[@value='Confirm Create New Patient']")).click();
		
		driver.switchTo().defaultContent();
		
		WebDriverWait wait =new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.alertIsPresent());
		
		String actualAlertText=driver.switchTo().alert().getText();
		System.out.println(actualAlertText);
		
		driver.switchTo().alert().accept();
		
		//check for presence of element
		if(driver.findElements(By.xpath("//div[@data-dismiss='modal']")).size()>0)
		{
			driver.findElement(By.xpath("//div[@data-dismiss='modal']")).click();
		}
		
		driver.switchTo().frame("pat");
		
		String actualValue = driver.findElement(By.xpath("//h2[contains(text(),'Medical')]")).getText();
		System.out.println(actualValue);
		
		driver.switchTo().defaultContent();
		
		//assertion on alert
		//assertion on patient name
		
	}
	
}
