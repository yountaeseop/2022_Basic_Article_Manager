package com.KoreaIT.java.BAM.dto;

public class Dto {
// DTO(VO) -> Data Transfer Object
// 데이터 베이스의 테이블(Entity)에 해당하는 객체로 테이블의 컬럼들을 일대일로 저장할 수 있는 필드가 있고, getter/setter 메서드를 갖는다.
//	DTO는 로직이 없다.
//	하나의 DTO가 하나의 행(row)에 해당되고, 대부분 DAO와 같이 사용된다.
//	DAO의 메서드는 DTO를 반환하거나 DTO를 대입한다.
	
	public int id;
	public String regDate;
	
}
