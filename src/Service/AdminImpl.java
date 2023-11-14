package Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import model.Cart;
import model.Product;
import view.MenuImpl;

/*
 * 작성일 : 2023년 07월 10일
 * 작성자 : 이승민
 * 클래스 : 관리자 클래스 메서드 구현
 * 		  상품 리스트, 상품 추가, 상품 수정, 상품 삭제, 주문 목록, 결제 승인, 결제취소 환불승인, 결산 메서드 구현
 */
public class AdminImpl extends Thread implements Admin  {
	///////////////////////////////////////////////////////////////
	static Scanner scan = new Scanner(System.in);
	static Map <Integer, Product> admap = new HashMap<Integer, Product>(); //상품저장
	Random random = new Random();

	///////////////////////////////////////////////////////////////
	Product product = Product.getInstance();
	int StockID; //번호
	String StockName; //주식명
	int StockPrice; //주가
	int StockQuantity; //수량
	int total;//토탈
	int point;//포인트
	///////////////////////////////////////////////////////////////

	private AdminImpl () {}

	private static AdminImpl instance = null;

	public static AdminImpl getInstance() {
		if (instance == null) {
			instance = new AdminImpl(); 
		}
		return instance;
	}
	///////////////////////////////////////////////////////////////
	@Override//상품목록 Entry 이용 목록 진행
	public void productList() {
		MenuImpl xx = new MenuImpl();
		System.out.println("============상품============");
		System.out.println("고유번호"+"\t"+"번호"+"\t"+"주식명"+"\t"+"주가"+"\t"+"수량");
		for (Entry<Integer, Product> entry : admap.entrySet()) {
			int key = entry.getKey();
			Product value = admap.get(key);
			System.out.println(key + " " + value);
		}
//		admap.forEach((key, value) -> {System.out.println(key + " " + value);});
		System.out.println("=============================");
		xx.AdminStockMenu();
	}
	///////////////////////////////////////////////////////////////
	@Override//상품추가 
	public void productAdd() {
		MenuImpl xx = new MenuImpl();
		try {
			while (true) {
				int No = (int)(Math.random() * 1000)+1000;
				if (admap.containsKey(No)) {
					System.out.println("이미 등록된 상품입니다.");	
					xx.AdminStockMenu();
				} else {
					System.out.println("==========상품을 추가 합니다.==========");
					System.out.print("번호 : ");
					int StockID = scan.nextInt();
					System.out.print("주식명 : ");
					String StockName = scan.next();
					int StockPrice = (int)(Math.random() * 1000)+1000;
					System.out.print("수량 : ");
					int StockQuantity = scan.nextInt();
					admap.put(No, new Product(StockID,StockName,StockPrice,StockQuantity));
					System.out.println("=======상품 등록이 완료 되었습니다.=======");
					xx.AdminStockMenu();
				}
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다.");
			xx.AdminStockMenu();
		}

	}
	///////////////////////////////////////////////////////////////
	@Override//상품수정
	public void productUpdate() {
		MenuImpl xx = new MenuImpl();
		try {
			while (true) {
				System.out.print("수정하려는 종목의 고유번호를 입력하세요 [이전 : 0]: ");
				int No = scan.nextInt();
				if (admap.containsKey(No)) {
					System.out.println("==========상품을 수정 합니다.==========");
					System.out.print("번호 : ");
					int StockID = scan.nextInt();
					System.out.print("주식명 : ");
					String StockName = scan.next();
					int StockPrice = (int)(Math.random() * 1000)+1000;
					System.out.print("수량 : ");
					int StockQuantity = scan.nextInt();
					System.out.println("=======상품 수정이 완료 되었습니다.=======");
					admap.put(No, new Product(StockID,StockName,StockPrice,StockQuantity));
					xx.AdminStockMenu();
				} else if (No == 0) {
					xx.AdminStockMenu();
				}
				else {
					System.out.println("없는 상품입니다.");
					xx.AdminStockMenu();
				}
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다.");
			xx.AdminStockMenu();
		}
	}
	///////////////////////////////////////////////////////////////
	public void run() { // 주가변동
		MenuImpl xx = new MenuImpl();
		GuestImpl xxx = GuestImpl.getInstance();
		for (int i = 1; i <= 5; i++) {
			try {
				for (Entry<Integer, Product> entry : admap.entrySet()) {
					int key = entry.getKey();
					//					Product value = admap.get(key);
					int StockID = admap.get(key).getStockID();
					String StockName = admap.get(key).getStockName();
					int StockPrice = (int)(Math.random() * 1000)+1000;
					int StockQuantity = admap.get(key).getStockQuantity();
					admap.put(key, new Product (StockID, StockName, StockPrice, StockQuantity));
				}
				
				System.out.println("★★★★★주가가 변동되었습니다.★★★★★");
				System.out.println("============상품============");
				System.out.println("고유번호"+"\t"+"번호"+"\t"+"주식명"+"\t"+"주가"+"\t"+"수량");
				for (Entry<Integer, Product> entry : admap.entrySet()) {
					int key = entry.getKey();
					Product value = admap.get(key);
					System.out.println(key + "," + value);
				}
				System.out.println("=============================");

				Thread.sleep(1000);
			}catch (Exception e) {
				System.out.println("잘못입력하셨습니다.");
				xx.AdminLogin();
			}
		}
		
		try {
			for(Entry<Integer, Cart > e : xxx.refund.entrySet() ) {
				int key = e.getKey();
				xxx.refund.get(key).setStockPrice(admap.get(key).getStockPrice());	
			}
			for(Entry<Integer, Cart > e : xxx.cartsbuy.entrySet() ) {
				int key = e.getKey();
				xxx.cartsbuy.get(key).setStockPrice(admap.get(key).getStockPrice());	
			}
		} catch (Exception e) {
			System.out.println("!!!!!");
		}
		xx.AdminLogin();
	}
	///////////////////////////////////////////////////////////////
	@Override//상품삭제
	public void productRemove() {
		MenuImpl xx = new MenuImpl();
		while (true) {
			System.out.print("삭제하려는 종목의 고유번호를 입력하세요 [이전 : 0]: ");
			int No = scan.nextInt();
			if (admap.containsKey(No)) {
				System.out.println("==========상품을 삭제 합니다.==========");
				admap.remove(No);
				System.out.println("=======상품 삭제가 완료 되었습니다.=======");
				xx.AdminStockMenu();
			} else if (No == 0) {
				xx.AdminStockMenu();
			} else {
				System.out.println("잘못입력하셨습니다.");
				xx.AdminStockMenu();
			}
		}
	}
	///////////////////////////////////////////////////////////////
	@Override//주문목록
	public void orderList() {
		MenuImpl xxx = new MenuImpl();
		GuestImpl xx = GuestImpl.getInstance();
		System.out.println("============주문목록============");
		System.out.println("고유번호"+"\t"+"번호"+"\t"+"주식명"+"\t"+"주가"+"\t"+"수량");
		for (Entry <Integer, Cart> entry1 : xx.buysell.entrySet()) {
			int key = entry1.getKey();
			Cart value = entry1.getValue();
			System.out.println(key + "" + value);
		}
		System.out.println("=============================");
		try {
			System.out.println("=============================");
			System.out.print("승인할 고유 코드를 입력하세요 [이전 : 0]:");
			int i = scan.nextInt();
			if (admap.containsKey(i)) {	
				System.out.println("구매 승인이 완료되었습니다.");
				orderConfirm();
			} else if (i == 0) {
				xxx.AdminOrderMenu();
			} else {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				xxx.AdminOrderMenu();
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
			xxx.AdminOrderMenu();
		}
	}
	///////////////////////////////////////////////////////////////
	@Override//결제승인
	public void orderConfirm() {
		MenuImpl xxx = new MenuImpl();
		GuestImpl xx = GuestImpl.getInstance();
		try {
			System.out.print("최종 결제 승인을 위한 고유코드를 입력하세요 : ");
			int i = scan.nextInt();
			if (admap.containsKey(i)) {
				System.out.println("결제 승인이 완료 되었습니다.");
				//수량감소
				admap.get(i).setStockQuantity(admap.get(i).getStockQuantity() - xx.buybuy.get(i).getStockQuantity());
				int t = (xx.buybuy.get(i).getStockQuantity() * admap.get(i).getStockPrice()) - (xx.buybuy.get(i).getStockQuantity() * xx.price.get(i).getStockPrice()); // (살때 수량 * 기존 가격) - (살때 수량 * 팔때의 가격)
				total += t; // 결산
				int r = (xx.buybuy.get(i).getStockQuantity() * xx.price.get(i).getStockPrice()) - (xx.buybuy.get(i).getStockQuantity() * admap.get(i).getStockPrice()); // (살때 수량 * 팔때 가격) - (살때 수량 * 기존 가격)
				point += r; // 포인트
				xx.cartsbuy.remove(i);
				xx.buysell.remove(i);
				xxx.AdminOrderMenu();
			} else {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				xxx.AdminOrderMenu();
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
			xxx.AdminOrderMenu();
		}	
	}
	///////////////////////////////////////////////////////////////
	@Override//결제취소 환불승인
	public void orderCancel() {
		GuestImpl xx = GuestImpl.getInstance();
		MenuImpl xxx = new MenuImpl();
		System.out.println("============매도목록============");
		System.out.println("고유번호"+"\t"+"번호"+"\t"+"주식명"+"\t"+"주가"+"\t"+"수량");
		for (Entry <Integer, Cart> entry1 : xx.refundlist.entrySet()) {
			int key = entry1.getKey();
			Cart value = entry1.getValue();
			System.out.println(key + "" + value);
		}
		try {
			System.out.print("환불을 진행할 고유코드를 입력하세요 : ");
			int i = scan.nextInt();
			if (xx.buybuy.containsKey(i)) {
				System.out.println("환불이 완료되었습니다.");
				admap.get(i).setStockQuantity(admap.get(i).getStockQuantity() + xx.buybuy.get(i).getStockQuantity());
				int t = (xx.buybuy.get(i).getStockQuantity() * admap.get(i).getStockPrice()) - (xx.buybuy.get(i).getStockQuantity() * xx.price.get(i).getStockPrice());
				total -= t;
				int r = (xx.buybuy.get(i).getStockQuantity() * xx.price.get(i).getStockPrice()) - (xx.buybuy.get(i).getStockQuantity() * admap.get(i).getStockPrice()); // (살때 수량 * 살때 가격) - (기존의 가격)
				point -= r;
				xx.refundlist.remove(i);
				xx.refund.remove(i);
				xxx.AdminOrderMenu();
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다.");
			xxx.AdminOrderMenu();
		}
	}
	///////////////////////////////////////////////////////////////
	@Override//결산
	public void saleTotal() {
		//		GuestImpl xx = GuestImpl.getInstance();
		//		Product xxx = Product.getInstance();
		MenuImpl xx = new MenuImpl();
		System.out.println("이만큼 벌었습니다 : " + total);
		xx.AdminOrderMenu();

		//		//Set<Integer> key = xx.getCartsbuy().keySet();
		//		try {
		//			System.out.println(xx.getCartsbuy().get(key).getStockQuantity());
		//			
		//		} catch (Exception e) {
		//			System.out.println("!!!!!" + xx.getCartsbuy().keySet());
		//		}
		//		xx.getCartsbuy().get(key).getStockQuantity()

	}
	///////////////////////////////////////////////////////////////
}
