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

public class Inventory_Items_Testing extends TestBase {
	TestUtil testUtil;
	ItemScanning item;
	ExcelReader reader;
	ExtentSparkReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void setExtent() {
		// specify location of the report
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output-inventory/myReport.html");

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
	public void setUp() throws InterruptedException {
		testUtil = new TestUtil();
		testUtil.setUp();
	}

	@DataProvider
	public Object[][] invalidQuanity() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\inventory_items_testing.xlsx", 0);
	}

	@Test(dataProvider = "invalidQuanity", priority = 3)
	public void invalidQuantity(String itemname, String location, String bin, String serial, String quantity,
			String testcase,String error_message) throws InterruptedException {
		test = extent.createTest("The testcase is" + " " + testcase);
		item = new ItemScanning();
		item.Inventory_count();
		item.invalid_data(itemname, location, bin, serial, quantity, testcase,error_message,test);
	}

	@DataProvider
	public Object[][] Different_quantities() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\inventory_items_testing.xlsx", 2);
	}

	@Test(dataProvider = "Different_quantities", priority = 1)
	public void Different_quantities(String itemname, String location, String bin, String quantity, String testcase,String same_quantity,String greater_quantity,String lesser_quantity)
			throws Exception {
		test = extent.createTest("The testcase is" + " " + testcase);
		item = new ItemScanning();
		item.Inventory_count();
		item.inventory_adjustment_test(itemname, location, bin, quantity, testcase,same_quantity,greater_quantity,lesser_quantity,test);
	}

	@DataProvider
	public Object[][] invalid_bin() throws IOException {
		reader = new ExcelReader();
		return reader.readExcelData("C:\\Users\\Sindhuja\\Desktop\\inventory_items_testing.xlsx", 1);
	}

	@Test(dataProvider = "invalid_bin", priority = 2)
	public void invalid_bin(String itemname, String location, String bin, String quantity, String testcase)
			throws InterruptedException {
		test = extent.createTest("The testcase is" + " " + testcase);
		item = new ItemScanning();
		item.Inventory_count();
		item.bin_mismatch_inventory(itemname, location, bin, quantity, testcase,test);
	}

}
