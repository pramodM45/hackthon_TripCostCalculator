package com.driverManagement;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	
public static WebDriver driver;
	
	public WebDriver openDriver(int option) {
//		if(option==1) {
//			openChrome();
//		} else if(option==2) {
//			openFirefox();
//		} else if(option==3) {
//			openEdge();
//		} else if(option==4) {
//			openIE();
//		}
		
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		try {
			driver = new RemoteWebDriver(new URL("http://192.168.29.252:4444/wd/hub"),dc);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return driver;
	}
	
	public void openChrome() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	public void openFirefox() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	public void openEdge() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	}
	public void openIE() {
		WebDriverManager.iedriver();
		driver =  new InternetExplorerDriver();
		driver.manage().window().maximize();
	}


}
