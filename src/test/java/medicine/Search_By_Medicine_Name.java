package medicine;

import java.io.IOException;
import java.net.URL;
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

public class Search_By_Medicine_Name {

	static Logger log = Logger.getLogger(Search_By_Medicine_Name.class.getName());

	AndroidDriver<WebElement> driver;
	Define d;

	By drawer = By.className("android.widget.ImageButton");
	By opentab = By.className("android.widget.ImageButton");
	By tab_list = By.id("item_name");
	By Medicine_name = By.id("medicine_name");
	By edt_search = By.id("edt_search");

	@DataProvider
	public Object[][] dp_Search_medicine() {
		return new Object[][] { new Object[] { "Decold" } };
	}

	@Test(dataProvider = "dp_Search_medicine")
	public void add_new_prescription_For_Patient(String medi_name) throws InterruptedException, IOException {

		log.info("add_new_prescription_For_Patient.....!");
		Thread.sleep(2000);
		d.click(opentab);
		List<WebElement> list = driver.findElements(tab_list);
		Thread.sleep(2000);
		list.get(2).click();// Patient
		d.type(edt_search, medi_name);

	}

	@BeforeClass
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
		// d.masterLogin(7);

	}// beforeclass

}
