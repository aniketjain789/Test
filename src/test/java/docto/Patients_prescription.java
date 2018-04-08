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

public class Patients_prescription 
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
	int row=1,col=10;
	
	By drawer = By.className("android.widget.ImageButton");
	By patients_tab = By.name("Patients");
	
	By search_box = By.id("edt_search");
	By search_result_patient_list = By.id("txtPatientName");
	
	By add_btn = By.id("actionbar_icon_add");
	By pres_text = By.name("Prescription");
	
	By date_bx = By.id("prsc_date_txt");
	By date_ok = By.id("ok");
	
	By bp_bx = By.id("bp_txt");
	By bp_systo = By.id("txt_max_patient_count_systolic");
	By bp_diasto = By.id("txt_max_patient_count_diastolic");
	By systo_plus = By.id("icon_plus_systolic");
	By systo_miunus = By.id("icon_minus_systolic");
	By diato_plus = By.id("icon_plus_diastolic");
	By diato_miunus = By.id("icon_minus_diastolic");
	By bp_ok = By.id("btn_ok");
	
	By select_opd = By.id("icon_arrow_down");
	By opd_list = By.id("text1");
	
	By medicine_name = By.id("edittext_medicinename");
	
	By morning = By.id("btn_sunhalf");
	By after = By.id("btn_sunfull");
	By night = By.id("btn_moon");
	
	By none = By.id("swipe_none");	
	By before_food = By.id("swipe_before_food");
	By after_food = By.id("swipe_after_food");
	
	By right_btn = By.id("btn_right");
	
	By unit = By.id("medicine_quantity_edt");
	By days = By.id("medicine_days_edt");
	
	By additional_details = By.id("edt_additional_note");
	By notes_id = By.id("edittext_notes");
	
	By action_save = By.id("actionbar_icon_save");
	By action_back_btn = By.id("actionbar_icon_back");
	
	By prescription_list = By.xpath("//android.widget.RelativeLayout[@index='0']");
	
	By action_close = By.id("actionbar_icon_cancel");
	
	boolean notes_flag = true,flag= true;
	String old_pName="xxx"; 
  @Test(dataProvider = "dp")
  public void prescrip(String p_name, String bp, String opd_name, String medi_name, String time, String when, String unit_days, String addi_details, String notes) throws Exception 
  {	  
	  
	  if(old_pName.equalsIgnoreCase(p_name))
	  {
		  flag=false;		  
	  }
	  else
	  {
		  flag=true;
		  notes_flag = true;		  
		  if(!old_pName.equalsIgnoreCase("xxx"))
		  {
			save_prescription();
		  }
		  
	  }
	  
  while(flag)
	{
	  d.API_click(search_box);
	  d.clear(search_box);
	  d.type(search_box, p_name);
	  Thread.sleep(2000);
	  
	  d.click(search_result_patient_list);
	  
	  d.API_click(pres_text);
	  d.click(add_btn);
	  Thread.sleep(1500);
	  d.click(date_bx);
	  Thread.sleep(1000);
//	  String year = driver.findElement(By.id("date_picker_year")).getText();
//	  driver.findElement(By.xpath("//android.view.View[@index='2']")).click();
	  d.click(date_ok);
	  d.click(bp_bx);
	  
	  String arr[] = bp.split("/");
	  
	  int systollic = Integer.parseInt(driver.findElement(bp_systo).getText()); //120
	  int diastolic = Integer.parseInt(driver.findElement(bp_diasto).getText());//80
	  
	  int loop = Integer.parseInt(arr[0])-systollic;	
	  if(loop>0)
	  { loop(systo_plus,0, loop); }
	  else
	  { loop(systo_miunus, loop, 0); } 
	  loop = Integer.parseInt(arr[1])-diastolic;	  
	  if(loop>0)
	  { loop(diato_plus,0, loop); }
	  else
	  { loop(diato_miunus, loop, 0); }	  
	  
	  d.click(bp_ok);
	  d.click(select_opd);
	  Thread.sleep(2000);
	 
	  driver.findElement(By.name(opd_name)).click();
	  flag= false;
  }
	  ////Medicine 
	  d.click(medicine_name);
	  d.type(medicine_name, medi_name);
	  d.closekeyboard();
	  
//	  m,a,n
	  String arr1[] = time.split("/");	  
	  for(int i=0;i<arr1.length;i++)
	  {		   
		  switch (arr1[i])
		  {
		  	case "m": d.click(morning);			
			break;
		  	case "a": d.click(after);			
			break;
		  	case "n": d.click(night);			
			break;
		  }
	  }
	  switch (when)
	  {
	  	case "none": d.click(none);			
		break;
	  	case "after": d.click(after_food);			
		break;
	  	case "before": d.click(before_food);			
		break;
	  }
	  
	  String arr2[] = unit_days.split("/");
	  
	  d.click(unit);
	  d.type(unit, arr2[0]);
	  d.closekeyboard();
	  
	  d.click(days);
	  d.type(days, arr2[1]);
	  d.closekeyboard();
	  
	  if(!addi_details.equalsIgnoreCase("None"))
	  {
		  d.click(additional_details);
		  d.type(additional_details, addi_details);
		  d.closekeyboard();
	  }
	  
	  if(notes_flag)
	  {
	  if(!notes.equalsIgnoreCase("None"))
	  {
		  d.click(notes_id);
		  d.type(notes_id, notes);
		  d.closekeyboard();
		  notes_flag=false;
	  }
	  }
	  
	  d.click(right_btn);

	  
	  // medicine	  
	  old_pName = p_name;
	  
  }

@DataProvider
  public Object[][] dp() throws Exception {
/*    return new Object[][] {
    	new Object[] { "Helan", "121/79", "Om Sai Clinic", "Vepan 250", "m/a/n", "after", "5/4", "nothing", "None" },
        new Object[] { "Helan", "119/81", "Om Sai Clinic", "Vepan 500", "a", "before", "5/4", "Additional", "None" },
        new Object[] { "Nana", "121/88", "Opd", "Addifalm", "a/n", "before", "3/4", "Additional pp", "Notess" },
    };*/
	  file = new File("E:\\DoctoPlus\\Docto_Excel_Sheet\\Patient_prescription.xls");	  
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
	  d.API_click(drawer);
	  d.click(patients_tab);
  }
  
  private void save_prescription() throws InterruptedException, IOException 
  {
	  try 
	  {
		d.click(bp_ok);
		Thread.sleep(1500);
	} catch (Exception e) {}
	  
  	  d.click(action_save);
  	  Thread.sleep(2000);
  	  try 
  	  {
  	  	  d.click(action_back_btn);
  	  } catch (Exception e) 
  	  {
			Thread.sleep(3000);
	  	  d.click(action_back_btn);
  	  }
  	  Thread.sleep(1500);	  
  	  List<WebElement> prs_lst = driver.findElements(prescription_list);
//  	  System.out.println(prs_lst.size());	  
  	  if(prs_lst.size()>8)
  	  {
  		  System.out.println("pass");
  		  pass();
  	  }
  	  d.click(action_close);	
  }
  private void loop(By locator,int scount, int ecount) throws InterruptedException
  {
	  for(int i=scount;i<ecount;i++)
	  {
		d.click(locator);
		Thread.sleep(100);
	  }	  
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
  public void afterClass() throws InterruptedException, IOException 
  {
	  save_prescription();
	  d.logout();	  
  }

}
