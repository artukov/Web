package beans;

public class Admin extends User {

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
		this.setRole(UserRole.ADMIN);
	}
	
	public Admin(User user) {
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setName(user.getName());
		this.setLastName(user.getLastName());
		this.setGender(user.getGender());
		this.setRole(UserRole.ADMIN);
		
		
	}

}
