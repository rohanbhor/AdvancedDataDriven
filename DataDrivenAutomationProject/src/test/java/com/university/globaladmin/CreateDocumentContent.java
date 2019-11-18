package com.university.globaladmin;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ds.base.TestBase;
import com.ds.utilities.CommonUtils;
import com.ds.utilities.Constants;
import com.ds.utilities.DataProviders;
import com.ds.utilities.ExcelReader;
import com.relevantcodes.extentreports.LogStatus;



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
		Thread.sleep(5000);
		type("contentName_XPATH", data.get("ContentName"));
		
		//select("contentModeDropDown_XPATH", data.get("Mode"));
		click("contentModeDropDown_XPATH");		
		Thread.sleep(2000);
		if(data.get("Mode").equalsIgnoreCase("Document")) {
			click("doumentMode_XPATH");
			test.log(LogStatus.INFO, "Document Mod Selected");
		}else if(data.get("Mode").equalsIgnoreCase("elearning")) {
			click("elearningMode_XPATH");
			test.log(LogStatus.INFO, "elearning Mod Selected");
		}else if(data.get("Mode").equalsIgnoreCase("instructorLedMode")) {
			click("instructorLedMode_XPATH");
			test.log(LogStatus.INFO, "Instructor Led Mod Selected");
		}else {
			Assert.fail("couldn't find the Mode");
		}
		
		//select("contentTypeDropDown_XPATH", data.get("Type"));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("contentTypeDropDown_XPATH"))));
		click("contentTypeDropDown_XPATH");
		Thread.sleep(2000);
		if(data.get("Type").equalsIgnoreCase("Document")) {	
			//click("documentContentType_XPATH");
			System.out.println(Keys.ESCAPE);
			System.out.println("Document Content type is Preselected for Content Mode- Document");
			test.log(LogStatus.INFO, "Document Content Type Selected");
		}else if(data.get("Type").equalsIgnoreCase("SCORM")) {
			click("scormContentType_XPATH");
			test.log(LogStatus.INFO, "SCORM content type Selected");
		}else if(data.get("Type").equalsIgnoreCase("training content")) {
			click("trainingContentType_XPATH");
			test.log(LogStatus.INFO, "Training content type selected");
		}
		
		//else {
		//	Assert.fail("couldn't find the Content Type");
		//}
		
		
		type("durationHH_XPATH",data.get("DurationHH"));
		type("durationHH_XPATH",data.get("DurationMM"));
		
		
		//select("selectSource_XPATH", data.get("Source"));
		click("selectSource_XPATH");
		click("selectInternalDSSource_XPATH");
		
		Thread.sleep(1000);
		click("ta3DS_XPATH");
		click("taBusinessPartner_XPATH");
		click("taCSIPartner_XPATH");
		click("taPCPartner_XPATH");
		type("selectTargetRoles_XPATH",data.get("TargetRoles"));
		Thread.sleep(1000);
		click("targetRoleQA");
		type("geoAvailability_XPATH",data.get("GeoAvailability"));
		click("geoIndia_XPATH");
		
		if(data.get("Axis").equalsIgnoreCase("Brand")) {
		select("selectAxisDropDown_XPATH",data.get("Axis"));
		Thread.sleep(2000);
		click("selectBrandBtn_XPATH");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("selectBrandENOVIA_XPATH"))));
		click("selectBrandENOVIA_XPATH");
		click("addBrandBtn_XPATH");		
		}
		
		System.out.println("PASSED");
	}	

}
