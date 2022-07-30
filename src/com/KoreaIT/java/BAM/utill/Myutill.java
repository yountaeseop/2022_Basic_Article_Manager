package com.KoreaIT.java.BAM.utill;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
	// 파일에 내용쓰기
		public static void writeFileContents(String filePath, int data) {
			writeFileContents(filePath, data + "");
		}

		// 첫 문자 소문자화
		public static String lcfirst(String str) {
			String newStr = "";
			newStr += str.charAt(0);
			newStr = newStr.toLowerCase();

			return newStr + str.substring(1);
		}

		// 파일이 존재하는지
		public static boolean isFileExists(String filePath) {
			File f = new File(filePath);
			if (f.isFile()) {
				return true;
			}

			return false;
		}

		// 파일내용 읽어오기
		public static String getFileContents(String filePath) {
			String rs = null;
			try {
				// 바이트 단위로 파일읽기
				FileInputStream fileStream = null; // 파일 스트림

				fileStream = new FileInputStream(filePath);// 파일 스트림 생성
				// 버퍼 선언
				byte[] readBuffer = new byte[fileStream.available()];
				while (fileStream.read(readBuffer) != -1) {
				}

				rs = new String(readBuffer);

				fileStream.close(); // 스트림 닫기
			} catch (Exception e) {
				e.getStackTrace();
			}

			return rs;
		}

		// 파일 쓰기
		public static void writeFileContents(String filePath, String contents) {
			BufferedOutputStream bs = null;
			try {
				bs = new BufferedOutputStream(new FileOutputStream(filePath));
				bs.write(contents.getBytes()); // Byte형으로만 넣을 수 있음
			} catch (Exception e) {
				e.getStackTrace();
			} finally {
				try {
					bs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 	
}
		 


