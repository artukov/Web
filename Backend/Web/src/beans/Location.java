package beans;

import java.util.UUID;

public class Location {
	private Double longitude;
	private Double latitude;
	private Address address;
	private UUID id;
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Location(Double longitude, Double latitude, Address address) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.id = UUID.randomUUID();
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
