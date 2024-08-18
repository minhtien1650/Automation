package Basic;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_10_Xpath_CSS_III {
	WebDriver driver;

	@BeforeClass
	public void BeforeClass() {
		driver = new EdgeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	// @BeforeMethod
	public void BeforeMethod() {
		driver.get("");
	}

	@Test
	public void Lesson() {
		// 1 - Xpath Axes
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// * preceding-sibling (từ node em đi lên node anh)
		// xpath tìm đến thẻ label là thẻ anh của thẻ <div class="input-box">
		List<WebElement> precedingSiblingXpath = driver
				.findElements(By.xpath("//div[@class='input-box']/preceding-sibling::label"));
		// xpath này cho ra 2 kết quả lable: Email Address và Password
		// xpath của Email Address
		WebElement emailAddressXpath = driver.findElement(By.xpath("//label[@for='email']"));
		// Ktra phần tử đầu precedingSiblingXpath[0] có phải emailAddressXpath không
		Assert.assertEquals(precedingSiblingXpath.get(0), emailAddressXpath, "Is not Email Address Xpath");
		// Xpath của Password
		WebElement passwordXpath = driver.findElement(By.xpath("//label[@for='pass']"));
		// Ktra phần tử thứ 2 precedingSiblingXpath[1] có phải passwordXpath không
		Assert.assertEquals(precedingSiblingXpath.get(1), passwordXpath, "Is not Password Xpath");

		// * following-sibling (từ node anh xuống node em)
		// Xpath tìm đến thử <p class="required"> từ thẻ h2
		WebElement followingSiblingXpath = driver
				.findElement(By.xpath("//h2/following-sibling::p[@class = 'required']"));
		// Xpath không dùng following-sibling
		WebElement requiredFieldsXpath = driver.findElement(By.xpath("//p[@class = 'required']"));
		// Kiểm tra xem 2 thẻ có phải cùng 1 element không
		Assert.assertEquals(followingSiblingXpath, requiredFieldsXpath, "Is not Required Fields Xpath");

		// * child (node con)/descendant (node con cháu)
		// Lấy node con cháu của thẻ <div class="content fieldset">
		List<WebElement> descendantXpath = driver
				.findElements(By.xpath("//div[@class='content fieldset']/descendant::*"));
		System.out.println("Lấy node con cháu của thẻ <div class=\"content fieldset\">: " + descendantXpath.size());
		for (WebElement webElement : descendantXpath) {
			System.out.println(webElement.getTagName());
		}

		// * parent (node cha)/ancestor (node cha trở lên)
		// Lấy tìm từ node cha trở lên của thẻ <ul class="benefits">
		List<WebElement> ancestorXpath = driver.findElements(By.xpath("//ul[@class='benefits']/ancestor::*"));
		System.out.println("Lấy tìm từ node cha trở lên của thẻ <ul class=\"benefits\">: " + ancestorXpath.size());
		for (WebElement webElement : ancestorXpath) {
			System.out.println(webElement.getAttribute().getA);
		}

		// 2 - CSS selector
		// Cú pháp CSS tiêu chuẩn: tagname[attribute_name='attribute_value']
		// * ID: tagname#id_value or #id_value (dấu # đại diện cho ID)
		// * class: tagname.class_value or .class_value (dấu . đại diện cho class)
		// Với class có khoảng trắng ở giữa thì với mỗi khoảng trắng thay bằng 1 dấu .
		// Có thể lấy 1 phần giá trị trong class không cần lấy toàn bộ
		// * Đi 1 node (dùng dấu > thay cho dấu / trong xpath):
		// tagname[attribute_name='attribute_value']>childtagname
		// * Đi nhiều node (dùng khoảng trắng thay cho // trong xpath
		// tagname[attribute_name='attribute_value'] ancestortagname
		// * AND
		// tagname[attribute_name1='attribute_value1'][attribute_name2='attribute_value2']
		// * OR
		// tagname[attribute_name1='attribute_value1'],[attribute_name2='attribute_value2']
		// * NOT
		// tagname:not([attribute_name='attribute_value'])
		// * INDEX
		// tagname[attribute_name='attribute_value']>childtagname:nth-child(index)
		// First: tagname[attribute_name='attribute_value']>childtagname:first-child
		// Last: tagname[attribute_name='attribute_value']>childtagname:last-child
		// * Lấy node em
		// + : Lấy em gần kề nhất: tagname[attribute_name='attribute_value']+fstagname
		// ~ : Lấy tất cả node em: tagname[attribute_name='attribute_value']~fstagname
		// * CSS không đi ngược lên node anh và node cha được
		// * contains: tagname[attribute_name*='attribute_value']
		// * start-with: tagname[attribute_name*='attribute_value']
		// * end-with (xpath ko sp): tagname[attribute_name$='attribute_value']
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
