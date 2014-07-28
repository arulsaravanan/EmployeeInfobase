package EmpInfo;



import java.io.FileInputStream;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

/**
 *The below code will call the listener. The listener is implemented to take a screenshot of the failed Test cases.
 *
 */
@Listeners(EmpInfo.ScreenshotOnFailureListener.class)


public class Login extends CommonSetup
{
		 
public Login() throws Exception {
		super();
		
	}
/*
 * The method checks for login functionality by inputing with no data. Click on the login button without entering the user name 
 * and password to check for the validation messages.
 */
 @Test(priority=0)
  public void loginNoData() throws Exception
  { 
	
	
	  driver.get(baseUrl);
	 // driver.findElement(By.xpath(".//*[@id='edit-submit']")).click();
	  driver.findElement(By.xpath(prop.getProperty("submitButton"))).click();	  
	  Thread.sleep(3000);
	   
	  String bodyUserName = driver.findElement(By.xpath(prop.getProperty("textUserName"))).getText();
	  String text ="Username field is required.";
	  Assert.assertTrue(bodyUserName.contains(text ));
	      
	 // String bodyPassword = driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/ul/li[2]")).getText();
	  String bodyPassword = driver.findElement(By.xpath(prop.getProperty("textPassword"))).getText();
	  String pwdText ="Password field is required.";
	  Assert.assertTrue(bodyPassword.contains(pwdText));
	  
  }
 
 /**
 * @throws InterruptedException
 * The method checks for login functionality by inputing with valid data. User name and password are taken from an xl file.
 * @throws IOException 
 * @throws BiffException 
 */
@Test(priority=1)
 public void loginValidData() throws InterruptedException, BiffException, IOException
 {  
	Sheet s;	
	FileInputStream fi = new FileInputStream("D:\\Datatest\\Data.xls");
	Workbook w = Workbook.getWorkbook(fi);
	s = w.getSheet(0);
	for(int row=1; row <s.getRows();row++)
	{
	String username = s.getCell(0, row).getContents();
	//System.out.println("Username is"+username);
	
	 driver.findElement(By.xpath(prop.getProperty("UserName"))).sendKeys(username);
	 
	 String password= s.getCell(1, row).getContents();
	 //System.out.println("Password is"+password);
	 
	 driver.findElement(By.xpath(prop.getProperty("Password"))).sendKeys(password);
	 
	  driver.findElement(By.xpath(prop.getProperty("submitButton"))).click();
	  Thread.sleep(5000);
	}
	  
	  String homePageSucessfullLoginCheck = driver.findElement(By.xpath(prop.getProperty("HomePageSuccessfullMesssage"))).getText();
	  System.out.println(homePageSucessfullLoginCheck);
	  String homePageMessage ="This website acts as a centrally located data store of all employees of any organization.\n"+
			                   "Using this website, new employees can be added to the system, modify the changes later.";
	  Assert.assertTrue(homePageSucessfullLoginCheck.contains(homePageMessage));
 }
 
  /**
 * This is an annotation used in testNG to be called after all the test's has been run.
 */
@AfterTest
  public void afterTest()
  {
	  //driver.quit();
  }
}

