package com.org.pages.crm;

import org.openqa.selenium.By;

import com.org.base.Page;

public class CRMAccounts extends Page {

	public void goToImport() {
		
	}
	
	public CRMAccounCreate goToCreateAccount() {
		
		//driver.findElement(By.xpath("//*[@id='topRightIcons']/span[1]/link-to/button")).click();
		click("createaccount_XPATH");
		return new CRMAccounCreate();
	}
}
