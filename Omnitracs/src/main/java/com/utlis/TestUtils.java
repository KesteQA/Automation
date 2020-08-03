package com.utlis;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.StaleElementReferenceException;

import com.base.BasePage;
import com.constants.Constants;

public class TestUtils {


	public static FileInputStream fs;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static List<String> testCases= new ArrayList<String>();
	public static List<String> runStatus= new ArrayList<String>();
	public static List<String> testDescription= new ArrayList<String>();

	public static HashMap<Integer,String> rowAndTestCaseMap=new HashMap<Integer,String>();

static String TestDataLocation = Constants.TESTCASES_PATH;
static String sheetName = Constants.TESTCASESSHEETNAME;


	/*
	 * Reads the data from the excel sheet and store the values in respective lists which will be used in annotation transformer class
	 */

	public static void getRunStatus() throws Exception {
		try {
			fs=new FileInputStream(TestDataLocation);
			workbook=new XSSFWorkbook(fs);
			
			sheet=workbook.getSheet(sheetName);
			for(int i=1;i<=getLastRowNum();i++) {
				testCases.add(getCellContent(i, "TestCaseName"));
				//testDescription.add(getCellContent(sheetName, i, "Test Case Description"));
				runStatus.add(getCellContent(i, "RunMode"));
			}
		
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

	}


	/*
	 * Takes rowname and sheetname as parameter
	 * return row number based of rowname
	 */
	public static int getRowNumForRowName(String rowName) {
		int rownum=0;
		sheet=workbook.getSheet(sheetName);
		for(int i=1;i<=getLastRowNum();i++) {
			if(rowName.equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue())) {
				rownum=i;
				break;
			}
		}

		return rownum;
	}

	/*
	 * Takes columnname and sheetname as parameter
	 * return column number based of columnheader
	 */

	public static int getColumnNumForColumnName(String columnname) {
		int colnum=0;
		sheet=workbook.getSheet(sheetName);
		for(int i=0;i<getLastColumnNum(0);i++) {
			if(columnname.equalsIgnoreCase(sheet.getRow(0).getCell(i).getStringCellValue())) {
				colnum=i;
				break;
			}
		}

		return colnum;

	}


	/*
	 * Takes sheetname as parameter
	 * return last row number of the sheet
	 */
	public static int getLastRowNum() {
		return workbook.getSheet(sheetName).getLastRowNum();
	}

	/*
	 * Takes sheetname, row number as parameter
	 * return last cell number of the row
	 */
	public static int getLastColumnNum(int rownum) {
		return workbook.getSheet(sheetName).getRow(rownum).getLastCellNum();
	}


	/*
	 * Takes sheetname, row number, column number as parameter
	 * return cell value
	 */
	public static String getCellContent(int rownum,int colnum) {
		sheet=workbook.getSheet(sheetName);
		return sheet.getRow(rownum).getCell(colnum).getStringCellValue().concat("").toString();

		
	}

	/*
	 * Takes sheetname, row number, column name as parameter
	 * return cell value
	 */
	public static String getCellContent(int rownum,String columnname) {
		sheet=workbook.getSheet(sheetName);
		return sheet.getRow(rownum).getCell(getColumnNumForColumnName(columnname)).getStringCellValue().concat("").toString();

	}

	/*
	 * Takes sheetname, row name, column name as parameter
	 * return cell value
	 */
	public static String getCellContent(String rowname,String columnname) {
		sheet=workbook.getSheet(sheetName);
		int rownum=getRowNumForRowName(rowname);
		int colnum=getColumnNumForColumnName(columnname);
		return sheet.getRow(rownum).getCell(colnum).getStringCellValue().concat("").toString();

	}



	



public static String getRandomString(int n) 
{ 
	  
    // chose a Character random from this String 
    String AlphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                + "abcdefghijklmnopqrstuvxyz"; 

   
    StringBuilder sb = new StringBuilder(n); 

    for (int i = 0; i < n; i++) { 

       
        int index 
            = (int)(AlphaString.length() 
                    * Math.random()); 

      
        sb.append(AlphaString 
                      .charAt(index)); 
    } 

    return sb.toString(); 
} 
	
	/*
	 * public static int generateRandomDigits(int n) { int m = (int) Math.pow(10, n
	 * - 1); num=m + new Random().nextInt(9 * m); return num.toString();
	 * 
	 * }
	 */
public static String getRandomNumber(int n) 
{ 
	  
    // chose a Character random from this String 
    String AlphaString = "0123456789"; 

   
    StringBuilder sb = new StringBuilder(n); 

    for (int i = 0; i < n; i++) { 

       
        int index 
            = (int)(AlphaString.length() 
                    * Math.random()); 
  
        
        sb.append(AlphaString 
                      .charAt(index)); 
    } 

    return sb.toString(); 
} 



}
