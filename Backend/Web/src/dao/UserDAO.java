package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.User;

public class UserDAO {

	private HashMap<String, User> users = new HashMap<>();
	
	private UserDAO() {
		
		
	}
	public UserDAO(String contextPath) {
		loadUsers(contextPath);
	}
	
	public User find(String username,String password) {
		if (!users.containsKey(username)) {
			return null;
		}
		User user = users.get(username);
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}
	public HashMap<String, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}
	
	public boolean find(String username) {
		if (!users.containsKey(username)) {
			return false;
		}
		
		return true;
	}
	
	public User searchUsers(String u) {
		if (!users.containsKey(u)) {
			return null;
		}
		User user = users.get(u);
	
		return user;
	}
	
	public Collection<User> findAll() {
		return users.values();
	}
	
	public void dodaj(User u, String contextPath)
	{
				
		try
		{
			File file = new File(contextPath + "/users.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<User> proba=new ArrayList<>();
			//proba.add(new User("pera","pera","Petar","Petrovic",0,"06055555","fasf@casc.com", "08-09-1995"));
			//proba.add(new User("jov","jov","jovana","jov",0,"0605020313","asfa@gmail.com", "06-06-1985"));
			//objectMapper.writeValue(new File(contextPath + "/users.json"), proba);
			
			User[] car = objectMapper.readValue(file, User[].class);
			System.out.println("register User: "+car);
			
			//objectMapper.writeValue(new File(contextPath + "/proba.json"), new User("asfas","joasfasv","jov","jov",0,"jov","jov", "jov"));
			
			for(User g : car)
			{
				proba.add(g);
			}
			proba.add(u);  //sve koji su bili + novi, mapa i fajl moraju biti uskladjeni
			objectMapper.writeValue(new File(contextPath + "/users.json"), proba);
			User r=users.put(u.getUsername(), u);
			
			System.out.println(users);
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
		this.users.put(u.getUsername(),u);
		
	}
	
	
	private void loadUsers(String contextPath) {
		try
		{
			File file = new File(contextPath + "/users.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true); //nebitno, kopiramo
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			
			ArrayList<User> proba=new ArrayList<>();
			
			//OVO JE ZA DEFAULT VREDNOSTI, napravi sve sama pred projekat i zakomentarisi ovo
			//proba.add(new User("pera","pera","Petar","Petrovic",0,"06055555","fasf@casc.com", "08-09-1995"));
			//proba.add(new User("jov","jov","jovana","jov",1,"0605020313","asfa@gmail.com", "06-06-1985"));
			//objectMapper.writeValue(new File(contextPath + "/users.json"), proba);
			
			
			User[] car = objectMapper.readValue(file, User[].class);
			System.out.println("load User: "+car);
			
			//objectMapper.writeValue(new File(contextPath + "/proba.json"), new User("asfas","joasfasv","jov","jov",0,"jov","jov", "jov"));
			
			for(User u : car)
			{
				users.put(u.getUsername(),u);
			}
			
			System.out.println(users);
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
	}
	@Override
	public String toString() {
		return "UserDAO [users=" + users + "]";
	}
	
	public void stavi(User restoran) {
		// TODO Auto-generated method stub
		
		users.put(restoran.getUsername(), restoran);
		
	}
	
	public void dodajuFile(HashMap<String, User> users, String contextPath)
	{
				
		try
		{
			File file = new File(contextPath + "/users.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

			ArrayList<User> proba=new ArrayList<>();
			
					
			for(User g : users.values())
			{
				proba.add(g);
			}
			objectMapper.writeValue(new File(contextPath + "/users.json"), proba);
			
			System.out.println(users +" u file");
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
	}
	
	
	
	
	
	public User add(User user) {
		
		if (!users.containsKey(user.getUsername())) {
			users.put(user.getUsername(), user);
			return users.get(user.getUsername());
		}
		
		return null;
	}

	
	public void remove(User user) {
		if (users.containsKey(user.getUsername())) {
			users.remove(user.getUsername(), user);
		}
	}

//	public void save() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			objectMapper.writeValue(new File(this.path + "/users.json"), this.users);
//		} catch (IOException e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//}

}



