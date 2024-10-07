package org.aepl.tcu4g.tests;

import java.time.Duration;
import java.util.List;

import org.aepl.tcu4g.base.BaseTest;
import org.aepl.tcu4g.pages.LoginPage;
import org.aepl.tcu4g.utils.DataProviderUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TicketPageTest extends BaseTest {
	private LoginPage loginPage;

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

	@Test(priority = 0, enabled = true, dataProvider = "loginData", dataProviderClass = DataProviderUtil.class)
	public void login(String email, String password) {
		loginPage.navigateToLoginPage();
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickSignIn();
	}

	@Test(priority = 1, enabled = true, dependsOnMethods = "login")
	public void testNavigateInstance() {
		String ticketURL= "http://20.219.88.214:6102/my-tickets";
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));

		List<WebElement> profileDropdownElements = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@id='navbarDropdownProfile']")));
		System.out.println("Found Elements: " + profileDropdownElements.size());

		if (profileDropdownElements.size() >= 5) {
			WebElement firstElement = profileDropdownElements.get(1);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstElement);

			try {
				firstElement.click();
			} catch (ElementClickInterceptedException e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstElement);
			}
		} else {
			Assert.fail(
					"Less than " + profileDropdownElements.size() + " elements found with ID 'navbarDropdownProfile'");
		}

		WebElement tag = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("My AIS 140 Ticke")));
		tag.click();

		softAssert.assertEquals(driver.getCurrentUrl(), ticketURL, "Failed to navigate to my ticket page");
		softAssert.assertAll();
	}

