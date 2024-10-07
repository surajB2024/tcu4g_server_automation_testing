/**
 * TestCaseMapper.java
 * Created by: Arnav T.
 * Created on: 23/08/2024
 * Last updated on: 23/08/2024

 * This utility class provides a mapping between test method names and their corresponding test case IDs.
 * It allows for easy retrieval of test case IDs during test execution and reporting.

 * Key features:
 * - Uses a static HashMap to store mappings
 * - Provides a method to retrieve test case IDs based on method names
 * - Initializes mappings in a static block for easy maintenance

 * Important notes:
 * - Ensure that test case IDs are unique across all test suites
 * - Update the static block with new mappings as new test methods are added
 * - Consider organizing mappings into logical groups (e.g., by page or functionality)

 * Usage example:
 *     String testCaseId = TestCaseMapper.getTestCaseId("testValidLogin");
 *     // testCaseId will be "TC_LP_03"
 */

import java.util.HashMap;
import java.util.Map;

public class TestCaseMapper {
    private static final Map<String, String> testCaseMap = new HashMap<>(); //will contain all the function to test case ID mappings
    static {
        // Add your test case mappings here

        // Login Page Test Cases
        testCaseMap.put("testNavigateToWebsite", "TC_LP_01");
        testCaseMap.put("testLoginPageDisplayed", "TC_LP_02");
        testCaseMap.put("testValidLogin", "TC_LP_03");
        testCaseMap.put("testEmailFieldAcceptsVariousCharacters", "TC_LP_04");
        testCaseMap.put("testPasswordFieldAcceptsVariousCharacters", "TC_LP_05");
        testCaseMap.put("testInvalidEmailValidPassword", "TC_LP_06");
        testCaseMap.put("testValidEmailInvalidPassword", "TC_LP_07");
        testCaseMap.put("testBlankCredentials", "TC_LP_08");
        testCaseMap.put("testShortPassword", "TC_LP_09");
        testCaseMap.put("testForgotPasswordLink", "TC_LP_10");

        // Create subsections for different test case pages. Also make sure that the Test case IDs do not overlap
        // Add more mappings as needed
    }

    public static String getTestCaseId(String testMethodName) {
        return testCaseMap.getOrDefault(testMethodName, "N/A");
    }

    public static void addTestCaseMapping(String testMethodName, String testCaseId) { //just in case for future improvements
        testCaseMap.put(testMethodName, testCaseId);
    }
}