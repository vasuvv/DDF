package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.TestBase;
import utilities.TestUtility;

public class CustomListeners extends TestBase implements ITestListener {

	 public void onTestStart(ITestResult result) {
		   
	}
	 public void onTestSuccess(ITestResult result) {
		 log.info("Test case is passed : " + result.getClass().getName());
	  }
	 public void onTestFailure(ITestResult result) {
		 log.info("Test case is failed : " + result.getClass().getName());
		 TestUtility.captureScreenshot();
	 }
	 public  void onTestSkipped(ITestResult result) {
		    // not implemented
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
		    // not implemented
	  }
}
