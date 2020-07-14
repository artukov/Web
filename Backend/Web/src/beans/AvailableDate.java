package beans;

import java.util.Date;

public class AvailableDate {

	private Date date;
	private boolean status;
	
	public AvailableDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AvailableDate(Date date, boolean status) {
		super();
		this.date = date;
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
