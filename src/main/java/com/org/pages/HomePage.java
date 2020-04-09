package com.org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.org.base.Page;

public class HomePage extends Page{

	
	
	public void goToFreeSignUp() {
		
		driver.findElement(By.cssSelector(".zh-signup")).click();

		
	}
	
	public void goToCustomers() {
		
		driver.findElement(By.cssSelector(".zh-customers")).click();

		
	}
	
	public void goToContactSales() {
		
		driver.findElement(By.cssSelector(".zh-contact")).click();

		
	}
	
	public void goToSupport() {
		
		driver.findElement(By.cssSelector(".zh-support")).click();

	}
	
	public LoginPage goToLogin() {
		
		click("loginbtn_CSS");
		return new LoginPage();
	}
	
	public void goToLearnMore() {
		
		driver.findElement(By.cssSelector(".learn-more")).click();

	}
	
	public void goToValidateFooter() {
		
		driver.findElement(By.cssSelector(".footer-links-wrap")).click();

	}
	
	
}
