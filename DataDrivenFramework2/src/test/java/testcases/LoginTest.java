package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtility;

public class LoginTest extends TestBase {

	@Test(dataProvider = "getData")
	public void login(String email, String pwd) {
		log.info("Inside the login testcase..");
		driver.findElement(By.xpath(or.getProperty("eId"))).sendKeys(email);
		driver.findElement(By.xpath(or.getProperty("pwd"))).sendKeys(pwd);
		driver.findElement(By.xpath(or.getProperty("lBtn"))).click();
		log.info("login testcase is executed successfully..");
	}

	@DataProvider
	public static Object[][] getData() {
		return TestUtility.getData("LoginTest");
/*		String sheetName="LoginTest";
		int rows= excel.getNoOfRows(sheetName);
		int cols= excel.getNoOfCols(sheetName, 0);
		Object[][] data = new Object[rows][cols];
		for(int rowNo=0; rowNo< rows; rowNo++)
		{
			for(int colNo = 0; colNo<cols; colNo++)
			{
				data[rowNo][colNo]=excel.getStringData(sheetName, rowNo, colNo);
			}
		}
		return data;*/
	}

}
