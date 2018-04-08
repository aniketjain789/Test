package Seleniumpractice;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class dropdown {
	public static void main(String[] args)  {
		

	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
	WebDriver  driver=new ChromeDriver();
	
	
	driver.get("https://secure.droom.in/user/login");
		driver.findElement(By.xpath("html/body/div[2]/div/div/div/div/form/div/div[1]/input")).sendKeys("qabuyer@droom.in");
	
	
		driver.findElement(By.xpath("html/body/div[2]/div/div/div/div/form/div/div[2]/input")).sendKeys("droom123");
	
		driver.findElement(By.xpath("html/body/div[2]/div/div/div/div/form/div/div[4]/input")).click();
	
		Thread.sleep(6000);
		driver.findElement(By.xpath("html/body/div[2]/div[2]/section/div[2]/div[1]/div[1]/ul/li[3]/a")).click();
		System.out.println("clicked");
		
	
	
	
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	/*driver.manage().window().maximize();
	System.out.println("launchurl Successful");
	driver.navigate().to("http://www.facebook.com");
	Select sc = new Select(driver.findElement(By.id("month")));
	WebElement	select_value=sc.getFirstSelectedOption();
	System.out.println("Before Selecting Element :"+select_value.getText());
	sc.selectByIndex(3);
	Thread.sleep(1000);
	sc.selectByValue("1");
	sc.selectByVisibleText("Aug");
    WebElement	selected_value=sc.getFirstSelectedOption();
	System.out.println("After Selecting Element :"+selected_value.getText());
 	sc.getOptions();
 	driver.close();
 	*/
 	

	
	
	//driver.navigate().to("http://www.calculator.net/interest-calculator.html");
	
	
/*	

	//Select month_dd=  
	Select dd = Select(driver.findElement(By.id("mnfbd")));
	*/
	//WebElement month = driver.findElement(By.id("month"));
	
	
	//WebElement we = sc.getOptions(); //to get the options values into list**

//System.out.println(we.size());  //to print the size in console, this and
   //  		sc.selectByIndex(5);  //this will select the 5th index and 6th value(indexing starts from 0)**

	
	
