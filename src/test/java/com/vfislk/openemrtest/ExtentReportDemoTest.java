package com.vfislk.openemrtest;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemoTest {
	public ExtentReports extent; //setup the report
	public ExtentTest test; //log the detail in the report
	
	@BeforeSuite
	public void start()
	{
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
		extent.attachReporter(spark);
	}
	
	@AfterSuite
	public void end()
	{
		extent.flush();
	}
	
	@BeforeMethod
	public void beforeMethod(Method method)
	{
		test=extent.createTest(method.getName());
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		System.out.println(result.getStatus());
		
		if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(Status.FAIL,"Failed "+result.getName());
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS,"Passed "+result.getName());
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			test.log(Status.SKIP,"Skipped "+result.getName());
		}
		
	}
	
	@Test
	public void validTest()
	{
		System.out.println("valid");
		Assert.fail("failed driver");
		test.log(Status.INFO, "validTest completed!!");
	}

	@Test
	public void invalidTest()
	{
		System.out.println("invalid");
		test.log(Status.INFO, "invalidTest completed!!");
	}

}
