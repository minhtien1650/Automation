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

public class Topic_25_Handle_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Fixed_Popup_ngoainu24h() {
		driver.get("https://ngoaingu24h.vn/");

		// Click to Button "Đăng nhập"
		driver.findElement(By.xpath("//button[contains(.,'Đăng nhập')]")).click();

		driver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys("AutomationFC");
		driver.findElement(By.xpath("//input[@autocomplete='new-password']")).sendKeys("AutomationFC");
		driver.findElement(By.cssSelector("div[role='dialog'] .dialog-button")).click();

		WebElement noti = explicitWait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notistack-snackbar"))));

		Assert.assertEquals(noti.getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

		List<WebElement> loginPopup = driver.findElements(By.cssSelector("div[role='dialog']"));

		if (loginPopup.size() > 0) {
			for (WebElement webElement : loginPopup) {
				driver.findElement(By.className("close-btn")).click();
				explicitWait.until(ExpectedConditions.invisibilityOf(webElement));
			}
		}

		Assert.assertEquals(driver.findElements(By.cssSelector("div[role='dialog']")).size(), 0);
	}

	// @Test
	public void TC_01_Fixed_Popup_kynaenglish() {
		driver.get("https://skills.kynaenglish.vn/dang-nhap");

		String email = "automation@gmail.com";
		String password = "123456";

		By loginPopupBy = By.cssSelector(".modal-content .right");

		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(loginPopupBy)));

		driver.findElement(By.cssSelector("input#user-login")).sendKeys(email);
		driver.findElement(By.cssSelector("input#user-password")).sendKeys(password);
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();

		Assert.assertEquals(explicitWait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password-form-login-message"))))
				.getText(), "Sai tên đăng nhập hoặc mật khẩu");
	}

	// @Test
	public void TC_01_Fixed_Popup_tiki() {
		driver.get("https://tiki.vn/");
		sleepInSecond(5);

		By containerBy = By.id("VIP_BUNDLE");

		if (driver.findElements(containerBy).size() > 0) {
			driver.findElement(By.cssSelector("img[alt='close-icon']")).click();
		}

		driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();

		By loginPopupBy = By.xpath("//div[@role='dialog']/div");

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(loginPopupBy));

		driver.findElement(By.className("login-with-email")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector(".heading>h4")).getText(), "Đăng nhập bằng email");

		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());

		driver.findElement(By.className("close-img")).click();

		Assert.assertTrue(
				explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(loginPopupBy))));
	}

	// @Test
	public void TC_01_Fixed_Popup_facebook() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[text()='Create new account']")).click();

		Assert.assertFalse(explicitWait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("_8ien"))).isEmpty());

		driver.findElement(By.cssSelector("._8ien>img")).click();

		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("_8ien")))));
	}

	// @Test
	public void TC_02_Random_Popup_In_DOM() {
		driver.get("https://www.kmplayer.com/home");

		By popupBy = By.className("pop-container");

		if (driver.findElement(popupBy).isDisplayed()) {
			driver.findElement(By.className("check")).click();
			driver.findElement(By.cssSelector(".close close")).click();
			sleepInSecond(2);
			Assert.assertFalse(driver.findElement(popupBy).isDisplayed());
		}
	}

	@Test
	public void TC_03_Random_Popup_Not_In_DOM() {
		driver.get("https://dehieu.vn/");

		if (explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("modal-content")))
				.size() > 0) {
			driver.findElement(By.cssSelector("button.close")).click();
		}

		Assert.assertTrue(explicitWait.until(
				ExpectedConditions.invisibilityOf(driver.findElement(By.className("modal-content")))));
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
