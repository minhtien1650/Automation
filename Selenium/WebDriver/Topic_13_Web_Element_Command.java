package WebDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Web_Element_Command {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	TakesScreenshot scrShot;

	@BeforeClass
	public void BeforeCLass() {
		driver = new EdgeDriver();

		scrShot = ((TakesScreenshot) driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	// @Test
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

		// Xóa dữ liệu trong editable field (textbox/ textarea/ dropdown)
		element.clear(); // **

		// Nhập dữ liệu vào editable field (textbox/ textarea/ dropdown)
		element.sendKeys(""); // **
		element.sendKeys(Keys.ENTER);

		// Click vào button/ radio/ checkbox/ image/...
		element.click(); // **

		// Trả về dữ liệu nằm trong attribute của element
		element.getAttribute("placeholder"); // **
		// Email address or phone number

		// Lấy thuộc tính của element: font size/size/color/font style/...
		element.getCssValue("background-color"); // *

		// GUI
		element.getLocation();
		element.getRect();
		element.getSize();

		// Take screenshot -> Attach to HTML report
		// Java Generic
		element.getScreenshotAs(OutputType.FILE); // **
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);

		// Tên thẻ HTML
		// Dùng by.id/class/css/name
		// Đầu ra của step này là đầu vào của step kia
		element.findElement(By.cssSelector("#save-info-button"));
		String saveButtonTagName = element.getTagName();
		driver.findElement(By.xpath("//" + saveButtonTagName + "[@name='email']"));

		String addressName = "123 Ly Thuong Kiet";
		String cityName = "Ho Chi Minh City";

		System.out.println(addressName + cityName);
		System.out.println(addressName.concat(cityName));
		System.out.println(addressName + "-" + cityName);

		// Lấy text của header/link/label/error/success message
		element.getText(); // **

		// Verify dữ liệu
		// Kiểm tra element có hiển thị hay không (người dùng có thể nhìn thấy được và
		// thao tác được)
		element.isDisplayed(); // **

		// Kiểm trâ element có thao tác được hay không (không bị disable/ko phải
		// readonly field)
		element.isEnabled(); // **

		// Kiểm tra element đã được chọn hay chưa (radio/checkbox/dropdown)
		element.isSelected(); // **

		// Submit vào 1 form
		element.submit();
	}

	//@Test
	public void TC_01_isDisplay() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement emailTextBox = driver.findElement(By.id("mail"));
		WebElement under18Radio = driver.findElement(By.id("under_18"));
		WebElement eduTextArea = driver.findElement(By.id("edu"));
		WebElement user5 = driver.findElement(By.xpath("//*[contains(text(),'User5')]"));

		String userEmail = emailTextBox.getAttribute("name");
		String under18Value = under18Radio.getAttribute("value");
		String userEdu = eduTextArea.getAttribute("name");

		if (emailTextBox.isDisplayed()) {
			emailTextBox.clear();
			emailTextBox.sendKeys("Automation Testing");
			System.out.println(userEmail + " is displayed");
		} else {
			System.out.println(userEmail + " is not displayed");
		}

		if (under18Radio.isDisplayed()) {
			under18Radio.click();
			System.out.println(under18Value + " is displayed");
		} else {
			System.out.println(under18Value + " is not displayed");
		}
		
		if (eduTextArea.isDisplayed()) {
			eduTextArea.clear();
			eduTextArea.sendKeys("Automation Testing");
			System.out.println(userEdu + " is displayed");
		} else {
			System.out.println(userEdu + " is not displayed");
		}
		
		if (user5.isDisplayed()) {
			System.out.println("User5 is displayed");
		} else {
			System.out.println("User5 is not displayed");
		}
	}
	
	//@Test
	public void TC_02_isEnable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		List<WebElement> element = new ArrayList<WebElement>();
		element.add(driver.findElement(By.id("email")));
		element.add(driver.findElement(By.id("under_18")));
		element.add(driver.findElement(By.id("edu")));
		element.add(driver.findElement(By.id("job1")));
		element.add(driver.findElement(By.id("job2")));
		element.add(driver.findElement(By.id("development")));
		element.add(driver.findElement(By.id("slider-1")));
		element.add(driver.findElement(By.id("disable_password")));
		element.add(driver.findElement(By.id("radio-disabled")));
		element.add(driver.findElement(By.id("bio")));
		element.add(driver.findElement(By.id("job3")));
		element.add(driver.findElement(By.id("check-disbaled")));
		element.add(driver.findElement(By.id("slider-2")));
		
		for (WebElement webElement : element) {
			String elementName = webElement.getAttribute("id");
			if (webElement.isEnabled()) {
				System.out.println(elementName + " is enable");
			} else {
				System.out.println(elementName + " is disable");
			}
		}
	}
	
	//@Test
	public void TC_03_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement under18Radio = driver.findElement(By.id("under_18"));
		WebElement javaCheckBox = driver.findElement(By.id("java"));
		
		under18Radio.click();
		javaCheckBox.click();
		
		if (under18Radio.isSelected()) {
			System.out.println("Age Under 18 is selected");
		} else {
			System.out.println("Age Under 18 is de-selected");
		}
		
		if (javaCheckBox.isSelected()) {
			System.out.println("Java is selected");
		} else {
			System.out.println("Java is de-selected");
		}
	}

	@Test
	public void TC_04_Register_function_at_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		WebElement lowercaseCharacter, uppercaseCharacter, numberCharacter, specialCharacter, eightCharacter, usernameCheck;
		
		WebElement emailTextBox = driver.findElement(By.id("email"));
		WebElement userNameTextBox = driver.findElement(By.id("new_username"));
		WebElement passwordTextBox = driver.findElement(By.id("new_password"));
		
		
		emailTextBox.sendKeys("automationfc@gmail.com");
		emailTextBox.sendKeys(Keys.TAB);
		
		Assert.assertEquals(userNameTextBox.getAttribute("value"), emailTextBox.getAttribute("value"));
		
		// Lower case character
