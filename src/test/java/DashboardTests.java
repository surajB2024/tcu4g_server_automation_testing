import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public class DashboardTests extends BaseTest {
    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private final String VALID_EMAIL = "shivam.mohite@accoladeelectronics.com";
    private final String VALID_PASSWORD = "2t31tn4g";

    @BeforeClass
    public void setUpClass() {
        DashboardPage.setExcelSheet();
        super.setUp();
        loginPage = new LoginPage(driver, wait);
        dashboardPage = new DashboardPage(driver, wait);

        // Login before running dashboard tests
        loginPage.login(VALID_EMAIL,VALID_PASSWORD);
        System.out.println("Log in successful ");
    }

    @Test(priority = 1)
    public void testDashboardDropdownDisplayed() {
        softAssert.assertTrue(dashboardPage.isDashboardDropdownDisplayed(), "Dashboard dropdown is not displayed");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void testDeviceUtilityDropdownDisplayed() {
        softAssert.assertTrue(dashboardPage.isDeviceUtilityDropdownDisplayed(), "Device Utility dropdown is not displayed");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void testDeviceDashboard() {
        System.out.println("Starting testDeviceDashboard");
        dashboardPage.clickDashboardDropdown();
        dashboardPage.clickDeviceDashboard();

        String expectedUrlAfterLogin = "http://20.219.88.214:6102/dashboard";
        wait.until(ExpectedConditions.urlToBe(expectedUrlAfterLogin));
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrlAfterLogin, "Failed to navigate to Device Dashboard");
        softAssert.assertAll();
        System.out.println("Finished testDeviceDashboard");
    }

    @Test(priority = 4)
    public void testTicketDashboard() {
        System.out.println("Starting testTicketDashboard");
        dashboardPage.clickDashboardDropdown();
        dashboardPage.clickTicketDashboard();

        // Wait for the new page to load
        wait.until(ExpectedConditions.urlContains("/ticket-dashboard-page"));

        // Perform your assertions
        softAssert.assertTrue(driver.getCurrentUrl().contains("/ticket-dashboard-page"),
                "Failed to navigate to Ticket Dashboard");
        softAssert.assertAll();
        System.out.println("Finished testTicketDashboard");
    }

    @Test(priority = 5)
    public void testDeviceUtilityDropdownItems() {
        dashboardPage.clickDeviceUtilityDropdown();
        dashboardPage.clickMyAIS140Tickets();
        softAssert.assertTrue(driver.getCurrentUrl().contains("/my-tickets"), "Failed to navigate to My AIS 140 Tickets");

        dashboardPage.clickDeviceUtilityDropdown();
        dashboardPage.clickGovernmentServers();
        softAssert.assertTrue(driver.getCurrentUrl().contains("/govt-servers"), "Failed to navigate to Government Servers");

        // Add more tests for other Device Utility items

        softAssert.assertAll();
    }

    // Add more test methods for other functionalities on the dashboard page
}