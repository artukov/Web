package services;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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

import beans.Amenities;
import beans.User;
import beans.UserRole;
import dao.AmenitiesDAO;
import dao.ApartmentDAO;

@Path("/amenities")
public class AmenitiesService {

	@Context
	ServletContext ctx;
	
	public AmenitiesService() {
		
	}
	
	@PostConstruct
	public void init() {
		if(this.ctx.getAttribute("amenitiesDAO") == null) {
			this.ctx.setAttribute("amenitiesDAO", new AmenitiesDAO(this.ctx.getRealPath("/")));
		}
//		if(this.ctx.getAttribute("apartmentDAO") == null) {
//			this.ctx.setAttribute("apartmentDAO", new ApartmentDAO(this.ctx.getRealPath("/")));
//		}
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAmenities( @Context HttpServletRequest request) {
		User admin = (User) request.getSession().getAttribute("user");
//		if(admin.getRole() != UserRole.ADMIN) {
//			return Response.status(403).entity("Sami admin ima pristup").build();
//		}
		AmenitiesDAO amenitiesDao = (AmenitiesDAO) this.ctx.getAttribute("amenitiesDAO");
		Amenities amenity = new Amenities("Pansion");
		Amenities amenity2 = new Amenities("Polu");
		String contextPath = ctx.getRealPath("");
		amenitiesDao.dodaj(amenity, contextPath);
		amenitiesDao.dodaj(amenity2, contextPath);
		
		Collection<Amenities> amenitiess = amenitiesDao.getAmenitiess().values();
		return Response.status(200).entity(amenitiess).build();
		
	}
	
//	@POST
//	@Path("/new")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response createNewAmenity(Amenities amenities, @Context HttpServletRequest request) {
//		User admin = (User) request.getSession().getAttribute("user");
//		if(admin.getRole() != UserRole.ADMIN) {
//			return Response.status(403).entity("Samo admin ima dozvolu").build();
//		}
//		
//		AmenitiesDAO dao = (AmenitiesDAO) this.ctx.getAttribute("amenitiesDAO");
//		dao.putAmenities(amenities);
//		
//		try {
//			dao.saveAmenities();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return Response.status(500).entity("nije sacuvano,GRESKA").build();
//		}
//		
//		return Response.status(200).build();
//		
//	}
	
	
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewAmenity(Amenities amenities, @Context HttpServletRequest request) {
		User admin = (User) request.getSession().getAttribute("user");
		if(admin.getRole() != UserRole.ADMIN) {
			return Response.status(403).entity("Samo admin ima dozvolu").build();
		}
		
		AmenitiesDAO amenitiesDAO = (AmenitiesDAO) this.ctx.getAttribute("amenitiesDAO");
		String contextPath = ctx.getRealPath("");
		amenitiesDAO.dodaj(amenities,contextPath);
		
//		try {
//			amenitiesDAO.saveAmenities();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return Response.status(500).entity("nije sacuvano,GRESKA").build();
//		}
		
		return Response.status(200).entity(amenities).build();
		
	}
	
//	@PUT
//	@Path("/modify")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response modifyAmenity(Amenities amenities, @Context HttpServletRequest request) {
//		User admin = (User) request.getSession().getAttribute("user");
//		if(admin.getRole() != UserRole.ADMIN) {
//			return Response.status(403).entity("Nije dozvoljeno za druge osim za admine").build();
//		}
//		
//		AmenitiesDAO dao = (AmenitiesDAO) this.ctx.getAttribute("amenitiesDAO");
//		
//		dao.modifyAmenity(amenities);
//		
//		
//		try {
//			dao.saveAmenities();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return Response.status(500).entity("Greska u cuvanju izmjenjenog sadrzaja apartmana").build();
//		}
//		
//		ApartmentDAO apDAO = (ApartmentDAO) this.ctx.getAttribute("apartmentDAO");
//		apDAO.modifyApartmentsWithAmenity(amenities);
//		
//		
//		return Response.status(200).build();
//		
//	}
//	
//	@DELETE
//	@Path("/delete/{id}")
//	public Response deleteAmenity(@PathParam("id") Long id, @Context HttpServletRequest request) {
//		
//		User admin = (User) request.getSession().getAttribute("user");
//		if(admin.getRole() != UserRole.ADMIN) {
//			return Response.status(403).entity("Nije dozvoljeno za druge osim za admine").build();
//		}
//		
//		AmenitiesDAO dao = (AmenitiesDAO) this.ctx.getAttribute("amenitiesDAO");
//		
//		dao.deleteAmenity(id);
//		
//		try {
//			dao.saveAmenities();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return Response.status(500).entity("Greska u cuvanju izmjenjenog sadrzaja apartmana").build();
//		}
//		
//		ApartmentDAO apDAO = (ApartmentDAO) this.ctx.getAttribute("apartmentDAO");
//		apDAO.deleteAmenityFromApartment(dao.findById(id));
//		
//		
//		return Response.status(200).build();
//	}
}
