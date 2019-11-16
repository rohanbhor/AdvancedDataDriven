package com.ds.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	public static ExtentReports extent;
	
	public static ExtentReports getInstance(){
		if(extent==null){
			String reportpath= System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+"extent.html";
			String reportconfigpath= System.getProperty("user.dir")+"\\src\\test\\resources\\extentconfig\\ReportsConfig.xml";
			
			extent = new ExtentReports(reportpath, true, DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File(reportconfigpath));
		}		
		return extent;		
	}
	

}
