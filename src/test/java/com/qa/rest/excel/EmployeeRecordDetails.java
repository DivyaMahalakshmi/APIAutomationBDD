package com.qa.rest.excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qa.rest.api.EmployeeDataObject;

/**
 * @author Divya Mahalakshmi S
 *
 */
public class EmployeeRecordDetails {

	public static List<EmployeeDataObject> employeeList = new ArrayList();

	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static FileInputStream fis;
    
	//This method is to retrieve data from given Excel sheet and feed it to the data object
	public List<EmployeeDataObject> getEmployeeData() throws Exception {

		fis = new FileInputStream("src/test/resources/Rest API- data.xlsx");
		wb = new XSSFWorkbook(fis);
		List<EmployeeDataObject> employeelist = new ArrayList<EmployeeDataObject>();
        int totalRecords= getIntCellData(1,0);
		for (int i = 1; i <= totalRecords; i++) {

			EmployeeDataObject employee = new EmployeeDataObject();
			employee.setName(getCellData(i, 2));
			employee.setAge(getIntCellData(i, 3));
			employee.setSalary(getIntCellData(i, 4));
			employeelist.add(employee);
		}
		return employeelist;
	}

	private String getCellData(int rownum, int cellnum) {
		try {

			sheet = wb.getSheet("PostMethod");
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

			sheet = wb.getSheet("PostMethod");
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
