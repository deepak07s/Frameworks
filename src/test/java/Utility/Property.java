package Utility;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;



public class Property {
	
	public static String getProperty(String key){
		
		String val=null;
		try {
			
			FileInputStream fis=new FileInputStream("src/test/resources/OR.properties");
			
			Properties prop=new Properties();
			prop.load(fis);
			val=prop.getProperty(key);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not present");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}

}