package beans;

import java.util.List;

public class Guest extends User {
	private List<Apartment> rented;
	private List<Reservation> reservations;
	public Guest() {
		super();
		// TODO Auto-generated constructor stub
		this.setRole(UserRole.GUEST);
	}
	
	public Guest(User user) {
		this.setName(user.getName());
		this.setLastName(user.getLastName());
		this.setPassword(user.getPassword());
		this.setGender(user.getGender());
		this.setUsername(user.getUsername());
		this.setRole(user.getRole());
	}
	
	public Guest(String username, String password, String name, String lastName, GenderEnum gender, UserRole role) {
		super(username, password, name, lastName, gender, role);
		// TODO Auto-generated constructor stub
	}
	public Guest(List<Apartment> rented, List<Reservation> reservations) {
		super();
		this.rented = rented;
		this.reservations = reservations;
	}
	public List<Apartment> getRented() {
		return rented;
	}
	public void setRented(List<Apartment> rented) {
		this.rented = rented;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
}
