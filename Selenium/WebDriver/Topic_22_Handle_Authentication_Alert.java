package WebDriver;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Handle_Authentication_Alert {
	WebDriver driver;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	String authenticationChrome = projectPath + "\\AutoIT\\authen_chrome.exe";

	@BeforeClass
	public void BeforeClass() {
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Accept_Alert() {
		driver.get("https://demo.guru99.com/v4/");

		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(2);

		// Wait cho alert xuất hiện trong vòng 15 seconds
		// Wait + switchTo alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		// switchTo: Alert/ Window/ Tab/ Frame/ Iframe
		// alert = driver.switchTo().alert();

		System.out.println(alert.getText());

		// Accept alert (OK)
		alert.accept();

		// Cancel alert (Cancel)
		// alert.dismiss();
	}

	// @Test
	public void TC_02_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		alert.accept();

		Assert.assertEquals(driver.findElement(By.id("result")).getText().trim(), "You clicked an alert successfully");
	}

	// @Test
	public void TC_03_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		By confirmAlertBy = By.xpath("//button[text()='Click for JS Confirm']");

		driver.findElement(confirmAlertBy).click();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(2);

		alert.accept();

		Assert.assertEquals(driver.findElement(By.id("result")).getText().trim(), "You clicked: Ok");

		driver.findElement(confirmAlertBy).click();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(2);

		alert.dismiss();

		Assert.assertEquals(driver.findElement(By.id("result")).getText().trim(), "You clicked: Cancel");
	}

	// @Test
	public void TC_04_Promt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		String addressName = "Hanoi";

		alert.sendKeys(addressName);
		sleepInSecond(2);

		alert.accept();

		Assert.assertEquals(driver.findElement(By.id("result")).getText().trim(), "You entered: " + addressName);

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		alert.dismiss();

		Assert.assertEquals(driver.findElement(By.id("result")).getText().trim(), "You entered: null");
	}

	
	//@Test
	public void TC_05_Authentication_Alert() {
		String username = "admin";
		String password = "admin";
		String url = "http://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";
		
		driver.get(url);

		Assert.assertEquals(driver.findElement(By.xpath("//p")).getText().trim(),
				"Congratulations! You must have the proper credentials.");
	}
	
	//@Test
	public void TC_06_Authentication_Alert_AutoIT() throws IOException {
		String username = "admin";
		String password = "admin";
		String url = "http://@the-internet.herokuapp.com/basic_auth";
		
		Runtime.getRuntime().exec(new String[] {authenticationChrome, username, password});
		
		driver.get(url);
		
		Assert.assertEquals(driver.findElement(By.xpath("//p")).getText().trim(),
				"Congratulations! You must have the proper credentials.");
	}
	
	@Test
	public void TC_08_Authentication_Alert_loginToLinkByUsernamePassword() {
		String username = "admin";
		String password = "admin";
		String link = "http://the-internet.herokuapp.com/basic_auth";
		
		loginToLinkByUsernamePassword(link, username, password);

		Assert.assertEquals(driver.findElement(By.xpath("//p")).getText().trim(),
				"Congratulations! You must have the proper credentials.");
	}
	
	public void loginToLinkByUsernamePassword(String link, String username, String password) {
		String[] links = link.split("//");
		String url = links[0] + "//" + username + ":" + password + "@" + links[1];
		driver.get(url);
	}

	public void sleepInSecond(long sec) {
		try {
			Thread.sleep(sec * 1000);
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
