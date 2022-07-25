package com.KoreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.controller.ArticleController;
import com.KoreaIT.java.BAM.controller.Controller;
import com.KoreaIT.java.BAM.controller.MemberController;
import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.utill.Myutill;

public class App {
	
	public App() {
		
	}

	public void run() {
		
			System.out.println("==프로그램 시작==");

			Scanner sc = new Scanner(System.in);
			
			ArticleController articleController = new ArticleController(sc);
			MemberController memberController = new MemberController(sc);
			
			articleController.makeTestData();
			memberController.makeTestData();
			

			while (true) {

				System.out.printf("명령어 ) ");
				String cmd = sc.nextLine().trim();
				
				if (cmd.length() == 0) {
					System.out.println("명령어를 입력해주세요");
					continue;
				}
				if (cmd.equals("exit")) {
					break;
				}
				
				String[] cmdBits = cmd.split(" "); //article detail
				
				if (cmdBits.length == 1) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}
				
				String controllerName = cmdBits[0]; // article
				String actionMethodName = cmdBits[1]; // detail
				
				Controller controller = null;
				
				if(controllerName.equals("article")) {
					controller = articleController;
				} else if(controllerName.equals("member")) {
					controller = memberController;
				} else {
					System.out.println("존재하지 않는 명령어입니다.");
					continue;
				}
				
				String actionName = controllerName + "/" + actionMethodName;
				switch (actionName) {
				case "article / write" :
				case "article / delete" :
				case "article / modify" :
				case "member / profile" :
				case "member / logout" :
					if(Controller.isLogined() == false) {
						System.out.println("로그인 후 이용해주세요");
						continue;
					}
					break;
				}
				
				switch (actionName) {
				case "member / login":
				case "member / join":
					if(Controller.isLogined()) {
						System.out.println("로그아웃 후 이용해주세요");
						continue;
					}
					break;
				}
				
				controller.doAction(cmd, actionMethodName);
				
//				if (cmd.equals("article write")) {
//					articleController.doWrite();
//				} else if (cmd.startsWith("article list")) {
//					articleController.showList(cmd);
//				} else if (cmd.startsWith("article detail ")) {
//					articleController.showDtail(cmd);
//				} else if (cmd.startsWith("article delete ")) {
//					articleController.doDelete(cmd);
//				} else if (cmd.startsWith("article modify ")) {
//					articleController.doModify(cmd);
//				} else if (cmd.equals("member join")) {
//					memberController.doJoin();
//				} else if(cmd.equals("member login")) {
//				//	memberController.dologin();
//				} else {
//					System.out.println("존재하지 않는 명령어입니다");
//				}
			}

			System.out.println("==프로그램 끝==");
			sc.close();
		}
	
	}
	
	
