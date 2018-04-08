package medicine;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Attendants.Define;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Medicine_add {

	static Logger log = Logger.getLogger(Medicine_add.class.getName());

	AndroidDriver<WebElement> driver;
	Define d;
	

	By drawer = By.className("android.widget.ImageButton");
	By medicine_tab = By.id("item_name");
	By add_button = By.id("menu_madd");

	By medicinename = By.id("medicine_name_edt");
	By medicinetype = By.id("medicine_type_edt");

	By sunhalf = By.id("btn_sunhalf");
	By sunfull = By.id("btn_sunfull");
	By btnmoon = By.id("btn_moon");

	By swipenone = By.id("swipe_none");
	By swipebfood = By.id("swipe_before_food");
	By swipeafood = By.id("swipe_after_food");

	By q_plus = By.id("q_plus_img");
	By q_minus = By.id("q_minus_img");

	By d_plus = By.id("d_plus_img");
	By d_minus = By.id("d_minus_img");

	By unit = By.id("medicine_quantity_edt");
	By days = By.id("medicine_days_edt");

	By editnotes = By.id("edittext_notes");
	boolean notes_flag = true;

	By savebtn = By.id("actionbar_icon_save");

	// next activity
	By mediname = By.id("innomationtech.doctoplus.doctor:id/medicine_name");
	By medicontainer = By.id("medicine_list");
	By txtday = By.id("txt_days");
	// del
	By del = By.id("icon_delete");
	By delete_yes = By.id("btn_ok");
	By cancel = By.id("actionbar_icon_cancel");
	By m_list = By.id("medicine_name");
	By search_box = By.id("edt_search");
	By medi_list = By.id("medicine_name");
	By ok = By.id("btn_ok");
	By del_btn = By.id("icon_delete");
	By serach_btn = By.id("icon_search");
	// for list storing purpose
	List<String> medicine_list = new ArrayList<String>();
	List<String> medicinetype_list = new ArrayList<String>();

	By title = By.id("toolbar");

	@DataProvider
	public Object[][] Add_medicine() {
		return new Object[][] { new Object[] { "abcd12", "Aplicaps", "e", "before", "3/5", "Drink hot water" } };
	}

	@DataProvider
	public Object[][] dp_Edit_medicine() {
		return new Object[][] { new Object[] { "abcd3", "Aplicaps", "e", "after", "4/5", "Drink hot water" } };
	}

	@Test(dataProvider = "dp_Edit_medicine", priority = 2)
	public void Edit_medicine(String m_name, String m_type, String time, String when, String unit_days, String Notes)
			throws InterruptedException, IOException {

		System.out.println("medicinetype_list.size() :" + medicinetype_list.size());
		for (int i = 0; i < medicinetype_list.size(); i++) {
			log.info("visit Edit_medicine...!");
			System.out.println(medicinetype_list);
			// d.type(serach_btn, medicinetype_list.get(i));
			System.out.println("i th value :" + i);
			String medi_name = medicinetype_list.get(i);
			
			log.info("to find element...!");
			System.out.println(medi_name);
			open_tab();
			int count = 0;
			boolean flag1 = true;
			do {
				List<WebElement> m_list = driver.findElements(medi_list);

				for (WebElement we : m_list) {
					String temp = we.getText();
					System.out.println(temp + ":" + count);

					if (temp.equalsIgnoreCase(medi_name)) {

						System.out.println("clicking " + count);
						Thread.sleep(2000);
						m_list.get(count).click();
						
						snapshot();
						flag1 = false;
						break;
					}
					count++;
				}
				if (flag1) {
					Point point = m_list.get(m_list.size() - 1).getLocation();
					int startX = point.getX();
					int startY = point.getY();
					point = m_list.get(0).getLocation();
					int endX = point.getX();
					int endY = point.getY();
					d.swipe(startX, startY, endX, endY);
				}

			} while (flag1);
			
		}
		System.out.println("out of the LOOP.....!");
		// medicine name
				d.clear(medicinename);
				d.type(medicinename, m_name);
				Thread.sleep(1000);
				d.click(medicinename);
				medicinetype_list.removeAll(medicinetype_list);
				
				
				
				

				// medcine type
				d.clear(medicinetype);
				d.type(medicinetype, m_type);

				String arr1[] = time.split("/");
				for (int i = 0; i < arr1.length; i++) {
					switch (arr1[i]) {
					case "m":
						d.click(sunhalf);
						break;
					case "a":
						d.click(sunfull);
						break;
					case "e":
						d.click(btnmoon);
						break;

					}
					switch (when) {
					case "none":
						d.click(swipenone);
						break;
					case "after":
						d.click(swipeafood);
						break;
					case "before":
						d.click(swipebfood);
						break;
					}

				}

				String arr2[] = unit_days.split("/");
				// unit
				d.click(unit);
				d.clear(unit);
				d.type(unit, arr2[0]);
				d.closekeyboard();

				// days
				d.click(days);
				d.clear(days);
				d.type(days, arr2[1]);
				d.closekeyboard();

				// notes
				if (notes_flag == true) {
					log.info("I am in notes condition");
					if (Notes.equalsIgnoreCase("None")) {
						d.click(editnotes);
						d.type(editnotes, Notes);
						d.closekeyboard();
						notes_flag = false;
					} else {
						log.info("I am in else part of else condition");
						d.type(editnotes, Notes);
					}
				}
				log.info("leaved if-else condition");
				System.out.println(Notes);
				d.closekeyboard();
				Thread.sleep(1000);
				// assertion( m_name);
				log.info("clicking on save button");
				Thread.sleep(1000);
				medicinetype_list.add(m_name + " " + "(" + m_type + ")");
				d.click(savebtn);
				log.info("clicked on save button");

	}

	@Test(dataProvider = "Add_medicine",priority=1)
	public void Add_medicine(String m_name, String m_type, String time, String when, String unit_days, String Notes)
			throws InterruptedException, IOException {
		log.info("I am in test_Report method");

		d.API_click(drawer);
		snapshot();
		Thread.sleep(1000);
		List<WebElement> s = driver.findElements(By.id("item_name"));
		s.get(2).click();

		d.click(add_button);

		// medicine name
		d.clear(medicinename);
		d.type(medicinename, m_name);
		Thread.sleep(1000);
		d.click(medicinename);
		//medicine_list.add(m_name);

		// medcine type
		d.clear(medicinetype);
		d.type(medicinetype, m_type);

		String arr1[] = time.split("/");
		for (int i = 0; i < arr1.length; i++) {
			switch (arr1[i]) {
			case "m":
				d.click(sunhalf);
				break;
			case "a":
				d.click(sunfull);
				break;
			case "e":
				d.click(btnmoon);
				break;

			}
			switch (when) {
			case "none":
				d.click(swipenone);
				break;
			case "after":
				d.click(swipeafood);
				break;
			case "before":
				d.click(swipebfood);
				break;
			}

		}

		String arr2[] = unit_days.split("/");
		// unit
		d.click(unit);
		d.clear(unit);
		d.type(unit, arr2[0]);
		d.closekeyboard();

		// days
		d.click(days);
		d.clear(days);
		d.type(days, arr2[1]);
		d.closekeyboard();

		// notes
		if (notes_flag == true) {
			log.info("I am in notes condition");
			if (Notes.equalsIgnoreCase("None")) {
				d.click(editnotes);
				d.type(editnotes, Notes);
				d.closekeyboard();
				notes_flag = false;
			} else {
				log.info("I am in else part of else condition");
				d.type(editnotes, Notes);
			}
		}
		log.info("leaved if-else condition");
		System.out.println(Notes);
		d.closekeyboard();
		Thread.sleep(1000);
		// assertion( m_name);
		log.info("clicking on save button");
		Thread.sleep(1000);
		d.click(savebtn);
		log.info("clicked on save button");

		log.info("call check method");
		medicinetype_list.add(m_name + " " + "(" + m_type + ")");

	}// test

	public void open_tab() throws InterruptedException {
		d.API_click(drawer);
		Thread.sleep(1000);
		List<WebElement> s = driver.findElements(By.id("item_name"));
		s.get(2).click();// medicine
	}

	public void delete_medicine(String medi_name) throws InterruptedException, IOException {
		log.info("visit Delete method...!");
		System.out.println(medi_name);
		open_tab();
		int count = 0;
		boolean flag1 = true;
		do {
			List<WebElement> m_list = driver.findElements(medi_list);
			List<WebElement> del_list = driver.findElements(del_btn);

			for (WebElement we : m_list) {
				String temp = we.getText();
				System.out.println(temp + ":" + count);

				if (temp.equalsIgnoreCase(medi_name)) {

					System.out.println("clicking " + count);
					// System.out.println("element found
					// :"+m_list.get(count).getText());
					Thread.sleep(2000);
					del_list.get(count).click();

					// m_list.get(count).click();
					d.click(ok);
					snapshot();
					flag1 = false;
					break;
				}
				count++;
			}
			if (flag1) {
				Point point = m_list.get(m_list.size() - 1).getLocation();
				int startX = point.getX();
				int startY = point.getY();
				point = m_list.get(0).getLocation();
				int endX = point.getX();
				int endY = point.getY();
				d.swipe(startX, startY, endX, endY);
			}

		} while (flag1);

	}

	@AfterClass
	public void afterClass() throws InterruptedException, IOException {
		log.info("after class.........!");
	
		System.out.println("medicinetype_list.size() :" + medicinetype_list.size());
		for (int i = 0; i < medicinetype_list.size(); i++) {
			log.info("after class for loop...!");
			System.out.println(medicinetype_list);
			// d.type(serach_btn, medicinetype_list.get(i));
			System.out.println("i th value :" + i);
			System.out.println("medicinetype_list.get(i) :" + medicinetype_list.get(i));
			delete_medicine(medicinetype_list.get(i));

		}
		Thread.sleep(2000);
		// d.logout();
	}

	public void snapshot() throws IOException {
		WebDriver driver1 = new Augmenter().augment(driver);
		File file1 = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file1, new File("E:\\Excel sheet\\snapshot" + ".png"));
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

}// class

/*
 * @DataProvider public Object[][] dp() throws Exception {
 * 
 * return new Object[][] { // new Object[] { "vepan","Aplicaps" , "e","before",
 * "3/5", "Drink hot water" }, // new Object[] { "Aspirin","Capsule" ,
 * "m","after", "3/5", "Take a rest after lunch" }, }; }
 */
