package beans;

public class ApartmentComment {

	private String guest;
	private Apartment apartment;
	private String text;
	private Integer grade;
	private boolean visible;
	
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public ApartmentComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ApartmentComment(String guest, Apartment apartment, String text, Integer grade, boolean visible) {
		super();
		this.guest = guest;
		this.apartment = apartment;
		this.text = text;
		this.grade = grade;
		this.visible = visible;
	}
	
	public ApartmentComment(String guest, Apartment apartment, String text, Integer grade) {
		super();
		this.guest = guest;
		this.apartment = apartment;
		this.text = text;
		this.grade = grade;
	}
	
	public ApartmentComment(String text, Integer grade, boolean visible) {
		super();
		this.text = text;
		this.grade = grade;
		this.visible = visible;
	}
	
	public String getGuest() {
		return guest;
	}
	public void setGuest(Guest String) {
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
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
	
	
	
}
