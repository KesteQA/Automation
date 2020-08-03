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
import com.pages.ContactsPage;


public class ContactsTest extends BaseTest{
	
	ContactsPage contactsPage;
	Logger logger=Logger.getLogger(this.getClass());

	@Test
	public void VerifyContactsCreation(Method method){
		BasePage.navigateTabs("Contacts");
		logger.info("ContactsTest: Navigated to contacts page");
		ExtentTestManager.getTest().log(Status.INFO,"Navigated to contacts page");
		BasePage.domLoaded();
		contactsPage = new ContactsPage();
		Map<String, String> TestData;
		TestData = BasePage.getTestDataForMethod(method.getName());
		String contactName = contactsPage.createContact(TestData);
		//contactsPage.createContact(TestData.get("Accounanem"), testdate.get(""));
		logger.info("ContactsTest: Created a new contact record");
		ExtentTestManager.getTest().log(Status.INFO," Created a new contact record: "+ contactName);
		BasePage.domLoaded();
		Assert.assertEquals(contactsPage.getContactName(), contactName);
		logger.info("ContactsTest: Verified the successfuul contact record creation");
		ExtentTestManager.getTest().log(Status.INFO,"Verified the successfuul contact record creation");
	}
	
	@Test
	public void VerifyContactsValidationSkip(){
		BasePage.navigateTabs("Contacts");
		logger.info("ContactsTest: Navigated to contacts page");
		ExtentTestManager.getTest().log(Status.INFO,"Navigated to contacts page");
		BasePage.domLoaded();
		contactsPage = new ContactsPage();
		contactsPage.createEmptyContact();
		Assert.assertEquals(contactsPage.getErrorText(), contactsPage.mandatoryValidationTxt);
		ExtentTestManager.getTest().log(Status.INFO,"Verified the validation message");
		logger.info("ContactsTest: Verified the validation message");
	}

}
