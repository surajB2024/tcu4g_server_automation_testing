package org.aepl.tcu4g.mappers;

import java.util.HashMap;
import java.util.Map;

public class TicketTestCaseMapper {
	private static final Map<String, String> ticketTestCaseMap = new HashMap<>();

	static {
		ticketTestCaseMap.put("navigateInstance", "TC_MAT_01");
		ticketTestCaseMap.put("clickSearchBox", "TC_MAT_02");
		ticketTestCaseMap.put("clickViewButton", "TC_MAT_03");
		ticketTestCaseMap.put("clickTicketInformation", "TC_MAT_04");
		ticketTestCaseMap.put("ticketInformation", "TC_MAT_05");
		ticketTestCaseMap.put("ticketInformationTicketHandler", "TC_MAT_06");
		ticketTestCaseMap.put("ticketInformationTicketHandlerName", "TC_MAT_07");
		ticketTestCaseMap.put("ticketInformationTicketCreatedTime", "TC_MAT_08");
		ticketTestCaseMap.put("ticketInformationTicketAssignedTime", "TC_MAT_09");
		ticketTestCaseMap.put("ticketInformationTicketCanceledCompletedTime", "TC_MAT_10");
		ticketTestCaseMap.put("ticketInformationCeritificateDurationInYear", "TC_MAT_11");
		ticketTestCaseMap.put("ticketInformationOverallTicketStatus", "TC_MAT_12");
		ticketTestCaseMap.put("ticketInformationCurrentTicketRemark", "TC_MAT_13");
		ticketTestCaseMap.put("ticketInformationTicketGeneratedBy", "TC_MAT_14");
		ticketTestCaseMap.put("ticketInformationTicketDescription", "TC_MAT_15");
		ticketTestCaseMap.put("clickDeviceInformation", "TC_MAT_16");
		ticketTestCaseMap.put("deviceInformationUINNumber", "TC_MAT_17");
		ticketTestCaseMap.put("deviceInformationIMEINumber", "TC_MAT_18");
		ticketTestCaseMap.put("deviceInformationICCIDNumber", "TC_MAT_19");
		ticketTestCaseMap.put("deviceInformationModelName", "TC_MAT_20");
		ticketTestCaseMap.put("deviceInformationDeviceMake", "TC_MAT_21");
		ticketTestCaseMap.put("deviceInformationPrimaryOperatorName", "TC_MAT_22");
		ticketTestCaseMap.put("deviceInformationPrimaryOperatorNumber", "TC_MAT_23");
		ticketTestCaseMap.put("deviceInformationSecondaryOperatorName", "TC_MAT_24");
		ticketTestCaseMap.put("deviceInformationSecondaryOperatorNumber", "TC_MAT_25");
		ticketTestCaseMap.put("clickVehicleOwnerInformation", "TC_MAT_26");
		ticketTestCaseMap.put("vehicleOwnerInformationVehicleOwnerName", "TC_MAT_27");
		ticketTestCaseMap.put("vehicleOwnerInformationOwnerMobileNumber", "TC_MAT_28");
		ticketTestCaseMap.put("vehicleOwnerInformationPOADocType", "TC_MAT_29");
		ticketTestCaseMap.put("vehicleOwnerInformationPOADocNumber", "TC_MAT_30");
		ticketTestCaseMap.put("vehicleOwnerInformationPOIDocType", "TC_MAT_31");
		ticketTestCaseMap.put("vehicleOwnerInformationPOIDocNumber", "TC_MAT_32");
		ticketTestCaseMap.put("vehicleOwnerInformationAddress", "TC_MAT_33");
		ticketTestCaseMap.put("clickVehicleInformation", "TC_MAT_34");
		ticketTestCaseMap.put("vehicleInformationVehicleModel", "TC_MAT_35");
		ticketTestCaseMap.put("vehicleInformationVehicleMake", "TC_MAT_36");
		ticketTestCaseMap.put("vehicleInformationManufacturingYear", "TC_MAT_37");
		ticketTestCaseMap.put("vehicleInformationChassisNumber", "TC_MAT_38");
		ticketTestCaseMap.put("vehicleInformationEngineNumber", "TC_MAT_39");
		ticketTestCaseMap.put("vehicleInformationRegistrationNumber", "TC_MAT_40");
		ticketTestCaseMap.put("vehicleInformationInvoiceDate", "TC_MAT_41");
		ticketTestCaseMap.put("vehicleInformationInvoiceNumber", "TC_MAT_42");
		ticketTestCaseMap.put("vehicleInformationRTOState", "TC_MAT_43");
		ticketTestCaseMap.put("vehicleInformationRTOCode", "TC_MAT_44");
		ticketTestCaseMap.put("vehicleInformationReloadButton", "TC_MAT_45");
		ticketTestCaseMap.put("vehicleInformationIgnitionStatus", "TC_MAT_46");
		ticketTestCaseMap.put("clickDealerInformation", "TC_MAT_47");
		ticketTestCaseMap.put("vehicleInformationDealerCode", "TC_MAT_48");
		ticketTestCaseMap.put("vehicleInformationDealerEmail", "TC_MAT_49");
		ticketTestCaseMap.put("vehicleInformationDealerCity", "TC_MAT_50");
		ticketTestCaseMap.put("vehicleInformationDealerPhoneNumber", "TC_MAT_51");
		ticketTestCaseMap.put("vehicleInformationPOSName", "TC_MAT_52");
		ticketTestCaseMap.put("vehicleInformationPOSCode", "TC_MAT_53");
		ticketTestCaseMap.put("clickDeviceFOTAStatus", "TC_MAT_54");
		ticketTestCaseMap.put("deviceFOTAStatusBatchID", "TC_MAT_55");
		ticketTestCaseMap.put("deviceFOTAStatusCurrentfirmware", "TC_MAT_56");
		ticketTestCaseMap.put("deviceFOTAStatusAssignedFirmwareVersion", "TC_MAT_57");
		ticketTestCaseMap.put("deviceFOTAStatusFOTAStatus", "TC_MAT_58");
		ticketTestCaseMap.put("deviceFOTAStatusFotaProgress", "TC_MAT_59");
		ticketTestCaseMap.put("deviceFOTAStatusOTAPrimaryIP", "TC_MAT_60");
		ticketTestCaseMap.put("deviceFOTAStatusOTAPrimaryIPStatus", "TC_MAT_61");
		ticketTestCaseMap.put("deviceFOTAStatusOTASecondaryIP", "TC_MAT_62");
		ticketTestCaseMap.put("deviceFOTAStatusOTASecondaryIPStatus", "TC_MAT_63");
		ticketTestCaseMap.put("deviceFOTAStatusStateEnableOTAStatus", "TC_MAT_64");
		ticketTestCaseMap.put("deviceFOTAStatusRemoveStage2Restriction", "TC_MAT_65");
		ticketTestCaseMap.put("deviceFOTAStatusRemoveStage2RestrictionReason", "TC_MAT_66");
		ticketTestCaseMap.put("deviceFOTAStatusStage2SkipRemark", "TC_MAT_67");
		ticketTestCaseMap.put("deviceFOTAStatusSkippedBy", "TC_MAT_68");
		ticketTestCaseMap.put("deviceTicketStatusDealerRequestVehicleIgnitionOn", "TC_MAT_69");
		ticketTestCaseMap.put("deviceTicketStatusOverallTicketStatus", "TC_MAT_70");
		ticketTestCaseMap.put("deviceTicketStatusGSMNetworkAvailability", "TC_MAT_71");
		ticketTestCaseMap.put("deviceTicketStatusGPSFixavailability", "TC_MAT_72");
		ticketTestCaseMap.put("deviceTicketStatusVahanPortalAvailability", "TC_MAT_73");
		ticketTestCaseMap.put("deviceTicketStatusVehicleDataavailabilityonVahan", "TC_MAT_74");
		ticketTestCaseMap.put("deviceTicketStatusBackendPortalAvailability", "TC_MAT_75");
		ticketTestCaseMap.put("deviceTicketStatusPanicEventConfirmation", "TC_MAT_76");
		ticketTestCaseMap.put("deviceTicketStatusOTPLiveLocationFetching", "TC_MAT_77");
		ticketTestCaseMap.put("deviceTicketStatusCertificateGenerationSubmission", "TC_MAT_78");
		ticketTestCaseMap.put("genareateCertificateDate", "TC_MAT_79");
		ticketTestCaseMap.put("deviceTicketStatusSelectCertificateValidity", "TC_MAT_80");
		ticketTestCaseMap.put("certificatExpiryeDate", "TC_MAT_81");
		ticketTestCaseMap.put("clickOnUploadVehicleCertificate", "TC_MAT_82");
		ticketTestCaseMap.put("clickOnUploadBackendCertificate", "TC_MAT_83");
		ticketTestCaseMap.put("deviceTicketStatusOverallTicketStatus1", "TC_MAT_84");
	}

	public static String getTestCaseId(String methodName) {
		return ticketTestCaseMap.get(methodName);
	}
}
