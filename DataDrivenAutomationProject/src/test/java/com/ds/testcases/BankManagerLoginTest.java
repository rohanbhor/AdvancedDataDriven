package com.ds.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ds.base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	
	@Test
	public void bankManagerLoginTest() throws IOException{	
			
		//verifyEquals("abc","xyz");
		click("bmlBtn_XPATH");
		log.debug("Clicked on :"+"Bank Manager Login Button");		
		Assert.assertTrue(isElementPresent(By.cssSelector(or.getProperty("addCustBtn_CSS"))),"Login not successful");
		log.debug("Login Successfully executed");
		Reporter.log("<br>Login Successfully executed");
		
		//Assert.fail();
		
	}

}
