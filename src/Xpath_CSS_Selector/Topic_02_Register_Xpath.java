package Xpath_CSS_Selector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Register_Xpath {
	WebDriver driver;

	//@Test
	public void TC_01_Register_with_empty_data() {
		driver = new EdgeDriver();

		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.xpath("//button[@class = 'btn_pink_sm fs16']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtFirstname-error']")).getText(),
				"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtEmail-error']")).getText(),
				"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtCEmail-error']")).getText(),
				"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtPassword-error']")).getText(),
				"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtCPassword-error']")).getText(),
				"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtPhone-error']")).getText(),
				"Vui lòng nhập số điện thoại.");
		
		driver.quit();
	}

	// @Test
	public void TC_02_Register_with_invalid_email() {
		driver = new EdgeDriver();

		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Minh Tiến");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("abc@123@456");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@123@456");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");

		driver.findElement(By.xpath("//button[@class = 'btn_pink_sm fs16']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtEmail-error']")).getText(),
				"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtCEmail-error']")).getText(),
				"Email nhập lại không đúng");
		
		driver.quit();
	}
	
	//@Test
	public void TC_03_Register_with_incorrect_confirm_email() {
		driver = new EdgeDriver();
		
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Minh Tiến");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@gmail.net");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");
		
		driver.findElement(By.xpath("//button[@class = 'btn_pink_sm fs16']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtCEmail-error']")).getText(),
				"Email nhập lại không đúng");
		
		driver.quit();
	}
	
	//@Test
	public void TC_04_Register_with_password_less_than_6_characters() {
		driver = new EdgeDriver();
		
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Minh Tiến");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");
		
		driver.findElement(By.xpath("//button[@class = 'btn_pink_sm fs16']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtPassword-error']")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtCPassword-error']")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
		
		driver.quit();
	}
	
	//@Test
	public void TC_05_Register_with_incorrect_confirm_password() {
		driver = new EdgeDriver();
		
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Minh Tiến");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123789");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");
		
		driver.findElement(By.xpath("//button[@class = 'btn_pink_sm fs16']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtCPassword-error']")).getText(),
				"Mật khẩu bạn nhập không khớp");
		
		driver.quit();
	}
	
	@Test
	public void TC_06_Register_with_invalid_Phone_number() {
		driver = new EdgeDriver();
		
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Minh Tiến");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("01234567");
		
		driver.findElement(By.xpath("//button[@class = 'btn_pink_sm fs16']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtPhone-error']")).getText(),
				"Số điện thoại phải từ 10-11 số.");
		
		driver.quit();
	}
	
	@Test
	public void TC_07_Register_with_invalid_mobile_networ_prefixes() {
		driver = new EdgeDriver();

		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Minh Tiến");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("123456789");

		driver.findElement(By.xpath("//button[@class = 'btn_pink_sm fs16']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id = 'txtPhone-error']")).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
		
		driver.quit();
	}
}