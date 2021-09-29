package com.excelFileHandler;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	
	public static String[] searchData = new String[4];
	
	public String[] readExcelData(String filename) throws IOException{
		FileInputStream file = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("InputSheet");
		for(int i=0;i<4;i++) {
			searchData[i]=String.valueOf(sheet.getRow(i+1).getCell(1));
		}
		return searchData;
	}

}
