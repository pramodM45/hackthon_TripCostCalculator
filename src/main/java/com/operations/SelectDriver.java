package com.operations;

import org.openqa.selenium.WebDriver;

import com.driverManagement.DriverSetup;

public class SelectDriver extends DriverSetup{
	WebDriver driver;
	
	public WebDriver selectDriver() {
		System.out.println("Select Driver:");
		System.out.println("1: Chrome");
		System.out.println("2: Firefox");
		System.out.println("3: MS Edge");
		System.out.println("4: IE");
		//Scanner input =  new Scanner(System.in);
		//int choose = input.nextInt();
		driver = openDriver(1);
		return driver;
		// /html/body/div[13]/div/div/div[2]/div/div/div[2]/div[1]/div[1]/div
	}

}
