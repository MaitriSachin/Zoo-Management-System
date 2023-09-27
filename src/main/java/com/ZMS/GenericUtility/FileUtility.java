package com.ZMS.GenericUtility;

import java.io.FileInputStream;
import java.util.Properties;

import com.ZMS.GenericUtility.Interface.IPathConstants;

public class FileUtility {

	public String getPropertyKeyValue(String key) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstants.propertiesFile);
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}
}
