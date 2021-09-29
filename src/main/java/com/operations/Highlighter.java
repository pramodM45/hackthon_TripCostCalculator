package com.operations;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Highlighter {
	
	private WebElement lastElem = null;
	private String lastBorder = null;
	
	public void highlightElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		if (lastElem != null) {
	        try {
	            // if there already is a highlighted element, unhighlight it
	            js.executeScript(lastBorder, lastElem);
	        } catch (StaleElementReferenceException ignored) {
	            // the page got reloaded, the element isn't there
	        }
		}
	    // remember the new element
	    lastElem = ele;
	    lastBorder = ele.getCssValue("border");
	    lastBorder = "arguments[0].setAttribute('style',' border:"+lastBorder+";');";
	    
	    js.executeScript("arguments[0].setAttribute('style', ' border: 2px solid red;');", ele);
	}

}
