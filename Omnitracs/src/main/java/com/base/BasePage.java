package com.base;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Constants;
import com.google.common.io.Files;
import com.utlis.EventCapture;
import com.utlis.ReadPropertyFile;
import com.utlis.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	public static WebDriver driver;
	
	Logger logger=Logger.getLogger(this.getClass());
	
	public BasePage(){
	
		PropertyConfigurator.configure(Constants.log4jPropertiesPath);
	}
	
	private static void explicitlyWait(WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Constants.EXPLICITWAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void startBrowser() {
		String browser=ReadPropertyFile.get("Browser");
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				//WebDriverManager.chromedriver().setup();  
				System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVERPATH);
				driver=new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) 
			{
				WebDriverManager.firefoxdriver().setup();
				driver= new FirefoxDriver();
			}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(ReadPropertyFile.get("WaitTime")), TimeUnit.SECONDS);
		EventHandlerInit();
		driver.get(ReadPropertyFile.get("url"));
		driver.manage().deleteAllCookies();
	logger.info("Launched url in browser");
		}
		catch (Exception e) {
			
		}
	}
	
	private void EventHandlerInit() {
		EventFiringWebDriver eventhandle = new EventFiringWebDriver(driver);
		EventCapture capture= new EventCapture();
		eventhandle.register(capture);
		driver=eventhandle;
	}
	
	public static void click(WebElement element)  {
		explicitlyWait(element);
		element.click();
	}

	public static void click(By by)  {
		click(driver.findElement(by));
	}


	public static void sendkeys(WebElement element, String text)  {
		explicitlyWait(element);
		element.sendKeys(text);
	}
	
	public static String getText(WebElement element)  {
		explicitlyWait(element);
		return element.getText();
	}
	
	public static void sendkeys(By by, String text)  {
		sendkeys(driver.findElement(by), text);
	}
	
	
	public static void moveToElement(WebElement element) {
		Actions actions= new Actions(driver);
		actions.moveToElement(element).build().perform();
	}
	
	public static void moveToElement(By by) {
		moveToElement(driver.findElement(by));
	}


	
	public static void switchToNewWindow() {
		String parentWinHandle = driver.getWindowHandle();
		Set<String> winHandles = driver.getWindowHandles();
		for(String temp:winHandles) {
			if(!temp.equalsIgnoreCase(parentWinHandle)) {
				driver.switchTo().window(temp);
			}
		}
	}
	
	public static int getRandomInteger(int maximum, int minimum){ 
		return ((int) (Math.random()*(maximum - minimum))) + minimum; }

	
	public static void selectByValue(WebElement element,String text) {
		new Select(element).selectByValue(text);
	}
	
	public static void selectByVisibleText(WebElement element,String text) {
		new Select(element).selectByVisibleText(text);
		
	}
	
	public static void selectByIndex(WebElement element,int index) {
		new Select(element).selectByIndex(index);
	}
	
	
	/**
	 * Wait for page to load based on document.readyState=complete
	 */
	public static void domLoaded() {
		final JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean domReady = js.executeScript("return document.readyState").equals("complete");

		if (!domReady) {
			new WebDriverWait(driver, 30).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return (js.executeScript("return document.readyState").equals("complete"));
				}
			});
		}
	}

	public static int getCode(String url) {
		int connectionCode = 0;
		try {

			URL link = new URL(url);

			HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();

			httpConn.setConnectTimeout(2000);
			httpConn.connect();

			connectionCode = httpConn.getResponseCode();
		}

		catch (Exception e) {
		}
		return connectionCode;
	}

	
	public static boolean navigateTabs(String tabname)
	{
		WebElement tab = driver.findElement(By.xpath("//a[@title='"+ tabname+"']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", tab);
		domLoaded();
		return true;
	}

	

	public static String takeScreenshot() throws Exception {

		String screenshotName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File destinationPath = new File(
				Constants.screenshotsFolder_path + File.separator + screenshotName + ".png");

		Files.copy(sourcePath, destinationPath);

		return destinationPath.toString();
	}

public static Map<String, String> getTestDataForMethod(String methodname)
{
	
	String testData =  TestUtils.getCellContent(methodname, Constants.TestDataColumnName);
	return convertStringToMap(testData);
	
}


public static Map<String, String> convertStringToMap(String value)
{
	String[] keyValuePairs = value.split(",");              //split the string to creat key-value pairs
	Map<String,String> map = new HashMap<>();               

	for(String pair : keyValuePairs)                        //iterate over the pairs
	{
	    String[] entry = pair.split(":");                   //split the pairs to get key and value 
	    map.put(entry[0].trim(), entry[1].trim());    //add them to the hashmap and trim whitespaces

	}
	return map;
}


public static void selectValueFromLookUpField(WebElement element, String value)
{
	click(element);
	sendkeys(element,value);
	WebElement  dropdownValue = driver.findElement(By.xpath("//ul[@class='lookup__list  visible']//li//a//div[@title = '"+value+"']"));
click(dropdownValue);
}

public static void selectValueFromSalesForceLookUpField(String fieldname, String value)
{
	WebElement  lookupfield = driver.findElement(By.xpath("//span[contains(text(),'"+ fieldname+"')]/parent::label//following-sibling::div//input"));
	click(lookupfield);
	lookupfield.sendKeys(value);
	WebElement  lookupValue = driver.findElement(By.xpath("//ul[@class='lookup__list  visible']//li//a//div[@title = '"+value+"']"));
    click(lookupValue);
}

public static void selectValueFromSalesForceDropdown(WebElement element, String value)
{
	click(element);
	WebElement  dropdownValue = driver.findElement(By.xpath("//div[@class= 'select-options']//ul//li/a[contains(text(),'"+value+ "')]"));
	click(dropdownValue);
}

public static void selectValueFromDropdown(String fieldname, String value)
{
	WebElement  dropdownfield = driver.findElement(By.xpath("//span[text()='"+ fieldname+"']/parent::span/following-sibling::div"));
	click(dropdownfield);
	WebElement  dropdownValue = driver.findElement(By.xpath("//div[@class= 'select-options']//ul//li/a[contains(text(),'"+value+ "')]"));
	click(dropdownValue);
}


public static void selectValueFromDropDown(Boolean isLookUp, String fieldname, String value)
{
	if(isLookUp)
	{
		WebElement  lookupfield = driver.findElement(By.xpath("//span[contains(text(),'"+ fieldname+"')]/parent::label//following-sibling::div//input"));
		click(lookupfield);
		lookupfield.sendKeys(value);
		WebElement  lookupValue = driver.findElement(By.xpath("//ul[@class='lookup__list  visible']//li//a//div[@title = '"+value+"']"));
	    click(lookupValue);
	}
	else
		{
		WebElement  dropdownfield = driver.findElement(By.xpath("//span[text()='"+ fieldname+"']/parent::span/following-sibling::div"));
		click(dropdownfield);
		WebElement  dropdownValue = driver.findElement(By.xpath("//div[@class= 'select-options']//ul//li/a[contains(text(),'"+value+ "')]"));
		click(dropdownValue);
		}
		}

public static void selectRandomFromDropDown(Boolean isLookUp, String fieldname)
{
	WebElement dropDown = null;
	if(isLookUp)
	{
		 dropDown = driver.findElement(By.xpath("//span[contains(text(),'"+ fieldname+"')]/parent::label//following-sibling::div//input"));
	}
	else {
		dropDown = driver.findElement(By.xpath("//span[text()='"+ fieldname+"']/parent::span/following-sibling::div"));
	}
	click(dropDown);
	List<WebElement> dropdownOptions = driver.findElements(By.xpath("//div[@class= 'select-options']//ul//li/a"));
	int dropDownValuesCount = dropdownOptions.size();
	int randomIndex = getRandomInteger(dropDownValuesCount, 1);
	click(dropdownOptions.get(randomIndex));
}

public static WebElement getInputTextField(String fieldname)
{
	 return driver.findElement(By.xpath("//span[text()= '"+ fieldname+"']/parent::*//following-sibling::input"));
	
}


public static void selectObjectType(String type)
{
	
	WebElement  typeSelection = driver.findElement(By.xpath("//div[@class='changeRecordTypeOptionRightColumn']//span[text()='"+type+"']"));
	click(typeSelection);
}

public static int searchRecord(String searchKeyword)
{
	WebElement searchBox = driver.findElement(By.xpath("//input[contains(@placeholder, 'Search this list...')]"));
	sendkeys(searchBox, searchKeyword);
	searchBox.sendKeys(Keys.ENTER);
	String searchresult =  driver.findElement(By.xpath("//span[text()='Updated a few seconds ago']//preceding-sibling::span")).getText();
	System.out.println(searchresult);
	int count = Integer.parseInt(searchresult.substring(0, searchresult.indexOf("i")).trim());
			return count;
	
}

public static void clickRecord(String recordName)
{
	WebElement record = driver.findElement(By.xpath("//th//a[text()='"+recordName +"']"));
	click(record);
}

public static void changeViewinTab(String viewName)
{
	WebElement viewListDropDown = driver.findElement(By.xpath("//span[text()='Select List View'][1]"));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", viewListDropDown);
	//click(viewListDropDown);
	WebElement view = driver.findElement(By.xpath("//span[text()='"+viewName+"']"));
	//executor.executeScript("arguments[0].click();", view);
	click(view);
}

public static int globalSearch(String objectName, String searchKeyword)
{
	WebElement globalSearchDropdown = driver.findElement(By.xpath("//div[@role='combobox']"));
	click(globalSearchDropdown);
	WebElement object =  driver.findElement(By.xpath("//div[@role='combobox']//div[@role='listbox']//span[@title='"+objectName+"'][1]"));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", object);
	//click(object);
	WebElement globalSearch = driver.findElement(By.xpath("//input[@placeholder='Search...']"));
	sendkeys(globalSearch, searchKeyword);
	domLoaded();
	try {
		Thread.sleep(6000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List<WebElement> searchsuggestions = driver.findElements(By.xpath("//div[@class='listContent']//ul[@role='presentation']//li"));
	//System.out.println("value is "+ searchsuggestions.size());
	
	return searchsuggestions.size();
}

public static void selectSearchSuggestion(String searchKeyword)
{

	WebElement searchSuggestion = driver.findElement(By.xpath("//div[@class='listContent']//ul[@role='presentation']//li//span[@title ='"+searchKeyword+"']"));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	try {
		Thread.sleep(6000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	executor.executeScript("arguments[0].click();", searchSuggestion);		
	//click(searchSuggestion);
}

}
