package com.ds.rough;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ds.utilities.ExcelReader;

public class TestNG_DataProvider {
	
	public static ExcelReader excel = null;
	
	
	@Test(dataProvider="getData")
	public void testDatat(Hashtable<String,String>data){
		System.out.println(data.get("firstname"));
	}
	
	
	@DataProvider
	public static Object[][] getData(){
		
		if (excel == null){
			
			//excel = new ExcelReader(System.getProperty("user.dir")+"/src/test/resources/excel/testdata.xlsx");
			excel = new ExcelReader("D:\\Learning_2\\AdvancedDataDriven\\src\\test\\resources\\excel\\testdataexcel.xlsx");
		}
		
		String sheetName = "AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][1];
		
		Hashtable<String, String> table =null;
				
		for(int rowNum=2; rowNum<=rows; rowNum++){
			
			table = new Hashtable<String, String>(); // this will generate new table every time column loop ends
			
			for(int colNum=0; colNum<cols; colNum++){
				
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][0] = table;			
				
				/*
				 * Here column is kept 0 in data because the table will be generated as this :
				 * data[0][0] = {(username, Rohan),(password, abcd), (is_correct, Y)} // first bunch of hash table
				 * 
				 * data[1][0] = {(username, Sumitra),(password, xyz),(is_correct, Y)} // second bunch of hash table
				 * 
				 */				 				
			}			
		}
		
		return data;
		
		
		
	}

}
