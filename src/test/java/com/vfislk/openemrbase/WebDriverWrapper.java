package com.vfislk.openemrbase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.vfislk.openemrpages.DashboardPage;
import com.vfislk.openemrpages.LoginPage;
import com.vfislk.utilities.PropertiesUtils;

public class WebDriverWrapper {
	protected WebDriver driver;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeSuite(alwaysRun = true)
	public void start() {
		extent = new ExtentReports();
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent-reportt/testReport.html");
		extent.attachReporter(htmlReporter);
	}

	@AfterSuite(alwaysRun = true)
	public void end() {
		// to write or update test information to reporter
		extent.flush();
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browsername", "url" })
	public void setUp(@Optional("ch") String browser,
			@Optional("https://demo.openemr.io/a/openemr/index.php") String url, Method method) {

		if (PropertiesUtils.getValue("src/test/resources/testdata/data.properties", "priority").equals("yes")) {
			browser = PropertiesUtils.getValue("src/test/resources/testdata/data.properties", "browser");
			url = PropertiesUtils.getValue("src/test/resources/testdata/data.properties", "url");
		}

		launchBrowser(browser);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		
		test = extent.createTest(method.getName());
	}

	public void launchBrowser(String browser) {
		switch (browser.toLowerCase()) {
		case "ff":
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", "src/test/resources/driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
	}

	public String takeScreenshot() throws IOException {
		String fileName = "screenshot_" + LocalDateTime.now().toString().replace(":", "-") + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("src/test/resources/screenshots/" + fileName));
		
		
		String base64=ts.getScreenshotAs(OutputType.BASE64);
		return base64;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
//			test.addScreenCaptureFromPath("src/test/resources/screenshots/screenshot_2021-07-29T09-49-23.601.png"); //add file 
		
		test.addScreenCaptureFromBase64String(takeScreenshot(), result.getName());
		driver.quit();
	}

}
