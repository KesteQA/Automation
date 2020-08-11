package com.TestRunner;


import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.listener.ExtentTestManager;
import com.pages.LoginPage;
import com.utlis.ReadPropertyFile;
import com.utlis.SendEmail;



public class BaseTest extends BasePage{
	
	LoginPage loginPage;
	
	Logger logger=Logger.getLogger(this.getClass());
	
	@BeforeMethod
	public void setUp() {
		startBrowser();
		BasePage.domLoaded();
	}
	
	


	@AfterMethod
	
	 public void wrapUp() { 
		//driver.close(); logger.info("Driver is closed.");
	//  ExtentTestManager.getTest().log(Status.INFO,"Closed the browser"); 
	  }
	 
	
	@AfterSuite
	public void afterSuite() {
		try {
			SendEmail.sendEmailWithResults();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

 }
