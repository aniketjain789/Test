package Seleniumpractice;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class alerttextverify {

	public static void main(String[] args)throws InterruptedException  {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		driver.get("http://www.kesinenitravels.com/");
		
		//Click on search button
		driver.findElement(By.xpath("html/body/section/div[2]/div/div[1]/div[3]/form/button")).click();
		
		Thread.sleep(3000);
		//verify the alert text
		Alert alert= driver.switchTo().alert();
		System.out.println("Alert Text :::::"+alert.getText());
		
		String actualAlertText = alert.getText();
		String ExpectedAlertText = "Please select the Source city";
		
		if(ExpectedAlertText.equalsIgnoreCase(actualAlertText))
		{
			//handel the alert select source value
			alert.accept();
		}
		
		
		
		//handel alert
		//driver.switchTo().alert().accept();
		
		driver.findElement(By.id("source")).sendKeys("HYDERABAD");
		//new Select( driver.findElement(By.id("source"))).selectByVisibleText("HYDERABAD");
		
	
	
	
	
	}

}
