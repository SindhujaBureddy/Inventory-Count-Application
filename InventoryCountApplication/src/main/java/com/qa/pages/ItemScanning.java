package com.qa.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qa.util.TestBase;

public class ItemScanning extends TestBase {
	static String parentWindow;
	@FindBy(xpath = "//span[@id='custrecord_tss_seraial_lot_no_fs_lbl_uir_label']//following-sibling::span")
	WebElement lotnrs;
	@FindBy(xpath = "//span[@id='custrecord_tss_bin_no_fs_lbl_uir_label']//following-sibling::span")
	WebElement bin_text;
	@FindBy(xpath = "//*[@id=\"main_form\"]/table/tbody/tr[2]/td/table/tbody/tr/td[3]/table/tbody/tr[3]/td/div/span[2]")
	WebElement removal_serial_nrs;
	@FindBy(xpath = "//*[@id=\"custpage_show_processed_fs\"]/img")
	WebElement show_processed;
	@FindBy(xpath = "//td[@id='tdbody_submitter']")
	WebElement process;
	@FindBy(xpath = "//li[@data-title='Inventory Count' and @class='ns-header ns-menuitem ns-submenu']")
	WebElement inventory_count;
	@FindBy(xpath = "//li[@data-title='Inventory Count' and @class='ns-header ns-menuitem ns-menuitem-selected ns-submenu']")
	WebElement inventory_count1;
	@FindBy(xpath = "//li//li[@data-title='Inventory Count']")
	WebElement IC;
	@FindBy(xpath = "//li//li[@data-title='Item Scanning']")
	WebElement IS;
	@FindBy(xpath = "//*[@id=\"custpage_process1_fs\"]/img")
	WebElement item_process;
	@FindBy(xpath = "//input[@id='itemname']")
	WebElement itemname;
	@FindBy(xpath = "//input[@id='submission']")
	WebElement submit_button;
	@FindBy(xpath = "//select[@id='location_1']")
	Select location;
	@FindBy(xpath = "//select[@id='inventorylocation']")
	WebElement location_click;
	@FindBy(xpath = "//select[@id='inventorybin']")
	WebElement bin_click;
	@FindBy(xpath = "//select[@id='bin_1']")
	Select bin;
	@FindBy(xpath = "//textarea[@id='seriallot_1']")
	WebElement serial;
	@FindBy(xpath = "//input[@id='quantity_1']")
	WebElement quantity;
	@FindBy(xpath = "//input[@id='selectrows']")
	WebElement select_rows;
	@FindBy(xpath = "//span[@id='parent_actionbuttons_custpage_accountinfo_fs']")
	WebElement account_arrow;
	@FindBy(xpath = "//a[@id='custpage_accountinfo_popup_list']")
	WebElement account_list;
	@FindBy(xpath = "//input[@id='st']")
	WebElement account_search_input;
	@FindBy(xpath = "//input[@id='Search']")
	WebElement account_search_button;
	@FindBy(xpath = "//div[@id='inner_popup_div']//table//tbody//tr")
	List<WebElement> account_lists;
	@FindBy(xpath = "//span[@id='custpage_haserrors_fs']//img")
	WebElement has_errors;
	@FindBy(xpath = "//td[@id='tdbody_custpage_refresh']")
	WebElement filter;
	@FindBy(xpath = "//table[@id='custpage_list_splits']//tbody//tr[@class='uir-list-row-tr uir-list-row-odd']//td[count( //td[@data-label='error Details']//preceding-sibling::td)+1]")
	WebElement error_message;
	@FindBy(xpath = "//table[@id='custpage_list_splits']//tbody//tr[@class='uir-list-row-tr uir-list-row-odd']//td[count( //td[@data-label='Netsuite Qty']//preceding-sibling::td)]")
	WebElement physcial_quantity;
	@FindBy(xpath = "//table[@id='custpage_list_splits']//tbody//td[@class='uir-list-row-cell listtext']//a")
	WebElement view_link;
	@FindBy(xpath = "//span[@id='custrecord_tss_add_serial_nos_fs_lbl_uir_label']//following-sibling::span")
	WebElement serial_numbers_text;
	@FindBy(xpath = "//span[@id='custrecord_tss_remove_serial_nos_fs_lbl_uir_label']//following-sibling::span")
	WebElement removed_serial_numbers;
	@FindBy(xpath = "//tr[@id='custpage_listrow0']//td//a")
	WebElement processed_view_link;
	@FindBy(xpath = "//span[@id='custrecord_tss_inventory_adj_link_lbl_uir_label']//following-sibling::span")
	WebElement adjustment_link;
	@FindBy(xpath = "//span[@id='custrecord_tss_seraial_lot_no_fs_lbl_uir_label']//following-sibling::span")
	WebElement lot_nrs;

