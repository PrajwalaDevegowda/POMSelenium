package com.org.pages.crm;

import org.openqa.selenium.By;

import com.org.base.Page;

public class CRMAccounCreate extends Page {

	public void createAccount(String name) {
		
		type("AccountName_ID",name);
		
	}
	
}
