package keywordFramework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.beust.jcommander.IValueValidator;

import Utility.Property;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Keyword {

	/**
	 * To open the browser
	 * @param bName Provide the browser type like Chrome, Firefox etc
	 */
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

	/**
	 * To open the particular URL
	 * @param sURL
	 */
	public static void getURL(String sURL) {
		Constants.driver.get(sURL);
		Constants.driver.manage().window().maximize();
		Constants.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	/**
	 * To enter the text in particular text field
	 * @param strVal details of search text field
	 * @param textEnter Text to enter
	 */
	public static void enterText(String strVal, String textEnter) {
		
		String[] str = null;
		str = strVal.split("##");
		String lType = str[0];
		String lValue = str[1];
		
		switch (lType) {
		case "xpath":
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

	/**
	 * To click on particular Webelement
	 * @param strVal Provide Locator type and its value
	 */
	public static void clickOnElement(String strVal) {

		String[] str = null;
		str = strVal.split("##");
		String lType = str[0];
		String lValue = str[1];

		switch (lType) {
		case "xpath":
			Constants.driver.findElement(By.xpath(lValue)).click();
			break;
		case "css":
			Constants.driver.findElement(By.cssSelector(lValue)).click();
			break;
		case "linkText":
			Constants.driver.findElement(By.linkText(lValue)).click();
			break;
		default:
			System.out.println("Invalid locator type");
			break;
		}
	}
	
	public static Boolean elementPresent(String strVal) {
		String[] str = null;
		str = strVal.split("##");
		String lType = str[0];
		String lValue = str[1];

		Boolean elmntPresent=false;
		switch (lType) {
		case "xpath":
			elmntPresent=Constants.driver.findElement(By.xpath(lValue)).isDisplayed();
			break;
		case "css":
			elmntPresent=Constants.driver.findElement(By.cssSelector(lValue)).isDisplayed();
			break;
		case "linkText":
			elmntPresent=Constants.driver.findElement(By.linkText(lValue)).isDisplayed();
			break;
		default:
			System.out.println("Invalid locator type");
			break;
		}
		return elmntPresent;
	}

	public static void scrollBottomDown() {
		Constants.js = (JavascriptExecutor) Constants.driver;
		Constants.js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	/**
	 * Mouse hover to specific WebElement on Webpage
	 * 
	 * @param elementVal
	 *            Provide the locator type and its value seperated by '##'
	 */
	public static void mouseHoverSpecificElement(String elementVal) {

		String[] str = null;
		str = elementVal.split("##");
		String lType = str[0];
		String lValue = str[1];
		WebElement element = null;

		Actions action = new Actions(Constants.driver);
		switch (lType) {
		case "xpath":
			// Constants.driver.findElement(By.xpath("//*[@id='skill']/div[1]/div[2]/input")).sendKeys("selenium");
			element = Constants.driver.findElement(By.xpath(lValue));

			break;
		case "css":
			element = Constants.driver.findElement(By.cssSelector(lValue));
			break;
		case "linkText":
			element = Constants.driver.findElement(By.linkText(lValue));
			break;
		default:
			System.out.println("Invalid locator type");
			break;
		}
		action.moveToElement(element).perform();
	}

	public static String getAttributeValue(String elementVal, String attribute) {
		String[] str = null;
		str = elementVal.split("##");
		String lType = str[0];
		String lValue = str[1];

		String attValue = Constants.driver.findElement(By.xpath(lValue)).getAttribute(attribute);
		return attValue;
	}

	public static List<WebElement> getSubElements(String elementVal) {
		String[] str = null;
		str = elementVal.split("##");
		String lType = str[0];
		String lValue = str[1];

		List<WebElement> subElements = Constants.driver.findElements(By.xpath(lValue));
		return subElements;
	}

	public static void explicitWait(String elementVal, String attribute, String expectedAttVal, int waitTime) {
		String[] str = null;
		str = elementVal.split("##");
		String lType = str[0];
		String lValue = str[1];

		Constants.wait = new WebDriverWait(Constants.driver, waitTime);
		Constants.wait.until(ExpectedConditions.attributeContains(Constants.driver.findElement(By.xpath(lValue)),
				attribute, expectedAttVal));
	}

	public static String getTextOfElement(String elementVal) {

		String[] str = null;
		str = elementVal.split("##");
		String lType = str[0];
		String lValue = str[1];
		String elementText = Constants.driver.findElement(By.xpath(lValue)).getText();
		return elementText;
	}
	
	public static boolean verifyElementPresentDropdown(String strVal,String srchVal ) {
		
		String[] str = null;
		str = strVal.split("##");
		String lType = str[0];
		String lValue = str[1];
		
		WebElement element=Constants.driver.findElement(By.xpath(lValue));
		Select select=new Select(element);
		List<WebElement> allValues=select.getOptions();
		Boolean found=false;
		for(int i=0; i<allValues.size(); i++) {
		    if(allValues.get(i).equals(srchVal)) {
		        found=true;
		        break;
		    }
		}
		return found;
	}

	public static void navigateBack() {
		Constants.driver.navigate().back();
	}

	/**
	 * To Quit the existing browser instantiate by Webdriver
	 */
	public static void closeBrowser() {
		Constants.driver.quit();
	}
}
