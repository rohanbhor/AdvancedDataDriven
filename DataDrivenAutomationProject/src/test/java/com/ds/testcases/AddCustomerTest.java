package com.ds.testcases;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.ds.base.TestBase;
import com.ds.utilities.TestUtil;



public class AddCustomerTest extends TestBase{

	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void addCustomerTest(Map<String,String>data){
		
		
		if(!data.get("runmode").equalsIgnoreCase("Y")){
			throw new SkipException("Skipped the test case as runmode is NO");
		}
		
		click("addCustBtn_CSS");
		
		System.out.println("first name is :"+data.get("firstname"));
		type("firstName_XPATH", data.get("firstname"));
		
		System.out.println("last name is :"+data.get("lastname"));
		type("lastName_XPATH",data.get("lastname"));
		
		System.out.println("postcode is :"+data.get("postcode"));
		type("postCode_XPATH",data.get("postcode"));		
		
		click("addCust_XPATH");
		
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		alert.accept();
		log.debug("Alert Validated and Accepted!! :" + data.get("alerttext"));
		Reporter.log("<br>Alert Validated and Accepted!! :" + data.get("alerttext"));
		
		
	}

	
}
