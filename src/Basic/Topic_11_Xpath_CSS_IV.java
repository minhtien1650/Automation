package Basic;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_11_Xpath_CSS_IV {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new EdgeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("");
	}

	@Test
	public void Lesson() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
