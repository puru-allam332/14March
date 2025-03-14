package com.listeners.in;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.utils.in.ReadConfigFileClass;
import com.utils.in.TakingScreenshotClass;
import com.utils.in.getCapabilities;
import com.utils.in.setupExtentReports;

import base.Base;

public class MyListeners implements ITestListener{
	getCapabilities cap;
ExtentReports	reports=setupExtentReports.getExtentReports();


ExtentTest test;

public void onStart(ITestContext context) {

reports.setSystemInfo("Java version", System.getProperty("java.version"));
reports.setSystemInfo("Operating system", System.getProperty("os.name"));
Properties prop = null;
try {
	prop = ReadConfigFileClass.ReadConfigFileMethod();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
reports.setSystemInfo("Username", prop.getProperty("email"));
	String str=context.getName();
	System.out.println("onStart onStart  "+str);
}

public void onTestStart(ITestResult result)
{
	WebDriver driver=null;
	try {
		//driver=	(WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//test.addScreenCaptureFromPath(TakingScreenshotClass.takeScreenshot(testName,driver),testName);
		//test.addScreenCaptureFromPath(Base.takeScreenshot(testName, driver));
		
	}
	reports.setSystemInfo("get browser data",getCapabilities.getCap(driver).getBrowserName());
	reports.setSystemInfo("get browser version",getCapabilities.getCap(driver).getBrowserVersion());
	//reports.setSystemInfo("get browser version",getCapabilities.getCap(driver).);
	test=reports.createTest("verify the login page");
	String onTest=result.getMethod().getMethodName();
	test.log(Status.INFO, "this   kkjhghh "+onTest);
//	test.assignAuthor("purushotham");
//	test.assignCategory("smoke");
//	test.assignDevice("Windows 7");


}

public void onTestSuccess(ITestResult result) {
	String onTest=result.getMethod().getMethodName();
	test.log(Status.PASS, MarkupHelper.createLabel("Account got sucessfully login  "+onTest, ExtentColor.YELLOW));
}

public void onTestSkipped(ITestResult result) {
	String onTest=result.getMethod().getMethodName();
	test.log(Status.SKIP, MarkupHelper.createLabel("Methods got skipped  "+onTest, ExtentColor.RED));
	
}

public void onTestFailure(ITestResult result) {
//	String testName = result.getName();
//	test.log(Status.FAIL,testName+" got failed");
//	
	WebDriver driver = null;
//	
//	try {
//		driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
//	} catch (IllegalArgumentException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IllegalAccessException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (NoSuchFieldException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (SecurityException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	test.addScreenCaptureFromPath(Base.takeScreenshot(testName,driver),testName);

	
	
	
	String testName=result.getName();

try {
	//driver=	(WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	//test.addScreenCaptureFromPath(TakingScreenshotClass.takeScreenshot(testName,driver),testName);
	//test.addScreenCaptureFromPath(Base.takeScreenshot(testName, driver));
	
}
test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(TakingScreenshotClass.takeScreenshot(testName, driver)).build());
//test.addScreenCaptureFromPath(TakingScreenshotClass.takeScreenshot(testName, driver));
}

public void onTestFailedWithTimeout(ITestResult result)
{
	System.out.println("onTestFailedWithTimeout");
}

public void onTestFailedButWithinSuccessPercentage(ITestResult result)
{
	System.out.println("onTestFailedButWithinSuccessPercentage");
}


public void onFinish(ITestContext context) {
	reports.flush();
	String str=context.getName();
	System.out.println("onFinish onFinish "+str);
	File file=new File(System.getProperty("user.dir")+"./src/test/resources/ExtentReports/login.html");
	try {
		Desktop.getDesktop().browse(file.toURI());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}