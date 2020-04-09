package com.org.base;

import org.testng.annotations.AfterSuite;

public class TestBase {

	@AfterSuite
	public void TearDown() {
		Page.quit();
	}
}
