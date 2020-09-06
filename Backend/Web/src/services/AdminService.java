package services;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Address;
import beans.GenderEnum;
import beans.User;
import beans.UserRole;
import dao.UserDAO;

@Path("")
public class AdminService {
	
	@Context
	ServletContext ctx;
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vise puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("userDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("userDAO", new UserDAO(contextPath));
		}
	}
	
	
	
//	@POST
//	@Path("/search")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<User> register(User user, @Context HttpServletRequest request) { //vidi dal user ili response pa sta onda
//		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
//		User korisnik = new User("uganda", "sifra", "Bojan", "Simeunoviski", GenderEnum.MALE, UserRole.GUEST);
//		userDao.add(korisnik);
//		Collection<User> users = userDao.getUsers().values();
//		Collection<User> returnList = new ArrayList<>();
//		
//		for(User u : users) {
//			if(!user.getUsername().equals("")) {
//				if(!u.getUsername().equals(user.getUsername())) {
//					continue;
//				}
//			}
//			if(!user.getName().equals("")) {
//				if(!u.getName().equals(user.getName())) {
//					continue;
//				}
//			}
//			if(!user.getLastName().equals("")) {
//				if(!u.getLastName().equals(user.getLastName())) {
//					continue;
//				}
//			}
//			if(user.getGender() != null) {
//				if(!u.getGender().equals(user.getGender())) {
//					continue;
//				}
//			}
//			if(user.getRole() != null) {
//				if(!u.getRole().equals(user.getRole())) {
//					continue;
//				}
//			}
//			
//			returnList.add(u);
//		}
//		
//		return returnList;
//	}
	
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(User user, @Context HttpServletRequest request) { //vidi dal user ili response pa sta onda
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		User korisnik = new User("uganda", "sifra", "Bojan", "Simeunoviski", GenderEnum.MALE, UserRole.GUEST);
		User korisnik2 = new User("ugandaa", "sifra", "Bojan", "Simeunovski", GenderEnum.MALE, UserRole.GUEST);
		userDao.add(korisnik);
		userDao.add(korisnik2);
		Collection<User> users = userDao.getUsers().values();
		Collection<User> returnList = new ArrayList<>();
		
		
		for(User u : users) {
			if(!user.getUsername().equals("")) {
				if(!u.getUsername().equals(user.getUsername())) {
					continue;
				}
			}
			if(!user.getName().equals("")) {
				if(!u.getName().equals(user.getName())) {
					continue;
				}
			}
			if(!user.getLastName().equals("")) {
				if(!u.getLastName().equals(user.getLastName())) {
					continue;
				}
			}
			if(user.getGender() != null) {
				if(!u.getGender().equals(user.getGender())) {
					continue;
				}
			}
			if(user.getRole() != null) {
				if(!u.getRole().equals(user.getRole())) {
					continue;
				}
			}	
			returnList.add(u);
		}
		
//		String a = new String("ajmo");
//		return a;
		return Response.status(Response.Status.OK).entity(returnList).build();
	}
	
	
	@GET
	@Path("/users")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getUsers(@Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		User korisnik = new User("uganda", "sifra", "Bojan", "Simeunoviski", GenderEnum.MALE, UserRole.GUEST);
		User korisnik2 = new User("peru", "sifra", "Nikola", "Artukov", GenderEnum.MALE, UserRole.ADMIN);
		String contextPath = ctx.getRealPath("");
		userDao.dodaj(korisnik,contextPath);
		userDao.dodaj(korisnik2, contextPath);
		//		userDao.add(korisnik);
		
		Collection<User> users = userDao.getUsers().values();
		return users;
	}
	
	
//	@GET
//	@Path("/locations")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getLocations(@Context HttpServletRequest request) {
//		Address adresa = new Address("ldkasj",2,"Novi Sad",21000);
//		return Response.status(200).entity(adresa).build(); 
//	}
	
	
}
