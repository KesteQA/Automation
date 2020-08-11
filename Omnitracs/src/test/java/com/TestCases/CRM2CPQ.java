package com.TestCases;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.TestRunner.BaseTest;
import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.listener.ExtentTestManager;
import com.pages.Accounts;
import com.pages.LeadPage;
import com.pages.LoginPage;
import com.pages.OpportunitiesPage;
import com.pages.QuotePage;

public class CRM2CPQ extends BaseTest{
	LeadPage leadPage;
	OpportunitiesPage opportunitiesPage;
	Accounts accountspage;
	LoginPage loginPage;
	QuotePage quotePage;
	Logger logger=Logger.getLogger(this.getClass());
	String competitorAccount; 
	

	@Test
	public void LeadtoQuote() {	
		
		loginPage = new LoginPage();
		loginPage.login("BD");
		ExtentTestManager.getTest().log(Status.INFO,"logged in as BD profile");
		logger.info("Logged in to application");
		BasePage.domLoaded();
		if(BasePage.navigateTabs("Accounts")) {
			logger.info("CRM2CPQ: Navigated to Accounts page");
			}else
			{
				return;
			}
			ExtentTestManager.getTest().log(Status.INFO,"Navigated to Accounts page");
			BasePage.domLoaded();
			if(BasePage.navigateTabs("New")){
				logger.info("CRM2CPQ: Clicked on New Button");
				
				}else
				{
					return;
				}
			ExtentTestManager.getTest().log(Status.INFO,"Clicked on New Button");
			accountspage = new Accounts();
			accountspage.selectAccountType("Competitor");
			ExtentTestManager.getTest().log(Status.INFO,"Selected Competitor Account type");
			competitorAccount = accountspage.createAccount();
			ExtentTestManager.getTest().log(Status.INFO,"Created a Competitor account with name: "+competitorAccount );
			BasePage.domLoaded();
			logger.info("AccountToQuote: Created a new account");
			
		//Clicking on Leads Tab Leads tab
		if(BasePage.navigateTabs("Leads")) {
		logger.info("CRM2CPQ: Navigated to Lead page");
		}else
		{
			return;
		}
		ExtentTestManager.getTest().log(Status.INFO,"Navigated to Lead page");
		driver.navigate().refresh();
		BasePage.domLoaded();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//BasePage.domLoaded();
	    //Clicking on New button to create lead
		if(BasePage.navigateTabs("New")){
			logger.info("CRM2CPQ: Clicked on New Button");
			}else
			{
				return;
			}
		
		ExtentTestManager.getTest().log(Status.INFO,"Clicked on New Button");
		leadPage = new LeadPage();
		String LeadName = leadPage.CreateLeadPage();
		logger.info("CRM2CPQ: Created a new Lead page");
		ExtentTestManager.getTest().log(Status.INFO," Created a new Lead record: "+ LeadName);
		BasePage.domLoaded();
		Assert.assertEquals(leadPage.getLeadName(), LeadName);
		logger.info("CRM2CPQ: Verified the successful lead record creation");
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
	
	    leadPage.UpdateLead(competitorAccount);
	    logger.info("CRM2CPQ: Created Lead  got updated page");
		ExtentTestManager.getTest().log(Status.INFO,"Updated the lead");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		  if(BasePage.navigateTabs("Convert")){
		  logger.info("CRM2CPQ: Clicked on Convert Button");
		  ExtentTestManager.getTest().log(Status.INFO,"Clicked on Convert Button");
		  }else { return; } BasePage.domLoaded(); 
		  
		  try {
				Thread.sleep(6000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
		  if(leadPage.ConvertLead()) { logger.info("CRM2CPQ: Converted the lead");
		  ExtentTestManager.getTest().log(Status.INFO,"Converted the lead");
		  
		  }else { return; }
		  try {
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		  BasePage.domLoaded(); 
		  String Names[]=leadPage.ConvertedAccountContactOpportunityName();
		  logger.info("CRM2CPQ: Verified the successful Account record creation ");
		  ExtentTestManager.getTest().log(Status.INFO," Created a new Account record: "+ Names[0]); 
		  logger.info("CRM2CPQ: Verified the successful Contact record creation");
		  ExtentTestManager.getTest().log(Status.INFO," Created a new contact record: " + Names[1]); 
		  logger.info("CRM2CPQ: Verified the successful Opportunity record creation");
		  ExtentTestManager.getTest().log(Status.INFO," Created a new opportunity record: "+ Names[2]);
		  leadPage.clickConvertedOpportunity();
		  ExtentTestManager.getTest().log(Status.INFO," clicked on converted opportunity");
		  BasePage.domLoaded();
		  try {
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		 opportunitiesPage = new OpportunitiesPage();
		String opportunityName =  opportunitiesPage.getOppTitle();
		Assert.assertTrue(opportunityName.contains(Names[2]));
		  ExtentTestManager.getTest().log(Status.INFO,"verified the opportunity record "+ opportunityName + " is displayed");
	//	opportunitiesPage.updatetask("Prospecting");
	//	  ExtentTestManager.getTest().log(Status.INFO,"updated the tasks in Prospecting tab of the opportunity");
		  loginPage = new LoginPage();
		  loginPage.logout();
		  ExtentTestManager.getTest().log(Status.INFO,"Logged out as BD");
		  loginPage.login("Sales");
		  ExtentTestManager.getTest().log(Status.INFO,"Logged in with Sales profile");
		  BasePage.domLoaded();
		  try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  if(BasePage.globalSearch("Opportunities", opportunityName)>=1)
			{
			  ExtentTestManager.getTest().log(Status.INFO,"search  records are displayed");
				BasePage.selectSearchSuggestion(opportunityName);
				ExtentTestManager.getTest().log(Status.INFO,"clicked on the search record");
			}
			else {
				ExtentTestManager.getTest().log(Status.INFO,"No records are displayed");
			}
			BasePage.domLoaded();
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			opportunitiesPage.createQuote(true, "10");
			ExtentTestManager.getTest().log(Status.INFO,"Quote is created");
			String QuoteNumber =  opportunitiesPage.getquoteTitle();
			//System.out.println(QuoteNumber.substring(14, QuoteNumber.length() - 19));
			String quoteNumber=QuoteNumber.substring(14, QuoteNumber.length() - 19);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(BasePage.globalSearch("Quotes", quoteNumber)>=1)
			{
			  ExtentTestManager.getTest().log(Status.INFO,"search records(quote) are displayed");
				BasePage.selectSearchSuggestion(quoteNumber);
				ExtentTestManager.getTest().log(Status.INFO,"clicked on the quote record");
			}
			else {
				ExtentTestManager.getTest().log(Status.INFO,"No records are displayed");
			}
			BasePage.domLoaded();
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			quotePage = new QuotePage();
			quotePage.EditLines();
			ExtentTestManager.getTest().log(Status.INFO,"Clicked on Edit Lines");
			BasePage.domLoaded();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//BasePage.driver=quotePage.getFrame();
			WebElement containerFrame = driver.findElement(By.xpath("//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@class='oneAlohaPage']//*[contains(@title,'accessibility title')]"));
			driver.switchTo().frame(containerFrame);
			//System.out.println(driver.getTitle());
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			quotePage.addProducts();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			quotePage.changeStatus("Approved");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			quotePage.openOpportunity();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			opportunitiesPage.salesplanout();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.navigate().refresh();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			opportunitiesPage.Closedwon();
	}
	

}
