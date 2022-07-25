package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.utill.Myutill;

public class ArticleController extends Controller {
	
	private Scanner sc;
	private List<Article> articles;
	private String cmd;
	private String actionMethodName;
	
	public ArticleController(Scanner sc) {
		this.sc = sc; 
		
		articles = new ArrayList<>();
	}
	
	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName; 
				
		switch(actionMethodName){
			case"list":
				showList();
				break;
			case"write":
				if(isLogined() == false) {
					System.out.println("로그인 후 이용해주세요");
					break;
				};
				doWrite();
				break;
			case"detail":
				showDetail();
				break;
			case"modify":
				doModify();
				break;
			case"delete":
				doDelete();
				break;
			default:
				System.out.println("존재하지 않는 명령어입니다.");
				break;
		}
	}
	

	private void doWrite() {
		
		
		int id = articles.get(articles.size()-1).id + 1;
		//int id = articles.size() + 1; 바뀌기 전에 id계산 코드
		
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		String regDate = Myutill.getDate("yyyy-MM-dd HH:mm:ss");
		Article article = new Article(id, title, body, regDate, 0);
		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다\n", id);
		
	}

	private void showList() {
		
		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return;
		}
		
		String searchKeyword = cmd.substring("article list".length()).trim();
		
		List<Article> forPrintArticles = articles;
		
		if(searchKeyword.length() > 0) {
			forPrintArticles = new ArrayList<>();
			
			for (Article article : articles) {
				if(article.title.contains(searchKeyword)) {
					forPrintArticles.add(article);
				}
			}
			
			if(forPrintArticles.size() == 0) {
				System.out.println("해당 검색어를 포함하는 게시물이 없습니다");
				return;
			}
			
		}
		
		System.out.printf("번호     |    제목    |      %5s          |   조회\n", "날짜");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);

			System.out.printf("%7d | %6s   | %5s  |%5d\n", article.id, article.title, article.regDate, article.hit);
		}
		
	}

	private void showDetail() {
		
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);

		int foundindex = getArticleIndexByid(id); // 배열안에서 음수인 인덱스가 없기때문에
		 // 구별해줄때 -1로 많이 한다.
		
		if (foundindex == -1) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		} 
		
		Article foundArticle = articles.get(foundindex); 
		foundArticle.increaseHit();
		
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회 : %d\n", foundArticle.hit);
		
	}
	
	

	private void doModify() {
		
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);
		
		int foundindex = getArticleIndexByid(id); // 배열안에서 음수인 인덱스가 없기때문에
		 // 구별해줄때 -1로 많이 한다.
		
		if (foundindex == -1) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		} 
		
		System.out.printf("제목 %s를 무엇으로 변경하시겠습니까? : ", articles.get(foundindex).title);
		String title = sc.nextLine();
		System.out.printf("내용 %s를 무엇으로 변경하시겠습니까? : ", articles.get(foundindex).body);
		String body = sc.nextLine();
		
		Article article = articles.get(foundindex);
		
		article.title = title;
		article.body = body;
		
		System.out.printf("%d번 게시물을 수정했습니다.\n",id);
		
	}
	
	private void doDelete() {
		
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		int foundindex = getArticleIndexByid(id); // 배열안에서 음수인 인덱스가 없기때문에
		 // 구별해줄때 -1로 많이 한다.
		
		if (foundindex == -1) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		}
		
		articles.remove(foundindex);
		System.out.printf("%d번 게시물을 삭제했습니다.\n",id);
		
	}
	
	private int getArticleIndexByid(int id) {
		
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			
			if (article.id == id) {
				int foundindex = i;
				return foundindex;
			}
		}
		return -1;
	}
	
	public void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");
		
		String regDate = Myutill.getDate("yyyy-MM-dd HH:mm:ss"); 
		
		articles.add(new Article(1, "aa", "aa", regDate, 12));
		articles.add(new Article(2, "bb", "bb", regDate, 34));
		articles.add(new Article(3, "cc", "cc", regDate, 5));
		
//		members.add(new Member(1, regDate, "test1", "aa", "test1"));
//		members.add(new Member(2, regDate, "test2", "bb", "test2"));
//		members.add(new Member(3, regDate, "test3", "cc", "test3"));
		
	}

}
