package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import model.Cart;
import model.Product;
import view.MenuImpl;
/*
 * 작성일 : 2023년 07월 10일
 * 작성자 : 이승민
 * 클래스 : 게스트 클래스 메서드 구현
 * 		  장바구니 리스트, 장바구니 담기, 장바구니 삭제, 장바구니 구매, 바로구매, 환불 메서드 구현
 */
public class GuestImpl implements Guest {
	static Map <Integer, Product> admap = new HashMap<Integer, Product>();
	Map <Integer, Cart > carts = new HashMap<Integer, Cart>();		//장바구니
	Map <Integer, Cart > cartsbuy = new HashMap<Integer, Cart>();	//장바구니 구매 후 삭제
	Map <Integer, Cart > buybuy = new HashMap<Integer, Cart>();		//구매
	Map <Integer, Cart > buysell = new HashMap<Integer, Cart>();	//환불	
	Map <Integer, Cart > refund = new HashMap<Integer, Cart>();		//목록
	Map <Integer, Cart > refundlist = new HashMap<Integer, Cart>();	//환불목록
	Map <Integer, Cart > price = new HashMap<Integer, Cart>();		//구매시점 가격
	static Scanner scan = new Scanner(System.in);
	Cart cartc = Cart.getInstance();
	int StockID; //번호
	String StockName; //주식명
	int StockPrice; //주가
	int StockQuantity; //수량
	
	///////////////////////////////////////////////////////////////
	private GuestImpl () {}
	
	private static GuestImpl instance = null;
	
	public static GuestImpl getInstance() {
		if (instance == null) {
			instance = new GuestImpl();
		}
		return instance;
	}
//	
//	public Map<Integer, Cart> getCartsbuy() {
//		return cartsbuy;
//	}
	///////////////////////////////////////////////////////////////
	//@Override// 장바구니 리스트
	public void cartList() {
		MenuImpl xx = new MenuImpl();
		System.out.println("============장바구니============");
		System.out.println("고유번호"+"\t"+"번호"+"\t"+"주식명"+"\t"+"주가"+"\t"+"수량");
		for (Entry <Integer, Cart> entry : cartsbuy.entrySet()) {
			int key = entry.getKey();
			Cart value = entry.getValue();
			System.out.println(key + "" + value);
		} 
		System.out.println("=============================");
		xx.GuestCartList();
	}

