package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BasePage;
import com.utlis.ReadPropertyFile;


public class LoginPage extends BasePage{
	
	@FindBy(xpath="//input[@name='username']")
	WebElement Login_username_txt;
	
	@FindBy(xpath="//input[@name='pw']")
	WebElement Login_pswd_txt;
	
	@FindBy(xpath="//input[@name='Login']")
	WebElement Login_bttn;

	
	@FindBy(xpath="//span[contains(@class,'userProfileCardTriggerRoot ')]")
	WebElement user_icon;
	
	
	@FindBy(xpath="//a[text()='Log Out']")
	WebElement logout_bttn;
public LoginPage()
{
	PageFactory.initElements(driver, this);
		
}
	public void login(String username, String password)
	{
		sendkeys(Login_username_txt, username);
		sendkeys(Login_pswd_txt, password);
		click(Login_bttn);
	
	}
	
	public void login(String profile)
	{
		String uname = null;
		String pswd = null;
		
        switch(profile) 
        { 
            case "Sales": 
            	uname = ReadPropertyFile.get("SalesUsername");
            	pswd= ReadPropertyFile.get("SalesPassword");
                break; 
          
            case "BD": 
            	uname = ReadPropertyFile.get("Username");
            	pswd= ReadPropertyFile.get("Password");
            	break;
        } 
        login(uname,pswd);
	}

public void logout()
{
	click(user_icon);
	click(logout_bttn);
	
}
}
