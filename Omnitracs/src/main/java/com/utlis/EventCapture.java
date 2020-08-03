package com.utlis;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.listener.ExtentTestManager;

public class EventCapture implements WebDriverEventListener{

	Logger logger=Logger.getLogger(this.getClass());
	
	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In Before alert");
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In after accepting the  alert");
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In after dismissing the  alert");
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In before dismissing the  alert");
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In before navigating to the url : "+ url);
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In After navigating to the url : "+ url);
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In before Navigate Back");
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In after Navigate Back");
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In Before Navigate forward");
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In after Navigate forward");
	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In Before Navigate refresh");
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In After Navigate refresh");
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		//logger.info("In Before finding the element :" + element );
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In After finding the element :" + element );
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In Before Clicking on  the element :" + element );
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In After Clicking the element :" + element );
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		logger.info("In Before changing the value of the element :" + element + ", value to be  sent is :" + keysToSend);
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		logger.info("In Before changing the value of the element :" + element + ", value sent is :" + keysToSend);
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In Before executing the script: "+ script);
		
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In After executing the script: "+ script);
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In Before moving to the window : "+ windowName);
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In After moving to the window : "+ windowName);
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
		try 
		{
			logger.info("In exception: ");
			//ExtentTestManager.getTest().log(Status.WARNING,"Exception has occured");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			//ExtentTestManager.getTest().log(Status.WARNING, "Exception message is : "+e.getMessage());
		}
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		logger.info("In Before taking the screenshot");
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub
		logger.info("In After  taking the screenshot");
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		logger.info("In Before taking the text for the webelement : "+ element);
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		logger.info("In After taking the text for the webelement : "+ element +" The text value is: "+ text);
		 
	}

}
