package Mode.Mode;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Attendants.Define;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Attendant_Mode {
	
	static Logger log = Logger.getLogger(Attendant_Mode.class.getName());
	AndroidDriver<WebElement> driver;
	Define d;
	By Appoinment_tab = By.xpath("//android.widget.TextView[@text='Appointment List']");
	By opentab = By.className("android.widget.ImageButton");
	By tab_list = By.id("item_name");
	
	By attendent_icon = By.id("attendent_icon");
	By display_icon = By.id("display_icon");
	By kiosk_icon = By.id("kiosk_icon");
	
	
	
	
	@Test(enabled=false)
	public void Attendant() throws InterruptedException, IOException, ParseException {

		log.info("Visit Attendant Mode.....!");
		Thread.sleep(2000);
		d.click(opentab);
		List<WebElement> list = driver.findElements(tab_list);
		Thread.sleep(2000);
		list.get(4).click();// mode
		d.click(attendent_icon);


	}

	
	@Test(enabled=false)
	public void Display() throws InterruptedException, IOException, ParseException {

		log.info("Visit Attendant Mode.....!");
		Thread.sleep(2000);
		d.click(opentab);
		List<WebElement> list = driver.findElements(tab_list);
		Thread.sleep(2000);
		list.get(4).click();// mode
		d.click(display_icon);
	}

	
	@Test
	public void Kiosk() throws InterruptedException, IOException, ParseException {

		log.info("Visit Kiosk Mode.....!");
		Thread.sleep(2000);
		d.click(opentab);
		List<WebElement> list = driver.findElements(tab_list);
		Thread.sleep(2000);
		list.get(4).click();// mode
		d.click(kiosk_icon);
		


	}
	
	
	
	
	
	@BeforeClass()
	public void beforeClass() throws InterruptedException, IOException {
		DesiredCapabilities capab = new DesiredCapabilities();

		capab.setCapability("BROWSER_NAME", "Android");
		capab.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
		capab.setCapability("VERSION", "4.4.2");
		capab.setCapability("deviceName", "Itg Guru (SM-G350E)");
		capab.setCapability("platformName", "Android");
		capab.setCapability("appPackage", "innomationtech.doctoplus.doctor");
		capab.setCapability("appActivity", "activity.LoginActivity");
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capab);
		d = new Define(driver);
		// d.device_permission();

		// d.masterLogin1();

	}// beforeclass
}
