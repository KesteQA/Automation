package com.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BasePage;
import com.utlis.TestUtils;

public class OpportunitiesPage extends BasePage{
	
	@FindBy(xpath="//div[@title='New']")
	WebElement oppNew_bttn;
	
	
	@FindBy(xpath="//button[@title='Save']")
	WebElement oppSave_bttn;
	
	@FindBy(xpath="//ul[@class='errorsList']/li")
	WebElement oppValidation_info;  
	
	@FindBy(xpath = "//span[text()= 'Opportunity Name']/parent::*//following-sibling::input")
	WebElement oppName_txt;
	
	@FindBy(xpath = "//span[contains(text(),'Account Name')]/parent::label//following-sibling::div//input")
	WebElement accName_txt;
	
	
	@FindBy(xpath = "//span[text()='Contact']/parent::label//following-sibling::div//input")
	WebElement contacts_txt;
	
	@FindBy(xpath = "//span[text()='Close Date']/parent::label/following::input[@type='text'][1]")
	WebElement closedDate_txt;
	
	
	@FindBy(xpath = "//span[text()='Sales Stage']/parent::span/following-sibling::div")
	WebElement salesStage_txt;
	
	@FindBy(xpath = "//span[text()='Probability']/parent::span/following-sibling::div")
	WebElement probability_txt;
	
	@FindBy(xpath = " //h1//lightning-formatted-text")
	WebElement oppTitle;
	
	@FindBy(xpath = " //button[text()='Update Plan']")
	WebElement updatePlan_bttn;
	
	@FindBy(xpath = " //div[text()='New Quote']")
	WebElement newQuote_bttn;
	
	@FindBy(xpath="//span[text()='Primary']//parent::label//following-sibling::input")
	WebElement primary_checkbox;
	
	@FindBy(xpath= "//div[contains(@class,'modal-footer')]//button//span[text()='Save']")
	WebElement saveQuote_bbtn;
	
	@FindBy(xpath="/html/body/div[6]/div/div/div")
	WebElement quoteNumber_text;
	
	public @FindBy(xpath = " //span[text()='Edit Sales Plan Opt Out']")
	WebElement editSalesPlanOut_bttn;
	
	@FindBy(xpath = "//input[@name='Sales_Plan_Opt_Out__c']")
	WebElement editSalesPlanOut_checkbox;
	
	@FindBy(xpath = " //button[text()='Save']")
	WebElement save_bttn;
	
	@FindBy(xpath = " //span[text()='Closed']//parent::a")
	WebElement closed_link;
	
	@FindBy(xpath = " //span[text()='Select Closed Stage']//parent::button")
	WebElement selectClosedStage_bttn;
	
	@FindBy(xpath = " //button[text()='Done']")
	WebElement done_bttn;
	
	
public String validationError = "failing on purpose";
	
public OpportunitiesPage()
{
	PageFactory.initElements(driver, this);
		
}
	public void createEmptyOpp() 
	{
		click(oppNew_bttn);
		try {
		Thread.sleep(6000);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		click(oppSave_bttn);
	}
	
	
	public String getErrorText()
	{
		return getText(oppValidation_info);
	}
	
	
	
	
	public String createOpportunity(Map<String, String> testData)
	{
		String opportunityName= TestUtils.getRandomString(8); 
		click(oppNew_bttn);
		try {
			Thread.sleep(8000);
			}
			catch (Exception e){
				System.out.println(e.getMessage());
			}
		sendkeys(getInputTextField("Opportunity Name"), opportunityName);
		selectValueFromLookUpField(accName_txt, testData.get("AccountName"));
		selectValueFromLookUpField(contacts_txt, testData.get("ContactName"));
		sendkeys(closedDate_txt, testData.get("ClosedDate"));	
		selectValueFromSalesForceDropdown(salesStage_txt, testData.get("SalesStage"));
		selectValueFromSalesForceDropdown(probability_txt, testData.get("Probability"));
		click(oppSave_bttn);
	return opportunityName;
	}
	
	
	public String getOppTitle()
	{
		return getText(oppTitle);
	}
	
	public void clickTasksTab(String tabname)
	{
		click(driver.findElement(By.xpath("//li[@title='"+tabname+"']//a")));
		
	}
	
	public void updatetask(String tabname)
	{
		clickTasksTab(tabname);
		List<WebElement> tasks = driver.findElements(By.xpath("//div[text()='Name']//ancestor::table/tbody/tr"));
		for(int i=0;i<tasks.size();i++)
		{
			click(driver.findElement(By.xpath("//div[text()='Name']//ancestor::table/thead/tr["+i+"]/th[3]//div")));
		}
		click(updatePlan_bttn);
	}
	
	public void createQuote(Boolean isPrimary, String subscriptionterm)
	{
		click(newQuote_bttn);
		click(primary_checkbox);
		sendkeys(getInputTextField("Subscription Term"), subscriptionterm);
		click(saveQuote_bbtn);
		
	}
	public String getquoteTitle()
	{
		return getText(quoteNumber_text);
	}
	public void Javascript(WebElement searchSuggestion)
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",searchSuggestion);		
	}
	public void salesplanout()
	{
		Javascript(editSalesPlanOut_bttn);
		Javascript(editSalesPlanOut_checkbox);
		Javascript(save_bttn);
	}
	public void Closedwon()
	{
		Javascript(closed_link);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Javascript(selectClosedStage_bttn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Javascript(done_bttn);
	}
	
}