//		passwordTextBox.sendKeys("auto");
//		
//		lowercaseCharacter = driver.findElement(By.xpath("//*[contains(text(),'One lowercase character')]"));
//		uppercaseCharacter = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char')]"));
//		numberCharacter = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
//		specialCharacter = driver.findElement(By.xpath("//li[contains(@class,'special-char')]"));
//		eightCharacter = driver.findElement(By.xpath("//li[contains(@class,'8-char')]"));
//		usernameCheck = driver.findElement(By.xpath("//li[contains(@class,'username-check')]"));
//		
//	//	wait.until(ExpectedConditions.attributeContains(By.xpath("//li[contains(@class,'lowercase-char')]"), "class", "complete"));
////		System.out.println(rgbatoHex(driver.findElement(By.xpath("//*[contains(text(),'One lowercase character')]")).getCssValue("color")));
//		Assert.assertEquals(rgbatoHex(lowercaseCharacter.getCssValue("color")), "#008547");
//		Assert.assertEquals(rgbatoHex(uppercaseCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(numberCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(specialCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(eightCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(usernameCheck.getCssValue("color")), "#a73205");
//		
//		// Upper case character
//		passwordTextBox.clear();
//		passwordTextBox.sendKeys("AUTO");
//		
//		lowercaseCharacter = driver.findElement(By.xpath("//*[contains(text(),'One lowercase character')]"));
//		uppercaseCharacter = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char')]"));
//		numberCharacter = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
//		specialCharacter = driver.findElement(By.xpath("//li[contains(@class,'special-char')]"));
//		eightCharacter = driver.findElement(By.xpath("//li[contains(@class,'8-char')]"));
//		usernameCheck = driver.findElement(By.xpath("//li[contains(@class,'username-check')]"));
//		
//		//wait.until(ExpectedConditions.attributeContains(By.xpath("//li[contains(@class,'lowercase-char')]"), "class", "complete"));
//		
//		Assert.assertEquals(rgbatoHex(lowercaseCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(uppercaseCharacter.getCssValue("color")), "#008547");
//		Assert.assertEquals(rgbatoHex(numberCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(specialCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(eightCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(usernameCheck.getCssValue("color")), "#a73205");
//		
//		// One number
//		passwordTextBox.clear();
//		passwordTextBox.sendKeys("123456");
//		
//		lowercaseCharacter = driver.findElement(By.xpath("//*[contains(text(),'One lowercase character')]"));
//		uppercaseCharacter = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char')]"));
//		numberCharacter = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
//		specialCharacter = driver.findElement(By.xpath("//li[contains(@class,'special-char')]"));
//		eightCharacter = driver.findElement(By.xpath("//li[contains(@class,'8-char')]"));
//		usernameCheck = driver.findElement(By.xpath("//li[contains(@class,'username-check')]"));
//		
//		wait.until(ExpectedConditions.attributeContains(By.xpath("//li[contains(@class,'lowercase-char')]"), "class", "complete"));
//		
//		Assert.assertEquals(rgbatoHex(lowercaseCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(uppercaseCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(numberCharacter.getCssValue("color")), "#008547");
//		Assert.assertEquals(rgbatoHex(specialCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(eightCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(usernameCheck.getCssValue("color")), "#008547");
//		
//		// One special character
//		passwordTextBox.clear();
//		passwordTextBox.sendKeys("@@@###");
//		
//		lowercaseCharacter = driver.findElement(By.xpath("//*[contains(text(),'One lowercase character')]"));
//		uppercaseCharacter = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char')]"));
//		numberCharacter = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
//		specialCharacter = driver.findElement(By.xpath("//li[contains(@class,'special-char')]"));
//		eightCharacter = driver.findElement(By.xpath("//li[contains(@class,'8-char')]"));
//		usernameCheck = driver.findElement(By.xpath("//li[contains(@class,'username-check')]"));
//		
//		wait.until(ExpectedConditions.attributeContains(By.xpath("//li[contains(@class,'lowercase-char')]"), "class", "complete"));
//		
//		Assert.assertEquals(rgbatoHex(lowercaseCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(uppercaseCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(numberCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(specialCharacter.getCssValue("color")), "#008547");
//		Assert.assertEquals(rgbatoHex(eightCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(usernameCheck.getCssValue("color")), "#008547");
//		
//		// 8 characters minimum
//		passwordTextBox.clear();
//		passwordTextBox.sendKeys("12345678");
//		
//		lowercaseCharacter = driver.findElement(By.xpath("//*[contains(text(),'One lowercase character')]"));
//		uppercaseCharacter = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char')]"));
//		numberCharacter = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
//		specialCharacter = driver.findElement(By.xpath("//li[contains(@class,'special-char')]"));
//		eightCharacter = driver.findElement(By.xpath("//li[contains(@class,'8-char')]"));
//		usernameCheck = driver.findElement(By.xpath("//li[contains(@class,'username-check')]"));
//		
//		wait.until(ExpectedConditions.attributeContains(By.xpath("//li[contains(@class,'lowercase-char')]"), "class", "complete"));
//		
//		Assert.assertEquals(rgbatoHex(lowercaseCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(uppercaseCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(numberCharacter.getCssValue("color")), "#008547");
//		Assert.assertEquals(rgbatoHex(specialCharacter.getCssValue("color")), "#a73205");
//		Assert.assertEquals(rgbatoHex(eightCharacter.getCssValue("color")), "#008547");
//		Assert.assertEquals(rgbatoHex(usernameCheck.getCssValue("color")), "#008547");
//		
//		// All is valid
//		passwordTextBox.clear();
//		passwordTextBox.sendKeys("Abc@1234");
//		
//		lowercaseCharacter = driver.findElement(By.xpath("//*[contains(text(),'One lowercase character')]"));
//		uppercaseCharacter = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char')]"));
//		numberCharacter = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
//		specialCharacter = driver.findElement(By.xpath("//li[contains(@class,'special-char')]"));
//		eightCharacter = driver.findElement(By.xpath("//li[contains(@class,'8-char')]"));
//		usernameCheck = driver.findElement(By.xpath("//li[contains(@class,'username-check')]"));
//		
//		wait.until(ExpectedConditions.attributeContains(By.xpath("//li[contains(@class,'lowercase-char')]"), "class", "complete"));
//		
//		Assert.assertFalse(lowercaseCharacter.isDisplayed());
//		Assert.assertFalse(uppercaseCharacter.isDisplayed());
//		Assert.assertFalse(numberCharacter.isDisplayed());
//		Assert.assertFalse(specialCharacter.isDisplayed());
//		Assert.assertFalse(eightCharacter.isDisplayed());
//		Assert.assertFalse(usernameCheck.isDisplayed());
		
		String[] password = {"auto", "AUTO", "Abc@1234", "123456","abC#6789" , "@@@###", "12345678", "aBc!3456"};
		
		By lowercaseCharacterBy = By.xpath("//*[contains(text(),'One lowercase character')]");
		By uppercaseCharacterBy = By.xpath("//li[contains(@class,'uppercase-char')]");
		By numberCharacterBy = By.xpath("//li[contains(@class,'number-char')]");
		By specialCharacterBy = By.xpath("//li[contains(@class,'special-char')]");
		By eightCharacterBy = By.xpath("//li[contains(@class,'8-char')]");
		By usernameCheckBy = By.xpath("//li[contains(@class,'username-check')]");
		
		for (int i = 0; i < password.length; i++) {
			String passwordKey = password[i];
			passwordTextBox.clear();
			passwordTextBox.sendKeys(passwordKey);
			if (driver.findElement(lowercaseCharacterBy).isDisplayed()) {
				System.out.println(passwordKey);
				System.out.println(rgbatoHex(driver.findElement(lowercaseCharacterBy).getCssValue("color")));
				System.out.println(rgbatoHex(driver.findElement(uppercaseCharacterBy).getCssValue("color")));
				System.out.println(rgbatoHex(driver.findElement(numberCharacterBy).getCssValue("color")));
				System.out.println(rgbatoHex(driver.findElement(specialCharacterBy).getCssValue("color")));
				System.out.println(rgbatoHex(driver.findElement(eightCharacterBy).getCssValue("color")));
				System.out.println(rgbatoHex(driver.findElement(usernameCheckBy).getCssValue("color")));
			} else {
				System.out.println(passwordKey + " is all valid");
			}
		}
	}
	
	public static String rgbatoHex(String rgba) {
		// Tách chuỗi rgba ra làm r,g,b  
		// Ví dụ với: rgba(255, 99, 71, 1) 
		// replace("rgba(", ""): thay thế "rgba(" bằng "" nếu có 
		// -> Kết quả: 255, 99, 71, 1)
		// replace("rgb(", ""): thay thế "rgb(" bằng "" nếu có 
		// -> vì không có nên kết quả vẫn là: 255, 99, 71, 1)
		// replace(")", ""): thay thế ")" bằng "" nếu có
		// -> Kết quả: 255, 99, 71, 1
		// split(","): Tách chuỗi dựa trên dấu "," thành các phần tử riêng lẻ trong mảng
		// -> Kết quả: ["255", " 99", " 71", " 1"]
		String[] parts = rgba.replace("rgba(", "").replace("rgb(", "").replace(")", "").split(",");
		
		// Lấy giá trị số của r, g, b
		// parts[0]: Lấy phần tử đầu tiên
		// trim(): Loại bỏ khoảng trắng đầu cuối
		// Integer.parseInt(parts[0].trim()) chuyển đổi string thành int
		int r = Integer.parseInt(parts[0].trim());
		int g = Integer.parseInt(parts[1].trim());
		int b = Integer.parseInt(parts[2].trim());
		
		// Chuyển đổi giá trị sang dạng hex
		String hexR = String.format("%02x", r);
		String hexG = String.format("%02x", g);
		String hexB = String.format("%02x", b);
		
		return "#"+hexR+hexG+hexB;
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