	@Override// 장바구니 담기
	public void cartAdd() {
		AdminImpl xx = AdminImpl.getInstance();
		MenuImpl xxx = new MenuImpl();
		System.out.println("============상품============");
		System.out.println("고유번호"+"\t"+"번호"+"\t"+"주식명"+"\t"+"주가"+"\t"+"수량");
		for (Entry<Integer, Product> entry : AdminImpl.admap.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		try {
			while (true) {
				System.out.print("추가할 상품의 고유번호를 입력하세요 [이전 : 0] :");
				int i = scan.nextInt();
				if (AdminImpl.admap.containsKey(i)) {
					System.out.print("추가할 상품의 수량을 입력하세요 [이전 : 0] :");
					int StockQuantity = scan.nextInt();
					if(StockQuantity == 0) {
						xxx.GuestCartList();
					} else {
						int StockID = AdminImpl.admap.get(i).getStockID(); //번호
						String StockName = AdminImpl.admap.get(i).getStockName(); //주식명
						int StockPrice = AdminImpl.admap.get(i).getStockPrice(); //주가
						carts.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
						cartsbuy.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
//						buysell.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
//						refund.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
						System.out.println();
						break;					
					}
				} else if (i == 0) {
					xxx.GuestCartList();
				} 
			} 
			xxx.GuestCartList();
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다.");
			xxx.GuestCartList();
		}
	}

	@Override// 장바구니 삭제
	public void cartDel() {
		MenuImpl xx = new MenuImpl();
		
		try {
			while (true) {
				System.out.print("삭제하려는 종목의 고유번호를 입력하세요 : ");
				int No = scan.nextInt();
				if (carts.containsKey(No)) {
					System.out.println("==========상품을 삭제 합니다.==========");
					carts.remove(No);
					cartsbuy.remove(No);
//					buysell.remove(No);
//					refund.remove(No);
					System.out.println("=======상품 삭제가 완료 되었습니다.=======");
					System.out.println();
					System.out.println("============장바구니============");
					System.out.println("고유번호"+"\t"+"번호"+"\t"+"주식명"+"\t"+"주가"+"\t"+"수량");
					System.out.println("=============================");
					for (Entry <Integer, Cart> entry : carts.entrySet()) {
						int key = entry.getKey();
						Cart value = entry.getValue();
						System.out.println(key + "" + value);
					}
					System.out.println("=============================");
					xx.GuestCartList();
				} else {
					System.out.println("잘못입력하셨습니다.");
					xx.GuestCartList();
				}
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
			xx.GuestCartList();
		}
	}

	@Override// 장바구니 구매
	public void cartBuy() {
		MenuImpl xx = new MenuImpl();
		System.out.print("구매할 상품의 고유번호를 입력하세요 [이전 : 0]:");
		int i = scan.nextInt();
		try {
			if (carts.containsKey(i)) {
				int StockID = carts.get(i).getStockID(); //번호
				String StockName = carts.get(i).getStockName(); //주식명
				int StockPrice = carts.get(i).getStockPrice(); //주가
				int StockQuantity = carts.get(i).getStockQuantity();//수량
				buybuy.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
				buysell.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
				refund.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
				price.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
				System.out.println("구매 요청이 완료 되었습니다.");
				xx.GuestMenu();
			} else if (i == 0) {
				System.out.println("메뉴로 돌아갑니다.");
				xx.GuestMenu();
			} else {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				xx.GuestMenu();
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
			xx.GuestMenu();
		}
	}

	@Override// 바로구매
	public void nowBuy() {
		MenuImpl xx = new MenuImpl();
		System.out.println("============상품============");
		System.out.println("고유번호"+"\t"+"번호"+"\t"+"주식명"+"\t"+"주가"+"\t"+"수량");
		for (Entry<Integer, Product> entry : AdminImpl.admap.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		try {
			System.out.print("구매할 상품의 고유번호를 입력하세요 [이전 : 0] :");
			int i = scan.nextInt();
			if (AdminImpl.admap.containsKey(i)) {
				System.out.print("구매할 상품의 수량를 입력하세요 [이전 : 0] :");
				int StockQuantity = scan.nextInt();
				if(StockQuantity != 0) {
					int StockID = AdminImpl.admap.get(i).getStockID(); //번호
					String StockName = AdminImpl.admap.get(i).getStockName(); //주식명
					int StockPrice = AdminImpl.admap.get(i).getStockPrice(); //주가
					buybuy.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
					buysell.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
					refund.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
					price.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
					xx.GuestMenu();
				}
			} else if (StockQuantity == 0) {
				xx.GuestMenu();
			} else {
				System.out.println("잘못입력하셨습니다.");
				xx.GuestMenu();
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다.");
			xx.GuestMenu();
		}
	}

	@Override// 환불
	public void refund() {
		GuestImpl xx = GuestImpl.getInstance();
		MenuImpl xxx = new MenuImpl();
		System.out.println("============주문목록============");
		System.out.println("고유번호"+"\t"+"번호"+"\t"+"주식명"+"\t"+"주가"+"\t"+"수량");
		for (Entry <Integer, Cart> entry1 : refund.entrySet()) {
			int key = entry1.getKey();
			Cart value = entry1.getValue();
			System.out.println(key + "" + value);
		}
		try {
			System.out.print("환불요청 할 고유번호를 입력하세요 [이전 : 0]:");
			int i = scan.nextInt();
			if (buybuy.containsKey(i)) {
				int StockID = refund.get(i).getStockID(); //번호
				String StockName = refund.get(i).getStockName(); //주식명
				int StockPrice = refund.get(i).getStockPrice(); //주가
				int StockQuantity = refund.get(i).getStockQuantity(); //수량
				refundlist.put(i, new Cart(StockID, StockName, StockPrice, StockQuantity));
				System.out.println("환불요청이 완료되었습니다.");
				xxx.GuestMenu();
			} else if (i == 0) {
				xxx.GuestMenu();
			} else {
				System.out.println("잘못입력하셨습니다.");
				xxx.GuestMenu();
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다.");
			xxx.GuestMenu();
		}
	}
	public void point () {
		AdminImpl xx = AdminImpl.getInstance();
		MenuImpl xxx = new MenuImpl();
		System.out.println("포인트 : " + xx.point);
		xxx.GuestMenu();
	}
}