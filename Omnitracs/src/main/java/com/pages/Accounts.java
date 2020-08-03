package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BasePage;
import com.utlis.TestUtils;

public class Accounts extends BasePage{

	public Accounts()
	{
		PageFactory.initElements(driver, this);
			
	}
	
	@FindBy(xpath="//button[@title='Save']")
	WebElement Save_bttn;
	
	@FindBy(xpath="//div[@class='inlineFooter']/div/button/span[text()='Next']")
	WebElement Next_bttn;
	
	@FindBy(xpath="//textarea[@placeholder='Billing Street']")
	WebElement BillingStreet_textBox;
	
	@FindBy(xpath="//input[@placeholder= 'Search this list...']")
	WebElement search_text;
	
	
	@FindBy(xpath= "//h2[contains(text(), 'New Account:')]/parent::article/parent::div")
	WebElement NewAccountPopUp;
	
	
	@FindBy(xpath = "//div//span[text()='*']//preceding-sibling::span[text()='Account Name']//parent::label//following-sibling::div//input")
	WebElement AccountName_txt;
	
	public void selectAccountType(String accType)
	{
		selectObjectType(accType);
		Next_bttn.click();
	}
	
	
	public String createAccount()
	{
		
		String accountName = TestUtils.getRandomString(5);
		String phonenumberdata= "2"+TestUtils.getRandomNumber(9);
		String size= TestUtils.getRandomNumber(2);
		sendkeys(AccountName_txt, accountName);
		//sendkeys(getInputTextField("FMCSA Fleet Size"), size);
		sendkeys(getInputTextField("Phone"), phonenumberdata);
		selectRandomFromDropDown(false, "Type");
		selectValueFromDropDown(false, "Segment", "DCC");
		selectValueFromDropDown(false, "Region", "Domestic");
		selectValueFromDropDown(false, "Market", "Channel");
		//selectValueFromDropDown(false, "Billing Country", "United States");
	   //sendkeys(BillingStreet_textBox, TestUtils.getRandomString(10));
		//sendkeys(getInputTextField("Billing City"), TestUtils.getRandomString(6));
		//sendkeys(getInputTextField("Billing Zip/Postal Code"), TestUtils.getRandomNumber(4));
		//selectValueFromDropDown(false, "Billing State/Province", "Alabama");
		click(Save_bttn);
		return accountName;
	}
	
	public void searchforRecord(String tabname, String record)
	{
		navigateTabs(tabname);
		sendkeys(search_text, record);
	}
	
	public String getAccountName()
	{
		return null ;
		
	}
}
