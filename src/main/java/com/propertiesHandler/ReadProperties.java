package com.propertiesHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	//Return the required properties file
		public static Properties readPropertiesFile() {
			
			FileInputStream  file = null;
			
			//define the location path for the properties file
			try {
				String path = System.getProperty("user.dir")+"\\src\\main\\resources\\Xpaths.properties";
				file=new FileInputStream(path);
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			
			Properties property = new Properties();
			
			//Load the file in the properties object.
			try {
				property.load(file);
			} catch(IOException e) {
				e.printStackTrace();
			}
			return property;
		}

}
