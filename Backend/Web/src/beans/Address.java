package beans;

public class Address {
	private String street;
	private Integer number;
	private String city;
	private Integer postalCode;
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String street, Integer number, String city, Integer postalCode) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}
	
	
}
