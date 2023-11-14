package Service;
/*
 * 작성일 : 2023년 07월 10일
 * 작성자 : 이승민
 * 클래스 : 관리자 작업명세서 
 */
//작업명세서
public interface Admin {
	public static final String ID = "1";			//관리자 고유 ID 생성
	public static final String PASSWORD = "1";	//관리자 고유 비밀번호
	
	//Admin - 재고관리
	public void productList();							//상품목록
	public void productAdd();							//상품추가
	public void productUpdate();						//상품수정
	public void productRemove();						//상품삭제
	
	//Admin - 주문관리
	public void orderList();							//주문목록
	public void orderConfirm();							//결제승인
	public void orderCancel();							//결제취소
	
	//Admin - 결산
	public void saleTotal();							//결산
}
