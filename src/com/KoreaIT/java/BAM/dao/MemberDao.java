package com.KoreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.BAM.dto.Member;

public class MemberDao extends Dao {
	public List<Member> members;
	
	public MemberDao() {
		members = new ArrayList<>();
	}
	
	public void add(Member member) {
		members.add(member);
		lastId++;
	}
	
	public boolean isJoinableLoginId(String memberId) {
		
		int index = getMemberIndexBymemberId(memberId);
		
		if(index == -1) {
			return true;
		}
		
	return false;
}
	
	public Member getMemberByLoginId(String loginId) {
		
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				//System.out.printf("%d님 환영합니다!!!!!\n", member.name);
				return member;
			} 
		}
		
		return null;
	}
	
	public int getMemberIndexBymemberId(String memberId) {
		
		int i = 0;
		
		for(Member member : members) {
			if(member.loginId.equals(memberId)) {
				return i;
			}
			i++;
		}
		
		return -1;
	}

	public List<Member> getMembers() {
		return members;
	}
}
