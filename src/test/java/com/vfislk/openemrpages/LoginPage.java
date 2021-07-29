package com.vfislk.openemrpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
		
	@FindBy(id = "authUser")
	private WebElement usernameElement;
	
	@FindBy(css = "#clearPass")
	private WebElement passwordElement;
	
	@FindBy(name="languageChoice")
	private WebElement languageElement;
	
	@FindBy(how = How.XPATH,using = "//button[@type='submit']")
	private WebElement loginElement;
	
	@FindBy(xpath = "//div[contains(text(),'Invalid')]")
	private WebElement errorElement;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT,using = "Acknowledgments")
	private WebElement ackLicCertWebElement;
	
	@FindBy(xpath = "//p[contains(text(),'most')]")
	private WebElement appDescriptionElement;
	
	public LoginPage(WebDriver driver) {	
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String username) {
		usernameElement.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordElement.sendKeys(password);
	}

	public void selectLanguageByText(String languageText) {
		Select selectLanguage = new Select(languageElement);
		selectLanguage.selectByVisibleText(languageText);
	}

	public void clickOnLogin() {
		loginElement.click();
	}

	public String getInvalidErrorMessage() {
		return errorElement.getText().trim();
	}

	public void clickOnAcknowledgmentsLicensingAndCertification() {
		ackLicCertWebElement.click();
	}

	public String getApplicationDescription() {
		return appDescriptionElement.getText().trim();
	}
}
