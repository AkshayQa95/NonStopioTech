package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import test.BaseTest;

public class Amazon_Page extends BaseTest {

	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	WebElement search_text_box;
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	WebElement search_button;
	
	@FindBy(xpath="//span[@class='a-list-item']//span[text()='Analogue']")
	WebElement watch_display_type_analogue_filter;
	
	@FindBy(xpath="//span[@class='a-list-item']//span[text()='Leather']")
	WebElement watchband_material_leather_filter;
	
	@FindBy(xpath="//span[@class='a-list-item']//span[text()='Titan']")
	WebElement brand_titan_filter;
	
	@FindBy(xpath="//span[@class='a-list-item']//span[text()='25% Off or more']//parent::a")
	WebElement discount_filter;
	
	@FindBy(xpath="//div[@data-component-type='s-search-result'][5]")
	WebElement fifth_product_from_list;
	
	@FindBy(xpath="//div[@data-component-type='s-search-result'][8]")
	WebElement eight_product_from_list;
	
	@FindBy(xpath="//span[@class='a-price-whole']")
	WebElement price_of_a_product;
	
	@FindBy(xpath="//input[@id='add-to-cart-button']")
	WebElement product_add_to_cart;
	
	@FindBy(xpath="//a[@id='nav-cart']")
	WebElement check_add_to_cart;
	
	@FindBy(xpath="//*[@id=\"sc-active-955ed21f-8c95-4592-b1dd-3d4f72fa8f00\"]/div[4]/div/div[2]/ul/div/p/span")
	WebElement second_product_price_in_cart;
	
	@FindBy(xpath="//*[@id=\"sc-active-5957b699-e596-48c3-8522-6c91060d1c86\"]/div[4]/div/div[2]/ul/div/p/span")
	WebElement first_product_price_in_cart;
	
	@FindBy(xpath="//span[@id='sc-subtotal-amount-activecart']//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")
	WebElement total_price_in_cart;
	
	@FindBy(xpath="//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']")
	public List<WebElement> individual_product_price;
	
	@FindBy(xpath="//input[@name='proceedToRetailCheckout']")
	WebElement proceed_to_buy_button;
	
	@FindBy(xpath="//i[@class='a-icon a-icon-logo']")
	WebElement homepage_button;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement delete_product_from_cart;
	
	@FindBy(xpath="//h1[contains(text(),'Your Amazon Cart is empty.')]")
	WebElement cart_is_empty_message;
	
