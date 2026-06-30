package parking.model;

import java.time.LocalDateTime;

public class Vehicle {

	private String carNumber;
	private boolean status = false;
	
	
	public Vehicle(String carNumber) {
		this.carNumber = carNumber;
	}
	
	
	public String getCarNumber() {
		return carNumber;
	}

	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}

	public void verhicleInfo() {
		
	}
}
