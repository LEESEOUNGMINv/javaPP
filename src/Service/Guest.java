package Service;
/*
 * 작성일 : 2023년 07월 10일
 * 작성자 : 이승민
 * 클래스 : 게스트 작업명세서 
 */
public interface Guest {
	//Guest - 장바구니
	public void cartList();			// 장바구니 리스트
	public void cartAdd();			// 장바구니 담기
	public void cartDel();			// 장바구니 삭제
	public void cartBuy();			// 장바구니 구매
	
	//Guest - 바로구매
	public void nowBuy();			// 바로구매
	
	//Guest - 환불
	public void refund();			// 환불
	
}