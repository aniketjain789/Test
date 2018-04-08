	package docto;

import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class Patients_update 
{
	AndroidDriver<WebElement> driver;
	Define d;

	By drawer = By.className("android.widget.ImageButton");
//	By patients_tab = By.name("Patients");
	By buttons_list = By.id("item_name");

	By search_box = By.id("edt_search");
	By search_result_patient_list = By.id("txtPatientName");
	
//	By info_tab = By.name("Generic Info");
	By groupName_id = By.id("edtGroup");
	By p_name_id = By.id("txtPatientName");
	By m_gen = By.id("clinics_addtoken_gender_m");
	By f_gen = By.id("clinics_addtoken_gender_f");
	
	By age_id = By.id("txtAge");
	By contact_no = By.id("txtContactNumber");
	By address_id = By.id("txtAddress");
	
	By allergy_box = By.id("edtAllergi");
	By allergy_add = By.id("btnadd");
	By save_btn = By.id("actionbar_icon_save");
	By cancel = By.id("actionbar_icon_cancel");
	
	By pers_info_tab = By.id("lay_details_title_layout");
	By mem_img = By.id("member_img");
	
  @Test(dataProvider = "dp")
  public void update_patient(String p_name, String grp_name, String name, String gender, String age, String cont_no, String addrss, String allergy) throws InterruptedException 
  {
	  open_tab();
	  d.API_click(search_box);
	  d.clear(search_box);
	  d.type(search_box, p_name);
	  d.closekeyboard();
	  Thread.sleep(1000);
	  d.click(search_result_patient_list);
	  
//	  d.API_click(info_tab);
	  /////////for aws
	  List<WebElement> tabs = driver.findElements(By.id("tab_text"));
	  System.out.println(tabs.size());
	  tabs.get(0).click();  
	  
	  /////////for aws
	  
	  d.click(groupName_id);
//	  d.clear(groupName_id);
	  d.type(groupName_id, grp_name);
	  d.closekeyboard();
	  
	  d.click(p_name_id);
//	  d.clear(p_name_id);
	  d.type(p_name_id, name);
	  d.closekeyboard();
	  
	  
	  if(gender.equalsIgnoreCase("Female"))
	  {d.click(f_gen);}
	  else
	  {d.click(m_gen);}
	  
	  Point point = driver.findElement(m_gen).getLocation(); 
	  int startX = point.getX();
	  int startY = point.getY();
	  point = driver.findElement(pers_info_tab).getLocation();
	  int endX = point.getX();
	  int endY = point.getY();
	  d.swipe(startX, startY, endX, endY);
	  
	  d.click(age_id);
//	  d.clear(age_id);
	  d.type(age_id, age);
	  d.closekeyboard();
	  
	  d.click(contact_no);
//	  d.clear(contact_no);
	  d.type(contact_no, cont_no);
	  d.closekeyboard();
	  	  
	  d.click(address_id);
	  
	  point = driver.findElement(m_gen).getLocation(); 
	  startX = point.getX();
	  startY = point.getY();
	  point = driver.findElement(mem_img).getLocation();
	  endX = point.getX();
	  endY = point.getY();
	  d.swipe(startX, startY, endX, endY);
	  
//	  d.clear(address_id);
	  d.type(address_id, addrss);
	  d.closekeyboard();
	  
	  Thread.sleep(1200);
	  point = driver.findElement(mem_img).getLocation(); 
	  startX = point.getX();
	  startY = point.getY();
	  point = driver.findElement(m_gen).getLocation();
	  endX = point.getX();
	  endY = point.getY();
	  d.swipe(startX, startY, endX, endY);
	  
	  d.click(pers_info_tab);
	  
	  if(!allergy.equalsIgnoreCase("No"))
	  {
		  String arr1[] = allergy.split(",");		  
		  for(int i=0;i<arr1.length;i++)
		  {
			  d.click(allergy_box);
			  d.type(allergy_box, arr1[i]);
			  d.click(allergy_add);
		  }		  
		  d.closekeyboard();
	  }
	  d.click(save_btn);
	  Thread.sleep(3000);
	  
	  try 
	  {
		  d.click(cancel);
//		  pass();		  
	} catch (Exception e) 
	  {		
		d.click(cancel);
//		fail(nm + ": Already Exist");
	}
	  
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] {"akbar","Testt", "akbar", "Female", "29", "1324657980", "pune", "no"},      
//      new Object[] {"temp","grp_name", "Sita", "Female", "29", "1324657980", "pune", "alcohol,non"},      

    };
  }
  @BeforeClass
  public void beforeClass() throws Exception 
  {
	  DesiredCapabilities capab = new DesiredCapabilities();	  	
	  capab.setCapability("BROWSER_NAME","Android");
	  capab.setCapability("VERSION","4.4.2");
	  capab.setCapability("deviceName","Itg Guru (SM-G350E)");
	  capab.setCapability("platformName","Android");	  
	  capab.setCapability("appPackage","innomationtech.doctoplus.doctor");
	  capab.setCapability("appActivity","activity.LoginActivity"); 
	  driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capab);
	  d = new Define(driver);
	  d.masterLogin(3);
//	  d.API_click(drawer);
//	  d.click(patients_tab);
  }
  private void open_tab() throws InterruptedException
  {
	  d.API_click(drawer);	  		  
	  List<WebElement> btn_list =driver.findElements(buttons_list);
	  System.out.println(btn_list.size());		
	  btn_list.get((1)).click();
	  Thread.sleep(800);
  }

  @AfterClass
  public void afterClass() throws InterruptedException 
  {
	  d.logout();
  }

}
