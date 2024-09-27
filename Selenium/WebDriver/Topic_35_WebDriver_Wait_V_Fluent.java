package WebDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import graphql.com.google.common.base.Function;

public class Topic_35_WebDriver_Wait_V_Fluent {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
//	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentWaitDriver;
	FluentWait<WebElement> fluentWaitElement;
	
	
	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();
		
//		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
//		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_FluentWaitWebDriver() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);
		
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(500))
		.ignoring(NoSuchElementException.class);
		
		WebElement countDownTime = (WebElement) fluentWaitDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[text()='01:01:00']"));
			}
		});
		
		Assert.assertTrue(countDownTime.isDisplayed());
	}
	
	//@Test
	public void TC_02_FluentWaitWebElement() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countDown = driver.findElement(By.id("javascript_countdown_time"));
		
		fluentWaitElement = new FluentWait<WebElement>(countDown);
		
		fluentWaitElement.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(500))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				boolean flag = element.getText().endsWith("00");
				System.out.println("Text = " + element.getText());
				
				return flag;
			}
		});
	}
	
	//@Test
	public void TC_03_FluentWait_HelloWorld() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		WebElement startButton = driver.findElement(By.tagName("button"));
		
		fluentWaitElementUntil(startButton, startButton.isDisplayed());
		
		startButton.click();
		
		Assert.assertEquals(fluentWaitGetElement(By.tagName("h4")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_04_Page_Ready() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		fluentWaitGetElement(By.cssSelector("input[name='username']")).sendKeys("admin");
		fluentWaitGetElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
		fluentWaitGetElement(By.className("orangehrm-login-button")).click();
		
		Assert.assertTrue(isPageLoadSuccess());
		
		driver.findElement(By.className("oxd-userdropdown-tab")).click();
		
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		Assert.assertTrue(isPageLoadSuccess());
		
		String loginTitle = (String) jsExecutor.executeScript("return document.title");
		
		Assert.assertEquals(loginTitle, "OrangeHRM");
	}
	
	public boolean isPageLoadSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return (boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active == 0)");
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>(){

			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	private long timeout = 30;
	private long polling = 100;
	
	public WebElement fluentWaitGetElement(By locator) {
		fluentWaitDriver = new FluentWait<WebDriver>(driver);
		
		return fluentWaitDriver.withTimeout(Duration.ofSeconds(timeout))
		.pollingEvery(Duration.ofMillis(polling))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
	}
	
	public void fluentWaitElementUntil(WebElement element, boolean condition) {
		fluentWaitElement = new FluentWait<WebElement>(element);
		
		fluentWaitElement.withTimeout(Duration.ofSeconds(timeout))
		.pollingEvery(Duration.ofMillis(polling))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				boolean flag = condition;
				return flag;
			}
		});
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
