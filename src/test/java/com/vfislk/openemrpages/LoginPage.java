package com.vfislk.openemrpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
	private By usernameLocator = By.id("authUser");
	private By passwordLocator = By.cssSelector("#clearPass");
	private By languageLocator=By.name("languageChoice");
	private By loginLocator=By.xpath("//button[@type='submit']");
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}

	public void enterUsername(String username) {
		this.driver.findElement(this.usernameLocator).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordLocator).sendKeys(password);
	}
	
	public void selectLanguageByText(String languageText)
	{
		Select selectLanguage=new Select(driver.findElement(languageLocator));
		selectLanguage.selectByVisibleText(languageText);
	}
	
	public void clickOnLogin()
	{
		driver.findElement(loginLocator).click();
	}
}



