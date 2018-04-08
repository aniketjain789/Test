package docto;

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
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Add_Token
{
	AndroidDriver<WebElement> driver;
	Define d;
	//axy
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
	int row=1,col=12;

	By appoint_label = By.name("Appointments");
	By opd_list = By.id("txt_clinic_name");
	By add_token = By.id("menu_add");
	By opd_cancel_label = By.name("CANCELLED");
	
	By token_label = By.id("actionbar_title_text");
	By token_name = By.id("clinics_addtoken_et_name");
	By cont_no = By.id("clinics_addtoken_et_phoneNo");
	By address_id = By.id("addtoken_address_edt");
	By gender_m = By.id("clinics_addtoken_gender_m");
	By gender_f = By.id("clinics_addtoken_gender_f");
	By age_id = By.id("clinics_addtoken_et_age");
	By fee_aplcbl = By.id("swtch_is_fee_applicable");
	By amount_id = By.id("edt_amount");
	By bal_amount_chkBx = By.id("chk_is_balance_amount");
	By bal_amount = By.id("edt_due_amount");
	By first_visit = By.id("firstVisit");
	By follow_up = By.id("followUpVisit");
	By save = By.id("actionbar_icon_save");
	By cancel = By.id("actionbar_icon_cancel");
	
	By appoint_book = By.id("txt_confirm");
	By token_no = By.id("dialog_addToken_token_no");
	By close = By.id("txt_cancel");
	
	//delete appointment
	By appoit_list_tab = By.name("Appointment List");
	By canl_appoint_buttons = By.id("ripple_lay_iconCancel");
	By delet_ok = By.id("btn_ok");
	int swipe_count;

	
  @Test(dataProvider = "dp")
  public void add_token(String opd_name,String t_name,String contact_no,String address,String gender,String age,String fee_applcbl,String amount,String balance,String bal_amnt,String fist_visit) throws InterruptedException, IOException 
  {	  
	  swipe_count=0;
	  d.API_click(appoint_label);
//////selecting OPD	  
	  List<WebElement> opd = driver.findElements(opd_list);
//	  System.out.println(opd.size());
	  boolean flag = true;
	  do
	  {
		  for(WebElement we:opd)
		  {
			  String temp = we.getText();
//			  System.out.println(temp);
			  if(temp.equalsIgnoreCase(opd_name))
			  {
				  we.click();
				  flag=false;
				  break;
			  }			
		  }
		  if(flag)
		  {Point point = opd.get(opd.size()-1).getLocation();
		  int startX = point.getX();
		  int startY = point.getY();
		  point = opd.get(0).getLocation();
		  int endX = point.getX();
		  int endY = point.getY();
		  System.out.println(startX +" a "+ startY);
		  System.out.println( endX +" b "+ endY);
		  d.swipe(startX, startY, endX, endY);
		  swipe_count++;
		  }
	  	}while(flag);	  
//////selecting OPD
	  d.click(add_token);
	  d.API_click(token_label);
	  d.type(token_name, t_name);
	  d.closekeyboard();
	  d.type(cont_no, contact_no);
	  d.type(address_id, address);
	  
/////gender
	  if(gender.equalsIgnoreCase("Male"))
	  {
		d.click(gender_m);  
	  }
	  else
	  {
		  d.click(gender_f);
	  }
/////gender
//	  int temp = (int) age; 
//	String agetext =   Integer.toString(temp);
	  d.type(age_id, age);
	  d.closekeyboard();
	  
	  if(fee_applcbl.equalsIgnoreCase("Yes"))
	  {
		 d.clear(amount_id);
		 d.type(amount_id, amount);
		 d.closekeyboard();
		 if(bal_amnt.equalsIgnoreCase("Yes"))
		 {
			 d.click(bal_amount_chkBx);
			 d.clear(bal_amount);
			 d.type(bal_amount, bal_amnt);
			 d.closekeyboard();
		 }
	  }
	  else
	  {
		  d.click(fee_aplcbl); 
	  }
	  if(fist_visit.equalsIgnoreCase("Yes"))
	  {
		  d.click(first_visit);
	  }
	  else
	  {
		  d.click(follow_up);
	  }
	  d.click(save);
	  Thread.sleep(2000);
	  if(driver.findElement(appoint_book).getText().equalsIgnoreCase("Appointment booked!"))
	  {
		  System.out.println("Appointment booked");
		  System.out.println("Token "+driver.findElement(token_no).getText());
		  pass();
	  }
	  else
	  {
		  fail("Appointment doesn't book");
	  }
	  d.click(close);
	  cancel_appointment();
	 /* /////////////reset list
	  System.out.println("Reseting");
	  opd = driver.findElements(opd_list);
	  Point point = opd.get(opd.size()-1).getLocation();
	  int startX = point.getX();
	  int startY = point.getY();
	  point = opd.get(0).getLocation();
	  int endX = point.getX();
	  int endY = point.getY();	  
	  
	  for(int i=0;i<=swipe_count;i++)
	  {  
		  d.swipe(endX, endY , startX, startY);
	  }
	  /////////////reset list
*/  }
  public void cancel_appointment() throws InterruptedException
  {
	  System.out.println("Canceling Apoointment");
	  d.click(appoit_list_tab);
	  Thread.sleep(1000);
	  List<WebElement> canclbtn_lst = driver.findElements(canl_appoint_buttons);
	  for(WebElement we : canclbtn_lst)
	  {
		  we.click();
		  try 
		  {
			d.click(delet_ok);
		  } catch (Exception e) 
		  {}
	  }
  }
  @DataProvider
  public Object[][] dp() throws Exception {
/*    return new Object[][] {
      new Object[] { "Kleen Plus", "Test13", "1234567890", "Pune", "Female", "20", "No", "500", "No" , "200", "No"},
//      new Object[] { 2, "b" },
    };*/
	  file = new File("E:\\DoctoPlus\\Docto_Excel_Sheet\\Add_Token.xls");	  
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
	  d.masterLogin(1);
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
	  d.logout();
  }
  	
}
