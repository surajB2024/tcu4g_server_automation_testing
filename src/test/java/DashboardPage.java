import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public static void setExcelSheet() {
        ExcelReportUtil.switchToSheet("Dashboard_Page");
    }

    // Dashboard dropdown
    private By overlayLocator = By.cssSelector("div.overlay");
    private By dashboardDropdown = By.xpath("//*[@id='navbarSupportedContent']/div/ul/li[1]");
    private By deviceDashboardItem = By.xpath("//*[@id='navbarSupportedContent']/div/ul/li[1]/div/a[1]");
    private By ticketDashboardItem = By.xpath("//*[@id='navbarSupportedContent']/div/ul/li[1]/div/a[3]");

    // Device Utility dropdown
    private By deviceUtilityDropdown = By.xpath("//*[@id='navbarSupportedContent']/div/ul/li[2]");
    private By myAIS140TicketsItem = By.xpath("//a[@class='dropdown-item' and text()=' My AIS 140 Tickets ']");
    private By governmentServersItem = By.xpath("//a[@class='dropdown-item' and text()=' Government Servers ']");
    private By fotaItem = By.xpath("//a[@class='dropdown-item' and text()=' FOTA ']");
    private By otaItem = By.xpath("//a[@class='dropdown-item' and text()='OTA ']");
    private By deviceModelsItem = By.xpath("//a[@class='dropdown-item' and text()=' Device Models ']");
    private By dealerFotaItem = By.xpath("//a[@class='dropdown-item' and text()=' Dealer FOTA ']");
    private By addDispatchDevicesItem = By.xpath("//a[@class='dropdown-item' and text()=' Add Dispatch Devices ']");
    private By simLotsItem = By.xpath("//a[@class='dropdown-item' and text()=' SIM Lots ']");
    private By changeMobileItem = By.xpath("//a[@class='dropdown-item' and text()=' Change Mobile ']");

    public void clickDashboardDropdown() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator));

        // Wait for the dropdown to be clickable
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dashboardDropdown));

        // Try to click using standard click method
        try {
            dropdown.click();
        } catch (Exception e) {
            // If standard click fails, try using JavaScript
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", dropdown);
        }    }

    public void clickTicketDashboard() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ticketDashboardItem));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickDeviceDashboard() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(deviceDashboardItem));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickDeviceUtilityDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(deviceUtilityDropdown)).click();
    }

    public void clickMyAIS140Tickets() {
        wait.until(ExpectedConditions.elementToBeClickable(myAIS140TicketsItem)).click();
    }

    public void clickGovernmentServers() {
        wait.until(ExpectedConditions.elementToBeClickable(governmentServersItem)).click();
    }

    public void clickFOTA() {
        wait.until(ExpectedConditions.elementToBeClickable(fotaItem)).click();
    }

    public void clickOTA() {
        wait.until(ExpectedConditions.elementToBeClickable(otaItem)).click();
    }

    public void clickDeviceModels() {
        wait.until(ExpectedConditions.elementToBeClickable(deviceModelsItem)).click();
    }

    public void clickDealerFOTA() {
        wait.until(ExpectedConditions.elementToBeClickable(dealerFotaItem)).click();
    }

    public void clickAddDispatchDevices() {
        wait.until(ExpectedConditions.elementToBeClickable(addDispatchDevicesItem)).click();
    }

    public void clickSIMlots() {
        wait.until(ExpectedConditions.elementToBeClickable(simLotsItem)).click();
    }

    public void clickChangeMobile() {
        wait.until(ExpectedConditions.elementToBeClickable(changeMobileItem)).click();
    }

    // Add methods to verify if elements are displayed
    public boolean isDashboardDropdownDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardDropdown)).isDisplayed();
    }

    public boolean isDeviceUtilityDropdownDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(deviceUtilityDropdown)).isDisplayed();
    }
}