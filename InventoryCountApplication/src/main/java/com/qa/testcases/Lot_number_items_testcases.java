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

public class Lot_number_items_testcases extends TestBase {
	TestUtil testUtil;
	ItemScanning item;
	ExcelReader reader;
	ExtentSparkReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void setExtent() {
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output-lotnumbers/myReport.html");

		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		htmlReporter.config().setReportName("Inventory Count Testing with Lot Numbers"); // Name of the report
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
			//test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent
																					// report
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			//test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		}
		driver.quit();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		testUtil = new TestUtil();
		testUtil.setUp();
	}
	@DataProvider
	public Object[][] bin_mismatch() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\lot_numbers_testing.xlsx", 0);
	}

	@Test(dataProvider = "bin_mismatch", priority = 4)
	public void bin_mismatch(String itemname, String location, String bin, String serial, String quantity,String testcase)
			throws InterruptedException {
		test = extent.createTest("The test case is" +" " +testcase);
		item = new ItemScanning();
		item.Inventory_count();
		item.bin_mismatch_serial(itemname, location, bin, serial, quantity, testcase, test);

	}

	@DataProvider
	public Object[][] Invalid_data() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\lot_numbers_testing.xlsx", 1);
	}

	@Test(dataProvider = "Invalid_data", priority = 3)
	public void Invalid_data(String itemname, String location, String bin, String serial, String quantity,String testcase,String error_message)
			throws InterruptedException {
		test = extent.createTest("The test case is" +" " +testcase);
		item = new ItemScanning();
		item.Inventory_count();
		item.invalid_data(itemname, location, bin, serial, quantity, testcase,error_message,test);

	}

	@DataProvider
	public Object[][] Without_lot_number() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\lot_numbers_testing.xlsx", 2);
	}

	@Test(dataProvider = "Without_lot_number", priority = 2)
	public void Without_lot_number(String itemname, String location, String bin, String serial, String quantity,
			String testcase) throws InterruptedException {
		test = extent.createTest("The test case is" +" " +testcase);
		item = new ItemScanning();
		item.Inventory_count(); 
		item.without_giving_serial_numbers(itemname, location, bin, serial, quantity, testcase,test);

	}

	@DataProvider
	public Object[][] lot_number_processing() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\lot_numbers_testing.xlsx", 3);
	}

	@Test(dataProvider = "lot_number_processing", priority = 1)
	public void lot_number_processing(String itemname, String location, String bin, String serial, String quantity,
			 String testcase,String new_lot,String new_quantity) throws Exception {
		test = extent.createTest("The test case is" +" " +testcase);
		item = new ItemScanning();
		item.Inventory_count();
		item.lot_numbers_processing(itemname, location, bin, serial, quantity, testcase,new_lot,new_quantity,test);

	}

}
