package EmpInfo;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import jxl.read.biff.BiffException;

/**
 * The below code will call the listener. The listener is implemented to take a
 * screenshot of the failed Test cases.
 * 
 */
@Listeners(EmpInfo.ScreenshotOnFailureListener.class)
public class Login extends CommonSetup {

	public Login() throws Exception {
		super();

	}

	/*
	 * The method checks for login functionality by inputing with no data. Click
	 * on the login button without entering the user name and password to check
	 * for the validation messages.
	 */
	@Test(priority = 0,groups="login")
	public void loginNoData() throws Exception {

		 System.out.println("Login");
		driver.get(prop.getProperty("Baseurl"));
		driver.findElement(By.xpath(prop.getProperty("submitButton"))).click();

		String bodyUserName = driver.findElement(
				By.xpath(prop.getProperty("textUserName"))).getText();

		Assert.assertTrue(bodyUserName.contains(prop
				.getProperty("AssertUsernameText")));

		String bodyPassword = driver.findElement(
				By.xpath(prop.getProperty("textPassword"))).getText();

		Assert.assertTrue(bodyPassword.contains(prop
				.getProperty("AssertPasswordText")));

	}

	/**
	 * @throws InterruptedException
	 *             The method checks for login functionality by inputing with
	 *             valid data. User name and password are taken from an xl file.
	 * @throws IOException
	 * @throws BiffException
	 */
	@Test(priority = 1,groups="login")
	public void loginValidData() throws InterruptedException, BiffException,
			IOException {
		// Login method takes the user name and password from the xl sheet.
		System.out.println("loginwithvalid data");
		login();

	}

	/**
	 * This is an annotation used in testNG to be called after all the test's
	 * has been run.
	 */
	@AfterTest(groups="logout")
	public void afterTest() {
		//driver.quit();
	}
}
