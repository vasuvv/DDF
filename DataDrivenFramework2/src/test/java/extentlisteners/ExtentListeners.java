package extentlisteners;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListeners implements ITestListener {

	public static Date d = new Date();
	public static DateFormat dateFormatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	public static String date = dateFormatter.format(d);
	public static String fileName = "Extent_" + date + ".html";
	private static ExtentReports extent = ExtentManager.createInstance(fileName);
	public static ExtentTest test;
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName() + "   @TestCase " + result.getMethod().getMethodName());
	}

	
	public void onTestSuccess(ITestResult result) {
		String methodName =result.getMethod().getMethodName();
		String text ="<b> TESTCASE : - "+methodName+" PASSED "+"</b";
		Markup m=MarkupHelper.createLabel(text, ExtentColor.GREEN);
		test.log(Status.PASS, m);
	}

	
	public void onTestFailure(ITestResult result) {
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		test.fail(exceptionMessage);
		String screenShot = "C:\\Users\\Testing\\Desktop\\Koala.jpg";
		try {
			test.fail("<b><font color=red>Screenshot of  failure</font></b><br>",
					MediaEntityBuilder.createScreenCaptureFromPath(screenShot).build());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		String methodName = result.getMethod().getMethodName();
		String text = "<b> TESTCASE : - " + methodName + " FAILED " + "</b";
		Markup m = MarkupHelper.createLabel(text, ExtentColor.RED);
		test.log(Status.FAIL, m);
	}

	
	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String text ="<b> TESTCASE : - "+methodName+" SKIPPED "+"</b";
		Markup m=MarkupHelper.createLabel(text, ExtentColor.YELLOW);
		test.log(Status.SKIP, m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	
	public void onStart(ITestContext context) {
		// not implemented
	}

	
	public void onFinish(ITestContext context) {
		if(extent != null) {
			extent.flush();
		}
	}
}
