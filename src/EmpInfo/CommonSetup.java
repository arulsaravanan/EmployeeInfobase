package EmpInfo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class CommonSetup {
	protected static WebDriver driver;
	Properties prop = new Properties();

	/*
	 * CommonSetup is a constructor where the setup method is declared
	 */
	public CommonSetup() throws Exception {

		setUp();
	}

	/*
	 * Setup method contains the Webdriver object and also properties file
	 * loading.Any code which is commonly used in many pages will be placed in
	 * this method.
	 */
	public void setUp() throws Exception {

		driver = new FirefoxDriver();
		InputStream input = null;
		String filename = "EmpInfo/common.properties";

		input = getClass().getClassLoader().getResourceAsStream(filename);
		prop.load(input);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	/**
	 * Login method takes the user anme and password from the xl sheet and logs
	 * into the Employee Infobase website
	 * 
	 * @throws BiffException
	 * @throws IOException
	 */
	public void login() throws BiffException, IOException {
		Sheet s;
		FileInputStream fi = new FileInputStream(
				prop.getProperty("UserAccountFilePath"));

		Workbook w = Workbook.getWorkbook(fi);
		s = w.getSheet(0);
		for (int row = 1; row < s.getRows(); row++) {
			String username = s.getCell(0, row).getContents();
			driver.findElement(By.xpath(prop.getProperty("UserName")))
					.sendKeys(username);
			String password = s.getCell(1, row).getContents();

			driver.findElement(By.xpath(prop.getProperty("Password")))
					.sendKeys(password);
			driver.findElement(By.xpath(prop.getProperty("submitButton")))
					.click();

			/*String homePageSucessfullLoginCheck = driver.findElement(
					By.xpath(prop.getProperty("HomePageSuccessfullMesssage")))
					.getText();*/

		/*	Assert.assertTrue(homePageSucessfullLoginCheck.contains(prop
					.getProperty("AssertHomePageMessage")));*/
		}
	}
	/*
	 * @AfterClass public static void tearDown() throws Exception {
	 * 
	 * // driver.quit(); }
	 */

}