//	@Test(priority = 2, dependsOnMethods = "login", enabled = true, dataProvider = "loginData", dataProviderClass = DataProviderUtil.class)
//	public void testClickSearchBox(String email, String password, String ticketUrl) {
//		String Ticket_no = "AEPL-240829-2";
//		String UIN_no = "ACON4NA202200082103";
//		String Chassis_no = "MAT771072NMJ0064701808";
//
//		login(email, password, ticketUrl);
//		waitForPageToLoad();
//
//		wait.until(ExpectedConditions
//				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));
//
//		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//		List<WebElement> profileDropdownElements = wait
//				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@id='navbarDropdownProfile']")));
//		System.out.println("Found Elements: " + profileDropdownElements.size());
//
//		if (profileDropdownElements.size() >= 5) {
//			WebElement firstElement = profileDropdownElements.get(1);
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstElement);
//
//			try {
//				firstElement.click();
//			} catch (ElementClickInterceptedException e) {
//				((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstElement);
//			}
//		} else {
//			Assert.fail(
//					"Less than " + profileDropdownElements.size() + " elements found with ID 'navbarDropdownProfile'");
//		}
//
//		WebElement tag = wait
//				.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("My AIS 140 Ticke")));
//		tag.click();
//
//		boolean isSearchSuccessful = false;
//		WebElement searchBox = wait
//				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-control.my-0.py-1.myform")));
//
//		try {
//			searchBox.clear();
//			searchBox.sendKeys(Ticket_no);
//			searchBox.sendKeys(Keys.ENTER);
//
//			waitForPageToLoad();
//
//			searchBox.clear();
//			searchBox.sendKeys(UIN_no);
//			searchBox.sendKeys(Keys.ENTER);
//
//			waitForPageToLoad();
//
//			searchBox.clear();
//			searchBox.sendKeys(Chassis_no);
//			searchBox.sendKeys(Keys.ENTER);
//
//			waitForPageToLoad();
//
//			searchBox.clear();
//
//			isSearchSuccessful = true;
//		} catch (Exception e) {
//			isSearchSuccessful = false;
//		}
//		softAssert.assertTrue(isSearchSuccessful, "Search box interaction failed");
//		softAssert.assertAll();
//	}
//
//	@Test(priority = 3, dependsOnMethods = "testClickSearchBox", enabled = true, dataProvider = "loginData", dataProviderClass = DataProviderUtil.class)
//	public void testClickViewButton(String email, String password, String ticketUrl) {
//		login(email, password, ticketUrl);
//		waitForPageToLoad();
//
//		wait.until(ExpectedConditions
//				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));
//
//		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//		List<WebElement> profileDropdownElements = wait
//				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@id='navbarDropdownProfile']")));
//		System.out.println("Found Elements: " + profileDropdownElements.size());
//
//		if (profileDropdownElements.size() >= 5) {
//			WebElement firstElement = profileDropdownElements.get(1);
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstElement);
//
//			try {
//				firstElement.click();
//			} catch (ElementClickInterceptedException e) {
//				((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstElement);
//			}
//		} else {
//			Assert.fail(
//					"Less than " + profileDropdownElements.size() + " elements found with ID 'navbarDropdownProfile'");
//		}
//
//		WebElement tag = wait
//				.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("My AIS 140 Ticke")));
//		tag.click();
//
//		WebElement viewButton = wait
//				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='ng-star-inserted'][3]")));
//
//		viewButton.click();
//
//		waitForPageToLoad();
//
//		String currentUrl = driver.getCurrentUrl();
//		softAssert.assertEquals(currentUrl, "http://20.219.88.214:6102/my-ticket-details/66d0463b72ef30c38602223c",
//				"Failed to navigate to the ticket details page");
//
//		softAssert.assertAll();
//	}
//
//	@Test(priority = 4, enabled = true, dataProvider = "loginData", dataProviderClass = DataProviderUtil.class)
//	public void testClickTicketInformation(String email, String password, String ticketUrl) {
//		login(email, password, ticketUrl);
//		waitForPageToLoad();
//
//		wait.until(ExpectedConditions
//				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));
//
//		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//		List<WebElement> profileDropdownElements = wait
//				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@id='navbarDropdownProfile']")));
//		System.out.println("Found Elements: " + profileDropdownElements.size());
//
//		if (profileDropdownElements.size() >= 5) {
//			WebElement firstElement = profileDropdownElements.get(1);
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstElement);
//
//			try {
//				firstElement.click();
//			} catch (ElementClickInterceptedException e) {
//				((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstElement);
//			}
//		} else {
//			Assert.fail(
//					"Less than " + profileDropdownElements.size() + " elements found with ID 'navbarDropdownProfile'");
//		}
//
//		WebElement tag = wait
//				.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("My AIS 140 Ticke")));
//		tag.click();
//
//		List<WebElement> elements = driver
//				.findElements(By.cssSelector(".crm_head_b d-flex text-center justify-content-between"));
//		int indexToClick = 0;
//
//		if (indexToClick >= 0 && indexToClick < elements.size()) {
//			elements.get(indexToClick).click();
//
//			WebElement verificationElement = wait.until(
//					ExpectedConditions.presenceOfElementLocated(By.className("mat-form-field-infix ng-tns-c69-113")));
//			softAssert.assertTrue(verificationElement.isDisplayed(),
//					"The expected element was not displayed after clicking.");
//		} else {
//			softAssert.fail("Index " + indexToClick + " is out of bounds for the elements list.");
//		}
//
//	}
//
//	@Test(priority = 5, enabled = true, dataProvider = "loginData", dataProviderClass = DataProviderUtil.class)
//	public void testTicketInformation(String email, String password, String ticketUrl) {
//		login(email, password, ticketUrl);
//
//		waitForPageToLoad();
//
//		driver.get("http://20.219.88.214:6102/my-ticket-details/66d0463b72ef30c38602223c");
//
//		WebElement dropdown = wait.until(
//				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='crm_head_b d-flex text-center justify-content-between'])[1]")));
//		dropdown.click();
//
//		WebElement ticketNumberElement = driver.findElement(By.id("mat-input-24"));
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].style.border='5px solid Green'", ticketNumberElement);
//
//		String inputValue = ticketNumberElement.getAttribute("value");
//
//		System.out.println("\u001B[1m\u001B[34mTicket Information:\u001B[0m");
//		System.out.println("\u001B[1m\u001B[35mTicket Number:\u001B[0m " + inputValue);
//
//		softAssert.assertNotNull(inputValue, "Ticket Number should not be null or empty");
//		softAssert.assertTrue(!inputValue.isEmpty(), "Ticket Number should not be empty");
//
//		softAssert.assertAll();
//	}

	/*
	 * #############################################################################
	 * ##############################################
	 */

