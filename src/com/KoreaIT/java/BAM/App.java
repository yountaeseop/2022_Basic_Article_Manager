package com.KoreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		// 
	}

	public void run() {
		
			System.out.println("==프로그램 시작==");

			Scanner sc = new Scanner(System.in);
			
			makeTestData();

			while (true) {

				System.out.printf("명령어 ) ");
				String cmd = sc.nextLine().trim();
				
				if (cmd.length() == 0) {
					System.out.println("명령어를 입력해주세요");
					continue;
				}
				else if (cmd.equals("exit")) {
					break;
				}
				else if (cmd.equals("article write")) {
					int id = articles.size() + 1;
					
					System.out.printf("제목 : ");
					String title = sc.nextLine();
					System.out.printf("내용 : ");
					String body = sc.nextLine();
					
					String regDate = Myutill.getDate("yyyy-MM-dd HH:mm:ss");
					Article article = new Article(id, title, body, regDate, 0);
					articles.add(article);

					System.out.printf("%d번 글이 생성되었습니다\n", id);
					
				} else if (cmd.startsWith("article list")) {
					
					if (articles.size() == 0) {
						System.out.println("게시물이 없습니다");
						continue;
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
							continue;
						}
						
					}
					
					System.out.printf("번호     |    제목    |      %5s          |   조회\n", "날짜");
					for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
						Article article = forPrintArticles.get(i);

						System.out.printf("%7d | %6s   | %5s  |%5d\n", article.id, article.title, article.regDate, article.hit);
					}

				} else if (cmd.startsWith("article detail ")) {

					String[] cmdBits = cmd.split(" ");

					int id = Integer.parseInt(cmdBits[2]);

					int foundindex = getArticleIndexByid(id); // 배열안에서 음수인 인덱스가 없기때문에
					 // 구별해줄때 -1로 많이 한다.
					
					if (foundindex == -1) {
						System.out.printf("%d번 게시물은 없습니다\n", id);
						continue;
					} 
					
					Article foundArticle = articles.get(foundindex); 
					foundArticle.increaseHit();
					
					System.out.printf("번호 : %d\n", foundArticle.id);
					System.out.printf("날짜 : %s\n", foundArticle.regDate);
					System.out.printf("제목 : %s\n", foundArticle.title);
					System.out.printf("내용 : %s\n", foundArticle.body);
					System.out.printf("조회 : %d\n", foundArticle.hit);
					

				} else if (cmd.startsWith("article delete ")) {

					String[] cmdBits = cmd.split(" ");

					int id = Integer.parseInt(cmdBits[2]);

					int foundindex = getArticleIndexByid(id); // 배열안에서 음수인 인덱스가 없기때문에
					 // 구별해줄때 -1로 많이 한다.
					
					if (foundindex == -1) {
						System.out.printf("%d번 게시물은 없습니다\n", id);
						continue;
					}
					
					articles.remove(foundindex);
					System.out.printf("%d번 게시물을 삭제했습니다.\n",id);
					
				} else if (cmd.startsWith("article modify ")) {

					String[] cmdBits = cmd.split(" ");

					int id = Integer.parseInt(cmdBits[2]);
					
					int foundindex = getArticleIndexByid(id); // 배열안에서 음수인 인덱스가 없기때문에
					 // 구별해줄때 -1로 많이 한다.
					
					if (foundindex == -1) {
						System.out.printf("%d번 게시물은 없습니다\n", id);
						continue;
					} 
					
					System.out.printf("제목 %s를 무엇으로 변경하시겠습니까? : ", articles.get(foundindex).title);
					String title = sc.nextLine();
					System.out.printf("내용 %s를 무엇으로 변경하시겠습니까? : ", articles.get(foundindex).body);
					String body = sc.nextLine();
					
					Article article = articles.get(foundindex);
					
					article.title = title;
					article.body = body;
					
					System.out.printf("%d번 게시물을 수정했습니다.\n",id);
					
				} else if (cmd.equals("member join")) {
					int id = members.size() + 1;
					
					System.out.printf("이름 : ");
					String memberName = sc.nextLine();
					System.out.printf("아이디 : ");
					String memberId = sc.nextLine();
					System.out.printf("비밀번호 : ");
					String memberPw = sc.nextLine();
					
					while(true) {
						System.out.printf("비밀번호 확인 : ");
						String memberPwCheck = sc.nextLine();
						
						if (memberPw.equals(memberPwCheck)) {
							break;
						} else {
							System.out.println("비밀번호가 다릅니다!!! 다시 입력해주세요.");
						}
					}
					
					
					Member member = new Member(memberId, memberPw, memberName);
					members.add(member);

					System.out.printf("%s님 환영합니다!!!\n", member.name);
					
				}
				else {
					System.out.println("존재하지 않는 명령어입니다");
				}
			}

			System.out.println("==프로그램 끝==");
			sc.close();
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

		private static void makeTestData() {
			System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");
			
			articles.add(new Article(1, "aa", "aa", Myutill.getDate("yyyy-MM-dd HH:mm:ss"), 12));
			articles.add(new Article(2, "bb", "bb", Myutill.getDate("yyyy-MM-dd HH:mm:ss"), 34));
			articles.add(new Article(3, "cc", "cc", Myutill.getDate("yyyy-MM-dd HH:mm:ss"), 5));
			
		}
	
	}
	
	
