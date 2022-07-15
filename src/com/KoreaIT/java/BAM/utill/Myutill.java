package com.KoreaIT.java.BAM.utill;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Myutill {
	 public static String getDate(String dateFormat) {
		 
//		 // 현재 날짜 구하기        
//		 LocalDate now = LocalDate.now();        
//		 // 포맷 정의        
//		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);         
//		 // 포맷 적용        
//		 String formatedNow = now.format(formatter);        
//		 
//		 return formatedNow;
// --------------------------------------------------------------
		 
		 
		 SimpleDateFormat sdf1 = new SimpleDateFormat(dateFormat);

		 Date now = new Date();

		 return sdf1.format(now);
		 
		 
		 }
	 }
		 


