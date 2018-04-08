package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class MystoreManager 
{
	public WebDriver driver;
	
	public MystoreManager(WebDriver driver) throws InterruptedException
	{
	this.driver=driver;	
	}

	public void StoreManager() throws InterruptedException
	{
	 //  Close pop-up	 
	 Thread.sleep(9000);	
//	 WebElement popup= driver.findElement(By.xpath(".//*[@id='modalQuicksell']/div/div/div/button"));
//	 popup.click();		
		
     // Click on My regular listings	 
	System.out.println("Reached StoreManager");
    
	  // System.out.println(driver.findElement(By.cssSelector(".active>a")).isDisplayed());
      WebElement elemnt= driver.findElement(By.xpath("html/body/div[2]/div[2]/section/div[2]/div[1]/div[1]/ul/li[3]/a"));
      elemnt.click();

    System.out.println("i am able to click");

        // Close pop-up
       // driver.findElement(By.xpath(".//*[@id='modalQuicksell']/div/div/div/button")).click();
   
 // Create regular listings
      WebElement create= driver.findElement(By.xpath("html/body/div[2]/div/section/div[1]/div[2]/a[1]")); 
      create.click();	
	}
}
