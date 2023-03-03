package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import main.Amazon_Page;

public class BaseTest {

	public static WebDriver driver;
	
	protected Amazon_Page AmazonPage;
	
	@BeforeClass
	public void launchBrowser(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
	}
	
	
	@BeforeClass
	public void pageObjects(){
		AmazonPage = new Amazon_Page(driver);
	}
	
	
	public void switchToCorrectWindow(String mainwindow, List<String> allwindowid){
		for(String e : allwindowid){
			if(!e.equals(mainwindow)){
				driver.switchTo().window(e);
				
			}
		}
	}
	
	public void closeCorrectWindow(String mainwindow, List<String> allwindowid){
		for(String e : allwindowid){
			if(!e.equals(mainwindow)){
				driver.switchTo().window(e).close();
			}
		}
	}
	
	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File target = new File("C:\\Users\\Lenovo\\eclipse-workspace\\Problem3\\test-output" + System.currentTimeMillis()
		+ ".png");
		String errflpath = target.getAbsolutePath();
		FileUtils.copyFile(scrFile, target);
		return errflpath;
		}

	
}
