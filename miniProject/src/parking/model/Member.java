package parking.model;

import java.time.LocalDate;

public class Member {
	private int id;
	private Vehicle vehicle;
	private int monthFee = 70000;
	private LocalDate expireDate;
	
	public Member() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getMonthFee() {
		return monthFee;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}
	
}
