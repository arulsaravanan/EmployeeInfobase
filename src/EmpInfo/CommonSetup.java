package EmpInfo;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CommonSetup 
{
	protected static WebDriver driver;
	Properties prop = new Properties();
	String baseUrl="http://hr.clearwebworks.com/";

	

			    public CommonSetup() throws Exception 
			    {
			    	//super();
			    	setUp();
			    	
			    	
				}
			    			
			    
				public  void setUp() throws Exception 
				    {
					
					 driver = new FirefoxDriver();
					InputStream input = null;
					String filename = "EmpInfo/common.properties";
					
					 input = getClass().getClassLoader().getResourceAsStream(filename);
					 prop.load(input);
				    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				    }
			    @After
			    public void tearDownAfterTest() 
			    {
			    	//driver.manage().deleteAllCookies();
			    }
			    @AfterClass
			    public static void tearDown() throws Exception {
			    	
			       // driver.quit();
			    }

}