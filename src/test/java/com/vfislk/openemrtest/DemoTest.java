package com.vfislk.openemrtest;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class DemoTest {

	public static void main(String[] args) throws IOException  {
	
		FileInputStream file=new FileInputStream("src/test/resources/testdata/OpenEMRData.xlsx"); //location - read
		
		XSSFWorkbook book=new XSSFWorkbook(file);
		
		
		

		
		
		
	}

}
