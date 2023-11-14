package Service;
/*
 * 작성일 : 2023년 07월 10일
 * 작성자 : 이승민
 * 클래스 : 로그인, 회원가입, 메서드 구현
 */
import java.util.HashMap;
import java.util.Scanner;

import view.MenuImpl;
 
public class LoginProgram {
	HashMap<String,String> memberTable = new HashMap<String,String>();
    static Scanner sc = new Scanner (System.in);
	///////////////////////////////////////////////////////////////

    private String id;
    private String password;
    static boolean loginFlag = false;
	///////////////////////////////////////////////////////////////
    private LoginProgram () {}
    private static LoginProgram instance = new LoginProgram();
    public static LoginProgram getInstance() {
    	if (instance == null) {
    		instance = new LoginProgram();
    	}
    	return instance;
    }
	///////////////////////////////////////////////////////////////
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
	///////////////////////////////////////////////////////////////
    
    //회원가입 메소드
    public void Join() {
        while(true) {
            System.out.print("가입 id : ");
            String newId = sc.nextLine();
            if(memberTable.containsKey(newId)) {
                System.out.println("이미 존재하는 아이디 입니다.");
                continue;
            }
            System.out.print("가입 pw : ");
            String newPwd = sc.nextLine();
            memberTable.put(newId, newPwd);
            break;
        }
        System.out.println("회원가입이 완료되었습니다.");
    }
    // 로그인
    public void isMemberCheck() {
    	memberTable.put(Admin.ID,Admin.PASSWORD);
    	while (true) {
    		MenuImpl xx2 = new MenuImpl();
        	System.out.print("ID : ");
        	id = sc.nextLine();
            if (memberTable.containsKey(id)) {
            	System.out.print("PW : ");
            	password = sc.nextLine();
                if (!memberTable.get(id).equals(password)) {
                    System.out.println("비밀번호가 맞지 않습니다. 인증 실패");
                    loginFlag = false;
                    xx2.LoginView();
                } else {
                    System.out.println("인증 성공");
                    loginFlag = true;
                    break;
                }
            } else {
                System.out.println("존재하지 않는 아이디 입니다.");
                loginFlag = false;
                xx2.LoginView();
            }
    	}
    }
}