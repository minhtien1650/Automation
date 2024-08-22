package WebDriver;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Handle_Textbox_Textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		email = "auto" + getRandomNumber() + "@gmail.us";
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		driver.get("http://demo.guru99.com/");

		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		String userID = driver.findElement(By.xpath("//td[contains(.,'User ID :')]/following-sibling::td")).getText();
		String password = driver.findElement(By.xpath("//td[contains(.,'Password :')]/following-sibling::td")).getText();
		
		driver.get("http://demo.guru99.com/v4");

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.xpath("//input[@value='LOGIN']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText().contains("Welcome To Manager's Page of Guru99 Bank"));

		driver.findElement(By.xpath("//a[contains(.,'New Customer')]")).click();

		driver.findElement(By.xpath("//input[@onkeyup='validatecustomername();']")).sendKeys("Selenium Online");
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(By.xpath("//input[@onkeyup='validatedob();']")).sendKeys("10012000");
		driver.findElement(By.xpath("//textarea[@onkeyup='validateAddress();']")).sendKeys("123 Address");
		driver.findElement(By.xpath("//input[@onkeyup='validateCity();']")).sendKeys("Ho Chi Minh");
		driver.findElement(By.xpath("//input[@onkeyup='validateState();']")).sendKeys("Thu Duc");
		driver.findElement(By.xpath("//input[@onkeyup='validatePIN();']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@onkeyup='validateTelephone();']")).sendKeys("0123456789");
		driver.findElement(By.xpath("//input[@onkeyup='validateEmail();']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@onkeyup='validatepassword();']")).sendKeys(password);
		driver.findElement(By.cssSelector("input[value='Submit']")).click();

		System.out.println(driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]//following-sibling::td")).getText());
		
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
