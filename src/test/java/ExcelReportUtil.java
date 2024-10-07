/**
 * ExcelReportUtil.java
 * Created by: Arnav T.
 * Created on: 21/08/2024
 * Last updated on: 23/08/2024

 * This utility class handles Excel report generation for test results.
 * It provides methods to initialize, populate, and save Excel workbooks based on a template.

 * Key features:
 * - Initializes workbook from a template file
 * - Supports switching between different sheets in the workbook
 * - Dynamically locates required columns (Test Case ID, Status, Comment)
 * - Adds test results to the appropriate rows
 * - Saves the workbook with a timestamp-based filename in a organized folder structure

 * Important notes:
 * - Update BASE_FOLDER and TEMPLATE_PATH constants as needed for the test environment
 * - Ensure the template Excel file has the expected structure (headers in the 5th row)
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReportUtil {
    private static final String BASE_FOLDER = "results";
    private static final String TEMPLATE_PATH = "Reference_template.xlsx";
    private static Workbook workbook;
    private static Sheet currentSheet;
    private static int headerRow = 4; // 5th row (0-based index)
    private static int testCaseIdColumn = -1;
    private static int statusColumn = -1;
    private static int remarksColumn = -1;

    public static void initializeWorkbook() {
        try {
            FileInputStream fis = new FileInputStream(new File(TEMPLATE_PATH));
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize workbook", e);
        }
    }

    public static void switchToSheet(String sheetName) {
        currentSheet = workbook.getSheet(sheetName);
        if (currentSheet == null) {
            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the workbook.");
        }
        // Reset column indices
        testCaseIdColumn = -1;
        statusColumn = -1;
        remarksColumn = -1;
        findColumns();
    }

    private static void findColumns() {
        Row headerRow = currentSheet.getRow(4); // 5th row (0-based index)
        if (headerRow == null) {
            throw new IllegalStateException("Header row not found in the sheet.");
        }
        for (Cell cell : headerRow) {
            if (cell == null) {
				continue;
			}
            String cellValue = getCellValueAsString(cell).toLowerCase().trim();
            if (cellValue.equals("test case id")) {
                testCaseIdColumn = cell.getColumnIndex();
            } else if (cellValue.equals("status")) {
                statusColumn = cell.getColumnIndex();
            } else if (cellValue.equals("comment")) {
                remarksColumn = cell.getColumnIndex();
            }
        }
        if (testCaseIdColumn == -1 || statusColumn == -1 || remarksColumn == -1) {
            throw new IllegalStateException("Required columns not found in the sheet. " +
                    "TestCaseID: " + testCaseIdColumn +
                    ", Status: " + statusColumn +
                    ", Comment: " + remarksColumn);
        }
    }

    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    public static synchronized void addTestResult(String testCaseId, String result, String remarks) {
        for (Row row : currentSheet) {
            if (row.getRowNum() <= headerRow) {
				continue;
			}
            Cell idCell = row.getCell(testCaseIdColumn);
            if (idCell != null && getCellValueAsString(idCell).equals(testCaseId)) {
                Cell statusCell = row.getCell(statusColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                statusCell.setCellValue(result);
                Cell remarksCell = row.getCell(remarksColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                remarksCell.setCellValue(remarks);
                return;
            }
        }
        System.out.println("Test Case ID '" + testCaseId + "' not found in the sheet.");
    }

    public static void saveWorkbook() {
        LocalDateTime now = LocalDateTime.now();
        String filePath = createFilePath(now);
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
            System.out.println("Excel file saved: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String createFilePath(LocalDateTime dateTime) {
        String year = String.valueOf(dateTime.getYear());
        String month = String.format("%02d", dateTime.getMonthValue());
        String day = String.format("%02d", dateTime.getDayOfMonth());
        String timestamp = dateTime.format(DateTimeFormatter.ofPattern("HHmmss"));
        String folderPath = BASE_FOLDER + File.separator + year + File.separator + month + File.separator + day;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folderPath + File.separator + "TestResults_" + timestamp + ".xlsx";
    }
}