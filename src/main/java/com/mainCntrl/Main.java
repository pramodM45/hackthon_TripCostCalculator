package com.mainCntrl;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.excelFileHandler.ReadExcelFile;
import com.operations.SearchTrip;
import com.operations.SelectDriver;

public class Main {
	
	public static String[] readExcel() {
		String[] strArr = new String[4];
		ReadExcelFile ref =  new ReadExcelFile();
		try {
			String path = System.getProperty("user.dir");
			strArr = ref.readExcelData(path+ "\\Excel\\DataFile.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strArr;
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		String[] strArr = new String[4];
		strArr = readExcel();
		SelectDriver sd = new SelectDriver();
		WebDriver driver = sd.selectDriver();
		SearchTrip st = new SearchTrip();
		st.openUrl(driver);
		st.searchHolidayHome(strArr[0], strArr[1], strArr[2], strArr[3]);
		driver.quit();
		
	}

}
