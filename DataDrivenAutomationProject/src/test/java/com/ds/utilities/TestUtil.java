package com.ds.utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.ds.base.TestBase;

public class TestUtil extends TestBase {

	public static String screenshotpath1;
	public static String screenshotpath2;
	public static String screenshotname1;
	public static String screenshotname2;

	public static void captureScreenshot() throws IOException {

		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);

		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);

		try {

			screenshotname1 = year + "_" + date + "_" + (month + 1) + "_" + day
					+ "_" + min + "_" + sec + ".jpg";
			screenshotname2 = timeStamp() + ".jpg";

			screenshotpath1 = System.getProperty("user.dir")
					+ "\\target\\surefire-reports\\html\\" + screenshotname1;
			screenshotpath2 = System.getProperty("user.dir")
					+ "\\target\\surefire-reports\\html\\" + screenshotname2;

			FileUtils.copyFile(scrFile, new File(screenshotpath2));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String timeStamp() {
		Date d = new Date();
		String stamp = d.toString();
		String datestamp = stamp.replace(":", "_").replace(" ", "_");
		return datestamp;
	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		if (excel == null) {
			excel = new ExcelReader(System.getProperty("user.dir")
					+ "/src/test/resources/excel/testdata.xlsx");
			System.out.println("Excel is set null");
		}

		String sheetName = m.getName();
		System.out.println("Sheet Name is :" + sheetName);
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		System.out.println("Row count is :" + rows);
		System.out.println("Col count is :" + cols);

		Object[][] data = new Object[rows - 1][1];

		HashMap<String, String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new HashMap<String, String>();

			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1),
						excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}
		System.out.println("Data entries in the table: " + data.length);
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i][0]);
		}

		return data;

	}

	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName = "test_suite";
		int rows = excel.getRowCount(sheetName);
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			String testCase = excel.getCellData(sheetName, "tcid", rowNum);
			if (testCase.equalsIgnoreCase(testName)) {
				String runMode = excel.getCellData(sheetName, "runmode", rowNum);
				if (runMode.equalsIgnoreCase("Y")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}


}