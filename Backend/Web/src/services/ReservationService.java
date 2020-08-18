package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import dao.ReservationDAO;

@Path("/reservations")
public class ReservationService {

	@Context
	public ServletContext ctx;
	public ReservationService() {}
	
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("reservationDAO") == null ) {
			this.ctx.setAttribute("reservationDAO", new ReservationDAO(this.ctx.getRealPath("/")));
			
		}
	}
	
	@GET
	@Path("/getAll")
	public Response getAll(@Context HttpServletRequest request) {
		ReservationDAO dao = (ReservationDAO) this.ctx.getAttribute("reservationDAO");
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(dao.findAll()).build();
	}
	
}
