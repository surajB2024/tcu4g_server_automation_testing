package org.aepl.tcu4g.mappers;

import java.util.HashMap;
import java.util.Map;

public class OtaTestCaseMapper {
    private static final Map<String, String> OtaTestCaseMapper = new HashMap<>();

    static {
        OtaTestCaseMapper.put("login", "TC_OTA_01");
        OtaTestCaseMapper.put("navigateToOtaAndClick", "TC_OTA_02");
        OtaTestCaseMapper.put("clickCreateNewBatchBtn", "TC_OTA_03");
        OtaTestCaseMapper.put("checkOtaBatchDetails", "TC_OTA_04");
        OtaTestCaseMapper.put("clickOnChooseCSVBtn", "TC_OTA_05");
        OtaTestCaseMapper.put("testDownloadBtn", "TC_OTA_06");

/*
Test Case ID
TC_OTA_01
TC_OTA_02
TC_OTA_03
TC_OTA_04
TC_OTA_05
TC_OTA_06
TC_OTA_07
TC_OTA_08
TC_OTA_09
TC_OTA_10
TC_OTA_11
TC_OTA_12
TC_OTA_13
TC_OTA_14
TC_OTA_15
TC_OTA_16
TC_OTA_17
TC_OTA_18
TC_OTA_19
TC_OTA_20
TC_OTA_21
TC_OTA_22
TC_OTA_23
TC_OTA_24
TC_OTA_25
TC_OTA_26
TC_OTA_27
TC_OTA_28
TC_OTA_29
TC_OTA_30
TC_OTA_31
TC_OTA_32
TC_OTA_33
TC_OTA_34
TC_OTA_35
TC_OTA_36
TC_OTA_37
TC_OTA_38
TC_OTA_39
TC_OTA_40
TC_OTA_41
TC_OTA_42
TC_OTA_43
TC_OTA_44
TC_OTA_45
TC_OTA_46
TC_OTA_47
TC_OTA_48
TC_OTA_49
TC_OTA_50
TC_OTA_51
TC_OTA_52
TC_OTA_53
TC_OTA_54
TC_OTA_55
TC_OTA_56
TC_OTA_57
TC_OTA_58
TC_OTA_59
TC_OTA_60
TC_OTA_61
TC_OTA_62
TC_OTA_63
TC_OTA_64
TC_OTA_65
TC_OTA_66
TC_OTA_67
TC_OTA_68
TC_OTA_69
TC_OTA_70
TC_OTA_71
TC_OTA_72
TC_OTA_73
TC_OTA_74
TC_OTA_75
TC_OTA_76
TC_OTA_77
TC_OTA_78
TC_OTA_79
TC_OTA_80
TC_OTA_81
TC_OTA_82
TC_OTA_83
TC_OTA_84
TC_OTA_85
TC_OTA_86
TC_OTA_87
TC_OTA_88
TC_OTA_89
TC_OTA_90
TC_OTA_91
TC_OTA_92
TC_OTA_93
TC_OTA_94
TC_OTA_95
TC_OTA_96
TC_OTA_97
TC_OTA_98
TC_OTA_99
TC_OTA_100
TC_OTA_101
TC_OTA_102
TC_OTA_103
TC_OTA_104
TC_OTA_105
TC_OTA_106
TC_OTA_107
TC_OTA_108
TC_OTA_109
TC_OTA_110
TC_OTA_111
TC_OTA_112
TC_OTA_113
TC_OTA_114
TC_OTA_115
TC_OTA_116
TC_OTA_117
TC_OTA_118
*/
    }

    public static String getTestCaseId(String testMethodName) {
        return OtaTestCaseMapper.get(testMethodName);
    }
}
