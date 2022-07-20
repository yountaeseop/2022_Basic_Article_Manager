package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.utill.Myutill;

public class ArticleController {
	
	private Scanner sc;
	private List<Article> articles;
	
	public ArticleController(Scanner sc, List<Article> articles) {
		this.sc = sc;
		this.articles = articles;
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

	public void doWrite() {
		
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

	public void showList(String cmd) {
		
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

	public void showDtail(String cmd) {
		
		String[] cmdBits = cmd.split(" ");

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
	
	

	public void doModify(String cmd) {
		
		String[] cmdBits = cmd.split(" ");

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
	
	public void doDelete(String cmd) {
		
		String[] cmdBits = cmd.split(" ");

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
	
	


}
