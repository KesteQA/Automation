package com.TestCases;

import java.lang.reflect.Method;
import java.util.Map;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.TestRunner.BaseTest;
import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.listener.ExtentTestManager;
import com.pages.OpportunitiesPage;


public class OpportunitiesTest extends BaseTest {

	OpportunitiesPage opportunitiesPage;
	
	Logger logger=Logger.getLogger(this.getClass());

	
	@Test
	public void VerifyOppsCreation(Method method){
		BasePage.navigateTabs("Opportunities");
		logger.info("OpportunitiesTest: Navigated to Opportunity page");
		ExtentTestManager.getTest().log(Status.INFO,"Navigated to Opportunity page");
		BasePage.domLoaded();
		opportunitiesPage = new OpportunitiesPage();
		Map<String, String> TestData;
		TestData = BasePage.getTestDataForMethod(method.getName());
		String oppsName = opportunitiesPage.createOpportunity(TestData);
		BasePage.domLoaded();
		logger.info("OpportunitiesTest: Created opportunity record");
		ExtentTestManager.getTest().log(Status.INFO,"Created opportunity record" + oppsName);
		Assert.assertEquals(opportunitiesPage.getOppTitle(), oppsName);
		logger.info("OpportunitiesTest: Verified opportunity creation");
		ExtentTestManager.getTest().log(Status.INFO,"Verified opportunity creation");
	}

	@Test
	public void VerifyOppValidationFail(){
		BasePage.navigateTabs("Opportunities");
		logger.info("OpportunitiesTest: Navigated to Opportunity page");
		ExtentTestManager.getTest().log(Status.INFO,"Navigated to Opportunity page");
		BasePage.domLoaded();
		opportunitiesPage = new OpportunitiesPage();
		opportunitiesPage.createEmptyOpp();
		Assert.assertEquals(opportunitiesPage.getErrorText(), opportunitiesPage.validationError);
		logger.info("OpportunitiesTest: Verified the validation message");
		ExtentTestManager.getTest().log(Status.INFO,"Verified the validation message");
	}

	


	

}
