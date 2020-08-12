package services;



import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.User;
import dao.UserDAO;

@Path("")
public class RegisterService {
	
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
	
//	@PostConstruct
//	public void init() {
//		String contextPath = ctx.getRealPath("/");
//		if (ctx.getAttribute("userDAO") == null) {
//			ctx.setAttribute("userDAO", new UserDAO(contextPath));
//		}
//	}
	
	
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(User user, @Context HttpServletRequest request) { //vidi dal user ili response pa sta onda
		System.out.print("ajmooo");
		System.out.println(user);
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		boolean loggedUser = userDao.find(user.getUsername());
		System.out.println(loggedUser+"register klasa");
		if (loggedUser== true) {
			//return Response.status(400).entity("Korisnicko ime vec postoji!").build();
			System.out.println("Idemoo");
			return Response.status(400).build();
		}
		
		boolean ime=isValidExpression(user.getName());
		boolean prezime=isValidExpression(user.getLastName());
		
		
		if(!ime) return Response.status(401).build();
		if(!prezime) return Response.status(402).build();
		
		
		String contextPath = ctx.getRealPath("");
		userDao.dodaj(user,contextPath);
		System.out.println(userDao);
		
		return Response.status(200).build();
	}
	
	
//	@POST
//	@Path("/register")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public User register(User user, @Context HttpServletRequest request) { //vidi dal user ili response pa sta onda
//		System.out.print("ajmooo");
//		System.out.println(user);
//		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
//		boolean loggedUser = userDao.find(user.getUsername());
//		User userLog = userDao.find(user.getUsername(), user.getPassword());
//		System.out.println(loggedUser+"register klasa");
//		if (loggedUser== true) {
//			//return Response.status(400).entity("Korisnicko ime vec postoji!").build();
//			System.out.println("Idemoo");
//			return null;
//		}
//		
//		boolean ime=isValidExpression(user.getName());
//		boolean prezime=isValidExpression(user.getLastName());
//		
//		
//		if(!ime) return null;;
//		if(!prezime) return null;;
//		
//		
//		String contextPath = ctx.getRealPath("");
//		userDao.dodaj(user,contextPath);
//		System.out.println(userDao);
//		
//		return userLog;
//	}
	
	
//	@POST
//	@Path("/register")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response register(User user, @Context HttpServletRequest request) {
//
//		UserDAO users = (UserDAO) ctx.getAttribute("userDAO");
//		if (users == null) {
//			return Response.status(400).entity("Lista korisnika nije pronadjena").build();
//		}
//
//		User u = users.add(user);
//
//		if (u == null) {
//			return Response.status(400).entity("Korisnik vec postoji").build();
//		}
//
//		request.getSession().setAttribute("user", u);
//
//		return Response.status(200).entity(u).build();
//
//	}
//	
//	
	private boolean isValidExpression(String word) {
		String regex = "^[\\p{L} ]*$";
		return Pattern.matches(regex, word);
	}
	
}
