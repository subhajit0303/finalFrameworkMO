package com.learnautomation.pages;

import java.io.File;
import java.io.IOException;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.w3c.dom.UserDataHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.utilities.BrowserFactory;
import com.learnautomation.utilities.ConfigDataProvider;
import com.learnautomation.utilities.ExcelDataProvider;
import com.learnautomation.utilities.Helper;

public class BaseClass {
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setUpSuite() {
		 excel = new ExcelDataProvider();
		 config = new ConfigDataProvider();
		 
		 ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/testReport.html"));
		 report = new ExtentReports();
		 report.attachReporter(extent);
	}
	
	@BeforeClass
	public void setup() {
		driver = BrowserFactory.startApplication(driver,config.getBrowserFromConfig(),config.getAppUrlFromConfig());
	}
	
	@AfterClass
	public void teardown() {
		BrowserFactory.closeBrowser(driver);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(Status.FAIL, result.getThrowable());
			logger.log(Status.FAIL, "Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, "Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if (result.getStatus()==ITestResult.SKIP)
		{
			logger.log(Status.SKIP, "Test Skipped", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		
		report.flush();
	}

}
