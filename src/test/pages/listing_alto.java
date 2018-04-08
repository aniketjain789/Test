package pages;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class listing_alto 
{
	public WebDriver driver= null;
    Random v= new Random();  
 
    // WebElement close=  driver.findElement(By.xpath(".//*[@id='modalQuicksell']/div/div/div/button"));
   //  close.click();
        
    public listing_alto(WebDriver driver)
     {
	 this.driver= driver;
      }
        
//Main First Page 
      public void Exteriorcolour()
      {
       List<WebElement> exterior= driver.findElements(By.xpath(".//*[@id='typ_861']/div/label"));
       int ext= v.nextInt(exterior.size());
       exterior.get(ext).click(); 
      }
      
     // click on Interior Colour
      public void Interiorcolour()
      {
         List<WebElement> interior= driver.findElements(By.xpath(".//*[@id='typ_221']/div[1]/label"));
         int inter= v.nextInt(interior.size());
         interior.get(inter).click();
      }  

//   driver.findElement(By.cssSelector(".btn.btn-lg.btn-block.btn-primary.submit")).click();

       public void Save1() throws InterruptedException
       {
   // click on save and continue  
         JavascriptExecutor jse22 = (JavascriptExecutor) driver;  
         jse22.executeScript("window.scrollBy(0,250)");  
           
         Thread.sleep(5000);      
        
         System.out.println("Basic Facts Save" +driver.findElement((By.xpath(".//*[@id='frm-Basic_Facts']/div/div/div/div[2]/input"))).isDisplayed());
    	   
     WebDriverWait waitsave = new WebDriverWait(driver,30);  
     WebElement save1 = waitsave.until(
     ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='frm-Basic_Facts']/div/div/div/div[2]/input")));  

         save1.click();
         
         Thread.sleep(5000);
       }
       
//Second Page ||Key Factors
       
       public void Location()
     {
       //location
     WebElement location=  driver.findElement(By.xpath(".//*[@id='location']"));
     location.sendKeys("Gurgaon");
     }
     
     public void Registrationnumber()
     {
     // Enter Registration number
     WebElement reg= driver.findElement(By.xpath(".//*[@id='vehicle_registration_number']"));
     reg.sendKeys("HR35L3873");
     }
     
     public void Registrationstate() throws InterruptedException
  {
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
     }
    
// Enter Warranty	       
       public void Warranty()
{
       WebElement warranty=  driver.findElement(By.xpath(".//*[@id='warranty']"));  
       warranty.sendKeys("No Warranty");
}
// Enter Kilometers driven           
        public void Kilometersdriven() throws InterruptedException
  {
       driver.findElement(By.id("kilometers_driven")).sendKeys("123456");
       JavascriptExecutor jse11 = (JavascriptExecutor) driver;  
       jse11.executeScript("window.scrollBy(0,250)");
       Thread.sleep(10000);
  }
    
       public void Calender()
    {
       WebElement cal1= driver.findElement(By.xpath(".//*[@id='insurance_valid_till']/div"));
       cal1.click();
    }
            
     //click on save and continue    	
     public void Save2() throws InterruptedException
    {
         Thread.sleep(3000);           
     WebElement save2=driver.findElement(By.xpath(".//*[@id='frm-Key_Factors']/div/div/div/div[2]/input"));
     save2.click();
    }

// Third page | Seller Declaration
     
	
 public void Accident() throws InterruptedException 
     {
	 // Vehicle ever met with an accident - Major/Minor
		Thread.sleep(5000);		 
		List<WebElement> vehicles = driver.findElements(By.xpath(".//*[@id='typ_1791']/div/label"));
		int accident= v.nextInt(vehicles.size());
		System.out.println(vehicles.size());
		System.out.println(vehicles.get(accident).getText());
		//vehicles.get(0).click();
		vehicles.get(accident).click();
     }
     
     public void FaultyAC()
     {
	// The vehicle has faulty AC
		List<WebElement> AC = driver.findElements(By.xpath(".//*[@id='typ_1801']/div/label"));
		int veh= v.nextInt(AC.size());
		AC.get(veh).click();
     }
     
     public void SafetyEquipments()
     {
	// safety equipments
		List<WebElement> safety= driver.findElements(By.xpath(".//*[@id='typ_1811']/div/label"));
		int safety1=v.nextInt(safety.size());
		safety.get(safety1).click();
     }
     
     public void MusicSystemdefective()
     {
	// music system defective
		List<WebElement> music= driver.findElements(By.xpath(".//*[@id='typ_1821']/div/label"));  //yes
		int musics=v.nextInt(music.size());
		music.get(musics).click();
     }
     
     public void Noticeabledrop()
     {
	//	Noticeable drop
       List<WebElement> notice= driver.findElements(By.xpath(".//*[@id='typ_2351']/div/label"));
       int noticed=v.nextInt(notice.size());
       notice.get(noticed).click(); 
     }
     
     public void Damage_lightings()
     {
    // damage to the lightings  
	    List<WebElement> damage=driver.findElements(By.xpath(".//*[@id='typ_1831']/div/label"));
	    int damagel=v.nextInt(damage.size());
	    damage.get(damagel).click(); 
     }
     
     public void Odometer()
     {
	// Odometer been tampered
	    List<WebElement> odometer=driver.findElements(By.xpath(".//*[@id='typ_1841']/div/label"));
	    int odo=v.nextInt(odometer.size());
	    odometer.get(odo).click(); 
     }
     
     public void Repaint()
     {
	// repaint or major body work
	    List<WebElement> repaint= driver.findElements(By.xpath(".//*[@id='typ_1851']/div/label"));
	    int re=v.nextInt(repaint.size());
	    repaint.get(re).click();
     }
     
     public void Rust()
	 {
	// is there any rust 
	    List<WebElement> rust=driver.findElements(By.xpath(".//*[@id='typ_1871']/div/label"));
	    int rusts=v.nextInt(rust.size());
	    rust.get(rusts).click();
	 }  
	 
	 public void Damagetointerior()
	 {
	//is there any damage to interior
	    List<WebElement> interior1=driver.findElements(By.xpath(".//*[@id='typ_1911']/div/label"));
	    int inte=v.nextInt(interior1.size());
	    interior1.get(inte).click();
	 }
	 
	 public void Replace_Tyres()
	 {
	// need to replace tyres in immediate future
	    List<WebElement> replace= driver.findElements(By.xpath(".//*[@id='typ_241']/div/label"));
	    int rep= v.nextInt(replace.size());
	    replace.get(rep).click();
	 }
	 
	 public void fault_ElectronicSystem()
	 {
	 //Is there any fault in the electronic systems of the vehicle 
	    List<WebElement> fault=driver.findElements(By.xpath(".//*[@id='typ_241']/div/label"));
	    int f= v.nextInt(fault.size());
	    fault.get(f).click();
	 }  
	  
	 public void Suspension()
	 {
	 //Any suspension problem (squeaks or sounds) 
       List<WebElement> susp= driver.findElements(By.xpath(".//*[@id='typ_2221']/div/label"));
       int s= v.nextInt(susp.size());
       susp.get(s).click();
	 } 
	   
	 public void Transmission()
	 {
     //Any transmission issue in the vehicle 
       List<WebElement> trans=driver.findElements(By.xpath(".//*[@id='typ_1961']/div/label"));
       int t= v.nextInt(trans.size());
       trans.get(t).click();
	 } 
      
	 public void Brake_issue()
	 {
     // Any brake related issues with the vehicle 
       List<WebElement> brake=driver.findElements(By.xpath(".//*[@id='typ_1941']/div/label"));
       int b= v.nextInt(brake.size());
       brake.get(b).click();
	 }
	 
	 public void Bank_loan()
	 {
    // Any existing bank loans on the vehicle
       List<WebElement> bank= driver.findElements(By.xpath(".//*[@id='typ_1951']/div/label"));
       int ba=  v.nextInt(bank.size());
       bank.get(ba).click();
	 } 
	 
       // Engine and Transmission
/*       List<WebElement> engine=  driver.findElements(By.xpath(".//*[@id='typ_2281']/div/label"));
       int en=  v.nextInt(engine.size());
       engine.get(en).click();
*/
	 public void Accept_terms() throws InterruptedException
     {
       driver.findElement(By.id("opt_2741")).click(); // accept terms
       Thread.sleep(5000);
     }  
     
     public void Save3()
   {
    WebElement save3= driver.findElement(By.xpath(".//*[@id='frm-Seller_Declaration']/div/div/div/div[2]/input"));
    save3.click();
   }
     
 // 4th Page || Inspection Report
    
     public void InspectionReport()
   {
     WebDriverWait waitForFormLabel = new WebDriverWait(driver, 30);     
     WebElement inspect=  driver.findElement(By.xpath(".//*[@id='typ_1081']/div[4]/label"));    
     waitForFormLabel.until(ExpectedConditions.elementToBeClickable(inspect));   
     inspect.click();
   }
    
    /*   Random inre= new Random();
       int in= inre.nextInt(inspection.size());
       System.out.println("inspection"+ inspection.size()); */

    
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
   public void Warranty1() throws IOException
     {
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
     }
     
     public void Certification() throws IOException
     {
  //  Certification       
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
        	   //  certification.get(c).click();
        	   //  Runtime.getRuntime().exec("C://Users//Aanchali//Desktop//fileupload.exe");
           }
     }
     
     public void CopyofRC() throws IOException
     {
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
     }
      
   public void CopyofInsurance() throws IOException
   {
   // Copy of Insurance
          
          List<WebElement> copy= driver.findElements(By.xpath(".//*[@id='typ_1601']/div/label")); 
          int co= v.nextInt(copy.size());
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
   }
   
   public void COPYOFRECENTSERVICELOGS() throws InterruptedException, IOException
   {
   // COPY OF RECENT SERVICE LOGS  
          
          List<WebElement> csl= driver.findElements(By.xpath(".//*[@id='typ_1691']/div/label")); 
          int csl1= v.nextInt(csl.size());
          System.out.println(csl.get(csl1).getText());
          csl.get(csl1).click();
          
          if((csl.get(csl1).getText()).equals("Yes and attached"))
          {
        	  WebElement insurance=driver.findElement(By.xpath(".//*[@id='copy_of_recent_service_logs_file_upload']"));  
        	  Thread.sleep(5000);  
        	  
        	  insurance.click();
        	  Runtime.getRuntime().exec("C://Users//Aanchali//Desktop//fileupload.exe");
          }
          else if ((csl.get(csl1).getText()).equals("Yes but not attached"))
          {
        	  System.out.println("Yes but not attached");	
          }
          else if ((csl.get(csl1).getText()).equals("No Registration Copy"))
          {
        	  System.out.println("No Registration Copy");	
          }   
   }
    
   public void NAMEONTHERC()
   {
	   //   NAME ON THE RC        
       driver.findElement(By.xpath(".//*[@id='name_on_the_rc']")).sendKeys("ABC");
       
   }

   public void Save4()
  {
     // Save and Continue      
      WebElement save5= driver.findElement(By.xpath(".//*[@id='frm-Trust_Factors']/div/div/div/div[2]/input"));
      save5.click();
  }
  
   // Fifth page | description and radio button
  
  public void Description()
    {
        WebDriverWait waitdesc = new WebDriverWait(driver,30);
        WebElement desc = waitdesc.until(
                            ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='description']"))); 	
   	//	  Thread.sleep(5000);
   //    WebElement desc=  driver.findElement(By.xpath(".//*[@id='description']"));
   		     
        desc.sendKeys("123456");
    	JavascriptExecutor jse12 = (JavascriptExecutor) driver;  
        jse12.executeScript("window.scrollBy(0,250)");
    }	  

   		     
    public void Add_listing_quicksell()
   	{
        //Do u want to add this listing to Quick sell 		     
   		    List<WebElement> quicksell= driver.findElements(By.xpath(".//*[@id='typ_2661']/div/label"));
            int qu= v.nextInt(quicksell.size());
   		    quicksell.get(qu).click();
   	}
   	
   	public void	Save5()
   	{
   	//click on save and continue
            System.out.println("check");
            WebDriverWait wait = new WebDriverWait(driver,30);
            WebElement element = wait.until(
                                ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='frm-Description']/div/div/div/div[2]/input")));
            element.click();
   	}
   	
