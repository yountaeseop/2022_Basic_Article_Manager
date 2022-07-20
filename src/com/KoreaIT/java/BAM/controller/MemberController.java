package com.KoreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.utill.Myutill;

public class MemberController{

	private Scanner sc;
	private List<Member> members;
	
	public MemberController(Scanner sc, List<Member> members) {
		super();
		this.sc = sc;
		this.members = members;
	}

	public void doJoin() {
		
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
	
}
