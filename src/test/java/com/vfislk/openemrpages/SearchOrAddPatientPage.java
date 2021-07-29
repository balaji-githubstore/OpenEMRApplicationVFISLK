package com.vfislk.openemrpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchOrAddPatientPage {
	private String patFrameName = "pat";
	private By modalFrameLocator=By.xpath("//input[@value='Confirm Create New Patient']");
	private By confirmCreateNewPatientLocator=By.xpath("//input[@value='Confirm Create New Patient']");
	private By closeLocator=By.xpath("//div[@data-dismiss='modal']");
	private WebDriver driver;
	
	@FindBy(xpath = "//div[@data-dismiss='modal']")
	private WebElement closeElement;
	
	@FindBy(xpath = "//div[@data-dismiss='modal']")
	private List<WebElement> closeElements;
	
	

	public SearchOrAddPatientPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void switchToPatFrame() {
		driver.switchTo().frame(patFrameName);
	}

	public String handleAlertAndGetText() {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.alertIsPresent());

		String actualAlertText = driver.switchTo().alert().getText();
//		System.out.println(actualAlertText);

		driver.switchTo().alert().accept();

		return actualAlertText;
	}
	
	
	public void clickOnConfirmCreateNewPatient()
	{
		driver.switchTo().frame(driver.findElement(modalFrameLocator));
		driver.findElement(confirmCreateNewPatientLocator).click();
		driver.switchTo().defaultContent();
	}
	
	public void clickOnHBDClose()
	{
		if(closeElements.size()>0)
		{
			closeElement.click();
		}
	}
}
