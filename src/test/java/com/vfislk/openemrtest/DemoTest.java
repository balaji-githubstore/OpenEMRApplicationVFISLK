package com.vfislk.openemrtest;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class DemoTest {
	
	@Test
	public void excelRead() throws IOException  {
	
		FileInputStream file=new FileInputStream("src/test/resources/testdata/OpenEMRData.xlsx"); //location - read
		
		XSSFWorkbook book=new XSSFWorkbook(file); //open
		
		XSSFSheet sheet=  book.getSheet("invalidCredentialTest"); //sheet
		
		XSSFRow row=sheet.getRow(1); //row
		
		XSSFCell cell= row.getCell(0);//cell
		
		DataFormatter format=new DataFormatter();		
		String cellValue = format.formatCellValue(cell);
		
		System.out.println(cellValue);
		
		book.close();
		file.close();
	}

}
