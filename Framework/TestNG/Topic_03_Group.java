package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_03_Group {
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
}
