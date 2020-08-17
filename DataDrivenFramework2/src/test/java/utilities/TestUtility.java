package utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import base.TestBase;

public class TestUtility extends TestBase{
	
	public static Object[][] getData(String sheetName) 
	{
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
		return data;
	}
	public static void waitTo(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void captureScreenshot(){
		Date d = new Date();
		DateFormat dateFormatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		filePath = dateFormatter.format(d);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots\\"+"FB_"+filePath+".png");
		try {
			FileHandler.copy(srcFile, destFile);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
