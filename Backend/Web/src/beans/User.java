package beans;

public class User {

		private String username;
		private String password;
		private String name;
		private String lastName;
		private GenderEnum gender;
		private UserRole role;
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		public User(User u) {
			super();
			this.username = u.username;
			this.password = u.password;
			this.name = u.name;
			this.lastName = u.lastName;
			this.gender = u.gender;
			this.role = u.role;
		}
		public User(String username, String password, String name, String lastName, GenderEnum gender, UserRole role) {
			super();
			this.username = username;
			this.password = password;
			this.name = name;
			this.lastName = lastName;
			this.gender = gender;
			this.role = role;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public GenderEnum getGender() {
			return gender;
		}
		public void setGender(GenderEnum gender) {
			this.gender = gender;
		}
		public UserRole getRole() {
			return role;
		}
		public void setRole(UserRole role) {
			this.role = role;
		}
		
		
		
}
