package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import javax.ws.rs.core.Response;

import beans.Address;
import beans.Admin;
import beans.Apartment;
import beans.GenderEnum;
import beans.Location;
import beans.User;
import beans.UserRole;
import dao.AddressDAO;
import dao.ApartmentDAO;
import dao.LocationDAO;
import dao.ReservationDAO;
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
		
		if(ctx.getAttribute("userDAO") == null) {
			this.ctx.setAttribute("userDAO", new UserDAO(this.ctx.getRealPath("/")));
		}
		if(ctx.getAttribute("locationDAO") == null) {
			this.ctx.setAttribute("locationDAO", new LocationDAO(this.ctx.getRealPath("/")));
		}
		if(ctx.getAttribute("addressDAO") == null) {
			this.ctx.setAttribute("addressDAO", new AddressDAO(this.ctx.getRealPath("/")));
		}
//		if(ctx.getAttribute("reservationDAO") == null) {
//			this.ctx.setAttribute("reservationDAO", new ReservationDAO(this.ctx.getRealPath("/")));
//		}
//		if(ctx.getAttribute("addressDAO") == null) {
//			String contextPath = ctx.getRealPath("");
//			ctx.setAttribute("addressDAO", new AddressDAO(contextPath));
//		}
	}
	
	
//	@GET
//	@Path("/locations")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Location> getAllLocations(@Context HttpServletRequest request){
//		
//		LocationDAO apDAO = (LocationDAO) this.ctx.getAttribute("locationDAO");
//		return (ArrayList<Location>) apDAO.getLocations();
//		
//	}
	
	
	@GET
	@Path("/locations")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocations(@Context HttpServletRequest request) {
		LocationDAO locationDAO = (LocationDAO) ctx.getAttribute("locationDAO");
//		User korisnik = new User("uganda", "sifra", "Bojan", "Simeunoviski", GenderEnum.MALE, UserRole.GUEST);
//		User korisnik2 = new User("peru", "sifra", "Nikola", "Artukov", GenderEnum.MALE, UserRole.ADMIN);
		Address adresa = new Address("Marka Miljanova", 5, "Novi Sad", 21000);
		Address adresa2 = new Address("Episkopa Visariona", 10, "Beograd", 11000);
		Location loca1 = new Location((double)10,(double)10, adresa);
		Location loca2 = new Location((double)20,(double)42,adresa2);
		String contextPath = ctx.getRealPath("");
//		userDao.dodaj(korisnik,contextPath);
//		userDao.dodaj(korisnik2, contextPath);
		
		
//		String str = String.valueOf(1);
//		HashMap has = new HashMap();
//		has.put(str, loca1);
//		
//		String str2 = String.valueOf(2);
//		has.put(str2, loca2);
//		
//		
//		locationDAO.dodajuFile(has, contextPath);


		locationDAO.dodaj(loca1, contextPath);
		locationDAO.dodaj(loca2, contextPath);
		
		
		Collection<Location> locations = locationDAO.getLocations().values();
		return Response.status(200).entity(locations).build();
	}
	
	
	@GET
	@Path("/addresses")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAddresses(@Context HttpServletRequest request) {
		AddressDAO addressDAO = (AddressDAO) ctx.getAttribute("addressDAO");
//		User korisnik = new User("uganda", "sifra", "Bojan", "Simeunoviski", GenderEnum.MALE, UserRole.GUEST);
//		User korisnik2 = new User("peru", "sifra", "Nikola", "Artukov", GenderEnum.MALE, UserRole.ADMIN);
		Address adresa1 = new Address("Marka miljanova", 5, "Novi Sad", 21000);
		Address adresa2 = new Address("Kosovska", 30, "Novi Sad", 21000);
		String contextPath = ctx.getRealPath("");
//		userDao.dodaj(korisnik,contextPath);
//		userDao.dodaj(korisnik2, contextPath);
		addressDAO.dodaj(adresa1, contextPath);
		addressDAO.dodaj(adresa2, contextPath);
//		locationDAO.dodaj(loca1, contextPath);
		//		userDao.add(korisnik);
		Collection<Address> addresses = addressDAO.getAddresss().values();
		return Response.status(200).entity(addresses).build();
	}
	
//	@GET
//	@Path("/locations")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getLocations(@Context HttpServletRequest request) {
//		Address adresa = new Address("ldkasj",2,"Novi Sad",21000);
//		Location loca1 = new Location((double)10,(double)10,adresa);
//		return Response.status(200).entity(loca1).build(); 
//	}
	
	
//	@GET
//	@Path("/locations")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAllApartments(@Context HttpServletRequest request) {
//		
//		LocationDAO locationDAO = (LocationDAO)this.ctx.getAttribute("locationDAO");
//		Admin admin = (Admin) request.getSession().getAttribute("user");
//		
//		if(admin == null) {
//			return Response.status(403).build();
//		}
//		
//		return Response.ok().entity(locationDAO.findAll()).build();
//	}
	
	
	

}
