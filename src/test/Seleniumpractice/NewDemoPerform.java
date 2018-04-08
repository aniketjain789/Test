package Seleniumpractice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class NewDemoPerform {

	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		WebDriver od = new ChromeDriver();
		od.get("https://www.flipkart.com/");
		od.manage().window().maximize();
		od.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement obj=od.findElement(By.xpath("//span[text()='Electronics']"));
		Actions act=new Actions(od);
		act.moveToElement(obj).perform();
		Thread.sleep(2000);
		od.findElement(By.xpath("//span[text()='Samsung']")).click();
	}

}
