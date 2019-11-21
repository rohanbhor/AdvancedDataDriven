package com.ds.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ds.utilities.ExcelReader;
import com.ds.utilities.ExtentManager;
import com.ds.utilities.TestUtil;
import com.jacob.com.LibraryLoader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import autoitx4java.AutoItX;

public class TestBase {
	
	/* We will be initializing following in this class::
	 * Webdriver
	 * Properties
	 * Logs
	 * ExtentReports
	 * DB
	 * ExcelReader
	 * Mail
	 * ReportNG, ExtendReports
	 * */
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoylogger");
	public static ExcelReader excel=null;
	public static WebDriverWait wait;
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static AutoItX autoitX;
	
	@BeforeSuite
	public void setUp(){
		
		if(driver==null){
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.debug("Config file loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			or.load(fis);
			log.debug("or file loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		//here we are selecting the browser from the jenkins services , if it is not available then
		//we are selecting it from Config file. 
		if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
			browser= System.getenv("browser");
		}else{
			browser= System.getProperty("browser");
		}
		config.setProperty("browser", browser);
		*/
		//Picking up the browser
		if(config.getProperty("browser").equals("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/test/resources/executables/"+"geckodriver.exe");
			driver= new FirefoxDriver();
			log.debug("Firefox Browser Launched");
		}else if(config.getProperty("browser").equals("chrome")){	
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/executables/"+"chromedriver.exe");			
			DesiredCapabilities cap = DesiredCapabilities.chrome();
		    cap.setCapability("applicationCacheEnabled", false);    
			driver= new ChromeDriver(cap);
			log.debug("Chrome Browser Launched");
		
		}else if(config.getProperty("browser").equals(System.getProperty("user.dir")+"/src/test/resources/executables/"+"internetexlorer")){
			System.setProperty("webdriver.ie.driver", "IEdriverServer.exe");
			driver= new InternetExplorerDriver();
			log.debug("IE Browser Launched");
		}else{
			System.out.println("Please Mention the browser type in the object properties file");
		}
		
		AutoItInit();
		autoitX = new AutoItX();
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,20);
		
		}
	}
	
