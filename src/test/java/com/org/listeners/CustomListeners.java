package com.org.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.org.base.Page;
import com.org.utilities.MonitoringMail;
import com.org.utilities.ScreenShotUtil;
import com.org.utilities.TestConfig;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends Page implements ITestListener, ISuiteListener{

	public String messagebody;
	public void onTestStart(ITestResult result) {

		test =  exp.startTest(result.getName().toUpperCase());
	}

	public void onTestSuccess(ITestResult result) {
		
		test.log(LogStatus.PASS, result.getName().toUpperCase() +"PASS");
		exp.endTest(test);
		exp.flush();
		
	}

	public void onTestFailure(ITestResult result) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Test is successful");
		try {
			ScreenShotUtil.caputreScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL, result.getName().toUpperCase() +"Failed with Exception : " +result.getThrowable());
		test.log(LogStatus.FAIL,test.addScreenCapture(ScreenShotUtil.screenshotName));
		Reporter.log("<a color:red target= \"_blank\" href="+ScreenShotUtil.screenshotName+"> ScreenShot </a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a  color:red target= \"_blank\" href="+ScreenShotUtil.screenshotName+" > <img src="+ScreenShotUtil.screenshotName+" height=400 width=200></img> </a>");
		exp.endTest(test);
		exp.flush();
		
	}

	public void onTestSkipped(ITestResult result) {


		
		  test.log(LogStatus.SKIP, result.getName().toUpperCase() +"SKIPPED");
		  exp.endTest(test); exp.flush();
		 
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		
		
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite suite) {

		/*
		 * MonitoringMail mail = new MonitoringMail();
		 * 
		 * try { messagebody =
		 * "Report of my First Selenium Test is ready. Please click the link to see the Report http://"
		 * + InetAddress.getLocalHost().getHostAddress()+
		 * ":8080/job/DataDrivenProject/HTML_20Report/"; } catch (UnknownHostException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * try { mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to,
		 * TestConfig.subject, messagebody); } catch (AddressException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (MessagingException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		
	}

	
}
