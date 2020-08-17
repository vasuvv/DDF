package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell col;
	
	public ExcelReader() {
		try {
			wb = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\excelData\\TestData.xlsx")));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public ExcelReader(String filePath) {
		try {
			wb = new XSSFWorkbook(new FileInputStream(new File(filePath)));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public int getNoOfRows(String sheetName) {
		return wb.getSheet(sheetName).getPhysicalNumberOfRows();
		/*
		 sheet = wb.getSheet(sheetName);
		 int rows = sheet.getPhysicalNumberOfRows();
		 return rows;
		 */
	}
	public int getNoOfCols(String sheetName, int rowNum) {
		return wb.getSheet(sheetName).getRow(rowNum).getPhysicalNumberOfCells();
	}
	public String getStringData(String sheetName, int rowNum, int colNum) {
		return wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).getStringCellValue();
		/*sheet = wb.getSheet(sheetName);
		row =sheet.getRow(rowNum);
		col=row.getCell(colNum);
		String data = col.getStringCellValue();
		return data;*/
	}
	public String getStringData(int sheetIndexNo, int rowNum, int colNum) {
		return wb.getSheetAt(sheetIndexNo).getRow(rowNum).getCell(colNum).getStringCellValue();
	}
	public double getDoubleData(String sheetName, int rowNum, int colNum) {
		return wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).getNumericCellValue();
	}
	public int getIntegerData(String sheetName, int rowNum, int colNum) {
		return (int)wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).getNumericCellValue();
	}
	public boolean getBooleanData(String sheetName, int rowNum, int colNum) {
		return wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).getBooleanCellValue();
	}
}
