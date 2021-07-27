package com.vfislk.openemrtest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vfislk.openemrbase.WebDriverWrapper;
import com.vfislk.openemrpages.DashboardPage;
import com.vfislk.openemrpages.LoginPage;
import com.vfislk.utilities.DataProviderUtils;
	
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
	
	@Test(dataProviderClass = DataProviderUtils.class,dataProvider = "validCredentialData",description = "Valid Credential Test")
	public void validCredentialTest(String username,String password,String language,String expectedValue) {
	
		LoginPage login = new LoginPage(driver);
		login.enterUsername(username);
		login.enterPassword(password);
		login.selectLanguageByText(language);
		login.clickOnLogin();

		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.waitForPresenceOfCalendarText();

		Assert.assertEquals(dashboard.getCurrentTitle(), expectedValue);
	}
	
	
}
