package com.KoreaIT.java.BAM.container;

import com.KoreaIT.java.BAM.dao.ArticleDao;
import com.KoreaIT.java.BAM.dao.MemberDao;
import com.KoreaIT.java.BAM.service.ArticleService;
import com.KoreaIT.java.BAM.service.MemberService;

public class Container {
	
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		articleService = new ArticleService();
		memberService = new MemberService();
	}
	// static 초기화 블럭 이거 왜 하는거지....
}
