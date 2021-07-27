package com.vfislk.openemrpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
	private static By usernameLocator = By.id("authUser");
	private static By passwordLocator = By.cssSelector("#clearPass");
	private static By languageLocator=By.name("languageChoice");

	public static void enterUsername(WebDriver driver, String username) {
		driver.findElement(usernameLocator).sendKeys(username);
	}

	public static void enterPassword(WebDriver driver, String password) {
		driver.findElement(passwordLocator).sendKeys(password);
	}
	
	public static void selectLanguageByText(WebDriver driver,String languageText)
	{
		Select selectLanguage=new Select(driver.findElement(languageLocator));
		selectLanguage.selectByVisibleText(languageText);
	}

}
