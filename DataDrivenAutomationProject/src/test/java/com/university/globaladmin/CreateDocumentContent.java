package com.university.globaladmin;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ds.base.TestBase;
import com.ds.utilities.CommonUtils;
import com.ds.utilities.Constants;
import com.ds.utilities.DataProviders;
import com.ds.utilities.ExcelReader;



public class CreateDocumentContent extends TestBase{
	
	
	@BeforeClass
	public void setUp() {
		driver.get(config.getProperty("qalGlobalAdmin"));
		log.debug("Website Launched : "+config.getProperty("qalGlobalAdmin"));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("loginUsername_XPATH"))));
		type("loginUsername_XPATH", "TSTITM1");
		type("loginPassword_XPATH", "3dplm_123#");
		click("loginBtn_XPATH");		
		click("dashBoardDropDownBtn_XPATH");		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("searchDashboard_XPATH"))));
		//type("searchDashboard_XPATH", config.getProperty("qalDashboardName"));
		type("searchDashboard_XPATH", "3DXU- Global Admin (QAL)");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("selectDashboard_XPATH"))));
		click("selectDashboard_XPATH");
	}
	
	
	
	@Test(dataProviderClass=DataProviders.class, dataProvider="getDataGlobalAdmin")
	public void createDocumentContent(HashMap<String, String>data) throws InterruptedException{
		
		ExcelReader excel = new ExcelReader(Constants.UNIVERSITY_PATH_SUITE1);
		CommonUtils.checkExecution("GlobalAdmin", "createDocumentContent", data.get(Constants.TESTCASE_COL_RUNMODE), excel);
		
	
		click("manageContentWidget_XPATH");
		
		List<WebElement> ele = driver.findElements(By.tagName("iframe"));
	    System.out.println("Number of frames in a page :" + ele.size());
	    for(WebElement el : ele){
	        //Returns the Id of a frame.
	          System.out.println("Frame Id :" + el.getAttribute("id"));
	        //Returns the Name of a frame.
	        //System.out.println("Frame name :" + el.getAttribute("name"));
	      }
	    
		driver.switchTo().frame(ele.get(0));	
		System.out.println("Switch to frame successfully");
		Thread.sleep(10000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("manageAllContentsTab_XPATH"))));
		click("manageAllContentsTab_XPATH");	
		click("createNewContentBtn_XPATH");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("contentName_XPATH"))));
		type("contentName_XPATH", data.get("ContentName"));
		
		System.out.println("PASSED");
	}	

}
