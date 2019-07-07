package com.learnautomation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
	XSSFWorkbook wb;
	
	public ExcelDataProvider() {
		
		File src = new File("./TestData/Data.xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			
			System.out.println("Unable to read excel file:"+e.getMessage());
		}
	}
	
	public String getStringData(String sheetName, int rowNum, int colNum) {
		return wb.getSheet("Login").getRow(0).getCell(0).getStringCellValue();
	}
	
	public double getNumericData() {
		return wb.getSheet("Login").getRow(0).getCell(0).getNumericCellValue();
	}

}
