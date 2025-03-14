package com.login.in;

import java.io.File;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.utils.in.ReadConfigFileClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginWithoutValidCredentals {
public WebDriver driver;
public Properties prop;

//public ;
 
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@BeforeMethod
	public void setup() throws IOException {
		prop=ReadConfigFileClass.ReadConfigFileMethod();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String sss=prop.getProperty("url");
		driver.get("http://tutorialsninja.com/demo/");
		
		
	}
	
	@Test
	public void loginWithValidCredentials() {
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("purushothaminfo71@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("888@Hello");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
	}

	@Test
	public void loginWithInvalidCredentials() {
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("amotooricap"+generateTimeStamp()+"@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		System.out.println(10/0);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText().contains("WarningS: 0No match for E-Mail Address and/or Password."));
		
	}
	
	@Test(dependsOnMethods="loginWithInvalidCredentials")
	public void loginWithoutCredentials() {
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText().contains("Warning: No match for E-Mail Address and/or Password."));
		
	}
//	
//	public String getScreenshot() throws IOException {
//		
//	File source= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	String sss=System.getProperty("user.dir");
//	File dest=new File(sss+"./src/test/resources/ImageFolder/index.png");
//	FileUtils.copyFile(source, dest);
//	return dest.getAbsolutePath();
//		//driver.
//		}
	
	public String generateTimeStamp(){
		
		Date date = new Date();
		return date.toString().replace(" ","_").replace(":","_");
		
	}

	
	
}
