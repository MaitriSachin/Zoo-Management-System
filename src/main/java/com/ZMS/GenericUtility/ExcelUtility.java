package com.ZMS.GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ZMS.GenericUtility.Interface.IPathConstants;
import com.google.common.collect.Multiset.Entry;

public class ExcelUtility {

	//1
	public String readDataFromExcelSheet(String sheet, int rowNum, int cellNum) throws Throwable
	{
		Workbook wb = WorkbookFactory.create(new FileInputStream(IPathConstants.excelFile));
		Sheet sh=wb.getSheet(sheet);
		Row row = sh.getRow(rowNum);
		org.apache.poi.ss.usermodel.Cell cell = row.getCell(cellNum);
		String data=cell.toString();
		return data;
	}
	
	//2
	public void writeDataToExcel(String sheet, int rowNum, int cellNum,String value) throws Throwable
	{
		Workbook wb = WorkbookFactory.create(new FileInputStream(IPathConstants.excelFile));
		Sheet sh=wb.getSheet(sheet);
		Row row = sh.getRow(rowNum);
		org.apache.poi.ss.usermodel.Cell cell = row.getCell(cellNum);
		cell.setCellValue(value);
		
		FileOutputStream fout = new FileOutputStream(IPathConstants.excelFile);
		wb.write(fout);
		wb.close();
	}
	
	//3
	public int getRowCount(String sheet, int rowNum) throws Throwable
	{
		Workbook wb = WorkbookFactory.create(new FileInputStream(IPathConstants.excelFile));
		Sheet sh=wb.getSheet(sheet);
		int row=sh.getLastRowNum();
		return row;
	}
	
	//4
	public HashMap<String, String> getMultipleDataFromExcel(String SheetName, int keyNo, int valueNo, WebDriver driver, JavaUtility jLib) throws Throwable
	{
		//fetching data from excel
		FileInputStream fis= new FileInputStream(IPathConstants.excelFile);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(SheetName);
		int count=sh.getLastRowNum();
		
		HashMap<String, String> map=new HashMap<String, String>();  //empty map
		
		for(int i=1;i<=count; i++)
		{
			String key=sh.getRow(i).getCell(keyNo).getStringCellValue();
			String value=sh.getRow(i).getCell(valueNo).getStringCellValue();
			map.put(key, value);
		}
		for(java.util.Map.Entry<String, String> s:map.entrySet())
		{
			if(s.getKey().contains(SheetName)) 
			{
				driver.findElement(By.name(s.getKey())).sendKeys(s.getValue()+jLib.getRandomNumber());
			}
			else
			{
				driver.findElement(By.name(s.getKey())).sendKeys(s.getValue());
			}
		}
		return map;
	}
	
}
	