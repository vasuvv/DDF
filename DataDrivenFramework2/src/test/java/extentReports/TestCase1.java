package extentReports;

import java.io.IOException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestCase1 {
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeTest
	public void setReport() {
		htmlReporter = new ExtentSparkReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("uft-8");
		htmlReporter.config().setDocumentTitle("CIS Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Vasu");
		extent.setSystemInfo("Orgnizaion", "Computech");
		extent.setSystemInfo("Build-No", "Ver1.3");

	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}

	@Test
	public void doLogin() {
		test = extent.createTest("LoginTest");
		test.log(Status.INFO, "Inside doLoginTest");
	}

	@Test
	public void doUserReg() {
		test = extent.createTest("User Reg Test");
		test.log(Status.INFO, "Enter user name");
		test.log(Status.INFO, "Enter Password");
		test.log(Status.FAIL, "Failing the test - Screen shot attached");
		Assert.fail("Failing user reg test case..");
	}

	@Test
	public void validateTitle() {
		test = extent.createTest("Validate Title Test");
		test.log(Status.INFO, "Validating title");
		throw new SkipException("Skipping the test case..");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
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
//			String text = "<b> TESTCASE : - " + methodName.toUpperCase() + " FAILED " + "</b";
			Markup m = MarkupHelper.createLabel(text, ExtentColor.RED);
			test.log(Status.FAIL, m);
			//test.log(Status.FAIL, "Failed Test");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			String methodName=result.getMethod().getMethodName();
			String text ="<b> TESTCASE : - "+methodName+" PASSED "+"</b";
//			String text ="<b> TESTCASE : - "+methodName.toUpperCase()+" PASSED "+"</b";
			Markup m=MarkupHelper.createLabel(text, ExtentColor.GREEN);
			test.log(Status.PASS, m);
			//test.log(Status.PASS, "Passed Test");
		} else if (result.getStatus() == ITestResult.SKIP) {
			String methodName=result.getMethod().getMethodName();
			String text ="<b> TESTCASE : - "+methodName+" SKIPPED "+"</b";
//			String text ="<b> TESTCASE : - "+methodName.toUpperCase()+" SKIPPED "+"</b";
			Markup m=MarkupHelper.createLabel(text, ExtentColor.YELLOW);
			test.log(Status.SKIP, m);
			//test.log(Status.SKIP, "Skipped Test");
		}

	}
}
