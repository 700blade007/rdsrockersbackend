package com.cs.riskit.rdsrockersbackend.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.h2.jdbcx.JdbcDataSource;

public class ExcelReader {

    private static final String FILE_NAME = "Financial Sample.xlsx";

    public static void main(String[] args) {
    	
    	

        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            
            List<String> workBookDataTypes = ExcelHelper.getTypes(workbook);
            List<String> workBookColumns = ExcelHelper.getColumns(workbook);
            
            JdbcDataSource h2Datasource = new JdbcDataSource();
            h2Datasource.setURL("jdbc:h2:file:~/test;AUTO_SERVER=TRUE");
            h2Datasource.setUser("sa");
            
            DataSource ds= h2Datasource;
            
            ExcelDbHelper.createTable("TEST1",workBookColumns,workBookDataTypes);
            ExcelDbHelper.insertIntoTable("TEST1",workbook);
            
            try {
				Connection con = ds.getConnection();
				String sql = "SELECT * FROM TEST1;";
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					System.out.println(rs.getString(1));
					System.out.println(rs.getString(2));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}