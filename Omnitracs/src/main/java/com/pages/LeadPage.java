package com.pages;

import java.util.Map;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BasePage;
import com.utlis.TestUtils;

public class LeadPage extends BasePage{
	
	public LeadPage()
	{
		PageFactory.initElements(driver, this);		
	}
	@FindBy(xpath="//button[@title='Save']")
	WebElement LeadSave_bttn;
	@FindBy(xpath= "//lightning-formatted-name")
	WebElement leadName_txt;
	@FindBy(xpath= "//span[contains(text(),'Street')]/parent::label//following-sibling::textarea")
	WebElement street_txt;
	@FindBy(xpath= "//span[text()='Choose Existing']/ancestor::span")
	WebElement ChooseExisting_txt;
	@FindBy(xpath="//span[text()='Convert']")
	WebElement ConvertSave_bttn;
	@FindBy(xpath="//div[text()='Account']/parent::div/div[@class='bodyConvertedItem']/div[@class='primaryField truncate']/a")
	WebElement AccountOnConvert_link;
	@FindBy(xpath="//div[text()='Contact']/parent::div/div[@class='bodyConvertedItem']/div[@class='primaryField truncate']/a")
	WebElement ContactOnConvert_link;
	@FindBy(xpath="//div[text()='Opportunity']/parent::div/div[@class='bodyConvertedItem']/div[@class='primaryField truncate']/a")
	WebElement OpportunityOnConvert_link;
	
	@FindBy(xpath="//ul[@class='errorsList']/li")
	WebElement LeadValidation_info; 
	
	public void clickConvertedOpportunity()
	{
		click(OpportunityOnConvert_link);
	}
	public String validationError = "failing on purpose";
	public String CreateLeadPage()
	{
		String nametestdata = TestUtils.getRandomString(4);
		String phonenumberdata= "3"+TestUtils.getRandomNumber(9);
		String size= TestUtils.getRandomNumber(2);
		
		sendkeys(getInputTextField("Last Name"), nametestdata);
		sendkeys(getInputTextField("First Name"), nametestdata);
		sendkeys(getInputTextField("Company"), nametestdata);
		sendkeys(getInputTextField("Phone"), phonenumberdata);
		sendkeys(getInputTextField("Email"), nametestdata+"@test.com");
		sendkeys(getInputTextField("FMCSA Fleet Size"), "30");
		click(LeadSave_bttn);
		return nametestdata +" "+ nametestdata;	
	}
	

	
	public String getLeadName()
	{
		return getText(leadName_txt);
	}
	public void UpdateLead(Map<String, String> testData)
	{
		String nametestdata = TestUtils.getRandomString(3);
		String zipdata= TestUtils.getRandomNumber(5);
		String size= TestUtils.getRandomNumber(2);
		sendkeys(getInputTextField("SIC Code"), size);
		sendkeys(getInputTextField("NAICS Code"), size);
		selectValueFromDropdown("Segment",testData.get("Segment"));
		selectValueFromSalesForceLookUpField("Competitor",testData.get("Competitor"));
		selectValueFromDropdown("Market", testData.get("Market"));
		street_txt.click();
		street_txt.sendKeys(nametestdata);
		sendkeys(getInputTextField("City"), nametestdata+"abc");
		selectValueFromDropdown("State/Province", testData.get("State/Province"));
		sendkeys(getInputTextField("Zip/Postal Code"), zipdata);
		selectValueFromDropdown("Lead Source", testData.get("Lead Source"));
		sendkeys(getInputTextField("Website"),"www."+nametestdata+".com");
		click(LeadSave_bttn);
	}
	
	public void UpdateLead(String competitorAccount)
	{
		String nametestdata = TestUtils.getRandomString(3);
		String zipdata= TestUtils.getRandomNumber(5);
		String size= TestUtils.getRandomNumber(2);
		sendkeys(getInputTextField("SIC Code"), size);
		sendkeys(getInputTextField("NAICS Code"), size);
		selectValueFromDropdown("Segment","DCC");
		selectValueFromDropDown(true, "Competitor",competitorAccount);
		selectValueFromDropdown("Market", "Channel");
		street_txt.click();
		street_txt.sendKeys(nametestdata);
		sendkeys(getInputTextField("City"), nametestdata+"abc");
		//selectRandomFromDropDown(false, "State/Province");
		selectValueFromDropdown("State/Province", "Alaska");
		sendkeys(getInputTextField("Zip/Postal Code"), zipdata);
		//selectRandomFromDropDown(false, "Lead Source");
		selectValueFromDropdown("Lead Source", "Direct");
		sendkeys(getInputTextField("Website"),"www."+nametestdata+".com");
		click(LeadSave_bttn);
	}
	
	public boolean ConvertLead()
	{
		//selectValueFromSalesForceLookUpField("Account", "automation1");
		
		ConvertSave_bttn.click();
		return true;
	}
	public String[] ConvertedAccountContactOpportunityName()
	{
		String[] ret = new String[3];
		 ret[0]=getText(AccountOnConvert_link);
		 ret[1]=getText(ContactOnConvert_link);
		 ret[2]=getText(OpportunityOnConvert_link);
		 return ret;
	}
	public void getAccountName()
	{
		AccountOnConvert_link.click();	
	}
	public void createEmptyLead() 
	{
		try {
		Thread.sleep(6000);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		click(LeadSave_bttn);
	}
	public String getErrorText()
	{
		return getText(LeadValidation_info);
	}
	
};
