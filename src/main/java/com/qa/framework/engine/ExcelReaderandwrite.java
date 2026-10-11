package com.qa.framework.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.concurrent.locks.ReentrantLock;

public class ExcelReaderandwrite extends Thread {
	private static final BlockingQueue<ExcelReadTask> taskQueue = new LinkedBlockingQueue<>();
    private static final Map<String, Integer> columnIndexMap = new HashMap<>();
    private static final ReentrantLock lock = new ReentrantLock();

    private static volatile boolean running = true;

	 @Override
	    public void run() {
	        while (running || !taskQueue.isEmpty()) {
	        	 try {
	                 ExcelReadTask task = taskQueue.poll(5, java.util.concurrent.TimeUnit.SECONDS);
	                 if (task != null) {
	                     task.setResult(readExcelCellWithStatusCheck(task));
	                 }
	             } catch (Exception e) {
	                 e.printStackTrace();
	             }
	        }
	    }
	 public static synchronized void addTask(ExcelReadTask task) {
	        taskQueue.offer(task);
	    }
	 public static void stopWorker() {
	        running = false;
	    }
	 public static synchronized String readExcelCellWithStatusCheck(ExcelReadTask task) throws IOException {

		  String excelPath = task.getExcelPath();
		    String sheetName = task.getSheetName();
		    String columnName = task.getColumnName();
		    String value = null;
		    File file = new File(excelPath);

		    try (FileInputStream fis = new FileInputStream(file);
		         XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

		        XSSFSheet sheet = workbook.getSheet(sheetName);
		        Row headerRow = sheet.getRow(0);
		        if (headerRow == null) {
		            throw new IllegalArgumentException("Header row is empty.");
		        }

		        int columnIndex = -1;
		        int statusColumnIndex = -1;

		       
		        for (Cell cell : headerRow) {
		            String cellValue = cell.getStringCellValue().trim();
		            if (cellValue.equalsIgnoreCase(columnName)) {
		                columnIndex = cell.getColumnIndex();
		                break;
		            }
		        }

		        if (columnIndex == -1) {
		            throw new IllegalArgumentException("Column '" + columnName + "' not found.");
		        }

		        
		        String expectedStatusColumnName = (columnIndex == 0) ? "Status" : "status";

		        for (Cell cell : headerRow) {
		            String cellValue = cell.getStringCellValue().trim();
		            if (cellValue.equals(expectedStatusColumnName)) { // Case-sensitive match
		                statusColumnIndex = cell.getColumnIndex();
		                break;
		            }
		        }

		        if (statusColumnIndex == -1) {
		            throw new IllegalArgumentException("Status column '" + expectedStatusColumnName + "' not found.");
		        }

		        
		        for (int k = 1; k <= sheet.getLastRowNum(); k++) {
		            Row row = sheet.getRow(k);
		            if (row == null) continue;

		            Cell statusCell = row.getCell(statusColumnIndex);
		            String statusValue = (statusCell != null) ? getCellValueAsString(statusCell).trim().toLowerCase() : "";

		            if (!statusValue.equals("used")) {
		                Cell dataCell = row.getCell(columnIndex);
		                if (dataCell != null) {
		                    value = getCellValueAsString(dataCell);

		                  
		                    if (statusCell == null) {
		                        statusCell = row.createCell(statusColumnIndex);
		                    }
		                    statusCell.setCellValue("USED");

		                    try (FileOutputStream fos = new FileOutputStream(file)) {
		                        workbook.write(fos);
		                    }
		                    break;
		                }
		            }
		        }
		    }
		    if (value == null || value.equalsIgnoreCase("")) {
		        throw new RuntimeException(
		            "Excel read failed: no unused value found for column '" +
		            columnName + "' in sheet '" + sheetName + "'"
		        );
		    }
		    return value;
		}

