package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class Topic_05_DataProvider {
  @Test(dataProvider = "data")
  public void TC_01(String username, String password) {
	  System.out.println(username);
	  System.out.println(password);
  }

  @DataProvider
  public Object[][] data() {
    return new Object[][] {
      new Object[] { "admin", "admin123" },
      new Object[] { "user", "user123" },
    };
  }
}
