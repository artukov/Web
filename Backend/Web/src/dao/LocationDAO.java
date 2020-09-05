package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Address;
import beans.Amenities;
import beans.Location;
import beans.ApartmentType;
import beans.Host;

public class LocationDAO {

	private Map<Long , Location> locations = new HashMap<Long, Location>();
	private String contextPath;
	
	public LocationDAO() {
		
	}
	public LocationDAO(String contextPath) {
		this.contextPath = contextPath;
		try {
			loadLocations();
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
	
	private void loadLocations() throws JsonParseException, JsonMappingException ,IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		BufferedReader br = null;
		File locationFile = new File(this.contextPath + "data/location.json");
		StringBuilder json = new StringBuilder();
		String line;
		try {
			 br  = new BufferedReader(
					new FileReader(locationFile) );
			while((line = br.readLine()) != null) {
				json.append(line);
			}
					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			br.close();
		}
		
		List<Location> locationList = mapper.readValue(json.toString(),
				new TypeReference<ArrayList<Location>>() {
				});
		this.locations.clear();
		for(Location iter : locationList) {
			this.locations.put(iter.getId(), iter);
		}
		
		
	}
	public void saveLocations() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		File locationFile = new File(this.contextPath + "data/location.json");
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(locationFile,
					this.locations.values());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Greska pri upisivanju apartmana u fajlove");
		}
	}
	public Map<Long, Location> getLocations() {
		return locations;
	}
	public void setLocations(Map<Long, Location> locations) {
		this.locations = locations;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	public Location find(Long Id) {
		Location location = this.locations.get(Id);
		if(location == null) {
			return null;
		}
		return location;
	}
public void putLocation(Location location) {
		
		List<Location> allLocations = (List<Location>) this.locations.values();
		
		/**
		 * Java Lambda funckija za sortiranje liste apartmana po id-u 
		 * u opadajucem redosljedu
		 * */
		allLocations.sort((a,b) -> Long.compare(b.getId(), a.getId()));
		
		Long maxId = allLocations.get(0).getId(); //id prvog u sortiranoj listi je najveci
		location.setId(++maxId); //uvecavanjem za jedan dobijamo da se vrijednost kljuca nece ponoviti
		
//		location.setAppStatus(false);
		
		this.locations.put(location.getId(), location);
	}
	/**
	 * Metoda za dobavljanje svih ucitanih apartmana mape
	 * */
	public Collection<Location> findAll(){
		return this.locations.values();
	}
	
	public Collection<Location> findAllAdressLocations(Address address){
		ArrayList<Location> retLocations = new ArrayList<Location>();
		for(Location l : this.locations.values()) {
			if(l.getAddress().equals(address)) {
				retLocations.add(l);
			}
		}
		
		return retLocations;
		
	}
	/**
	 * Metoda za pronalazenje svih apartmana odredjenog tipa
	 * @param beans.ApartmentType type
	 * */
//	public Collection<Location> findAllTypeLocations(LocationType type){
//		ArrayList<Location> retLocations = new ArrayList<Location>();
//		for(Location a : this.locations.values()) {
//			if(a.getLocationType() == type) {
//				retLocations.add(a);
//			}
//		}
//		
//		return retLocations;
//	}
//	
	
	/**
	 * Metoda za pronalazenje svih aktivnih apartmana
	 * */
//	public Collection<Location> getAllActive(){
//		ArrayList<Location> retLocations = new ArrayList<Location>();
//		for(Location iter : this.locations.values()) {
//			if(iter.isAppStatus()) {
//				retLocations.add(iter);
//			}
//		}
//		
//		return retLocations;
//	}
	
	public void modifyLocation(Location modifiedLocation, Long id) {
		// TODO Auto-generated method stub
		Location location = this.find(id);
		location = modifiedLocation;
		this.locations.put(id, location);
		
		
	}

	public void deleteLocation(Long id) {
		// TODO Auto-generated method stub
		//this.locations.get(id).setApartmentStatus(false);//postavljanje statusa apartmana na neaktivan
		
		this.locations.remove(id);
		
	}
	
//	public void modifyLocationsWithAmenity(Amenities amenities) {
//		// TODO Auto-generated method stub
//		ArrayList<Location> list = (ArrayList<Location>) this.locations.values();
//		//lista svih apartmana
//		for(Location a : list) {
//			if(a.getAmenities() != null) {
//				//lista svih sadrzaja apartmana za odredjeni apartman
//				ArrayList<Amenities> iterAmenities = (ArrayList<Amenities>) a.getAmenities();
//				for(Amenities amenIter : iterAmenities) {
//					if(amenIter.getId() == amenities.getId()) {
//						//ako se poklapaju samo modifikovati vrijednosti i prekinuti petlju,
//						//jer znamo da postoji samo jedan sa tim id
//						amenIter = amenities;
//						this.locations.put(a.getId(), a);
//						break;
//					}
//				}
//			}
//		}
//		
//		
//	}
//	
//	public void deleteAmenityFromLocation(Amenities amenity) {
//		// TODO Auto-generated method stub
//		ArrayList<Location> list = (ArrayList<Location>) this.locations.values();
//		//lista svih apartmana
//		for(Location a : list) {
//			if(a.getAmenities() != null) {
//				//lista svih sadrzaja apartmana za odredjeni apartman
//				ArrayList<Amenities> iterAmenities = (ArrayList<Amenities>) a.getAmenities();
//				for(Amenities amenIter : iterAmenities) {
//					if(amenIter.getId() == amenity.getId()) {
//						//ako se poklapaju kljucevi treba ga obrisati
//						//jer znamo da postoji samo jedan sa tim id
//						iterAmenities.remove(amenIter);
//						this.locations.put(a.getId(), a);
//						break;
//					}
//				}
//			}
//		}
//	}
}
