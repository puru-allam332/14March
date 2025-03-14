package com.utils.in;

import java.io.File;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class setupExtentReports {
	public static ExtentReports getExtentReports() {
ExtentReports reports=new ExtentReports(); 
//reports.setSystemInfo("get browser data",getCapabilities.getCap(driver).getBrowserName());
//reports.setSystemInfo("get browser version",getCapabilities.getCap(driver).getBrowserVersion());

//

//
String getExtentFolder=System.getProperty("user.dir");
File file=new File(getExtentFolder+"./src/test/resources/ExtentReports/login.html");
ExtentSparkReporter epr=new  ExtentSparkReporter(file);


epr.config().setTheme(Theme.DARK);
epr.config().setDocumentTitle("waste");
epr.config().setReportName("jamppa");
epr.config().setTimeStampFormat("dd_MM_yy HH:mm:ss");

//epr.loadJSONConfig(System.getProperty("user.dir"));
reports.attachReporter(epr);
//reports.flush();

	return reports;
	}
}