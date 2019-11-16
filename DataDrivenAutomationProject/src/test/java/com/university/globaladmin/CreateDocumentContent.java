package com.university.globaladmin;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.ds.base.TestBase;
import com.ds.utilities.CommonUtils;
import com.ds.utilities.Constants;
import com.ds.utilities.DataProviders;
import com.ds.utilities.ExcelReader;



public class CreateDocumentContent extends TestBase{
	
	
	@Test(dataProviderClass=DataProviders.class, dataProvider="getDataGlobalAdmin")
	public void createDocumentContent(HashMap<String, String>data){
		
		ExcelReader excel = new ExcelReader(Constants.UNIVERSITY_PATH_SUITE1);
		CommonUtils.checkExecution("GlobalAdmin", "createDocumentContent", data.get(Constants.TESTCASE_COL_RUNMODE), excel);
		
		driver.get(config.getProperty("qalGlobalAdmin"));
		log.debug("Website Launched : "+config.getProperty("qalGlobalAdmin"));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("loginUsername_XPATH"))));
		type("loginUsername_XPATH", "TSTITM1");
		type("loginPassword_XPATH", "3dplm_123#");
		click("loginBtn_XPATH");
		
		click("manageContentWidget_XPATH");
		driver.switchTo().frame("frame-99E6WaCA234PDJ95W29x");
		type("contentName_XPATH", data.get("ContentName"));
		
		System.out.println("PASSED");
	}	

}
