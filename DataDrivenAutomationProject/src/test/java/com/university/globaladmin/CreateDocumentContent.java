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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("selectGlobalAdminDashboard_XPATH"))));
		click("selectGlobalAdminDashboard_XPATH");
		click("manageContentWidget_XPATH");
		
	}
	
	@Test(dataProviderClass=DataProviders.class, dataProvider="getDataGlobalAdmin")
	public void createDocumentContent(HashMap<String, String>data) throws InterruptedException{
		
		ExcelReader excel = new ExcelReader(Constants.UNIVERSITY_PATH_SUITE1);
		CommonUtils.checkExecution("GlobalAdmin", "createDocumentContent", data.get(Constants.TESTCASE_COL_RUNMODE), excel);
			
		Thread.sleep(15000);	
		List<WebElement> ele = driver.findElements(By.tagName("iframe"));
	    System.out.println("Number of frames in a page :" + ele.size());
	    for(WebElement el : ele){
	        //Returns the Id of a frame.
	          System.out.println("Frame Id :" + el.getAttribute("id"));
	        //Returns the Name of a frame.
	        //System.out.println("Frame name :" + el.getAttribute("name"));
	      }
	    
		driver.switchTo().frame(ele.get(0));	
		test.log(LogStatus.INFO, "Switched to the frame successfully: "+ele.get(0).toString());
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("manageAllContentsTab_XPATH"))));
		click("manageAllContentsTab_XPATH");	
		
		try {
			click("createNewContentBtn_XPATH");
			Thread.sleep(2000);
		}catch(Exception e){
			test.log(LogStatus.INFO, "Inside the catch block of Create New Content Button");
			click("createNewContentPreliminaryBtn_XPATH");
			Thread.sleep(2000);
		}
		
		type("contentName_XPATH", data.get("ContentName"));
		
		//select("contentModeDropDown_XPATH", data.get("Mode"));
		click("contentModeDropDown_XPATH");		
		Thread.sleep(1000);
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
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("contentTypeDropDown_XPATH"))));
		click("contentTypeDropDown_XPATH");
		Thread.sleep(1000);
		if(data.get("Type").equalsIgnoreCase("Document")) {	
			//click("documentContentType_XPATH");
			System.out.println(Keys.ESCAPE);
			test.log(LogStatus.INFO, "Document Content type is Preselected for Content Mode- Document");
		}else if(data.get("Type").equalsIgnoreCase("SCORM")) {
			click("scormContentType_XPATH");
			test.log(LogStatus.INFO, "SCORM content type Selected");
		}else if(data.get("Type").equalsIgnoreCase("training content")) {
			click("trainingContentType_XPATH");
			test.log(LogStatus.INFO, "Training content type selected");
		}else if(data.get("Type").equalsIgnoreCase("Reference Link")) {
			click("referenceLinkContentType_XPATH");
			test.log(LogStatus.INFO, "Training content type selected");	
		}
		
		
		//else {
		//	Assert.fail("couldn't find the Content Type");
		//}
		
		Thread.sleep(1000);
		type("durationHH_XPATH",data.get("DurationHH"));
		Thread.sleep(1000);
		type("durationMM_XPATH",data.get("DurationMM"));
		
		click("selectSource_XPATH");
		click("selectInternalDSSource_XPATH");
		
		Thread.sleep(1000);
		click("ta3DS_XPATH");
		click("taBusinessPartner_XPATH");
		click("taCSIPartner_XPATH");
		click("taPCPartner_XPATH");
		type("selectTargetRoles_XPATH",data.get("TargetRoles"));
		Thread.sleep(1000);
		click("targetRoleQA_XPATH");
		type("geoAvailability_XPATH",data.get("GeoAvailability"));
		click("geoSelect_XPATH");
		//System.out.println(Keys.ENTER);
		click("selectAxisDropDown_XPATH");
		if(data.get("Axis").equalsIgnoreCase("Brand")) {
		Thread.sleep(1000);	
		click("selectBrandOption_XPATH");
		click("selectBrandBtn_XPATH");	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("selectBrandENOVIA_XPATH"))));
		click("selectBrandENOVIA_XPATH");
		click("addBrandBtn_XPATH");		
		}
		
		//Add Language 
		click("addLanguageBtn_XPATH");
		Thread.sleep(1000);
		click("languageDropDown_XPATH");	
		if(data.get("Language").equalsIgnoreCase("English")) {
			Thread.sleep(1000);	
			click("selectEnglishLanguage_XPATH");	
		}else if(data.get("Language").equalsIgnoreCase("Chinese")) {
			click("selectChineseLanguage_XPATH");
		}else if(data.get("Language").equalsIgnoreCase("French")) {
			click("selectFrenchLanguage_XPATH");
		}else {
			Assert.fail("couldn't find the Language");
		}
		
		type("languageDescription_XPATH",data.get("LangDesc"));
		type("learningObjectives_XPATH",data.get("LearningObjectives"));
		type("addObjectivesRow1_XPATH",data.get("LineOne"));
		type("addObjectivesRow2_XPATH",data.get("LineTwo"));
		type("addObjectivesRow3_XPATH",data.get("LineThree"));
		type("addObjectivesRow4_XPATH",data.get("LineFour"));
		type("addObjectivesRow5_XPATH",data.get("LineFive"));
		
		if(data.get("Type").equalsIgnoreCase("Document") || data.get("Type").equalsIgnoreCase("SCORM")) {
			Thread.sleep(1000);	
			click("languageBrowseDocumentsBtn_XPATH");	
			autoitX.winWait("Open", "", 5000);
			autoitX.winWaitActive("Open");
			autoitX.controlFocus("Open", "", "Edit1");
			String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdatafiles\\"+data.get("Documents");
			System.out.println(filepath);
			autoitX.ControlSetText("Open", "", "Edit1", filepath);
			Thread.sleep(2000);
			autoitX.controlClick("Open", "", "Button1") ;
			Thread.sleep(5000);
			test.log(LogStatus.INFO, "File uploaded Successfully :"+filepath);
			
		}else if(data.get("Type").equalsIgnoreCase("Reference Link")) {
			type("specifyURL_XPATH", data.get("SpecifyURL"));
			test.log(LogStatus.INFO, "Reflink URL entered Successfully :"+data.get("SpecifyURL"));
		}else {
			Assert.fail("Failed to upload a document or Failed to enter a URL(for Reflink)");
		}
		
		click("languageSubmitBtn_XPATH");	
		Thread.sleep(1000);
		test.log(LogStatus.INFO, "Switching to Create New cotent >Source File Section");
		click("browseSourceFileInput_XPATH");
		autoitX.winWait("Open", "", 5000);
		autoitX.winWaitActive("Open");
		autoitX.controlFocus("Open", "", "Edit1");
		String filepath2 = System.getProperty("user.dir")+"\\src\\test\\resources\\testdatafiles\\"+data.get("File");
		System.out.println(filepath2);
		autoitX.ControlSetText("Open", "", "Edit1", filepath2);
		Thread.sleep(2000);
		autoitX.controlClick("Open", "", "Button1") ;
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Source File uploaded Successfully :"+filepath2);
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("fileUploadSuccess_XPATH"))));
		
		type("fileComment_XPATH",data.get("FileComment"));
		click("uploadFileBtn_XPATH");
		Thread.sleep(6000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("uploadedVisibleFileLink_XPATH"))));
		
	   click("RASCIPage_XPATH");
	   Thread.sleep(1000);
	   type("responsible_XPATH",data.get("Responsible"));
	   Thread.sleep(3000);
	   click("responsibleUser_XPATH");
  
	   type("support_XPATH",data.get("Support"));
	   Thread.sleep(3000);
	   click("supportUser_XPATH");
	   
	   type("approver_XPATH",data.get("Approver"));
	   Thread.sleep(3000);
	   click("approverUser_XPATH");
	 
	   click("publishContentBtn_XPATH");
	   Thread.sleep(2000);
	   
	   test.log(LogStatus.INFO, "Content "+"'"+data.get("ContentName")+"'"+" published successfully");
		   
	   driver.switchTo().defaultContent();
	   System.out.println("PASSED");
	}	

}
