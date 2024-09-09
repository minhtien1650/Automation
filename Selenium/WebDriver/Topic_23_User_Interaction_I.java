package WebDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_User_Interaction_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	Actions action;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		action = new Actions(driver);

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Hover_Textbox() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");
	}

	// @Test
	public void TC_01_Hover_Menu() {
		driver.get("https://www.myntra.com/");

		action.moveToElement(driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[text()='Kids']")))
				.perform();
		sleepInSecond(2);

		action.click(driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[text()='Home & Bath']"))).perform();

		Assert.assertEquals(driver.findElement(By.className("title-title")).getText(), "Kids Home Bath");
	}

	// @Test
	public void TC_02_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> allNumber = driver.findElements(By.cssSelector("#selectable>li"));

		action.clickAndHold(allNumber.get(5)).moveToElement(allNumber.get(10)).release().perform();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectable .ui-selected")).size(), 4);
	}

	//@Test
	public void TC_03_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> allNumber = driver.findElements(By.cssSelector("#selectable>li"));
		
		action.keyDown(Keys.CONTROL).perform();
		
		action.click(allNumber.get(0)).click(allNumber.get(2)).click(allNumber.get(8)).perform();
		
		action.keyUp(Keys.CONTROL).perform();
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectable .ui-selected")).size(), 3);
	}
	
	//@Test
	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
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
