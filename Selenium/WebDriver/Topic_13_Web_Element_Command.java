package WebDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Web_Element_Command {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeCLass() {
		driver = new EdgeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@Test
	public void ElementCommand() {
		// WebBrowser command/ method/ API
		// driver instance/ variable
		
		// WebBrowser command/ method/ API
		// 1
		driver.findElement(By.name("Login")).click();
		
		// 2
		WebElement emailTextBox = driver.findElement(By.id("email"));
		emailTextBox.clear();
		emailTextBox.sendKeys("abc@gmail.com");
		emailTextBox.isDisplayed();
		
		WebElement element = driver.findElement(By.id(""));
		
		// Xóa dữ liệu
		element.clear();
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