	public Amazon_Page(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void applyFillters() throws InterruptedException{
		//Search the "wrist watches" inside the Amazon search box
		search_text_box.sendKeys("wrist watches");
		
		//Click on the Search button.
		search_button.click();
		
		//Select the Analogue watch display
		watch_display_type_analogue_filter.click();
		Thread.sleep(1000);
		
		//Select Leather watchband material
		watchband_material_leather_filter.click();
		Thread.sleep(1000);
		
		//Selecct Titan as brand
		brand_titan_filter.click();
		Thread.sleep(2000);
		
		//Select discount 25% Off or more
		discount_filter.click();
		Thread.sleep(2000);
		
	}
	
	int sumofprice;
	int fifthproductprice;
	int eightproductprice;
	List<Integer> ExpectedIndividalPriceList;
	public void selectFifthAndEigthProduct() throws InterruptedException{
		
		//Capture the Parent WindowID
        String mainwindow = driver.getWindowHandle();
		
        //Select the Fifth Product from the List of Product
		fifth_product_from_list.click();
		
		//Capture all WindowID currently active
		Set<String> allwin = driver.getWindowHandles();
		List<String> allwindowid = new ArrayList<String>(allwin);

		//Use "switchToCorrectWindow" method present in BaseTest class to switch the driver to appropriate window
		switchToCorrectWindow(mainwindow, allwindowid);
		
		//Capture the Price of the Fifth Product and store in "fprice" variable.
		String fprice = price_of_a_product.getText();
		
		//Remove all White spaces, Special characters and alphabets from the "fprice" variable.
		//And convert the String type variable(fprice) to Integer type variable(fifthproductprice).
		fifthproductprice = Integer.parseInt(fprice.replaceAll("\\s+|\\$|,|\\.\\d+",""));
		System.out.println("Price of 5th Product: "+fifthproductprice);
		
		//Add the Fifth Product to cart
		product_add_to_cart.click();
		Thread.sleep(500);
		
		//Use "closeCorrectWindow" method present in BaseTest class to close the appropriate window.
		closeCorrectWindow(mainwindow, allwindowid);
		
		//Switch the driver to parent window.
		driver.switchTo().window(mainwindow);
		
		//Select the Eight Product from the List of Products.
		eight_product_from_list.click();
		
		//Capture all WindowID currently active
		Set<String> allnewwin = driver.getWindowHandles();
		List<String> allnewwindowid = new ArrayList<String>(allnewwin);
		
		//Use "switchToCorrectWindow" method present in BaseTest class to switch the driver to appropriate window
		switchToCorrectWindow(mainwindow, allnewwindowid);
		
		//Capture the Price of the Eight Product and store in "eprice" variable.
		String eprice = price_of_a_product.getText();
		
		//Remove all White spaces, Special characters and alphabets from the "eprice" variable.
		//And convert the String type variable(eprice) to Integer type variable(eightproductprice).
		eightproductprice = Integer.parseInt(eprice.replaceAll("\\s+|\\$|,|\\.\\d+",""));
		System.out.println("Price of 8th Product: "+eightproductprice);
		
		//Add the Eight Product to cart
		product_add_to_cart.click();
		
		//Logic to close the Window
		for(String e : allnewwindowid){
			if(!e.equals(mainwindow)){
				driver.switchTo().window(e).close();
			}
		}
		
		//Switch the driver to parent window.
		driver.switchTo().window(mainwindow);
		
		
	}
	
	public void searchLAndMApparel() {
		
		//Add the Fifth and Eight Product price and store it in a "sumofprice" variable
		sumofprice = fifthproductprice + eightproductprice;
				
		//Create a list of Price of an individual Product(Fifth and Eight product)
		ExpectedIndividalPriceList = new ArrayList<Integer>();
		ExpectedIndividalPriceList.add(fifthproductprice); 
		ExpectedIndividalPriceList.add(eightproductprice);
				
		//Sort the List of Price
		Collections.sort(ExpectedIndividalPriceList);
				
		//Clear the Amazon Search box
		search_text_box.clear();
				
		//Search the "L and M size apparel" inside the Amazon search box
		search_text_box.sendKeys("L and M size apparel");
				
		//Click on the Search button.
		search_button.click();
	}
	
	public void validateIndividualProductPrice() {
		
		//Click on the cart button.
		check_add_to_cart.click();
		
		//Create a list of Price of an individual Product inside the cart.
		List<Integer> ActualIndividualProductPriceList = new ArrayList<Integer>();
		for(WebElement e : individual_product_price){
			
			//Capture the Price of the each Product Present inside the cart and store it in a "priceofeachproductincart" variable.
			String priceofeachproductincart = e.getText();
			
			//Remove all White spaces, Special characters and alphabets from the "priceofeachproductincart" variable.
			//And convert the String type variable(priceofeachproductincart) to Integer type variable(eachproductprice).
			int eachproductprice = Integer.parseInt(priceofeachproductincart.replaceAll("\\s+|\\$|,|\\.\\d+",""));
			ActualIndividualProductPriceList.add(eachproductprice);
		}
        
		//Sort the List of Price
        Collections.sort(ActualIndividualProductPriceList);
		
        //Validate the price of both the products present in the cart with the price before adding the product to cart.
        Assert.assertEquals(ActualIndividualProductPriceList, ExpectedIndividalPriceList);
	}
	
	int totalpricetopay;
	public void validateTotalPriceInCart() {
		
	      //Capture the TotalPrice inside the cart and store it in "totalprice" variable.
			String totalprice = total_price_in_cart.getText();
			
			//Remove all White spaces, Special characters and alphabets from the "totalprice" variable.
			//And convert the String type variable(totalprice) to Integer type variable(totalpricetopay).
			totalpricetopay = Integer.parseInt(totalprice.replaceAll("\\s+|\\$|,|\\.\\d+",""));
			
			//Validate the Total amount to be paid by the user
			Assert.assertEquals(sumofprice, totalpricetopay);
	}
	
	public void validateTotalPriceLessThan3500() {
		
		SoftAssert sa = new SoftAssert();
		//Validate The total amount should not exceed Rs 3500/-
		sa.assertEquals(totalpricetopay <= 3500, true);
		sa.assertAll();
	}
	
	public void validateCartIsEmpty() throws InterruptedException{

		//Click on Proceed to Buy button
		proceed_to_buy_button.click();
		
		//Click on Homepage logo
		homepage_button.click();
		
		//Click on the cart button.
		check_add_to_cart.click();
		
		//Delete the Product Present inside the Cart.
		delete_product_from_cart.click();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",delete_product_from_cart);
		
		Thread.sleep(1000);
		//Validate that the cart is empty
		Assert.assertEquals(cart_is_empty_message.isDisplayed(), true);
		
	}
	
	
}
