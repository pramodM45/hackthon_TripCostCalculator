package com.testOperations;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.excelFileHandler.ReadExcelFile;
import com.excelFileHandler.WriteExcelFile;
import com.operations.Highlighter;
import com.operations.SearchTrip;
import com.operations.SelectDriver;
import com.operations.TakeScreenshots;
import com.propertiesHandler.AccessProperties;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomeSearchTest {
	static WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	static String[] strArr = new String[4];
	AccessProperties ap = new AccessProperties();
	WriteExcelFile wef = new WriteExcelFile();
	TakeScreenshots ts = new TakeScreenshots();
	Highlighter hlt = new Highlighter();
	
	@BeforeClass
	public static void startTest() {
		//Create Extent Report 
		report = new ExtentReports(System.getProperty("user.dir")+"\\Output-Reports\\ExtentReportResults.html");
		test = report.startTest("HandleAlerts");
	}
	
	@BeforeTest
	public void testWebDriver() throws IOException, InterruptedException {
		SelectDriver sd = new SelectDriver();
		driver = sd.selectDriver();
		SearchTrip st = new SearchTrip();
		st.openUrl(driver);
	}
	
	@Test(priority=1)
	public void testExcelRead() {
		ReadExcelFile ref =  new ReadExcelFile();
		try {
			String path = System.getProperty("user.dir");
			strArr = ref.readExcelData(path+ "\\Excel\\DataFile.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] expectedExcelArray = {"Nairobi","15-Dec-2021","20-Dec-2021","4.0"};
		Assert.assertEquals(strArr, expectedExcelArray);
		test.log(LogStatus.INFO,"Read Excel File and get all input data");
	}
	
	@Test(priority=2)
	public void testSite() {
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.tripadvisor.in/");
		test.log(LogStatus.INFO, "Opening Tripadvisor website");
	}
	
	@Test(priority=3)
	public void testSearchCity() throws IOException, InterruptedException {
		WebElement homeSearch = driver.findElement(By.xpath(ap.getHomeSearch()));
		hlt.highlightElement(driver, homeSearch);
		homeSearch.sendKeys(strArr[0]);
		Thread.sleep(700);
		ts.captureSS(driver);
		Thread.sleep(700);
		//Select one of the option from the available options.
		WebElement option1 = driver.findElement(By.cssSelector("a[href='/Tourism-g294207-Nairobi-Vacations.html']"));
		hlt.highlightElement(driver, option1);
		option1.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		ts.captureSS(driver);
		String expectedURL = "https://www.tripadvisor.in/Tourism-g294207-Nairobi-Vacations.html";
		Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
		test.log(LogStatus.INFO,"Search Nairobi");
		
	}
	
	@Test(priority=4)
	public void testSearchHldyHome() throws IOException, InterruptedException {
		//Select the Holiday Homes Option for trip in the selected city.
		WebElement hldyhm = driver.findElement(By.xpath(ap.getHolidayHomesBtn()));
		hlt.highlightElement(driver, hldyhm);
		hldyhm.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(700);
		ts.captureSS(driver);
		Thread.sleep(700);
		String expectedURL1 = "https://www.tripadvisor.in/VacationRentals-g294207-Reviews-Nairobi-Vacation_Rentals.html";
		Assert.assertEquals(driver.getCurrentUrl(), expectedURL1);
		test.log(LogStatus.INFO,"Click on Holiday Homes and get the Holiday Homes available in Nairobi");
	}
	
	//Calendar for shuffling between the months to select the Check-In and Check-Out dates
		public int calendarMonths(String current, int req) {
			//array of all months
			String[] months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			int m1=0, diff=0;
			for(int i=0;i<12;i++) {
				if(months[i].equalsIgnoreCase(current)) {
					m1=i;
				}
			}
			if(m1<req) {
				diff=req-m1;
			}
			
			return diff;
		}
		
		//A string to date converter 
		public Date dateConverter(String strDate) {
			try {
				Date date = new SimpleDateFormat("dd-MMM-yy").parse(strDate);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	@Test(priority=5)
	public void testDateSelection()  throws IOException, InterruptedException{
		test.log(LogStatus.INFO,"Select Check-In and Check-Out Dates");
		//Open the dialog box to select the Check-In date.
		WebElement check_In = driver.findElement(By.xpath(ap.getCheckInDiv()));
		hlt.highlightElement(driver, check_In);
		check_In.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Find out the month on the first column of the calendar box.
		String calMonth  = driver.findElement(By.xpath(ap.getCalMonth())).getText();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String[] calArr = calMonth.split(" ");
		Date checkIn = dateConverter(strArr[1]);
		Date checkOut = dateConverter(strArr[2]);
		int diff = calendarMonths(calArr[0], checkIn.getMonth());
		for(int i=0;i<diff;i++) {
			WebElement forward = driver.findElement(By.xpath(ap.getCalForward()));
			hlt.highlightElement(driver, forward);
			forward.click();
			Thread.sleep(700);
		}
		//Select the date for the check-in.
		WebElement date1 = driver.findElement(By.xpath(ap.getDate()+"[contains(text(),"+checkIn.getDate()+" )]"));
		hlt.highlightElement(driver, date1);
		date1.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(1000);
		ts.captureSS(driver);
		Thread.sleep(700);
		String checkInDateExpected = "15/12/21";
		String checkInDateActual = driver.findElement(By.xpath(ap.getCheckInDate())).getText();
		Assert.assertEquals(checkInDateActual, checkInDateExpected);
		//Select the date for the check-out
		WebElement date2 = driver.findElement(By.xpath(ap.getDate()+"[contains(text(), "+checkOut.getDate()+")]"));
		hlt.highlightElement(driver, date2);
		date2.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(1000);
		ts.captureSS(driver);
		Thread.sleep(700);
		String checkOutDateExpected = "20/12/21";
		String checkOutDateActual = driver.findElement(By.xpath(ap.getCheckOutDate())).getText();
		Assert.assertEquals(checkOutDateActual, checkOutDateExpected);
	}
	
	@Test(priority=6)
	public void testGuestSelection() throws IOException, InterruptedException {
		test.log(LogStatus.INFO,"Select Number of Guests");
		//Click to open the Guest Box
		WebElement guestBox = driver.findElement(By.xpath(ap.getGuestBox()));
		hlt.highlightElement(driver, guestBox);
		guestBox.click();
		Thread.sleep(700);
		ts.captureSS(driver);
		Thread.sleep(700);
		//Get the default number of Guests selected.
		String guests = driver.findElement(By.xpath(ap.getGuests())).getAttribute("value");
		String totalGuest = guests.substring(0,guests.length()-1);
		int total = Integer.parseInt(totalGuest);
		int rem=0;
		int totalGuests = Integer.parseInt(strArr[3].substring(0,strArr[3].length()-2));
		if(total<totalGuests) {
			rem = totalGuests-total;
			for(int k=0;k<rem;k++) {
				//Increase the number of guests
				WebElement guestPlus = driver.findElement(By.xpath(ap.getGuestsPlus()));
				hlt.highlightElement(driver, guestPlus);
				guestPlus.click();
				Thread.sleep(700);
			}
		}
		else if(total>totalGuests) {
			rem = total-totalGuests;
			for(int k=0;k<rem;k++) {
				//Reduce the number of guests
				WebElement guestMinus = driver.findElement(By.xpath(ap.getGuestsMinus()));
				hlt.highlightElement(driver, guestMinus);
				guestMinus.click();
				Thread.sleep(700);
			}
		}
		String finalGuests = driver.findElement(By.xpath(ap.getGuests())).getAttribute("value");
		String expectedGuests = "4+";
		Assert.assertEquals(finalGuests, expectedGuests);
		//Apply and search for the Holiday Homes for selected number of guests.
		WebElement applyBtn = driver.findElement(By.xpath(ap.getApplyBtn()));
		hlt.highlightElement(driver, applyBtn);
		applyBtn.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(700);
		ts.captureSS(driver);
		Thread.sleep(700);
	}
	
	@Test(priority=7)
	public void testSortBy() throws IOException, InterruptedException {
		test.log(LogStatus.INFO, "Sort result by ratings");
		WebElement sort = driver.findElement(By.xpath(ap.getSortBy()));
		hlt.highlightElement(driver, sort);
		sort.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(700);
		ts.captureSS(driver);
		Thread.sleep(700);
		//Select the Sort By as Traveller Ratings.
		WebElement ratingsSort = driver.findElement(By.xpath(ap.getSortRatings()));
		hlt.highlightElement(driver, ratingsSort);
		ratingsSort.click();
		Thread.sleep(700);
		ts.captureSS(driver);
		Thread.sleep(700);
	}
	
	@Test(priority=8)
	public void testAmmenitiesFilter()  throws IOException, InterruptedException {
		test.log(LogStatus.INFO, "Filter the results for Holiday homes with Elevator Access");
		//Click to see more available options for selecting the required ammenities provided by the holiday homes.
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement ammenities = driver.findElement(By.xpath(ap.getAmmenitiesShowMore()));
		Thread.sleep(700);
		ts.captureSS(driver);
		Thread.sleep(700);
		hlt.highlightElement(driver, ammenities);
		ammenities.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//Click on Elevator option.
		WebElement elevator = driver.findElement(By.xpath(ap.getElevator()));
		hlt.highlightElement(driver, elevator);
		elevator.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(700);
		ts.captureSS(driver);
		Thread.sleep(700);
		
	}
	
	@Test(priority=9)
	public void testWirteExcelFile() {
		test.log(LogStatus.INFO, "Write the result in the Excel File");
		int count=0;
		int i=3;
		while (count<3) {
			ArrayList<String> resultArray = new ArrayList<String>(4);
			int divRank = i;
			String xPath = ap.getHomePre()+"["+divRank+"]";
			String classVal = driver.findElement(By.xpath(xPath)).getAttribute("class");
			if(classVal.equals("dXXoB")) {
				resultArray.add(driver.findElement(By.xpath(xPath+ap.getHomeTitle())).getText());
				try {
					String str1 = driver.findElement(By.xpath(xPath+ap.getHomeReview())).getAttribute("class");
					String[] reviewArr = str1.split("_");
					int rate = Integer.parseInt(reviewArr[3])/10;
					String rating = Integer.toString(rate);
					resultArray.add(rating);
				} catch(org.openqa.selenium.NoSuchElementException ex1) {
					String rating="NA";
					resultArray.add(rating);
				}
				
				resultArray.add(driver.findElement(By.xpath(xPath+ap.getHomePerNight())).getText());
				resultArray.add(driver.findElement(By.xpath(xPath+ap.getHomeTotal())).getText());
				count++;
				i++;
				//Log the details of holiday homes in the Excel file
				try {
					String path = System.getProperty("user.dir");
					wef.writeHolidayHomes(path+ "\\Excel\\DataFile.xlsx", resultArray);
				} catch (IOException e) {
					e.printStackTrace();
					
				}
			} else {
				i++;
			}
		}
	}
	@AfterMethod
	public void testStatusLog(ITestResult testResult) {
		if(testResult.getStatus()==1) {
			test.log(LogStatus.PASS,"Test Passed");
		} else {
			test.log(LogStatus.FAIL,"Test Failed");
		}
		
	}
	
	@AfterTest
	public void stopWebDriver() {
		driver.quit();
	}
	//Flush the extent report
	@AfterClass
	public static void endTest()
	{
		report.endTest(test);
		report.flush();
	}

}
