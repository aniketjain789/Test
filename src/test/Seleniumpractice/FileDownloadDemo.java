package Seleniumpractice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileDownloadDemo {
	public static void main(String []arg) throws InterruptedException, AWTException
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		//launching browser
		WebDriver driver = new ChromeDriver();
		
		//Enter url
		driver.get("https://www.google.co.in");
		
		driver.manage().window().maximize();
		Thread.sleep(5000);
		//driver.findElement(By.xpath(".//*[@id='sb_ifc0']")).click();
		
		driver.findElement(By.id("lst-ib")).click();		
		
		driver.findElement(By.id("lst-ib")).sendKeys("java download");		
		//clicking on enter option in keyboard
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		 // wait for 2 second
		Thread.sleep(2000);
		//click on Download Free Java Software link
		driver.findElement(By.linkText("Download Free Java Software")).click();
		Thread.sleep(5000);
		//click on free java download button
		driver.findElement(By.xpath("//div[@class='jvdl0 jvdl0v0']/a/span")).click();
		Thread.sleep(5000);
		//click on agree start free download option
		driver.findElement(By.xpath("//div[@class='jvdl0 jvdl0v0']/a/span")).click();
		Thread.sleep(3000);
		//handling window based controls
		Robot robotobj =new Robot();
		//click on tab opration
		robotobj.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		//click on enter options
		robotobj.keyPress(KeyEvent.VK_ENTER);
		
	}
}