	public ItemScanning() {
		PageFactory.initElements(driver, this);
	}
	public void Inventory_count1() throws InterruptedException {
		Actions action = new Actions(driver);
		Thread.sleep(3000);
		action.moveToElement(inventory_count1).build().perform();
		Thread.sleep(2000);
		action.moveToElement(IC).build().perform();
		Thread.sleep(2000);
		action.moveToElement(IS).build().perform();
		IS.click();

	}
	
	public void Inventory_count() throws InterruptedException {
		Actions action = new Actions(driver);
		Thread.sleep(3000);
		action.moveToElement(inventory_count).build().perform();
		Thread.sleep(2000);
		action.moveToElement(IC).build().perform();
		Thread.sleep(2000);
		action.moveToElement(IS).build().perform();
		IS.click();

	}
	
	public void has_errors(ExtentTest test) throws InterruptedException
	{
		account_arrow.click();
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		has_errors.click();
		filter.click();
		String error = error_message.getText();
		System.out.println("The error is" + error);
		if(!error.equals(""))
		{
			test.pass(error);
		}
		else
		{
			
			test.fail("Test case is failed");
		}
	}
	public void invalid_data(String itemname1, String location1, String bin1, String serial1, String quantity1,
			String testcase,String error_messages,ExtentTest test) throws InterruptedException {
		@SuppressWarnings("deprecation")
		WebDriverWait wait=new WebDriverWait(driver,100);
		itemname.sendKeys(itemname1.trim());
		submit_button.click();
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOf(location_click));
		location_click.click();
		location_click.sendKeys(location1.trim());
		if(!bin1.equals(""))
		{
			bin_click.click();
			bin_click.sendKeys(bin1);
		}
		if (serial.isEnabled()) {
			serial.sendKeys(serial1);
		}

		quantity.clear();
		quantity.sendKeys(quantity1.trim());
		select_rows.click();
		submit_button.click();
		Thread.sleep(10000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		account_arrow.click();
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		has_errors.click();
		filter.click();
		String error = error_message.getText();
		if(error_messages.trim().equals(error.trim()))
		{
			System.out.println("The error message is: "+error);
			test.pass(error);
		}
		else
		{
			test.fail("Displayed error message is incorrect");
		}

	}
	public void processing(String itemname1, String location1, String bin1, String serial1,
			String quantity1, String testcase1,String new_serial,String new_quantity,ExtentTest test) throws InterruptedException
	{
		for(int i=0;i<2;i++)
		{
		if(i==1)
		{
			Inventory_count1();
		}
	
		itemname.sendKeys(itemname1.trim());
		submit_button.click();
		Thread.sleep(10000);
		location_click.sendKeys(location1.trim());
		if (!bin1.trim().equals(""))
		{
			bin_click.click();
			bin_click.sendKeys(bin1.trim());
		}
		if(i==0)
		{
		serial.sendKeys(serial1.trim());
		quantity.clear();
		quantity.sendKeys(quantity1.trim());
		}
		if(i==1)
		{
			serial.sendKeys(new_serial.trim());
			quantity.clear();
			quantity.sendKeys(new_quantity.trim());
		}
		select_rows.click();
		submit_button.click();
		Thread.sleep(10000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		account_arrow.click();
		Thread.sleep(5000);
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		item_process.click();
		process.click();
		Thread.sleep(3000);
		account_arrow.click();
		Thread.sleep(5000);
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		show_processed.click();
		filter.click();
		Thread.sleep(2000);
		show_processed.click();
		account_arrow.click();
		Thread.sleep(5000);
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		Thread.sleep(2000);
		show_processed.click();
		filter.click();
		driver.navigate().refresh();
		Thread.sleep(2000);
		if(i==1)
		{
		processed_view_link.click();
		}
		}

		
	}
	public void bin_mismatch_inventory(String itemname1, String location1, String bin1, String quantity1, String testcase,ExtentTest test)
			throws InterruptedException {
		itemname.sendKeys(itemname1);
		submit_button.click();
		Thread.sleep(3000);
		location_click.click();
		Thread.sleep(1000);
		location_click.sendKeys(location1);
		Thread.sleep(5000);
		bin_click.click();
		bin_click.sendKeys(bin1);
		bin_click.click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		String error = alert.getText();
		alert.accept();
		ExtentTest test1=test.createNode("Invalid bin");
		System.out.println("The error is :" + error);
		test1.pass(error);
		quantity.clear();
		quantity.sendKeys(quantity1);
		select_rows.click();
		submit_button.click();
		Thread.sleep(6000);
		driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);
		ExtentTest test2=test.createNode("Without giving bin testcase");
		has_errors(test2);

		
	}

