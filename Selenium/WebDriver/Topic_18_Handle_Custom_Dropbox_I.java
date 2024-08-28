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
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// Ép kiểu tường minh (Reference casting)
		jsExecutor = (JavascriptExecutor) driver;

		// implicitlyWait: Wait để tìm element (findElement/findElements)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		By parent = By.id("number-button");
		By child = By.cssSelector("#number-menu div");

		selectItemInDropdown(parent, child, "5");
		Assert.assertTrue(isElementDisplay(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']")));

		selectItemInDropdown(parent, child, "19");
		Assert.assertTrue(isElementDisplay(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")));

		selectItemInDropdown(parent, child, "10");
		Assert.assertTrue(isElementDisplay(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='10']")));

		selectItemInDropdown(parent, child, "15");
		Assert.assertTrue(isElementDisplay(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']")));

	}

	//@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		By parent = By.xpath("//div[@role='listbox']");
		By child = By.cssSelector("div[role='option']>span");

		selectItemInDropdown(parent, child, "Elliot Fu");
		isElementDisplay(By.xpath("//div[@role='alert' and text()='Elliot Fu']"));

		selectItemInDropdown(parent, child, "Stevie Feliciano");
		isElementDisplay(By.xpath("//div[@role='alert' and text()='Stevie Feliciano']"));

		selectItemInDropdown(parent, child, "Matt");
		isElementDisplay(By.xpath("//div[@role='alert' and text()='Matt']"));
	}

	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		By parent = By.cssSelector("li.dropdown-toggle");
		By child = By.xpath("//li[@class='dropdown-toggle']/following-sibling::ul//a");

		selectItemInDropdown(parent, child, "First Option");
		isElementDisplay(By.xpath("//li[@class='dropdown-toggle' and contains(.,'First Option')]"));

		selectItemInDropdown(parent, child, "Second Option");
		isElementDisplay(By.xpath("//li[@class='dropdown-toggle' and contains(.,'Second Option')]"));

		selectItemInDropdown(parent, child, "Third Option");
		isElementDisplay(By.xpath("//li[@class='dropdown-toggle' and contains(.,'Third Option')]"));
	}

	public void TC_04_KendoUI() {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/cascadingdropdownlist");

		// Chờ cho đến khi cái icon loading biến mất trong vòng 15 giây
		Assert.assertTrue(
				explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.kd-loader"))));

		// Chờ cho đến khi cái icon loading trong đropdown biến mất trong vòng 15 giây
		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.className("k-input-loading-icon"))));

		By categoryParent = By.xpath("//span[@aria-controls='categories_listbox']/span[@class='k-input-inner']");
		By categoryChild = By.xpath("//ul[@id='categories_listbox']//span[@class='k-list-item-text']");

		selectItemInDropdown(categoryParent, categoryChild, "Confections");

		// Product Dropdown
		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.className("k-input-loading-icon"))));

		By productParent = By.xpath("//span[@aria-controls='products_listbox']/span[@class='k-input-inner']");
		By productChild = By.cssSelector("ul#products_listbox>li>span");

		selectItemInDropdown(productParent, productChild, "Maxilaku");

		// Orders Dropdown
		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.className("k-input-loading-icon"))));

		By ordersParent = By.xpath("//span[@aria-controls='orders_listbox']/span[@class='k-input-inner']");
		By ordersChild = By.cssSelector("ul#orders_listbox>li>span");

		selectItemInDropdown(ordersParent, ordersChild, "Boise");
	}

	@Test
	public void TC_05_Angular(){
		
	}

	@Test
	public void TC_06_Editable(){
		driver.get("https://indrimuska.github.io/jquery-editable-select/");

		selectItemInDropdown(By.cssSelector("#default-place>input"), By.cssSelector("#default-place li"), "Citroen");
		sleepInSecond(2);
		
		selectItemInDropdown(By.cssSelector("#default-place>input"), By.cssSelector("#default-place li"), "Jaguar");
		sleepInSecond(2);
		
		selectItemInDropdown(By.cssSelector("#default-place>input"), By.cssSelector("#default-place li"), "Nissan");
		sleepInSecond(2);
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectItemInDropdown(By parentBy, By childBy, String expectedText) {
		// Chờ cho đến khi được phép click
		// 1 - Click vào 1 element cho xổ ra tất cả item
		explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy)).click();

		// 2 - Wait cho tất cả element được load ra (có trong HTML/DOM)
		// presence
		// Store lại tất cả Element (item của Dropdown)
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedText)) {
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
