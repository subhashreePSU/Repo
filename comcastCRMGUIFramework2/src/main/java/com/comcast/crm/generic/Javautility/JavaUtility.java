package com.comcast.crm.generic.Javautility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
 
	public int getRandomNumber() {
		
		Random ranDom = new Random();
		int randomNumber = ranDom.nextInt(2000);
		return randomNumber;
		
	}
	
	
	public String getSystemDateYYYYDDMM() {
		Date dateobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(dateobj);
		return date;
		
	}
	
	public String getSystemDateYYYYDDMM(int days) {
		
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
	    String ReguireDate = sim.format(cal.getTime());
		return ReguireDate;
		
	}
	
	
	
	
	
	
	
	
	
}
