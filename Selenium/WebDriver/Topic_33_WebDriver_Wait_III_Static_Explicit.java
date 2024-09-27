package WebDriver;

import java.time.Duration;
import java.util.List;

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

public class Topic_33_WebDriver_Wait_III_Static_Explicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Sleep_2s() throws InterruptedException {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		Thread.sleep(2000);

		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}

	// @Test
	public void TC_02_Sleep_10s() throws InterruptedException {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		Thread.sleep(10000);

		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}

	// @Test
	public void TC_03_Sleep_Until_Element_isDisplayed() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		sleepUntilElementDisplayed(10, By.xpath("//button[text()='Start']"));

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		sleepUntilElementDisplayed(10, By.xpath("//h4[text()='Hello World!']"));

		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}

	// @Test
	public void TC_04_ExplicitWait_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Start']"))).click();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
	}

	// @Test
	public void TC_05_ExplicitWait_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Start']"))).click();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")))
				.isDisplayed());
	}

	// @Test
	public void TC_06_ExplicitWait_telerik() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));

		Assert.assertEquals(driver.findElement(By.xpath("//legend//following-sibling::div/span")).getText(),
				"No Selected Dates to display.");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='20']"))).click();

		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv")));

		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='20']")));

		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='rcSelected']")).getAttribute("title"),
				driver.findElement(By.xpath("//legend//following-sibling::div/span")).getText());
	}

	@Test
	public void TC_07_ExplicitWait_gofile() {
		driver.get("https://gofile.io/?t=uploadFiles");

		String dienBienPhuName = "dien bien phu.jpg";
		String haNoiName = "hanoi.jpg";
		String ninhBinhName = "ninh binh.jpg";

		String dienBienPhuPath = projectPath + "\\Upload Files\\" + dienBienPhuName;
		String haNoiPath = projectPath + "\\Upload Files\\" + haNoiName;
		String ninhBinhPath = projectPath + "\\Upload Files\\" + ninhBinhName;

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']"))).click();

		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));

		driver.findElement(By.xpath("//input[@type='file']"))
				.sendKeys(dienBienPhuPath + "\n" + haNoiPath + "\n" + ninhBinhPath);

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.mainUploadInitInfo")));

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class,'mainUploadSuccessLink')]//following-sibling::div/a"))).click();

		List<WebElement> itemNames = explicitWait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.contentName")));

		for (WebElement itemName : itemNames) {
			if (itemName.getText().equals(dienBienPhuName) || itemName.getText().equals(haNoiName)
					|| itemName.getText().equals(ninhBinhName)) {
				System.out.println(itemName.getText() + " đã tải lên thành công");
			} else {
				System.out.println(itemName.getText() + " không có trong danh sách tải lên ban đầu");
			}
		}
	}

	public void sleepUntilElementDisplayed(int seconds, By by) {
		for (int i = 0; i < seconds * 2; i++) {
			if (driver.findElements(by).size() > 0) {
				break;
			} else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
