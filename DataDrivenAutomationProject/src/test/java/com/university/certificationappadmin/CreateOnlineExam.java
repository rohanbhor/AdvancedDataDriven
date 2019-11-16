package com.university.certificationappadmin;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.ds.utilities.CommonUtils;
import com.ds.utilities.Constants;
import com.ds.utilities.DataProviders;
import com.ds.utilities.ExcelReader;



public class CreateOnlineExam {

	@Test(dataProviderClass=DataProviders.class, dataProvider="getDataCertificationAppAdmin")
	public void createOnlineExam(HashMap<String, String>data){
		
		ExcelReader excel = new ExcelReader(Constants.UNIVERSITY_PATH_SUITE2);
		CommonUtils.checkExecution("CertificationAppAdmin", "createOnlineExam", data.get(Constants.TESTCASE_COL_RUNMODE), excel);
		
	
	}

}
