package model;

//메뉴코드
public interface Code {
	//상수 
	static final int SHOP_LOGIN = 999; // 로그인
	static final int EXIT = 4; //시스템 종료
	
	//Admin
	static final int ADMIN_MENU = 100; //관리자
	static final int ADMIN_MENU_LOGIN = 199; //관리자
	
	//Admin-재고관리
	static final int ADMIN_STOCK_MENU = 110; //재고관리
	static final int ADMIN_GOODS_LIST = 111; //상품목록
	static final int ADMIN_GOODS_ADD = 112; //상품추가
	static final int ADMIN_GOODS_UPDATE = 113; //상품수정
	static final int ADMIN_GOODS_DEL= 114; //상품삭제
	
	//Admin-주문관리
	static final int ADMIN_ORDER_MENU = 120; //주문관리
	static final int ADMIN_ORDER_LIST = 121; //주문목록
	
	//Admin-결재관리
	static final int ADMIN_ORDER_CONFIRM = 122; //구매
	static final int ADMIN_ORDER_CANCEL = 123; //취소
	
	//Admin-결산
	static final int ADMIN_ORDER_TOTAL = 130; //결산
	
	//////////////////////////////////////////////////////////
	
	// Guest
	static final int GUEST_MENU = 200; // 고객
	static final int GUEST_JOIN = 298; // 고객 회원가입
	static final int GUEST_LOGIN = 299; // 고객 로그인
	
	// Guest 상품목록
	static final int GUEST_GOODS = 210; // 상품목록
	
	// Guest 장바구니
	static final int CART_LIST = 220; // 장바구니 목록
	static final int CART_ADD = 221; // 장바구니 추가
	static final int CART_DEL = 222; // 장바구니 삭제
	static final int CART_BUY = 223; // 장바구니 구매
	
	// Guest 구매 및 환불
	static final int GUEST_NOW_BUY = 230; // 바로구매
	static final int GUEST_REFUND = 240; // 환불

	
}