	public static void click(String locator){
		if (locator.contains("XPATH")){
			driver.findElement(By.xpath(or.getProperty(locator))).click();
			System.out.println("clicked on locator: "+locator);
			test.log(LogStatus.INFO, "Clicked on locator: "+or.getProperty(locator));
		}else if(locator.contains("CSS")){
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
			System.out.println("clicked on locator: "+or.getProperty(locator));
			test.log(LogStatus.INFO, "Clicked on locator: "+locator);
		}else if(locator.contains("ID")){
			driver.findElement(By.id(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "Clicked on locator: "+locator);
		}else if(locator.contains("NAME")){
			driver.findElement(By.name(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "Clicked on locator: "+locator);
		}else if(locator.contains("TAGNAME")){
			driver.findElement(By.tagName(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "Clicked on locator: "+locator);
		}else{
			Assert.fail("Locator is incorrect or not present :"+ locator);
			test.log(LogStatus.FAIL, "Locator is incorrect or not present :"+locator);
		}
		
	}
	
	public static void type(String locator, String value){
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		if (locator.contains("XPATH")){
			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(selectAll+value);
			System.out.println("clicked on locator: "+or.getProperty(locator)+" Entered Value: "+value);
			test.log(LogStatus.INFO, "Clicked on locator: "+locator+", "+" Entered Value: "+value);
		}else if(locator.contains("CSS")){
			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(selectAll+value);
			System.out.println("clicked on locator: "+or.getProperty(locator)+" Entered Value: "+value);
			test.log(LogStatus.INFO, "Clicked on locator: "+locator+", "+" Entered Value: "+value);
		}else if(locator.contains("ID")){
			driver.findElement(By.id(or.getProperty(locator))).sendKeys(selectAll+value);
			test.log(LogStatus.INFO, "Clicked on locator: "+locator+", "+" Entered Value: "+value);
		}else if(locator.contains("NAME")){
			driver.findElement(By.name(or.getProperty(locator))).sendKeys(selectAll+value);
			test.log(LogStatus.INFO, "Clicked on locator: "+locator+", "+" Entered Value: "+value);
		}else if(locator.contains("TAGNAME")){
			driver.findElement(By.tagName(or.getProperty(locator))).sendKeys(selectAll+value);
			test.log(LogStatus.INFO, "Clicked on locator: "+locator+", "+" Entered Value: "+value);
		}else{
			Assert.fail("Locator is incorrect or not present :"+ locator);
			test.log(LogStatus.FAIL, "Locator is incorrect or not present :"+locator);
		}
		
	}
	
	
	public static WebElement dropdown;
	public void select(String locator, String value){
		if (locator.contains("XPATH")){
			dropdown= driver.findElement(By.xpath(or.getProperty(locator)));
			test.log(LogStatus.INFO, "Clicked on locator: "+locator);
		}else if(locator.contains("CSS")){
			dropdown= driver.findElement(By.cssSelector(or.getProperty(locator)));
			test.log(LogStatus.INFO, "Clicked on locator: "+locator);
		}else if(locator.contains("ID")){
			dropdown= driver.findElement(By.id(or.getProperty(locator)));
			test.log(LogStatus.INFO, "Clicked on locator: "+locator);
		}else if(locator.contains("NAME")){
			dropdown= driver.findElement(By.name(or.getProperty(locator)));
			test.log(LogStatus.INFO, "Clicked on locator: "+locator);
		}else if(locator.contains("TAGNAME")){
			dropdown= driver.findElement(By.tagName(or.getProperty(locator)));
			test.log(LogStatus.INFO, "Clicked on locator: "+locator);
		}else{
			Assert.fail("Locator is incorrect or not present :"+ locator);
			test.log(LogStatus.FAIL, "Locator is incorrect or not present :"+locator);
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, "selecting from dropdown :"+locator+" Value as: "+ value);
	}
	
	public void verifyEquals(String actual, String expected) throws IOException{
		try{
			Assert.assertEquals(actual, expected);
		}catch(Throwable t){
			TestUtil.captureScreenshot();
			
			//Report NG Logs:
			Reporter.log("<br>"+ "Verification Failure: "+t.getMessage()+"<br>");
			Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotpath2+"><img height=200 width=200 src="+TestUtil.screenshotpath2+"></img></a>");
			Reporter.log("<br>");
			
			//Extent Reports
			test.log(LogStatus.FAIL,"Verification FAILED with exception :"+t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotpath2));			
		}
	}
	
	
	public static boolean isElementPresent(By by){		
		try{
			driver.findElement(by);
			return true;		
		}catch(NoSuchElementException e){
			return false;			
		}	
	}
	
	public static void doPassportLogin() {
		type("passportUsername_XPATH", "UE7");
		type("passportPassword_XPATH", "Pokahontas@300");
		click("passportLoginBtn_XPATH");	
	}
	
	public static String jvmBitVersion(){
		 return System.getProperty("sun.arch.data.model");
		}
	
	
	public static void AutoItInit() {
		String jacobDllVersionToUse;

		if (TestBase.jvmBitVersion().contains("32")){
		jacobDllVersionToUse = "jacob-1.18-M2-x86.dll";
		//jacobDllVersionToUse = "AutoItX3.dll";
		}
		else {
		jacobDllVersionToUse = "jacob-1.18-M2-x64.dll";
		//jacobDllVersionToUse = "AutoItX3_x64.dll";
		}

		File file = new File(System.getProperty("user.dir")+"/src/test/resources/lib/"+jacobDllVersionToUse);
		System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());		
	}
	
	@AfterSuite
	public void tearDown(){
		
		//driver.quit();
		log.debug("Test Execution is Completed");
		
	}
	
	


}
