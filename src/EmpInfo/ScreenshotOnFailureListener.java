package EmpInfo;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.net.*;

public class ScreenshotOnFailureListener extends TestListenerAdapter {
	private int Count = 0;

	/**
	 * The method will be called whenever a test case is failed.
	 */
	@Override
	public void onTestFailure(ITestResult tr) {
		ScreenShot();
	}

	/**
	 * The method will be called whenever a test case is skipped.
	 */
	@Override
	public void onTestSkipped(ITestResult tr) {
		// ScreenShot();
	}

	/**
	 * The method will be called whenever a test case is passed.
	 */
	@Override
	public void onTestSuccess(ITestResult tr) {
		// ScreenShot();

	}

	/**
	 * The method takes a screen shot for failed test case
	 */
	private void ScreenShot() {
		try {

			String NewFileNamePath;

			// Get the dir path
			File directory = new File(".");
			// System.out.println(directory.getCanonicalPath());

			// get current date time with Date() to create unique file name
			DateFormat dateFormat = new SimpleDateFormat(
					"dd_MMM_yyyy__hh_mm_ssaa");
			// get current date time with Date()
			Date date = new Date();
			// System.out.println(dateFormat.format(date));

			// To identify the system
			InetAddress ownIP = InetAddress.getLocalHost();
			// System.out.println("IP of my system is := "+ownIP.getHostAddress());

			NewFileNamePath = directory.getCanonicalPath() + "\\ScreenShots\\"
					+ dateFormat.format(date) + "_" + ownIP.getHostAddress()
					+ ".png";
			System.out.println(NewFileNamePath);

			// Capture the screen shot of the area of the screen defined by the
			// rectangle
			Robot robot = new Robot();
			BufferedImage bi = robot.createScreenCapture(new Rectangle(1280,
					1024));

			ImageIO.write(bi, "png", new File(NewFileNamePath));
			Count++;
			// Assign each screen shot a number

			NewFileNamePath = "<a href=" + NewFileNamePath + ">ScreenShot"
					+ Count + "</a>";

			// Place the reference in TestNG web report
			Reporter.log(NewFileNamePath);

		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}