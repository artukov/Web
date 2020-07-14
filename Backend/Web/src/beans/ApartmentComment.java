package beans;

public class ApartmentComment {

	private Guest guest;
	private Apartment apartment;
	private String text;
	private Integer Grade;
	
	
	
	public ApartmentComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApartmentComment(Guest guest, Apartment apartment, String text, Integer grade) {
		super();
		this.guest = guest;
		this.apartment = apartment;
		this.text = text;
		Grade = grade;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	public Apartment getApartment() {
		return apartment;
	}
	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getGrade() {
		return Grade;
	}
	public void setGrade(Integer grade) {
		Grade = grade;
	}
	
	
	
	
	
}
