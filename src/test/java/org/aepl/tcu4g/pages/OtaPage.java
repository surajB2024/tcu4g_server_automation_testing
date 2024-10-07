package org.aepl.tcu4g.pages;

import org.aepl.tcu4g.utils.ExcelReportUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OtaPage {
	private final WebDriver driver;
	private WebDriverWait wait;

	public OtaPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public WebDriverWait getWait() {
		return wait;
	}

	public void setWait(WebDriverWait wait) {
		this.wait = wait;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public static void setExcelSheet() {
		ExcelReportUtil.switchToSheet("OTA");
	}
}
