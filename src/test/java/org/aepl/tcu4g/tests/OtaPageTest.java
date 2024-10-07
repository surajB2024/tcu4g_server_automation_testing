package org.aepl.tcu4g.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.aepl.tcu4g.base.BaseTest;
import org.aepl.tcu4g.pages.LoginPage;
import org.aepl.tcu4g.pages.OtaPage;
import org.aepl.tcu4g.utils.DataProviderUtil;
import org.aepl.tcu4g.utils.DropdownUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OtaPageTest extends BaseTest {
	private LoginPage loginPage;
	public OtaPage otaPage;
	private DropdownUtil dropUtil;
	Actions action;

	@BeforeClass
	public void setUpClass() {
		loginPage = new LoginPage(driver, wait);
		otaPage = new OtaPage(driver, wait);
		dropUtil = new DropdownUtil(driver);
		action = new Actions(driver);
	}

	@Test(priority = 1, enabled = true, dataProvider = "loginData", dataProviderClass = DataProviderUtil.class)
	public void login(String email, String pass) {
		loginPage.navigateToLoginPage();
		loginPage.enterEmail(email);
		loginPage.enterPassword(pass);
		loginPage.clickSignIn();
		softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true)
	public void navigateToOtaAndClick() {
		loginPage.enterEmail("suraj.bhalerao@accoladeelectronics.com");
		loginPage.enterPassword("cqf9tnvl");
		loginPage.clickSignIn();

		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));

		List<WebElement> profileDropdownElements = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@id='navbarDropdownProfile']")));
		System.out.println("Found Elements: " + profileDropdownElements.size()); // Debug line

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

		WebElement tag = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("OTA")));
		tag.click();
		softAssert.assertAll();
	}

	@Test(priority = 3, enabled = true)
	public void clickCreateNewBatchBtn() {
		navigateToOtaAndClick();
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));

		List<WebElement> btn = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("btn-outline-primary")));

		if (!btn.isEmpty()) {
			try {
				btn.get(1).click();
				System.out.println("Clicked on 'Create New Batch' button."); // Debug Line
			} catch (ElementClickInterceptedException e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn.get(0));
				System.out.println("Clicked on 'Create New Batch' button using JavaScript.");
			}
		} else {
			Assert.fail("No 'Create New Batch' button found on the page.");
		}
		softAssert.assertAll();
	}

	@Test(priority = 4, enabled = true)
	public void checkBulkOtaBatchDetails() {
		clickCreateNewBatchBtn();

		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));

		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-0")));
		input.sendKeys("Demo_Batch");

		WebElement input1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-1")));
		input1.sendKeys("Demo_Batch_Details....");

		List<WebElement> selectValue = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("example-full-width")));
		selectValue.get(2).click();
		softAssert.assertAll();
	}

	@Test(priority = 5, enabled = true)
	public void clickOnChooseCSVBtn() {
		checkBulkOtaBatchDetails();

		List<WebElement> listOfElements = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("mat-option-text")));

		if (listOfElements.size() >= 3) {
			WebElement thirdOption = listOfElements.get(2);
			thirdOption.click();
		} else {
			Assert.fail("Less than 3 options found in the dropdown.");
		}

		String filePath = "C:\\Users\\Suraj Bhaleroa\\Downloads\\demo_csv.csv";

		WebElement uploadCsv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("csv")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", uploadCsv);

		if (uploadCsv.isDisplayed() && uploadCsv.isEnabled()) {
			try {
				uploadCsv.sendKeys(filePath);
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", uploadCsv);
				uploadCsv.sendKeys(filePath);
			}
		} else {
			Assert.fail("File upload button is not visible or not enabled.");
		}
		softAssert.assertAll();
	}

	@Test(priority = 6, enabled = true)
	public void testDownloadBtn() throws InterruptedException {
		clickOnChooseCSVBtn();

		WebElement downBtn = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@class='btn btn-outline-primary btn-sm example-full-width float-lg-right']")));
		Thread.sleep(1000);
		downBtn.click();

		String downloadDirectory = "C:\\Users\\Suraj Bhaleroa\\Downloads";
		String fileName = "SampleOTATemplate.csv";

		File downloadedFile = new File(downloadDirectory + File.separator + fileName);
		long timeoutInSeconds = 30;
		long endTime = System.currentTimeMillis() + timeoutInSeconds * 1000;
		boolean fileExists = false;

		while (System.currentTimeMillis() < endTime) {
			if (downloadedFile.exists()) {
				fileExists = true;
				break;
			}
			Thread.sleep(1000);
		}

		Assert.assertTrue(fileExists, "File not downloaded.");

		long fileSize = 0;
		try {
			fileSize = Files.size(Paths.get(downloadedFile.getAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertTrue(fileSize > 0, "Downloaded file is empty.");
	}

	@Test(priority = 7, enabled = true)
	public void checkSetConfigParams() throws InterruptedException {
		testDownloadBtn();
		List<WebElement> checkBoxes = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("form-check-input")));
		for (WebElement checkBox : checkBoxes) {
			if (checkBox.isDisplayed() && checkBox.isEnabled()) {
				scrollToElement(checkBox);
				if (checkBox.isSelected()) {
					checkBox.click();
					highlightElement(checkBox, "red");
				}
				softAssert.assertFalse(checkBox.isSelected(), "Checkbox should be unselected.");
			}
		}
		if (checkBoxes.size() >= 2) {
			WebElement firstCheckbox = checkBoxes.get(0);
			WebElement secondCheckbox = checkBoxes.get(1);

			selectCheckbox(firstCheckbox);
			selectCheckbox(secondCheckbox);
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("overlay")));

		WebElement btn = driver.findElement(By.xpath("//button[@class='btn btn-outline-primary ng-star-inserted']"));
		btn.click();

		softAssert.assertAll();
	}

	@Test(priority = 8, enabled = true)
	public void setConfigurationValue() throws InterruptedException {
		checkSetConfigParams();

		WebElement input = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[contains(@class, 'form-control') and @type='text']")));

		scrollToElement(input);
		input.sendKeys("100");

		input.sendKeys(Keys.TAB);
		Thread.sleep(500);

		WebElement input2 = driver.switchTo().activeElement();
		input2.sendKeys("1");
		Thread.sleep(1000);

		input2.sendKeys(Keys.TAB);
		Thread.sleep(500);

		WebElement subBatchBtn = driver.switchTo().activeElement();

		highlightElement(subBatchBtn, "Dark Green");
		subBatchBtn.click();

		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();

		wait.until(ExpectedConditions.urlContains("http://20.219.88.214:6102/device-batch"));

		softAssert.assertAll();
	}

	@Test(priority = 9, enabled = true)
	public void checkActionButtonClickable() throws InterruptedException {
		navigateToOtaAndClick();

		WebElement search = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[contains(@class, 'form-control') and @type='text']")));
		search.sendKeys("400");
		search.sendKeys(Keys.ENTER);

		Thread.sleep(1000);

		search.sendKeys(Keys.CLEAR);
		Thread.sleep(1000);
		search.sendKeys(Keys.ENTER);

	}

	@Test(priority = 10, enabled = true)
	public void checkManualOtaBatch() throws InterruptedException {
		clickCreateNewBatchBtn();

		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));

		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-0")));
		input.sendKeys("Demo_Batch");

		WebElement input1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-1")));
		input1.sendKeys("Demo_Batch_Details....");

		List<WebElement> selectValue = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("example-full-width")));
		selectValue.get(2).click();

		List<WebElement> dropList = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("mat-option-text")));
		dropList.get(1).click();

		softAssert.assertAll();
	}

	@Test(priority = 11, enabled = true)
	public void checkSearchBoxAndCheckBox() throws InterruptedException {
		checkManualOtaBatch();
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));

		WebElement search = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[contains(@class, 'form-control') and @type='text']")));

		scrollToElement(search);
		highlightElement(search, "Green");

		search.sendKeys("ACON4IA012100132003"); // UIN
		search.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));

		search.clear();
		search.sendKeys(Keys.ENTER);

		WebElement chechBox = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("form-check-input")));
		chechBox.click();
