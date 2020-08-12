package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BasePage;

public class QuotePage extends BasePage {
	
	public QuotePage()
	{
		PageFactory.initElements(driver, this);		
	}
	@FindBy(xpath = " //h1//lightning-formatted-text")
	WebElement quoteTitle;
	
	@FindBy(xpath = " //button[text()='Edit Lines']")
	WebElement editLines_bttn;
	@FindBy(xpath = " //span[text()='Show more actions']//parent::button")
	WebElement showmore_bttn;
	@FindBy(xpath = "//span[text()='Edit Lines']//parent::a")
	WebElement editLines_link;
	
	@FindBy(xpath="//*[contains(@name,'vfFrameId_')]")
	WebElement containerFrame;
	
	@FindBy(xpath="//slot[@name='actionsProvider']//following-sibling::ul/li[4]//button")
	WebElement moreActions_icon;
	
	@FindBy(xpath="//span[contains(text(), 'Generate Document')]")
	WebElement generateDocument_bttn;
	
	//span[contains(text(), 'Generate Document')]
	
	public @FindBy(xpath = " //paper-button[text()='Add Products']")
	WebElement addProducts_bttn;
	
	public @FindBy(xpath = " //paper-button[@id='suggest']//sb-i18n[text()='Suggest']")
	WebElement suggest_bttn;
	
	public @FindBy(xpath = " //div[text()='Software']")
	WebElement software_Tab;
	
	public @FindBy(xpath = " //div[text()='Training']")
	WebElement training_Tab;
	
	public @FindBy(xpath = " //div[@id='allActions']//paper-button//sb-i18n[text()='Save']")
	WebElement save_bttn;
	
	public @FindBy(xpath = " //paper-button[text()='Save']")
	WebElement editSave_bttn;
	
	public @FindBy(xpath = " //paper-button[text()='Add Group']")
	WebElement addGroup_bttn;
	
	public @FindBy(xpath = " //span[text()='Edit Status']")
	WebElement editstatus_bttn;
	
	public @FindBy(xpath = " //button[text()='Save']")
	WebElement quoteSave_bttn;
	
	 @FindBy(xpath = " //label[contains(text(),'Document Generation Choice')]/parent::lightning-combobox//div/input")
	WebElement DocumentType_dropdown;
	 
	 @FindBy(xpath = " //label[contains(text(),'Document Language')]/following-sibling::lightning-input-field//div[@role ='combobox']")
		WebElement DocumentLang_dropdown;
		 
	
	public @FindBy(xpath = " //span[text()='Edit Opportunity']/parent::button/preceding-sibling::span//a")
	WebElement opportunity_link;
	
	@FindBy(xpath = "//button[text()='Generate']")
	WebElement generate_bttn;
	
	
	public void clickGeneratbttn() {
		javascriptClick(generate_bttn);
		explicitlyWait(generate_bttn);
	}
	
	public void clickGeneratDocument()
	{
		javascriptClick(moreActions_icon);
		explicitlyWait(generateDocument_bttn);
		javascriptClick(generateDocument_bttn);
	}
	
	public void selectDocumentType(String doctype, String lang)
	{
		explicitlyWait(DocumentType_dropdown);
		javascriptClick(DocumentType_dropdown);
		WebElement docTypeElement = driver.findElement(By.xpath("//span[text()='"+doctype+"']"));
		explicitlyWait(docTypeElement);
		javascriptClick(docTypeElement);
		explicitlyWait(DocumentLang_dropdown);
		javascriptClick(DocumentLang_dropdown);
		WebElement langElement = driver.findElement(By.xpath("//span[text()='"+lang+"']"));
		explicitlyWait(langElement);
		javascriptClick(langElement);
	}
	
	
	 public WebDriver getFrame() 
	 { 
		 return driver.switchTo().frame(containerFrame); 
	 }
	 
	
	public String getquoteTitle()
	{
		return getText(quoteTitle);
	}
	public void EditLines()
	{
		click(showmore_bttn);	
		click(editLines_link);
	}
	public void AddProducts()
	{
		click(addProducts_bttn);	
	}
	public void Javascript(WebElement searchSuggestion)
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",searchSuggestion);		
	}
	public void selectRandomFromDropDown(String fieldname)
	{
		WebElement dropDown = null;
		dropDown = driver.findElement(By.xpath("//div[contains(text(),'"+ fieldname+"')]/parent::div//following-sibling::div//select"));
		click(dropDown);
		List<WebElement> dropdownOptions = driver.findElements(By.xpath("//div[contains(text(),'"+ fieldname+"')]/parent::div//following-sibling::div//select//option"));
		//int dropDownValuesCount = dropdownOptions.size();
		//int randomIndex = getRandomInteger(dropDownValuesCount, 1);
		click(dropdownOptions.get(1));
	}
	public void selectRandomCheckbox(String fieldname,int number)
	{
		WebElement dropDown1 = null;
		dropDown1 = driver.findElement(By.xpath("//sb-product-feature-list[@label='"+ fieldname +"']//div[@id='feature-body']//div//sb-table-row//sb-group//div[@id='checkboxContainer']"));
		List<WebElement> dropdownOptions = driver.findElements(By.xpath("//sb-product-feature-list[@label='"+ fieldname+"']//div[@id='feature-body']//div//sb-table-row//sb-group//div[@id='checkboxContainer']"));
		//int dropDownValuesCount = dropdownOptions.size();
		//int randomIndex = getRandomInteger(dropDownValuesCount, 1);
		click(dropdownOptions.get(number));
	}
	public void selectquoteValueFromDropDown(String fieldname, String value)
	{
			WebElement  dropdownfield = driver.findElement(By.xpath("//label[contains(text(),'"+ fieldname+"')]/parent::lightning-combobox//following-sibling::div//input"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", dropdownfield);
			WebElement  dropdownValue = driver.findElement(By.xpath("//lightning-base-combobox-item[@class= 'slds-media slds-listbox__option slds-media_center slds-media_small slds-listbox__option_plain']//span[contains(text(),'"+ value+"')]"));
			executor.executeScript("arguments[0].click();", dropdownValue);
	}
	
	public void addProducts()
	{
		Javascript(addProducts_bttn);
		//quotePage.AddProducts();
		selectRandomFromDropDown("What geo-region are you selling into?");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectRandomFromDropDown("What product family are you selling?");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectRandomFromDropDown("What product line are you selling?");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Javascript(suggest_bttn);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectRandomCheckbox("Hardware",1);
		Javascript(software_Tab);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectRandomCheckbox("Software",1);
		Javascript(training_Tab);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectRandomCheckbox("Training",2);
		Javascript(save_bttn);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Javascript(addGroup_bttn);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Javascript(editSave_bttn);
	}
	
	public void changeStatus(String statusToBeUpdated)
	{
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Javascript(editstatus_bttn);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectquoteValueFromDropDown("Status", statusToBeUpdated);
		Javascript(quoteSave_bttn);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openOpportunity()
	{
		Javascript(opportunity_link);
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].scrollIntoView();",opportunity_link);
		//Javascript(opportunity_link);
		
	}

	
}

