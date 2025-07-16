package com.comcast.crm.generic.ExcelUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcel(String sheetname, int rownum, int celnum) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testdata\\TestScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rownum).getCell(celnum).getStringCellValue();
		wb.close();
		return data;
		
	}
	
     public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testdata\\TestScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetname).getLastRowNum();
		wb.close();
		return rowCount;	
		}
     
     public void setDataIntoExcel(String sheetname, int rownum, int celnum, String data) throws EncryptedDocumentException, IOException {
 		
 		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testdata\\TestScriptdata.xlsx");
 		Workbook wb = WorkbookFactory.create(fis);
 		wb.getSheet(sheetname).getRow(rownum).getCell(celnum).setCellValue(data);
 	
 		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\testdata\\TestScriptdata.xlsx");
 		wb.write(fos);
 		wb.close();	
 		}
     
	

}
