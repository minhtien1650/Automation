package Basic;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterTest;

public class Topic_03_Login_Xpath {
	WebDriver driver;
	
	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("http://live.techpanda.org/");	

		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
	}

	@Test
	public void TC_01_Login_with_empty_email_password() {
		//driver = new EdgeDriver();

		//driver.get("http://live.techpanda.org/");

		//driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		//driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();

		driver.navigate().refresh();

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");

		driver.findElement(By.xpath("//button[@id='send2']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),
				"This is a required field.");		
	}
	
	@Test
	public void TC_02_Login_with_invalid_email(){
		//driver = new EdgeDriver();

		//driver.get("http://live.techpanda.org/");

		//driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		//driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();

		driver.navigate().refresh();

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("1234@123.123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");

		driver.findElement(By.xpath("//button[@id='send2']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
		
		//driver.quit();
	}
	
	@Test
	public void TC_03_Login_with_password_less_than_6_characters(){
		//driver = new EdgeDriver();

		//driver.get("http://live.techpanda.org/");

		//driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		//driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();

		driver.navigate().refresh();

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");

		driver.findElement(By.xpath("//button[@id='send2']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
		
		//driver.quit();
	}
		
	@Test
	public void TC_04_Login_with_incorrect_email_password(){
		//driver = new EdgeDriver();

		//driver.get("http://live.techpanda.org/");

		//driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		//driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();

		driver.navigate().refresh();

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456789");

		driver.findElement(By.xpath("//button[@id='send2']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");
		
		//driver.quit();
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
