package keywordFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.beust.jcommander.IValueValidator;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Keyword {

	public static void openBrowser(String bName) {
		switch (bName) {
		case "Chrome":
			// System.setProperty("webdriver.chrome.driver","E:\\Selenium\\ChromeDriver\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			Constants.driver = new ChromeDriver();
			break;
		case "IE":
			// System.setProperty("webdriver.ie.driver","E:\\Selenium\\Driver\\IEDriverServer_x64_3.6.0\\IEDriverServer.exe");
			Constants.driver = new InternetExplorerDriver();
			break;
		}
	}

	public static void getURL(String sURL) {
			Constants.driver.get(sURL);
			Constants.driver.manage().window().maximize();
			Constants.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	}

	public static void enterText(String lType, String lValue, String textEnter){
		switch (lType) {
		case "xpath":
			//Constants.driver.findElement(By.xpath("//*[@id='skill']/div[1]/div[2]/input")).sendKeys("selenium");
			Constants.driver.findElement(By.xpath(lValue)).sendKeys(textEnter);
			break;
		case "css":
			Constants.driver.findElement(By.cssSelector(lValue)).sendKeys(textEnter);
			break;
		default:
			System.out.println("Invalid locator type");
			break;
		}
	}
	
	public static void clickOnElement(String lType, String lValue){
		switch (lType) {
		case "xpath":
			//Constants.driver.findElement(By.xpath("//*[@id='skill']/div[1]/div[2]/input")).sendKeys("selenium");
			Constants.driver.findElement(By.xpath(lValue)).click();
			break;
		case "css":
			Constants.driver.findElement(By.cssSelector(lValue)).click();
			break;
		default:
			System.out.println("Invalid locator type");
			break;
		}
	}
	
	public static String getElementText(String lType, String lValue) {
		String textString=null;
		switch (lType) {
		case "xpath":
			//Constants.driver.findElement(By.xpath("//*[@id='skill']/div[1]/div[2]/input")).sendKeys("selenium");
			textString=Constants.driver.findElement(By.xpath(lValue)).getText();
			break;
		case "css":
			textString=Constants.driver.findElement(By.cssSelector(lValue)).getText();
			break;
		default:
			System.out.println("Invalid locator type");
			break;
		}
		return textString;
	}

	public static void navigateBack() {
		Constants.driver.navigate().back();
	}
	public static void closeBrowser() {
		Constants.driver.quit();
	}
}
