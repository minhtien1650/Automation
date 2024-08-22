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

public class Topic_15_Web_Element_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, fullName, email, password;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		firstName = "John";
		lastName = "Wick";
		fullName = firstName + " " + lastName;
		email = "john" + getRandomNumber() + "@gmail.us";
		password = "123456";

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Signup_Validate() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();

		Assert.assertTrue(driver.findElement(By
				.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']"))
				.isDisplayed());

		// Cách 1
		String contactInformation = driver.findElement(By.xpath(
				"//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']//p"))
				.getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(email));

		// Cách 2
		Assert.assertTrue(driver.findElement(By.xpath(
				"//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']//p[contains(text(),'"
						+ fullName + "')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(
				"//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']//p[contains(.,'"
						+ email + "')]"))
				.isDisplayed());
		
		driver.findElement(By.xpath("//a/span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
		
		driver.findElement(By.id("send2")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		
		String contactInformationLogin = driver.findElement(By.xpath(
				"//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']//p"))
				.getText();
		Assert.assertTrue(contactInformationLogin.contains(fullName));
		Assert.assertTrue(contactInformationLogin.contains(email));
	}

	//@Test
	public void TC_02_LiveGuru_Login() {

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
