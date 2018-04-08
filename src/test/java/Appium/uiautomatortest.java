package Appium;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class uiautomatortest {

	AndroidDriver<WebElement> driver;
	DesiredCapabilities capab = new DesiredCapabilities();
	Define d;

	@Test(priority = 1, enabled = true)
	public void uiautomator() throws InterruptedException, IOException {
		
		// driver.findElementByAndroidUIAutomator("attribute("value")");
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();

		// driver.findElementsByAndroidUIAutomator("new
		// UiSelector().property(Value)");
		System.out.println(driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size());

	}

	@BeforeClass
	public void beforeClass() throws InterruptedException, MalformedURLException {
		capab = new DesiredCapabilities();
		capab.setCapability("BROWSER_NAME", "Android");
		capab.setCapability("VERSION", "4.4.2");
		capab.setCapability("deviceName", "Itg Guru (SM-G350E)");
		capab.setCapability("platformName", "Android");
		capab.setCapability("appPackage", "io.appium.android.apis");
		capab.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		Thread.sleep(2000);
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capab);
		d = new Define(driver);
	}

}
