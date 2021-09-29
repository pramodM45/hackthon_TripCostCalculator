package com.operations;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshots {
	
static int ssCount=0;
	
	public static void captureSS( WebDriver driver) throws IOException, InterruptedException {
		Thread.sleep(2000);
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		ssCount+=1;
		String path = System.getProperty("user.dir");
		String fileLocation = path+"\\Output-Reports\\Screenshots\\Screenshot"+ssCount+".jpg";
		FileUtils.copyFile(src, new File(fileLocation));

	}

}
