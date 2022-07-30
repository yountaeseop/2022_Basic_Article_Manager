package com.KoreaIT.java.BAM.controller;

import java.util.Scanner;

import com.KoreaIT.java.BAM.container.Container;
import com.KoreaIT.java.BAM.service.ExportService;

public class ExportController extends Controller {
	
	private Scanner sc;
	private String command;
	private String actionMethodName;
	public ExportService exportService;
	
	public ExportController(Scanner sc) {
		this.sc = sc;
		exportService = Container.exportService;
	}

	@Override
	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;
		
		switch (actionMethodName) {
		case "html":
			doHtml();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
		
	}

	@Override
	public void makeTestData() {
		
		
	}
	
	private void doHtml() {
		System.out.println("== html 생성을 시작합니다. ==");
		exportService.makeHtml();
	}
	
	// 추상 클래스를 상속받은 클래스는 추상 클래스가 갖고 있는 
	// 추상 메소드를 반드시 구현(Overriding)해야 한다.
}
