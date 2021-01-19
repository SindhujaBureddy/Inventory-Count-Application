package com.qa.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase()
	{
		try
		{
			prop=new Properties();
			FileInputStream fis=new FileInputStream("E:\\Old\\InventoryCountApplication\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fis);
		}
		catch(IOException e)
		{
			e.getMessage();
		}
		
	}
	public static void initilizaton()
	{
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\SELENIUM LEARNING\\Chromedriver.exe");
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}
	

}