//	@Test(priority = 6)
//	public void testTicketInformationTicketHandler() {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//
//		WebElement TicketHandler = driver.findElement(By.id("mat-select-46"));
//		js.executeScript("arguments[0].style.border='5px solid Green'", TicketHandler);
//
//	}
//
//	@Test(priority = 7)
//	public void testTicketInformationTicketHandlerName() {
//		final String TICKET_HANDLER_DROPDOWN_ID = "mat-select-46";
//		final String SELECTED_OPTION_TEXT = "mat-option-355";
//		final String[] EXPECTED_OPTIONS = { "mat-option-340", "mat-option-341", "mat-option-355" };
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//
//		WebElement ticketHandlerDropdown = driver.findElement(By.id(TICKET_HANDLER_DROPDOWN_ID));
//		js.executeScript("arguments[0].style.border='5px solid Green'", ticketHandlerDropdown);
//
//		ticketHandlerDropdown.click();
//
//		DropdownUtil dropdownUtil = new DropdownUtil(driver);
//		dropdownUtil.assertDropdownOptions(By.id(TICKET_HANDLER_DROPDOWN_ID), EXPECTED_OPTIONS);
//		dropdownUtil.selectByVisibleText(By.id(TICKET_HANDLER_DROPDOWN_ID), SELECTED_OPTION_TEXT);
//		dropdownUtil.assertSelectedOption(By.id(TICKET_HANDLER_DROPDOWN_ID), SELECTED_OPTION_TEXT);
//
//		String result = driver.findElement(By.id(TICKET_HANDLER_DROPDOWN_ID)).getText().equals(SELECTED_OPTION_TEXT)
//				? "PASS"
//				: "FAIL";
//		String testCaseId = TicketTestCaseMapper.getTestCaseId("ticketInformationTicketHandler");
//		ExcelReportUtil.addTestResult(testCaseId, result, "N/A");
//
//		softAssert.assertAll();
//	}
//
//	@Test(priority = 8)
//	public void testTicketInformationTicketCreatedTime() {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		WebElement TicketCreatedTime = driver.findElement(By.id("mat-input-28"));
//		js.executeScript("arguments[0].style.border='5px solid Green'", TicketCreatedTime);
//
//		String inputValue = TicketCreatedTime.getAttribute("value");
//		System.out.println("\u001B[1m\u001B[35mTicket Created Date and Time:\u001B[0m " + inputValue);
//
//		Assert.assertNotNull(inputValue, "Ticket Created Date and Time value should not be null");
//		Assert.assertFalse(inputValue.isEmpty(), "Ticket Created Date and Time value should not be empty");
//
//		String testCaseId = TicketTestCaseMapper.getTestCaseId("ticketInformationTicketCreatedTime");
//		String result = (inputValue != null && !inputValue.isEmpty()) ? "PASS" : "FAIL";
//		ExcelReportUtil.addTestResult(testCaseId, result, "N/A");
//
//		softAssert.assertAll();
//	}
//
//	@Test(priority = 9)
//	public void testTicketInformationTicketAssignedTime() {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		WebElement TicketAssignedTime = driver.findElement(By.id("mat-input-29"));
//		js.executeScript("arguments[0].style.border='5px solid Green'", TicketAssignedTime);
//
//		String inputValue = TicketAssignedTime.getAttribute("value");
//		System.out.println("\u001B[1m\u001B[35mTicket Assigned Date and Time:\u001B[0m " + inputValue);
//
//		Assert.assertNotNull(inputValue, "Ticket Assigned Date and Time value should not be null");
//		Assert.assertFalse(inputValue.isEmpty(), "Ticket Assigned Date and Time value should not be empty");
//
//		String testCaseId = TicketTestCaseMapper.getTestCaseId("ticketInformationTicketAssignedTime");
//		String result = (inputValue != null && !inputValue.isEmpty()) ? "PASS" : "FAIL";
//		ExcelReportUtil.addTestResult(testCaseId, result, "N/A");
//
//		softAssert.assertAll();
//	}
//
//	@Test(priority = 10)
//	public void testTicketInformationTicketCanceledCompletedTime() {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		WebElement TicketCancelCompletedTime = driver.findElement(By.id("mat-input-30"));
//		js.executeScript("arguments[0].style.border='5px solid Green'", TicketCancelCompletedTime);
//
//		String inputValue = TicketCancelCompletedTime.getAttribute("value");
//		System.out.println("\u001B[1m\u001B[35mTicket Cancel/Completed Date and Time:\u001B[0m " + inputValue);
//
//		Assert.assertNotNull(inputValue, "Ticket Cancel/Completed Date and Time value should not be null");
//		Assert.assertFalse(inputValue.isEmpty(), "Ticket Cancel/Completed Date and Time value should not be empty");
//
//		String testCaseId = TicketTestCaseMapper.getTestCaseId("ticketInformationTicketCanceledCompletedTime");
//		String result = (inputValue != null && !inputValue.isEmpty()) ? "PASS" : "FAIL";
//		ExcelReportUtil.addTestResult(testCaseId, result, "N/A");
//
//		softAssert.assertAll();
//	}
//
//	@Test(priority = 11)
//	public void testTicketInformationCertificateDurationInYear() {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		WebElement CertificateDuration = driver.findElement(By.id("mat-input-40"));
//		js.executeScript("arguments[0].style.border='5px solid Green'", CertificateDuration);
//		String inputValue = CertificateDuration.getAttribute("value");
//		System.out.println("\u001B[1m\u001B[35mCertificate Duration in Year:\u001B[0m " + inputValue);
//
//		Assert.assertNotNull(inputValue, "Certificate Duration in Year value should not be null");
//		Assert.assertFalse(inputValue.isEmpty(), "Certificate Duration in Year value should not be empty");
//
//		String testCaseId = TicketTestCaseMapper.getTestCaseId("ticketInformationCertificateDurationInYear");
//		String result = (inputValue != null && !inputValue.isEmpty()) ? "PASS" : "FAIL";
//		ExcelReportUtil.addTestResult(testCaseId, result, "N/A");
//
//		softAssert.assertAll();
//	}
//
//	@Test(priority = 12)
//	public void testTicketInformationOverallTicketStatus() {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		WebElement TicketStatus = driver.findElement(By.id("mat-input-27"));
//		js.executeScript("arguments[0].style.border='5px solid Green'", TicketStatus);
//		String inputValue = TicketStatus.getAttribute("value");
//		System.out.println("\u001B[1m\u001B[35mOverall Ticket Status:\u001B[0m " + inputValue);
//
//		Assert.assertNotNull(inputValue, "Overall Ticket Status value should not be null");
//		Assert.assertFalse(inputValue.isEmpty(), "Overall Ticket Status value should not be empty");
//
//		String testCaseId = TicketTestCaseMapper.getTestCaseId("ticketInformationOverallTicketStatus");
//		String result = (inputValue != null && !inputValue.isEmpty()) ? "PASS" : "FAIL";
//		ExcelReportUtil.addTestResult(testCaseId, result, "N/A");
//
//		softAssert.assertAll();
//	}
//
//	@Test(priority = 13)
//	public void testTicketInformationCurrentTicketRemark() {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		WebElement TicketRemark = driver.findElement(By.id("mat-input-33"));
//		js.executeScript("arguments[0].style.border='5px solid Green'", TicketRemark);
//		String inputValue = TicketRemark.getAttribute("value");
//		System.out.println("\u001B[1m\u001B[35mStage Wise Current Ticket Remark:\u001B[0m " + inputValue);
//
//		Assert.assertNotNull(inputValue, "Stage Wise Current Ticket Remark value should not be null");
//		Assert.assertFalse(inputValue.isEmpty(), "Stage Wise Current Ticket Remark value should not be empty");
//
//		String testCaseId = TicketTestCaseMapper.getTestCaseId("ticketInformationCurrentTicketRemark");
//		String result = (inputValue != null && !inputValue.isEmpty()) ? "PASS" : "FAIL";
//		ExcelReportUtil.addTestResult(testCaseId, result, "N/A");
//
//		softAssert.assertAll();
//	}
//
//	@Test(priority = 14)
//	public void testTicketInformationTicketGeneratedBy() {
//	}
//
//	@Test(priority = 15)
//	public void testTicketInformationTicketDescription() {
//	}
//
//	@Test(priority = 16)
//	public void testClickDeviceInformation() {
//	}
//
//	@Test(priority = 17)
//	public void testDeviceInformationUINNumber() {
//	}
//
//	@Test(priority = 18)
//	public void testDeviceInformationIMEINumber() {
//	}
//
//	@Test(priority = 19)
//	public void testDeviceInformationICCIDNumber() {
//	}
//
//	@Test(priority = 20)
//	public void testDeviceInformationModelName() {
//	}
//
//	@Test(priority = 21)
//	public void testDeviceInformationDeviceMake() {
//	}
//
//	@Test(priority = 22)
//	public void testDeviceInformationPrimaryOperatorName() {
//	}
//
//	@Test(priority = 23)
//	public void testDeviceInformationPrimaryOperatorNumber() {
//	}
//
//	@Test(priority = 24)
//	public void testDeviceInformationSecondaryOperatorName() {
//	}
//
//	@Test(priority = 25)
//	public void testDeviceInformationSecondaryOperatorNumber() {
//	}
//
//	@Test(priority = 26)
//	public void testClickVehicleOwnerInformation() {
//	}
//
//	@Test(priority = 27)
//	public void testVehicleOwnerInformationVehicleOwnerName() {
//	}
//
//	@Test(priority = 28)
//	public void testVehicleOwnerInformationOwnerMobileNumber() {
//	}
//
//	@Test(priority = 29)
//	public void testVehicleOwnerInformationPOADocType() {
//	}
//
//	@Test(priority = 30)
//	public void testVehicleOwnerInformationPOADocNumber() {
//	}
//
//	@Test(priority = 31)
//	public void testVehicleOwnerInformationPOIDocType() {
//	}
//
//	@Test(priority = 32)
//	public void testVehicleOwnerInformationPOIDocNumber() {
//	}
//
//	@Test(priority = 33)
//	public void testVehicleOwnerInformationAddress() {
//	}
//
//	@Test(priority = 34)
//	public void testClickVehicleInformation() {
//	}
//
//	@Test(priority = 35)
//	public void testVehicleInformationVehicleModel() {
//	}
//
//	@Test(priority = 36)
//	public void testVehicleInformationVehicleMake() {
//	}
//
//	@Test(priority = 37)
//	public void testVehicleInformationManufacturingYear() {
//	}
//
//	@Test(priority = 38)
//	public void testVehicleInformationChassisNumber() {
//	}
//
//	@Test(priority = 39)
//	public void testVehicleInformationEngineNumber() {
//	}
//
//	@Test(priority = 40)
//	public void testVehicleInformationRegistrationNumber() {
//	}
//
//	@Test(priority = 41)
//	public void testVehicleInformationInvoiceDate() {
//	}
//
//	@Test(priority = 42)
//	public void testVehicleInformationInvoiceNumber() {
//	}
//
//	@Test(priority = 43)
//	public void testVehicleInformationRTOState() {
//	}
//
//	@Test(priority = 44)
//	public void testVehicleInformationRTOCode() {
//	}
//
//	@Test(priority = 45)
//	public void testVehicleInformationReloadButton() {
//	}
//
//	@Test(priority = 46)
//	public void testVehicleInformationIgnitionStatus() {
//	}
//
//	@Test(priority = 47)
//	public void testClickDealerInformation() {
//	}
//
//	@Test(priority = 48)
//	public void testVehicleInformationDealerCode() {
//	}
//
//	@Test(priority = 49)
//	public void testVehicleInformationDealerEmail() {
//	}
//
//	@Test(priority = 50)
//	public void testVehicleInformationDealerCity() {
//	}
//
//	@Test(priority = 51)
//	public void testVehicleInformationDealerPhoneNumber() {
//	}
//
//	@Test(priority = 52)
//	public void testVehicleInformationPOSName() {
//	}
//
//	@Test(priority = 53)
//	public void testVehicleInformationPOSCode() {
//	}
//
//	@Test(priority = 54)
//	public void testClickDeviceFOTAStatus() {
//	}
//
//	@Test(priority = 55)
//	public void testDeviceFOTAStatusBatchID() {
//	}
//
//	@Test(priority = 56)
//	public void testDeviceFOTAStatusCurrentfirmware() {
//	}
//
//	@Test(priority = 57)
//	public void testDeviceFOTAStatusAssignedFirmwareVersion() {
//	}
//
//	@Test(priority = 58)
//	public void testDeviceFOTAStatusFOTAStatus() {
//	}
//
//	@Test(priority = 59)
//	public void testDeviceFOTAStatusFotaProgress() {
//	}
//
//	@Test(priority = 60)
//	public void testDeviceFOTAStatusOTAPrimaryIP() {
//	}
//
//	@Test(priority = 61)
//	public void testDeviceFOTAStatusOTAPrimaryIPStatus() {
//	}
//
//	@Test(priority = 62)
//	public void testDeviceFOTAStatusOTASecondaryIP() {
//	}
//
//	@Test(priority = 63)
//	public void testDeviceFOTAStatusOTASecondaryIPStatus() {
//	}
//
//	@Test(priority = 64)
//	public void testDeviceFOTAStatusStateEnableOTAStatus() {
//	}
//
//	@Test(priority = 65)
//	public void testDeviceFOTAStatusRemoveStage2Restriction() {
//	}
//
//	@Test(priority = 66)
//	public void testDeviceFOTAStatusRemoveStage2RestrictionReason() {
//	}
//
//	@Test(priority = 67)
//	public void testDeviceFOTAStatusStage2SkipRemark() {
//	}
//
//	@Test(priority = 68)
//	public void testDeviceFOTAStatusSkippedBy() {
//	}
//
//	@Test(priority = 69)
//	public void testDeviceTicketStatusDealerRequestVehicleIgnitionOn() {
//	}
//
//	@Test(priority = 70)
//	public void testDeviceTicketStatusOverallTicketStatus() {
//	}
//
//	@Test(priority = 71)
//	public void testDeviceTicketStatusGSMNetworkAvailability() {
//	}
//
//	@Test(priority = 72)
//	public void testDeviceTicketStatusGPSFixavailability() {
//	}
//
//	@Test(priority = 73)
//	public void testDeviceTicketStatusVahanPortalAvailability() {
//	}
//
//	@Test(priority = 74)
//	public void testDeviceTicketStatusVehicleDataavailabilityonVahan() {
//	}
//
//	@Test(priority = 75)
//	public void testDeviceTicketStatusBackendPortalAvailability() {
//	}
//
//	@Test(priority = 76)
//	public void testDeviceTicketStatusPanicEventConfirmation() {
//	}
//
//	@Test(priority = 77)
//	public void testDeviceTicketStatusOTPLiveLocationFetching() {
//	}
//
//	@Test(priority = 78)
//	public void testDeviceTicketStatusCertificateGenerationSubmission() {
//	}
//
//	@Test(priority = 79)
//	public void testGenareateCertificateDate() {
//	}
//
//	@Test(priority = 80)
//	public void testDeviceTicketStatusSelectCertificateValidity() {
//	}
//
//	@Test(priority = 81)
//	public void testCertificatExpiryeDate() {
//	}
//
//	@Test(priority = 82)
//	public void testClickOnUploadVehicleCertificate() {
//	}
//
//	@Test(priority = 83)
//	public void testClickOnUploadBackendCertificate() {
//	}
//
//	@Test(priority = 84)
//	public void testDeviceTicketStatusOverallTicketStatus1() {
//	}

	public void waitForPageToLoad() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}
}
