package com.TestCases;

import java.lang.reflect.Method;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.TestRunner.BaseTest;
import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.listener.ExtentTestManager;
import com.pages.LeadPage;

public class LeadsTest extends BaseTest{
	LeadPage leadPage;
	Logger logger=Logger.getLogger(this.getClass());

	@Test
	public void LeadstoOpptyConversion(Method method) {
		BasePage.domLoaded();
		//Clicking on Leads Tab Leads tab
		if(BasePage.navigateTabs("Leads")) {
		logger.info("EndToEndFlow: Navigated to Lead page");
		}else
		{
			return;
		}
		ExtentTestManager.getTest().log(Status.INFO,"Navigated to Lead page");
		BasePage.domLoaded();
	    //Clicking on New button to create lead
		if(BasePage.navigateTabs("New")){
			logger.info("EndToEndFlow: Clicked on New Button");
			}else
			{
				return;
			}
		ExtentTestManager.getTest().log(Status.INFO,"Clicked on New Button");
		leadPage = new LeadPage();
		String LeadName = leadPage.CreateLeadPage();
		logger.info("EndToEndFlow: Created a new Lead page");
		ExtentTestManager.getTest().log(Status.INFO," Created a new Lead record: "+ LeadName);
		BasePage.domLoaded();
		Assert.assertEquals(leadPage.getLeadName(), LeadName);
		logger.info("EndToEndFlow: Verified the successful lead record creation");
		ExtentTestManager.getTest().log(Status.INFO,"Verified the successful lead record creation");
		BasePage.domLoaded();
		//Clicking on Edit button to update lead
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	        	BasePage.navigateTabs("Edit");
	            //result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        }
	        attempts++;
	    }
	    ExtentTestManager.getTest().log(Status.INFO," Clicked on Edit Button");
	    Map<String, String> TestData;
		TestData = BasePage.getTestDataForMethod(method.getName());
	    leadPage.UpdateLead(TestData);
	    logger.info("EndToEndFlow: Created Lead  got updated page");
		ExtentTestManager.getTest().log(Status.INFO,"Updated the lead");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		  if(BasePage.navigateTabs("Convert")){
		  logger.info("EndToEndFlow: Clicked on Convert Button");
		  ExtentTestManager.getTest().log(Status.INFO,"Clicked on Convert Button");
		  }else { return; } BasePage.domLoaded(); 
		  
		  try {
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
		  if(leadPage.ConvertLead()) { logger.info("EndToEndFlow: Converted the lead");
		  ExtentTestManager.getTest().log(Status.INFO,"Converted the lead");
		  
		  }else { return; }
		  try {
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		  BasePage.domLoaded(); 
		  String Names[]=leadPage.ConvertedAccountContactOpportunityName();
		  logger.info("EndToEndFlow: Verified the successful Account record creation ");
		  ExtentTestManager.getTest().log(Status.INFO," Created a new Account record: "+ Names[0]); 
		  logger.info("EndToEndFlow: Verified the successful Contact record creation");
		  ExtentTestManager.getTest().log(Status.INFO," Created a new contact record: " + Names[1]); 
		  logger.info("EndToEndFlow: Verified the successful Opportunity record creation");
		  ExtentTestManager.getTest().log(Status.INFO," Created a new opportunity record: "+ Names[2]);
		  
}
	
	  @Test 
	  public void VerifyMandatoryValidation(Method method){
	  BasePage.domLoaded(); 
	  //Clicking on Leads Tab Leads tab
	  try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  if(BasePage.navigateTabs("Leads"))
	  {
	  logger.info("EndToEndFlow: Navigated to Lead page"); 
	  }else 
	  { 
		  return;
		  }
	  ExtentTestManager.getTest().log(Status.INFO,"Navigated to Lead page");
	  BasePage.domLoaded(); 
	  try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  //Clicking on New button to create lead
	  if(BasePage.navigateTabs("New")){
	  logger.info("EndToEndFlow: Clicked on New Button"); 
	  }else {
		  return; 
		  }
	  ExtentTestManager.getTest().log(Status.INFO,"Clicked on New Button");
	  leadPage = new LeadPage();  
	  leadPage.createEmptyLead();
	  ExtentTestManager.getTest().log(Status.INFO,"Clicked save button without providing input values in Mandatory Fields");
	  ExtentTestManager.getTest().log(Status.INFO,"Expected error message is : "+ leadPage.validationError );
	  ExtentTestManager.getTest().log(Status.INFO,"Captured the validation message : " + leadPage.getErrorText());
	  Assert.assertEquals(leadPage.getErrorText(), leadPage.validationError);
	  logger.info("EndToEnd: Verified the validation message");
	  ExtentTestManager.getTest().log(Status.INFO,"Verified the validation message");
	  }
	  
	 
}