	 private static String getCellValueAsString(Cell cell) {
         switch (cell.getCellType()) {
             case STRING:
                 return cell.getStringCellValue();
             case NUMERIC:
                 if (DateUtil.isCellDateFormatted(cell)) {
                 	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                       return dateFormat.format(cell.getDateCellValue());
                 }else if(cell.getCellType()==CellType.NUMERIC){
                 	DataFormatter fromatter=new DataFormatter();
                 	return fromatter.formatCellValue(cell);
                 	
                 }
                 
                 
                 
                 
                 else {
                 	double cellValue = cell.getNumericCellValue();
                     // Check if the value is an integer
                     if (cellValue == Math.floor(cellValue)) {
                        // System.out.println((int) cellValue);
                         return String.valueOf((int) cellValue);
                     } else {
                     	 return String.valueOf(cellValue);
                     }
                 }
             case BOOLEAN:
                 return String.valueOf(cell.getBooleanCellValue());
             case FORMULA:
                 return cell.getCellFormula();
             case BLANK:
                 return "";
             default:
                 return "Unknown cell type";
         }
     }
	 public static void writeExcelCell(String columnName, String excelPath, String inputValue, String sheetName) throws IOException {
         lock.lock();  // Lock to prevent simultaneous access to the file
         try (FileInputStream fis = new FileInputStream(new File(excelPath));
              XSSFWorkbook workbook = new XSSFWorkbook(fis);
              FileOutputStream fos = new FileOutputStream(new File(excelPath))) {

             XSSFSheet sheet = workbook.getSheet(sheetName);
             if (sheet == null) {
                 throw new IllegalArgumentException("Sheet " + sheetName + " not found in " + excelPath);
             }

             // Find column index
             Row headerRow = sheet.getRow(0);
             int columnIndex = -1;
             for (Cell cell : headerRow) {
                 if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                     columnIndex = cell.getColumnIndex();
                     break;
                 }
             }
             if (columnIndex == -1) {
                 throw new IllegalArgumentException("Column with heading " + columnName + " not found.");
             }

             // Find the last row for writing data
             int lastRow = findLastRow(sheet, columnIndex);
             Row row = sheet.getRow(lastRow);
             if (row == null) {
                 row = sheet.createRow(lastRow);
             }

             Cell newCell = row.createCell(columnIndex);
             if (columnName.equalsIgnoreCase("New_cheque_No")) {
                 BigInteger num = new BigInteger(inputValue);
                 for (int i = 0; i < 25; i++) {
                     row = sheet.createRow(lastRow + i);
                     newCell = row.createCell(columnIndex);
                     newCell.setCellValue(String.format("%014d", num.add(BigInteger.valueOf(i + 10))));
                 }
             } else {
                 newCell.setCellValue(inputValue);
             }

             workbook.write(fos); // Write to file only once

         } finally {
             lock.unlock();  // Ensure lock is released in case of an exception
         }
     }

     public static int findlastrow(XSSFSheet sheet,int coloumnindex) {
               int columnindex=coloumnindex; 
               int lastRowNum=sheet.getLastRowNum();
               int lastrow=-1;
               for(int rowindex=0;rowindex<=lastRowNum;rowindex++){
             	  Row row=sheet.getRow(rowindex);
             	  if(row !=null) {
             		  Cell cell=row.getCell(columnindex);
             		  if(cell!=null && cell.getCellType()!=CellType.BLANK) {
             			  lastrow=rowindex;
             		  }
             	  }
               }
               return lastrow+1;
     }
     public static synchronized void writeExcelCell(ExcelwriteTask Task) throws IOException {
         lock.lock();  // Lock to prevent simultaneous access to the file
         try (FileInputStream fis = new FileInputStream(new File(Task.getExcelPath()));
              XSSFWorkbook workbook = new XSSFWorkbook(fis);
              FileOutputStream fos = new FileOutputStream(new File(Task.getExcelPath()))) {

             XSSFSheet sheet = workbook.getSheet(Task.getSheetName());
             if (sheet == null) {
                 throw new IllegalArgumentException("Sheet " + Task.getSheetName() + " not found in " + Task.getExcelPath());
             }

             // Find column index
             Row headerRow = sheet.getRow(0);
             int columnIndex = -1;
             for (Cell cell : headerRow) {
                 if (cell.getStringCellValue().equalsIgnoreCase(Task.getColHeader())) {
                     columnIndex = cell.getColumnIndex();
                     break;
                 }
             }
             if (columnIndex == -1) {
                 throw new IllegalArgumentException("Column with heading " + Task.getColHeader() + " not found.");
             }

             // Find the last row for writing data
             int lastRow = findLastRow(sheet, columnIndex);
             Row row = sheet.getRow(lastRow);
             if (row == null) {
                 row = sheet.createRow(lastRow);
             }

             Cell newCell = row.createCell(columnIndex);
             if (Task.getColHeader().equalsIgnoreCase("New_cheque_No")) {
            	    BigInteger num = new BigInteger(Task.getWriteValue());

            	    for (int i = 0; i < 25; i++) {
            	        row = sheet.createRow(lastRow + i);
            	        newCell = row.createCell(columnIndex);

            	        // Start exactly from the given number
            	        newCell.setCellValue(
            	            String.format("%014d", num.add(BigInteger.valueOf(i)))
            	        );
            	    }
             }else {
                 newCell.setCellValue(Task.getWriteValue());
             }

             workbook.write(fos); // Write to file only once

         } finally {
             lock.unlock();  // Ensure lock is released in case of an exception
         }
     }

     private static int findLastRow(XSSFSheet sheet, int columnIndex) {
         int lastRow = sheet.getLastRowNum();
         for (int i = lastRow; i >= 0; i--) {
             Row row = sheet.getRow(i);
             if (row != null && row.getCell(columnIndex) != null) {
                 return i + 1;
             }
         }
         return 1; // Start from row 1 if nothing is found
     }
     public static void writeToExcel(String filePath, String sheetName, String headerColumn1, 
             String headerColumn2, String matchValue, String updateValue) throws IOException {
synchronized (lock) {  // Fine-grained synchronization
try (FileInputStream fis = new FileInputStream(filePath);
XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

XSSFSheet sheet = workbook.getSheet(sheetName);
if (sheet == null) {
throw new IllegalArgumentException("Sheet '" + sheetName + "' not found.");
}

Row headerRow = sheet.getRow(0);
if (headerRow == null) {
throw new IllegalArgumentException("Header row missing in sheet '" + sheetName + "'.");
}

// Find column indices
int column1Index = -1, column2Index = -1;
for (Cell cell : headerRow) {
String cellValue = cell.getStringCellValue().trim();
if (cellValue.equalsIgnoreCase(headerColumn1)) {
 column1Index = cell.getColumnIndex();
} else if (cellValue.equalsIgnoreCase(headerColumn2)) {
 column2Index = cell.getColumnIndex();
}
}

if (column1Index == -1 || column2Index == -1) {
throw new IllegalArgumentException("Headers '" + headerColumn1 + "' or '" + headerColumn2 + "' not found.");
}

DataFormatter formatter = new DataFormatter();

for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row
Row row = sheet.getRow(i);
if (row == null) continue;

Cell column1Cell = row.getCell(column1Index);
String column1Value = column1Cell != null ? formatter.formatCellValue(column1Cell).trim() : "";

if (column1Value.equalsIgnoreCase(matchValue.trim())) {
 Cell column2Cell = row.getCell(column2Index);
 if (column2Cell == null) {
     column2Cell = row.createCell(column2Index, CellType.STRING);
 }
 column2Cell.setCellValue(updateValue);
}
}

// Save the updated Excel file
try (FileOutputStream fos = new FileOutputStream(filePath)) {
workbook.write(fos);
}
}
}
}
     public static void writeMulitpleValuesToColumns(String excelPath, String sheetName, Map<String, String> columnValueMap) throws IOException {
         try (FileInputStream fis = new FileInputStream(new File(excelPath));
                 XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

                // Get the sheet
        	 XSSFSheet sheet = null;
        	 for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
        		    String actual = workbook.getSheetName(i)
        		            .replace("\u00A0", " ")
        		            .trim();

        		    String expected = sheetName
        		            .replace("\u00A0", " ")
        		            .trim();

        		    if (actual.equalsIgnoreCase(expected)) {
        		        sheet = workbook.getSheetAt(i);
        		        break;
        		    }
        		}
                if (sheet == null) {
                    throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in the Excel file.");
                }

                // Fetch or create the last row
                int lastRowIndex = sheet.getLastRowNum() + 1; // Last row index + 1 to add a new row
                Row dataRow = sheet.createRow(lastRowIndex);

                // Map column headings to their indices
                Row headerRow = sheet.getRow(0);
                if (headerRow == null) {
                    throw new IllegalArgumentException("Header row not found in sheet " + sheetName);
                }
                Map<String, Integer> columnIndexMap = getColumnIndexMap(headerRow);

                // Write values into the data row based on headings
                for (Map.Entry<String, String> entry : columnValueMap.entrySet()) {
                    String columnHeading = entry.getKey();
                    String value = entry.getValue();

                    Integer columnIndex = columnIndexMap.get(columnHeading);
                    if (columnIndex != null) {
                        Cell cell = dataRow.createCell(columnIndex, CellType.STRING);
                        cell.setCellValue(value);
                    } else {
                        System.out.println("Column heading not found: " + columnHeading);
                    }
                }

                // Save the updated workbook
                try (FileOutputStream fos = new FileOutputStream(new File(excelPath))) {
                    workbook.write(fos);
                }
            }
        }
     public static void writeValuesByReferenceColumn(String excelPath, String sheetName, 
                String referenceColumnName, String referenceValue, Map<String, String> columnValueMap) throws IOException {

            try (FileInputStream fis = new FileInputStream(new File(excelPath));
                 XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

                XSSFSheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    throw new IllegalArgumentException("Sheet " + sheetName + " does not exist.");
                }

                // Get header row
                Row headerRow = sheet.getRow(0);
                if (headerRow == null) {
                    throw new IllegalArgumentException("Header row not found.");
                }

                // Map column headings to indices
                Map<String, Integer> columnIndexMap = getColumnIndexMap(headerRow);

                // Get reference column index
                Integer referenceColumnIndex = columnIndexMap.get(referenceColumnName);
                if (referenceColumnIndex == null) {
                    throw new IllegalArgumentException("Reference column '" + referenceColumnName + "' not found.");
                }

                // Find the row that matches the reference value
                int targetRowIndex = -1;
                for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Cell refCell = row.getCell(referenceColumnIndex);
                        if (refCell != null && refCell.getCellType() == CellType.STRING &&
                                refCell.getStringCellValue().equalsIgnoreCase(referenceValue)) {
                            targetRowIndex = i;
                            break;
                        }
                    }
                }

