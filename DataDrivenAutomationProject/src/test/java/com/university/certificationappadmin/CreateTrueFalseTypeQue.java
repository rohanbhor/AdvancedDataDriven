package com.university.certificationappadmin;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.ds.utilities.CommonUtils;
import com.ds.utilities.Constants;
import com.ds.utilities.DataProviders;
import com.ds.utilities.ExcelReader;



public class CreateTrueFalseTypeQue {
	
	@Test(dataProviderClass=DataProviders.class, dataProvider="getDataCertificationAppAdmin")
	public void createTrueFalseTypeQue(HashMap<String, String>data){
		
		ExcelReader excel = new ExcelReader(Constants.UNIVERSITY_PATH_SUITE2);
		CommonUtils.checkExecution("CertificationAppAdmin", "createTrueFalseTypeQue", data.get(Constants.TESTCASE_COL_RUNMODE), excel);
		
	
	}
	

}
