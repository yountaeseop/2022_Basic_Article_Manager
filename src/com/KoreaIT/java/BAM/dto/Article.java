package com.KoreaIT.java.BAM.dto;


// 이런 식의 데이터들을 dto라고 부른다, 또는 vo라고 하기도 함 
public class Article extends Dto{
	
	public String title;
	public String body;
	public int hit;
	public int memberId;
	public String memberName; //
	
	
	
	public Article(int id, int memberId, String memberName, String title, String body, String regDate) {
 		this(id, memberId, memberName, title, body, regDate, 0);	
 		// 생성자 중복을 편하게 하기 위해서 있는 기능
 		// 이렇게만 해놓아도 밑에 있는 생성자에 작업을 떠넘겨서 실행된다.
//		this.id = id;
//		this.title = title;
//		this.body = body;
//		this.regDate = regDate;
//		this.hit = 0; 
	}
	
	public Article(int id, int memberId, String memberName, String title, String body, String regDate, int hit) {
		super();
		this.id = id;
		this.title = title;
		this.memberName = memberName;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
		this.memberId = memberId;
	} // 테스트 데이터들을 위한 생성자 오버로딩!!!!!
	  // 매개변수를 각각 다르게 받기 위해서 만들어준 것이다!!!
	
	
	public void increaseHit() {
		hit++;
	}
}