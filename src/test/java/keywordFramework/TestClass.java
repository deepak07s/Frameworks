package keywordFramework;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utility.Property;

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
		Keyword.enterText("xpath", Property.getProperty("email"),"deepak07.s@gmail.com");
		Keyword.enterText("xpath", Property.getProperty("pwd"),"seleniumPwd");
		Keyword.clickOnElement("xpath", "//*[@id='u_0_b']");
		String actualVal=Keyword.getElementText("xpath", "//*[@id='header_block']/span/div/div[1]/div[2]/span");
		String expectedVal="Log in as Deepak Shitole";
		Assert.assertEquals(actualVal, expectedVal);
	}
	
	@Test
	public void Test3() {
		Keyword.navigateBack();
		
		Keyword.clickOnElement("xpath","//*[@id='login_form']/table/tbody/tr[3]/td[2]/div/a");
		String actualTitle=Constants.driver.getTitle();
		String expectedTitle="Facebook – log in or sign up";
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	@AfterTest
	public void closeBrowser() {
		Keyword.closeBrowser();
	}
	
	
	

}
