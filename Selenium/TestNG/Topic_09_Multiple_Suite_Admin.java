package TestNG;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_09_Multiple_Suite_Admin {

	@Test()
	public void TC_01() {
		System.out.println("Run Admin Mode");
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
