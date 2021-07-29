package com.vfislk.openemrpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	private By calendarLocator = By.xpath("//span[text()='Calendar']");
//	private By patientClientLocator = By.xpath("//div[text()='Patient/Client']");
//	private By patientsLocator = By.xpath("//div[text()='Patients']");
//	private By aboutLocator=By.xpath("//div[text()='About']");
	
	@FindBy(xpath = "//div[text()='Patient/Client']")
	private  WebElement patientClientElement;

	@FindBy(xpath = "//div[text()='Patients']")
	private WebElement patientsElement;
	
	@FindBy(xpath = "//div[text()='About']")
	private WebElement aboutElement;
	
	private WebDriver driver;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForPresenceOfCalendarText() {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(calendarLocator)).click();
	}

	public String getCurrentTitle() {
		return driver.getTitle().trim();
	}

	public void mousehoverOnPatientClient() {
		Actions action = new Actions(driver);
		action.moveToElement(patientClientElement).perform();
	}

	public void clickOnPatients() {
		patientsElement.click();
	}
	
	public void clickOnAbout()
	{
		aboutElement.click();
	}

}
