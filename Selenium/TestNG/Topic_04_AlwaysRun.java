package TestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_AlwaysRun {
	WebDriver driver;
	
	@BeforeClass(alwaysRun = true)
	public void BeforeClass() {
		driver = new EdgeDriver();
	}
	
	@Test(groups = "user")
	public void TC_01() {
		System.out.println("Run Testcase 01");
	}

	@Test(groups = {"user", "admin"})
	public void TC_02() {
		System.out.println("Run Testcase 02");
	}

	@Test(groups = "admin")
	public void TC_03() {
		System.out.println("Run Testcase 03");
	}
	
	@AfterClass(alwaysRun = true)
	public void AfterClass() {
		driver.quit();
	}
}
