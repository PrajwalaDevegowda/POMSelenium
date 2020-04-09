package com.org.pages;

import org.openqa.selenium.By;

import com.org.base.Page;
import com.org.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page {

	public CRMHomePage gotoCRM() {
		click("crmpage_XPATH");
		return new CRMHomePage();
	}

	public void gotoCalendar() {
		driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[2]/div/a/span")).click();
	}

	public void gotoDesk() {
		driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[7]/div/a/span")).click();
	}
}
