package com.qa.framework.engine;
import java.util.concurrent.CompletableFuture;
import java.util.Objects;
public class ExcelwriteTask {
	private final String excelPath;
    private final String sheetName;
    private final String columnName;
    private String result;
    private final String rowHeader;

    private final String writeValue;
    private final String colHeader;
    private final CompletableFuture<String> futureResult = new CompletableFuture<>();

    public ExcelwriteTask(String columnName,String excelPath,String writeValue, String sheetName) {
      
        this.excelPath = Objects.requireNonNull(excelPath, "Excel path cannot be null");
        this.sheetName = Objects.requireNonNull(sheetName, "Sheet name cannot be null");
        this.columnName =null;
        this.rowHeader = null;
        this.colHeader =  Objects.requireNonNull(columnName, "Column name cannot be null");;
        this.writeValue =Objects.requireNonNull(writeValue, "writeValuecannot be null");
    }

    public String getExcelPath() { return excelPath; }
    public String getSheetName() { return sheetName; }
    public String getColumnName() { return columnName; }
    public String getRowHeader() { return rowHeader; }
    public String getColHeader() { return colHeader; }
    public String getWriteValue() { return writeValue; }
    public String getResult() { return result; }

   
    public void setResult(String result) {
        this.result = result;
        futureResult.complete(result); 
    }

   
    public CompletableFuture<String> getFutureResult() {
        return futureResult;
    }

    @Override
    public String toString() {
        return "ExcelwriteTask{" +
               "excelPath='" + excelPath + '\'' +
               ", sheetName='" + sheetName + '\'' +
               ", columnName='" + columnName + '\'' +
                 ", rowHeader='" + rowHeader + '\'' +
                ", colHeader='" + colHeader + '\'' +
                 ", writeValue='" + writeValue + '\'' +
               ", result='" + result + '\'' +
               '}';
    }


}
