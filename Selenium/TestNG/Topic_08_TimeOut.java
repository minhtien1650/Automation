package TestNG;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_08_TimeOut {
	WebDriver driver;
	String emailAddress = "afc";
	String password = "afc123456";

	@Test(timeOut = 1000)
	public void TC_01() throws InterruptedException {
		Thread.sleep(2000);
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
