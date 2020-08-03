package com.constants;

import java.io.File;

public class Constants {

	public static final String PROJECTPATH = System.getProperty("user.dir");

	public static final int EXPLICITWAIT = 60;
	public static final long pageLoadTime = 40;
	
	
	public static final String PropertiesFilePATH = System.getProperty("user.dir") + File.separator
			+ "src\\test\\resources\\PropertyFiles\\TestRunDetails.properties";
	
	public static final String TESTCASES_PATH = System.getProperty("user.dir") + File.separator
			+ "\\src\\test\\resources\\Excel\\TestCases.xlsx";

	public static final String screenshotsFolder_path = System.getProperty("user.dir") + File.separator
			+ "src\\test\\resources\\Screenshot";
	
	public static final String extentReportPath = System.getProperty("user.dir") + File.separator
			+ "\\TestReport\\Test-Automaton-Report.html";
	
	public static final String log4jPropertiesPath = System.getProperty("user.dir") + File.separator
			+ "src\\test\\resources\\PropertyFiles\\log4j.properties";

	public static final String TESTCASESSHEETNAME = "TestCases";

	public static final String TestDataColumnName = "TestData";

	public static final String CHROMEDRIVERPATH = System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\chromedriver.exe";

	


}
