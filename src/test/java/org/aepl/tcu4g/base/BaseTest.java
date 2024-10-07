package org.aepl.tcu4g.base;

import java.time.Duration;

import org.aepl.tcu4g.utils.ExcelReportUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

public class BaseTest {
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected final String BASE_URL = "http://20.219.88.214:6102/login";
	protected SoftAssert softAssert;

	@BeforeSuite
	public void setUpSuite() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("chrome.driver.path", "chromedriver-win64/chromedriver.exe"));
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(System.getProperty("webdriver.wait.timeout", "10"))));
			ExcelReportUtil.initializeWorkbook();
		}
	}

	@BeforeMethod
	public void setUp() {
		softAssert = new SoftAssert();
		if (driver != null) {
			driver.get(BASE_URL);
		}
	}

	@AfterSuite
	public void tearDownSuite() {
		ExcelReportUtil.saveWorkbook();
		if (driver != null) {
			driver.quit();
		}
	}
}

