package WebDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_29_Upload_File_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_sendKeys() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		String dienBienPhuName = "dien bien phu.jpg";
		String haNoiName = "hanoi.jpg";
		String ninhBinhName = "ninh binh.jpg";

		String dienBienPhuPath = projectPath + "\\Upload Files\\" + dienBienPhuName;
		String haNoiPath = projectPath + "\\Upload Files\\" + haNoiName;
		String ninhBinhPath = projectPath + "\\Upload Files\\" + ninhBinhName;

		// single file
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(dienBienPhuPath);

		List<WebElement> uploadFiles = driver.findElements(By.cssSelector("tr.template-upload button.start"));

		for (WebElement uploadFile : uploadFiles) {
			uploadFile.click();
		}

		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
				driver.findElements(By.cssSelector("tbody.files div.progress-bar-success"))));

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + dienBienPhuName + "']")).isDisplayed());

		// multiple files
		String multiple = haNoiPath + "\n" + ninhBinhPath;

		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(multiple);

		uploadFiles = driver.findElements(By.cssSelector("tr.template-upload button.start"));

		for (WebElement uploadFile : uploadFiles) {
			uploadFile.click();
		}
		
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
				driver.findElements(By.cssSelector("tbody.files div.progress-bar-success"))));

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + haNoiName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + ninhBinhName + "']")).isDisplayed());
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
