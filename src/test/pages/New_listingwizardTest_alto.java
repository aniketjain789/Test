package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class New_listingwizardTest_alto 
{
	public void f(WebDriver driver) throws InterruptedException
	  {		
	      // Select a category	    
			WebElement category= driver.findElement(By.cssSelector("#category_id"));

		  // Select a vehicle
			
		  Select select= new Select(category);
		  List<WebElement> categories= select.getOptions(); 
		  System.out.println("categories:"+ categories.size());
	       
		  for(WebElement i:categories)
		{
			  String vehicle= i.getText();
			  if(vehicle.equals("Car"))
			  {
	          category.sendKeys(vehicle);
			  }
		}		  
		  
	  // Enter a product
		  
		  WebElement product= driver.findElement(By.xpath(".//*[@id='product_title']"));
		  product.sendKeys("Maruti Suzuki  Ertiga ZDi 2013 ");
		  
		  Thread.sleep(7000);
		  
	      product.sendKeys(Keys.SPACE);
		  
		  WebDriverWait waitvehicle = new WebDriverWait(driver,30);  
		  
		  WebElement veh1= waitvehicle.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/div[2]/header/div/form/div/div[1]/div[2]/div[2]/ul/li")));
		  
		  veh1.click();
	      	  
	    // click button create used vehicle listing
	      
		  System.out.println("Hey");
		  
	      WebElement createusedvehicle= driver.findElement(By.cssSelector("#old_listing"));
	      createusedvehicle.click();
	      
	  } 
}
