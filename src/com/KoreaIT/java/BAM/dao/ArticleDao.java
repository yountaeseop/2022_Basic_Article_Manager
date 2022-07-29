package com.KoreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;

public class ArticleDao extends Dao {
	private List<Article> articles;
	private Member loginedMember;
	// articles에 밖에서 접근할 수 없도록 private으로 막아둔다.
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}
	
	public void add(Article article) {
		articles.add(article);
		lastId++;
	}

	public List<Article> getArticles(String searchKeyword) {
		if (searchKeyword != null && searchKeyword.length() != 0) {
			
			List<Article> forPrintArticles = new ArrayList<>();
			
			if(searchKeyword.length() > 0) {
				System.out.printf("검색어 : %s\n", searchKeyword);
				for (Article article : articles) {
					if(article.title.contains(searchKeyword)) {
						forPrintArticles.add(article);
					}
				}
					
			}
			return forPrintArticles;
		}
		
		return articles;
	}
	
	public int getArticleIndexByid(int id) {
		
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			
			if (article.id == id) {
				int foundindex = i;
				return foundindex;
			}
		}
		return -1;
	}
	
	public void remove(int foundindex) {
		articles.remove(foundindex);
	}
	
    public boolean isIdEqualLoginedId(int foundindex) {
		
		return articles.get(foundindex).memberId == loginedMember.id;
	}

	public Article getArticleByIndex(int foundindex) {
		return articles.get(foundindex);
	}
	
}

















