/**
 * BaseTest.java
 * Created by: Arnav T.
 * Created on: 21/08/2024
 * Last updated on: 23/08/2024

 * This class serves as the foundation for all test classes in the project.
 * It provides common setup and teardown procedures, as well as shared resources for tests.

 * Key features:
 * - Initializes WebDriver and WebDriverWait for each test method
 * - Sets up SoftAssert for flexible assertions
 * - Manages Excel report initialization and saving
 * - Defines BASE_URL for consistent navigation across tests

 * Important notes:
 * - Update the ChromeDriver path as needed for your environment
 * - Adjust the WebDriverWait timeout if necessary
 * - Ensure ExcelReportUtil is properly configured before running tests

 * Usage:
 * Extend this class in your test classes to inherit common setup and teardown procedures:
 *
 * public class LoginTest extends BaseTest {
 *     // Your test methods here
 * }
 */

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String BASE_URL = "http://20.219.88.214:6102/login";
    protected SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeSuite
    public void setUpSuite() {
        ExcelReportUtil.initializeWorkbook();
    }

    @AfterSuite
    public void tearDownSuite() {
        ExcelReportUtil.saveWorkbook();
    }
}