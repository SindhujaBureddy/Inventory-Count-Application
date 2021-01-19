package com.qa.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.util.TestBase;

public class AuthPage extends TestBase {
	@FindBy(xpath="//td[@class='smalltextnolink']//following-sibling::td")
	WebElement question;
	@FindBy(css="tr td input[type='password'][name='answer']")
	WebElement answer;
	@FindBy(css="tr td input[type='submit'][name='submitter']")
	WebElement authsubmit;
	@FindBy(xpath="//div[@id='spn_cRR_d1']")
	WebElement roles;
	@FindBy(xpath="//li[@class='ns-menuitem ns-role-menuitem']")
	List<WebElement> accounts;
	@FindBy(xpath = "//table[@id='div__bodytab']//tr//td[count(//td[@data-label='Role']//preceding-sibling::td)+1]//a")
	List<WebElement> roles_;
	@FindBy(xpath = "//table[@id='div__bodytab']//tr//td[count(//td[@data-label='Partner / Vendor']//preceding-sibling::td)]")
	List<WebElement> company_name;
	@FindBy(xpath = "//table[@id='div__bodytab']//tr//td[count(//td[@data-label='Last Login']//preceding-sibling::td)]")
	List<WebElement> account_type;
	@FindBy(xpath = "//a//preceding::div[@id='spn_cRR_d1']")
	WebElement view_roles;
	@FindBy(xpath = "//a[@href='?role_switch=234071887']")
	WebElement choose_role;
	public AuthPage()
	{
		PageFactory.initElements(driver, this);
	}
	public String validateAuthPageTitle()
	{
		return driver.getTitle();
	}
	public String authquestion()
	{
		return question.getText();
	}
	public void enterAuthCode(String code)
	{
		answer.sendKeys(code);
		authsubmit.click();
	}
//	public void selectAccount()
//	{
//		Actions action=new Actions(driver);
//		action.moveToElement(roles).build().perform();
//		for(int i=0;i<accounts.size();i++)
//		{
//		String text=accounts.get(i).getText();
//		if(text=="Tvarana Dev Production  -  Administrator" && text=="RP")
//		{
//			accounts.get(i).click();
//		}
//		
//		}
//	}
	public void  choose_role()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",choose_role);
		choose_role.click();
	}
	public void required_account(String role,String company,String account) throws InterruptedException
	{
	Actions action=new Actions(driver);
	view_roles.click();
	Thread.sleep(5000);
	for(int i=0;i<roles_.size();i++)
	{
		WebElement role_index=roles_.get(i);
		String role_text=role_index.getText();
		WebElement account_index=account_type.get(i+1);
		String account_text=account_index.getText();
		WebElement company_index=company_name.get(i+1);
		String company_text=company_index.getText();
		if(role_index.getText().trim().equals(role)&&company_index.getText().trim().equals(company)&&account_index.getText().trim().equals(account))
		{
			System.out.println("role is "+role_index.getText());
			System.out.println("account is "+account_index.getText());
			System.out.println("company is "+company_index.getText());
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);",role_index);
			role_index.click();
			break;
			
		}
	}
	}
}


	
	
	

