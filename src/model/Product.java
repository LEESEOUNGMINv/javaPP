package model;
/*
 * 작성일 : 2023년 07월 10일
 * 작성자 : 이승민
 * 클래스 : 상품 입력 클래스 DTO 
 */
//DTO (Data TransFer Object) 데이터 입력, 수정, 삭제, 조회 → 자료구조 활용
public class Product {
	public int StockID; //번호
	public String StockName; //주식명
	public int StockPrice; //주가
	public int StockQuantity; //수량
	///////////////////////////////////////////////////////////////
	private Product () {}
	private static Product instance = new Product();
	public static Product getInstance() {
		if (instance == null) {
			instance = new Product();
		}
		return instance;
	}
	///////////////////////////////////////////////////////////////
	public Product (int StockID, String StockName, int StockPrice, int StockQuantity) {
		this.StockID = StockID;
		this.StockName = StockName;
		this.StockPrice = StockPrice;
		this.StockQuantity = StockQuantity;
	}	
	///////////////////////////////////////////////////////////////
	public int getStockID() {
		return StockID;
	}
	public void setStockID(int stockID) {
		StockID = stockID;
	}
	public String getStockName() {
		return StockName;
	}
	public void setStockName(String stockName) {
		StockName = stockName;
	}
	public int getStockPrice() {
		return StockPrice;
	}
	public void setStockPrice(int stockPrice) {
		StockPrice = stockPrice;
	}
	public int getStockQuantity() {
		return StockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		StockQuantity = stockQuantity;
	}
	public String toString() {
		return "\t" + StockID  + "\t" + StockName + "\t" + StockPrice + "\t" + StockQuantity;
	}
}
