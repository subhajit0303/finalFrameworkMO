package com.learnautomation.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.learnautomation.pages.BaseClass;
import com.learnautomation.pages.LoginPage;
import com.learnautomation.utilities.ExcelDataProvider;
import com.learnautomation.utilities.Helper;

public class loginTestCRM extends BaseClass {

	
	@Test
	public void loginApp() throws InterruptedException{
		
		logger = report.createTest("Login to CRM Application");
		
		LoginPage loginPg = PageFactory.initElements(driver, LoginPage.class);
		
		logger.log(Status.INFO, "Starting Application");
		
		loginPg.loginToCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		
		logger.log(Status.PASS, "Login Successful");
		
	}
}
