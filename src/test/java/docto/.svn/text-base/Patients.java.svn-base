	package docto;

import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

public class Patients 
{
	AndroidDriver<WebElement> driver;
	Define d;
	File file;
	FileOutputStream fos;
	FileInputStream fis;
	HSSFCellStyle style;
	HSSFFont ifPass, ifFail;	
	HSSFWorkbook wrkbook ;
	HSSFSheet sheet;
	HSSFRow shRow;
	HSSFCell shCells;
	String shname;
	int rCount, cCount;
	int row=1,col=7;
	String nm;
	
	By drawer = By.className("android.widget.ImageButton");
	By patients_tab = By.name("Patients");
	
	By add_button = By.id("menu_padd");
	
	By title = By.id("actionbar_title_text");
	By personal_info_tab = By.id("lay_details_title_layout");
	By grp_name = By.id("edtGroup");
	By p_name = By.id("txtPatientName");
	By m_gen = By.id("clinics_addtoken_gender_m");
	By f_gen = By.id("clinics_addtoken_gender_f");
	
	By age_id = By.id("txtAge");
	By contact_no = By.id("txtContactNumber");
	By address_id = By.id("txtAddress");
	
	By allergy_box = By.id("edtAllergi");
	By allergy_add = By.id("btnadd");
	By save_btn = By.id("actionbar_icon_save");
	By cancel = By.id("actionbar_icon_cancel");
	
	By search_box = By.id("edt_search");
	By search_result_patient_list = By.id("txtPatientName");
	By search_result_delete_btn = By.id("icon_delete");
	By delete_yes = By.id("btn_ok");
	
	List<String> patient_list = new ArrayList<String>();
	
	@Test(dataProvider = "dp")
  public void patients(String grpName, String gender, String age, String con_no, String address, String allergy) throws InterruptedException, IOException 
  {
	  d.click(add_button);
	  String arr[] = grpName.split("-");
	  if(arr.length>1)
	  {
		  d.click(grp_name);
		  d.type(grp_name, arr[0]);
		  d.closekeyboard();
		  d.click(p_name);
		  d.type(p_name, arr[1]);
		  nm = arr[1];
		  d.closekeyboard();
//		  patient_list.add(arr[1]);
	  }
	  else
	  {
		  d.click(p_name);
		  d.type(p_name, arr[0]);
		  d.closekeyboard();
//		  patient_list.add(arr[0]);
		  nm = arr[0];
	  }
	  
	  ////////////////Gender
	  if(gender.equalsIgnoreCase("Female"))
	  {
		  d.click(f_gen);
	  }
	  else
	  {
		  d.click(m_gen);
	  }
	  
	  //Age
	  d.click(age_id);
	  d.type(age_id, age);
	  d.closekeyboard();
	  
	  d.click(contact_no);
	  d.type(contact_no, con_no);
	  d.closekeyboard();
	  
	  d.click(address_id);
	  d.type(address_id, address);
	  d.closekeyboard();
	  
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
		  pass();
		  patient_list.add(nm);
	} catch (Exception e) 
	  {
		d.click(delete_yes); //yes button on already exist popup
		Thread.sleep(1000);
		d.click(cancel);
		fail(nm + ": Already Exist");
	}
	  
  }
  public void delete_patient() throws InterruptedException
  {
//	  d.click(search_box);
	  System.out.println(patient_list);
	  for(int i=0; i<patient_list.size();i++)
	  {
		  d.click(search_box);
		  d.type(search_box, patient_list.get(i));
		  d.closekeyboard();
		  Thread.sleep(1500);
		  
		  List<WebElement> res_p_list = driver.findElements(search_result_patient_list);
		  List<WebElement> res_delet_btn = driver.findElements(search_result_delete_btn);
		  
		  int k=0;
		  for(WebElement we : res_p_list)
		  {
			  String temp = we.getText();
			  if(temp.equalsIgnoreCase(patient_list.get(i)))
			  {
				  res_delet_btn.get(k).click();
				  d.click(delete_yes);				  
				  break;
			  }
			  else
			  {
				  k++;
			  }
		  }
		  d.API_click(search_box);
		  d.clear(search_box);		  
	  }	  
  }
  
  @DataProvider
  public Object[][] dp() throws Exception 
  {
  /*  return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };*/
	  
	  file = new File("E:\\DoctoPlus\\Docto_Excel_Sheet\\Patient.xls");	  
	  fis = new FileInputStream(file);
	  wrkbook = new HSSFWorkbook(fis);
	  shname = wrkbook.getSheetName(0);
	  sheet = wrkbook.getSheet(shname);
//	  System.out.println(shname);
	  Thread.sleep(2000);	  
	  rCount = sheet.getPhysicalNumberOfRows()-1;  //1
	  cCount = sheet.getRow(0).getPhysicalNumberOfCells()-2; //2
//	  System.out.println(rCount +" "+ cCount);
	  Object[][] data = new Object [rCount][cCount];	  
	  for(int i=0;i<rCount;i++)
	  {		  
		  shRow = sheet.getRow(i+1);		  
		  for(int j=0;j<cCount;j++)
		  {
			  shCells = shRow.getCell(j+1);
			  data[i][j] = shCells.getStringCellValue();		 
		  }
	  }
//		  fis.close();
	  return data;	
  }
  @BeforeClass
  public void beforeClass() throws IOException, InterruptedException 
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
	  d.API_click(drawer);
	  d.click(patients_tab);
  }
  public void pass() throws IOException, InterruptedException
  {
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
		//logout();	  
  }
  public void fail(String message) throws IOException
  {
	    fos = new FileOutputStream(file);
	    style= wrkbook.createCellStyle();
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
  public void afterClass() throws InterruptedException
  {
	  delete_patient();
	  d.logout();
  }

}
