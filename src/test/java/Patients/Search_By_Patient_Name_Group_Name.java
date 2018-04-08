package Patients;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Attendants.Define;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Search_By_Patient_Name_Group_Name {
	static Logger log = Logger.getLogger(Delete_Patient_details.class.getName());
	AndroidDriver<WebElement> driver;
	Define d;
	By opentab = By.className("android.widget.ImageButton");
	By tab_list = By.id("item_name");
	By search = By.id("edt_search");
	
	@DataProvider
	public Object[][] dp_search_By_groupname() {
		return new Object[][] { new Object[] { "Rto" } };
	}
	
	@DataProvider
	public Object[][] dp_search_By_name() {
		return new Object[][] { new Object[] { "Ankush Katkar" } };
	}

	@Test(dataProvider = "dp_search_By_groupname", priority = 1, enabled = true)
	public void Search_By_Grp_name(String grp_name) throws ParseException, InterruptedException {
		d.click(opentab);
		List<WebElement> list = driver.findElements(tab_list);
		Thread.sleep(2000);
		list.get(1).click();// petiants
		Thread.sleep(1000);
		d.type(search, grp_name);
		
}

	@Test(dataProvider = "dp_search_By_name", priority = 2, enabled = true)
	public void Search_Byname(String name) throws ParseException, InterruptedException {
		d.click(opentab);
		List<WebElement> list = driver.findElements(tab_list);
		Thread.sleep(2000);
		list.get(1).click();// petiants
		Thread.sleep(1000);
		d.type(search, name);
		
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