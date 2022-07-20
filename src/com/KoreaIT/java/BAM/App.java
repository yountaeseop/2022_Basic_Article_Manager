package com.KoreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.controller.ArticleController;
import com.KoreaIT.java.BAM.controller.MemberController;
import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.utill.Myutill;

public class App {
	
	private static List<Article> articles = new ArrayList<>();
	private static List<Member> members = new ArrayList<>();
	
	public App(List<Article> articles) {
		this.articles = articles;
	}

	public App() {
		// main함수 실행을 위한 생성자
	}

	public void run() {
			
		
			System.out.println("==프로그램 시작==");

			Scanner sc = new Scanner(System.in);
			
			ArticleController articleController = new ArticleController(sc, articles);
			MemberController memberController = new MemberController(sc, members);
			
			makeTestData();

			while (true) {

				System.out.printf("명령어 ) ");
				String cmd = sc.nextLine().trim();
				
				if (cmd.length() == 0) {
					System.out.println("명령어를 입력해주세요");
					continue;
				}
				if (cmd.equals("exit")) {
					break;
				}else if (cmd.equals("article write")) {
					articleController.doWrite();
				} else if (cmd.startsWith("article list")) {
					articleController.showList(cmd);
				} else if (cmd.startsWith("article detail ")) {
					articleController.showDtail(cmd);
				} else if (cmd.startsWith("article delete ")) {
					articleController.doDelete(cmd);
				} else if (cmd.startsWith("article modify ")) {
					articleController.doModify(cmd);
				} else if (cmd.equals("member join")) {
					memberController.doJoin();
				} else if(cmd.equals("member login")) {
				//	memberController.dologin();
				} else {
					System.out.println("존재하지 않는 명령어입니다");
				}
			}

			System.out.println("==프로그램 끝==");
			sc.close();
		}

		

		private static void makeTestData() {
			System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");
			
			String regDate = Myutill.getDate("yyyy-MM-dd HH:mm:ss"); 
			
			articles.add(new Article(1, "aa", "aa", regDate, 12));
			articles.add(new Article(2, "bb", "bb", regDate, 34));
			articles.add(new Article(3, "cc", "cc", regDate, 5));
			
			members.add(new Member(1, regDate, "test1", "aa", "test1"));
			members.add(new Member(2, regDate, "test2", "bb", "test2"));
			members.add(new Member(3, regDate, "test3", "cc", "test3"));
			
		}
	
	}
	
	
