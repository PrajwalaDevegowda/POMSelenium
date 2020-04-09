package com.org.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.org.pages.HomePage;
import com.org.pages.LoginPage;
import com.org.pages.ZohoAppPage;
import com.org.utilities.ScreenShotUtil;

public class TestSignIn {

	@Test(dataProviderClass= ScreenShotUtil.class,dataProvider="dp")
	public void login(Hashtable<String,String> data) {
		HomePage home = new HomePage();
		LoginPage page = home.goToLogin();
		ZohoAppPage apppage = page.doLogin(data.get("username"), data.get("password"));
	}
}
