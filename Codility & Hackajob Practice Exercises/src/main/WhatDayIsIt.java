package main;

import java.util.*;
import java.text.*;

public class WhatDayIsIt {
	
	public static void main(String[] args){
		System.out.println(getDays("29-02"));
	}
	
	public static String getDays(String inputDate){
		String futureDates = "";
		
		List<String> dates = new ArrayList<>();
		
		String temp = "";
		
		if (!inputDate.equals("29-02")){
			for (int i=2016; i<2066; i++){
				temp = inputDate + "-" + i;
				dates.add(temp);
			}
		} else {
			for (int i=2016; i<2066; i+=4){
				temp = inputDate + "-" + i;
				dates.add(temp);
			}
		}
		
		// Calendar c = Calendar.getInstance();

		for (String dt : dates){
			SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
			
			try {
				Date date1 = format1.parse(dt);
				DateFormat format2 = new SimpleDateFormat("EE");
				String day = format2.format(date1);
				// System.out.println(day);
				
				if (day.equals("Sat") || day.equals("Sun") || day.equals("Fri")){
					futureDates = futureDates + day + "-" + dt.substring(6) + " ";
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return futureDates;
	}

}
