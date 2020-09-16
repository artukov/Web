package beans;

import java.util.Date;

public class AvailableDate {

	private Date date;
	private AvailableEnum status;
	
	public AvailableDate() {
		super();
		this.status = status.FREE;
		// TODO Auto-generated constructor stub
	}

	public AvailableDate(Date date, AvailableEnum status) {
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

	public AvailableEnum isStatus() {
		return status;
	}

	public void setStatus(AvailableEnum status) {
		this.status = status;
	}
	
	
}
