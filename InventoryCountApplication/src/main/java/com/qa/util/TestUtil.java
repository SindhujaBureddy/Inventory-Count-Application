package com.qa.util;

import com.qa.pages.AuthPage;
import com.qa.pages.ItemScanning;
import com.qa.pages.LoginPage;
public class TestUtil extends TestBase{

	public static int IMPLICIT_WAIT=30;
	static LoginPage loginpage;
	static AuthPage authPage;
	public TestUtil()
	{
		super();
	}
	public void setUp() throws InterruptedException
	{
		initilizaton();
		loginpage=new LoginPage();
		authPage=new AuthPage();
		authPage=loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		authPage.choose_role();
		if(authPage.authquestion().equalsIgnoreCase("What was your childhood nickname?"))
		{
			authPage.enterAuthCode(prop.getProperty("auth1"));
		}
		else if(authPage.authquestion().equalsIgnoreCase("In what city or town did your mother and father meet?"))
		{
			authPage.enterAuthCode(prop.getProperty("auth2"));
		
		}
		else
		{
			authPage.enterAuthCode(prop.getProperty("auth3"));
		
		}
		//authPage.required_account("Administrator", "Tvarana Dev Test", "PRODUCTION");
	
	
	}
}