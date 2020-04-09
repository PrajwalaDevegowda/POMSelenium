package com.org.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.org.pages.crm.CRMAccounts;

public class TopMenu {
	
	WebDriver driver;
	
	public TopMenu(WebDriver driver) {
		this.driver = driver;
	}

	public void goToHome() {
		
	}
	
	public void goToLeads() {
		
	}
	
	public CRMAccounts goToAccount() {
		Page.click("accountTab_XPATH");
		return new CRMAccounts();
	}
	
	public void goToContacts() {
		
	}
	
	public void goToSingout() {
		
	}
	
}
