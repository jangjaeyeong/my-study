package parking.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import parking.model.FeeCalculator;
import parking.model.Member;
import parking.model.ParkingRecord;
import parking.model.Vehicle;

public class ParkingController {
	private ArrayList<ParkingRecord> currentParking = new ArrayList<>();
	private ArrayList<ParkingRecord> history = new ArrayList<>();
	private ArrayList<Member> members = new ArrayList<>();
	private FeeCalculator feeCalculator = new FeeCalculator();
 	private int memberCount = 0;
	
	
	public ParkingController() {
		
	}
	//입차
	public String insertVehicle(String carNumber) {
		ParkingRecord p = null;
		if(currentParking.size() != 0) {
			 p = findVehicle(carNumber);
		}
		if(p != null) {
			return "이미 입차가 된 차량입니다.";
		}
		
		Vehicle v = new Vehicle(carNumber);
		p = new ParkingRecord(v);
		p.setEnterTime(LocalDateTime.now());
		currentParking.add(p);
		Member isMember = findMember(carNumber);
		if(isMember == null) {
			return "(방문 차량) 환영합니다";
		}else {
			return "(등록차량) 환영합니다";
		}
		
		
	}
	//출차
	public String exitVehicle(String carNumber) {
		ParkingRecord p = findVehicle(carNumber);
		int price = 0;
		if(p == null) {
			return "입차가 되지 않았거나 출차가 완료된 차량입니다.";
		}
		currentParking.remove(p);
		p.setExitTime(LocalDateTime.now());
		history.add(p);
		
		Member isMember = findMember(carNumber);
		if(isMember == null) {
			price =  feeCalculator.calculatorFee(p.getEnterTime(), p.getExitTime());
			return "(방문차량) 조심히 가세요. 요금은 " + price + "원 입니다.";
		}else {
			if(isMember.getExpireDate().isAfter(LocalDate.now())) {
				price = 0;
				return "(등록차량) 조심히 가세요. 요금은 " + price + "원 입니다.";
			}else {
				members.remove(isMember);
				price =  feeCalculator.calculatorFee(p.getEnterTime(), p.getExitTime());
				return "(등록차량) 조심히 가세요. 요금은 " + price + "원 입니다.";
			}
				
		}
		
	}
	//회원 등록
	public String insertMember(String carNumber, String phoneNumber, String name) {
		Member m = findMember(carNumber);
		if(m != null) {
			return "이미 가입된 회원입니다.";
		}
		Vehicle v = new Vehicle(carNumber);
		m = new Member();
		m.setVehicle(v);
		m.setId(memberCount+1);
		m.setExpireDate(LocalDate.now().plusMonths(1));
		m.setPhoneNumber(phoneNumber);
		m.setName(name);
		members.add(m);
		memberCount++;
		return "월 회원 등록이 완료되었습니다.";
	}
	public ArrayList<String> printAllCurrentList() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분");
		ArrayList<String> printList = new ArrayList<>();
		for(ParkingRecord p : currentParking) {
			printList.add(" 차량 번호: " + p.getVerhicle().getCarNumber()
					+ " 입차 시간: " + p.getEnterTime().format(formatter));
		}
		printList.add(0, "현재 입차 수: " + currentParking.size());
		return printList;
	}
	
	
	public ArrayList<String> printAllHistoty() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분");
		ArrayList<String> printList = new ArrayList<>();
		for(ParkingRecord p : history) {
			printList.add("차량 번호: " + p.getVerhicle().getCarNumber()
					+ " 입차 시간: " + p.getEnterTime().format(formatter) + " 출차 시간: " + p.getExitTime().format(formatter));
		}
		return printList;
	}
	public ArrayList<String> printAllMember() {
		ArrayList<String>printMembers = new ArrayList<>();
		for(Member m : members) {
			printMembers.add(" 이름: " + m.getName()+ " 차량 번호: " + m.getVehicle().getCarNumber()
					+" 전화번호: " + m.getPhoneNumber() + " 만료일: " + m.getExpireDate());
		}
		printMembers.add(0, "월 회원 수: " + memberCount);
		return printMembers;
	}
	
	public Member setMember(String phoneNumber, ArrayList<String> newMember) {
		for(Member m : members) {
			if(phoneNumber.equals(m.getPhoneNumber())) {
				m.setVehicle(new Vehicle(newMember.get(0)));
				m.setPhoneNumber(newMember.get(1));
				m.setName(newMember.get(2));
				return m;
			}
		}
		return null;
	}
	public int exitVehicleSave() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분");
		try(BufferedWriter bw = new BufferedWriter(
				new FileWriter("exitManagement.txt"))) {
			for(ParkingRecord p : history) {
				String data = "차량번호: " + p.getVerhicle().getCarNumber() + ", 입차 시간: " 
			+ p.getEnterTime().format(formatter) + ", 출차 시간: " + p.getExitTime().format(formatter);
				bw.write(data);
				bw.newLine();
				bw.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public ParkingRecord findVehicle(String carNumber) {
		for(ParkingRecord p : currentParking) {
			if(carNumber.equals(p.getVerhicle().getCarNumber())) {
				return p;
			}
		}
		return null;
	}
	
	public Member findMember(String carNumber) {
		for(Member m : members) {
			if(carNumber.equals(m.getVehicle().getCarNumber())) {
				return m;
			}
		}
		return null;
	}
	
	public int empty() {
		return 50 - currentParking.size();
	}

}
