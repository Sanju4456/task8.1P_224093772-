package web.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class LoginServiceTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Ensure to set the correct path to your chromedriver executable
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/sanju/chromedriver-win64 (1)/chromedriver-win64/chromedriver.exe");

        // Initialize ChromeDriver
        driver = new ChromeDriver();
    }

    @Test
    public void openChromeAndClose() {
        // Step 1: Locate chrome driver folder (chromium browser) in the local drive.
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/sanju/chromedriver-win64 (1)/chromedriver-win64/chromedriver.exe");

        // Step 2: Use above chrome driver to open up a chromium browser.
        WebDriver driver = new ChromeDriver();

        // Sleep for a while to allow the browser to open
        sleep(5); // Sleep for 5 seconds

        // Your testing logic goes here.
        // For example, you can maximize the window and navigate to a URL.
        driver.manage().window().maximize();
        driver.get("C:/SIT707/Task 8.1P/8.1P-resources/pages/login.html");

        // Sleep for a while to observe the actions (optional)
        sleep(5); // Sleep for 5 seconds

        // close chrome driver. You can comment below line to keep the browser opened.
        driver.quit();
    }

    @Test
    public void testLoginSuccess() {
        // Navigate to the local login.html file
        driver.get("C:/SIT707/Task 8.1P/8.1P-resources/pages/login.html");
        
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("passwd"));
        WebElement dob = driver.findElement(By.name("dob"));

        username.sendKeys("user");
        password.sendKeys("pass");
        dob.sendKeys("2000-01-01");

        driver.findElement(By.cssSelector("input[type='submit']")).click();

        // Wait for the response and check the title
        String title = driver.getTitle();
        assertEquals("success", title);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void testLoginFailure() {
        // Set up the test scenario
        String username = "invalidUser";
        String password = "invalidPassword";
        String dob = "2000-01-01"; // Example date of birth
        
        // Perform the login attempt
        boolean loginResult = LoginService.login(username, password, dob);
        
        // Verify the result
        assertFalse(loginResult); // Expecting login to fail
    }
    // Additional unit tests for the LoginService
    @Test
    public void testLoginFailureWrongUsername() {
        assertFalse(LoginService.login("wrongPass", "pass", "2000-01-01"));
    }

    @Test
    public void testLoginFailureWrongPassword() {
        assertFalse(LoginService.login("user", "wrongPass", "2000-01-01"));
    }



    // Utility method for sleep
    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

