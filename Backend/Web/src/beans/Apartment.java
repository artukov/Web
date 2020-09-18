package beans;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//import org.apache.tomcat.jni.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Apartment {
	private ApartmentType apartmentType;
	private Integer numberRooms;
	private Integer guestNumber;
	private Integer location;
	private HashMap<Date,ReservationStatus> appartmentDates;
	private String host;
	private List<ApartmentComment> comments;
	private String image;
	private Integer priceNight;
	private String checkIn;
	private String checkOut;
	private boolean appStatus;
	private List<String> amenities;
	private List<Reservation> reservations;
	private UUID id;
	
	public Apartment() {
		super();
		this.id = UUID.randomUUID();
		// TODO Auto-generated constructor stub
	}
	
	public Apartment(Integer numberRooms, Integer guestNumber, Integer location, Integer priceNight, boolean appStatus) {
		this.numberRooms = numberRooms;
		this.guestNumber = guestNumber;
		this.location = location;
		this.priceNight = priceNight;
		this.appStatus = appStatus;
		this.id = UUID.randomUUID();
	}
	
	public Apartment(Integer numberRooms, Integer guestNumber, Integer location, String host, Integer priceNight, boolean appStatus) {
		this.numberRooms = numberRooms;
		this.guestNumber = guestNumber;
		this.location = location;
		this.host = host;
		this.priceNight = priceNight;
		this.appStatus = appStatus;
		this.id = UUID.randomUUID();
	}
	
	public Apartment(ApartmentType apartmentType, Integer numberRooms, Integer guestNumber, Integer location, Integer priceNight) {
		this.appartmentDates = appartmentDates;
		this.location = location;
		this.priceNight = priceNight;
		this.numberRooms = numberRooms;
		this.guestNumber = guestNumber;
		this.id = UUID.randomUUID();
	}
	
	public Apartment(Integer location, Integer priceNight, Integer numberRooms,  Integer guestNumber) {
		this.appartmentDates = appartmentDates;
		this.location = location;
		this.priceNight = priceNight;
		this.numberRooms = numberRooms;
		this.guestNumber = guestNumber;
		this.id = UUID.randomUUID();
	}
	
	public Apartment(ApartmentType apartmentType, Integer numberRooms, Integer guestNumber, Integer location, String host, Integer priceNight, String checkIn, String checkOut, boolean appStatus, List<String> amenities) {
		this.apartmentType = apartmentType;
		this.numberRooms = numberRooms;
		this.guestNumber = guestNumber;
		this.location = location;
		this.host = host;
		this.priceNight = priceNight;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.appStatus = appStatus;
		this.amenities = amenities;
		this.id = UUID.randomUUID();
	}
	
	public Apartment(Integer numberRooms, Integer guestNumber, Integer location, List<ApartmentComment> comments, Integer priceNight, boolean appStatus) {
		this.numberRooms = numberRooms;
		this.guestNumber = guestNumber;
		this.location = location;
		this.comments = comments;
		this.priceNight = priceNight;
		this.appStatus = appStatus;
		this.id = UUID.randomUUID();
	}
	
	public Apartment(Integer numberRooms, Integer guestNumber, Integer location, String host, List<ApartmentComment> comments, Integer priceNight, boolean appStatus) {
		this.numberRooms = numberRooms;
		this.guestNumber = guestNumber;
		this.location = location;
		this.host = host;
		this.comments = comments;
		this.priceNight = priceNight;
		this.appStatus = appStatus;
		this.id = UUID.randomUUID();
	}
	
	public Apartment(ApartmentType apartmentType, Integer numberRooms, Integer guestNumber, Integer location, List<ApartmentComment> comments, Integer priceNight) {
		this.appartmentDates = appartmentDates;
		this.location = location;
		this.priceNight = priceNight;
		this.numberRooms = numberRooms;
		this.guestNumber = guestNumber;
		this.comments = comments;
		this.id = UUID.randomUUID();
	}
	
	public Apartment(Integer location, Integer priceNight, Integer numberRooms,  Integer guestNumber, List<ApartmentComment> comments) {
		this.appartmentDates = appartmentDates;
		this.location = location;
		this.priceNight = priceNight;
		this.numberRooms = numberRooms;
		this.guestNumber = guestNumber;
		this.comments = comments;
		this.id = UUID.randomUUID();
	}
	
	public Apartment(ApartmentType apartmentType, Integer numberRooms, Integer guestNumber, Integer location, String host, List<ApartmentComment> comments, Integer priceNight, String checkIn, String checkOut, boolean appStatus, List<String> amenities) {
		this.apartmentType = apartmentType;
		this.numberRooms = numberRooms;
		this.guestNumber = guestNumber;
		this.location = location;
		this.host = host;
		this.comments = comments;
		this.priceNight = priceNight;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.appStatus = appStatus;
		this.amenities = amenities;
		this.id = UUID.randomUUID();
	}
