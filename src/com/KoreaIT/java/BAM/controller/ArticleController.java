package com.KoreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.container.Container;
import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.service.ArticleService;
import com.KoreaIT.java.BAM.service.MemberService;
import com.KoreaIT.java.BAM.utill.Myutill;

public class ArticleController extends Controller {
	
	private Scanner sc;
	private List<Article> articles;
	private String cmd;
	private String actionMethodName;
	private ArticleService articleService;
	private MemberService memberService;
	
	public ArticleController(Scanner sc) {
		this.sc = sc; 
		
		articleService = Container.articleService;
		memberService = Container.memberService;
	}
	
	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName; 
				
		switch(actionMethodName){
			case"list":
				showList();
				break;
			case"write":
//				if(isLogined() == false) {
//					System.out.println("로그인 후 이용해주세요");
//					break;
//				};
				doWrite();
				break;
			case"detail":
				showDetail();
				break;
			case"modify":
//				if(isLogined() == false) {
//					System.out.println("로그인 후 이용해주세요");
//					break;
//				};
				doModify();
				break;
			case"delete":
//				if(isLogined() == false) {
//					System.out.println("로그인 후 이용해주세요");
//					break;
//				};
				doDelete();
				break;
			default:
				System.out.println("존재하지 않는 명령어입니다.");
				break;
		}
	}
	

	

	private void doWrite() {
		
		int id = articleService.setNewId();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		String regDate = Myutill.getDate("yyyy-MM-dd HH:mm:ss");
		Article article = new Article(id, loginedMember.id, loginedMember.name, title, body, regDate, 0);
		articleService.add(article);  
		//add는 Controller의 일이 아니고 Dao의 일이기 때문에 Dao를 불러온 것임.
		
		System.out.printf("%d번 글이 생성되었습니다\n", id);
		
	}

	private void showList() {
		
		String searchKeyword = cmd.substring("article list".length()).trim();
		
		List<Article> forPrintArticles = Container.articleService.getForPrintArticles(searchKeyword);
		
		if (forPrintArticles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return;
		}
		
		System.out.printf("번호     |     작성자     |     제목    |      %5s      |   조회\n", "날짜");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);
			
			String writerName = null;
			
			List<Member> members = memberService.getMembers();
			
			for (Member member : members) {
				if(article.memberId == member.id) {
					writerName = member.name;
					break;
				}
			}

			System.out.printf("%7d | %6s   | %5s  |  %7s |%5d\n", article.id, writerName, article.title, article.regDate, article.hit);
		}
		
	}

	private void showDetail() {
		
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);

		int foundindex = articleService.getArticleIndexByid(id); // 배열안에서 음수인 인덱스가 없기때문에
		 // 구별해줄때 -1로 많이 한다.
		
		if (foundindex == -1) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		}
		
		String writerName = null;
		
		List<Member> members = memberService.getMembers();
		Article foundArticle = articleService.getArticleByIndex(foundindex); 
		
		for (Member member : members) {
			if(foundArticle.memberId == member.id) {
				writerName = member.name;
				break;
			}
		}
		
		foundArticle.increaseHit();
		
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("작성자 : %s\n", writerName);
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
		
		int foundindex = articleService.getArticleIndexByid(id); // 배열안에서 음수인 인덱스가 없기때문에
		 // 구별해줄때 -1로 많이 한다.
		
		if(articleService.isIdEqualLoginedId(foundindex) == false) {
			System.out.println("작성자가 아니면 접근할 수 없습니다.");
			return;
		};
		
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

		int foundindex = articleService.getArticleIndexByid(id); // 배열안에서 음수인 인덱스가 없기때문에
		 // 구별해줄때 -1로 많이 한다.
		
		if(isIdEqualLoginedId(foundindex) == false) {
			System.out.println("작성자가 아니면 접근할 수 없습니다.");
			return;
		};
		
		if (foundindex == -1) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		}
		
		articleService.remove(foundindex);
		System.out.printf("%d번 게시물을 삭제했습니다.\n",id);
		
	}
	
	
	
	private boolean isIdEqualLoginedId(int foundindex) {
		// TODO Auto-generated method stub
		return false;
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");
		
		String regDate = Myutill.getDate("yyyy-MM-dd HH:mm:ss"); 
		
		articleService.add(new Article(articleService.setNewId(), 1, "홍길동", "aa", "aa", regDate, 12));
		articleService.add(new Article(articleService.setNewId(), 2, "임꺽정","bb", "bb", regDate, 34));
		articleService.add(new Article(articleService.setNewId(), 3, "곽두팔","cc", "cc", regDate, 5));
		
//		members.add(new Member(1, regDate, "test1", "aa", "test1"));
//		members.add(new Member(2, regDate, "test2", "bb", "test2"));
//		members.add(new Member(3, regDate, "test3", "cc", "test3"));
		
	}

}
