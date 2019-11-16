package com.ds.rough;



import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.ds.utilities.MonitoringMail;
import com.ds.utilities.TestConfig;

public class TestHostAddr {

	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {
		// TODO Auto-generated method stub
		MonitoringMail mail= new MonitoringMail();
		String messageBody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/MasterDataDriven/Extent_20Report/";
		
		//To get local host address
		System.out.println("Localhost name: "+messageBody);
		
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
		
		

	}

}
