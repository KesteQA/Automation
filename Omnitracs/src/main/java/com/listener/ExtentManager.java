package com.listener;

import java.io.File;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utlis.ReadPropertyFile;

public class ExtentManager {

	private static ExtentReports extent;
	private static String reportFileName = "Test-Automaton-Report" + ".html";
	private static String fileSeperator = System.getProperty("file.separator");
	private static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "TestReport";
	private static String reportFileLocation = reportFilepath + fileSeperator + reportFileName;

	private static Logger logs = Logger.getLogger(ExtentManager.class.getName()); 
	
	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	// Create an extent report instance
	public static ExtentReports createInstance() {
		String fileName = getReportPath(reportFilepath);

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Keste Automation Framework");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Omnitracs Automation Report");
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		// Set environment details
		
		extent.setSystemInfo("OS ", System.getProperty("os.name"));
		extent.setSystemInfo("OS version", System.getProperty("os.version"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("Executed By", System.getProperty("User.name"));
		extent.setSystemInfo("Browser", ReadPropertyFile.get("Browser"));
		extent.setSystemInfo("Environment", "QA Environment");

		return extent;
	}

	// Create the report path
	private static String getReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				logs.info("Info: Extent Manager: Directory: " + path + " is created!");
				return reportFileLocation;
			} else {
				logs.error("Info: Extent Manager: Failed to create directory: " + path);
				return System.getProperty("user.dir");
			}
		} else {
			logs.fatal("Info: Extent Manager: Directory already exists: " + path);
		}
		return reportFileLocation;
	}

}
