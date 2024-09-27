package WebDriver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_30_Upload_File_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	
	String dienBienPhuName = "dien bien phu.jpg";
	String haNoiName = "hanoi.jpg";
	String ninhBinhName = "ninh binh.jpg";

	String dienBienPhuPath = projectPath + "\\Upload Files\\" + dienBienPhuName;
	String haNoiPath = projectPath + "\\Upload Files\\" + haNoiName;
	String ninhBinhPath = projectPath + "\\Upload Files\\" + ninhBinhName;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_AutoIT() {
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
