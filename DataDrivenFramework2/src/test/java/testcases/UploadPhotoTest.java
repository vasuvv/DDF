package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtility;

public class UploadPhotoTest extends TestBase{
	
	@Test(dataProvider = "getData")
	public void UploadPic(String pName, String photoPath) {
		log.info("Inside the UploadPhoto testcase..");
		driver.findElement(By.xpath(pName)).click();
		driver.findElement(By.xpath(or.getProperty("aPhoto"))).click();
		driver.findElement(By.xpath(or.getProperty("uPhoto"))).sendKeys(photoPath);
		TestUtility.waitTo(7);
		driver.findElement(By.xpath(or.getProperty("cBtn"))).click();
		driver.findElement(By.xpath(or.getProperty("leaBtn"))).click();
		driver.findElement(By.xpath(or.getProperty("clsLink"))).click();
		log.info("UploadPhoto testcase is executed successfully..");
	}
	@DataProvider
	public static Object[][] getData() {
		return TestUtility.getData("UploadPhoto");
		/*String sheetName="UploadPhoto";
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
