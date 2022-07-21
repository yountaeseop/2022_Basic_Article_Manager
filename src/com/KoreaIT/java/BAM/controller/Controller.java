package com.KoreaIT.java.BAM.controller;

public abstract class Controller {
	// 추상메서드를 가진 클래스는 추상 클래스가 돼야한다.
	
	public abstract void doAction(String cmd, String actionMethodName); 
	// 추상메서드는 구현부를 가지지 않는다.
	
}
