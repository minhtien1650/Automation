package Basic;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_09_Xpath_CSS_II {
	WebDriver driver;
	String name, emailAddress, password, phone;

	// Action Field
	By nameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmPasswordTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//form[@id=\"frmLogin\"]//button");

	// Error
	By nameErrorBy = By.id("txtFirstname-error");
	By emailErrorBy = By.id("txtEmail-error");
	By confirmEmailErrorBy = By.id("txtCEmail-error");
	By passwordErrorBy = By.id("txtPassword-error");
	By confirmPasswordErrorBy = By.id("txtCPassword-error");
	By phoneErrorBy = By.id("txtPhone-error");

	@BeforeClass
	public void beforeClass() {
		driver = new EdgeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		name = "John Wick";
		emailAddress = "automation@gmail.net";
		password = "123456";
		phone = "0987666555";
	}

	@BeforeMethod
	public void BeforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void Lesson() {
		driver.get("https://automationfc.github.io/basic-form/");

		// 1 - Tìm tương đối (contains)
		// cpntains(text(),"")
		// - Text nó nằm trên chính node chứa đó
		// - Dạng nested text nhưng text phải nằm ở index đầu tiên
		// - Text này có khoảng trắng/xuống dòng/tab ở đầu hoặc cuối text vẫn work đước
		// Tìm kiếm tên Michael Jackson bằng phương pháp này cho ra 4 kết quả
		Assert.assertEquals(driver.findElements(By.xpath("//h5[contains(text(),'Michael Jackson')]")).size(), 4);

		// contain(.,"")
		// - Text nó nằm ở chính node đó
		// - hoặc nằm ở trong child node bất kỳ vị trí nào
		// - hoặc nằm trong nested text ở bất kỳ vị trí nào
		// - Text này có khoảng trắng/xuống dòng/tab ở đầu hoặc cuối text vẫn work đước
		// Tìm kiếm tên Michael Jackson bằng phương pháp này cho ra 8 kết quả
		Assert.assertEquals(driver.findElements(By.xpath("//h5[contains(.,'Michael Jackson')]")).size(), 8);

		// contain(string(),""): tương đương cách dùng contain(.,"")
		// Tìm kiếm tên Michael Jackson bằng phương pháp này cho ra 8 kết quả
		Assert.assertEquals(driver.findElements(By.xpath("//h5[contains(string(),'Michael Jackson')]")).size(), 8);

		// 2 - Nối chuỗi (concat)
		// Dùng trong trường hợp Text vừa có dấu nháy đôi vừa có dấu nháy đơn
		// Với text (Hello "John", What's happened?)
		// cần chia ra làm 2 chuỗi ('Hello "John", What') và ("'s happened?")
		// dùng concat để nối 2 chuỗi với nhau
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[text()=concat('Hello \"John\", What',\"'s happened?\")]")).getText(),
				"Hello \"John\", What's happened?");
	}

	 @Test
	public void TC_01_Verify_Text() {
		driver.get("https://automationfc.github.io/basic-form/");

		// 1 - Get text của Element đó ra 1 biết
		// Biết này để kiểm tra chứa Text mong muốn hay không -> Java String (contains)
		String populationValue = driver.findElement(By.xpath("//div[@id='population']")).getText();
		System.out.println(populationValue);
		Assert.assertTrue(populationValue.contains("Mongolia: 500-1,000"));

		// 2 - Xpath check contains text có nằm trong element đó không
		// Check display cái element có xpath đó (isDisplay)
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id ='population' and contains(.,'Mongolia: 500-1,000')]"))
				.isDisplayed());
	}

	 @Test
	public void TC_02_Empty() {
		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(nameErrorBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorBy).getText(), "Vui lòng nhập số điện thoại.");
	}

	 @Test
	public void TC_03_Invalid_Email() {
		// 123@123.234@
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys("123@123.234@");
		driver.findElement(confirmEmailTextboxBy).sendKeys("123@123.234@");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(emailErrorBy).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailErrorBy).getText(), "Email nhập lại không đúng");
	}

	 @Test
	public void TC_04_Incorrect_Confirm_Email() {
		// Email nhập lại không đúng
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys("automation@gmai.com");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(confirmEmailErrorBy).getText(), "Email nhập lại không đúng");
	}

	 @Test
	public void TC_05_Password_less_than_6_chars() {
		// Mật khẩu ít hơn 6 ký tự
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123");
		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(passwordErrorBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}

	 @Test
	public void TC_06_Incorrect_Confirm_Password() {
		// Mật khẩu nhập lại không đúng
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys("654321");
		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(confirmPasswordErrorBy).getText(), "Mật khẩu bạn nhập không khớp");
	}

	 @Test
	public void TC_07_Invalid_Phone() {
		// Vui lòng nhập số
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(emailAddress);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(phoneErrorBy).getText(), "Vui lòng nhập con số");

		// Clear dữ liệu để nhập lại
		driver.findElement(phoneTextboxBy).clear();

		// 0987654
		// Số điện thoại phải từ 10-11 số
		driver.findElement(phoneTextboxBy).sendKeys("0987654");

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(phoneErrorBy).getText(), "Số điện thoại phải từ 10-11 số.");

		// 098765432145
		// Số điện thoại phải từ 10-11 số
		driver.findElement(phoneTextboxBy).clear();

		driver.findElement(phoneTextboxBy).sendKeys("098765432145");

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(phoneErrorBy).getText(), "Số điện thoại phải từ 10-11 số.");

		// 123
		// Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 -
		// 07 - 08
		driver.findElement(phoneTextboxBy).clear();

		driver.findElement(phoneTextboxBy).sendKeys("123");

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(phoneErrorBy).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