//  Sixth Page || Pricing
   public void Selling_price()
   	{
          // Enter Selling price     
          WebDriverWait waitt = new WebDriverWait(driver,30);          
          WebElement price = waitt.until(
                              ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='selling_price']")));        
          //    WebElement price= driver.findElement(By.xpath(".//*[@id='selling_price']"));
          price.sendKeys("500000");
   	}
   	
   
   public void Payout()
   	{
         // Total Pay out
       WebElement payout= driver.findElement(By.xpath(".//*[@id='total_payout_value']"));
       payout.sendKeys("0");
   	}
   
   public void Calculate()
   	{
   		// Click Calculate 	
       WebElement calculate=  driver.findElement(By.id("calculate_tp"));
       calculate.click();  
       JavascriptExecutor jse99 = (JavascriptExecutor) driver;  
       jse99.executeScript("window.scrollBy(0,250)"); 
   	} 
       //** Choose Additional Pricing format**       
  //    driver.findElement(By.xpath(".//*[@id='best_offer']")).click();
          
       
   public void AutoAcceptOffer()
    {
	 // Auto Accept Offer
	   
       WebElement autoaccept= driver.findElement(By.xpath(".//*[contains(@class,'cls-par form-group selling_format_child accept_offer')]/div[2]/label"));
       autoaccept.click();
    }
    
   public void AutoRejectOffer()
  {
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
  }  

 
   public void Auction()
       {
	// Auction     
       driver.findElement(By.xpath(".//*[@id='auction']")).click();
       }
    
