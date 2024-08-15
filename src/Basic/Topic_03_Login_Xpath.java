package Basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Login_Xpath {
	WebDriver driver;
	
  @Test
  public void TC_01_Login_with_empty_email_password() {
	  driver = new EdgeDriver();
	  
	  driver.get("http://live.techpanda.org/");
	  
	  driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
	  driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).click();
	  
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
	  
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");
  }
}
