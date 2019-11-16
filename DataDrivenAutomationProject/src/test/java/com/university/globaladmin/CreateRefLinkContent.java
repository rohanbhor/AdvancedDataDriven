package com.university.globaladmin;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.ds.base.TestBase;
import com.ds.utilities.CommonUtils;
import com.ds.utilities.Constants;
import com.ds.utilities.DataProviders;
import com.ds.utilities.ExcelReader;



public class CreateRefLinkContent extends TestBase {

	@Test(dataProviderClass=DataProviders.class, dataProvider="getDataGlobalAdmin")
	public void createRefLinkContent(HashMap<String, String>data){
		
		ExcelReader excel = new ExcelReader(Constants.UNIVERSITY_PATH_SUITE1);
		CommonUtils.checkExecution("GlobalAdmin", "createRefLinkContent", data.get(Constants.TESTCASE_COL_RUNMODE), excel);
		
	
	}	
}
