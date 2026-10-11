package com.qa.framework.engine;
import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
public class ExcelReadTask {
	private final String excelPath;
    private final String sheetName;
    private final String columnName;
    private String result;
    private final String rowHeader;
    private final ArrayList<String> RowHeader;
    private final String writeValue;
    private final String[] columnHeaders=new String[2];
   

    private final ArrayList<String> WriteValue;
    private final String colHeader;
    private final CompletableFuture<String> futureResult = new CompletableFuture<>();

    public ExcelReadTask(String excelPath, String sheetName, String columnName) {
        
        this.excelPath = Objects.requireNonNull(excelPath, "Excel path cannot be null");
        this.sheetName = Objects.requireNonNull(sheetName, "Sheet name cannot be null");
        this.columnName = Objects.requireNonNull(columnName, "Column name cannot be null");
        this.rowHeader = null;
        this.colHeader = null;
        this.writeValue =null;
        this.WriteValue =null;
        this.RowHeader=null;
    
    }
    public ExcelReadTask(String[] columnHeaders, String excelPath, String sheetName) {
        
        this.excelPath = Objects.requireNonNull(excelPath, "Excel path cannot be null");
        this.sheetName = Objects.requireNonNull(sheetName, "Sheet name cannot be null");
        this.columnHeaders[0]=Objects.requireNonNull(columnHeaders[0], "Column name cannot be null");
        this.columnHeaders[1]=Objects.requireNonNull(columnHeaders[1], "Column name cannot be null");
        this.columnName = null;
        this.rowHeader =null;
        this.colHeader = null; 
        this.writeValue =null;
        this.RowHeader=null;
        this.WriteValue =null;
    }
    
 
    public ExcelReadTask(String excelPath, String sheetName, String rowHeader, String colHeader) {
        this.excelPath = Objects.requireNonNull(excelPath, "Excel path cannot be null");
        this.sheetName = Objects.requireNonNull(sheetName, "Sheet name cannot be null");
        this.rowHeader = Objects.requireNonNull(rowHeader, "Row header cannot be null");
        this.colHeader = Objects.requireNonNull(colHeader, "Column header cannot be null");
        this.columnName = null;
        this.writeValue =null;
        this.RowHeader=null;
        this.WriteValue =null;
       
        
    }
    public ExcelReadTask(String excelPath, String sheetName, String rowHeader, String colHeader, String writeValue) {
    	 this.excelPath = Objects.requireNonNull(excelPath, "Excel path cannot be null");
         this.sheetName = Objects.requireNonNull(sheetName, "Sheet name cannot be null");
         this.rowHeader = Objects.requireNonNull(rowHeader, "Row header cannot be null");
         this.colHeader = Objects.requireNonNull(colHeader, "Column header cannot be null");
         this.writeValue = Objects.requireNonNull(writeValue, "Write value cannot be null");
         this.columnName = null;
         this.RowHeader=null;
         this.WriteValue =null;
      
     
       
    }
    public ExcelReadTask(String excelPath, String sheetName, ArrayList<String> rowHeader, String colHeader, ArrayList<String> writeValue) {
   	 this.excelPath = Objects.requireNonNull(excelPath, "Excel path cannot be null");
        this.sheetName = Objects.requireNonNull(sheetName, "Sheet name cannot be null");
        this.RowHeader = Objects.requireNonNull(rowHeader, "Row header cannot be null");
        this.colHeader = Objects.requireNonNull(colHeader, "Column header cannot be null");
        this.WriteValue = Objects.requireNonNull(writeValue, "Write value cannot be null");
        this.columnName = null;
        this.rowHeader=null;
        this.writeValue =null;
      
   }

    // Getters (Immutable)
    public String getExcelPath() { return excelPath; }
    public String getSheetName() { return sheetName; }
    public String getColumnName() { return columnName; }
    public String getRowHeader() { return rowHeader; }
    public String getColHeader() { return colHeader; }
    public String getWriteValue() { return writeValue; }
    public ArrayList<String> getwriteValue() { return WriteValue; }
    public String[] getColumnHeaders() { 
        return columnHeaders; 
    }
    public ArrayList<String> getrowHeader() { 
        return  RowHeader; 
    }

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
        return "ExcelReadTask{" +
               "excelPath='" + excelPath + '\'' +
               ", sheetName='" + sheetName + '\'' +
               ", columnName='" + columnName + '\'' +
                 ", rowHeader='" + rowHeader + '\'' +
                ", colHeader='" + colHeader + '\'' +
                 ", writeValue='" + writeValue + '\'' +
                 ",columnHeaders='"+  Arrays.toString(columnHeaders)+'\''+
               ", result='" + result + '\'' +
               '}';
    }


}

