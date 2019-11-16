package com.ds.rough;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.ds.base.TestBase;

public class CaptureScreenshot extends TestBase{
	
	
	Calendar cal = new GregorianCalendar();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	int day = cal.get(Calendar.DATE);

	int hour = cal.get(Calendar.HOUR);
	int minute = cal.get(Calendar.MINUTE);
	int seconds = cal.get(Calendar.SECOND);
	
	File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
	//String filePath= System.getProperty("user.dir")+"//screenshot//"+year+month+day+ 
	
	
}
