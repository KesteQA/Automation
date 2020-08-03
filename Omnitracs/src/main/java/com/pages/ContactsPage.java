package com.pages;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BasePage;
import com.utlis.TestUtils;

public class ContactsPage extends BasePage{

	public ContactsPage()
	{
		PageFactory.initElements(driver, this);
			
	}
	
	
	@FindBy(xpath="//div[@title='New']")
	WebElement contactNew_bttn;
	
	@FindBy(xpath="//ul[@class='errorsList']/li")
	WebElement contactsValidation_info;  
	
	@FindBy(xpath="//button[@title='Save']")
	WebElement contactSave_bttn;
	
	
	@FindBy(xpath="//span[text()= 'Salutation']/parent::*//following-sibling::div")
	WebElement salutation_txt;
	
	@FindBy(xpath = "//span[contains(text(),'Account Name')]/parent::label//following-sibling::div//input")
	WebElement accName_txt;
	

	@FindBy(xpath= "//lightning-formatted-name")
	WebElement contactName_txt;
	
	public String mandatoryValidationTxt = "These required fields must be completed: Account Name, Primary Email, Last Name";
	
	public void createEmptyContact() 
	{
		click(contactNew_bttn);
		try {
		Thread.sleep(6000);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		click(contactSave_bttn);
	}
	
	public String createContactFporExstingAcc(String accname)
	{
		return accname;
		
	}
	
	public String createContact(Map<String, String> testData)
	{
		click(contactNew_bttn);
		try {
			Thread.sleep(6000);
			}
			catch (Exception e){
				System.out.println(e.getMessage());
			}
		String lastnametestdata = TestUtils.getRandomString(4);
		selectValueFromSalesForceDropdown(salutation_txt, testData.get("Salutation"));
		sendkeys(getInputTextField("First Name"), testData.get("First Name"));
		sendkeys(getInputTextField("Last Name"), lastnametestdata);
		selectValueFromLookUpField(accName_txt, testData.get("AccountName"));
		sendkeys(getInputTextField("Primary Email"), lastnametestdata+"@test.com");
		click(contactSave_bttn);
		return testData.get("Salutation") + " "+ testData.get("First Name") + " " + lastnametestdata;
	}
	
	public String getContactName()
	{
		return getText(contactName_txt);
	}
	
	public String getErrorText()
	{
		return getText(contactsValidation_info);
	}
	
	
	
}
