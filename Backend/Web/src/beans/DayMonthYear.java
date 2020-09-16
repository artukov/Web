package beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayMonthYear {
	int day;
	int month;
	int year;
	
//	public DayMonthYear() {
//		super();
//	}
//	
//	public DayMonthYear(int day, int month, int year) {
//		this.day = day;
//		this.month = month;
//		this.year = year;
//	}
	
	public void setDay(int day) {
		this.day = day;
	}
	public int getDay() {
		return day;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	public int getMonth() {
		return month;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	public int getYear() {
		return year;
	}
}
