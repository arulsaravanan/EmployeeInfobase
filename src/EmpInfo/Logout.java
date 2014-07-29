package EmpInfo;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * The below code will call the listener. The listener is implemented to take a
 * screenshot of the failed Test cases.
 * 
 */
@Listeners(EmpInfo.ScreenshotOnFailureListener.class)
public class Logout extends CommonSetup {

	public Logout() throws Exception {

		super();

	}

	/**
	 * The method checks whether EmployeeInfobase is loaded correctly in the
	 * webbrowser
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 0)
	public void loginEmployeeInfobase() throws InterruptedException {
		driver.get(prop.getProperty("Baseurl"));
	}

	/**
	 * The method checks for login functionality by inputing with valid data.
	 * User name and password are taken from an xl file.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws BiffException
	 */
	@Test(priority = 1)
	public void loginEmployeeInfobaseWithValidData()
			throws InterruptedException, BiffException, IOException {

		login();

	}

	/**
	 * The method logout from the EmployeeInfobase website.
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority = 2,groups="logout")
	public void logout() throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("Logout"))).click();
	}

}
