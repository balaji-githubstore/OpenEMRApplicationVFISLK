package com.vfislk.openemrtest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vfislk.openemrbase.WebDriverWrapper;
import com.vfislk.openemrpages.DashboardPage;
import com.vfislk.openemrpages.LoginPage;
	
public class LoginTest extends WebDriverWrapper {

	@Test
	public void invalidCredentialTest() {
		LoginPage login = new LoginPage(driver);
		login.enterUsername("admin123");
		login.enterPassword("pass");
		login.selectLanguageByText("Dutch");
		login.clickOnLogin();

		Assert.assertEquals(login.getInvalidErrorMessage(), "Invalid username or password");
	}

	@Test(description = "Valid Credential Test")
	public void validCredentialTest() {
		
		LoginPage login = new LoginPage(driver);
		login.enterUsername("admin");
		login.enterPassword("pass");
		login.selectLanguageByText("English (Indian)");
		login.clickOnLogin();

		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.waitForPresenceOfCalendarText();

		Assert.assertEquals(dashboard.getCurrentTitle(), "OpenEMR");
	}

}
