package com.comcast.crm.generic.JsonUtility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {

	public String  getDataFromJSONFile(String key) throws IOException, ParseException {
		
		FileReader fileR = new FileReader("./ConfigAppData/commondata.properties");
		
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fileR);
		JSONObject map = (JSONObject)obj;
		String data=(String)map .get(key);
		
		
		return data;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
