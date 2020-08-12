package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.User;
import dao.UserDAO;

@Path("")
public class LoginService {

	@Context
	ServletContext ctx;
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira visse puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("userDAO") == null) {
	    	String contextPath = ctx.getRealPath("");  //relativna putanja, isto za sve
			ctx.setAttribute("userDAO", new UserDAO(contextPath));
		}
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(User user, @Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		
		User loggedUser = userDao.find(user.getUsername(), user.getPassword());
		System.out.println(loggedUser);
		if (loggedUser== null) {
			System.out.println("Nema takog");
			return null;
		}
		request.getSession().setAttribute("user", loggedUser);
		System.out.println(userDao);
		return loggedUser;
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
////		boolean ime=isValidExpression(user.getName());
////		boolean prezime=isValidExpression(user.getLastName());
//		
//		
////		if(!ime) return null;;
////		if(!prezime) return null;;
//		
//		
//		String contextPath = ctx.getRealPath("");
//		userDao.dodaj(user,contextPath);
//		System.out.println(userDao);
//		
//		return userLog;
//	}
	
	@GET
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(@Context HttpServletRequest request) {
		request.getSession().invalidate();
		return Response.status(200).build();
	}
	
	@GET
	@Path("/currentUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User currentUser(@Context HttpServletRequest request) {
		return (User) request.getSession().getAttribute("user");
	}
	
	@PUT
	@Path("/changeUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User changeUser(User user, @Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		String contextPath = ctx.getRealPath("");
		userDao.dodaj(user,contextPath); //proveri jel dobro ovo da nece napraviti duplog
		return user;		
		
//		User userToChange = (User) request.getSession().getAttribute("user");
//		userToChange.setUsername(user.getUsername());
//		userToChange.setName(user.getName());
//		userToChange.setPassword(user.getPassword());
//		userToChange.setRole(user.getRole());
//		userToChange.setGender(user.getGender());
		
	}
}
