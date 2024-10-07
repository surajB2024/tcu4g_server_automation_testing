package org.aepl.tcu4g.utils;
import org.aepl.tcu4g.mappers.LoginTestCaseMapper;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        updateTestResult(result, "FAIL");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        updateTestResult(result, "PASS");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        updateTestResult(result, "SKIPPED");
    }

    private void updateTestResult(ITestResult result, String status) {
        String testName = result.getMethod().getMethodName();
        String testCaseId = LoginTestCaseMapper.getTestCaseId(testName);
        String remarks = getRemarks(result);
        ExcelReportUtil.addTestResult(testCaseId, status, remarks);
    }

    private String getRemarks(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Throwable throwable = result.getThrowable();
            if (throwable instanceof AssertionError) {
                return throwable.getMessage();
            } else if (throwable != null) {
                return "Unexpected error: " + throwable.getClass().getSimpleName() + " - " + throwable.getMessage();
            }
        }
        return "";
    }

    // Implement other methods of ITestListener interface if needed
}