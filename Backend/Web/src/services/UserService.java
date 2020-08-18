package services;

import java.io.IOException;

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

import beans.User;
import beans.UserRole;
import dao.UserDAO;

@Path("/users")
public class UserService {

	@Context 
	public ServletContext ctx;
	
	public UserService() {}
	
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("UserDAO")==null) {
			this.ctx.setAttribute("userDAO",new UserDAO(this.ctx.getRealPath("/")));
		}
	}
	@GET
	@Path("/currentUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User currentUser(@Context HttpServletRequest req) {
		
		return (User) req.getSession().getAttribute("user");
	}
	@GET
	@Path("/allUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(@Context HttpServletRequest request){
		User admin = (User) request.getSession().getAttribute("user");
		if(admin.getRole() != UserRole.ADMIN) {
			return Response.status(403).build();
		}
		
		
		
		UserDAO dao = (UserDAO) this.ctx.getAttribute("userDAO");
		
		
		return Response.status(200).entity(dao.findAll()).build();
		
		
	}
	@POST
	@Path("/modifyUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifyUSer(User newUSer, @Context HttpServletRequest req) {
		UserDAO dao = (UserDAO) this.ctx.getAttribute("UserDao");
		if(!dao.modifyUser(newUSer,newUSer.getUsername())) {
			return Response.status(400).entity("Username je menjan").build();
		}
		
		try {
			dao.saveUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).entity("Greska tokom cuvanjaa").build();
		}
		
		return Response.status(200).build();
	}
}
