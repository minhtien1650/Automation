package WebDriver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_34_WebDriver_Wait_IV_Mix_Implicit_Explicit {
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

	@Test
	public void Lesson() {
		// Nếu tìm thấy element thì hoạt động bình thường tìm được thì sẽ kết thúc

		// Nếu không tìm thấy element thì:
		
		// - Nếu dùng vivibilityOfElementLocated(By): sẽ thực hiện Implicit để
		// findElement trước sau đó 0.5 ~ 2(3) giây sau sẽ thực hiện explicit
		
		// - Nếu sử dụng visibilityOf(WebElement): sẽ thực hiện Implicit để findElement
		// trước sau khi hết thời gian Implicit mới thực hiện Explicit
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
