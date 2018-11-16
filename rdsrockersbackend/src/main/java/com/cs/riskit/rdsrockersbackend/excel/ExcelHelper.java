package com.cs.riskit.rdsrockersbackend.excel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelHelper {

	public static List<String> getTypes(Workbook workbook) {
		List<String> workBookDataTypes = new ArrayList<String>();

		Sheet datatypeSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = datatypeSheet.iterator();

		iterator.next();
		Row currentRow = iterator.next();

		Iterator<Cell> cellIterator = currentRow.iterator();
		while (cellIterator.hasNext()) {
			Cell currentCell = cellIterator.next();
			if (currentCell.getCellTypeEnum() == CellType.BOOLEAN)
				workBookDataTypes.add("boolean");
			else if (currentCell.getCellTypeEnum() == CellType.STRING)
				workBookDataTypes.add("varchar");
			else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
				if (HSSFDateUtil.isCellDateFormatted(currentCell)) {
					workBookDataTypes.add("date");
					continue;
				}
				workBookDataTypes.add("int");
			}

		}
		return workBookDataTypes;
	}

	public static List<String> getColumns(Workbook workbook) {
		List<String> workBookColumns = new ArrayList<String>();

		Sheet datatypeSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = datatypeSheet.iterator();

		Row currentRow = iterator.next();

		Iterator<Cell> cellIterator = currentRow.iterator();
		while (cellIterator.hasNext()) {
			Cell currentCell = cellIterator.next();
			String temp = currentCell.getStringCellValue();
			temp = temp.trim().replaceAll("\\s+", "_");
			System.out.println(temp);
			workBookColumns.add(temp);
		}
		return workBookColumns;
	}

	public static String dateToString(Date d) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(d);
	}
}
