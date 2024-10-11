package TestNG;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assertions {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@Test
	public void TC_01() {
		boolean status = 3 > 5;
		
		System.out.println(status);
		
		Assert.assertFalse(status);
	}
	
	@Test
	public void TC_02() {
		boolean status = 3 < 5;
		
		System.out.println(status);
		
		Assert.assertTrue(status);
	}
	
	@Test
	public void TC_03() {
		int multiple = 3*5;
		
		System.out.println(multiple);
		
		Assert.assertEquals(multiple, 15);
	}
}
