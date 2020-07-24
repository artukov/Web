package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Amenities;

public class AmenitiesDAO {

	private Map<Long,Amenities> amenities= new HashMap<>(); 
	private String contextPath;
	
	public AmenitiesDAO() {
		
	}
	
	public AmenitiesDAO(String contextPath) {
		this.contextPath = contextPath;
		
		try {
			loadAmenities();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadAmenities() throws IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		BufferedReader br = null;
		File amenitiesFile = new File(this.contextPath + "data/amenities.json");
		StringBuilder json = new StringBuilder();
		String line;
		
		try {
			br = new BufferedReader(new FileReader(amenitiesFile));
			while ((line = br.readLine()) != null) {
				json.append(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			br.close();
		}
		
		List<Amenities> amenitiesList = mapper.readValue(json.toString(),
				new TypeReference<ArrayList<Amenities>>() {
				});
		this.amenities.clear();
		for(Amenities iter : amenitiesList) {
			this.amenities.put(iter.getId(), iter);
		}
		
	}
	
	public  void saveAmenities() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		File amenitiesFile = new File(this.contextPath + "data/amenities.json");
		
		mapper.writerWithDefaultPrettyPrinter().writeValue(amenitiesFile, 
				this.amenities.values());
	}
	
	public Amenities findById(Long id) {
		Amenities amenity = this.amenities.get(id);
		if(amenity == null) {
			return null;
		}
		
		return amenity;
	}
	
	public Collection<Amenities> findAll(){
		return this.amenities.values();
	}

	public Map<Long, Amenities> getAmenities() {
		return amenities;
	}

	public void setAmenities(Map<Long, Amenities> amenities) {
		this.amenities = amenities;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	public void putAmenities(Amenities amenities) {
		// TODO Auto-generated method stub
		List<Amenities> list = (List<Amenities>) this.amenities.values();
		
		list.sort((a,b) -> Long.compare(b.getId(), a.getId()));
		
		Long maxId = list.get(0).getId();
		amenities.setId(++maxId);
		
		this.amenities.put(amenities.getId(), amenities);
		
	}
	public void modifyAmenity(Amenities amenities) {
		// TODO Auto-generated method stub
		this.amenities.put(amenities.getId(), amenities);
		
	}

	public void deleteAmenity(Long id) {
		// TODO Auto-generated method stub
		
		this.amenities.remove(id);
		
	}
	
	
}
