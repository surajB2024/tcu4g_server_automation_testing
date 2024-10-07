/**
 * LoginTest.java
 * Created by: Arnav T.
 * Created on: 21/08/2024
 * Last updated on: 23/08/2024

 * This class contains test cases for the login functionality of the application.
 * It extends BaseTest to leverage common setup and teardown procedures.

 * Key features:
 * - Covers various login scenarios (valid login, invalid credentials, etc.)
 * - Uses SoftAssert for multiple validations within a single test
 * - Prioritizes tests to ensure logical flow

 * Important notes:
 * - Update VALID_EMAIL and VALID_PASSWORD as needed for the test environment
 * - Ensure all locators are up-to-date with the current application version
 * - Consider data-driven testing for more comprehensive coverage
 */
package org.aepl.tcu4g.tests;

import org.aepl.tcu4g.base.BaseTest;
import org.aepl.tcu4g.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTests extends BaseTest {
	private LoginPage loginPage;
	private final String VALID_EMAIL = "suraj.bhalerao@accoladeelectronics.com";
	private final String VALID_PASSWORD = "cqf9tnvl";

	@BeforeClass
	public static void setUpClass() {
		LoginPage.setExcelSheet();
	}

	@BeforeMethod
	@Override
	public void setUp() {
		super.setUp();
		loginPage = new LoginPage(driver, wait);
	}

	@Test(priority = 1)
	public void testNavigateToWebsite() {
		driver.get(BASE_URL);
		softAssert.assertEquals(driver.getCurrentUrl(), BASE_URL, "Failed to navigate to the login page");
		softAssert.assertAll();
	}

	@Test(priority = 2)
	public void testLoginPageDisplayed() {
		loginPage.navigateToLoginPage();
		WebElement loginForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("form")));
		softAssert.assertTrue(loginForm.isDisplayed(), "Login form is not displayed");
		softAssert.assertAll();
	}

	@Test(priority = 3)
	public void testValidLogin() {
		loginPage.navigateToLoginPage();
		loginPage.enterEmail(VALID_EMAIL);
		loginPage.enterPassword(VALID_PASSWORD);
		loginPage.clickSignIn();

		String expectedUrlAfterLogin = "http://20.219.88.214:6102/dashboard";
		wait.until(ExpectedConditions.urlToBe(expectedUrlAfterLogin));
		softAssert.assertEquals(driver.getCurrentUrl(), expectedUrlAfterLogin, "Login failed with valid credentials");
		softAssert.assertAll();
	}

	@Test(priority = 4)
	public void testEmailFieldAcceptsVariousCharacters() {
		loginPage.navigateToLoginPage();
		String testInput = "test123!@#$%^&*()_+{}|:<>?";
		loginPage.enterEmail(testInput);
		WebElement emailInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[formcontrolname='email']")));
		softAssert.assertEquals(emailInput.getAttribute("value"), testInput,
				"Email field does not accept all characters");
		softAssert.assertAll();
	}

	@Test(priority = 5)
	public void testPasswordFieldAcceptsVariousCharacters() {
		loginPage.navigateToLoginPage();
		String testInput = "pass123!@#$%^&*()_+{}|:<>?";
		loginPage.enterPassword(testInput);
		WebElement passwordInput = driver.findElement(By.cssSelector("input[formcontrolname='password']"));
		softAssert.assertEquals(passwordInput.getAttribute("value"), testInput,
				"Password field does not accept all characters");
		softAssert.assertAll();
	}

	@Test(priority = 6)
	public void testInvalidEmailValidPassword() {
		loginPage.navigateToLoginPage();
		loginPage.enterEmail("invalid@test");
		loginPage.enterPassword(VALID_PASSWORD);
		loginPage.clickSignIn();

		WebElement popUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//simple-snack-bar[@class='mat-simple-snackbar ng-star-inserted']//span[contains(text(), 'User Not Found')]")));

		softAssert.assertTrue(popUp.isDisplayed(), "The 'User Not Found' pop-up is not displayed");
		softAssert.assertEquals(popUp.getText(), "User Not Found", "The pop-up text does not match 'User Not Found'");
		softAssert.assertAll();
	}

	@Test(priority = 7)
	public void testValidEmailInvalidPassword() {
		loginPage.navigateToLoginPage();
		loginPage.enterEmail(VALID_EMAIL);
		loginPage.enterPassword("invalidpassword");
		loginPage.clickSignIn();

		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//simple-snack-bar[@class='mat-simple-snackbar ng-star-inserted']/span[contains(text(), 'login Failed due to Incorrect email or password')]")));

		softAssert.assertTrue(errorMessage.isDisplayed(), "Error message for invalid credentials is not displayed");
		softAssert.assertEquals(errorMessage.getText(), "login Failed due to Incorrect email or password",
				"Error message text does not match 'Invalid credentials'");
		softAssert.assertNotEquals(driver.getCurrentUrl(), "http://20.219.88.214:6102/dashboard",
				"Login succeeded with invalid password");
		softAssert.assertAll();
	}

	@Test(priority = 8)
	public void testBlankCredentials() {
		loginPage.navigateToLoginPage();
		loginPage.clickSignIn();

		WebElement emailErrorMessage = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//mat-error[contains(text(), 'Please Enter Email ID.')]")));
		WebElement passwordErrorMessage = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//mat-error[contains(text(), 'Please Enter Password.')]")));

		softAssert.assertTrue(emailErrorMessage.isDisplayed(), "Email error message is not displayed");
		softAssert.assertTrue(passwordErrorMessage.isDisplayed(), "Password error message is not displayed");
		softAssert.assertAll();
	}

	@Test(priority = 9)
	public void testShortPassword() {
		loginPage.navigateToLoginPage();
		loginPage.enterEmail(VALID_EMAIL);
		loginPage.enterPassword("12345");
		loginPage.clickSignIn();

		WebElement passwordErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//mat-error[contains(text(), 'Please Enter Minimum 6 Characters.')]")));

		softAssert.assertTrue(passwordErrorMessage.isDisplayed(), "Short password error message is not displayed");
		softAssert.assertEquals(passwordErrorMessage.getText(), "Please Enter Minimum 6 Characters.",
				"Short password error message text is incorrect");
		softAssert.assertAll();
	}

	@Test(priority = 10)
	public void testForgotPasswordLink() {
		loginPage.navigateToLoginPage();

		WebElement forgotPasswordLink = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Forgot Password?')]")));

		forgotPasswordLink.click();

		String expectedForgotPasswordUrl = "http://20.219.88.214:6102/login/forgot-password";
		wait.until(ExpectedConditions.urlToBe(expectedForgotPasswordUrl));

		softAssert.assertEquals(driver.getCurrentUrl(), expectedForgotPasswordUrl,
				"Failed to navigate to the forgot password page");
		softAssert.assertAll();
	}
}