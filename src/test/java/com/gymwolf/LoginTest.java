package com.gymwolf;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {
	
	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	@Parameters({ "username", "password", "expectedMessage" })
	public void LogInCorrect(String username, String password, String expectedErrorMessage) {
		System.out.println("Starting logInCorrect");
		
		
//		Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
//		Maximize browser window
		driver.manage().window().maximize();
		
//		open  page
		String url = "https://www.gymwolf.com/staging/";
		driver.get(url);
		System.out.println("Page is opened");

//		Click logi sisse button
		WebElement logButton = driver.findElement(By.xpath(("//div[@id='main-menu']/ul[@class='nav navbar-nav navbar-right']//a[@title='Logi sisse']")));
		logButton.click(); 	
	
//		Oota, et pop up logi sisse oleks ees
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='login-front']/div[@class='modal-dialog']/div[@class='modal-content']//form//button[@type='submit']")));

		
//		enter username
		WebElement usernameElement = driver.findElement(By.xpath(("//div[@id='login-front']/div[@class='modal-dialog']//form//input[@name='email']")));
		usernameElement.sendKeys(username);		
		
//		enter password
		WebElement passwordElement = driver.findElement(By.xpath(("/html//div[@id='login-front']/div[@class='modal-dialog']//form//input[@name='password']")));
		passwordElement.sendKeys(password);
		
//		click login button
		WebElement logInButton = driver.findElement(By.xpath("//div[@id='login-front']/div[@class='modal-dialog']/div[@class='modal-content']//form//button[@type='submit']"));
		logInButton.click();
		
//		succesful login message
		WebElement successMessage = driver.findElement(By.xpath(("//div[@id='main-menu']/ul[@class='nav navbar-nav navbar-right']//a[@title='Harjutused ja kavad']")));
		String expectedMessage = "Harjutused ja kavad";
		String actualMessage = successMessage.getText();
		//Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual Message: " + actualMessage
						+ "\nExpected Message: " + expectedMessage);
		
		//Close browser
		driver.quit();
	}
	
	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	@Parameters({ "username", "password", "expectedMessage" })
	public void LogInWrong(String username, String password, String expectedErrorMessage) {
		System.out.println("Starting loginWrong");
				
//		Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
//		Maximize browser window
		driver.manage().window().maximize();
		
//		open  page
		String url = "https://www.gymwolf.com/staging/";
		driver.get(url);
		System.out.println("Page is opened");

//		Click logi sisse button
		WebElement logButton = driver.findElement(By.xpath(("//div[@id='main-menu']/ul[@class='nav navbar-nav navbar-right']//a[@title='Logi sisse']")));
		logButton.click(); 	
	
//		Oota, et pop up logi sisse oleks ees
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='login-front']/div[@class='modal-dialog']/div[@class='modal-content']//form//button[@type='submit']")));

//		enter username
		WebElement usernameElement = driver.findElement(By.xpath(("//div[@id='login-front']/div[@class='modal-dialog']//form//input[@name='email']")));
		usernameElement.sendKeys(username);	
		
//		enter password
		WebElement passwordElement = driver.findElement(By.xpath(("/html//div[@id='login-front']/div[@class='modal-dialog']//form//input[@name='password']")));
		passwordElement.sendKeys(password);
		
//		click login button
		WebElement logInButton = driver.findElement(By.xpath("//div[@id='login-front']/div[@class='modal-dialog']/div[@class='modal-content']//form//button[@type='submit']"));
		logInButton.click();
		
//		succesful login message
		WebElement successMessage = driver.findElement(By.xpath(("//div[@class='landing-content']/div[2]//div[@class='alert alert-danger']")));
		String expectedMessage = "Vale parool või e-maili aadress, palun proovige uuesti.";
		String actualMessage = successMessage.getText();
		//Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual Message: " + actualMessage
						+ "\nExpected Message: " + expectedMessage);
		
		//Close browser
		driver.quit();
	
	}
		
}
