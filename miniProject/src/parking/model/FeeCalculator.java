package parking.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class FeeCalculator {
	private int baseFee;
	private int unitTime;
	private int unitFee;
	
	public int calculatorFee(LocalDateTime enterTime, LocalDateTime exitTime) {
		int parkingTime = (int)Duration.between(enterTime, exitTime).toMinutes();
		if(parkingTime <= 60) {
			return 3000;
		}else {
			int a = (parkingTime - 60) / 30;
			return 3000 + (a*500);
		}
	}
}
