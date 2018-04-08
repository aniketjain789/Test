package pages;
import java.util.List;
import java.util.Random;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.readexcel;

public class listing_vintage 
{
	public static HSSFRow Row;
	public static HSSFCell cell;
	public static HSSFSheet Sheet;
	public static String FilePath = "C://Users//Aanchali//workspace//sanity links//data.xls";
	public static String SheetName = "signup";
    public static String fullDate= "12/08/2017";
    public static String file="tata12.pdf";
  
	public static void main(String[] args) throws Exception 
	{
		WebDriver driver=new FirefoxDriver();
		   
		driver.get("http://qa6.secure.droom.in/user/login"); 
		driver.manage().window().maximize();
		Sheet = readexcel.DataSheet(FilePath, SheetName);
		
		String username = Sheet.getRow(1).getCell(0).getStringCellValue(); /*.toString();*/
		String password = Sheet.getRow(1).getCell(1).getStringCellValue(); /*.toString();*/
		
		System.out.println(username+" "+password); 
		
        Random v= new Random();
		
		WebElement ele= driver.findElement(By.id("email"));
		WebElement el1e= driver.findElement(By.id("password"));
	    ele.sendKeys(username);  
	    el1e.sendKeys(password);  

	//    WebElement  login= driver.findElement(By.xpath("html/body/div[2]/div/div/div/div/form/div/div[4]/input"));  // click on link	   	    
	    WebElement login= driver.findElement(By.xpath(".//*[@id='signUpButton']/input"));
	    login.click();
	  
       //click on My regular listings
	  
       Thread.sleep(9000);
       System.out.println(driver.findElement(By.cssSelector(".active>a")).isDisplayed());
       WebElement elemnt= driver.findElement(By.xpath("html/body/div[2]/div[2]/section/div[2]/div[1]/div[1]/ul/li[3]/a"));
       elemnt.click();

       System.out.println("i am able to click");

	//Create listing
      WebElement create= driver.findElement(By.xpath("html/body/div[2]/div/section/div[1]/div[2]/a[1]")); //create regular listings
   	 create.click();
   	
	    New_listingwizardTest_vintage nl= new New_listingwizardTest_vintage();
	      nl.f(driver); 
	      
 //  Main First Page || click on exterior color
     
     List<WebElement> exterior= driver.findElements(By.xpath(".//*[@id='typ_861']/div/label"));
     int ext= v.nextInt(exterior.size());
     exterior.get(ext).click(); 
     
     // click on interior colour
         List<WebElement> interior= driver.findElements(By.xpath(".//*[@id='typ_221']/div[1]/label"));
         int inter= v.nextInt(interior.size());
         interior.get(inter).click();
  
         
     // click on save and continue  
         JavascriptExecutor jse22 = (JavascriptExecutor) driver;  
         jse22.executeScript("window.scrollBy(0,250)");  
         
      Thread.sleep(5000);      
      
  System.out.println(driver.findElement((By.xpath(".//*[@id='frm-Basic_Facts']/div/div/div/div[2]/input"))).isDisplayed());

//   driver.findElement(By.cssSelector(".btn.btn-lg.btn-block.btn-primary.submit")).click();

     WebDriverWait waitsave = new WebDriverWait(driver,30);  
     WebElement save1 = waitsave.until(
     ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='frm-Basic_Facts']/div/div/div/div[2]/input")));  

         save1.click();
    
      // Second Page || Key Factors
    
       Thread.sleep(5000);
       
       //location
     WebElement location=  driver.findElement(By.xpath(".//*[@id='location']"));
     location.sendKeys("Gurgaon");
       
     // Enter Registration number
     WebElement reg= driver.findElement(By.xpath(".//*[@id='vehicle_registration_number']"));
     reg.sendKeys("HR35L3873");

     // Enter Registration state
         Select state = new Select(driver.findElement(By.id("registration_state"))); 
         List<WebElement> states=state.getOptions();
   	   
         Random rm= new Random();
         int is= rm.nextInt(states.size());   
 
           if(is==0)
           {
        	   is++;
    		   System.out.println("Yes, we got Day i.e first element");
           }
               state.selectByIndex(is);

        JavascriptExecutor jse = (JavascriptExecutor) driver;  
        jse.executeScript("window.scrollBy(0,250)");

        Thread.sleep(3000);

       //Enter Warranty
     WebElement warranty=  driver.findElement(By.xpath(".//*[@id='warranty']"));  
       warranty.sendKeys("No Warranty");
       
       //Enter Kilometers driven
       driver.findElement(By.id("kilometers_driven")).sendKeys("123456");
       JavascriptExecutor jse11 = (JavascriptExecutor) driver;  
       jse11.executeScript("window.scrollBy(0,250)");

       Thread.sleep(10000);

      WebElement cal1= driver.findElement(By.xpath(".//*[@id='insurance_valid_till']/div"));
      cal1.click();
      
     Thread.sleep(3000);    
      
    //click on save and continue
	 
     WebElement save2=driver.findElement(By.xpath(".//*[@id='frm-Key_Factors']/div/div/div/div[2]/input"));
		save2.click();
  
		
		// Third page | description and radio button
		
		 Thread.sleep(5000);
		  WebElement desc=  driver.findElement(By.xpath(".//*[@id='description']"));
		  desc.sendKeys("123456");
		  
			 JavascriptExecutor jse12 = (JavascriptExecutor) driver;  
		     jse12.executeScript("window.scrollBy(0,250)");
		
		     
     //Do u want to add this listing to Quicksell
		     
		    List<WebElement> quicksell= driver.findElements(By.xpath(".//*[@id='typ_2661']/div/label"));
            int qu= v.nextInt(quicksell.size());
		    quicksell.get(qu).click();
           
	//click on save and continue
         System.out.println("check");
         
        // WebElement save3= driver.findElement(By.xpath(".//*[@id='frm-Description']/div/div/div/div[2]/input"));
         
         WebDriverWait wait = new WebDriverWait(driver,30);
         
         WebElement element = wait.until(
                             ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='frm-Description']/div/div/div/div[2]/input")));

         element.click();
		
         
// Fourth page | Seller Declaration
		 Thread.sleep(5000);
		 
		 
		 
	 // Vehicle ever met with an accident - Major/Minor
		List<WebElement> vehicles = driver.findElements(By.xpath(".//*[@id='typ_1791']/div/label"));
		int accident= v.nextInt(vehicles.size());
		System.out.println(vehicles.size());
		System.out.println(vehicles.get(accident).getText());
		//vehicles.get(0).click();
		vehicles.get(accident).click();
		
	// The vehicle has faulty AC
		List<WebElement> AC = driver.findElements(By.xpath(".//*[@id='typ_1801']/div/label"));
		int veh= v.nextInt(AC.size());
		AC.get(veh).click();
		
	// safety equipments
		List<WebElement> safety= driver.findElements(By.xpath(".//*[@id='typ_1811']/div/label"));
		int safety1=v.nextInt(safety.size());
		safety.get(safety1).click();
 		
	// music system defective
		List<WebElement> music= driver.findElements(By.xpath(".//*[@id='typ_1821']/div/label"));  //yes
		int musics=v.nextInt(music.size());
		music.get(musics).click();
		
	//	Noticeable drop
       List<WebElement> notice= driver.findElements(By.xpath(".//*[@id='typ_2351']/div/label"));
       int noticed=v.nextInt(notice.size());
       notice.get(noticed).click(); 
       
    // damage to the lightings  
	    List<WebElement> damage=driver.findElements(By.xpath(".//*[@id='typ_1831']/div/label"));
	    int damagel=v.nextInt(damage.size());
	    notice.get(damagel).click(); 
	        
	//odometer been tampered
	    List<WebElement> odometer=driver.findElements(By.xpath(".//*[@id='typ_1841']/div/label"));
	    int odo=v.nextInt(odometer.size());
	    odometer.get(odo).click(); 
	    
	// repaint or major body work
	    List<WebElement> repaint= driver.findElements(By.xpath(".//*[@id='typ_1851']/div/label"));
	    int re=v.nextInt(repaint.size());
	    repaint.get(re).click();
	 
	 
	// is there any rust 
	    List<WebElement> rust=driver.findElements(By.xpath(".//*[@id='typ_1871']/div/label"));
	    int rusts=v.nextInt(rust.size());
	    rust.get(rusts).click();
	    
	    
	//is there any damage to interior
	    List<WebElement> interior1=driver.findElements(By.xpath(".//*[@id='typ_1911']/div/label"));
	    int inte=v.nextInt(interior1.size());
	    interior1.get(inte).click();
	 
	// need to replace tyres in immediate future
	    List<WebElement> replace= driver.findElements(By.xpath(".//*[@id='typ_241']/div/label"));
	    int rep= v.nextInt(replace.size());
	    replace.get(rep).click();
	 
	 //Is there any fault in the electronic systems of the vehicle 
	    List<WebElement> fault=driver.findElements(By.xpath(".//*[@id='typ_241']/div/label"));
	    int f= v.nextInt(fault.size());
	    fault.get(f).click();
	    
	    
	 //Any suspension problem (squeaks or sounds) 
       List<WebElement> susp= driver.findElements(By.xpath(".//*[@id='typ_2221']/div/label"));
       int s= v.nextInt(susp.size());
       susp.get(s).click();
       
       
     //Any transmission issue in the vehicle 
       List<WebElement> trans=driver.findElements(By.xpath(".//*[@id='typ_1961']/div/label"));
       int t= v.nextInt(trans.size());
       trans.get(t).click();
       
       
     // Any brake related issues with the vehicle 
       List<WebElement> brake=driver.findElements(By.xpath(".//*[@id='typ_1941']/div/label"));
       int b= v.nextInt(brake.size());
       brake.get(b).click();
       
    // Any existing bank loans on the vehicle
       List<WebElement> bank= driver.findElements(By.xpath(".//*[@id='typ_1951']/div/label"));
       int ba=  v.nextInt(brake.size());
       bank.get(ba).click();
       
       // Engine and Transmission
/*       List<WebElement> engine=  driver.findElements(By.xpath(".//*[@id='typ_2281']/div/label"));
       int en=  v.nextInt(engine.size());
       engine.get(en).click();
*/
       
       driver.findElement(By.id("opt_2741")).click(); // accept terms
        
       Thread.sleep(5000);
       
    WebElement save4= driver.findElement(By.xpath(".//*[@id='frm-Seller_Declaration']/div/div/div/div[2]/input"));
    save4.click();
       
 // 5th Page || Inspection Report
       
     WebDriverWait waitForFormLabel = new WebDriverWait(driver, 30); 
    
     WebElement inspect=  driver.findElement(By.xpath(".//*[@id='typ_1081']/div[4]/label"));
       
     waitForFormLabel.until(ExpectedConditions.elementToBeClickable(inspect));
      
    
    /*   Random inre= new Random();
       int in= inre.nextInt(inspection.size());
       System.out.println("inspection"+ inspection.size()); */
       
       inspect.click();
  
    
 /*     if((inspection.get(in).getText()).equals("No Inspection Report"))
       {
       driver.findElement(By.xpath(".//*[@id='show_inspection_listings']")).click();  

   // Click on Droom   
       driver.findElement(By.xpath(".//*[@id='eco_location']")).sendKeys("Delhi");  //click on location 
       
       
       driver.findElement(By.xpath("")).sendKeys("HR35l 3873");
       
       JavascriptExecutor jse23 = (JavascriptExecutor) driver;  
         jse23.executeScript("window.scrollBy(0,250)");  
         
       driver.findElement(By.xpath(".//*[@id='order_eco_inspection_button']")).click();
       
       }
    */     
       
 // Warranty
       
          List<WebElement> warranty1= driver.findElements(By.xpath(".//*[@id='typ_1201']/div/label"));
          Random wa= new Random();
          int w= wa.nextInt(warranty1.size());
          System.out.println(warranty1.get(w).getText()); 
          warranty1.get(w).click();
          
         if((warranty1.get(w).getText()).equals("Seller Warranty"))
         {
   
       	   WebElement upload= driver.findElement(By.xpath(".//*[@id='warranty_file_upload']"));
       	   upload.click();
       	   Runtime.getRuntime().exec("C://Users//Aanchali//Desktop//fileupload.exe");
         }
          else if((warranty1.get(w).getText()).equals("Organized Dealer Network Warranty"))
          {
      //  	  WebDriverWait waitForupload = new WebDriverWait(driver, 30);
          	   WebElement upload= driver.findElement(By.xpath(".//*[@id='warranty_file_upload']"));
               upload.click();
			  Runtime.getRuntime().exec("C://Users//Aanchali//Desktop//fileupload.exe");
          }
          else if((warranty1.get(w).getText()).equals("3rd Party Warranty"))
          {
        	  
        //	  WebDriverWait waitForupload = new WebDriverWait(driver, 30);
         	   WebElement upload= driver.findElement(By.xpath(".//*[@id='warranty_file_upload']"));
         //       waitForupload.until(ExpectedConditions.elementToBeClickable(upload));           
         	  upload.click();
         	   Runtime.getRuntime().exec("C://Users//Aanchali//Desktop//fileupload.exe");
          }
          else
          {
        	  System.out.println("No additional warranty");
          }
      
         JavascriptExecutor jse112 = (JavascriptExecutor) driver;  
         jse112.executeScript("window.scrollBy(0,250)");        
         
  //  Certification
         
   /*     WebDriverWait waitcert = new WebDriverWait(driver,30);
         WebElement certifica = waitcert.until(
                             ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='typ_1341']/div[4]/label")));
     */ 
         
         List<WebElement> certification= driver.findElements(By.xpath(".//*[@id='typ_1341']/div/label"));
          Random ce= new Random();
          int c= ce.nextInt(certification.size());
          System.out.println(certification.get(c).getText()); 
          certification.get(c).click();
          
          if((certification.get(c).getText()).equals("Certification Pre-owned"))
          {
         	    WebElement upload= driver.findElement(By.xpath(".//*[@id='certification_file_upload']"));
         	    upload.click();
         	   Runtime.getRuntime().exec("C://Users//Aanchali//Desktop//fileupload.exe");
          }
           else if((certification.get(c).getText()).equals("Dealer Certified"))
           {
                WebElement upload= driver.findElement(By.xpath(".//*[@id='certification_file_upload']"));
                upload.click();
                Runtime.getRuntime().exec("C://Users//Aanchali//Desktop//fileupload.exe");
           }
           else if((certification.get(c).getText()).equals("No Certification"))
           {

           }
   
  //  COPY OF RC
          
   
          List<WebElement> RC= driver.findElements(By.xpath(".//*[@id='typ_1531']/div/label"));
          Random ce1= new Random();
          int r= ce1.nextInt(RC.size());
          
          System.out.println(RC.get(r).getText());
          
          RC.get(r).click();
          
          if((RC.get(r).getText()).equals("Yes and attached"))
          {
        	  WebElement rcupload= driver.findElement(By.xpath(".//*[@id='copy_of_rc_file_upload']"));     	 
        	  rcupload.click();
        	  Runtime.getRuntime().exec("C://Users//Aanchali//Desktop//fileupload.exe");
          }
          else if ((RC.get(r).getText()).equals("Yes but not attached"))
          {      	  
        	 System.out.println("Yes but not attached");	         
          
          }
          else if ((RC.get(r).getText()).equals("No Registration Copy"))
          {
        	  
        		 System.out.println("No Registration Copy");	  
          
          }
  
          
   // Copy of Insurance
          
          List<WebElement> copy= driver.findElements(By.xpath(".//*[@id='typ_1601']/div/label")); 
          int co= ce.nextInt(copy.size());
          System.out.println(copy.get(co).getText());
          copy.get(co).click();
          
         if((copy.get(co).getText()).equals("Yes and attached"))
          { 
        	    WebElement insurance= driver.findElement(By.xpath(".//*[@id='copy_of_insurance_file_upload']"));
        	    insurance.click();
        	    Runtime.getRuntime().exec("C://Users//Aanchali//Desktop//fileupload.exe");
          }
          else if ((copy.get(co).getText()).equals("Yes but not attached"))
          {
        	  System.out.println("Yes but not attached");	
          }
          else if ((copy.get(co).getText()).equals("No Registration Copy"))
          {
        	  System.out.println("No Registration Copy");	 
          }    
          
   // COPY OF RECENT SERVICE LOGS  
          
          List<WebElement> csl= driver.findElements(By.xpath(".//*[@id='typ_1691']/div/label")); 
          int csl1= ce.nextInt(csl.size());
          System.out.println(csl.get(csl1).getText());
          csl.get(csl1).click();
          
          if((copy.get(csl1).getText()).equals("Yes and attached"))
          {
        	  WebElement insurance=driver.findElement(By.xpath(".//*[@id='copy_of_recent_service_logs_file_upload']"));  
        	  Thread.sleep(5000);  
        	  insurance.click();
        	  Runtime.getRuntime().exec("C://Users//Aanchali//Desktop//fileupload.exe");
          }
          else if ((copy.get(csl1).getText()).equals("Yes but not attached"))
          {
        	  System.out.println("Yes but not attached");	
          }
          else if ((copy.get(csl1).getText()).equals("No Registration Copy"))
          {
        	  System.out.println("No Registration Copy");	
          }   
 
          
 //   NAME ON THE RC  
          
          driver.findElement(By.xpath(".//*[@id='name_on_the_rc']")).sendKeys("ABC");
          
     // Save and Continue
          
      WebElement save5= driver.findElement(By.xpath(".//*[@id='frm-Trust_Factors']/div/div/div/div[2]/input"));
      save5.click();
          
//  Sixth Page || Pricing   
          // Enter Selling price
      
          WebDriverWait waitt = new WebDriverWait(driver,30);
          
          WebElement price = waitt.until(
                              ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='selling_price']")));
          
         
   //    WebElement price= driver.findElement(By.xpath(".//*[@id='selling_price']"));
       price.sendKeys("500000");
       
         // Total Pay out
       WebElement payout= driver.findElement(By.xpath(".//*[@id='total_payout_value']"));
       payout.sendKeys("0");
        
       // Click Calculate
       driver.findElement(By.id("calculate_tp")).click();
       
       // scroll
       JavascriptExecutor jse99 = (JavascriptExecutor) driver;  
       jse99.executeScript("window.scrollBy(0,250)"); 
       
       
    //** Choose Additional Pricing format**       
    //   driver.findElement(By.xpath(".//*[@id='best_offer']")).click();
          
   // Auto Accept Offer  
       List<WebElement> accept= driver.findElements(By.xpath(".//*[contains(@class,'cls-par form-group selling_format_child accept_offer')]/div/label"));
        int auto=v.nextInt(accept.size());
          
        if((accept.get(auto).getText()).equals("Yes"))
        {
        	accept.get(auto).click();
        	
            //Auto Accept Offer
            
            WebElement autoaccept= driver.findElement(By.xpath(".//*[contains(@class,'cls-par form-group selling_format_child accept_offer')]/div[2]/label"));
            autoaccept.click();
            
       // Auto Reject Offer
          
            List<WebElement> reject= driver.findElements(By.xpath(".//*[contains(@class,'cls-par form-group selling_format_child reject_offer')]/div/label"));  
            int rej= v.nextInt(reject.size());
            
            if((reject.get(rej).getText()).equals("Yes"))
            {
            	reject.get(rej).click();
            	
       //  Enter Auto Reject Amount
     	
            	driver.findElement(By.cssSelector("#auto_reject_offer_amount")).sendKeys("5,000");
            }
            else if ((reject.get(rej).getText()).equals("No"))
            {
         	   reject.get(rej).click();
        
            }
            
            JavascriptExecutor jse2 = (JavascriptExecutor) driver;  
            jse2.executeScript("window.scrollBy(0,250)");

 // Auction      
        driver.findElement(By.xpath(".//*[@id='auction']")).click();
        
 // Starting Bid   
        
        Select st= new Select(driver.findElement(By.xpath(".//*[@id='starting_bid']")));
        st.selectByValue("99999");
          
  // Enter Reserve price
        
        driver.findElement(By.xpath(".//*[@id='reserve_price']")).sendKeys("100000");

    
   // Enter Minimum increment price
       Select min= new Select(driver.findElement(By.xpath(".//*[@id='minimum_bid_increment']")));
    min.selectByValue("1000");	  
    	  
 
  // Enter Transaction Closure Period(in Days)  
      driver.findElement(By.xpath(".//*[@id='auction_duration']")).sendKeys("23");   //issue here


 // click Save and continue
      driver.findElement(By.xpath(".//*[@id='frm-price']/div/div/div/div[5]/input")).click();
      
 // 7th Page || Upload Photos
  
      Thread.sleep(5000);
      System.out.println(driver.findElement(By.xpath("(//span[text()='Browse …'])[1]")).isDisplayed());	
    
      System.out.println("Photo");
       System.out.println(driver.findElement(By.id("input-fcount-1")).isDisplayed());
      
       WebElement uploadp= driver.findElement(By.id("input-fcount-1"));
      
       uploadp.click();
      
	   Runtime.getRuntime().exec("C://Users//Aanchali//Desktop//Photo.exe");
	   
      JavascriptExecutor scr = (JavascriptExecutor) driver;  
      scr.executeScript("window.scrollBy(0,250)");
  
      Thread.sleep(5000);
      
     //Upload Video
      
      WebElement uplv= driver.findElement(By.xpath("(//span[text()='Browse …'])[2]"));
      System.out.println(driver.findElement(By.xpath("(//span[text()='Browse …'])[2]")).isDisplayed());
       
      uplv.click();
      
    // Click save and continue       
 
      WebElement save7= driver.findElement(By.xpath(".//*[@id='frm-photo']/div/div/div/div[5]/input"));
      save7.click();
   
   // click on Video Pop-up (Maybe Later)
      
     System.out.println(driver.findElement(By.xpath(".//*[@id='videomodal']/div/div/div[2]/button[2]")).isDisplayed());
     WebElement video= driver.findElement(By.xpath(".//*[@id='videomodal']/div/div/div[2]/button[2]"));
     video.click();

 // 8th page || House Keeping
     JavascriptExecutor house = (JavascriptExecutor) driver;  
     house.executeScript("window.scrollBy(0,250)");

     driver.findElement(By.xpath(".//*[@id='frm-housekeeping']/div/div/div/div[5]/input")).click();       
  
   // 9th page || Activate listing
       JavascriptExecutor activate = (JavascriptExecutor) driver;  
       activate.executeScript("window.scrollBy(0,250)"); 
    
      Thread.sleep(6000);
      
      WebElement actlist= driver.findElement(By.cssSelector("#activate"));
      System.out.println(actlist.isDisplayed());
      actlist.click();
      
    // Deactivate the listing  
 //   Deactivatecases dc= new Deactivatecases(driver);
       
      
   //   View v1= new View();
   // v1.viewlisting(driver); 
     
	}	
}
}
