package WebDriver;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Handle_Button_Radio_CheckBox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String email, password;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		jsExecutor = (JavascriptExecutor) driver;

		email = "autofc" + getRandomNumber() + "@gmail.net";
		password = "123456";

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Fahasa() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.xpath("//a[text()='Đăng nhập']/parent::li")).click();

		driver.findElement(By.cssSelector("#login_username")).sendKeys(email);
		driver.findElement(By.cssSelector("#login_password")).sendKeys(password);

		Assert.assertTrue(isElementEnable(By.cssSelector(".fhs-btn-login")));

		isElementColor(By.cssSelector(".fhs-btn-login"), "background-color", "#C92127");
	}

	@Test
	public void TC_02_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.xpath("//a[text()='Đăng nhập']/parent::li")).click();

		By loginButton = By.cssSelector(".fhs-btn-login");

		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		driver.findElement(By.cssSelector("#login_username")).sendKeys(email);
		driver.findElement(By.cssSelector("#login_password")).sendKeys(password);
		sleepInSecond(1);

		Assert.assertTrue(driver.findElement(loginButton).isEnabled());

		driver.navigate().refresh();

		driver.findElement(By.xpath("//a[text()='Đăng nhập']/parent::li")).click();

		// Remove disable attribute trong button
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButton));

		driver.findElement(loginButton).click();

		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='fhs-input-box checked-error']//label[text()='Số điện thoại/Email']//following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='fhs-input-box fhs-input-display checked-error']//label[text()='Mật khẩu']//following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
	}

	public boolean isElementColor(By by, String css, String value) {
		String rgbColor = driver.findElement(by).getCssValue(css);
		Color color = Color.fromString(rgbColor);
		String hexColor = color.asHex().toUpperCase();
		if (hexColor == value) {
			return true;
		} else {
			return false;
		}
	}

	public void sleepInSecond(long sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isElementEnable(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			return true;
		} else {
			return false;
		}
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
