package com.ds.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.ds.base.TestBase;

public class Impersonation extends TestBase{
	
	
	@Test
	public void impersonateUser(WebDriver driver, String user, String env) throws InterruptedException{
	
		this.driver = driver;
		user="UE7";
		env="PPD";
		if (env.equalsIgnoreCase("PPD")){
			if (isElementPresent(By.xpath(config.getProperty("removeImpersonation_XPATH")))){
				click("removeImpersonation_XPATH");
				Thread.sleep(4000);
				driver.navigate().refresh();
				type("ppdImpersonate_XPATH",user);
				click("reomovePpdImpersonate_XPATH");
			}		
		}
		
	}
	
}
	


