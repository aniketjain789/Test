package Seleniumpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class dragndrop2 {
	public static void main(String args[])
	{	
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://jqueryui.com/droppable/");
	WebDriverWait wait = new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(".demo-frame")));
	WebElement Sourcelocator = driver.findElement(By.cssSelector(".ui-draggable"));
	Actions kk = new Actions(driver);
	kk.dragAndDropBy(Sourcelocator,150,200).perform();
	
	}
	

}
