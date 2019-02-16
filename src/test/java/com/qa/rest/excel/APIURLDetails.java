package com.qa.rest.excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Divya Mahalakshmi S
 *
 */

// This eclipse project is DataDriven
// The below class is used to retrieve data from an external source-xlsx sheet
// in this case.
public class APIURLDetails {

	// Traverse the path Workbook -> Excel -> Sheet -> Row ->Column -> Obtain the
	// value in the cell
	// XSSF is used by Apache POI to access workbook
	// The variables should be declared public - we could use it across the project
	// splitting variables and getting row/cell numbers so hardcoding could be
	// avoided
	public static XSSFWorkbook wb; 
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static FileInputStream fis;

	public List<Integer> getEmployeeID() throws Exception {

		FileInputStream fis = new FileInputStream("src/test/resources/Rest API- data.xlsx");
		wb = new XSSFWorkbook(fis);
		List<Integer> employeeID = new ArrayList<Integer>();

		for (int i = 1; i < 345; i++) {
			employeeID.add(getIntCellData(i, 3));
		}
		return employeeID;
	}

	public List<Integer> uniqueEmpID() throws Exception {

		FileInputStream fis = new FileInputStream("C:\\Users\\DELL\\Documents\\Rest API\\Rest API- data.xlsx");
		wb = new XSSFWorkbook(fis);
		List<Integer> uniqueEmpID = new ArrayList<Integer>();

		for (int i = 1; i < 20; i++) {
			uniqueEmpID.add(getIntCellData(i, 3));
		}
		return uniqueEmpID;
	}

	public String getEmpDataURL() {
		return getCellData(1, 0);
	}

	public String getEMPURLID() {
		return getCellData(1, 1);
	}

	public String getEMPBaseURI() {
		return getCellData(1, 2);
	}

	public String getEMPCreateURI() {
		return getCellData(1, 4);
	}
	
	public String getEMPUpdateURI() {
		return getCellData(1, 5);
	}
	
	public String getEMPDeleteURI() {
			return getCellData(1, 6);
		
	}

	protected String getCellData(int rownum, int cellnum) {
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\DELL\\Documents\\Rest API\\Rest API- data.xlsx");
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("BaseClass");
			row = sheet.getRow(rownum);
			cell = row.getCell(cellnum);
			return cell.getStringCellValue();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

	private int getIntCellData(int rownum, int cellnum) {
		try {

			sheet = wb.getSheet("BaseClass");
			row = sheet.getRow(rownum);
			cell = row.getCell(cellnum);
			return (int) cell.getNumericCellValue(); // Integer.parseInt(cell.getStringCellValue());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return 0;
		}

	}
}
