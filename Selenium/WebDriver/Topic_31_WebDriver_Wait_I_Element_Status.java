package WebDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_31_WebDriver_Wait_I_Element_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Visible() {
		driver.get("https://www.facebook.com/");

		explicitWait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']")))
				.click();

		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")))
				.isDisplayed());
	}

	@Test
	public void TC_02_Invisible() {
		driver.get("http://live.techpanda.org/index.php/");

		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='page-header-container']//span[text()='Account']")))
				.click();

		Assert.assertTrue(
				explicitWait
						.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[@class='page-header-container']//a[text()='My Account']")))
						.isDisplayed());

		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='page-header-container']//span[text()='Cart']")))
				.click();
		
		Assert.assertTrue(
				explicitWait
						.until(ExpectedConditions.invisibilityOfElementLocated(
								By.xpath("//div[@class='page-header-container']//a[text()='My Account']"))));
	}

	@Test
	public void TC_03_Presence() {
		driver.get("https://www.facebook.com/");

		explicitWait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']")))
				.click();

		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email__']")))
				.isDisplayed());
	}

	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");

		explicitWait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']")))
				.click();

		WebElement emailTextbox = explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));

		driver.findElement(By.xpath("//div[text()='Sign Up']//parent::div//preceding-sibling::img")).click();

		Assert.assertTrue(explicitWait.until(ExpectedConditions.stalenessOf(emailTextbox)));
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
