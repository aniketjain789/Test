import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class s{

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		//System.setProperty(key, value)
		WebDriver samiksha= new ChromeDriver();
		samiksha.get("www..com");
	}

}
