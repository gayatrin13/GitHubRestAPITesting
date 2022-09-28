package github.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataReader {
	static XSSFCell cell;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static int noOfRows, noOfCols;

	public static String[][] getCellData(String fileName, String sheetName) {
		FileInputStream fs;
		String[][] data = null;
		try {
			fs = new FileInputStream(fileName);
			workbook = new XSSFWorkbook(fs);
			sheet = workbook.getSheet(sheetName);
			noOfRows = sheet.getLastRowNum();
			noOfCols = sheet.getRow(noOfRows).getLastCellNum();
			data = new String[noOfRows][noOfCols];

			for (int i = 1; i <= noOfRows; i++) {
				for (int j = 0; j < noOfCols; j++) {

					if (sheet.getRow(i).getCell(j).getCellType() == CellType.STRING)
						data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
					else if (sheet.getRow(i).getCell(j).getCellType() == CellType.BOOLEAN)
						data[i - 1][j] = "" + sheet.getRow(i).getCell(j).getBooleanCellValue();
					else if (sheet.getRow(i).getCell(j).getCellType() == CellType.NUMERIC)
						data[i - 1][j] = "" + sheet.getRow(i).getCell(j).getNumericCellValue();

				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

}
