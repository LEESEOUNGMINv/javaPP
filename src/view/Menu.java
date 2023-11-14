package view;
/*
 * 작성일 : 2023년 07월 10일
 * 작성자 : 이승민
 * 클래스 : 메뉴 인터페이스
 */
public interface Menu {
	//메뉴 목록

	//로그인
	//1.고객		2.관리자		3.회원가입		4.종료
	public void LoginView();
	
	//관리자 메뉴
	//1.재고관리		2.주문관리		3.로그아웃
	public void AdminLogin();
	
	//관리자 메뉴 = 재고관리
	//1.목록		2.추가		3.수정		4.삭제		5.이전
	public void AdminStockMenu();

	//관리자 메뉴 = 주문관리
	//1.주문목록		2.결제승인		3.결제취소		4.결산		5.이전
	public void AdminOrderMenu();
	
	//고객 메뉴
	//1.구매		2.환불		3.장바구니		4.로그아웃
	public void GuestMenu();
	
	//고객 메뉴 = 장바구니
	//1.추가		2.삭제		3.구매		4.이전
	public void GuestCartList();
}


