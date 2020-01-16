package com.university.certificationappadmin;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ds.base.TestBase;
import com.ds.utilities.CommonUtils;
import com.ds.utilities.Constants;
import com.ds.utilities.DataProviders;
import com.ds.utilities.ExcelReader;




public class CreateOnlineExam extends TestBase {
	
	@BeforeClass
	public void setUp(){
		
		System.out.println("Test Started");
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
		type("searchDashboard_XPATH", "3DXU - Certification App Admin");
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("selectCertificationAppAdminDashboard_XPATH"))));
		click("selectCertificationAppAdminDashboard_XPATH");
		//click("manageContentWidget_XPATH");
		
	}
	
	
	@Test(dataProviderClass=DataProviders.class, dataProvider="getDataCertificationAppAdmin")
	public void createOnlineExam(HashMap<String, String>data){
		
		ExcelReader excel = new ExcelReader(Constants.UNIVERSITY_PATH_SUITE2);
		CommonUtils.checkExecution("CertificationAppAdmin", "createOnlineExam", data.get(Constants.TESTCASE_COL_RUNMODE), excel);
		
		System.out.println("Passed");
		
	}

}
