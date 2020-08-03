package com.utlis;

import java.io.File;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import com.constants.Constants;


public class SendEmail {
	static String testngReportPath = System.getProperty("user.dir") + File.separator + "\\test-output\\emailable-report.html";
	
	public static void sendEmailWithResults() throws Exception {

		if(ReadPropertyFile.get("SendExecutionResultsInEmail").equalsIgnoreCase("yes")) {
			MultiPartEmail email = new MultiPartEmail();
			
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(Constants.extentReportPath);
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Execution Results"); 
			attachment.setName("Status Report.html");
			email.attach(attachment);
			
			
		/*	
			attachment.setPath(testngReportPath);
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Execution Results"); 
			attachment.setName("Testng Report.html");
			email.attach(attachment);
		*/
			
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(ReadPropertyFile.get("FromEmail"), ReadPropertyFile.get("EmailPassword")));
			email.setSSLOnConnect(true);
			email.setStartTLSEnabled(true);
			email.setFrom(ReadPropertyFile.get("FromEmail"));
			email.setSubject("Test Execution Status Report");
			email.setMsg("Hi Team,\n\n Find the Attached test Automation Execution Report\n\n");
			try {
				email.addTo(getList("ToEmails"));
				email.addCc(getList("CCEmails"));
				email.addBcc(getList("BCCEmails"));
			}
			catch(Exception e) {

			}
			email.send();
			System.out.println("Email sent-->");
		}
	}

	/*
	 * Used to separate email list from the TestRunDetails.properties based on comma and return them as a String array.
	 */
	public static String[] getList(String maillist) {
		String[] toList=null;
		toList=ReadPropertyFile.get(maillist).split(",");

		return toList;
	}
	
}
