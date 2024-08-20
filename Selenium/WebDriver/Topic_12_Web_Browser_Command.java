package WebDriver;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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

	//@Test
	public void Browser() {
		// Mở ra 1 page URL
		driver.get("https://www.messenger.com/");	//*
		
		// Đóng 1 tab đang active
		driver.close();
		
		// Đóng trình duyệt(ko care có bao nhiêu tab/window đang mở)
		driver.quit();	//*
		
		// Lấy ra ID hiện tại của window/tab đang active
		String messagerID = driver.getWindowHandle();
		
		// Lấy ra tất cả ID của tất cả cách tab/window đang có
		Set<String> allIDs = driver.getWindowHandles();
		
		// Switch/ Nhảy đến 1 tab/window nào đó
		driver.switchTo().window(messagerID);	//*
		
		// Tìm ra 1 element với locator nào đó
		WebElement emailTextBox = driver.findElement(By.id(""));	//*
		
		// Tìm ra tất cả các element với locator nào đó
		List<WebElement> textboxes = driver.findElements(By.id(""));	//*
		
		// Trả về URL của page hiện tại
		String homePageURL = driver.getCurrentUrl();	//*
		
		// Trả về HTML source của page hiện tại
		String homePageSource = driver.getPageSource();

		// Trả về page title của page hiện tại
		String homePageTitle = drive.getTitle();

		// Get/xóa cookie của page
		// Build framework: Share state of Class
		// Get cookie sau khi login xong -> Truyền vào các Class khác -> Reduce time login cho từng Class
		driver.manage().deleteALlCookies();

		// Build framework: Get ra log của browser
		driver.manage().logs().getAvailableLogTypes();

		// Chờ cho việc tìm Element (findElement/findElements)
		// 1000ms = 1s
		// WabDriver Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSecond(15));	//*
		driver.manage().timeouts().implicitlyWait(Duration.ofMiliSecond(15000));

		// Chờ cho 1 page được load thành công (Option)
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSecond(15));

		// Chờ cho 1 script được execute thành công (Option)
		// Javascript Executor
		driver.manage().timeouts().setScriptTimeout(Duration.ofSecond(15));

		// Mở browser full màn hình (chế độ F11)
		driver.manage().window().fullscreen();

		// Maximize màn hình
		driver.manage().window().maximize();	//*

		// Lấy ra vị trí hiện tại của browser
		driver.manage().window().getPosition();

		// Set vào cho browser tại vị trí nào đó
		driver.manage().window().setPosition(new Point(0, 0));

		// Lấy ra kích thước hiện tại của browser (rộng/cao)
		Dimension browserSize = driver.manage().window().getSize();
		driver.manage().window().setSize(new Dimension(768, 680));

		// Back to page
		driver.navigate().back();

		// Forward to page
		driver.navigate().forward();

		// Tải lại trang
		driver.navigate().refresh();

		// Keep history
		driver.navigate().to("https://www.messenger.com/");

		// Windows/Tabs
		// Alert
		// Frame/Iframe
		driver.switchTo().alert();	//*
		driver.switchTo().window("");	//*
		driver.swithcTo().frame("");	//*
	}
	
	@Test
	public void TC_01_Verify_Url() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String urlLoginPage = driver.manage().getCurrentUrl();
		System.out().println("Login Page URL: " + urlLoginPage);

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String urlRegisterPage = driver.manage().getCurrentUrl();
		System.out().println("Register Page URL: " + urlRegisterPage);
	}

	@Test
	public void TC_02_Verify_Title(){
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String titleLoginPage = driver.getTitle();
		Assert.assertEquals(titleLoginPage, "Customer Login");

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String titleRegisterPage = driver.getTitle();
		Assert.assertEquals(titleRegisterPage, "Create New Customer Account");
	}

	@Test
	public void TC_03_Navigate_function(){
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/customer/account/create/");

		driver.navigate().back();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

		driver.navigate().forward();

		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_04_Page_Source(){
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Create an Account"));
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
