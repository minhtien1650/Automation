package WebDriver;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Web_Browser_Command {
	WebDriver driver;
	
	@BeforeClass
	public void BeforeClass() {
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@Test
	public void TC_01_Browser() {
		// Mở ra 1 page URL
		driver.get("https://www.messager.com/");
		
		// Đóng 1 tab đang active
		driver.close();
		
		// Đóng trình duyệt(ko care có bao nhiêu tab/window đang mở)
		driver.quit();
		
		// Lấy ra ID hiện tại của window/tab đang active
		String messagerID = driver.getWindowHandle();
		
		// Lấy ra tất cả ID của tất cả cách tab/window đang có
		Set<String> allIDs = driver.getWindowHandles();
		
		// Switch/ Nhảy đến 1 tab/window nào đó
		driver.switchTo().window(messagerID);
		
		// Tìm ra 1 element với locator nào đó
		WebElement emailTextBox = driver.findElement(By.id(""));
		
		// Tìm ra tất cả các element với locator nào đó
		List<WebElement> textboxes = driver.findElements(By.id(""));
		
		// Trả về URL của page hiện tại
		String homePageURL = driver.getCurrentUrl();
		
		// Trả về HTML source của page hiện tại
		driver.getPageSource();
	}
	
	@Test
	public void TC_02_Element() {
		
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
