package beans;

import java.util.UUID;

public class Amenities {

//	private UUID id;
	private String name;
	public Amenities() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Amenities(Long id, String name) {
		super();
//		this.id = id;
		this.name = name;
	}
	
	public Amenities(String name) {
//		this.id = UUID.randomUUID();
		this.name = name;
	}
	
//	public Amenities(String name) {
//		super();
//		this.name = name;
//	}
//	public Long getId() {
//		return id;
//	}
	
//	public UUID getId() {
//	return id;
//	}
	
//	public void setId(UUID id) {
//	this.id = id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
