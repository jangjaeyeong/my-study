package parking.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class FeeCalculator {
	private int baseFee = 3000;
	private int unitTime;
	private int unitFee;
	
	public FeeCalculator() {
		
	}
	public int calculatorFee(LocalDateTime enterTime, LocalDateTime exitTime) {
		unitTime = (int)Duration.between(enterTime, exitTime).toMinutes();
		System.out.println(unitTime);
		if(unitTime >= 60) {
			unitFee = (unitTime - 60) / 30;
			return baseFee + (unitFee*500);
			
		}else if(unitTime > 5){
			return baseFee;
			
		}else {
			return 0;
		}
	}
}
