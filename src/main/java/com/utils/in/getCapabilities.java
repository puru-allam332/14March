package com.utils.in;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class getCapabilities {
	public static Capabilities cap;
	public static Capabilities getCap(WebDriver driver) {
		
		 return cap=((RemoteWebDriver)driver).getCapabilities();
		 
		 
}
}
