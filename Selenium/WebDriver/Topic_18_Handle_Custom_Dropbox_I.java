package WebDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Handle_Custom_Dropbox_I {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();
		// Khi mở trình duyệt sẽ tạo ra 1 Driver ID

		// explicitWait: Wait để apply cho các trạng thái của element (visible/
		// invisible/ presence/ clickable/ ...)
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Ép kiểu tường minh (Reference casting)
		jsExecutor = (JavascriptExecutor) driver;

		// implicitlyWait: Wait để tìm element (findElement/findElements)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		By parent = By.id("number-button");
		By child = By.cssSelector("#number-menu div");

		selectItemInDropdown(parent, child, "5");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']")));

		selectItemInDropdown(parent, child, "19");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")));

		selectItemInDropdown(parent, child, "10");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='10']")));

		selectItemInDropdown(parent, child, "15");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplay(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']")));

	}

	@Test
	public void TC_02_ReactJS() {
	}

	@Test
	public void TC_03_VueJS() {
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectItemInDropdown(By parentBy, By childBy, String expectedText) {
		// 1 - Click vào 1 element cho xổ ra tất cả item
		driver.findElement(parentBy).click();

		// 2 - Wait cho tất cả element được load ra (có trong HTML/DOM)
		// presence
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

		// Store lại tất cả Element (item của Dropdown)
		List<WebElement> allItems = driver.findElements(childBy);

		for (WebElement item : allItems) {
			if (item.getText().equals(expectedText)) {
				if (item.isDisplayed()) { // 3 - Nếu item mình chọn nằm trong view (nhìn thấy được) thì click vào
					item.click();
				} else { // 4 - Nếu item mình chọn không nhìn thấy (che bên dưới) thì scroll xuống và
							// click vào
					jsExecutor.executeScript("selectedNumber.scrollIntoView(true);", item);
					item.click();
				}
			}
		}
	}

	public boolean isElementDisplay(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not displayed");
			return false;
		}
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
