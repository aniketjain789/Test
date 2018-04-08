package Seleniumpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConfirmationBoxDemo {

	public static void main(String[] args) throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		driver.get("http://softwaretesting-guru.blogspot.in/p/blog-page.html");
		
		//driver.findElements(By.xpath("")).click();
		
		driver.findElement(By.xpath(".//*[@id='sampleform']/table/tbody/tr[10]/td/input")).click();
		
		Thread.sleep(3000);
	//	driver.switchTo().alert().accept();
		
		driver.switchTo().alert().dismiss();
		driver.close();
		
		
	}

}
