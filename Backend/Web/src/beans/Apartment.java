package beans;

import java.util.Date;
import java.util.List;

public class Apartment {
	private ApartmentType apartmentType;
	private Integer numberRooms;
	private Integer guestNumber;
	private Location location;
	private List<AvailableDate> appartmentDates;
	private Host host;
	private List<ApartmentComment> comments;
	private String image;
	private double priceNight;
	private Date checkIn;
	private Date checkOut;
	private boolean appStatus;
	private List<Amenities> amenities;
	private List<Reservation> reservations;
	private Long id;
	
	public Apartment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Apartment(ApartmentType apartmentType, Integer numberRooms, Integer guestNumber, Location location,
			List<AvailableDate> appartmentDates, Host host, List<ApartmentComment> comments, String image,
			double priceNight, Date checkIn, Date checkOut, boolean appStatus, List<Amenities> amenities,
			List<Reservation> reservations) {
		super();
		this.apartmentType = apartmentType;
		this.numberRooms = numberRooms;
		this.guestNumber = guestNumber;
		this.location = location;
		this.appartmentDates = appartmentDates;
		this.host = host;
		this.comments = comments;
		this.image = image;
		this.priceNight = priceNight;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.appStatus = appStatus;
		this.amenities = amenities;
		this.reservations = reservations;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ApartmentType getApartmentType() {
		return apartmentType;
	}
	public void setApartmentType(ApartmentType apartmentType) {
		this.apartmentType = apartmentType;
	}
	public Integer getNumberRooms() {
		return numberRooms;
	}
	public void setNumberRooms(Integer numberRooms) {
		this.numberRooms = numberRooms;
	}
	public Integer getGuestNumber() {
		return guestNumber;
	}
	public void setGuestNumber(Integer guestNumber) {
		this.guestNumber = guestNumber;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public List<AvailableDate> getAppartmentDates() {
		return appartmentDates;
	}
	public void setAppartmentDates(List<AvailableDate> appartmentDates) {
		this.appartmentDates = appartmentDates;
	}
	public Host getHost() {
		return host;
	}
	public void setHost(Host host) {
		this.host = host;
	}
	public List<ApartmentComment> getComments() {
		return comments;
	}
	public void setComments(List<ApartmentComment> comments) {
		this.comments = comments;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPriceNight() {
		return priceNight;
	}
	public void setPriceNight(double priceNight) {
		this.priceNight = priceNight;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	public boolean isAppStatus() {
		return appStatus;
	}
	public void setAppStatus(boolean appStatus) {
		this.appStatus = appStatus;
	}
	public List<Amenities> getAmenities() {
		return amenities;
	}
	public void setAmenities(List<Amenities> amenities) {
		this.amenities = amenities;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
}
