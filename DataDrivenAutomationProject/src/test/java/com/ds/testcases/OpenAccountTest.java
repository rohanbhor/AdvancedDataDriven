package com.ds.testcases;

import java.util.HashMap;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ds.base.TestBase;
import com.ds.utilities.TestUtil;

public class OpenAccountTest extends TestBase{

	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void openAccountTest(HashMap<String, String>data){
		
			click("openAccount_XPATH");
			select("customer_XPATH",data.get("customer"));
			select("currency_XPATH",data.get("currency"));
			click("process_XPATH");
			
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
	}
	
	
	
	
	
	
	
	
	
	
	
}
