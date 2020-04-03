package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.IFactoryAnnotation;

import keywordFramework.Constants;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Property {

	public static String getProperty(String key) {

		String val = null;
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/OR.properties");
			Properties prop = new Properties();
			prop.load(fis);
			val = prop.getProperty(key);
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

	public static String[] readJSON(String key) throws IOException, ParseException {

		JSONParser jp = new JSONParser();
		FileReader fReader = new FileReader("src/test/java/ExpectedResult/ExpectedHelpTopics.json");

		Object obj = jp.parse(fReader);

		JSONObject myObj = (JSONObject) obj;
		String[] values = null;
		if (myObj.containsKey(key)) {
			JSONArray myArr = (JSONArray) myObj.get(key);
			values = new String[myArr.size()];
			for (int i = 0; i < myArr.size(); i++) {
				values[i] = (String) myArr.get(i);
			}
		} else {
			values = new String[1];
			values[0] = "NOT PRESENT";
		}
		return values;
	}

	public static void captureScreenshot() throws IOException {
		Screenshot screenshot = new AShot().takeScreenshot(Constants.driver);
		String fName = Property.getCuttentDateTime();
		ImageIO.write(screenshot.getImage(), "jpg", new File("Screenshot/" + fName + ".jpg"));
	}

	public static String getCuttentDateTime() {

		Constants.dateFormat = new SimpleDateFormat("MM-dd-HH-mm-ss");
		Date date = new Date();
		String dateTime = Constants.dateFormat.format(date);
		return dateTime;
	}

}