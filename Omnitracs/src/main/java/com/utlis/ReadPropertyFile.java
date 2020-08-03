package com.utlis;

import java.io.FileInputStream;
import java.util.Properties;

import com.constants.Constants;

public class ReadPropertyFile {
	
	public static String get(String propertyname) {
	    String propertyFileLocation = Constants.PropertiesFilePATH;	
		String returnproperty=null;
		Properties property = new Properties();
		try {
			FileInputStream file = new FileInputStream(propertyFileLocation);
			property.load(file);
			returnproperty = property.getProperty(propertyname);
			if(returnproperty==null) {
				throw new Exception("Property named "+propertyname+ "not found");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnproperty;
		
	}
	
}
