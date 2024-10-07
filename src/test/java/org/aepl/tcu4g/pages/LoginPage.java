/**
 * LoginPage.java
 * Created by: Arnav T.
 * Created on: 21/08/2024
 * Last updated on: 23/08/2024

 * This class represents the Login Page of the application and provides
 * methods to interact with its elements using the Page Object Model pattern.

 * Key features:
 * - Encapsulates all interactions with the login page
 * - Provides methods for common actions like entering credentials and clicking sign in

 * Important notes:
 * - BASE_URL should be updated if the login page URL changes
 * - Locators (e.g., CSS selectors) may need updates if the page structure changes

 * To expand this pattern to other pages:
 * 1. Create similar classes for each page (e.g., DashboardPage, ProfilePage)
 * 2. Implement methods specific to each page's functionality
 * 3. Use a common base class for shared functionality across pages
 * 4. Consider implementing a PageFactory pattern for more complex pages
 */
package org.aepl.tcu4g.pages;

import org.aepl.tcu4g.utils.ExcelReportUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /*Always create setExcelSheet function for every page class and set the name of the sheet in switchToSheet function*/
    public static void setExcelSheet() {
        ExcelReportUtil.switchToSheet("Login_Page");
    }

    public void navigateToLoginPage() {
        String BASE_URL = "http://20.219.88.214:6102/login";
        driver.get(BASE_URL);
    }

    public void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[formcontrolname='email']")));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(By.cssSelector("input[formcontrolname='password']"));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickSignIn() {
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary.btn-md.btn-block")));
        signInButton.click();
    }

    // Add other methods for login page interactions
}