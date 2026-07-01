package parking.view;

import java.util.ArrayList;
import java.util.Scanner;

import parking.controller.ParkingController;
import parking.model.Member;

public class ParkingView {
	private ParkingController pc = new ParkingController();
	private Scanner sc = new Scanner(System.in);

	public ParkingView() {
		
	}
	
	public void mainMenu() {
		while(true) {
			System.out.println("==== 주차 관리 프로그램 ====");
			System.out.println("1. 입차");
			System.out.println("2. 출차");
			System.out.println("3. 월 회원 등록");
			System.out.println("4. 현재 주차 현황");
			System.out.println("5. 출차 기록");
			System.out.println("6. 월 회원 목록");
			System.out.println("7. 회원 정보 수정");
			System.out.println("8. 출차 기록 저장");
			System.out.println("9. 종료");
			System.out.print("메뉴를 선택: ");
			int menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
			case 1:
				insertVehicle();
				break;
			case 2:
				exitVehicle();
				break;
			case 3:
				insertMember();
				break;
			case 4:
				printAllCurrent();
				break;
			case 5:
				printAllHistory();
				break;
			case 6:
				printAllMembers();
				break;
			case 7:
				updateMember();
				break;
			case 8:
				exitVehicleSave();
				break;
			case 9:
				System.out.println("종료");
				return;
			default :
					System.out.println("없는 메뉴입니다.");
			}
			
		}
	}
	
	public void insertVehicle() {
		System.out.println("남은 자리: " + pc.empty());
		System.out.print("차 번호: ");
		String carNumber = sc.nextLine();
		String result = pc.insertVehicle(carNumber);
		System.out.println(result);
	}
	
	public void exitVehicle() {
		System.out.print("차 번호: ");
		String carNumber = sc.nextLine();
		String result = pc.exitVehicle(carNumber);
		System.out.println(result);
	}
	public void insertMember() {
		System.out.println("======= 월 회원 등록 =======");
		ArrayList<String> memberInfo = memberInfo();
		System.out.println(pc.insertMember(memberInfo.get(0), memberInfo.get(1), memberInfo.get(2)));
	}
	public void printAllCurrent() {
		System.out.println("======= 주차 현황 =======");
		ArrayList<String> current = pc.printAllCurrentList();
		System.out.println(current);
	}
	public void printAllHistory() {
		System.out.println("======= 출차 기록 =======");
		ArrayList<String> history = pc.printAllHistoty();
		System.out.println(history);
	}
	public void printAllMembers() {
		System.out.println("======= 월 회원 목록 =======");
		ArrayList<String> members = pc.printAllMember();
		System.out.println(members);
	}
	
	public void updateMember() {
		System.out.println("======= 회원 정보 수정 =======");
		System.out.print("정보 수정할 회원의 전화번호를 입력하세요: ");
		String phoneNumber = sc.nextLine();
		ArrayList<String> memberInfo = memberInfo();
		Member newMember = pc.setMember(phoneNumber, memberInfo);
		if(newMember == null) {
			System.out.println("회원 정보를 찾지 못했습니다.");
		}else {
			System.out.println("회원 정보가 변경되었습니다.");
			System.out.println(newMember.toString());
		}
		
	}
	public void exitVehicleSave() {
		int result = pc.exitVehicleSave();
		if(result == 1) {
			System.out.println("저장에 성공했습니다.");
		}else {
			System.out.println("저장에 실패했습니다.");
		}
	}
	
	public ArrayList<String> memberInfo(){
		String carNumber, phoneNumber, name;
		ArrayList<String> memberInfo = new ArrayList<String>();
		while(true) {
			System.out.print("차 번호: ");
			 carNumber = sc.nextLine();
			 if(!carNumber.matches("^[0-9]{2,3}[가-힣]{1}[0-9]{4}$")) {
				 System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				 continue;
			 }else {
				 memberInfo.add(carNumber);
				 break;
			 }
		}
		while(true) {
			System.out.print("전화번호 (- 를 제외한 숫자만 입력해주세요): ");
			 phoneNumber = sc.nextLine();
			 if(!phoneNumber.matches("^01(?:0|1|[6-9])\\d{7,8}$")) {
				 System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				 continue;
			 }else {
				 memberInfo.add(phoneNumber);
				 break;
			 }
		}
		while(true) {
			System.out.print("이름: ");
			 name = sc.nextLine();
			 if(!name.matches("^[가-힣]{2,5}$")) {
				 System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				 continue;
			 }else {
				 memberInfo.add(name);
				 break;
			 }
		}
		return memberInfo;
	}
}
