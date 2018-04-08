package patient_app;

import org.testng.annotations.Test;
import docto.Define;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.List;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;

public class Family_member_add 
{
	AndroidDriver<WebElement> driver;
	Define d;
		
	By bottm_tab = By.id("tab_text");
	By menu_btn = By.xpath("//android.widget.ImageButton[@index='0']");
	
	//drawer
	By drawer = By.className("android.widget.ImageButton");
	By buttons_list = By.id("item_name");
	
	
	//add member
	By add_btn = By.id("menu_add");
	By mem_name = By.id("member_name_edt");
	By mem_mob_no = By.id("member_mobile_edt");
	By mem_gen_f = By.id("female");
	By mem_gen_m = By.id("male");
	By mem_dob = By.id("lay_date");
	By dob_cal = By.id("numberpicker_input");
	By dob_done = By.id("button1");
	By dob_close = By.id("button2");
	By relation = By.id("text_spiner");
	By done_btn = By.id("btn_add");
	By added_text = By.id("txt_message");
	By add_anthr_mem = By.id("btn_add_another_member");
	By get_strted = By.id("btn_get_started");
	
	By ok_btn = By.id("btn_ok");
	
	By set_date_panel = By.className("android.widget.Button");
	
	//mob chcek
	By edit_btn = By.id("edit_icon");
	By num_edit = By.id("iconEdit");
	By can_btn = By.id("btn_cancel");
	
	
	  @Test(priority=1,enabled=true)
	  public void mob_number_check() throws InterruptedException
	  {
		  open_tab();
		  Thread.sleep(1500);
		  List<WebElement> mem_list = driver.findElements(edit_btn);
		  mem_list.get(0).click();
		  Thread.sleep(1000);
		  d.click(num_edit);
		  Thread.sleep(2000);
		  d.click(can_btn);
		  Thread.sleep(200);
		  d.device_back();
		  Thread.sleep(200);
		  d.device_back();
	  }
  
  @Test(priority=2,enabled=true)
  public void add_member() throws InterruptedException 
  {
	  open_tab();
	  Thread.sleep(800);
	  d.click(add_btn);
	  d.API_click(mem_name);
	  d.type(mem_name, "Pandu");
	  d.type(mem_mob_no, "9658965896");
	  d.click(mem_gen_m);
	  d.click(mem_dob);
	  d.click(dob_close);
	  d.click(mem_dob);
	  Thread.sleep(800);	  

	  set_date();
	  
	  d.click(dob_done);	  
	  d.click(relation);
	  Thread.sleep(800);
	  
	  List<WebElement> relation_list = driver.findElements(relation);
	  relation_list.get(6).click();	 
	  
	  d.click(done_btn);
	  
	  try {
		  d.click(ok_btn);
	  } catch (Exception e) 
	  {}
	  
	  Thread.sleep(5000);
	  try {
		  System.out.println(d.get_text(added_text));
		  d.click(get_strted);
	} catch (Exception e) 
	  {
		System.err.println("Not Added");
	}	  
	  Thread.sleep(800);
	  d.device_back();
  }  
  
  public void set_date() throws InterruptedException  
  {
	  Thread.sleep(1000);
	  boolean flag = true;
	  String temp;
	  List<WebElement> sel_val = driver.findElements(dob_cal);

	  List<WebElement> btn = driver.findElements(By.className("android.widget.NumberPicker"));
	  System.out.println("Axy 1 "+btn.size());
	  
	  List<WebElement> sub_btn = btn.get(0).findElements(By.className("android.widget.Button"));
	  System.out.println("Axy 2 "+sub_btn.size());
	  
	  int x = sub_btn.get(0).getLocation().getX();
	  int y = sub_btn.get(0).getLocation().getY();	  
	  int x1 = (sub_btn.get(1).getLocation().getX())/2;
	  int y1 = sub_btn.get(1).getLocation().getY()/2;
	  	  
	  do
	  {		  
		  temp = sel_val.get(0).getText();
		  if(temp.equalsIgnoreCase("may"))
		  {
			  flag=false;
		  }
		  else
		  {
			  d.swipe(x, y, x1, y1);
			  Thread.sleep(1000);
		  }
		  
	  }while(flag);
	  sel_val.get(1).clear();
	  Thread.sleep(800);
	  sel_val.get(1).sendKeys("18");
	  Thread.sleep(800);
	  sel_val.get(2).clear();
	  Thread.sleep(800);
	  sel_val.get(2).sendKeys("1990");
	  d.device_back();
	  sel_val.get(2).click();

  }
  
  
  private void  open_tab() throws InterruptedException
  {
	  	d.click(drawer);	  
	  	List<WebElement> btn_list = driver.findElements(buttons_list);
//		System.out.println(btn_list.size());		
		btn_list.get((1)).click();
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
  public void afterMethod() 
  {
	  driver.quit();
  }

}
