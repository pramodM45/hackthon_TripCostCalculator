package com.excelFileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelFile {
	public static int i=0;
	
	public static void writeTotalResults(String filename, int counts, int totals) throws IOException {
		FileInputStream file = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("OutputSheet");
		String strResult = "Total "+ totals+ " Holiday Homes Found.";
		String strMatched = "Total "+ counts+ " Holiday Homes match the requirement.";
		Row row = sheet.createRow(2);
		row.createCell(6).setCellValue(strResult);
		Row row1 = sheet.createRow(3);
		row1.createCell(6).setCellValue(strMatched);
		FileOutputStream fos = new FileOutputStream(new File(filename));
		workbook.write(fos);
		fos.close();
	}
	public static void writeHolidayHomes(String filename, ArrayList<String> resultList) throws IOException {
		FileInputStream file = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("OutputSheet");
		Row row = sheet.createRow(i+2);
		row.createCell(0).setCellValue(i+1);
		row.createCell(1).setCellValue(resultList.get(0));
		row.createCell(2).setCellValue(resultList.get(1));
		row.createCell(3).setCellValue(resultList.get(2));
		row.createCell(4).setCellValue(resultList.get(3));
		i++;
		FileOutputStream fos = new FileOutputStream(new File(filename));
		workbook.write(fos);
		fos.close();
	}

}
