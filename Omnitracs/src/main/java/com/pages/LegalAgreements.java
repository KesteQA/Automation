package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BasePage;

public class LegalAgreements extends BasePage{
	
	public LegalAgreements()
	{
		PageFactory.initElements(driver, this);		
	}
	
	
	@FindBy(xpath="//p[text()='Agreement Type']//parent::div//following-sibling::p/slot/lightning-formatted-text")
	WebElement agreementType_info;
	
	
	@FindBy(xpath="//p[text()='Primary Quote']//parent::div//following-sibling::p/slot//a")
	WebElement primaryQuote_info;
	
	
	public String getAgreementType()
	{
		explicitlyWait(agreementType_info);
	String agreementType =getText(agreementType_info);
		return agreementType;
	}
	
	public void clickPrimaryQuote()
	{
		explicitlyWait(primaryQuote_info);
		click(primaryQuote_info);
	}
	
	public void clickPrimaryQuote(String quotenumber)
	{
		WebElement quote = driver.findElement(By.xpath("//a[text()='"+quotenumber+"']"));
		
		explicitlyWait(quote);
		click(quote);
	}
}
