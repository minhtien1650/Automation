package Basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Topic_01_Data_Type {
	
	public static void main(String[] args) {
		
		// Kiểu dữ liệu nguyên thủy (Primitive Type)
		// Number
		// Integer: Số nguyên không dấu
		// byte/ short/ int/ long
		
		byte bNumber = 5;
		short sNumber = 100;
		int studentNumber = 1000;
		long timeOut = 20000;
		
		// Số thực (có dấu)
		// float/ double
		
		float studentPoint = 8.5f;
		double employeeSalary = 28.5d;
		
		// Kí tự
		// Char
		// char
		
		char c = 'Q';
		char specialChar = '$';
		
		// Boolean
		// boolean
		
		boolean status = true;
		status = false;
		
		
		// Kiểu dữ liệu tham chiếu (Reference Type)
		// String
		String studentName = "Automation FC";
		String studentAddress = new String("123 Le Lai");
		
		// Array (Tập hợp kiểu dữ liệu giống nhau)
		String[] studentNames = {"Nguyen Van Nam", "Le Van An", "Ngo Si Lien"};
		
		// Class
		WebDriver driver = new ChromeDriver();
		Actions action = new Actions(driver);
		
		// Interface
		// Collection (Set/Queue/List)
	}

}
