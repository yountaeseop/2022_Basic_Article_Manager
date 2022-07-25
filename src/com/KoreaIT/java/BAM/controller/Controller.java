package com.KoreaIT.java.BAM.controller;

import com.KoreaIT.java.BAM.dto.Member;

public abstract class Controller {
	// 추상메서드를 가진 클래스는 추상 클래스가 돼야한다.
	
	public static Member loginedMember;
	
	public abstract void doAction(String cmd, String actionMethodName); 
	// 추상메서드는 구현부를 가지지 않는다.
	
	public abstract void makeTestData();
	
	public static boolean isLogined() {
		return loginedMember != null;
	}
	
}
