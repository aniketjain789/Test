package ActivateOPD;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Demo  {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.firefox.marionette", "C:\\Anu C drive  Data\\E drive\\Testing\\Selenium project Files\\geckodriver.exe");
		WebDriver driver  = new FirefoxDriver();
		driver.get("https://www.google.co.in/?gfe_rd=cr&dcr=0&ei=Gw69WoyVA4yC2QS-qZbAAg&gws_rd=ssl");
		System.out.println("kldhsd");
	}


}
