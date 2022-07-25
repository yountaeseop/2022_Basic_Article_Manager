package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.utill.Myutill;

public class MemberController extends Controller{

	private Scanner sc;
	private List<Member> members;
	private String cmd;
	private String actionMethodName;
	
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		
		members = new ArrayList<>();
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
		
//		if(isLogined()) {
//			System.out.println("이미 로그인 상태입니다.");
//			return;
//		}
		
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();
		
		Member member = getMemberByLoginId(loginId);
		
		if(member == null) {
			System.out.println("일치하는 회원이 없습니다.");
			return;
		}
		
		if(member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 다시 입력해주세요");
			return;
		}
		
		loginedMember = member;
		System.out.printf("로그인 성공! %s님 환영합니다.\n", loginedMember.name);
		
	}

	private Member getMemberByLoginId(String loginId) {
		
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				//System.out.printf("%d님 환영합니다!!!!!\n", member.name);
				return member;
			} 
		}
		
		return null;
	}

	private void doJoin() {
		
		int id = members.size() + 1;
		String regDate = Myutill.getDate("yyyy-MM-dd HH:mm:ss");
		System.out.printf("이름 : ");
		String memberName = sc.nextLine();
		
		String memberId = null;
		
		while(true) {
			System.out.printf("아이디 : ");
			memberId = sc.nextLine();
			
			if(isJoinableLoginId(memberId) == false) {
				System.out.printf("%s은()는 이미 사용중인 아이디입니다\n", memberId);
				continue;
			} else {
				break;
			}
		}
		
		String memberPw = null;   // member인스턴스에서 받을 수 있도록 미리 지정해서
		String memberPwCheck = null; // null을 넣어준다.
		
		while(true) {
			System.out.printf("비밀번호 : ");
			memberPw = sc.nextLine();
			System.out.printf("비밀번호 확인 : ");
			memberPwCheck = sc.nextLine();
			
			if (memberPw.equals(memberPwCheck)) {
				break;
			} else {
				System.out.println("비밀번호가 다릅니다!!! 다시 입력해주세요.");
			}
		}
		
		
		Member member = new Member(id, regDate, memberId, memberPw, memberName);
		members.add(member);

		System.out.printf("%s님 환영합니다!!!\n", member.name);
		
	}

	private boolean isJoinableLoginId(String memberId) {
		
		int index = getMemberIndexBymemberId(memberId);
		
		if(index == -1) {
			return true;
		}
		
	return false;
}

	private int getMemberIndexBymemberId(String memberId) {
		
		int i = 0;
		
		for(Member member : members) {
			if(member.loginId.equals(memberId)) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");
		
		String regDate = Myutill.getDate("yyyy-MM-dd HH:mm:ss"); 
		
		members.add(new Member(1, regDate, "test1", "aa", "홍길동"));
		members.add(new Member(2, regDate, "test2", "bb", "김철수"));
		members.add(new Member(3, regDate, "test3", "cc", "임꺽정"));
		
	}
	
}
