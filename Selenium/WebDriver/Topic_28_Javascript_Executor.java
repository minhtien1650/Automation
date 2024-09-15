package WebDriver;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Javascript_Executor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		jsExecutor = (JavascriptExecutor) driver;

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Techpanda() {
		navigateToUrlByJS("http://live.techpanda.org/");

		String domain = getDomain();

		Assert.assertEquals(domain, "live.techpanda.org");

		String url = getURL();

		Assert.assertEquals(url, "http://live.techpanda.org/");

		clickToElementByJS(By.xpath("//a[text()='Mobile']"));
		clickToElementByJS(By.xpath(
				"//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//span/span"));

		Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		String customerServiceURL = getAttributeValue(By.xpath("//a[text()='Customer Service']"), "href");

		navigateToUrlByJS(customerServiceURL);

		Assert.assertTrue(getTitle().contains("Customer Service"));

		scrollToElementOnBot(By.id("newsletter"));

		sendKeysToElementByJS(By.id("newsletter"), "automationFC@gmail.io");

		clickToElementByJS(By.cssSelector("button[title='Subscribe']"));

		Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));

		navigateToUrlByJS("https://www.facebook.com/");

		Assert.assertEquals(getDomain(), "facebook.com");
	}

	// @Test
	public void TC_02_Verify_HTML5_Validation_Message() {
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");

		By nameTextboxBy = By.id("fname");
		By passowrdTextboxBy = By.id("pass");
		By emailTextboxBy = By.id("em");
		By addressTextboxBy = By.cssSelector("fieldset select");

		clickToElementByJS(By.cssSelector("input[type='submit']"));

		String nameValidationMessage = getValidationMessageHTML5(nameTextboxBy);

		Assert.assertEquals(nameValidationMessage, "Please fill out this field.");

		sendKeysToElementByJS(nameTextboxBy, "Tien Dang");

		clickToElementByJS(By.cssSelector("input[type='submit']"));

		String passwordValidationMessage = getValidationMessageHTML5(passowrdTextboxBy);

		Assert.assertEquals(passwordValidationMessage, "Please fill out this field.");

		sendKeysToElementByJS(passowrdTextboxBy, "123456");

		clickToElementByJS(By.cssSelector("input[type='submit']"));

		String emailValidationMessage = getValidationMessageHTML5(emailTextboxBy);

		Assert.assertEquals(emailValidationMessage, "Please fill out this field.");

		sendKeysToElementByJS(emailTextboxBy, "123!@#$");

		clickToElementByJS(By.cssSelector("input[type='submit']"));

		emailValidationMessage = getValidationMessageHTML5(emailTextboxBy);

		Assert.assertEquals(emailValidationMessage, "A part following '@' should not contain the symbol '#'.");

		sendKeysToElementByJS(emailTextboxBy, "automation@gmail.io");

		clickToElementByJS(By.cssSelector("input[type='submit']"));

		String addressValidationMessage = getValidationMessageHTML5(addressTextboxBy);

		Assert.assertEquals(addressValidationMessage, "Please select an item in the list.");
	}

	//@Test
	public void TC_03_Verify_HTML5_Validation_Message() {
		navigateToUrlByJS("https://login.ubuntu.com/");
		
		By loginButton = By.cssSelector("button[data-qa-id='login_button']");
		By emailTextboxBy = By.id("id_email");
		By passowrdTextboxBy = By.id("id_password");

		clickToElementByJS(loginButton);

		String emailValidationMessage = getValidationMessageHTML5(emailTextboxBy);

		Assert.assertEquals(emailValidationMessage, "Please fill out this field.");
		
		sendKeysToElementByJS(emailTextboxBy, "a");
		
		clickToElementByJS(loginButton);
		
		emailValidationMessage = getValidationMessageHTML5(emailTextboxBy);
		
		Assert.assertEquals(emailValidationMessage, "Please include an '@' in the email address. 'a' is missing an '@'.");

		sendKeysToElementByJS(emailTextboxBy, "automation@gmail.io");

		clickToElementByJS(loginButton);

		String passwordValidationMessage = getValidationMessageHTML5(passowrdTextboxBy);

		Assert.assertEquals(passwordValidationMessage, "Please fill out this field.");
	}
	
	//@Test
	public void TC_04_Remove_Attribute() {
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		
		clickToElementByJS(By.xpath("//a[text()='here']"));
		
		int randomNumber = getRandomNumber();
		String email = "automation" + randomNumber + "@gmail.us";
		
		sendKeysToElementByJS(By.cssSelector("input[name='emailid']"), email);
		
		clickToElementByJS(By.cssSelector("input[name='btnLogin']"));
		
		String userID = getTextByJS(By.xpath("//td[text()='User ID :']//following-sibling::td"));
		String password = getTextByJS(By.xpath("//td[text()='Password :']//following-sibling::td"));
		
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		
		sendKeysToElementByJS(By.cssSelector("input[name='uid']"), userID);
		sendKeysToElementByJS(By.cssSelector("input[name='password']"), password);
		
		clickToElementByJS(By.cssSelector("input[name='btnLogin']"));
		
		clickToElementByJS(By.xpath("//a[text()='New Customer']"));
		
		String name = "test";
		String date = "30/04/1975";
		String address = "123 Address";
		String city = "Ho Chi Minh";
		String state = "Thu Duc";
		String pin = "123456";
		String phoneNumber = "0987654321";
		
		removeAttribute(By.cssSelector("input[name='dob']"), "type");
		sleepInSecond(2);

//		sendKeysToElementByJS(By.cssSelector("input[name='name']"), name);
//		sendKeysToElementByJS(By.cssSelector("input[name='dob']"), date);
//		sendKeysToElementByJS(By.cssSelector("textarea[name='addr']"), address);
//		sendKeysToElementByJS(By.cssSelector("input[name='city']"), city);
//		sendKeysToElementByJS(By.cssSelector("input[name='state']"), state);
//		sendKeysToElementByJS(By.cssSelector("input[name='pinno']"), pin);
//		sendKeysToElementByJS(By.cssSelector("input[name='telephoneno']"), phoneNumber);
//		sendKeysToElementByJS(By.cssSelector("input[name='emailid']"), email);
//		sendKeysToElementByJS(By.cssSelector("input[name='password']"), password);

		driver.findElement(By.cssSelector("input[name='name']")).sendKeys(name);
		driver.findElement(By.cssSelector("input[name='dob']")).sendKeys(date);
		driver.findElement(By.cssSelector("textarea[name='addr']")).sendKeys(address);
		driver.findElement(By.cssSelector("input[name='city']")).sendKeys(city);
		driver.findElement(By.cssSelector("input[name='state']")).sendKeys(state);
		driver.findElement(By.cssSelector("input[name='pinno']")).sendKeys(pin);
		driver.findElement(By.cssSelector("input[name='telephoneno']")).sendKeys(phoneNumber);
		driver.findElement(By.cssSelector("input[name='emailid']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea[name='addr']")).getText(), "1975-04-30");
		
		clickToElementByJS(By.cssSelector("input[name='sub']"));
	}

	public Object executeJavascript(String javascript) {
		return jsExecutor.executeScript(javascript);
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public String getDomain() {
		return (String) jsExecutor.executeScript("return document.domain");
	}

	public String getURL() {
		return jsExecutor.executeScript("return document.URL").toString();
	}

	public String getTitle() {
		return jsExecutor.executeScript("return document.title").toString();
	}

	public void clickToElementByJS(By by) {
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
		sleepInSecond(1);
	}

	public boolean isExpectedTextInInnerText(String expectedText) {
		return (boolean) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0];").toString()
				.equals(expectedText);
	}

	public String getAttributeValue(By by, String attribute) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attribute + "')",
				driver.findElement(by));
	}

	public void scrollToBottomOfPage() {
		jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}

	public void scrollToElementOnTop(By by) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(by));
	}

	public void scrollToElementOnBot(By by) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false)", driver.findElement(by));
	}

	public void sendKeysToElementByJS(By by, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "');", driver.findElement(by));
	}

	public String getValidationMessageHTML5(By by) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage", driver.findElement(by));
	}
	
	public void removeAttribute(By by, String attribute) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attribute + "')", driver.findElement(by));
	}
	
	public String getTextByJS(By by) {
		return (String) jsExecutor.executeScript("return arguments[0].innerText", driver.findElement(by));
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	@AfterClass
	public void AfterClass() {
		//driver.quit();
	}
}
