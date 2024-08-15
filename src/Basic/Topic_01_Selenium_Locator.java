package Basic;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import graphql.Assert;

public class Topic_01_Selenium_Locator {
	// Khai báo biến đại diện cho Selenium WebDriver
	WebDriver driver;

	@BeforeClass
	public void BeforeClass() {
		// Mở trình duyệt Edge
		// driver = new EdgeDriver();

		// Mở trình duyệt Firefox
//		driver = new FirefoxDriver();

		// Set timeout để tìm element
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Mở application lên (AUT/SUT)
		// driver.get("https://hrm.ltsgroup.tech/auth/login");
	}

	// @Test
	public void TC_01() {

		// Single element
		driver.findElement(By.id("")).click();
		driver.findElement(By.id("")).getText();

		// findElement: Tìm Element
		// By.xxx: với Locator nào
		// Action gì lên Locator đó: click/ sendkey/ getText/ ...

		// Multiple element: List WebElement
		List<WebElement> buttons = driver.findElements(By.className(""));
		buttons.get(0).click();
	}

	@Test
	public void TC_02_ID() {
		driver = new EdgeDriver();

		driver.get("https://hrm.ltsgroup.tech/auth/login");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Selenium Locator
		driver.findElement(By.className("ant-btn-lg")).click();

		// Verify User error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("username_help")).isDisplayed());
	}

	//@Test
	public void TC_03_Class() {
		driver = new EdgeDriver();

		driver.get("https://hrm.ltsgroup.tech/auth/login");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Refresh lại trang
		// driver.navigate().refresh();

		// Selenium Locator
		driver.findElement(By.id("username")).sendKeys("TienDM1");
		
		driver.findElement(By.id("password")).sendKeys("Passla123$");
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div/div[2]/form/button")).click();

		// Verify User error message xuất hiện
		Assert.assertTrue(driver.findElement(By.className("ant-picker-input")).isDisplayed());
	}

	//@Test
	public void TC_04_Tagname() {
		driver = new EdgeDriver();

		driver.get(
				"https://tiki.vn/khuyen-mai/sale-giua-thang-xa-kho-nua-gia?utm_source=google&utm_medium=cpc&utm_campaign=SEA_NBR_GGL_PMA_DAP_ALL_VN_ALL_UNK_UNK_C.PMAX_X.21548597496_Y._V._W.DT_A._O.CIR&gad_source=1&gclid=Cj0KCQjwiOy1BhDCARIsADGvQnB_ZslAriqyVEUnEdrnCThXmL7BJWw5UJhkHJUdQuP2Jn4bXdNC6CUaAuP8EALw_wcB");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		// Lấy hết tất cả đường link tại màn hình sau đó getText ra
		List<WebElement> loginPageLinks = driver.findElements(By.tagName("a"));

		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());
		}
	}

	//@Test
	public void TC_05_LinkText() {
		driver = new EdgeDriver();

		driver.get(
				"https://tiki.vn/khuyen-mai/sale-giua-thang-xa-kho-nua-gia?utm_source=google&utm_medium=cpc&utm_campaign=SEA_NBR_GGL_PMA_DAP_ALL_VN_ALL_UNK_UNK_C.PMAX_X.21548597496_Y._V._W.DT_A._O.CIR&gad_source=1&gclid=Cj0KCQjwiOy1BhDCARIsADGvQnB_ZslAriqyVEUnEdrnCThXmL7BJWw5UJhkHJUdQuP2Jn4bXdNC6CUaAuP8EALw_wcB");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.findElement(By.linkText("điện gia dụng")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/main/div/div/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/a")).isDisplayed());
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
