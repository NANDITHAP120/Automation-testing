package com.example.My_projectlatest;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

public class LoginTest {

    static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        
    	WebDriverManager.chromedriver().driverVersion("130.0.6723.116").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize(); 
    }

    @Test
    public void testPositiveLogin() {
        
       
    	driver.get("https://practicetestautomation.com/practice-test-login/");
     
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("student");

        
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Password123");

        
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

       
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("practicetestautomation.com/logged-in-successfully/"));

        WebElement successMessage = driver.findElement(By.xpath("//strong[contains(text(),'Congratulations student. You successfully logged in!')]"));

       
        assertTrue(successMessage.isDisplayed());
        WebElement logoutButton = driver.findElement(By.linkText("Log out"));
        assertTrue(logoutButton.isDisplayed());
        
    }
    @Test
    public void testNegativeUsername() {
    	driver.get("https://practicetestautomation.com/practice-test-login/");
       
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("incorrectUser");

        
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Password123");

        
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();


       
        WebElement errorMessage = driver.findElement(By.id("error"));

        
        assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");

        String errorMessageText = errorMessage.getText().trim();
        assertTrue(errorMessageText.contains("Your username is invalid!"), 
                "Expected error message: 'Your username is invalid!', but got: " + errorMessageText);
        
    }
    @Test
    public void testNegativePassword() {
    	driver.get("https://practicetestautomation.com/practice-test-login/");
      
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("student");

       
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("incorrect");

       
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();


       
        WebElement errorMessage = driver.findElement(By.id("error"));

        
        assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");

        String errorMessageText = errorMessage.getText().trim();
        assertTrue(errorMessageText.contains("Your password is invalid!"), 
                "Expected error message: 'Your password is invalid!', but got: " + errorMessageText);
    }
   
   
    @AfterAll
    public static void tearDown() {
       
        if (driver != null) {
            driver.quit();
        }
    }
}
