package com.qa.testcases;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.exceldataReader.ExcelReader;
import com.qa.pages.ItemScanning;
import com.qa.util.TestBase;
import com.qa.util.TestUtil;
public class Serial_Items_testcases extends TestBase {
	TestUtil testUtil;
	ItemScanning item;
	ExcelReader reader;
	ExtentSparkReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void setExtent() {
		// specify location of the report
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output-serialnumbers/myReport.html");

		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		htmlReporter.config().setReportName("Netsuite Login Testing"); // Name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// Passing General information
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Sindhuja"); 
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
//			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent
																					// report
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
//			test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		}
		driver.quit();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		testUtil=new TestUtil();
		testUtil.setUp();
	}
	 @DataProvider
		public Object[][] invalid_Data() throws IOException
		{
		 reader=new ExcelReader();
			return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\serial number with bins.xlsx",0);
		}
	 @Test(dataProvider = "invalid_Data", priority = 5)
	 public void invalid_Data(String itemname,String location,String bin,String serial, String quantity,String testcase,String error_message) throws InterruptedException
	 { 
		test = extent.createTest("The testcase is" + " " +testcase);
		 item=new ItemScanning();
		 item.Inventory_count();   
		 item.invalid_data(itemname, location, bin, serial, quantity,testcase,error_message,test);	 
	 }
	 @DataProvider
		public Object[][] bin_Mismatch() throws IOException
		{
		 reader=new ExcelReader();
			return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\serial number with bins.xlsx",1);
		}
	 @Test(dataProvider = "bin_Mismatch",priority = 2)
	 public void bin_Mismatch(String itemname,String location,String bin,String serial, String quantity,String testcase) throws InterruptedException
	 {
		 test = extent.createTest("The testcase is" + " " +testcase);
		 item=new ItemScanning();
		 item.Inventory_count();   
		 item.bin_mismatch_serial(itemname, location, bin, serial, quantity,testcase,test);
	 }
	 @DataProvider
		public Object[][] Without_serial_nrs() throws IOException
		{
		 reader=new ExcelReader();
			return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\serial number with bins.xlsx",2);
		}
	 @Test(dataProvider = "Without_serial_nrs",priority = 3)
	 public void Without_serial_nrs(String itemname,String location,String bin,String serial, String quantity,String testcase) throws InterruptedException
	 {
		 test = extent.createTest("The testcase is" + " " +testcase);
		 item=new ItemScanning();
		 item.Inventory_count();   
		 item.without_giving_serial_numbers(itemname, location, bin, serial, quantity, testcase,test);
	 }
	 @DataProvider
		public Object[][] Processing_without_bins() throws IOException
		{
		 reader=new ExcelReader();
			return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\serial number with bins.xlsx",4);
		}
	 @Test(dataProvider = "Processing_without_bins",priority = 1)
	 public void Processing_without_bins(String itemname,String location,String bin,String serial, String quantity,String testcase,String new_serial,String new_quantity) throws Exception
	 {
		 test = extent.createTest("The testcase is" + " " +testcase);
		 item=new ItemScanning();
		 item.Inventory_count();   
		 item.serial_items_numbers_adding_with_bins(itemname, location, bin, serial, quantity, testcase,new_serial,new_quantity,test);
	 }
	 @DataProvider
		public Object[][] Processing_with_bins() throws IOException
		{
		 reader=new ExcelReader();
			return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\serial number with bins.xlsx",3);
		}
	 @Test(dataProvider = "Processing_with_bins",priority = 4)
	 public void Processing_with_bins(String itemname,String location,String bin,String serial, String quantity,String testcase,String new_serial,String new_quantity) throws Exception
	 {
		 
	     test = extent.createTest("The testcase is" + " " +testcase);
		 item=new ItemScanning();
		 item.Inventory_count();   
		 item.serial_items_numbers_adding_with_bins(itemname, location, bin, serial, quantity,testcase,new_serial,new_quantity,test);
	 }

}


