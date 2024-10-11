package TestNG;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Listener.ExtentReport;

@Listeners(ExtentReport.class)
public class Topic_10_Dependence {
	@Test
	public void TC_01_Create_Account() {
		System.out.println("Create Account");
		
		
	}

	@Test(dependsOnMethods = "TC_01_Create_Account")
	public void TC_02_Edit_Account() {
		System.out.println("Edit Account");
		
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods = "TC_02_Edit_Account")
	public void TC_03_View_Account() {
		System.out.println("View Account");
	}

	@Test
	public void TC_04_Delete_Account() {
		System.out.println("Delete Account");
	}

}
