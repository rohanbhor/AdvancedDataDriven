package com.university.globaladmin;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.ds.utilities.CommonUtils;
import com.ds.utilities.Constants;
import com.ds.utilities.DataProviders;
import com.ds.utilities.ExcelReader;



public class CreateTrainingSession {
	
	@Test(dataProviderClass=DataProviders.class, dataProvider="getDataGlobalAdmin")
	public void createTrainingSession(HashMap<String, String>data){
		
		ExcelReader excel = new ExcelReader(Constants.UNIVERSITY_PATH_SUITE1);
		CommonUtils.checkExecution("GlobalAdmin", "createTrainingSession", data.get(Constants.TESTCASE_COL_RUNMODE), excel);
		
	
	}

}
