package EmpInfo;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


	@Listeners(EmpInfo.ScreenshotOnFailureListener.class)
	public class Logout extends CommonSetup
	{
		Login objLogin = new Login();
		public Logout() throws Exception 
		{
		
			super();
			
		}
		
		@Test(priority=0)
		 public void loginEmployeeInfobase() throws InterruptedException
		 {  
			driver.get(baseUrl);
			Thread.sleep(3000);
		 }
		

        @Test(priority=1)
		 public void loginEmployeeInfobaseWithValidData() throws InterruptedException, BiffException, IOException
		 {  
			 
        	objLogin.loginValidData();
        	
		 }


        @Test(priority=2)
       	public void logout() throws InterruptedException
       	{
       		driver.findElement(By.xpath(prop.getProperty("Logout"))).click();
       	}
        
	}		
		
		
			 



			  
			  
			  
			  