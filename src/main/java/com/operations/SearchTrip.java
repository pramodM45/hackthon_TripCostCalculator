package com.operations;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driverManagement.DriverSetup;
import com.excelFileHandler.ReadExcelFile;
import com.excelFileHandler.WriteExcelFile;
import com.propertiesHandler.AccessProperties;

public class SearchTrip {
	static WebDriver driver;
	DriverSetup ds = new DriverSetup();
	AccessProperties ap = new AccessProperties();
	WriteExcelFile wef = new WriteExcelFile();
	TakeScreenshots ts = new TakeScreenshots();
	Highlighter hlt = new Highlighter();
	
	//Open the url in the selected
	public void openUrl(WebDriver driver) throws IOException, InterruptedException {
		this.driver = driver;
		driver.get("https://www.tripadvisor.in/");
		ts.captureSS(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void mouseMover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		Action mouse = action.moveToElement(element).build();
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
	
	//Method to search Holiday Home
	public void searchHolidayHome(String city, String checkInDate, String checkOutDate, String tguests) throws IOException, InterruptedException {
		
		//Enter the name of the required city in the search field.
		WebElement homeSearch = driver.findElement(By.xpath(ap.getHomeSearch()));
		hlt.highlightElement(driver, homeSearch);
		homeSearch.sendKeys(city);
		//Select one of the option from the available options.
		WebElement option1 = driver.findElement(By.cssSelector("a[href='/Tourism-g294207-Nairobi-Vacations.html']"));
		hlt.highlightElement(driver, option1);
		option1.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		ts.captureSS(driver);
		
		Thread.sleep(3000);
		//Select the Holiday Homes Option for trip in the selected city.
		WebElement hldyhm = driver.findElement(By.xpath(ap.getHolidayHomesBtn()));
		hlt.highlightElement(driver, hldyhm);
		hldyhm.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		ts.captureSS(driver);
		
		//Open the dialog box to select the Check-In date.
		WebElement check_In = driver.findElement(By.xpath(ap.getCheckInDiv()));
		hlt.highlightElement(driver, check_In);
		check_In.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Find out the month on the first column of the calendar box.
		String calMonth  = driver.findElement(By.xpath(ap.getCalMonth())).getText();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String[] calArr = calMonth.split(" ");
		Date checkIn = dateConverter(checkInDate);
		Date checkOut = dateConverter(checkOutDate);
		int diff = calendarMonths(calArr[0], checkIn.getMonth());
		for(int i=0;i<diff;i++) {
			WebElement forward = driver.findElement(By.xpath(ap.getCalForward()));
			hlt.highlightElement(driver, forward);
			forward.click();
		}
		//Select the date for the check-in.
		WebElement date1 = driver.findElement(By.xpath(ap.getDate()+"[contains(text(),"+checkIn.getDate()+" )]"));
		hlt.highlightElement(driver, date1);
		date1.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		ts.captureSS(driver);
		//Select the date for the check-out
		WebElement date2 = driver.findElement(By.xpath(ap.getDate()+"[contains(text(), "+checkOut.getDate()+")]"));
		hlt.highlightElement(driver, date2);
		date2.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		ts.captureSS(driver);
		
		//Click to open the Guest Box
		WebElement guestBox = driver.findElement(By.xpath(ap.getGuestBox()));
		hlt.highlightElement(driver, guestBox);
		guestBox.click();
		ts.captureSS(driver);
		//Get the default number of Guests selected.
		String guests = driver.findElement(By.xpath(ap.getGuests())).getAttribute("value");
		String totalGuest = guests.substring(0,guests.length()-1);
		int total = Integer.parseInt(totalGuest);
		int rem=0;
		int totalGuests = Integer.parseInt(tguests);
		if(total<totalGuests) {
			rem = totalGuests-total;
			for(int k=0;k<rem;k++) {
				//Increase the number of guests
				WebElement guestPlus = driver.findElement(By.xpath(ap.getGuestsPlus()));
				hlt.highlightElement(driver, guestPlus);
				guestPlus.click();
			}
		}
		else if(total>totalGuests) {
			rem = total-totalGuests;
			for(int k=0;k<rem;k++) {
				//Reduce the number of guests
				WebElement guestMinus = driver.findElement(By.xpath(ap.getGuestsMinus()));
				hlt.highlightElement(driver, guestMinus);
				guestMinus.click();
			}
		}
		//Apply and search for the Holiday Homes for selected number of guests.
		WebElement applyBtn = driver.findElement(By.xpath(ap.getApplyBtn()));
		hlt.highlightElement(driver, applyBtn);
		applyBtn.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		ts.captureSS(driver);
		
		//Open the Sort By selection dropdown.
		try {
			WebElement sort = driver.findElement(By.xpath(ap.getSortBy()));
			hlt.highlightElement(driver, sort);
			sort.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//Select the Sort By as Traveller Ratings.
			WebElement ratingsSort = driver.findElement(By.xpath(ap.getSortRatings()));
			hlt.highlightElement(driver, ratingsSort);
			ratingsSort.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} catch(org.openqa.selenium.ElementClickInterceptedException ex) {
			WebElement sort = driver.findElement(By.xpath(ap.getSortBy()));
			hlt.highlightElement(driver, sort);
			sort.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//Select the Sort By as Traveller Ratings.
			WebElement ratingsSort = driver.findElement(By.xpath(ap.getSortRatings()));
			hlt.highlightElement(driver, ratingsSort);
			ratingsSort.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		ts.captureSS(driver);
		//Click to see more available options for selecting the required ammenities provided by the holiday homes.
		WebElement ammenities = driver.findElement(By.xpath(ap.getAmmenitiesShowMore()));
		hlt.highlightElement(driver, ammenities);
		ammenities.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//Click on Elevator option.
		WebElement elevator = driver.findElement(By.xpath(ap.getElevator()));
		hlt.highlightElement(driver, elevator);
		elevator.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
}
