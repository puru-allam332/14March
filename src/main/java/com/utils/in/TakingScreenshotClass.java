package com.utils.in;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakingScreenshotClass {
public static String takeScreenshot(String testName,WebDriver driver) {
		
		File sourceScreenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	//	File file=new File(System.getProperty("user.dir")+"./src/test/resources/ImageFolder/"+testName+".png");
		File destinationScreenshotFile = new File(System.getProperty("user.dir")+"/src/test/resources/ImageFolder/"+testName+".png");
		try {
			FileUtils.copyFile(sourceScreenshotFile, destinationScreenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return destinationScreenshotFile.getAbsolutePath();
	
	}

}
///SauceLab/src/test/resources/ImageFolder