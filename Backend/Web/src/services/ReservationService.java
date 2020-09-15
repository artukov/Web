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
import beans.Apartment;
import beans.ApartmentType;
import beans.AvailableDate;
import beans.Guest;
import beans.Host;
import beans.User;
import beans.Location;
import beans.Reservation;
import beans.ReservationStatus;
import dao.ApartmentDAO;
import dao.ReservationDAO;
import dao.UserDAO;

@Path("/reservation")
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
	
	@GET
	@Path("/reservations")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservations(@Context HttpServletRequest request) {
		ReservationDAO reservationDAO = (ReservationDAO) ctx.getAttribute("reservationDAO");
//		User korisnik = new User("uganda", "sifra", "Bojan", "Simeunoviski", GenderEnum.MALE, UserRole.GUEST);
//		User korisnik2 = new User("peru", "sifra", "Nikola", "Artukov", GenderEnum.MALE, UserRole.ADMIN);
		Address adresa = new Address("Marka miljanova", 5, "Novi Sad", 21000);
		Location loca1 = new Location((double)10,(double)10, adresa);
		Location loca2 = new Location((double)20,(double)42,adresa);
		AvailableDate ad = new AvailableDate(new Date(),true);
		List<AvailableDate> lista = new ArrayList();
		lista.add(ad);
		Apartment apartman = new Apartment(ApartmentType.COMPLETE,1,2,loca1,lista);
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
		
		Reservation res = new Reservation(apartman);


		reservationDAO.dodaj(res, contextPath);
		
		
		Collection<Reservation> reservations = reservationDAO.getReservations().values();
		return Response.status(200).entity(reservations).build();
	}
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpServletRequest request) {
		ReservationDAO resDAO = (ReservationDAO) this.ctx.getAttribute("reservationDAO");
		Collection<Reservation> reservations = resDAO.getReservations().values();
		return Response.ok().entity(reservations).build();
	}
	
	@GET
	@Path("/hostAll/{hostUsername}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllHostReservations(@Context HttpServletRequest request, @PathParam("hostUsername") String hostUsername) {
		UserDAO dao = (UserDAO)this.ctx.getAttribute("userDAO");
		
		Host host = (Host)dao.searchUsers(hostUsername);
		if(host == null) {
			return Response.status(400).entity("Pogresan korisnika").build();
		}
		ReservationDAO resDAO = (ReservationDAO)this.ctx.getAttribute("reservationDAO");

		Collection<Reservation> reservations = resDAO.getReservations().values();
		Collection<Reservation> hostRes= new ArrayList<Reservation>();
		
		for(Reservation res : reservations) {
			if(res.getApartment().getHost().getUsername() == host.getUsername()) {
				hostRes.add(res);
			}
		}
		
		return Response.status(200).entity(hostRes).build();
	}
	
	@GET
	@Path("/guestAll/{guestUsername}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllGuestReservations(@Context HttpServletRequest request, @PathParam("guestUsername") String guestUsername) {
		UserDAO dao = (UserDAO)this.ctx.getAttribute("userDAO");
		
		Guest guest = (Guest)dao.searchUsers(guestUsername);
		if(guest == null) {
			return Response.status(400).entity("Pogresan korisnika").build();
		}
		ReservationDAO resDAO = (ReservationDAO)this.ctx.getAttribute("reservationDAO");

		Collection<Reservation> reservations = resDAO.getReservations().values();
		
		
		return Response.status(200).entity(reservations).build();
	}
	
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewReservation(Reservation reservation, @Context HttpServletRequest request) {
		ReservationDAO resDAO=(ReservationDAO)this.ctx.getAttribute("reservationDAO");
		
		String contextPath = ctx.getRealPath("");
		
		Collection<Reservation> reservations = resDAO.getReservations().values();

		resDAO.dodaj(reservation, contextPath);
		return Response.status(200).entity(reservations).build();
		
	}
	
	@PUT
	@Path("/changeStatus/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reservationStatus(@Context HttpServletRequest request, @PathParam("id") UUID id,ReservationStatus resStatus) {
		ReservationDAO reservationDAO = (ReservationDAO) this.ctx.getAttribute("reservationDAO");
		String contextPath = ctx.getRealPath("");
		Collection<Reservation> reservations = reservationDAO.getReservations().values();
		for(Reservation res : reservations) {
			if(id.equals(res.getId())) {
				res.setStatusRes(resStatus);
				reservationDAO.dodaj(res,contextPath);
			}
		}
		return Response.status(200).entity(reservations).build();
	}
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchReservations(Reservation reservation, @Context HttpServletRequest request) {
		ReservationDAO reservationDAO = (ReservationDAO) ctx.getAttribute("reservationDAO");
		String contextPath = ctx.getRealPath("");
		Collection<Reservation> reservations = (Collection<Reservation>) reservationDAO.searchReservations(reservation);
		return Response.status(200).entity(reservations).build();
	}

}
