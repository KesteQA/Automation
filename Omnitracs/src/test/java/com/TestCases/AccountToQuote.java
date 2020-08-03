package com.TestCases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.TestRunner.BaseTest;
import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.listener.ExtentTestManager;
import com.pages.Accounts;


public class AccountToQuote extends BaseTest{
	
	Accounts accountsPage;
	Logger logger=Logger.getLogger(this.getClass());

@Test
	public void AccountCreation() {
		BasePage.domLoaded();
		if(BasePage.navigateTabs("Accounts")) {
		logger.info("AccountToQuote: Navigated to Accounts page");
		}else
		{
			return;
		}
		ExtentTestManager.getTest().log(Status.INFO,"Navigated to Accounts page");
		BasePage.domLoaded();
	    //Clicking on New button to create lead
		if(BasePage.navigateTabs("New")){
			logger.info("AccountToQuote: Clicked on New Button");
			}else
			{
				return;
			}
		ExtentTestManager.getTest().log(Status.INFO,"Clicked on New Button");
		accountsPage = new Accounts();
		accountsPage.selectAccountType("Other");
		
		String accountName = accountsPage.createAccount();
		BasePage.domLoaded();
		logger.info("AccountToQuote: Created a new account page");
		//ExtentTestManager.getTest().log(Status.INFO," Created a new account record: "+ accountName);
	//	BasePage.domLoaded();
	//	Assert.assertEquals(accountsPage.getAccountName(), accountName);
	//	logger.info("AccountToQuote: Verified the successful account record creation");
		//ExtentTestManager.getTest().log(Status.INFO,"Verified the successful account record creation");
	}

}
