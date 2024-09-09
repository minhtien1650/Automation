package WebDriver;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_User_Interaction_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	Actions action;
	Alert alert;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		action = new Actions(driver);

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(2);

		action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
		sleepInSecond(3);

		Assert.assertTrue(
				driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))
						.isDisplayed());

		action.click(
				driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")))
				.perform();
		sleepInSecond(2);

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "clicked: quit");
	}

	//@Test
	public void TC_02_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		WebElement drag = driver.findElement(By.cssSelector("div#draggable"));
		WebElement drop = driver.findElement(By.cssSelector("div#droptarget"));

		action.clickAndHold(drag).moveToElement(drop).release().perform();
		sleepInSecond(2);

		Assert.assertEquals(drop.getText(), "You did great!");

		driver.navigate().refresh();
		
		drag = driver.findElement(By.cssSelector("div#draggable"));
		drop = driver.findElement(By.cssSelector("div#droptarget"));

		action.dragAndDrop(drag, drop).perform();
		sleepInSecond(2);
		
		Assert.assertEquals(drop.getText(), "You did great!");
	}
	
	@Test
	public void TC_03_Drag_And_Drop_HTML5() {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		By drag = By.id("column-a");
		By drop = By.id("column-b");
		
		action.dragAndDrop(driver.findElement(drag), driver.findElement(drop)).perform();
		sleepInSecond(2);
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
