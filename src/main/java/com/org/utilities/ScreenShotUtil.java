package com.org.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import com.org.base.Page;


public class ScreenShotUtil extends Page {

	public static String screenshotName;
	
	public static void caputreScreenshot() throws IOException {
		
		File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		screenshotName= d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		Reporter.log("Inside Screen Shot Util : ScreenShot Name is " +screenshotName);
		FileUtils.copyFile(scr, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\" +screenshotName ));
	}

	
	public static boolean isTestRunnable(String Testname, ExcelReader excel) {
		
		String sheetname="test_suite";
		int rcnt= excel.getRowCount(sheetname);
		for(int rNum=2;rNum>=rcnt;rNum++) {
			
			String testcase = excel.getCellData(sheetname, "Testname", rNum);
			if(testcase.equalsIgnoreCase(Testname)) {
				String runmode= excel.getCellData(sheetname, "runmode", rNum);
				if(runmode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
				
			
		}
		return false;
		
	}
	
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m){
		
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String,String> table = null;
		
		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1),excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][0] = table;
				
			}

		}

		return data;
		
	}
}
