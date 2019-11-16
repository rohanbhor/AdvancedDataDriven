package com.ds.utilities;

import java.util.Date;

public class TestConfig{
	
	static Date d = new Date();
	static String stamp = d.toString();

	public static String server="smtp.gmail.com";
	public static String from = "rohan.tester2009@gmail.com";
	public static String password = "test@123#";
	public static String[] to ={"rohan.bhor2009@gmail.com","rohan.bhor@3ds.com"};
	public static String subject = "Test Report"+" "+ stamp;
	public static String messageBody ="TestMessage";
	public static String attachmentPath=System.getProperty("user.dir")+"//TestFinished.jpg";
	public static String attachmentName="TestFinished.jpg";
		
}
