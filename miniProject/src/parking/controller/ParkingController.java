package parking.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import parking.model.FeeCalculator;
import parking.model.Member;
import parking.model.ParkingRecord;
import parking.model.Vehicle;

public class ParkingController {
	private ArrayList<ParkingRecord> currentParking;
	private ArrayList<ParkingRecord> history;
	private ArrayList<Member> members;
	private FeeCalculator feeCalculator;
 	private int memberCount = 0;
	
	
	public ParkingController() {
		
	}
	
	public String insertVehicle(String carNumber) {
		ParkingRecord p = findVehicle(carNumber);
		if(p != null) {
			return "이미 입차가 된 차량입니다.";
		}
		Vehicle v = new Vehicle(carNumber);
		p = new ParkingRecord(v);
		p.setEnterTime(LocalDateTime.now());
		currentParking.add(p);
		return "입차 되었습니다";
		
	}
	
	public String exitVehicle(String carNumber) {
		ParkingRecord p = findVehicle(carNumber);
		int price = 0;
		if(p != null) {
			return "입차가 되지 않았거나 출차가 완료된 차량입니다.";
		}
		currentParking.remove(p);
		p.setExitTime(LocalDateTime.now());
		history.add(p);
		Member isMember = findMember(carNumber);
		if(isMember == null) {
			price =  feeCalculator.calculatorFee(p.getEnterTime(), p.getExitTime());
		}else {
			price  = isMember.getMonthFee();
		}
		
		return "출차가 완료되었습니다. 요금은 " + price + "원 입니다.";
	}
	
	public String insertMember(String carNumber) {
		Member m = findMember(carNumber);
		if(m != null) {
			return "이미 가입된 회원입니다.";
		}
		Vehicle v = new Vehicle(carNumber);
		m = new Member();
		m.setVehicle(v);
		m.setId(memberCount+1);
		m.setExpireDate(LocalDate.now().plusMonths(1));
		members.add(m);
		memberCount++;
		return "월 회원 등록이 완료되었습니다.";
	}
	
	public ParkingRecord findVehicle(String carNumber) {
		
		for(ParkingRecord p : currentParking) {
			if(p.getVerhicle().getCarNumber() == carNumber) {
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
}
