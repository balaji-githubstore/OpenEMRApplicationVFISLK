package com.vfislk.openemrtest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PatientTest {
	
private WebDriver driver;
	
	@BeforeMethod
	@Parameters({"browsername","url"})
	public void setUp(@Optional("ch") String browser,@Optional("https://demo.openemr.io/a/openemr/index.php") String url)
	{
		switch (browser.toLowerCase()) {
		case "ff":	
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
			driver=new FirefoxDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", "src/test/resources/driver/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
			driver=new ChromeDriver();
			break;
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.openemr.io/a/openemr/index.php");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

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
