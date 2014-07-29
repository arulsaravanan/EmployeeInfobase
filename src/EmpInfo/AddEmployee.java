package EmpInfo;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/*The below code will call the listener. The listener is implemented to take a screenshot of the failed Test cases.
 *
 */
@Listeners(EmpInfo.ScreenshotOnFailureListener.class)
public class AddEmployee extends CommonSetup {

	public AddEmployee() throws Exception {

		super();

	}

	/*
	 * The method checks for login functionality when the user enters valid
	 * username and password to login into the site
	 */
	@Test(priority = 0)
	public void loginData() throws InterruptedException, BiffException,
			IOException {
           
		login();
	}

	/*
	 * The method checks for the add employee functionality.When the user clicks
	 * on the add employee button, User should be able to see the employee form
	 * .
	 */
	@Test(priority = 1, groups = "AddEmp")
	public void addEmployee() throws InterruptedException {

		driver.findElement(By.xpath(prop.getProperty("AddEmployee"))).click();

	}

	/*
	 * The method checks for the save functionality.When the user clicks on the
	 * save button without adding the mandatory fields validation messages are
	 * seen.
	 */
	@Test(priority = 2, groups = "AddEmp")
	public void save() throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("Save"))).click();
		String bodyUserName = driver.findElement(
				By.xpath(prop.getProperty("Savetext"))).getText();
		String text = "Title field is required.";
		Assert.assertTrue(bodyUserName.contains(text));
		Thread.sleep(5000);
		addEmployee();
		driver.findElement(By.xpath(prop.getProperty("Preview"))).click();
		Thread.sleep(5000);

	}

	/*
	 * The method checks for the title fuctionality.User enters the desired
	 * title field in the title box and clicks on the save button and sees the
	 * message like title is been created.
	 */
	@Test(priority = 3, groups = "AddEmp")
	public void title() throws InterruptedException

	{
		driver.findElement(By.xpath(prop.getProperty("Title"))).sendKeys(
				prop.getProperty("AddEmployeeTitle"));
		driver.findElement(By.xpath(prop.getProperty("Firstname"))).sendKeys(
				prop.getProperty("AddEmployeeFirstnameEdit"));
		driver.findElement(By.xpath(prop.getProperty("Lastname"))).sendKeys(
				prop.getProperty("AddEmployeeLastnameEdit"));
		Select selDesignation = new Select(driver.findElement(By
				.id("edit-field-designation-nid-nid")));
		selDesignation.selectByVisibleText(prop.getProperty("Designationdata"));
		Select selJobType = new Select(driver.findElement(By
				.id("edit-field-jobtype-nid-nid")));
		selJobType.selectByVisibleText(prop.getProperty("JobTypeData"));
		Thread.sleep(5000);

		Select selvisa = new Select(driver.findElement(By
				.id("edit-field-visa-status-value")));
		selvisa.selectByVisibleText(prop.getProperty("VisaStatus"));
		driver.findElement(By.xpath(prop.getProperty("KeySkills"))).sendKeys(
				prop.getProperty("KeySkillsdata"));
		Thread.sleep(5000);
		driver.findElement(By.xpath(prop.getProperty("Mobile"))).sendKeys(
				prop.getProperty("Mobiledata"));
		driver.findElement(By.xpath(prop.getProperty("Email"))).sendKeys(
				prop.getProperty("Emaildata"));
		driver.findElement(By.xpath(prop.getProperty("Experience"))).sendKeys(
				prop.getProperty("Experiencedata"));
		Thread.sleep(5000);
		driver.findElement(By.xpath(prop.getProperty("Save"))).click();
		String bodyUserName = driver.findElement(
				By.xpath(prop.getProperty("Savedata"))).getText();
		String text = "Employee " + prop.getProperty("AddEmployeeTitle")
				+ " has been created.";
		Assert.assertTrue(bodyUserName.contains(text));
		Thread.sleep(5000);

	}

	/*
	 * The method checks for the edit functionality.User can edit the fields
	 * required and clicks on the save button and also sees the message like
	 * edited text is been updated.
	 */

	@Test(priority = 4, groups = "AddEmp")
	public void edit() throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("Edit"))).click();

		/*
		 * Dropdown selection for various functions
		 */

		/*
		 * User can enter the preferences in the preferences textbox.
		 */
		driver.findElement(By.xpath(prop.getProperty("Preferences"))).sendKeys(
				prop.getProperty("Preferencesdata"));
		/*
		 * User can upload the files from the desired location to the page
		 */

		driver.findElement(
				By.xpath(".//*[@id='node-form']/div/div/div[1]/div[10]/fieldset/legend/a"))
				.click();
		WebElement fileInput = driver.findElement(By
				.xpath("//input[@type='file']"));
		fileInput.sendKeys("D:\\Softwares\\sampletext\\sampletext.txt");

		String title = driver.findElement(By.xpath(prop.getProperty("Title")))
				.getAttribute("value");
		driver.findElement(By.xpath(prop.getProperty("Save"))).click();
		Thread.sleep(5000);
		String bodyUserName = driver.findElement(
				By.xpath(prop.getProperty("datasave"))).getText();
		String text = "Employee " + title + " has been updated.";
		Assert.assertTrue(bodyUserName.contains(text));

	}

	/*
	 * The method checks for the preview functionality.User clicks on the
	 * preview button and able to preview the updated text.
	 */

	@Test(priority = 5,groups="AddEmp")
	public void preview() throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("Edit"))).click();
		driver.findElement(By.xpath(prop.getProperty("Preview"))).click();
		Thread.sleep(5000);

	}

	/*
	 * The method checks for both delate and cancel functionalities.when the
	 * user clicks on the delete button shows the confirmation message but want
	 * to undo the changes then clicks on the cancel button which returns to the
	 * edit page.when the user clicks on the delete button after confirmation
	 * message,receives a message that text has been deleted.
	 */
	@Test(priority = 6,groups="AddEmp")
	public void deletecancel() throws InterruptedException {

		driver.findElement(By.xpath(prop.getProperty("Edit"))).click();
		String title = driver.findElement(By.xpath(prop.getProperty("Title")))
				.getAttribute("value");
		driver.findElement(By.xpath(prop.getProperty("Delete"))).click();

		String bodyUserName = driver.findElement(
				By.xpath(prop.getProperty("deletedata"))).getText();

		StringBuffer text = new StringBuffer();

		text.append("Are you sure you want to delete ");
		text.append(title);
		Assert.assertTrue(bodyUserName.contains(text));

		Thread.sleep(5000);
		driver.findElement(By.xpath(prop.getProperty("Cancel"))).click();

		driver.findElement(By.xpath(prop.getProperty("Editcancel"))).click();
		driver.findElement(By.xpath(prop.getProperty("Delete"))).click();
		driver.findElement(By.xpath(prop.getProperty("Conformationdelete")))
				.click();

		Thread.sleep(5000);

	}

	/*
	 * This method checks for viewall functionality.when user clicks on the
	 * viewall button will able to see table containing details of the employee.
	 */
	@Test(priority = 7,groups="AddEmp")
	public void viewall() throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("AddEmployee"))).click();

		/*Actions actions = new Actions(driver);
		WebElement menuHoverLink = driver.findElement(By
				.linkText("Add Employee"));
		actions.moveToElement(menuHoverLink);*/

		// WebElement subLink =
		// driver.findElement(By.cssSelector("#headerMenu .subLink"));
	/*	WebElement subLink = driver.findElement(By.linkText("View All"));
		actions.moveToElement(subLink);
		actions.click();
		actions.perform();*/

		driver.findElement(By.xpath(prop.getProperty("ViewAll"))).click();
		Thread.sleep(5000);

	}

	/*
	 * This method checks for edit option functionality in the viewall.when user
	 * clicks on the edit button will able to modify the data and perform the
	 * functions like save preview and delete
	 */

	@Test(priority = 8,groups="AddEmp")
	public void viewalledit() throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("edit"))).click();

		driver.findElement(By.xpath(prop.getProperty("EditDesignation")))
				.clear();
		driver.findElement(By.xpath(prop.getProperty("EditDesignation")))
				.sendKeys(prop.getProperty("Viewalleditdesignation"));
		driver.findElement(By.xpath(prop.getProperty("EditDesignation")))
				.getText();
		driver.findElement(By.xpath(prop.getProperty("Firstname"))).sendKeys(
				prop.getProperty("AddEmployeeFirstnameEdit"));
		driver.findElement(By.xpath(prop.getProperty("Lastname"))).sendKeys(
				prop.getProperty("AddEmployeeLastnameEdit"));
		Select selDesignation = new Select(driver.findElement(By
				.id("edit-field-designation-nid-nid")));
		selDesignation.selectByVisibleText(prop.getProperty("Designationdata"));
		Select selJobType = new Select(driver.findElement(By
				.id("edit-field-jobtype-nid-nid")));
		selJobType.selectByVisibleText(prop.getProperty("JobTypeData"));
		Thread.sleep(5000);

		Select selvisa = new Select(driver.findElement(By
				.id("edit-field-visa-status-value")));
		selvisa.selectByVisibleText(prop.getProperty("VisaStatus"));
		driver.findElement(By.xpath(prop.getProperty("KeySkills"))).sendKeys(
				prop.getProperty("KeySkillsdata"));
		Thread.sleep(5000);
		driver.findElement(By.xpath(prop.getProperty("Mobile"))).sendKeys(
				prop.getProperty("Mobiledata"));
		driver.findElement(By.xpath(prop.getProperty("Email"))).sendKeys(
				prop.getProperty("Emaildata"));
		driver.findElement(By.xpath(prop.getProperty("Experience"))).sendKeys(
				prop.getProperty("Experiencedata"));
		Thread.sleep(5000);

		driver.findElement(By.xpath(prop.getProperty("Save"))).click();

		String bodyUserName = driver.findElement(
				By.xpath(prop.getProperty("datasave"))).getText();
		System.out.println(bodyUserName);
		String text = "Employee testing engineer has been updated.";
		Assert.assertTrue(bodyUserName.contains(text));

	}

	/*
	 * This method checks for preview functionality in viewall option.when the
	 * user clicks on the preview button will able to preview the updated text.
	 */
	@Test(priority = 9,groups="AddEmp")
	public void viewalleditpreview() throws InterruptedException {
		driver.findElement(By.xpath(prop.getProperty("edit"))).click();

		preview();
		deletecancel();

	}
}
