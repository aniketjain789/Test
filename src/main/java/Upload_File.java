import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Upload_File {

	public static void main(String[] args) throws InterruptedException, AWTException {
		// TODO Auto-generated method stub

		Robot robot = new Robot();
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\BrowserDrivers\\chromedriver.exe");
		WebDriver d = new ChromeDriver();
		d.get("http://demo.automationtesting.in/Register.html");
	
		d.manage().window().maximize();
		
		
		
		d.findElement(By.id("imagesrc")).click();
		
		//robot.getAutoDelay(2000);
		StringSelection stringselection = new StringSelection("C:\\Users\\abhi\\Desktop\\RESUME2\\updated\\Aniket Jain.docx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, null);
		robot.setAutoDelay(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.setAutoDelay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		
		
	//	d.quit();

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
