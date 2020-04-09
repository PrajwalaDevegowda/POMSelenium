package com.org.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.org.base.Page;
import com.org.pages.HomePage;
import com.org.pages.LoginPage;
import com.org.pages.ZohoAppPage;
import com.org.pages.crm.CRMAccounCreate;
import com.org.pages.crm.CRMAccounts;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	

	public static void main(String[] args) {

		
		
		HomePage home = new HomePage();
		LoginPage page = home.goToLogin();
		ZohoAppPage apppage = page.doLogin("prajwalahd18@gmail.com", "Prahede@09");
		
		apppage.gotoCRM();
		
		CRMAccounts crmacct = Page.menu.goToAccount();
		CRMAccounCreate ac = crmacct.goToCreateAccount();
		ac.createAccount("Pavan");
		
	}
}
