package TestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_06_Parameter {
	WebDriver driver;
	
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void BeforeClass(@Optional("edge") String browserName) {
		switch(browserName) {
		case "edge":
			driver = new EdgeDriver();
			break;
		case "chrome":
			driver = new ChromeDriver();
			break;
		}
	}
	
	@Parameters("url")
	@Test
	public void TC_01(@Optional("https://automationfc.github.io/jquery-selectable/") String url) {
		driver.get(url);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void AfterClass() {
		driver.quit();
	}
}
