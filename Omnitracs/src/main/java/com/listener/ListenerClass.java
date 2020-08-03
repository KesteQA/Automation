package com.listener;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.aventstack.extentreports.Status;
import com.base.BasePage;

public class ListenerClass implements ITestListener {

	private static String TestcaseName;

	public static String getTestcaseName() {
		return TestcaseName;
	}

	public static void setTestcaseName(String testcaseName) {
		TestcaseName = testcaseName;
	}

	public void onStart(ITestContext context) {
		
		Reporter.log("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		
		// logs.info((result.getMethod().getMethodName() + " failed!"));
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		try {
			ExtentTestManager.getTest().addScreenCaptureFromPath(BasePage.takeScreenshot());
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		//logs.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}
}
