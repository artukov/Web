package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Address;
import beans.Amenities;
import beans.Apartment;
import beans.ApartmentType;
import beans.AvailableDate;
import beans.Location;
import beans.Reservation;
import beans.ReservationStatus;
import beans.User;
import dao.AddressDAO;
import dao.AmenitiesDAO;
import dao.ApartmentDAO;
import dao.LocationDAO;
import dao.ReservationDAO;
import dao.UserDAO;

@Path("")
public class ReservationService {
	
	
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
		if(ctx.getAttribute("reservationDAO") == null) {
			this.ctx.setAttribute("reservationDAO", new ReservationDAO(this.ctx.getRealPath("/")));
		}
	}
	
//	@GET
//	@Path("/reservations")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getReservations(@Context HttpServletRequest request) {
//		ReservationDAO reservationDAO = (ReservationDAO) ctx.getAttribute("reservationDAO");
////		User korisnik = new User("uganda", "sifra", "Bojan", "Simeunoviski", GenderEnum.MALE, UserRole.GUEST);
////		User korisnik2 = new User("peru", "sifra", "Nikola", "Artukov", GenderEnum.MALE, UserRole.ADMIN);
//		Address adresa = new Address("Marka miljanova", 5, "Novi Sad", 21000);
//		Location loca1 = new Location((double)10,(double)10, adresa);
//		Location loca2 = new Location((double)20,(double)42,adresa);
//		AvailableDate ad = new AvailableDate(new Date(),true);
//		List<AvailableDate> lista = new ArrayList();
//		lista.add(ad);
//		Apartment apartman = new Apartment(ApartmentType.COMPLETE,1,2,loca1,lista);
//		String contextPath = ctx.getRealPath("");
////		userDao.dodaj(korisnik,contextPath);
////		userDao.dodaj(korisnik2, contextPath);
//		
//		
////		String str = String.valueOf(1);
////		HashMap has = new HashMap();
////		has.put(str, loca1);
////		
////		String str2 = String.valueOf(2);
////		has.put(str2, loca2);
////		
////		
////		locationDAO.dodajuFile(has, contextPath);
//		
//		Reservation res = new Reservation(apartman);
//
//
//		reservationDAO.dodaj(res, contextPath);
//		
//		
//		Collection<Reservation> reservations = reservationDAO.getReservations().values();
//		return Response.status(200).entity(reservations).build();
//	} 
	
	
	@POST
	@Path("addReservation/{startDate}/{numberNights}/{reservationMessage}/{guest}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createReservation(Apartment apartment, @PathParam("startDate") String startDate, @PathParam("numberNights") Integer numberNights, @PathParam("reservationMessage") String reservationMessage, @PathParam("guest") String guest, @Context HttpServletRequest request) {
		User admin = (User) request.getSession().getAttribute("user");
//		if(admin.getRole() != UserRole.ADMIN) {
//			return Response.status(403).entity("Samo admin ima dozvolu").build();
//		}
		
		ReservationDAO reservationDAO = (ReservationDAO) this.ctx.getAttribute("reservationDAO");
		String contextPath = ctx.getRealPath("");
		Reservation reservation = new Reservation(apartment,startDate,numberNights,(double)(apartment.getPriceNight()*numberNights),reservationMessage,guest, ReservationStatus.CREATED);
		reservationDAO.dodaj(reservation,contextPath);
		Collection<Reservation> reservations = reservationDAO.getReservations().values();
		
//		HashMap<String,Amenities> amenityMap = new HashMap<String,Amenities>();
//		amenityMap.put(amenities.getName(),amenities);
//		amenitiesDAO.dodajuFile(amenityMap,contextPath);
//		Collection<Amenities> amenitiess = amenitiesDAO.getAmenitiess().values();
		
//		try {
//			amenitiesDAO.saveAmenities();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return Response.status(500).entity("nije sacuvano,GRESKA").build();
//		}
		
		return Response.status(200).entity(reservations).build();
		
	}
	
	
	@GET
	@Path("guestReservations/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGuestReservations(@PathParam("username") String username, @Context HttpServletRequest request) {
		ReservationDAO reservationDAO = (ReservationDAO) this.ctx.getAttribute("reservationDAO");
		String contextPath = ctx.getRealPath("");
		Collection<Reservation> reservations = reservationDAO.getGuestReservations(username);
		return Response.status(200).entity(reservations).build();
	}
	
	@GET
	@Path("hostReservations/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHostReservations(@PathParam("username") String username, @Context HttpServletRequest request) {
		ReservationDAO reservationDAO = (ReservationDAO) this.ctx.getAttribute("reservationDAO");
		String contextPath = ctx.getRealPath("");
		Collection<Reservation> reservations = reservationDAO.getHostReservations(username);
		return Response.status(200).entity(reservations).build();
	}
	
	@GET
	@Path("reservations")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservations(@Context HttpServletRequest request) {
		ReservationDAO reservationDAO = (ReservationDAO) this.ctx.getAttribute("reservationDAO");
		String contextPath = ctx.getRealPath("");
		Collection<Reservation> reservations = reservationDAO.getReservations().values();
		return Response.status(200).entity(reservations).build();
	}
	
	@PUT
	@Path("withdrawReservation/{id}/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response withdrawReservation(@PathParam("id") UUID id, @PathParam("username") String username, @Context HttpServletRequest request) {
		ReservationDAO reservationDAO = (ReservationDAO) this.ctx.getAttribute("reservationDAO");
		String contextPath = ctx.getRealPath("");
		reservationDAO.withdrawReservation(id);
		Collection<Reservation> reservations = reservationDAO.getGuestReservations(username);
		return Response.status(200).entity(reservations).build();
	}
	
	@PUT
	@Path("denyReservation/{id}/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response denyReservation(@PathParam("id") UUID id, @PathParam("username") String username, @Context HttpServletRequest request) {
		ReservationDAO reservationDAO = (ReservationDAO) this.ctx.getAttribute("reservationDAO");
		String contextPath = ctx.getRealPath("");
		reservationDAO.denyReservation(id);
		Collection<Reservation> reservations = reservationDAO.getHostReservations(username);
		return Response.status(200).entity(reservations).build();
	}
	
	@PUT
	@Path("acceptReservation/{id}/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response acceptReservation(@PathParam("id") UUID id, @PathParam("username") String username, @Context HttpServletRequest request) {
		ReservationDAO reservationDAO = (ReservationDAO) this.ctx.getAttribute("reservationDAO");
		String contextPath = ctx.getRealPath("");
		reservationDAO.acceptReservation(id);
		Collection<Reservation> reservations = reservationDAO.getHostReservations(username);
		return Response.status(200).entity(reservations).build();
	}
}
