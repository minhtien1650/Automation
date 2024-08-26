package WebDriver;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Handle_Textbox_Textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email, name, dob, address, city, state, pin, phoneNumber, editAddress, editCity, editState, editPin,
			editPhoneNumber, editEmail;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		email = "auto" + getRandomNumber() + "@gmail.us";
		name = "Selenium Online";
		dob = "10012000";
		address = "123 Address";
		city = "Ho Chi Minh";
		state = "Thu Duc";
		pin = "123456";
		phoneNumber = "0987654321";
		editEmail = "edit" + email;
		editAddress = "123 Edit Address";
		editCity = "Edit Ho Chi Minh";
		editState = "Edit Thu Duc";
		editPin = "654321";
		editPhoneNumber = "0123456789";

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		System.out.println(email);

		driver.get("http://demo.guru99.com/");

		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		String userID = driver.findElement(By.xpath("//td[contains(.,'User ID :')]/following-sibling::td")).getText();
		String password = driver.findElement(By.xpath("//td[contains(.,'Password :')]/following-sibling::td"))
				.getText();

		System.out.println(userID);
		System.out.println(password);

		driver.get("http://demo.guru99.com/v4");

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.xpath("//input[@value='LOGIN']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText()
				.contains("Welcome To Manager's Page of Guru99 Bank"));

		driver.findElement(By.xpath("//a[contains(.,'New Customer')]")).click();

		driver.findElement(By.xpath("//input[@onkeyup='validatecustomername();']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(By.xpath("//input[@onkeyup='validatedob();']")).sendKeys(dob);
		driver.findElement(By.xpath("//textarea[@onkeyup='validateAddress();']")).sendKeys(address);
		driver.findElement(By.xpath("//input[@onkeyup='validateCity();']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@onkeyup='validateState();']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@onkeyup='validatePIN();']")).sendKeys(pin);
		driver.findElement(By.xpath("//input[@onkeyup='validateTelephone();']")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@onkeyup='validateEmail();']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@onkeyup='validatepassword();']")).sendKeys(password);

//		String mailText = driver.findElement(By.xpath("//input[@value='m']/following-sibling::text()[1]")).getText().trim();

		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//String mailText = (String) js.executeScript(
		//		"return document.evaluate(\"//input[@value='m']/following-sibling::text()[1]\", document, null, XPathResult.STRING_TYPE, null).stringValue;");

		String gender = driver.findElement(By.xpath("//input[@value='m']/parent::td")).getText().trim();

		String[] parts = gender.Split("\n");

		driver.findElement(By.cssSelector("input[value='Submit']")).click();

		String customerID = driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]//following-sibling::td"))
				.getText();

		System.out.println(customerID);

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[contains(.,'Customer Name')]/following-sibling::td")).getText(),
				name);
		//Assert.assertEquals(driver.findElement(By.xpath("//td[contains(.,'Gender')]/following-sibling::td")).getText(),
		//		mailText.trim());
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(.,'Gender')]/following-sibling::td")).getText(),
				parts[1]);
		Assert.assertEquals(
				convertDate(
						driver.findElement(By.xpath("//td[contains(.,'Birthdate')]/following-sibling::td")).getText()),
				dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(.,'Address')]/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(.,'City')]/following-sibling::td")).getText(),
				city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(.,'State')]/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(.,'Pin')]/following-sibling::td")).getText(),
				pin);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[contains(.,'Mobile No.')]/following-sibling::td")).getText(),
				phoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(.,'Email')]/following-sibling::td")).getText(),
				email);

		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td/input"))
				.getAttribute("value"), name);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea")).getText(),
				address);

		driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea")).clear();
		driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea")).sendKeys(editAddress);
		driver.findElement(By.xpath("//td[text()='City']/following-sibling::td/input")).clear();
		driver.findElement(By.xpath("//td[text()='City']/following-sibling::td/input")).sendKeys(editCity);
		driver.findElement(By.xpath("//td[text()='State']/following-sibling::td/input")).clear();
		driver.findElement(By.xpath("//td[text()='State']/following-sibling::td/input")).sendKeys(editState);
		driver.findElement(By.xpath("//td[text()='PIN']/following-sibling::td/input")).clear();
		driver.findElement(By.xpath("//td[text()='PIN']/following-sibling::td/input")).sendKeys(editPin);
		driver.findElement(By.xpath("//td[contains(.,'Mobile Number')]/following-sibling::td/input")).clear();
		driver.findElement(By.xpath("//td[contains(.,'Mobile Number')]/following-sibling::td/input"))
				.sendKeys(editPhoneNumber);
		driver.findElement(By.xpath("//td[text()='E-mail']/following-sibling::td/input")).clear();
		driver.findElement(By.xpath("//td[text()='E-mail']/following-sibling::td/input")).sendKeys(editEmail);

		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		driver.switchTo().alert().accept();

		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea")).getText(),
				editAddress);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='City']/following-sibling::td/input")).getAttribute("value"),
				editCity);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='State']/following-sibling::td/input")).getAttribute("value"),
				editState);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='PIN']/following-sibling::td/input")).getAttribute("value"),
				editPin);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[contains(.,'Mobile Number')]/following-sibling::td/input"))
						.getAttribute("value"),
				editPhoneNumber);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='E-mail']/following-sibling::td/input")).getAttribute("value"),
				editEmail);
	}

	public static String convertDate(String dateStr) {
		String[] parts = dateStr.split("-");
		String year = parts[0];
		String month = parts[1];
		String day = parts[2];
		return day + month + year;
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
