package beans;

import java.util.List;

public class Host extends User {

	private List<Apartment> forRent;

	public Host() {
		super();
		// TODO Auto-generated constructor stub
		this.setRole(UserRole.HOST);
	}

	public Host(User user) {
		this.setName(user.getName());
		this.setLastName(user.getLastName());
		this.setPassword(user.getPassword());
		this.setGender(user.getGender());
		this.setUsername(user.getUsername());
		this.setRole(user.getRole());
	}
	public Host(String username, String password, String name, String lastName, GenderEnum gender, UserRole role) {
		super(username, password, name, lastName, gender, role);
		// TODO Auto-generated constructor stub
	}

	public Host(List<Apartment> forRent) {
		super();
		this.forRent = forRent;
	}

	public List<Apartment> getForRent() {
		return forRent;
	}

	public void setForRent(List<Apartment> forRent) {
		this.forRent = forRent;
	}
	
	
}

