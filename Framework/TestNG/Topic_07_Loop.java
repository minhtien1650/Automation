package TestNG;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_07_Loop {
	WebDriver driver;
	String emailAddress = "afc";
	String password = "afc123456";

	@Test(invocationCount = 100)
	public void TC_01() {
		System.out.println(emailAddress + getRandomNumber() + "@mail.com");
		System.out.println(password);
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
