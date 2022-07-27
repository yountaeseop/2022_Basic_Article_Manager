package com.KoreaIT.java.BAM.dao;

public abstract class Dao {
	//DAO(Data Access Object): 데이터 접근 객체
	//데이터 베이스에 관련된 작업(CRUD - Create, Retrive, Update, Delete *SQL DML+Select)을 전문적으로 담당하는 객체이다.
	//DAO 안의 메서드를 모두 데이터 베이스와 관련된 작업을 한다.
	//CRUD를 실행하는 메서드는 JDBC등을 이용하여 데이터 베이스에 접근해서 쿼리를 실행한다.

	protected int lastId;
	// 캡슐화의 개념 외부에서 이 변수에 접근하지 못하도록.
	// 부모, 자식끼리만 알 수 있도록
 	
	Dao(){
		lastId = 0;
	}
	
	public int getLastId() {
		return lastId;
	}
	
	public int setNewId() {
		return lastId + 1;
	}
	
}
