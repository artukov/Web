package beans;

import java.util.Date;
import java.util.UUID;

public class Reservation {
	
	private Apartment apartment;
	private String startDate;
	private Integer numberNights;
	private Double fullPrice;
	private String reservationMessage;
	private String guest;
	private ReservationStatus statusRes;
	private UUID id;
	
	
	public Reservation(Apartment apartment, String startDate, Integer numberNights, Double fullPrice,
			String reservationMessage, String guest, ReservationStatus statusRes) {
		super();
		this.apartment = apartment;
		this.startDate = startDate;
		this.numberNights = numberNights;
		this.fullPrice = fullPrice;
		this.reservationMessage = reservationMessage;
		this.guest = guest;
		this.statusRes = statusRes;
		this.id = UUID.randomUUID();
	}
	
	public Reservation(Apartment apartment) {
		super();
		this.apartment = apartment;
		this.id = UUID.randomUUID();
	}
	
	public Reservation(Integer numberNights) {
		this.numberNights = numberNights;
		this.id = UUID.randomUUID();
	}
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	public Apartment getApartment() {
		return apartment;
	}
	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Integer getNumberNights() {
		return numberNights;
	}
	public void setNumberNights(Integer numberNights) {
		this.numberNights = numberNights;
	}
	public Double getFullPrice() {
		return fullPrice;
	}
	public void setFullPrice(Double fullPrice) {
		this.fullPrice = fullPrice;
	}
	public String getReservationMessage() {
		return reservationMessage;
	}
	public void setReservationMessage(String reservationMessage) {
		this.reservationMessage = reservationMessage;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public ReservationStatus getStatusRes() {
		return statusRes;
	}
	public void setStatusRes(ReservationStatus statusRes) {
		this.statusRes = statusRes;
	}
	
	
}
