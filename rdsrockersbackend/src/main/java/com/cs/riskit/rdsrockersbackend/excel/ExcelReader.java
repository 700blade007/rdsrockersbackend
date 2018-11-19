package com.cs.riskit.rdsrockersbackend.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private static final String FILE = "uploads/ExcelFile.xlsx";

    public static void readExcel(){
 
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE));
            Workbook workbook = new XSSFWorkbook(excelFile);
            
            List<String> workBookDataTypes = ExcelHelper.getTypes(workbook);
            List<String> workBookColumns = ExcelHelper.getColumns(workbook);
            
            ExcelDbHelper.createTable("TEST1",workBookColumns,workBookDataTypes);
            ExcelDbHelper.insertIntoTable("TEST1",workbook);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}