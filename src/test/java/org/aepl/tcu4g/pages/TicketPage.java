package org.aepl.tcu4g.pages;

import org.aepl.tcu4g.utils.ExcelReportUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TicketPage {
	private final WebDriver driver;
	private WebDriverWait wait;

	public TicketPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public static void setExcelSheet() {
		ExcelReportUtil.switchToSheet("My_AIS140_Ticket");
	}

	public void navigateToLoginPage() {
		String BASE_URL = "http://20.219.88.214:6102/login";
		driver.get(BASE_URL);
	}
	public void navigateToTicketPage() {
		String TICK_URL = "http://20.219.88.214:6102/my-tickets";
		driver.get(TICK_URL);
	}

	// Methods to be implemented....
	public void navigateInstance() {
	}

	public void clickSearchBox() {
	}

	public void clickViewButton() {
	}

	public void clickTicketInformation() {
	}

	public void ticketInformation() {
	}

	public void ticketInformationTicketHandler() {
	}

	public void ticketInformationTicketHandlerName() {
	}

	public void ticketInformationTicketCreatedTime() {
	}

	public void ticketInformationTicketAssignedTime() {
	}

	public void ticketInformationTicketCanceledCompletedTime() {
	}

	public void ticketInformationCeritificateDurationInYear() {
	}

	public void ticketInformationOverallTicketStatus() {
	}

	public void ticketInformationCurrentTicketRemark() {
	}

	public void ticketInformationTicketGeneratedBy() {
	}

	public void ticketInformationTicketDescription() {
	}

	public void clickDeviceInformation() {
	}

	public void deviceInformationUINNumber() {
	}

	public void deviceInformationIMEINumber() {
	}

	public void deviceInformationICCIDNumber() {
	}

	public void deviceInformationModelName() {
	}

	public void deviceInformationDeviceMake() {
	}

	public void deviceInformationPrimaryOperatorName() {
	}

	public void deviceInformationPrimaryOperatorNumber() {
	}

	public void deviceInformationSecondaryOperatorName() {
	}

	public void deviceInformationSecondaryOperatorNumber() {
	}

	public void clickVehicleOwnerInformation() {
	}

	public void vehicleOwnerInformationVehicleOwnerName() {
	}

	public void vehicleOwnerInformationOwnerMobileNumber() {
	}

	public void vehicleOwnerInformationPOADocType() {
	}

	public void vehicleOwnerInformationPOADocNumber() {
	}

	public void vehicleOwnerInformationPOIDocType() {
	}

	public void vehicleOwnerInformationPOIDocNumber() {
	}

	public void vehicleOwnerInformationAddress() {
	}

	public void clickVehicleInformation() {
	}

	public void vehicleInformationVehicleModel() {
	}

	public void vehicleInformationVehicleMake() {
	}

	public void vehicleInformationManufacturingYear() {
	}

	public void vehicleInformationChassisNumber() {
	}

	public void vehicleInformationEngineNumber() {
	}

	public void vehicleInformationRegistrationNumber() {
	}

	public void vehicleInformationInvoiceDate() {
	}

	public void vehicleInformationInvoiceNumber() {
	}

	public void vehicleInformationRTOState() {
	}

	public void vehicleInformationRTOCode() {
	}

	public void vehicleInformationReloadButton() {
	}

	public void vehicleInformationIgnitionStatus() {
	}

	public void clickDealerInformation() {
	}

	public void vehicleInformationDealerCode() {
	}

	public void vehicleInformationDealerEmail() {
	}

	public void vehicleInformationDealerCity() {
	}

	public void vehicleInformationDealerPhoneNumber() {
	}

	public void vehicleInformationPOSName() {
	}

	public void vehicleInformationPOSCode() {
	}

	public void clickDeviceFOTAStatus() {
	}

	public void deviceFOTAStatusBatchID() {
	}

	public void deviceFOTAStatusCurrentfirmware() {
	}

	public void deviceFOTAStatusAssignedFirmwareVersion() {
	}

	public void deviceFOTAStatusFOTAStatus() {
	}

	public void deviceFOTAStatusFotaProgress() {
	}

	public void deviceFOTAStatusOTAPrimaryIP() {
	}

	public void deviceFOTAStatusOTAPrimaryIPStatus() {
	}

	public void deviceFOTAStatusOTASecondaryIP() {
	}

	public void deviceFOTAStatusOTASecondaryIPStatus() {
	}

	public void deviceFOTAStatusStateEnableOTAStatus() {
	}

	public void deviceFOTAStatusRemoveStage2Restriction() {
	}

	public void deviceFOTAStatusRemoveStage2RestrictionReason() {
	}

	public void deviceFOTAStatusStage2SkipRemark() {
	}

	public void deviceFOTAStatusSkippedBy() {
	}

	public void deviceTicketStatusDealerRequestVehicleIgnitionOn() {
	}

	public void deviceTicketStatusOverallTicketStatus() {
	}

	public void deviceTicketStatusGSMNetworkAvailability() {
	}

	public void deviceTicketStatusGPSFixavailability() {
	}

	public void deviceTicketStatusVahanPortalAvailability() {
	}

	public void deviceTicketStatusVehicleDataavailabilityonVahan() {
	}

	public void deviceTicketStatusBackendPortalAvailability() {
	}

	public void deviceTicketStatusPanicEventConfirmation() {
	}

	public void deviceTicketStatusOTPLiveLocationFetching() {
	}

	public void deviceTicketStatusCertificateGenerationSubmission() {
	}

	public void genareateCertificateDate() {
	}

	public void deviceTicketStatusSelectCertificateValidity() {
	}

	public void certificatExpiryeDate() {
	}

	public void clickOnUploadVehicleCertificate() {
	}

	public void clickOnUploadBackendCertificate() {
	}

	public void deviceTicketStatusOverallTicketStatus1() {
	}

}
