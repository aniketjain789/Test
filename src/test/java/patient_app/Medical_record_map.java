package patient_app;

import java.net.URL;
import java.util.List;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import docto.Define;
import io.appium.java_client.android.AndroidDriver;

public class Medical_record_map 
{
	AndroidDriver<WebElement> driver;
	Define d;
	//drawer
	By drawer = By.className("android.widget.ImageButton");
	By buttons_list = By.id("item_name");
	
	By bottom_tab = By.id("tab_text");
	By menu_filter = By.id("menu_filter");
	By menu_filter_list = By.id("rbt_family_member");
	
	//map
	By map_edit_btn = By.id("icon_img_edit");
	By map_mem_list = By.id("member_name");
	By map_ok_canl_btn = By.className("android.widget.Button");
	
	
	
  @Test
  public void map_test() throws InterruptedException 
  {
	  open_tab();
	  Thread.sleep(800);
	  System.out.println("All Records");
	  List<WebElement> list = driver.findElements(bottom_tab);
	  
	  list.get(0).click();
	  map();	  
	  list.get(1).click();
	  map();
	  list.get(2).click();
	  map();	  
	  list.get(0).click();
	  System.out.println("Indidual Records");

	  d.click(menu_filter);
	  List<WebElement> filtr_list = driver.findElements(menu_filter_list);
	  filtr_list.get(2).click();
	  
	  map();
	  list.get(1).click();
	  map();	  
	  list.get(2).click();
	  map();	  
	  list.get(0).click();
	  
  }
  private void map() throws InterruptedException
  {
	  d.click(map_edit_btn);
	  Thread.sleep(500);
	  
	  List<WebElement> mem_list = driver.findElements(map_mem_list);
	  List<WebElement> btn = driver.findElements(map_ok_canl_btn);
	  
	  try {
		mem_list.get(1).click();
		btn.get(1).click();		
	} catch (Exception e) 
	  {
		System.out.println("Members are not available");
	}
	
  }
private void  open_tab() throws InterruptedException
  {
	  	d.click(drawer);	  
	  	List<WebElement> btn_list = driver.findElements(buttons_list);
//		System.out.println(btn_list.size());		
		btn_list.get((2)).click();
		Thread.sleep(800);
  }
  @BeforeClass
  public void beforeClass() throws Exception 
  {
	  DesiredCapabilities capab = new DesiredCapabilities();	  	
	  capab.setCapability("BROWSER_NAME","Android");
	  capab.setCapability("VERSION","4.4.2");
	  capab.setCapability("deviceName","Itg Guru (SM-G350E)");
	  capab.setCapability("platformName","Android");	  
	  capab.setCapability("appPackage","innomationtech.doctoplus.patient");
	  capab.setCapability("appActivity","activity.MainActivity"); 
	  driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capab);
	  d = new Define(driver);
//	  d.API_click(add_new);
//	  open_tab();
	    
	}

  @AfterClass
  public void afterClass() 
  {
	  driver.quit();
  }

}
