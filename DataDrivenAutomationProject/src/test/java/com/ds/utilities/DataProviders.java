package com.ds.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name="getDataSuite1")
	public static Object[][] getDataSuite1(Method m){
		
	String testcase = m.getName();	
	ExcelReader excel = new ExcelReader(Constants.PATH_SUITE1);
		
	return CommonUtils.getData(testcase, excel);
	}
	
	@DataProvider(name="getDataSuite2")
	public static Object[][] getDataSuite2(Method m){
		
	String testcase = m.getName();	
	ExcelReader excel = new ExcelReader(Constants.PATH_SUITE2);
		
	return CommonUtils.getData(testcase, excel);
	}
	
	@DataProvider(name="getDataGlobalAdmin")
	public static Object[][] getDataGlobalAdmin(Method m){
		
	String testcase = m.getName();	
	ExcelReader excel = new ExcelReader(Constants.UNIVERSITY_PATH_SUITE1);
		
	return CommonUtils.getData(testcase, excel);
	}
	
	@DataProvider(name="getDataCertificationAppAdmin")
	public static Object[][] getDataCertificationAppAdmin(Method m){
		
	String testcase = m.getName();	
	ExcelReader excel = new ExcelReader(Constants.UNIVERSITY_PATH_SUITE2);
		
	return CommonUtils.getData(testcase, excel);
	}

}
