package keywordFramework;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utility.Property;

public class TestClass {

	@BeforeTest
	public void openBrowser() {
		Keyword.openBrowser("Chrome");
		Keyword.getURL(Property.getProperty("url"));
	}

	// To verify the title of the Amazon Home Page
	@Test
	public void Test1() throws IOException {
		String actualTitle = Constants.driver.getTitle();
		System.out.println(actualTitle);
		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		Property.captureScreenshot();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	// To verify title after clicking on Customer Service link
	@Test
	public void Test2() {
		// Keyword.clickOnElement("", lValue);
		Keyword.clickOnElement(Property.getProperty("customerSrevice"));
		String actualTitle = Constants.driver.getTitle();
		String expectedTitle = "Amazon.in Help: Help";
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	// To verify Shipping & Delivery in Help Topics section is enable when mouse
	// hover on it
	@Test
	public void Test3() throws IOException, ParseException {
		Keyword.mouseHoverSpecificElement(Property.getProperty("bckToTop"));
		Keyword.mouseHoverSpecificElement(Property.getProperty("shippingAndDelivery"));
		Property.captureScreenshot();
		String status = Keyword.getAttributeValue(Property.getProperty("shippingAndDelivery"), "class");
		String actualText = Keyword.getTextOfElement(Property.getProperty("shippingAndDelivery"));

		Assert.assertEquals(status, "active");
		Assert.assertEquals(actualText, "Shipping & Delivery");
	}

	// To verify all the expected values of Recommended Topics section is present when mouse hover on it
	@Test
	public void Test4() throws IOException, ParseException {
		Keyword.mouseHoverSpecificElement(Property.getProperty("recommendedTopics"));
		Keyword.explicitWait(Property.getProperty("recommendedTopics"), "class", "active", 20);
		List<WebElement> allElement = Keyword.getSubElements(Property.getProperty("subChildRecommendedTopics"));
		
		String[] expectedVal = Property.readJSON("Recommended Topics");
		Property.captureScreenshot();
		Boolean exist = false;
		for (String s1 : expectedVal) {
			for (int i = 0; i < allElement.size(); i++) {
				WebElement childrenElement = allElement.get(i);
				String s = childrenElement.getText();
				if (s1.equals(s)) {
					exist = true;
					break;
				}
			}
			Assert.assertTrue(exist);
			exist = false;
		}
	}

	@AfterTest
	public void closeBrowser() {
		Keyword.closeBrowser();
	}

}
