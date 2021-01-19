package com.qa.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.util.TestBase;
public class LoginPage extends TestBase {
	
	// we have to define page objects with the help of @FindBy
	@FindBy(name="email")
	WebElement username;
	@FindBy(name="password")
	WebElement password;
	@FindBy(name="submitButton")
	WebElement submit;
	
	//Initializing the Page Objects:
		public LoginPage(){
			PageFactory.initElements(driver, this);
		}
		
		//Actions:
		public String validateLoginPageTitle(){
			return driver.getTitle();
		}
	
	public AuthPage login(String usrn, String pswd)
	{
		username.sendKeys(usrn);
		password.sendKeys(pswd);
		submit.click();
		return new AuthPage();
	}
	
}