// Starting Bid   
   public void StartingBid()
    {
       Select st= new Select(driver.findElement(By.xpath(".//*[@id='starting_bid']")));
       st.selectByValue("99999");
    }
    
 // Enter Reserve price
   public void Reserveprice()
    {
       driver.findElement(By.xpath(".//*[@id='reserve_price']")).sendKeys("100000");
    }

   public void MinimumIncrementprice()
    {
  // Enter Minimum increment price
      Select min= new Select(driver.findElement(By.xpath(".//*[@id='minimum_bid_increment']")));
      min.selectByValue("1000");	  
    } 

 // Enter Transaction Closure Period(in Days)
    
   public void TransactionClosurePeriod()
    {
     driver.findElement(By.xpath(".//*[@id='auction_duration']")).sendKeys("23");   //issue here
    }

   public void Save6()
    {
// click Save and continue
     driver.findElement(By.xpath(".//*[@id='frm-price']/div/div/div/div[5]/input")).click();
    } 
      
 // 7th Page || Upload Photos
  
   public void Uploadphotos() throws Exception
    {
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
    }
   
  
   public void Uploadvideo()
     {
     //Upload Video    
      WebElement uplv= driver.findElement(By.xpath("(//span[text()='Browse …'])[2]"));
      System.out.println(driver.findElement(By.xpath("(//span[text()='Browse …'])[2]")).isDisplayed());      
      uplv.click();
     }
     
   public void Save7()
     {
    // Click save and continue        
      WebElement save7= driver.findElement(By.xpath(".//*[@id='frm-photo']/div/div/div/div[5]/input"));
      save7.click();
     }
     
   public void VideoPopup()
     {
	    // click on Video Pop-up (Maybe Later)
     System.out.println(driver.findElement(By.xpath(".//*[@id='videomodal']/div/div/div[2]/button[2]")).isDisplayed());
     WebElement video= driver.findElement(By.xpath(".//*[@id='videomodal']/div/div/div[2]/button[2]"));
     video.click();
     }

 // 8th page || House Keeping
   public void HouseKeeping()
    {
      JavascriptExecutor house = (JavascriptExecutor)driver;  
      house.executeScript("window.scrollBy(0,250)");
      driver.findElement(By.xpath(".//*[@id='frm-housekeeping']/div/div/div/div[5]/input")).click();       
     }
     
   // 9th page || Activate listing
     
   public void Activatelisting() throws Exception
       {
        JavascriptExecutor activate = (JavascriptExecutor)driver;  
        activate.executeScript("window.scrollBy(0,250)");    
        Thread.sleep(6000); 	    	
   
       WebElement actlist= driver.findElement(By.cssSelector("#activate"));
       System.out.println(actlist.isDisplayed());
       actlist.click();
       }
    
    // Deactivate the listing  
    //   Deactivatecases dc= new Deactivatecases(driver);
    
    /* Preview listing and get DLID
       View v1= new View();
       v1.viewlisting(driver); 
    */        	
   
   public void list(WebDriver driver) throws Exception 
   {
	   
	   New_listingwizardTest_alto nl= new New_listingwizardTest_alto();
	      nl.f(driver); 
	      
	   Exteriorcolour();
	   Interiorcolour();
	   Save1();

	   Location();
	   Registrationnumber();
	   Registrationstate();
	   Warranty();
	   Kilometersdriven();
	   Calender();
	   Save2();


	   Accident();
	   FaultyAC();
	   SafetyEquipments();
	   MusicSystemdefective(); 
	   Noticeabledrop();
	   Damage_lightings();
	   Odometer();
	   Repaint();
	   Rust();
	   Damagetointerior();
	   Replace_Tyres();
	   fault_ElectronicSystem();
	   Suspension();
	   Transmission();

	// 4th page
	   InspectionReport();
	   Warranty1();
	   Certification();
	   CopyofRC();
	   CopyofInsurance();
	   COPYOFRECENTSERVICELOGS();
	   NAMEONTHERC();
	   Save4();

	   //5th page
	   Description();
	   Add_listing_quicksell();
	   Save5();

	   //6th page
	   Selling_price();
	   Payout();
	   Calculate();
	   AutoAcceptOffer();
	   AutoRejectOffer();
	   Auction();
	   StartingBid();
	   Reserveprice();
	   MinimumIncrementprice();
	   TransactionClosurePeriod();
	   Save6();

	   //7th page
	   Uploadphotos();
	   Uploadvideo();
	   Save7();
	   VideoPopup();

	   //8th page
	   HouseKeeping();

	   //9th page
	   Activatelisting();
	   
	   
	   
	   
	   
	   
	   
   }
}