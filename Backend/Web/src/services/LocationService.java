package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Apartment;
import beans.GenderEnum;
import beans.Location;
import beans.User;
import beans.UserRole;
import dao.ApartmentDAO;
import dao.LocationDAO;
import dao.UserDAO;

@Path("")
public class LocationService {
	
	@Context
	ServletContext ctx;
	
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vise puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("locationDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("locationDAO", new UserDAO(contextPath));
		}
	}
	
	
	@GET
	@Path("/locations")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Location> getAllLocations(@Context HttpServletRequest request){
		
		ApartmentDAO apDAO = (ApartmentDAO) this.ctx.getAttribute("locationDAO");
		return (ArrayList<Apartment>) apDAO.getAllActive();
		
	}
	
	
	@GET
	@Path("/locations")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Location> getLocations(@Context HttpServletRequest request) {
		LocationDAO locationDAO = (LocationDAO) ctx.getAttribute("locationDAO");
//		User korisnik = new User("uganda", "sifra", "Bojan", "Simeunoviski", GenderEnum.MALE, UserRole.GUEST);
//		User korisnik2 = new User("peru", "sifra", "Nikola", "Artukov", GenderEnum.MALE, UserRole.ADMIN);
		String contextPath = ctx.getRealPath("");
//		userDao.dodaj(korisnik,contextPath);
//		userDao.dodaj(korisnik2, contextPath);
		//		userDao.add(korisnik);
		Collection<Location> locations = locationDAO.getLocations().values();
		return locations;
	}

}