//		List<WebElement> checkBoxes = wait
//				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("form-check-input")));
//		for (WebElement checkBox : checkBoxes) {
//			if (checkBox.isDisplayed() && checkBox.isEnabled()) {
//				scrollToElement(checkBox);
//				if (checkBoxes.size() <= 10) {
//					WebElement firstCheckbox = checkBoxes.get(1);
//					WebElement secondCheckbox = checkBoxes.get(5);
//
//					highlightElement(search, "Violet");
//					highlightElement(secondCheckbox, "Red");
//
//					selectCheckbox(firstCheckbox);
//					selectCheckbox(secondCheckbox);
//				}
//				softAssert.assertFalse(checkBox.isSelected(), "Checkbox should be unselected.");
//			}
//		}
		softAssert.assertAll();
	}

	@Test(priority = 12, enabled = true)
	public void checkSetParams() throws InterruptedException {
		checkSearchBoxAndCheckBox();
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));
		List<WebElement> checkBoxes = wait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@class='form-check-input']")));
		for (WebElement checkBox : checkBoxes) {
			if (checkBox.isDisplayed() && checkBox.isEnabled()) {
				scrollToElement(checkBox);
				if (checkBox.isSelected()) {
					checkBox.click();
					highlightElement(checkBox, "red");
				}
				softAssert.assertFalse(checkBox.isSelected(), "Checkbox should be unselected.");
			}
		}
		if (checkBoxes.size() >= 2) {
			WebElement firstCheckbox = checkBoxes.get(1);
			WebElement secondCheckbox = checkBoxes.get(2);

			selectCheckbox(firstCheckbox);
			selectCheckbox(secondCheckbox);
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("overlay")));

		WebElement btn = driver.findElement(By.xpath("//button[@class='btn btn-outline-primary ng-star-inserted']"));
		highlightElement(btn, "Orange");
		btn.click();

		softAssert.assertAll();
	}

	@Test(priority = 13, enabled = true)
	public void setConfigValues() throws InterruptedException {
		checkSetParams();

		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));

		List<WebElement> inputs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By
				.xpath("//input[contains(@class, 'form-control') and @type='text' and @aria-label='Parameter name']")));
		inputs.forEach(n -> n.sendKeys("1"));
		Thread.sleep(1000);

		// To erase that item
		List<WebElement> erase = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//i[@class='fas fa-eraser mx-3']")));
		erase.get(0).click();
		Thread.sleep(1000);

		// To delete that item
		List<WebElement> delete = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//i[@class='fas fa-trash mx-3']")));
		delete.get(0).click();
		
		Thread.sleep(1000);

		WebElement subBatchBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-outline-primary float-right']")));
		highlightElement(subBatchBtn, "Dark Green");
		subBatchBtn.click();

		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();

		wait.until(ExpectedConditions.urlContains("http://20.219.88.214:6102/device-batch"));

		softAssert.assertAll();
	}

	@Test(priority = 14, enabled = true)
	public void checkOtaMaster() throws InterruptedException{
		navigateToOtaAndClick();
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(".overlay.ng-tns-c14-0.ng-trigger.ng-trigger-fadeIn")));

		List<WebElement> btn = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("btn-outline-primary")));

		if (!btn.isEmpty()) {
			try {
				btn.get(0).click();
			} catch (ElementClickInterceptedException e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn.get(0));
			}
		} else {
			Assert.fail("No 'OTA Master' button found on the page.");
		}
		softAssert.assertAll();
	}
	// Helper Functions Goes Here....
	private void selectCheckbox(WebElement checkBox) throws InterruptedException {
		if (!checkBox.isSelected()) {
			checkBox.click();
			highlightElement(checkBox, "green");
			Thread.sleep(500);
		}
		softAssert.assertTrue(checkBox.isSelected(), "Checkbox should be selected.");
	}

	private void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	private void highlightElement(WebElement element, String color) {
		String script = "arguments[0].style.border='5px solid " + color + "'";
		((JavascriptExecutor) driver).executeScript(script, element);
	}

}
