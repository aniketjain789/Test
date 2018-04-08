package test;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.MystoreManager;
import pages.listing_alto;
import utils.readexcel;

public class listing_call
{
	public static WebDriver driver= null;
	public static HSSFRow Row;
	public static HSSFCell cell;
	public static HSSFSheet Sheet;
	//public static String FilePath = "C://Users//Aanchali//workspace//sanity links//data.xls";
	public static String SheetName = "signup";
	public static String fullDate= "12/08/2017";
	public static String file="tata12.pdf";	
	
	@BeforeTest
	public void init()
	{
	     driver=new FirefoxDriver();	
	/*	System.setProperty("webdriver.chrome.driver", "C:/Users/Aanchali/Downloads/chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
	*/		   
		driver.get("http://droom.in/user/login"); 		
		driver.manage().window().maximize();
		//Sheet = readexcel.DataSheet(FilePath, SheetName);
		//String username = Sheet.getRow(1).getCell(0).getStringCellValue(); /*.toString();*/
		//String password = Sheet.getRow(1).getCell(1).getStringCellValue(); /*.toString();*/
		
		System.out.println(username+" "+password); 
		
		WebElement ele= driver.findElement(By.id("email"));
		WebElement el1e= driver.findElement(By.id("password"));
	    ele.sendKeys("qabuyer@droom.in");  
	    el1e.sendKeys("droom123");  

	  //  WebElement login= driver.findElement(By.xpath("html/body/div[2]/div/div/div/div/form/div/div[4]/input"));  // click on link	   	    
	    WebElement login= driver.findElement(By.xpath(".//*[@id='signUpButton']/input"));
	    login.click();
	}
	
	@Test
	   public void call() throws InterruptedException
	   {
		MystoreManager my= new MystoreManager(driver);
		my.StoreManager();
		
		listing_alto l =new listing_alto(driver);
		
	   }

}
