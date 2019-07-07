package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriver driver;
	WebDriverWait wd;
	
	public LoginPage(WebDriver ldriver) {
		
		this.driver = ldriver;
		
	}
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//div[@class='input-group-btn']")
	WebElement LoginBtn;
	
	@FindBy(xpath="//td[@class='headertext']")
	WebElement loginSuccessHeader;
	
	public void loginToCRM(String uName, String pw) throws InterruptedException {
		
		username.sendKeys(uName);
		password.sendKeys(pw);
		Thread.sleep(5000);
		LoginBtn.click();
		
		wd = new WebDriverWait(driver, 30);
		
		wd.until(ExpectedConditions.textToBePresentInElement(loginSuccessHeader, "subhajit mitra"));
		
		
		
	}

}
