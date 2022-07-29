package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.container.Container;
import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.service.ArticleService;
import com.KoreaIT.java.BAM.service.MemberService;
import com.KoreaIT.java.BAM.utill.Myutill;

public class MemberController extends Controller{

	private Scanner sc;
	private List<Member> members;
	private String cmd;
	private String actionMethodName;
	private MemberService memberService;
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		
		memberService = Container.memberService;
		//members = new ArrayList<>();
		// 이렇게 했을때, article list를 하면 작성자가 null로 나온다
		// 왜 이런걸까?
	}
	
	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName; 
				
		switch(actionMethodName){
			case"join":
				doJoin();
				break;
			case"login":
				doLogin();
				break;
			case"profile":
				showProfile();
				break;
			case"logout":
				doLogout();
				break;	
			default:
				System.out.println("존재하지 않는 명령어입니다.");
				break;
			
		}
		
	}
	
//	public static boolean isLogined() {
//		return loginedMember != null;
//	}
	
	private void doLogout() {
//		if(isLogined() == false) {
//			System.out.println("로그인 상태가 아닙니다.");
//			return;
//		}
		
		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
		
	}

	private void showProfile() {
		System.out.println("== 현재 로그인 한 회원 정보 ==");
//		if(loginedMember == null) {
//			System.out.println("로그아웃 상태입니다");
//			return;
//		} else {
//			System.out.printf("로그인 아이디 : %s\n", loginedMember.loginId);
//			System.out.printf("이름 : %s\n",loginedMember.name);
//		}
		System.out.printf("로그인 아이디 : %s\n", loginedMember.loginId);
		System.out.printf("이름 : %s\n",loginedMember.name);
	}

	private void doLogin() {
		
		Member member = null;
		String loginPw = null;
		while (true) {
			
			System.out.printf("로그인 아이디 : ");
			String loginId = sc.nextLine();
			
			if (loginId.trim().length() == 0) {
				System.out.println("로그인 아이디를 입력해주세요");
				continue;
			}
			
			while(true) {
				
				System.out.printf("로그인 비밀번호 : ");
				loginPw = sc.nextLine();
				
				if (loginPw.length() == 0) {
					System.out.println("로그인 비밀번호를 입력해주세요");
					continue;
				}
				break;
			}
			
			member = memberService.getMemberByLoginId(loginId);
			
			if(member == null) {
				System.out.println("일치하는 회원이 없습니다.");
				return;
			}
			
			if(member.loginPw.equals(loginPw) == false) {
				System.out.println("비밀번호가 일치하지 않습니다");
				return;
			}
			
			break;
		}
			loginedMember = member;
			System.out.printf("로그인 성공! %s님 환영합니다.\n", loginedMember.name);
	}

	

	private void doJoin() {
		
		int id = memberService.setNewId();
		// 아이디를 정하는 것이 컨트롤러의 역할이 아니기 때문에 Dao에서 가져오는 것임
		
		String regDate = Myutill.getDate("yyyy-MM-dd HH:mm:ss");
		System.out.printf("이름 : ");
		String memberName = sc.nextLine();
		
		String memberId = null;
		
		while(true) {
			System.out.printf("아이디 : ");
			memberId = sc.nextLine();
			
			if(memberService.isJoinableLoginId(memberId) == false) {
				System.out.printf("%s은()는 이미 사용중인 아이디입니다\n", memberId);
				continue;
			} 
				break;
		}
		
		String memberPw = null;   // member인스턴스에서 받을 수 있도록 미리 지정해서
		String memberPwCheck = null; // null을 넣어준다.
		
		while(true) {
			System.out.printf("비밀번호 : ");
			memberPw = sc.nextLine();
			System.out.printf("비밀번호 확인 : ");
			memberPwCheck = sc.nextLine();
			
			if (memberPw.equals(memberPwCheck) == false) {
				System.out.println("비밀번호가 다릅니다!!! 다시 입력해주세요.");
				continue;
			} 
			break;
		}
		
		Member member = new Member(id, regDate, memberId, memberPw, memberName);
		memberService.add(member);

		System.out.printf("%s님 환영합니다!!!\n", member.name);
		
	}

	
	
	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");
		
		String regDate = Myutill.getDate("yyyy-MM-dd HH:mm:ss"); 
		
		memberService.add(new Member(memberService.setNewId(), regDate, "test1", "aa", "홍길동"));
		memberService.add(new Member(memberService.setNewId(), regDate, "test2", "bb", "김철수"));
		memberService.add(new Member(memberService.setNewId(), regDate, "test3", "cc", "임꺽정"));
		
		//여기도 add기능이기 때문에 Dao로 넘겨서 처리해줘야한다!!!
	}
	
}
