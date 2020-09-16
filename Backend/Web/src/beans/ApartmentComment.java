package beans;

public class ApartmentComment {

	private Guest guest;
	private Apartment apartment;
	private String text;
	private Integer Grade;
	
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
	public boolean Equals(ApartmentComment comment) {
		// TODO Auto-generated method stub
		if(this.getGuest() != comment.getGuest()) {
			return false;
		}
		if(this.getGrade() != comment.getGrade()) {
			return false;
		}
		if(this.getText() != comment.getText()) {
			return false;
		}
		
		return true;
	}
	
	
	
	
}
