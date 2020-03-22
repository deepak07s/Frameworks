package keywordFramework;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClass {

	@BeforeTest
	public void openBrowser() {
		Keyword.openBrowser("Chrome");
		Keyword.getURL("https://www.facebook.com");
	}
	
	@Test
	public void Test1() {
		String actualTitle=Constants.driver.getTitle();
		String expectedTitle="Facebook – log in or sign up";
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Test
	public void Test2() throws InterruptedException {
		Keyword.enterText("xpath", "//*[@id='email']","deepak07s@gmail.com");
		Keyword.enterText("xpath", "//*[@id='pass']","seleniumPwd");
		Keyword.clickOnElement("xpath", "//*[@id='u_0_b']");
		String actualVal=Keyword.getElementText("xpath", "//*[@id='header_block']/span/div/div[1]/div[2]/span");
		String expectedVal="Log in as Deepak Shitole";
		Assert.assertEquals(actualVal, expectedVal);
	}
	@AfterTest
	public void closeBrowser() {
		Keyword.closeBrowser();
	}
	
	
	

}
