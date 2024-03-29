package com.KoreaIT.java.BAM.service;

import java.util.List;

import com.KoreaIT.java.BAM.container.Container;
import com.KoreaIT.java.BAM.dao.ArticleDao;
import com.KoreaIT.java.BAM.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;

	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public List<Article> getForPrintArticles(String searchKeyword) {
		
		List<Article> articles = articleDao.getArticles(searchKeyword);
		
		return articles;
	}

	public int setNewId() {
		
		int id = articleDao.setNewId();
		
		return id;
	}

	public void add(Article article) {
		articleDao.add(article);
	}

	public int getArticleIndexByid(int id) {
		return articleDao.getArticleIndexByid(id);
	}
	
	
	
	public void remove(int foundindex) {
		articleDao.remove(foundindex);
		
	}
	
	public boolean isIdEqualLoginedId(int foundindex) {
		
		return articleDao.isIdEqualLoginedId(foundindex);
	}

	public Article getArticleByIndex(int foundindex) {
		return articleDao.getArticleByIndex(foundindex);
	}

	public List<Article> getForPrintArticles() {
		
		return articleDao.getArticles(null);
	}

	
	// 수정하기전에 원래 제목 내용가져오기 위한 함수
	public String getArticleTitle(int foundindex) {
		
		return articleDao.getArticleTitle(foundindex);
	}

	public String getArticleBody(int foundindex) {
		
		return articleDao.getArticleBody(foundindex);
	}
	
	
	
	
	
}
