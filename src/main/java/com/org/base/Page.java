package com.org.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.org.utilities.ExcelReader;
import com.org.utilities.ExtentManager;
import com.org.utilities.ScreenShotUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page {

	public static WebDriver driver;
	public static TopMenu menu;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\com\\org\\excel\\TestData.xlsx");

	public static WebDriverWait wait;
	public ExtentReports exp = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	
	public Page()  {
		if (driver == null) {
			
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\org\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("config loaded");

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\org\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("OR loaded");
			
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}

			config.setProperty("browser", browser);
			
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");

			if (config.getProperty("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);

			} else if (config.getProperty("browser").equals("ie")) {

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();

			}

			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			menu = new TopMenu(driver);
		}
	}
	
	public static void quit() {

		driver.quit();

	}
	
	public boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {

			return false;
		}
	}
	
	public static void click(String Locator) {
		
		if(Locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(Locator))).click();
		}else if(Locator.endsWith("_XPATH")){
			driver.findElement(By.xpath(OR.getProperty(Locator))).click();
		}else if(Locator.endsWith("_ID")){
			driver.findElement(By.id(OR.getProperty(Locator))).click();
		}
		test.log(LogStatus.INFO, Locator+ " was Clicked ");
		
	}
	
	public static void type(String Locator, String value) {
	
		if(Locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(Locator))).sendKeys(value);
		}else if(Locator.endsWith("_XPATH")){
			driver.findElement(By.xpath(OR.getProperty(Locator))).sendKeys(value);
		}else if(Locator.endsWith("_ID")){
			driver.findElement(By.id(OR.getProperty(Locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, Locator+ " was typed and entered value is : " +value);
		
	}
	
	public void verifyEquals(String actual, String expected) throws IOException {
		
		try {
			
			Assert.assertEquals(actual, expected);
			
		}catch(Throwable t) {
			
			ScreenShotUtil.caputreScreenshot();
			//Report NG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<br>");
			Reporter.log("<a  color:red target= \"_blank\" href="+ScreenShotUtil.screenshotName+" > <img src="+ScreenShotUtil.screenshotName+" height=400 width=200></img> </a>");
			
			
			//Extent Report
			test.log(LogStatus.FAIL, "Failed with Exception : " +t.getMessage());
			test.log(LogStatus.FAIL,test.addScreenCapture(ScreenShotUtil.screenshotName));
			exp.endTest(test);
			exp.flush();
			
		}
		
	}
	public static WebElement dropdown;
	public void select(String Locator, String value) {
		
		if(Locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(Locator)));
		}else if(Locator.endsWith("_XPATH")){
			dropdown = driver.findElement(By.xpath(OR.getProperty(Locator)));
		}else if(Locator.endsWith("_ID")){
			dropdown = driver.findElement(By.id(OR.getProperty(Locator)));
		}
		
		Select select = new Select(dropdown);
		
		
		select.selectByVisibleText(value);

		test.log(LogStatus.INFO, "Selecting from dropdown : " + Locator + " value as " + value);

		
		
	}
}
