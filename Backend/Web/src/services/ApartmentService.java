package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
//import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Admin;
import beans.Apartment;
import beans.ApartmentComment;
import beans.AvailableDate;
import beans.Guest;
import beans.Host;
import beans.Location;
import beans.Reservation;
import beans.User;
import dao.ApartmentDAO;
import dao.LocationDAO;
import dao.UserDAO;

@Path("/apartment")
public class ApartmentService {

	@Context
	ServletContext ctx;
	
	public ApartmentService() {
		
		}
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("apartmentDAO") == null) {
			this.ctx.setAttribute("apartmentDAO", new ApartmentDAO(this.ctx.getRealPath("/")));
		}
		if(ctx.getAttribute("userDAO") == null) {
			this.ctx.setAttribute("userDAO", new UserDAO(this.ctx.getRealPath("/")));
		}
		if(ctx.getAttribute("locationDAO") == null) {
			this.ctx.setAttribute("locationDAO", new LocationDAO(this.ctx.getRealPath("/")));
		}
	}
	
	@GET
	@Path("/helloWorld")
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWOrld(@Context HttpServletRequest request) {
		ApartmentDAO apDAO = (ApartmentDAO)this.ctx.getAttribute("apartmentDAO");
		
		return Response.ok().entity(apDAO.findAll()).build();
		//return Response.status(200).entity("Hello World").build();
	}
	/**
	 * Metoda za izlistavanje svih apartmana
	 * Moze koristi samo admin
	 * */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllApartments(@Context HttpServletRequest request) {
		
		ApartmentDAO apDAO = (ApartmentDAO)this.ctx.getAttribute("apartmentDAO");
		Admin admin = (Admin) request.getSession().getAttribute("user");
		
//		if(admin == null) {
//			return Response.status(403).build();
//		}
		Collection<Apartment> apartments = apDAO.getApartments().values();
		
		return Response.ok().entity(apartments).build();
	}
	
	@GET
	@Path("/hostAll/{hostUsername}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllHostApartments(@Context HttpServletRequest request, 
			@PathParam("hostUsername") String hostUsername) {
		UserDAO dao = (UserDAO)this.ctx.getAttribute("userDAO");
//		Host host = (Host)dao.searchUsers(hostUsername);
//		
//		if(host == null) {
//			return Response.status(400).entity("Pogresan korisnika").build();
//		}
		
		ApartmentDAO apDAO = (ApartmentDAO)this.ctx.getAttribute("apartmentDAO");
		
		Collection<Apartment> apartments = apDAO.getApartments().values();
		
		return Response.status(200).entity(apartments).build(); 
		
	}
	
	
	@GET
	@Path("/hostAllActive/{hostUsername}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllActiveHostApartments(@Context HttpServletRequest request, 
			@PathParam("hostUsername") String hostUsername) {
		UserDAO dao = (UserDAO)this.ctx.getAttribute("userDAO");
//		Host host = (Host)dao.searchUsers(hostUsername);
//		
//		if(host == null) {
//			return Response.status(400).entity("Pogresan korisnika").build();
//		}
		
		ApartmentDAO apDAO = (ApartmentDAO)this.ctx.getAttribute("apartmentDAO");
		
		Collection<Apartment> apartments = apDAO.getApartments().values();
		Collection<Apartment> activeApartments = new ArrayList<Apartment>();
		for(Apartment apartment : apartments) {
			if(apartment.isAppStatus()) {
				activeApartments.add(apartment);
			}
		}
		
		return Response.status(200).entity(activeApartments).build(); 
		
	}
	
	@GET
	@Path("/hostAllNotActive/{hostUsername}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllNotActiveHostApartments(@Context HttpServletRequest request, 
			@PathParam("hostUsername") String hostUsername) {
		UserDAO dao = (UserDAO)this.ctx.getAttribute("userDAO");
//		Host host = (Host)dao.searchUsers(hostUsername);
//		
//		if(host == null) {
//			return Response.status(400).entity("Pogresan korisnika").build();
//		}
		
		ApartmentDAO apDAO = (ApartmentDAO)this.ctx.getAttribute("apartmentDAO");
		
		Collection<Apartment> apartments = apDAO.getApartments().values();
		Collection<Apartment> inactiveApartments = new ArrayList<Apartment>();
		for(Apartment apartment : apartments) {
			if(!apartment.isAppStatus()) {
				inactiveApartments.add(apartment);
			}
		}
		
		return Response.status(200).entity(inactiveApartments).build(); 
		
	}
	
