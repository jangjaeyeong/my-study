package parking.model;

import java.time.LocalDateTime;

public class ParkingRecord {
	private LocalDateTime enterTime;
	private LocalDateTime exitTime;
	private Vehicle verhicle;
	private int fee;
	
	
	
	public ParkingRecord(Vehicle vehicle) {
		this.verhicle = vehicle;
	}
	
	public LocalDateTime getEnterTime() {
		return enterTime;
	}



	public void setEnterTime(LocalDateTime enterTime) {
		this.enterTime = enterTime;
	}



	public LocalDateTime getExitTime() {
		return exitTime;
	}



	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}



	public Vehicle getVerhicle() {
		return verhicle;
	}


	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

}
