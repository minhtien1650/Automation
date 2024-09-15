package WebDriver;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Select select;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		jsExecutor = (JavascriptExecutor) driver;

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_AutomationFC() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		String parentID = driver.getWindowHandle();

		jsExecutor.executeScript("arguments[0].scrollIntoView(true)",
				driver.findElement(By.xpath("//a[text()='GOOGLE']")));

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		switchToWindowByID(parentID);

		Assert.assertEquals(driver.getTitle(), "Google");

		switchToWindowByTitle("Selenium WebDriver");

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

		switchToWindowByTitle("Facebook – log in or sign up");

		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

		switchToWindowByTitle("Selenium WebDriver");

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();

		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		closeAllWindowsWithoutParentByID(parentID);

		Assert.assertEquals(driver.getTitle(), "Selenium WebDriver");
	}

	// @Test
	public void TC_02_Techpanda() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();

		driver.findElement(By.xpath(
				"//a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();

		Assert.assertEquals(driver.findElement(By.cssSelector(".success-msg li")).getText(),
				"The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.xpath(
				"//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();

		Assert.assertEquals(driver.findElement(By.cssSelector(".success-msg span")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//span[text()='Compare']//ancestor::button")).click();

		switchToWindowByTitle("Products Comparison List - Magento Commerce");

		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

		closeAlWindowWithoutParentByTitle("Mobile");

		driver.findElement(By.xpath("//a[text()='Clear All']")).click();

		explicitWait.until(ExpectedConditions.alertIsPresent()).accept();

		Assert.assertEquals(driver.findElement(By.cssSelector(".success-msg span")).getText(),
				"The comparison list was cleared.");
	}

	// @Test
	public void TC_03_Cambrigde() {
		driver.get("https://dictionary.cambridge.org/vi/");

		List<WebElement> dialog = driver.findElements(By.cssSelector(".fc-dialog-container div[role='dialog']"));

		if (dialog.size() > 0) {
			driver.findElement(By.xpath("div[role='dialog'] .fc-cancel-icon-svg")).click();
		}

		driver.findElement(By.xpath("//div[@role='button']//span[text()='Đăng nhập']")).click();

		switchToWindowByTitle("Login");

		driver.findElement(By.cssSelector("input[value='Log in']")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@data-bound-to='loginID' and @role='alert']")).getText(),
				"This field is required");

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@data-bound-to='password' and @role='alert']")).getText(),
				"This field is required");

		driver.close();

		switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");

		driver.findElement(By.id("searchword")).sendKeys("Automation");

		driver.findElement(By.cssSelector("button[aria-label='Search']")).click();

		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@id='cald4-1']//following-sibling::div//span[@class='hw dhw']")).getText(),
				"automation");
	}

	@Test
	public void TC_05_Havard() {
		driver.get("https://courses.dce.harvard.edu/");

		driver.findElement(By.cssSelector("a[data-action='login']")).click();

		switchToWindowByID(driver.getWindowHandle());

		Assert.assertEquals(driver.getTitle(), "Harvard Division of Continuing Education Login Portal");

		driver.close();

		switchToWindowByTitle("DCE Course Search");

		Assert.assertTrue(driver.findElement(By.cssSelector("div#sam-wait")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.className("sam-wait__message")).getText(),
				"Authentication was not successful. Please try again.");
		
		driver.findElement(By.className("sam-wait__close")).click();
		
		String 	keyword = "Data Science: An Artificial Ecosystem";
		String 	term = "Harvard Summer School 2024";
		String 	option = "Harvard College";
		
		driver.findElement(By.id("crit-keyword")).sendKeys(keyword);
		
		select = new Select(driver.findElement(By.id("crit-srcdb")));
		
		select.selectByVisibleText(term);
		
		select = new Select(driver.findElement(By.id("crit-summer_school")));
		
		select.selectByVisibleText(option);
		
		select = new Select(driver.findElement(By.id("crit-session")));
		
		select.selectByVisibleText("Any Part of Term");
		
		driver.findElement(By.id("search-button")).click();
		
		Assert.assertEquals(driver.findElement(By.className("result__title")).getText(), keyword);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='result-criteria__item'][2]")).getText().contains(term));
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='result-criteria__item'][3]")).getText().contains(option));
	}

	public void closeAllWindowsWithoutParentByID(String parentID) {
		Set<String> allWindowID = driver.getWindowHandles();

		for (String windowID : allWindowID) {
			if (!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
				driver.close();
			}
		}

		driver.switchTo().window(parentID);
	}

	public void closeAlWindowWithoutParentByTitle(String targetPageTitle) {
		Set<String> allWindowID = driver.getWindowHandles();
		for (String windowID : allWindowID) {
			driver.switchTo().window(windowID);
//			sleepInSecond(2);

			if (!driver.getTitle().equals(targetPageTitle)) {
				driver.close();
			}
		}

		switchToWindowByTitle(targetPageTitle);
	}

	public void switchToWindowByTitle(String targetPageTitle) {
		Set<String> allWindowID = driver.getWindowHandles();
		for (String windowID : allWindowID) {
			driver.switchTo().window(windowID);
//			sleepInSecond(2);

			if (driver.getTitle().equals(targetPageTitle)) {
				break;
			}
		}
	}

	public void switchToWindowByID(String parentPageID) {
		Set<String> allWindowID = driver.getWindowHandles();
		for (String windowID : allWindowID) {
			if (!windowID.equals(parentPageID)) {
				driver.switchTo().window(windowID);
//				sleepInSecond(2);
			}
		}
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
