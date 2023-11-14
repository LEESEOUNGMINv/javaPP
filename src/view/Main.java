package view;

import Service.AdminImpl;

/*
 * 작성일 : 2023년 07월 10일
 * 작성자 : 이승민
 * 클래스 : 메인 클래스
 */
public class Main {
	public static void main(String[] args) {
		MenuImpl xx = new MenuImpl(); // 로그인
		xx.LoginView();	
		AdminImpl thread = AdminImpl.getInstance(); // 주가변동을 위한 스레드
		thread.start();
	}
}
//수정할때 HashMap.replace()

//CRUD
//Create 생성 Read,Retrieve 조회 Update 갱신 Delete 삭제