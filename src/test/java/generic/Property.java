package generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {

	public static String getProperty(String path, String key) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String value = prop.getProperty(key);
		return value;
		
	}
}
