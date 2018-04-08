package Appium;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Base {
	static AndroidDriver<AndroidElement>  driver;
	 Define d;
	//public static  AndroidDriver<AndroidElement> capabilities() throws MalformedURLException
	{
		
		 DesiredCapabilities capab = new DesiredCapabilities();
		
		capab = new DesiredCapabilities();    
	    capab.setCapability("BROWSER_NAME","Android");
	    capab.setCapability("VERSION","4.4.2");
	    capab.setCapability("deviceName","Itg Guru (SM-G350E)");
	    capab.setCapability("platformName","Android");
	    capab.setCapability("appPackage","io.appium.android.apis");
	    capab.setCapability("appActivity","io.appium.android.apis.ApiDemos"); 
	 
	 //   driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capab);
	   // d = new Define(driver);

	}

}
