package org.aepl.tcu4g.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DropdownUtil {

	private WebDriver driver;

	public DropdownUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void selectByVisibleText(By dropdownLocator, String visibleText) {
		WebElement dropdownElement = driver.findElement(dropdownLocator);
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByVisibleText(visibleText);
	}

	public void selectByValue(By dropdownLocator, String value) {
		WebElement dropdownElement = driver.findElement(dropdownLocator);
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByValue(value);
	}

	public void selectByIndex(By dropdownLocator, int index) {
		WebElement dropdownElement = driver.findElement(dropdownLocator);
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByIndex(index);
	}

	public void assertSelectedOption(By dropdownLocator, String expectedText) {
		WebElement dropdownElement = driver.findElement(dropdownLocator);
		Select dropdown = new Select(dropdownElement);
		WebElement selectedOption = dropdown.getFirstSelectedOption();
		Assert.assertEquals(selectedOption.getText(), expectedText, "Selected option does not match expected text.");
	}

	public void assertDropdownOptions(By dropdownLocator, String[] expectedOptions) {
		WebElement dropdownElement = driver.findElement(dropdownLocator);
		Select dropdown = new Select(dropdownElement);
		java.util.List<WebElement> options = dropdown.getOptions();

		Assert.assertEquals(options.size(), expectedOptions.length, "Dropdown options count does not match.");

		for (int i = 0; i < expectedOptions.length; i++) {
			Assert.assertEquals(options.get(i).getText(), expectedOptions[i],
					"Option text does not match at index " + i);
		}
	}
}
