package com.pages;

import org.openqa.selenium.By;
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
	

	@FindBy(xpath = "//div[text()='Add Team Members']")
	WebElement addTeamMember_bttn;
	
	@FindBy(xpath = "//div[contains(@class,'modal-container ')]//table/tbody/tr/th/span")
	WebElement teamMemberUser_txt;
	
	@FindBy(xpath = "//input[@placeholder = 'Search People...']")
	WebElement teamMemberUserSearch_txt;
	
	@FindBy(xpath = "//div[contains(@class,'modal-container ')]//table/tbody/tr/td[2]")
	WebElement teamMemberRole_txt;
	
	@FindBy(xpath ="//span[text()='Legal Name']/parent::div/following-sibling::div/span")
	WebElement legalNameValue_txt;
	
	@FindBy(xpath ="//a[@title='New']")
	WebElement addAddress_bttn;
	
	@FindBy(xpath ="//span[text()='Close']")
	WebElement successMsgClose_icon;
	
	
	@FindBy(xpath ="//div[text()='New Contact']")
	WebElement newContact_bttn;
	
	
	public void selectAccountType(String accType)
	{
		selectObjectType(accType);
		Next_bttn.click();
	}
	
	
	public void clickNewContact()
	{
		explicitlyWait(newContact_bttn);
		javascriptClick(newContact_bttn);
	}
	
	public String createAccountWithType(String accType)
	{
		String accountName = null;
		selectAccountType(accType);
		if(accType.equalsIgnoreCase("Partner"))
		{
			accountName = createPatnerAccount();
		}
		else if(accType.equalsIgnoreCase("Competitor"))
		{
			accountName = createCompetitorAccount();
		}
		return accountName; 
	}
	public String createPatnerAccount()
	{
		
		String accountName = TestUtils.getRandomString(5);
		String phonenumberdata= "2"+TestUtils.getRandomNumber(9);
		String size= TestUtils.getRandomNumber(2);
		sendkeys(AccountName_txt, accountName);
		sendkeys(getInputTextField("FMCSA Fleet Size"), size);
		sendkeys(getInputTextField("Phone"), phonenumberdata);
		selectValueFromDropDown(false, "Type", "Prospect");
		//selectRandomFromDropDown(false, "Type");
		selectValueFromDropDown(false, "Segment", "DCC");
		selectValueFromDropDown(false, "Region", "Domestic");
		selectValueFromDropDown(false, "Market", "Channel");
		selectValueFromDropDown(false, "Billing Country", "United States");
	   sendkeys(BillingStreet_textBox, TestUtils.getRandomString(10));
		sendkeys(getInputTextField("Billing City"), TestUtils.getRandomString(6));
		sendkeys(getInputTextField("Billing Zip/Postal Code"), TestUtils.getRandomNumber(4));
		selectValueFromDropDown(false, "Billing State/Province", "Alabama");
		click(Save_bttn);
		return accountName;
	}
	public String createCompetitorAccount()
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
	  // sendkeys(BillingStreet_textBox, TestUtils.getRandomString(10));
		//sendkeys(getInputTextField("Billing City"), TestUtils.getRandomString(6));
		//sendkeys(getInputTextField("Billing Zip/Postal Code"), TestUtils.getRandomNumber(4));
		//selectValueFromDropDown(false, "Billing State/Province", "Alabama");
		click(Save_bttn);
		return accountName;
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
	
	public void addTeamMeber(String role , String value)
	{
		click(addTeamMember_bttn);
		click(teamMemberUser_txt);
		selectValueFromLookUpField(teamMemberUserSearch_txt, value);
		click(teamMemberRole_txt);
		selectValueFromSalesForceDropdown(teamMemberRole_txt, role);
		click(Save_bttn);
	}
	
public void addAccountAddress(String AddressTypeValue)
{
	explicitlyWait(addAddress_bttn);
	javascriptClick(addAddress_bttn);
	System.out.println("clicked on new");
	selectValueFromDropDown(false, "Address Type", AddressTypeValue);
	sendkeys(getInputTextField("Address Line 1"), TestUtils.getRandomString(5));
	sendkeys(getInputTextField("City"), TestUtils.getRandomString(4));
	sendkeys(getInputTextField("State/Province"), TestUtils.getRandomString(4));
	sendkeys(getInputTextField("Zip/Postal Code"), TestUtils.getRandomNumber(4));
	click(Save_bttn);
}

public void closeSuccessBanner() {
	javascriptClick(successMsgClose_icon);
}


}