	public void bin_mismatch_serial(String itemname2, String location2, String bin2, String serial2, String quantity2,String testcase,ExtentTest test1)
			throws InterruptedException {

		itemname.sendKeys(itemname2);
		submit_button.click();
		Thread.sleep(3000);
		location_click.click();
		Thread.sleep(1000);
		location_click.sendKeys(location2);
		bin_click.click();
		bin_click.sendKeys(bin2);
		bin_click.click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		String error = alert.getText();
		alert.accept();
		ExtentTest test=test1.createNode("Invalid bin testcase");
		System.out.println("The error is :" + error);
		test.pass("The error is :" + error);
		Thread.sleep(3000);
		serial.sendKeys(serial2);
		quantity.clear();
		quantity.sendKeys(quantity2);
		select_rows.click();
		submit_button.click();
		Thread.sleep(5000);
		driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);
		ExtentTest test2=test1.createNode("Without giving bin testcase");
		has_errors(test2);

	}

	public void inventory_adjustment_test(String itemname1, String location1, String bin1, String quantity1,
			String testcase,String same_quantity,String greater_quantity,String lesser_quantity,ExtentTest test) throws Exception {
		for(int i=0;i<4;i++)
		{
			if(i>0)
			{
				Inventory_count1();
			}
		itemname.sendKeys(itemname1.trim());
		submit_button.click();
		Thread.sleep(3000);
		location_click.click();
		Thread.sleep(1000);
		location_click.sendKeys(location1.trim());
		if (!bin1.equals("")) {
			bin_click.click();
			bin_click.sendKeys(bin1);
		}
		if(i==0)
		{
		quantity.clear();
		quantity.sendKeys(quantity1);
		}
		if(i==1)
		{
			quantity.clear();
			quantity.sendKeys(same_quantity);
		}
		if(i==2)
		{
			quantity.clear();
			quantity.sendKeys(greater_quantity);
		}
		if(i==3)
		{
			quantity.clear();
			quantity.sendKeys(lesser_quantity);
		}
		select_rows.click();
		submit_button.click();
		Thread.sleep(11000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		account_arrow.click();
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		item_process.click();
		process.click();
		Thread.sleep(2000);
		account_arrow.click();
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		show_processed.click();
		filter.click();
		Thread.sleep(2000);
		show_processed.click();
		account_arrow.click();
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		show_processed.click();
		filter.click();
		driver.navigate().refresh();
		driver.navigate().refresh();
//		if(!(testcase.trim().equals("inventory item creation without bins")) || !(testcase.trim().equals("inventory item creation with bins")))
		if(i>0)
		{
			processed_view_link.click();
			windowHandle();
			String text = adjustment_link.getText();
		if (text.trim().equals(""))
		{	
			System.out.println("Adjustment record is not created because physical quantity and netsuite quantity is same");
				ExtentTest test1=test.createNode("Equal Quantity Testing");
				test1.pass("Adjustment record is not created because physical quantity and netsuite quantity is same");
				driver.close();
				driver.switchTo().window(parentWindow);
		} 
		else 
		{
			System.out.println("The Adjustment record is created with" + text + " " + "number");
			if(i==2)
			{
			ExtentTest test2=test.createNode("Greater Quantity Testing");
			test2.pass("The Adjustment record is created with" + text + " " + "number");
			}
			if(i==3)
			{
				ExtentTest test3=test.createNode("Lesser Quantity Testing");
				test3.pass("The Adjustment record is created with" + text + " " + "number");
			}
			driver.close();
			driver.switchTo().window(parentWindow);

		}
		
		}
		}
	}
	public void serial_items_numbers_adding_with_bins(String itemname1, String location1, String bin1, String serial1,
			String quantity1, String testcase1,String new_serial,String new_quantity,ExtentTest test) throws Exception {

//		if(testcase1.trim().equals("quanity zero with new serial number with bins")||testcase1.trim().equals("quanity zero with existing serial number with bins")||testcase1.trim().equals("new serial numbers adding with bins")||testcase1.trim().equals("new serial numbers adding without bins")||testcase1.trim())
		System.out.println(new_serial);
		if(new_serial!=null)
		{
			processing(itemname1, location1, bin1, serial1, quantity1, testcase1, new_serial, new_quantity, test);
		}
		else
		{
		itemname.sendKeys(itemname1.trim());
		submit_button.click();
		Thread.sleep(10000);
		location_click.sendKeys(location1.trim());
		if (!bin1.trim().equals(""))
		{
			bin_click.click();
			bin_click.sendKeys(bin1.trim());
		}
		serial.sendKeys(serial1.trim());
		quantity.clear();
		quantity.sendKeys(quantity1.trim());
		select_rows.click();
		submit_button.click();
		Thread.sleep(8000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		account_arrow.click();
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		item_process.click();
		process.click();
		Thread.sleep(2000);
		if(testcase1.trim().equals("quanity zero without serial number with bins"))
		{
			System.out.println(" The Physical quantity is set to " +physcial_quantity.getText());
			test.pass(" The Physical quantity is set to " +physcial_quantity.getText());
		}
		account_arrow.click();
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		show_processed.click();
		filter.click();
		Thread.sleep(2000);
		show_processed.click();
		account_arrow.click();
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		Thread.sleep(2000);
		show_processed.click();
		filter.click();
		driver.navigate().refresh();
		Thread.sleep(2000);
		processed_view_link.click();
		}
		windowHandle();
		if(testcase1.trim().equals("new serial numbers adding with bins"))
		{
			String text_serial = serial_numbers_text.getText();
			String remove_text = removal_serial_nrs.getText();
			System.out.println("The new serial numbers" + " " + text_serial + " " + "are added" + " " + "and old serial numbers"
					+ " " + remove_text + " " + " are removed");
			test.pass("The new serial numbers" + " " + text_serial + " " + "are added" + " " + "and old serial numbers"
					+ " " + remove_text + " " + " are removed");
			driver.quit();
			
		}
		if(testcase1.trim().equals("existing and new serial numbers with bins"))
		{
			String text_serial = serial_numbers_text.getText();
			String remove_text = removal_serial_nrs.getText();
			System.out.println("The new serial numbers" + " " + text_serial + " " + "are added" + " " + "and old serial numbers"
					+ " " + remove_text + " " + " are removed");
			test.pass("The new serial numbers" + " " + text_serial + " " + "are added" + " " + "and old serial numbers"
					+ " " + remove_text + " " + " are removed");
			driver.quit();

		}
		if(testcase1.trim().equals("quanity zero with new serial number with bins"))
		{
			String text_serial = serial_numbers_text.getText();
			System.out.println("text is"+text_serial);
			if(text_serial.trim().isEmpty())
			{
				System.out.println("The item is processed succesfully without changing serial number");
				test.pass("The item is processed succesfully without changing serial number");
			}
			else
			{
				test.fail("Test case is failed");
			}
			driver.quit();

		}
			
		if(testcase1.trim().equals("Adding item to bin which is not used by the item before with bins"))
		{
			String bin_text_get=bin_text.getText();
			if(bin_text_get.equals(bin1.trim()))
			{
				System.out.println("The item is added to" +" " +bin1+ " " +"successfully");
				test.pass("The item is added to" +" " +bin1+ " " +"successfully");
				
			}
			else
			{
				test.fail("Item is not added to the bin");
			}
		}

		
		if(testcase1.trim().equals("quanity zero with existing serial number with bins"))
		{
			String remove_text = removal_serial_nrs.getText();
			if(remove_text.equals(serial1.trim()))
			{
				System.out.println("The serial number" +" " +remove_text+" "+"is removed successfully because we are using existing serial number when quantity is zero");
				test.pass("The serial number" +" " +remove_text+" "+"is removed successfully because we are using existing serial number when quantity is zero");
			}
			else
			{
				test.fail("The serial number is not removed");
			}
			driver.quit();

			
		}
		if(testcase1.trim().equals("New serial numbers adding without bins"))
		{
			String text_serial = serial_numbers_text.getText();
			String remove_text = removal_serial_nrs.getText();
			System.out.println("The new serial numbers" + " " + text_serial + " " + "are added" + " " + "and old serial numbers"
					+ " " + remove_text + " " + " are removed");
			driver.quit();
			test.pass("The new serial numbers" + " " + text_serial + " " + "are added" + " " + "and old serial numbers"
					+ " " + remove_text + " " + " are removed");
			
		}
		if(testcase1.trim().equals("Existing and new serial number combination without bins"))
		{
			String text_serial = serial_numbers_text.getText();
			String remove_text = removal_serial_nrs.getText();
			System.out.println("The new serial numbers" + " " + text_serial + " " + "are added" + " " + "and old serial numbers"
					+ " " + remove_text + " " + " are removed");
			driver.quit();
			test.pass("The new serial numbers" + " " + text_serial + " " + "are added" + " " + "and old serial numbers"
					+ " " + remove_text + " " + " are removed");
			
		}
		if(testcase1.trim().equals("Same item with different location and different serial number"))
		{
			String text_serial = serial_numbers_text.getText();
			String remove_text = removal_serial_nrs.getText();
			System.out.println("The new serial numbers" + " " + text_serial + " " + "are added" + " " + "and old serial numbers"
					+ " " + remove_text + " " + " are removed");
			driver.quit();
			test.pass("The new serial numbers" + " " + text_serial + " " + "are added" + " " + "and old serial numbers"
					+ " " + remove_text + " " + " are removed");
			
		}
		if(testcase1.trim().equals("New serial number with quantity zero without bin"))
		{
			String text_serial = serial_numbers_text.getText();
			System.out.println("text is"+text_serial);
			if(text_serial.trim().isEmpty())
			{
				System.out.println("The item is processed succesfully without changing serial number");
				test.pass("The item is processed succesfully without changing serial number");
			}
			else
			{
				System.out.println("Item is not processed");
				test.fail("Item is not processed");
			}
			driver.quit();
		}
		if(testcase1.trim().equals("Existing serial number with quantity zero without bins"))
		{
			String remove_text = removal_serial_nrs.getText();
			if(remove_text.equals(serial1.trim()))
			{
				System.out.println("The serial number" +" " +remove_text+" "+"is removed successfully because we are using existing serial number when quantity is zero");
				test.pass("The serial number" +" " +remove_text+" "+"is removed successfully because we are using existing serial number when quantity is zero");
			}
			else
			{
				test.fail("The serial number is not removed");
			}
			driver.quit();
		}
	}


	public void without_giving_serial_numbers(String itemname1, String location1, String bin1, String serial1,
			String quantity1, String testcase,ExtentTest test) throws InterruptedException {
		itemname.sendKeys(itemname1.trim());
		submit_button.click();
		Thread.sleep(10000);
		location_click.click();
		Thread.sleep(1000);
		location_click.sendKeys(location1.trim());
		if (testcase.trim().equals("without serial number for bin items testing")) {
			bin_click.click();
			bin_click.sendKeys(bin1.trim());
		}
		quantity.click();
		quantity.clear();
		quantity.sendKeys(quantity1.trim());
		select_rows.click();
		submit_button.click();
		Alert alert = driver.switchTo().alert();
		System.out.println("The error message is" + alert.getText());
		String error="The error message is" + alert.getText();
		alert.accept();
		if(!error.equals(""))
		{
			test.pass(error);
		}
		else
		{
			test.fail("Error Message is not displaying");
		}
		

	}

	public void lot_numbers_processing(String itemname1, String location1, String bin1, String serial1, String quantity1,
			 String testcase,String new_lot,String new_quantity,ExtentTest test) throws Exception {
		if(new_lot!=null)
		{
			processing(itemname1, location1, bin1, serial1, quantity1, testcase, new_lot, new_quantity, test);
			
		}
		else
		{
		itemname.sendKeys(itemname1.trim());
		submit_button.click();
		Thread.sleep(3000);
		location_click.click();
		Thread.sleep(1000);
		location_click.sendKeys(location1.trim());
		if (bin_click.isEnabled()) {
			bin_click.click();
			bin_click.sendKeys(bin1.trim());
		}
		serial.sendKeys(serial1.trim());
		quantity.clear();
		quantity.sendKeys(quantity1.trim());
		select_rows.click();
		submit_button.click();
		Thread.sleep(8000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		account_arrow.click();
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		item_process.click();
		process.click();
		Thread.sleep(2000);
		account_arrow.click();
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		show_processed.click();
		filter.click();
		Thread.sleep(2000);
		show_processed.click();
		account_arrow.click();
		account_list.click();
		account_search_input.sendKeys("1000 Checking");
		account_search_button.click();
		Thread.sleep(2000);
		account_lists.get(0).click();
		Thread.sleep(2000);
		Thread.sleep(2000);
		show_processed.click();
		filter.click();
		driver.navigate().refresh();
		Thread.sleep(2000);
		processed_view_link.click();
		}
		windowHandle();
		if (testcase.equals("same lot numbers in different bins")) {
			String lot_nr_text = lotnrs.getText();
			System.out.println(lot_nr_text);
			if (serial1.trim().equals(lot_nr_text)) {
				System.out.println("The item is processed succesfully without errors");
				test.pass("The item is processed succesfully without errors");
			}
			else
			{
				test.fail("Item is not processed");
			}
		} 
		else if (testcase.equals("zero quantity and existing lot number with bins")) 
		{
			String adjust_text = adjustment_link.getText();
			if (!adjust_text.equals(""))
			{
				System.out.println("The item is processed and adjustment record is created with" + " " + adjust_text
						+ " " + "number");
				test.pass("The item is processed and adjustment record is created with" + " " + adjust_text
						+ " " + "number");
			}
			else
			{
				test.fail("Item is not processed when we use zero quantity and existing lot number");
			}
		} 
		else if (testcase.equals("adding lot number item to new location with bin"))
		{
			String adjust_text = adjustment_link.getText();
			if (!adjust_text.equals(""))
			{
				System.out.println("The item is added to the" + " " + location1 + " "
						+ "and adjustment record is created with" + " " + adjust_text + " " + "number");
				test.pass("The item is added to the" + " " + location1 + " "
						+ "and adjustment record is created with" + " " + adjust_text + " " + "number");
			}
			else
			{
				test.fail("The item is not added to the "+location1);
			}
		} 
		else if (testcase.equals("Adding new lot numbers with bins")) 
		{
			String lot_text = lot_nrs.getText();
			System.out.println(lot_text);
			if (lot_text.equals(serial1.trim())) {
				System.out.println("Lot numbers "+lot_text+" " +"are added successfully");
				test.pass("Lot numbers "+lot_text+" " +"are added successfully");
			}
			else
			{
				test.fail("Lot numbers "+lot_text+" " +"are  not added");
			}
		}
		else if (testcase.equals("zero quantity and existing lot number without bins")) 
		{

				String adjust_text = adjustment_link.getText();
				if (!adjust_text.equals(""))
				{
					System.out.println("The item is processed and adjustment record is created with" + " " + adjust_text
							+ " " + "number");
					test.pass("The item is processed and adjustment record is created with" + " " + adjust_text
							+ " " + "number");
				}
				else
				{
					test.fail("Item is not processed when we use  zero quantity and existing lot number");
					
				}
				
		}
		else if(testcase.equals("Adding new lot numbers without bin"))
		{
			String lot_text = lot_nrs.getText();
			System.out.println(lot_text);
			if (lot_text.equals(serial1.trim())) {
				System.out.println("New lot numbers " +lot_text+" "+"are added succesfully");
				test.pass("New lot numbers " +lot_text+" "+"are added succesfully");
			}
			else
			{
				test.fail("New Lot numbers are not added");
			}
		}
		else if(testcase.equals("Adding  lot numbers  to the location without any lot nrs before without bin"))
		{
			String adjust_text = adjustment_link.getText();
			if (!adjust_text.equals(""))
				System.out.println("The lot numbers are added to the" + " " + location1 + " "
						+ "and adjustment record is created with" + " " + adjust_text + " " + "number");
			test.pass("The lot numbers are added to the" + " " + location1 + " "
						+ "and adjustment record is created with" + " " + adjust_text + " " + "number");
			
		}

		 else {
			System.out.println("not a test case");
			test.fail("Lot Numbers are not added to the given location");
		}
	}

	public static void windowHandle() throws Exception {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String currentwindow = driver.getWindowHandle();
		parentWindow = currentwindow;
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> i = allWindows.iterator();
		while (i.hasNext()) {
			String childwindow = i.next();
			if (!childwindow.equalsIgnoreCase(currentwindow)) {
				driver.switchTo().window(childwindow);
			}
		}
	}
	}


