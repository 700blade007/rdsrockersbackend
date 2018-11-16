package com.cs.riskit.rdsrockersbackend.excel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.h2.jdbcx.JdbcDataSource;

public class ExcelDbHelper {

	public static DataSource ds;

	{

	}

	public static void createTable(String tableName, List<String> workBookColumns, List<String> workBookDataTypes) {
		try {
			JdbcDataSource h2Datasource = new JdbcDataSource();
			h2Datasource.setURL("jdbc:h2:file:~/test;AUTO_SERVER=TRUE");
			h2Datasource.setUser("sa");
			ds = h2Datasource;
			Connection con = ds.getConnection();
			StringBuffer sql = new StringBuffer(
					"DROP TABLE IF EXISTS " + tableName + "; CREATE TABLE " + tableName + "(");
			for (int i = 0; i < workBookColumns.size(); i++) {
				sql.append(" " + workBookColumns.get(i) + " ");
				sql.append(" " + workBookDataTypes.get(i) + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(");");
			System.out.println(sql.toString());
			PreparedStatement ps = con.prepareStatement(sql.toString());
			boolean rs = ps.execute();
			System.out.println(rs);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoTable(String tableName, Workbook workbook) {
		try {
			JdbcDataSource h2Datasource = new JdbcDataSource();
			h2Datasource.setURL("jdbc:h2:file:~/test;AUTO_SERVER=TRUE");
			h2Datasource.setUser("sa");
			ds = h2Datasource;
			Connection con = ds.getConnection();
			StringBuffer sql = new StringBuffer();

			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				sql.append("INSERT INTO " + tableName + " VALUES(");

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						// System.out.print(currentCell.getStringCellValue() + " ");
						sql.append(" '" + currentCell.getStringCellValue() + "' ,");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						if (HSSFDateUtil.isCellDateFormatted(currentCell)) {
							// System.out.println (currentCell.getDateCellValue());
							sql.append(" '" + ExcelHelper.dateToString(currentCell.getDateCellValue()) + "' ,");
							continue;
						}
						sql.append(" " + currentCell.getNumericCellValue() + " ,");
					}
				}
				sql.deleteCharAt(sql.length() - 1);
				sql.append(");" + System.lineSeparator());
			}
			System.out.println(sql.toString());
			PreparedStatement ps = con.prepareStatement(sql.toString());
			boolean rs = ps.execute();
			System.out.println(rs);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
