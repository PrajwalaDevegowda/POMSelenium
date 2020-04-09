package com.org.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;


import com.org.base.Page;

public class LoginPage extends Page{


	public ZohoAppPage doLogin(String username, String password) {
		
		
		type("email_ID",username);
		//driver.findElement(By.id("email_ID")).sendKeys(username);
		click("nextbtn_ID");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		type("password_ID",password);
		click("loginbtn_ID");
		return new ZohoAppPage();
		
		
	}
}
