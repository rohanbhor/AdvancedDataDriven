package com.ds.utilities;

import java.util.HashMap;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;

public class CommonUtils {
	
	public static boolean isTestExecutable(String testCaseName, ExcelReader excel){
		String sheetName = Constants.TESTCASE_SHEET;
		
		int rows= excel.getRowCount(sheetName);
		for (int row = 2; row <= rows; row++) {

			String testcase = excel.getCellData(sheetName,Constants.TESTCASE_COL_TESTCASENAME, row);

			if (testcase.equalsIgnoreCase(testCaseName)) {
				String runMode = excel.getCellData(sheetName,Constants.TESTCASE_COL_RUNMODE, row);

				if (runMode.equalsIgnoreCase(Constants.RUNMODE_YES)) {
					return true;
				} else {
					return false;
				}
			}
			
		}
		return false;
	}
	
	

	public static boolean isSuiteExecutable(String suitename) {
		ExcelReader excel = new ExcelReader(Constants.UNIVERSITY_TESTSUITE);
		String sheetName = Constants.TESTSUITE_SHEET;

		int rows = excel.getRowCount(sheetName);
		
		for (int row = 2; row <= rows; row++) {

			String testSuiteName = excel.getCellData(sheetName,Constants.TESTSUITE_COL_SUITENAME, row);

			if (testSuiteName.equalsIgnoreCase(suitename)) {
				String runMode = excel.getCellData(sheetName,Constants.TESTSUITE_COL_RUNMODE, row);

				if (runMode.equalsIgnoreCase(Constants.RUNMODE_YES)) {
					return true;
				} else {
					return false;
				}
			}
			
		}
		return false;
		
	}
	
	
	public static void checkExecution(String testSuiteName, String testCaseName, String dataRunMode, ExcelReader excel){
		
		if(!isSuiteExecutable(testSuiteName)){
			
			throw new SkipException("Skipping the test: "+testCaseName+" as the run mode of Test Suite "+testSuiteName+" is set NO");
		}
		
		if(!isTestExecutable(testCaseName, excel)){
			
			throw new SkipException("Skipping the test: "+testCaseName+" as the run mode is set NO");
		}
		
		if(dataRunMode.equalsIgnoreCase(Constants.RUNMODE_NO)){
			
			throw new SkipException("Skipping the test as the run mode of test data is set NO");
		}
		
		
	}

	@DataProvider
	public static Object[][] getData(String testcase, ExcelReader excel) {

		String sheetName = "TestData";
		String testCase = testcase;
		//
		System.out.println("Testcase Name is : " + testCase);

		// Testcase starts from :
		int testCaseRowNum = 1;
		while (!excel.getCellData(sheetName, 0, testCaseRowNum)
				.equalsIgnoreCase(testCase)) {
			testCaseRowNum++;
		}
		System.out.println("Test Starts from: " + testCaseRowNum);

		// Find total rows and cols in the testcase
		int colsStartRowNum = testCaseRowNum + 1; // (row of Iteration,
													// TestData, Browser)
		int dataStartRowNum = colsStartRowNum + 1; // ( row of testdata)

		// Total cols in the test case
		int cols = 0;
		// following loop will run till the time it wont found the blank.
		while (!excel.getCellData(sheetName, cols, colsStartRowNum).trim()
				.equalsIgnoreCase("")) {
			cols++;
		}

		System.out.println("Total data cols in the TestCase are: " + cols);

		int rows = 0;
		// Total rows in the testcase :
		while (!excel.getCellData(sheetName, 0, dataStartRowNum + rows).trim()
				.equalsIgnoreCase("")) {
			rows++;
		}
		System.out.println("Total data rows in the TestCase are: " + rows);

		int i = 0;

		Object[][] data = new Object[rows][1];

		HashMap<String, String> table = null;
		for (int row = dataStartRowNum; row < dataStartRowNum + rows; row++) {
			table = new HashMap<String, String>();
			for (int col = 0; col < cols; col++) {
				// data[row-dataStartRowNum][col] = excel.getCellData(sheetName,
				// col, row);

				table.put(excel.getCellData(sheetName, col, colsStartRowNum),
						excel.getCellData(sheetName, col, row));
				data[i][0] = table;
			}
			i++;
		}
		return data;
	}

}