//                if (targetRowIndex == -1) {
//                    throw new IllegalArgumentException("No row found with " + referenceColumnName + " = " + referenceValue);
//                }

                // Get the target row
                Row targetRow = sheet.getRow(targetRowIndex);

                // Write values from map into the same row
                for (Map.Entry<String, String> entry : columnValueMap.entrySet()) {
                    String columnName = entry.getKey();
                    String value = entry.getValue();

                    Integer columnIndex = columnIndexMap.get(columnName);
                    if (columnIndex != null) {
                        Cell cell = targetRow.getCell(columnIndex);
                        if (cell == null) {
                            cell = targetRow.createCell(columnIndex, CellType.STRING);
                        }
                        cell.setCellValue(value);
                    } else {
                        System.out.println("Column not found: " + columnName);
                    }
                }

                // Save the updated file
                try (FileOutputStream fos = new FileOutputStream(new File(excelPath))) {
                    workbook.write(fos);
                }
            }
        }

        private static Map<String, Integer> getColumnIndexMap(Row headerRow) {
            Map<String, Integer> columnIndexMap = new java.util.HashMap<>();
            for (Cell cell : headerRow) {
                columnIndexMap.put(cell.getStringCellValue(), cell.getColumnIndex());
            }
            return columnIndexMap;
        }
        public static Map<String, String> readExcelValuesWithHeaders(ExcelReadTask task) throws IOException {

            lock.lock();

            try (FileInputStream fis = new FileInputStream(new File(task.getExcelPath()));
                 XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

                Map<String, String> headerValueMap = new HashMap<>();

                XSSFSheet sheet = workbook.getSheet(task.getSheetName());

                if (sheet == null) {
                    throw new IllegalArgumentException(
                            "Sheet with name " + task.getSheetName() + " not found.");
                }

                Row headerRow = sheet.getRow(0);

                if (headerRow == null) {
                    throw new IllegalArgumentException("Header row is missing.");
                }

                // Store column indexes
                Map<String, Integer> headerIndexMap = new HashMap<>();
                int statusColumnIndex = -1;

                for (Cell cell : headerRow) {

                    String cellValue = cell.getStringCellValue();

                    for (String header : task.getColumnHeaders()) {

                        if (cellValue.equalsIgnoreCase(header)) {
                            headerIndexMap.put(header, cell.getColumnIndex());
                        }
                    }

                    if (cellValue.equalsIgnoreCase("Status")) {
                        statusColumnIndex = cell.getColumnIndex();
                    }
                }

                // Validate headers
                for (String header : task.getColumnHeaders()) {

                    if (!headerIndexMap.containsKey(header)) {
                        throw new IllegalArgumentException(
                                "Header not found: " + header);
                    }
                }

                // Find first unused row
                boolean rowFound = false;

                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

                    Row row = sheet.getRow(rowIndex);

                    if (row == null) {
                        continue;
                    }

                    Cell statusCell = statusColumnIndex != -1
                            ? row.getCell(statusColumnIndex,
                            Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                            : null;

                    // Skip USED rows
                    if (statusCell != null &&
                            !getCellValueAsString(statusCell).trim().isEmpty()) {

                        continue;
                    }

                    // Read all headers from SAME row
                    for (String header : task.getColumnHeaders()) {

                        int columnIndex = headerIndexMap.get(header);

                        Cell cell = row.getCell(columnIndex,
                                Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                        headerValueMap.put(header,
                                getCellValueAsString(cell));
                    }

                    // Mark row as USED ONCE
                    if (statusCell != null) {
                        statusCell.setCellValue("USED");
                    }

                    rowFound = true;
                    break;
                }

                if (!rowFound || headerValueMap.isEmpty()) {

                    throw new RuntimeException(
                            "No unused rows found in sheet: "
                                    + task.getSheetName());
                }

                // Save workbook
                try (FileOutputStream fos =
                             new FileOutputStream(task.getExcelPath())) {

                    workbook.write(fos);
                }

                return headerValueMap;

            } finally {
                lock.unlock();
            }
        }
        private static String readCell_Header(String excelPath, String sheetName, String rowHeader, String colHeader) {
            try (FileInputStream fis = new FileInputStream(excelPath);
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    throw new RuntimeException("Sheet not found: " + sheetName);
                }

                // 1. Find column index by header (first row)
                Row headerRow = sheet.getRow(0);
                int colIndex = -1;
                for (Cell cell : headerRow) {
                    if (cell != null && cell.getCellType() == CellType.STRING &&
                        cell.getStringCellValue().equalsIgnoreCase(colHeader)) {
                        colIndex = cell.getColumnIndex();
                        break;
                    }
                }
                if (colIndex == -1) {
                    throw new RuntimeException("Column not found: " + colHeader);
                }

                // 2. Find row index by row header (first column)
                int rowIndex = -1;
                for (Row row : sheet) {
                    Cell firstCell = row.getCell(0);
                    if (firstCell != null && firstCell.getCellType() == CellType.STRING &&
                        firstCell.getStringCellValue().equalsIgnoreCase(rowHeader)) {
                        rowIndex = row.getRowNum();
                        break;
                    }
                }
                if (rowIndex == -1) {
                    throw new RuntimeException("Row not found: " + rowHeader);
                }

                // 3. Get value at intersection (rowIndex, colIndex)
                Row row = sheet.getRow(rowIndex);
                Cell cell = row.getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                // Use DataFormatter to get cell value as String (works for numbers, dates, strings)
                DataFormatter formatter = new DataFormatter();
                return formatter.formatCellValue(cell);

            } catch (Exception e) {
                throw new RuntimeException("Excel read failed", e);
            }
        }

        
        public static synchronized String readExcelCellByRowAndCol(ExcelReadTask task) throws Exception {

            try (FileInputStream fis = new FileInputStream(task.getExcelPath());
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheet(task.getSheetName());
                if (sheet == null) {
                    throw new RuntimeException("Sheet not found: " + task.getSheetName());
                }

                DataFormatter formatter = new DataFormatter();

                // ✅ 1. Find column index by header
                Row headerRow = sheet.getRow(sheet.getFirstRowNum());
                if (headerRow == null) {
                    throw new RuntimeException("Header row is empty");
                }

                int colIndex = -1;
                for (Cell cell : headerRow) {
                    String header = formatter.formatCellValue(cell)
                                             .replace("\u00A0", " ")
                                             .trim();

                    if (header.equalsIgnoreCase(task.getColHeader())) {
                        colIndex = cell.getColumnIndex();
                        break;
                    }
                }

                if (colIndex == -1) {
                    throw new RuntimeException("Column not found: " + task.getColHeader());
                }

                // ✅ 2. Find row index by row header (first column)
                int rowIndex = -1;
                for (int i = headerRow.getRowNum() + 1; i <= sheet.getLastRowNum(); i++) {

                    Row row = sheet.getRow(i);
                    if (row == null) continue;

                    Cell firstCell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String rowHeader = formatter.formatCellValue(firstCell)
                                                .replace("\u00A0", " ")
                                                .trim();

                    if (rowHeader.equalsIgnoreCase(task.getRowHeader().trim())) {
                        rowIndex = i;
                        break;
                    }
                }

                if (rowIndex == -1) {
                    throw new RuntimeException("Row not found: " + task.getRowHeader());
                }

                // ✅ 3. Read cell value
                Row row = sheet.getRow(rowIndex);
                Cell cell = row.getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                return formatter.formatCellValue(cell).trim();
            }
        }

        public static synchronized void writeExcelCellByRowAndCol(ExcelReadTask task) throws Exception {
            Workbook workbook;

            // 1. Open workbook
            try (FileInputStream fis = new FileInputStream(task.getExcelPath())) {
                workbook = new XSSFWorkbook(fis);  // assumes .xlsx
            }

            // 2. Get sheet
            Sheet sheet = workbook.getSheet(task.getSheetName());
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + task.getSheetName());
            }

            // 3. Find column index by header
            Row headerRow = sheet.getRow(0);
            int colIndex = -1;
            for (Cell cell : headerRow) {
                if (cell != null && cell.getStringCellValue().equalsIgnoreCase(task.getColHeader())) {
                    colIndex = cell.getColumnIndex();
                    break;
                }
            }
            if (colIndex == -1) {
                throw new RuntimeException("Column not found: " + task.getColHeader());
            }

            // 4. Find row index by row header
            int rowIndex = -1;
            for (Row row : sheet) {
                Cell firstCell = row.getCell(0);
                if (firstCell != null && firstCell.getStringCellValue().equalsIgnoreCase(task.getRowHeader())) {
                    rowIndex = row.getRowNum();
                    break;
                }
            }
            if (rowIndex == -1) {
                throw new RuntimeException("Row not found: " + task.getRowHeader());
            }

            // 5. Write value
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }

            Cell cell = row.getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellValue(task.getWriteValue());

            // 6. Save changes
            try (FileOutputStream fos = new FileOutputStream(task.getExcelPath(), false)) {
                workbook.write(fos);
                fos.flush();
            }

            workbook.close();
        }

        
       
     

}
