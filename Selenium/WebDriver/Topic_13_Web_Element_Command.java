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
		
		WebElement emailTextBox = driver.findElement(By.id("email"));
		WebElement userNameTextBox = driver.findElement(By.id("new_username"));
		WebElement passwordTextBox = driver.findElement(By.id("new_password"));
		
		
		emailTextBox.sendKeys("automationfc@gmail.com");
		emailTextBox.sendKeys(Keys.TAB);
		
		Assert.assertEquals(userNameTextBox.getAttribute("value"), emailTextBox.getAttribute("value"));
		
		passwordTextBox.sendKeys("1a");
		WebElement lowercaseCharacter = driver.findElement(By.xpath("//ul[@aria-label='Password helper text']/li[1]"));
		WebElement uppercaseCharacter = driver.findElement(By.xpath("//ul[@aria-label='Password helper text']/li[2]"));
		WebElement numberCharacter = driver.findElement(By.xpath("//ul[@aria-label='Password helper text']/li[3]"));
		WebElement specialCharacter = driver.findElement(By.xpath("//ul[@aria-label='Password helper text']/li[4]"));
		WebElement eightCharacter = driver.findElement(By.xpath("//ul[@aria-label='Password helper text']/li[5]"));
		WebElement usernameCheck = driver.findElement(By.xpath("//ul[@aria-label='Password helper text']/li[6]"));
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
