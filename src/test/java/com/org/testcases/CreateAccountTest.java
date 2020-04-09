package com.org.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.org.base.Page;
import com.org.pages.ZohoAppPage;
import com.org.pages.crm.CRMAccounCreate;
import com.org.pages.crm.CRMAccounts;
import com.org.utilities.ScreenShotUtil;

public class CreateAccountTest {

	@Test(dataProviderClass= ScreenShotUtil.class,dataProvider="dp")
	public void createaccount(Hashtable<String,String> data) {
		
		ZohoAppPage apppage= new ZohoAppPage();
		apppage.gotoCRM();
		
		CRMAccounts crmacct = Page.menu.goToAccount();
		CRMAccounCreate ac = crmacct.goToCreateAccount();
		ac.createAccount(data.get("name"));
		
	}
}
