//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.ITestResult;
//import org.testng.annotations.*;
//
//import java.time.Duration;
//
//public class LoginAutomation {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private final String BASE_URL = "http://20.219.88.214:6102/login";
//    private final String VALID_EMAIL = "shivam.mohite@accoladeelectronics.com";
//    private final String VALID_PASSWORD = "2t31tn4g";
//
//    @BeforeMethod
//    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\arnav\\OneDrive\\Desktop\\ACCOLADE\\Testing automation\\chromedriver-win64\\chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    @Test(priority = 1)
//    public void testNavigateToWebsite() {
//        driver.get(BASE_URL);
//        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL, "Failed to navigate to the login page");
//    }
//
//    @Test(priority = 2)
//    public void testLoginPageDisplayed() {
//        driver.get(BASE_URL);
//        WebElement loginForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("form")));
//        Assert.assertTrue(loginForm.isDisplayed(), "Login form is not displayed");
//    }
//
//    @Test(priority = 3)
//    public void testValidLogin() {
//        performLogin(VALID_EMAIL, VALID_PASSWORD);
//        String expectedUrlAfterLogin = "http://20.219.88.214:6102/dashboard";
//        wait.until(ExpectedConditions.urlToBe(expectedUrlAfterLogin));
//        Assert.assertEquals(driver.getCurrentUrl(), expectedUrlAfterLogin, "Login failed with valid credentials");
//    }
//
//    @Test(priority = 4)
//    public void testEmailFieldAcceptsVariousCharacters() {
//        driver.get(BASE_URL);
//        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[formcontrolname='email']")));
//        String testInput = "test123!@#$%^&*()_+{}|:<>?";
//        emailInput.sendKeys(testInput);
//        Assert.assertEquals(emailInput.getAttribute("value"), testInput, "Email field does not accept all characters");
//    }
//
//    @Test(priority = 5)
//    public void testPasswordFieldAcceptsVariousCharacters() {
//        driver.get(BASE_URL);
//        WebElement passwordInput = driver.findElement(By.cssSelector("input[formcontrolname='password']"));
//        String testInput = "pass123!@#$%^&*()_+{}|:<>?";
//        passwordInput.sendKeys(testInput);
//        Assert.assertEquals(passwordInput.getAttribute("value"), testInput, "Password field does not accept all characters");
//    }
//
//    @Test(priority = 6) //site does not meet this test case, not a bugged test case
//    public void testInvalidEmailValidPassword() {
//        driver.get(BASE_URL);
//        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[formcontrolname='email']")));
//        emailInput.sendKeys("invalid@test");
//
//        WebElement passwordInput = driver.findElement(By.cssSelector("input[formcontrolname='password']"));
//        passwordInput.sendKeys(VALID_PASSWORD);
//
//        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary.btn-md.btn-block")));
//        signInButton.click();
//
//        WebElement emailErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//mat-error[contains(text(), 'Please Enter Email ID.')]")));
//    }
//
//    @Test(priority = 7)
//    public void testValidEmailInvalidPassword() {
//        performLogin(VALID_EMAIL, "invalidpassword");
//        Assert.assertNotEquals(driver.getCurrentUrl(), "http://20.219.88.214:6102/dashboard", "Login succeeded with invalid password");
//    }
//
//    @Test(priority = 8)
//    public void testBlankCredentials() {
//        driver.get(BASE_URL);
//        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary.btn-md.btn-block")));
//        signInButton.click();
//
//        WebElement emailErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//mat-error[contains(text(), 'Please Enter Email ID.')]")));
//
//        WebElement passwordErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//mat-error[contains(text(), 'Please Enter Password.')]")));
//    }
//
//    @Test(priority = 9)
//    public void testShortPassword() {
//        driver.get(BASE_URL);
//        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[formcontrolname='email']")));
//        emailInput.sendKeys(VALID_EMAIL);
//
//        WebElement passwordInput = driver.findElement(By.cssSelector("input[formcontrolname='password']"));
//        passwordInput.sendKeys("12345");
//
//        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary.btn-md.btn-block")));
//        signInButton.click();
//
//        WebElement passwordErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//mat-error[contains(text(), 'Please Enter Minimum 6 Characters.')]")));
//    }
//
//    @Test(priority = 10)
//    public void testForgotPasswordLink() {
//        driver.get(BASE_URL);
//
//        // Wait for the "Forgot Password?" link to be clickable
//        WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//a[contains(text(), 'Forgot Password?')]")
//        ));
//
//        forgotPasswordLink.click();
//
//        String expectedForgotPasswordUrl = "http://20.219.88.214:6102/login/forgot-password";
//        wait.until(ExpectedConditions.urlToBe(expectedForgotPasswordUrl));
//
//        Assert.assertEquals(driver.getCurrentUrl(), expectedForgotPasswordUrl,
//                "Failed to navigate to the forgot password page");
//    }
//    private void performLogin(String email, String password) {
//        driver.get(BASE_URL);
//        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[formcontrolname='email']")));
//        emailInput.sendKeys(email);
//
//        WebElement passwordInput = driver.findElement(By.cssSelector("input[formcontrolname='password']"));
//        passwordInput.sendKeys(password);
//
//        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary.btn-md.btn-block")));
//        signInButton.click();
//    }
//
//    // Saving the results to excel sheet
//    @BeforeSuite
//    public void setUpSuite() {
//        ExcelReportUtil.initializeWorkbook();
//    }
//
//    @AfterMethod
//    public void afterMethod(ITestResult result) {
//        String testName = result.getMethod().getMethodName();
//        String testResult = result.isSuccess() ? "PASS" : "FAIL";
//        ExcelReportUtil.addTestResult(testName, testResult);
//    }
//
//    @AfterSuite
//    public void tearDownSuite() {
//        ExcelReportUtil.saveWorkbook();
//    }
//}


