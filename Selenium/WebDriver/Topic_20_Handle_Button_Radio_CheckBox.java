package WebDriver;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Handle_Button_Radio_CheckBox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String email, password;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		jsExecutor = (JavascriptExecutor) driver;

		email = "autofc" + getRandomNumber() + "@gmail.net";
		password = "123456";

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Fahasa() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.xpath("//a[text()='Đăng nhập']/parent::li")).click();

		driver.findElement(By.cssSelector("#login_username")).sendKeys(email);
		driver.findElement(By.cssSelector("#login_password")).sendKeys(password);

		Assert.assertTrue(isElementEnable(By.cssSelector(".fhs-btn-login")));

		isElementColor(By.cssSelector(".fhs-btn-login"), "background-color", "#C92127");
	}

	//@Test
	public void TC_02_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.xpath("//a[text()='Đăng nhập']/parent::li")).click();

		By loginButton = By.cssSelector(".fhs-btn-login");

		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		driver.findElement(By.cssSelector("#login_username")).sendKeys(email);
		driver.findElement(By.cssSelector("#login_password")).sendKeys(password);
		sleepInSecond(1);

		Assert.assertTrue(driver.findElement(loginButton).isEnabled());

		driver.navigate().refresh();

		driver.findElement(By.xpath("//a[text()='Đăng nhập']/parent::li")).click();

		// Remove disable attribute trong button
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButton));

		driver.findElement(loginButton).click();

		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='fhs-input-box checked-error']//label[text()='Số điện thoại/Email']//following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='fhs-input-box fhs-input-display checked-error']//label[text()='Mật khẩu']//following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
	}

	//@Test
	public void TC_03_Radio_Default(){
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		By petrolTwo = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

		//Verify Petrol 2.0 chưa được chọn
		Assert.assertFalse(driver.findElement(petrolTwo).isSelected());
		
		driver.findElement(petrolTwo).click();

		//Verify Petrol 2.0 được chọn
		Assert.assertTrue(driver.findElement(petrolTwo).isSelected());

		driver.findElement(petrolTwo).click();

		//Verify Petrol 2.0 được chọn
		Assert.assertTrue(driver.findElement(petrolTwo).isSelected());

		By dieselTwo = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::span/input");

		//Verify Diesel 2.0 chưa được chọn
		Assert.assertFalse(driver.findElement(dieselTwo).isSelected());
		
		driver.findElement(dieselTwo).click();

		//Verify Diesel 2.0 được chọn
		Assert.assertTrue(driver.findElement(dieselTwo).isSelected());
		//Verify Petrol 2.0 chưa được chọn
		Assert.assertFalse(driver.findElement(petrolTwo).isSelected());

		By petrolThree = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::span/input");

		Assert.assertFalse(driver.findElement(petrolThree).isEnabled());
	}

	//@Test
	public void TC_04_Checkbox_Default(){
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");

		checkToCheckbox(rearSideCheckbox);

		//Verify rear side checkbox selected
		Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());

		By lugage = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::span/input");

		//Verify lugage checkbox deselected
		Assert.assertFalse(driver.findElement(lugage).isSelected());

		checkToCheckbox(lugage);

		//Verify lugage checkbox selected
		Assert.assertTrue(driver.findElement(lugage).isSelected());

		uncheckToCheckbox(lugage);

		//Verify lugage checkbox deselected
		Assert.assertFalse(driver.findElement(lugage).isSelected());
	}
	
	//@Test
	public void TC_05_Radio_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		By winterRadioButton = By.xpath("//input[@value='Winter']");
		
		//driver.findElement(winterRadioButton).click();
		
		clickElementByJS(winterRadioButton);
		
		Assert.assertTrue(driver.findElement(winterRadioButton).isSelected());
//		
//		driver.get("https://login.ubuntu.com/");
//		
//		By dontHaveUOAccountRadioBy = By.cssSelector(".new-user");
//		
//		driver.findElement(dontHaveUOAccountRadioBy).click();
	}
	
	//@Test
	public void TC_06_Checkbox_Custom() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		By checkedCheckbox = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
		By indeterminateCheckbox = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");
		
		checkToCheckboxByJS(checkedCheckbox);
		checkToCheckboxByJS(indeterminateCheckbox);
		
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
		
		uncheckToCheckboxByJS(checkedCheckbox);
		uncheckToCheckboxByJS(indeterminateCheckbox);
		
		Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
	}
	
	//@Test
	public void TC_07_Radio_Checkbox_Google_Docs() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By canThoRadioBy = By.xpath("//div[@data-value='Cần Thơ']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		Assert.assertEquals(driver.findElement(canThoRadioBy).getAttribute("aria-checked"), "false");
		
		checkToCheckbox(canThoRadioBy);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")).isDisplayed());
		Assert.assertEquals(driver.findElement(canThoRadioBy).getAttribute("aria-checked"), "true");
		
		By checkboxBy = By.xpath("//div[@role='checkbox']");
		
		checkToMultiCheckbox(checkboxBy);
	}
	
	//@Test
	public void TC_08_Check_By_Customer_Email() {
		driver.get("http://live.techpanda.org/index.php/backendlogin");
		
		driver.findElement(By.id("username")).sendKeys("user01");
		driver.findElement(By.id("login")).sendKeys("guru99com");
		driver.findElement(By.className("form-button")).click();
		
		sleepInSecond(5);
		
		checkByCutomerEmail("test193@gmail.com");
		checkByCutomerEmail("automation8011@gmail.net");
		checkByCutomerEmail("automation7517@gmail.net");
	}
	
	public void checkByCutomerEmail(String email) {
		WebElement customerEmailCheckbox = driver.findElement(By.xpath("//td[contains(text(),'" + email + "')]/preceding-sibling::td/input"));
		
		if(!customerEmailCheckbox.isSelected()) {
			customerEmailCheckbox.click();
		}
	}
	
	public void checkToMultiCheckbox(By by) {
		List<WebElement> elements = driver.findElements(by);
		
		for (WebElement element : elements) {
			Assert.assertEquals(element.getAttribute("aria-checked"), "false");
			
			element.click();
			
			Assert.assertEquals(element.getAttribute("aria-checked"), "true");
		}
	}
	
	public void checkToCheckboxByJS(By by) {
		if(!driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
		}
	}
	
	public void uncheckToCheckboxByJS(By by) {
		if(driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
		}
	}
	
	public void clickElementByJS(By by) {
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
	}

	public void checkToCheckbox(By by){
		if(!driver.findElement(by).isSelected()){
			driver.findElement(by).click();
		}
	}

	public void uncheckToCheckbox(By by){
		if(driver.findElement(by).isSelected()){
			driver.findElement(by).click();
		}
	}

	public boolean isElementColor(By by, String css, String value) {
		String rgbColor = driver.findElement(by).getCssValue(css);
		Color color = Color.fromString(rgbColor);
		String hexColor = color.asHex().toUpperCase();
		if (hexColor == value) {
			return true;
		} else {
			return false;
		}
	}

	public void sleepInSecond(long sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isElementEnable(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			return true;
		} else {
			return false;
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
