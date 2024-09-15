package WebDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Handle_Frame_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		jsExecutor = (JavascriptExecutor) driver;

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Iframe_Toidicodedao() {
		driver.get("https://toidicodedao.com/");

		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//aside[@id='facebook-likebox-3']")))
				.isDisplayed());

		driver.switchTo().frame(driver.findElement(By.xpath("//aside[@id='facebook-likebox-3']//iframe")));

		Assert.assertEquals(driver.findElement(By.className("_1drq")).getText(), "406,526 followers");
	}

	// @Test
	public void TC_01_Iframe_formsite() {
		driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

		driver.findElement(By.xpath("//div[@class='details__form-preview']")).click();

		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='details__form-preview']//iframe")));

		Select select = new Select(driver.findElement(By.id("RESULT_RadioButton-2")));

		select.selectByVisibleText("Junior");

		select = new Select(driver.findElement(By.id("RESULT_RadioButton-3")));

		select.selectByVisibleText("East Dorm");

		driver.findElement(By.xpath("//label[text()='Male']")).click();

		driver.switchTo().defaultContent();

		driver.findElement(By.cssSelector(".header--desktop-floater a[title='Log in']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "https://fs1.formsite.com/form_app/FormSite?FormId=LoadLogin&Auto");
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		String customerID = "mtien1650";
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys(customerID);
		driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
		
		driver.switchTo().defaultContent();

		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Password/IPIN']")).isDisplayed());
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
