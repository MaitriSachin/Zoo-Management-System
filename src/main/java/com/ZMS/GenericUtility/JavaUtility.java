package com.ZMS.GenericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	//1
	public int getRandomNumber()
	{
		Random ran = new Random();
		int ranNum=ran.nextInt(1000);
		return ranNum;
	}
	
	//2
	public String getSystemDate()
	{
		Date date = new Date();
		return date.toString();
	}
	
	//3
	public String getSystemDateFormat()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy hh-MM-ss");
		Date date = new Date();
		String systemDateInFormat = dateFormat.format(date);
		return systemDateInFormat;
		
		/*
		 * Date dateTime=new Date(); String[] d=dateTime.toString().split("");
		 * 
		 * String day=d[0]; String month=d[1]; String date = d[2]; String year=d[5];
		 * 
		 * String finalFormat=day+" "+month+" "+date+" "+year; return finalFormat;
		 */
	}

}
