package view;


import java.util.Map;
import java.util.Scanner;

import Service.AdminImpl;
import Service.GuestImpl;
import Service.LoginProgram;


/*
 * 작성일 : 2023년 07월 10일
 * 작성자 : 이승민
 * 클래스 : 메뉴 화면 구현, 로그인, 관리자 메뉴-재고관리, 관리자 메뉴-주문관리, 고객메뉴, 고객메뉴-장바구니 메서드 구현
 */
public class MenuImpl implements Menu {
	static Scanner scan = new Scanner(System.in);
	///////////////////////////////////////////////////////////////
	//각 메뉴별 진입 싱글톤
	AdminImpl adminimpl = AdminImpl.getInstance();
	GuestImpl guestimpl = GuestImpl.getInstance();
	LoginProgram login = LoginProgram.getInstance();
	///////////////////////////////////////////////////////////////
//	private MenuImpl () {}
//	private static MenuImpl instance = new MenuImpl();
//	public static MenuImpl getInstance() {
//		if (instance == null) {
//			instance = new MenuImpl();
//		}
//		return instance;
//	}
	///////////////////////////////////////////////////////////////
	@Override //로그인 화면
	public void LoginView() {
		while(true) {
			System.out.println("---------------로그인---------------");
			System.out.println("1.고객\t2.관리자\t3.회원가입\t4.종료");
			System.out.println("----------------------------------");
			try {
				System.out.print("번호 입력 : ");
				int menuNum = scan.nextInt();
				if (menuNum <= 4) {
					switch (menuNum) {
						case 1 : login.isMemberCheck();//고객
							GuestMenu();
							break;
						case 2 : login.isMemberCheck();//관리자
							AdminLogin(); 
							break;
						case 3 : login.Join();//회원가입
							break;
						default: System.out.println("시스템을 종료 합니다.");
							System.exit(0);//종료
					}
				} else {
					System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
					scan.nextLine();
					LoginView();
				}
			} catch (Exception e) {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				scan.nextLine();
				LoginView();
			} 
		}
	}
	///////////////////////////////////////////////////////////////
	@Override // 관리자 로그인
	public void AdminLogin() {
		AdminImpl xx = AdminImpl.getInstance();
		System.out.println("---------------관리자---------------");
		System.out.println("1.재고관리" + "\t" + "2.주문관리" + "\t" + "3.주가변동" + "\t" + "4.로그아웃");
		System.out.println("----------------------------------");
		try {
			System.out.print("번호 입력 : ");
			int adminNum = scan.nextInt();
			if (adminNum <= 4 ) {
				switch (adminNum) {
					case 1 : AdminStockMenu(); //재고관리 = 같은 클래스
						break;
					case 2 : AdminOrderMenu(); //주문관리 = 같은 클래스
						break;
					case 3 : xx.run(); //주가변동
						break;
					default : LoginView(); //로그아웃
				} 
			} else {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				scan.nextLine();
				AdminLogin();
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
			scan.nextLine();
			AdminLogin();
		}
	}
	///////////////////////////////////////////////////////////////
	@Override//관리자 메뉴 = 재고관리 //1.목록		2.추가		3.수정		4.삭제		5.이전
	public void AdminStockMenu() {
		System.out.println("---------------재고관리---------------");
		System.out.println("1.목록\t2.추가\t3.수정\t4.삭제\t5.이전");
		System.out.println("----------------------------------");
		try {
			System.out.print("번호 입력 : ");
			int adminstockNum = scan.nextInt();
			if (adminstockNum <= 5) {
				switch (adminstockNum) {
					case 1 :  adminimpl.productList();//상품목록
						break;
					case 2 :  adminimpl.productAdd();//상품추가
						break;
					case 3 :  adminimpl.productUpdate();//상품수정
						break;
					case 4 :  adminimpl.productRemove();//상품삭제
						break;
					default : AdminLogin(); //이전
				} 
			}else {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				scan.nextLine();
				AdminStockMenu();
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
			scan.nextLine();
			AdminStockMenu();
		}
	}
	///////////////////////////////////////////////////////////////
	@Override//관리자 메뉴 = 주문관리 //1.주문목록		2.결제승인		3.결제취소		4.결산		5.이전
	public void AdminOrderMenu() {
		System.out.println("---------------주문관리---------------");
		System.out.println("1.주문목록\t2.결제승인\t3.매도목록\t4.결산\t5.이전");
		System.out.println("----------------------------------");
		try {
			System.out.print("번호 입력 : ");
			int adminorderNum = scan.nextInt();
			if (adminorderNum <= 5) {
				switch (adminorderNum) {
					case 1 :  adminimpl.orderList();//주문목록
						break;
					case 2 :  adminimpl.orderConfirm();//결제승인
						break;
					case 3 :  adminimpl.orderCancel();//결제취소
						break;
					case 4 :  adminimpl.saleTotal();//결산
						break;
					default : AdminLogin(); //이전
				} 
			}else {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				scan.nextLine();
				AdminOrderMenu();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
			scan.nextLine();
			AdminOrderMenu();
		}
	}
	///////////////////////////////////////////////////////////////
	@Override//고객 메뉴 //1.구매		2.환불		3.장바구니		4.로그아웃
	public void GuestMenu() {
		System.out.println("---------------고객메뉴---------------");
		System.out.println("1.바로매수" + "\t" + "2.매도" + "\t" + "3.장바구니" + "\t" + "4.포인트" + "\t" +"5.로그아웃");
		System.out.println("----------------------------------");
		
		try {
			System.out.print("번호 입력 : ");
			int guestmenuNum = scan.nextInt();
			if (guestmenuNum <= 5) {
				switch (guestmenuNum) {
					case 1 : guestimpl.nowBuy(); //바로구매
						break;
					case 2 :  guestimpl.refund();//환불
						break;
					case 3 :  GuestCartList();//장바구니
						break;
					case 4 :  guestimpl.point();//포인트
						break;
					default : LoginView(); //로그아웃
				} 
			}else {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				scan.nextLine();
				GuestMenu();
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
			scan.nextLine();
			GuestMenu();
		}
	}
	///////////////////////////////////////////////////////////////
	@Override//고객 메뉴 = 장바구니 //1.추가		2.삭제		3.구매		4.이전
	public void GuestCartList() {
		System.out.println("---------------장바구니---------------");
		System.out.println("1.추가\t2.삭제\t3.매수\t4.리스트\t5.이전");
		System.out.println("----------------------------------");
		try {
			System.out.print("번호 입력 : ");
			int guestmenuNum = scan.nextInt();
			if (guestmenuNum <= 5) {
				switch (guestmenuNum) {
					case 1 :  guestimpl.cartAdd(); //추가
						break;
					case 2 :  guestimpl.cartDel();//삭제
						break;
					case 3 :  guestimpl.cartBuy();//구매
						break;
					case 4 :  guestimpl.cartList();//장바구니 리스트
						break;	
					default : GuestMenu(); //이전
				} 
			}else {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				scan.nextLine();
				GuestCartList();
			}
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
			scan.nextLine();
			GuestCartList();
			
		}
	}
	///////////////////////////////////////////////////////////////
}
