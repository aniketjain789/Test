package Attendants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Login {
	File file;
	FileOutputStream fos;
	FileInputStream fis;
	HSSFCellStyle style;
	HSSFFont ifPass, ifFail;
	HSSFWorkbook wrkbook;
	HSSFSheet sheet;
	HSSFRow shRow;
	HSSFCell shCells;
	String shname;
	int rCount, cCount;
	int row = 1, col = 3;

	AndroidDriver<WebElement> driver;
	Define d;

	By username = By.id("login_et_username");
	By pwd = By.id("login_et_password");

	By pwd_cont = By.id("login_password_container");

	By login_btn = By.id("login_button_container");

	By appointment = By.name("Appointments");
	By drawer = By.className("android.widget.ImageButton");
	By logout = By.name("Logout");

	@Test(dataProvider = "dp")
	public void test(String username_text, String pwd_text) throws InterruptedException, IOException {
		int pwd_size = pwd_text.length();
		d.API_click(username);
		d.type(username, username_text);
		d.closekeyboard();
		d.type(pwd, pwd_text);
		d.closekeyboard();
		Thread.sleep(1000);
		d.click(login_btn);
		Thread.sleep(6000);
		try {
			d.click(appointment);
			System.out.println("Login Success");
			pass();
			//d.logout();

		} catch (Exception e) {
			System.out.println("Fail");
			d.clear(username);
			d.click(pwd);
			for (int i = 0; i < pwd_size; i++)
				System.out.println(pwd_size);
			{
				d.clear(pwd_cont);
			}
			fail("Invalid");
		}
	}

	@DataProvider
	public Object[][] dp() throws Exception {
		/*
		 * return new Object[][] { new Object[] { "Rohit5", "abc" }, new
		 * Object[] { "Rohi5", "abc" }, };
		 */
		file = new File("E:\\DoctoAttendants\\login.xls");
		fis = new FileInputStream(file);
		wrkbook = new HSSFWorkbook(fis);
		shname = wrkbook.getSheetName(0);
		sheet = wrkbook.getSheet(shname);
		// System.out.println(shname);
		Thread.sleep(2000);
		rCount = sheet.getPhysicalNumberOfRows() - 1; // 1
		cCount = sheet.getRow(0).getPhysicalNumberOfCells() - 2; // 2
		// System.out.println(rCount +" "+ cCount);
		Object[][] data = new Object[rCount][cCount];
		for (int i = 0; i < rCount; i++) {
			shRow = sheet.getRow(i + 1);
			for (int j = 0; j < cCount; j++) {
				shCells = shRow.getCell(j + 1);
				// data[i][j] = shCells.getStringCellValue();
				// System.out.println(shCells.getStringCellValue());
				data[i][j] = shCells.getStringCellValue();
			}
		}
		// fis.close();
		return data;
	}

	@BeforeClass
	public void beforeClass() throws MalformedURLException, InterruptedException {
		DesiredCapabilities capab = new DesiredCapabilities();
		capab.setCapability("BROWSER_NAME", "Android");
		capab.setCapability("VERSION", "4.4.2");
		capab.setCapability("deviceName", "Itg Guru (SM-G350E)");
		capab.setCapability("platformName", "Android");
		capab.setCapability("appPackage", "innomationtech.doctoplus.doctor");
		capab.setCapability("appActivity", "activity.LoginActivity");
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capab);
		d = new Define(driver);
		Thread.sleep(5000);
		d.device_permission();
		// device_permission();
	}

	public void pass() throws IOException, InterruptedException {
		fos = new FileOutputStream(file);
		style = wrkbook.createCellStyle();
		ifPass = wrkbook.createFont();

		ifPass.setBold(true);
		ifPass.setColor(HSSFColor.GREEN.index);

		shRow = sheet.getRow(row);
		shCells = shRow.createCell(col);

		shCells.setCellValue("Pass");
		style.setFont(ifPass);
		shCells.setCellStyle(style);

		wrkbook.write(fos);
		row++;
		fos.close();
		// logout();
	}

	public void fail(String message) throws IOException {
		fos = new FileOutputStream(file);
		style = wrkbook.createCellStyle();
		ifFail = wrkbook.createFont();

		ifFail.setBold(true);
		ifFail.setItalic(true);
		ifFail.setColor(HSSFColor.RED.index);

		shRow = sheet.getRow(row);
		shCells = shRow.createCell(col);
		shCells.setCellValue("Fail:- " + message);
		style.setFont(ifFail);
		shCells.setCellStyle(style);

		wrkbook.write(fos);
		row++;
		fos.close();
	}

	@AfterClass
	public void afterClass() {

	}

}