//	@GET
//	@Path("/allActive")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Apartment> getAllActive(@Context HttpServletRequest request){
//		
//		ApartmentDAO apDAO = (ApartmentDAO) this.ctx.getAttribute("apartmentDAO");
//		return (ArrayList<Apartment>) apDAO.getAllActive();
//		
//	}
	
	
//	@POST
//	@Path("/new")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response createNewApartment(Apartment apartment, @Context HttpServletRequest request) {
//		ApartmentDAO apDAO = (ApartmentDAO)this.ctx.getAttribute("apartmentDAO");
//		String contextPath = ctx.getRealPath("");
//		List<AvailableDate> listaDatuma = new ArrayList<>();
//		List<ApartmentComment> listaKomentara = new ArrayList<>();
//		List<Reservation> listaRezervacija = new ArrayList<>();
//		apartment.setAppartmentDates(listaDatuma);
//		apartment.setComments(listaKomentara);
//		apartment.setReservations(listaRezervacija);
//		apDAO.dodaj(apartment, contextPath);
////		try {
////			apDAO.saveApartments();
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////			return Response.status(500).entity("Greska pri cuvanja apartmana").build();
////		}
//		
//		return Response.status(200).build();
//			
//	}
	
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewApartment(Apartment apartment, @Context HttpServletRequest request) {
		ApartmentDAO apDAO = (ApartmentDAO)this.ctx.getAttribute("apartmentDAO");
//		Apartment apartment = new Apartment();
		String contextPath = ctx.getRealPath("");
		List<AvailableDate> listaDatuma = new ArrayList<>();
		List<ApartmentComment> listaKomentara = new ArrayList<>();
		List<Reservation> listaRezervacija = new ArrayList<>();
		apartment.setAppartmentDates(listaDatuma);
		apartment.setComments(listaKomentara);
		apartment.setReservations(listaRezervacija);
//		apDAO.dodaj(apartment, contextPath);
//		try {
//			apDAO.saveApartments();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return Response.status(500).entity("Greska pri cuvanja apartmana").build();
//		}
		Collection<Apartment> apartments = apDAO.getApartments().values();
//		HashMap<String,Apartment> apartmentMap = new HashMap<String,Apartment>();
//		String str = String.valueOf(apartment.getId());
//		apartmentMap.put(str,apartment);
//		apDAO.dodajuFile(apartmentMap, contextPath);
		apDAO.dodaj(apartment, contextPath);
		return Response.status(200).entity(apartments).build();
			
	}
	
//	@PUT
//	@Path("/modify/{id}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response modifyApartment(Apartment modifiedApartment ,@PathParam("id") Long id, 
//			@Context HttpServletRequest request) {
//		ApartmentDAO apDAO = (ApartmentDAO)this.ctx.getAttribute("apartmentDAO");
//		apDAO.modifyApartment(modifiedApartment,id);
//		try {
//			apDAO.saveApartments();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return Response.status(500).entity("Greska pri cuvanju modifikovanog apartmana").build();
//		}
//		
//		return Response.status(200).build();
//	}
//	@DELETE
//	@Path("/delete/{id}")
//	public Response deleteApartment(@PathParam("id") Long id, @Context HttpServletRequest request) {
//		ApartmentDAO apDAO = (ApartmentDAO) this.ctx.getAttribute("apartmentDAO");
//		apDAO.deleteApartment(id);
//		try {
//			apDAO.saveApartments();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return Response.status(500).entity("Greska pri cuvanju obrisanog apartmana").build();
//		}
//		
//		return Response.status(200).build();
//		
//	}
//	@PUT
//	@Path("addComment/{guest}/{apartment}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addCommentToApartment(ApartmentComment comment, @Context HttpServletRequest request,
//			@PathParam("guest") String username, @PathParam("apartment") Long id) {
//		UserDAO userDAO = (UserDAO) this.ctx.getAttribute("userDAO");
//		Guest guest = (Guest) userDAO.searchUsers(username);
//		
//		ApartmentDAO apDAO = (ApartmentDAO) this.ctx.getAttribute("apartmentDAO");
//		Apartment apartment = apDAO.find(id);
//		
//		comment.setGuest(guest);
//		comment.setApartment(apartment);
//		apartment.getComments().add(comment);
//		
//		apDAO.putApartment(apartment);
//		
//		try {
//			apDAO.saveApartments();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return Response.status(500).build();
//		}
//		
//		return Response.status(200).build();	
//	}

}
