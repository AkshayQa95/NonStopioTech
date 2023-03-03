package test;

import java.io.File;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class Amazon_Test extends BaseTest {
	
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeTest
	public void startReport() {
		extent = new ExtentReports("C:\\Users\\Lenovo\\eclipse-workspace\\Problem3\\test-output\\MyExtentReport.html",true);
		test = extent.startTest("TestName");
		extent.addSystemInfo("Host Name", "LocalHost");
	    extent.addSystemInfo("Environment", "QA");
	    extent.addSystemInfo("User Name", "Akshay");
		extent.loadConfig(new File("C:\\Users\\Lenovo\\eclipse-workspace\\Problem3\\extend-config.xml"));
				
	}
	
	
	@Test(priority=1)
	public void apply_Fillters() throws InterruptedException{
		test = extent.startTest("'Apply Filters' Test case pass");
		AmazonPage.applyFillters();	
		test.log(LogStatus.PASS, "Assert Pass as condition is True");
	}
	
	@Test(priority=2)
	public void select_Fifth_And_EigthProduct() throws InterruptedException{
		test = extent.startTest("'Select Fifth And Eigth Product' Test case pass");
		AmazonPage.selectFifthAndEigthProduct();
		test.log(LogStatus.PASS, "Assert Pass as condition is True");
	}
	
	@Test(priority=3)
	public void search_L_And_M_Apparel() throws InterruptedException{
		test = extent.startTest("'Search L And M Apparel' Test case pass");
		AmazonPage.searchLAndMApparel();
		test.log(LogStatus.PASS, "Assert Pass as condition is True");
	}
	
	@Test(priority=4)
	public void validate_Individual_Product_Price() throws InterruptedException{
		test = extent.startTest("'Validate Individual Product Price' Test case pass");
		AmazonPage.validateIndividualProductPrice();
		test.log(LogStatus.PASS, "Assert Pass as condition is True");
	}
	
	@Test(priority=5)
	public void validate_Total_Price_In_Cart() throws InterruptedException{
		test = extent.startTest("'Validate Total Price In Cart' Test case pass");
		AmazonPage.validateTotalPriceInCart();
		test.log(LogStatus.PASS, "Assert Pass as condition is True");
	}
	
	@Test(priority=6)
	public void validate_Total_Price_Less_Than_3500() throws InterruptedException{
		test = extent.startTest("'Validate Total Price Less Than 3500' Test case pass");
		AmazonPage.validateTotalPriceLessThan3500();
		test.log(LogStatus.PASS, "Assert Pass as condition is True");
	}
	
	@Test(priority=7)
	public void validate_Cart_Is_Empty() throws InterruptedException{
		test = extent.startTest("'Validate Cart Is Empty' Test case pass");
		AmazonPage.validateCartIsEmpty();
		test.log(LogStatus.PASS, "Assert Pass as condition is True");
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getThrowable());
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed");
		}
		extent.endTest(test);
	}
	
	@AfterTest
	public void endreport() {
		extent.flush();
	}
	

}
