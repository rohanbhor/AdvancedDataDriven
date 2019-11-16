package com.ds.rough;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ds.utilities.Impersonation;

public class ImersonationTest {
	
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/executables/"+"chromedriver.exe");
		driver= new ChromeDriver();
		
		driver.get("https://widgetfactorymanager.dsone.3ds.com/Impersonation");	
	    Thread.sleep(10000);
		//LoginTest login= new LoginTest();
		//login.doLogin();
		Impersonation imp = new Impersonation();
		imp.impersonateUser(driver,"UE7", "PPD");

	}

}
