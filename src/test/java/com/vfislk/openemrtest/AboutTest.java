package com.vfislk.openemrtest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.vfislk.openemrbase.WebDriverWrapper;
import com.vfislk.openemrpages.AboutOpenEMRPage;
import com.vfislk.openemrpages.DashboardPage;
import com.vfislk.openemrpages.LoginPage;
import com.vfislk.utilities.DataProviderUtils;

public class AboutTest extends WebDriverWrapper {
	
	@Test(dataProviderClass = DataProviderUtils.class,dataProvider = "commonDataProvider")
	public void checkHeaderAndVersionTest(String username,String password,String language,String expectedHeader,String expectedVersionNumber) throws IOException
	{
		LoginPage login=new LoginPage(driver);	
		login.enterUsername(username);
		test.log(Status.INFO, "Entered username as "+username);
		login.enterPassword(password);
		test.log(Status.INFO, "Entered password as "+password);
		login.selectLanguageByText(language);
		test.log(Status.INFO, "Entered language as "+language);
		login.clickOnLogin();
		test.log(Status.INFO, "Clicked on login");
		DashboardPage dashboard=new DashboardPage(driver);
		dashboard.clickOnAbout();
		
//		test.addScreenCaptureFromBase64String(takeScreenshot(), "AboutTestMethod"+username);

		AboutOpenEMRPage aboutPage=new AboutOpenEMRPage(driver);
		Assert.assertEquals(aboutPage.getHeaderText(), expectedHeader,"Assertion on header");
		Assert.assertEquals(aboutPage.getVersionNumberText(), expectedVersionNumber,"Assertion on version number");
		test.log(Status.INFO, "Verification completed for header and version number "+expectedHeader+" "+expectedVersionNumber);
	}
}
