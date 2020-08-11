package com.TestCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.TestRunner.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.listener.ExtentTestManager;
import com.pages.Accounts;
import com.pages.ContactsPage;
import com.pages.LeadPage;
import com.pages.LegalAgreements;
import com.pages.LoginPage;
import com.pages.OpportunitiesPage;
import com.pages.QuotePage;

public class CRM2CLM extends BaseTest {
	LeadPage leadPage;
	OpportunitiesPage opportunitiesPage;
	Accounts accountspage;
	LoginPage loginPage;
	QuotePage quotePage;
	Logger logger = Logger.getLogger(this.getClass());
	String competitorAccount;
	ContactsPage contactPage;
LegalAgreements legalAgreements;

	@Test
	public void AcctoLegalAgreement() {	
		String CAMember = "Lauren Legal" ;
		String SalesRepMember = "Steven CPQ Sales";
		legalAgreements = new LegalAgreements();
		opportunitiesPage = new OpportunitiesPage();
	ExtentTestManager.getTest().log(Status.PASS, "Logged in with Sales profile");
		loginPage = new LoginPage();
		loginPage.login("Sales");
		logger.info("Logged in to application");
		BasePage.domLoaded();
		if(BasePage.navigateTabs("Accounts")) {
			logger.info("CRM2CLM: Navigated to Accounts page");
			ExtentTestManager.getTest().log(Status.PASS, "Navigated to Accounts page");
		}else
		{
			return;
		}
		BasePage.domLoaded();
		if(BasePage.navigateTabs("New")){
			logger.info("CRM2CLM: Clicked on New Button");
			ExtentTestManager.getTest().log(Status.INFO,"Clicked on New Button");

		}else
		{
			return;
		}
		accountspage = new Accounts();
		String accountName =accountspage.createAccountWithType("Partner");
		Assert.assertTrue(BasePage.getSuccessText().contains("was created"), "Account creation failed and success text did not display");
		ExtentTestManager.getTest().log(Status.PASS,"created a Partner account with name:  "+accountName );

		BasePage.domLoaded();

		BasePage.clickRelatedList("Account Team");
		ExtentTestManager.getTest().log(Status.INFO, "Adding account team members to the partner account");
		accountspage.addTeamMeber("Contracts Administrator", CAMember);
		//BasePage.getSuccessText();
		Assert.assertTrue(BasePage.getSuccessText().contains("1 record was updated"), "Failed to add the account team member" + BasePage.getSuccessText());
		ExtentTestManager.getTest().log(Status.PASS, "Added Contract Admin " + CAMember +" to the account");

		BasePage.domLoaded();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		accountspage.addTeamMeber("Sales Rep", SalesRepMember);
		//BasePage.getSuccessText();
		Assert.assertTrue(BasePage.getSuccessText().contains("1 Account Team Member record was updated"), "Failed to add the account team member");
		ExtentTestManager.getTest().log(Status.PASS, "Added sales rep " + SalesRepMember  +" to the account");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BasePage.toObjFromRelatedList(accountName);
		
		BasePage.domLoaded();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentTestManager.getTest().log(Status.INFO, "Adding Address to the partner account");
		BasePage.clickRelatedList("Addresses");

		driver.navigate().refresh();
		ExtentTestManager.getTest().log(Status.INFO, "Clicked on addresses related list");
		BasePage.domLoaded();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		accountspage.addAccountAddress("Shipping Address");
		
	Assert.assertTrue(BasePage.getSuccessText().contains("was created"), "Failed to add the shipping address");
	ExtentTestManager.getTest().log(Status.PASS,"Shipping Address added to the account");	
	logger.info("Shipping Address added to the account");
	accountspage.closeSuccessBanner();
	BasePage.domLoaded();
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		accountspage.addAccountAddress("Corporate HQ");
		Assert.assertTrue(BasePage.getSuccessText().contains("was created"), "Failed to add the Corporate HQ address");
		ExtentTestManager.getTest().log(Status.PASS,"Corporate HQ added to the account");
		logger.info("Corporate HQ added to the account");
		accountspage.closeSuccessBanner();
		BasePage.domLoaded();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		accountspage.addAccountAddress("Billing Address");
		Assert.assertTrue(BasePage.getSuccessText().contains("was created"), "Failed to add the Billing address");
		ExtentTestManager.getTest().log(Status.PASS,"Billing Address added to the account");
		accountspage.closeSuccessBanner();
		logger.info("Billing Address  added to the account");
		
		BasePage.toObjFromRelatedList(accountName);
		
		BasePage.clickRelatedList("Related Contacts");
		ExtentTestManager.getTest().log(Status.INFO,"Adding contact to the partner account");
		
		BasePage.domLoaded();
		accountspage.clickNewContact();
		 contactPage = new ContactsPage();
		 contactPage.selectContactType("Internal");
		 String contactName = contactPage.createContactWithAccPopulated();
		 accountspage.closeSuccessBanner();
		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 if(BasePage.globalSearch("Contacts", contactName)>=1)
			{
			  ExtentTestManager.getTest().log(Status.INFO,"searched Contacts records is displayed");
				BasePage.selectSearchSuggestion(contactName);
				ExtentTestManager.getTest().log(Status.INFO,"clicked on the search record");
			}
			else {
				ExtentTestManager.getTest().log(Status.INFO,"No records are displayed");
			}
			BasePage.domLoaded();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BasePage.domLoaded();
			driver.navigate().refresh();
			ExtentTestManager.getTest().log(Status.INFO,"clicked on Opportunities related list");
			contactPage.clickOppRelatedList();
			//BasePage.clickRelatedList("Opportunities");
				BasePage.domLoaded();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(BasePage.navigateTabs("New")){
					logger.info("Regression: Clicked on New Button");
					ExtentTestManager.getTest().log(Status.INFO,"clicked on new Opportunity button");
				}else
				{
					return;
				}
		 //BasePage.clickHyperLinkObject(contactName);
			opportunitiesPage.selectOppType("New Contract");
			opportunitiesPage.createNewContractOPP("New Customer", "New Logo");
		//	BasePage.getSuccessText();
			Assert.assertTrue(BasePage.getSuccessText().contains("was created"), "Failed to create the opportunity record");
			
			String oppname = accountName+ " - "+ "New Contract";
			//System.out.println(oppname);
			ExtentTestManager.getTest().log(Status.PASS,"Created the Opportunity : "+ oppname);
			BasePage.clickHyperLinkObject(oppname);
			accountspage.closeSuccessBanner();
			BasePage.domLoaded();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			ExtentTestManager.getTest().log(Status.INFO,"Navigated to the Opportunity : "+ oppname);
			opportunitiesPage.salesplanout();
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ExtentTestManager.getTest().log(Status.INFO,"Creating an quote for the opportunity");
		opportunitiesPage.createQuote(true, "10");
		Assert.assertTrue(BasePage.getSuccessText().contains("was created"), "Failed to create the Quote record");

		String quoteSucessmessage =  BasePage.getSuccessText();
		String quoteNumber=quoteSucessmessage.substring(14, quoteSucessmessage.length() - 19);
		ExtentTestManager.getTest().log(Status.PASS,"Created an quote for the opportunity: "+ quoteNumber);
		accountspage.closeSuccessBanner();
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
		ExtentTestManager.getTest().log(Status.PASS,"Added products to the quote");
		quotePage.changeStatus("Approved");
		ExtentTestManager.getTest().log(Status.PASS,"Marked quote status as Approved");
		BasePage.domLoaded();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		quotePage.clickGeneratDocument();
		ExtentTestManager.getTest().log(Status.INFO,"Clicked on generate documents option");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		quotePage.selectDocumentType("Linked Contracts", "English");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		quotePage.clickGeneratbttn();
		ExtentTestManager.getTest().log(Status.INFO,"Clicked on generate Button");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(legalAgreements.getAgreementType(), "Header", "The agreement type mismatch, actual agreement type is :"+legalAgreements.getAgreementType());
		ExtentTestManager.getTest().log(Status.PASS,"The header legal agreement record is displayed");
		legalAgreements.clickPrimaryQuote();
		ExtentTestManager.getTest().log(Status.INFO,"Clicked on primary quote");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ExtentTestManager.getTest().log(Status.INFO,"Clicked on Opportunity of the quote");
		quotePage.openOpportunity();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(opportunitiesPage.getOppStatus(), "Negotiation", "The Opportunity status mismatch, actual status is :"+opportunitiesPage.getOppStatus());
		ExtentTestManager.getTest().log(Status.PASS,"The Opportunity status is updated to Negotiation");	
	}
	}

