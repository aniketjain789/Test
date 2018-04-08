package Seleniumpractice;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
public class dropdown1 {
		public static void main(String[] args) throws InterruptedException {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
			WebDriver  driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.navigate().to("http://www.facebook.com");
			WebElement month_dropdown=driver.findElement(By.id("month"));
			Select month_dd= new Select(month_dropdown);
			List<WebElement> month_List=month_dd.getOptions();
			int total_month= month_List.size();
			System.out.println("Total month count size"+total_month);
			for( WebElement ele : month_List)                             //Enhance loop or advanced loop
			{
				String month_name = ele.getText();
				System.out.println("month are==========>"+month_name);
 			}
			driver.close();
		}
}