//	public Apartment(ApartmentType apartmentType, Integer numberRooms, Integer guestNumber, Location location,
//			List<AvailableDate> appartmentDates, Host host, List<ApartmentComment> comments, String image,
//			double priceNight, Time checkIn, Time checkOut, boolean appStatus, List<Amenities> amenities,
//			List<Reservation> reservations) {
//		super();
//		this.apartmentType = apartmentType;
//		this.numberRooms = numberRooms;
//		this.guestNumber = guestNumber;
//		this.location = location;
//		this.appartmentDates = appartmentDates;
//		this.host = host;
//		this.comments = comments;
//		this.image = image;
//		this.priceNight = priceNight;
//		this.checkIn = checkIn;
//		this.checkOut = checkOut;
//		this.appStatus = appStatus;
//		this.amenities = amenities;
//		this.reservations = reservations;
//		this.id = UUID.randomUUID();
//	}
	
//	public Apartment(ApartmentType apartmentType, Integer numberRooms, Integer guestNumber, Location location,
//			 Host host,
//			double priceNight, Time checkIn, Time checkOut, boolean appStatus, List<Amenities> amenities) {
//		super();
//		this.apartmentType = apartmentType;
//		this.numberRooms = numberRooms;
//		this.guestNumber = guestNumber;
//		this.location = location;
////		this.appartmentDates = appartmentDates;
//		this.host = host;
////		this.comments = comments;
////		this.image = image;
//		this.priceNight = priceNight;
//		this.checkIn = checkIn;
//		this.checkOut = checkOut;
//		this.appStatus = appStatus;
//		this.amenities = amenities;
////		this.reservations = reservations;
//		this.id = UUID.randomUUID();
//	}
	
//	public Apartment(ApartmentType apartmentType, Integer numberRooms, Integer guestNumber, Location location,
//			double priceNight, Time checkIn, Time checkOut, boolean appStatus, List<Amenities> amenities) {
//		super();
//		this.apartmentType = apartmentType;
//		this.numberRooms = numberRooms;
//		this.guestNumber = guestNumber;
//		this.location = location;
////		this.appartmentDates = appartmentDates;
////		this.host = host;
////		this.comments = comments;
////		this.image = image;
//		this.priceNight = priceNight;
//		this.checkIn = checkIn;
//		this.checkOut = checkOut;
//		this.appStatus = appStatus;
//		this.amenities = amenities;
////		this.reservations = reservations;
//		this.id = UUID.randomUUID();
//	}
	
	
	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
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
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public HashMap<Date,ReservationStatus> getAppartmentDates() {
		return appartmentDates;
	}
	public void setAppartmentDates(HashMap<Date,ReservationStatus> appartmentDates) {
		this.appartmentDates = appartmentDates;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
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
	public Integer getPriceNight() {
		return priceNight;
	}
	public void setPriceNight(Integer priceNight) {
		this.priceNight = priceNight;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public boolean isAppStatus() {
		return appStatus;
	}
	public void setAppStatus(boolean appStatus) {
		this.appStatus = appStatus;
	}
	public List<String> getAmenities() {
		return amenities;
	}
	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
}
