package WebDriver;

import java.time.Duration;
import java.util.Random;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Handle_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;

	@BeforeClass
	public void BeforeClass() {
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		String firtName = "Automation";
		String lastName = "FC";
		String emailAddress = "autofc" + getRandomNumber() + "@gmail.net";
		String day = "30";
		String month = "April";
		String year = "1975";
		String company = "Automation VN";
		String password = "123456";
		
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firtName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		
		// Chọn 1 item
		select.selectByVisibleText(day);
		
		// Bỏ chọn 1 item
		//select.deselectByVisibleText("15");
		
		// Kiểm tra dropdown này có phải multiple hay không
		Assert.assertFalse(select.isMultiple());
		
		// Kiểm tra xem chọn đúng item chưa
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		// Get ra tổng số item trong dropdown là bao nhiêu
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		
		select.selectByVisibleText(month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		
		select.selectByVisibleText(year);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(company);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
	}

	@Test
	public void TC_02_Rode(){
		driver.get("https://www.rode.com/wheretobuy");

		select = new Select(driver.findElement(By.id("country")));
		Assert.assertFalse(select.isMultiple());

		select.selectByVisibleText("Vietnam");
		driver.findElement(By.id("map_search_query")).sendKeys("HO CHI MINH");

		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();

		List<WebElement> dealers = driver.findElements(By.xpath("//h3[contains(text(),'Dealers')]/following-sibling::div//h4"));

		Assert.assertEquals(dealers.size(), 16);

		for(WebElement dealerName : dealers){
			System.out.println(dealerName.getText());
		}
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
