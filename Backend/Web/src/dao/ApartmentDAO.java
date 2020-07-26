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

import beans.Amenities;
import beans.Apartment;
import beans.ApartmentType;
import beans.Host;

public class ApartmentDAO {

	private Map<Long , Apartment> apartments = new HashMap<Long, Apartment>();
	private String contextPath;
	
	public ApartmentDAO() {
		
	}
	public ApartmentDAO(String contextPath) {
		this.contextPath = contextPath;
		try {
			loadApartments();
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
	
	private void loadApartments() throws JsonParseException, JsonMappingException ,IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		BufferedReader br = null;
		File apartmentFile = new File(this.contextPath + "data/apartment.json");
		StringBuilder json = new StringBuilder();
		String line;
		try {
			 br  = new BufferedReader(
					new FileReader(apartmentFile) );
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
		
		List<Apartment> apartmentList = mapper.readValue(json.toString(),
				new TypeReference<ArrayList<Apartment>>() {
				});
		this.apartments.clear();
		for(Apartment iter : apartmentList) {
			this.apartments.put(iter.getId(), iter);
		}
		
		
	}
	public void saveApartments() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		File apartmentFile = new File(this.contextPath + "data/apartment.json");
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(apartmentFile,
					this.apartments.values());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Greska pri upisivanju apartmana u fajlove");
		}
	}
	public Map<Long, Apartment> getApartments() {
		return apartments;
	}
	public void setApartments(Map<Long, Apartment> apartments) {
		this.apartments = apartments;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	public Apartment find(Long Id) {
		Apartment apartment = this.apartments.get(Id);
		if(apartment == null) {
			return null;
		}
		return apartment;
	}
public void putApartment(Apartment apartment) {
		
		List<Apartment> allApartments = (List<Apartment>) this.apartments.values();
		
		/**
		 * Java Lambda funckija za sortiranje liste apartmana po id-u 
		 * u opadajucem redosljedu
		 * */
		allApartments.sort((a,b) -> Long.compare(b.getId(), a.getId()));
		
		Long maxId = allApartments.get(0).getId(); //id prvog u sortiranoj listi je najveci
		apartment.setId(++maxId); //uvecavanjem za jedan dobijamo da se vrijednost kljuca nece ponoviti
		
		apartment.setAppStatus(false);
		
		this.apartments.put(apartment.getId(), apartment);
	}
	/**
	 * Metoda za dobavljanje svih ucitanih apartmana mape
	 * */
	public Collection<Apartment> findAll(){
		return this.apartments.values();
	}
	
	public Collection<Apartment> findAllHostApartments(Host host){
		ArrayList<Apartment> retApartments = new ArrayList<Apartment>();
		for(Apartment a : this.apartments.values()) {
			if(a.getHost().getUsername() == host.getUsername()) {
				retApartments.add(a);
			}
		}
		
		return retApartments;
		
	}
	/**
	 * Metoda za pronalazenje svih apartmana odredjenog tipa
	 * @param beans.ApartmentType type
	 * */
	public Collection<Apartment> findAllTypeApartments(ApartmentType type){
		ArrayList<Apartment> retApartments = new ArrayList<Apartment>();
		for(Apartment a : this.apartments.values()) {
			if(a.getApartmentType() == type) {
				retApartments.add(a);
			}
		}
		
		return retApartments;
	}
	
	
	/**
	 * Metoda za pronalazenje svih aktivnih apartmana
	 * */
	public Collection<Apartment> getAllActive(){
		ArrayList<Apartment> retApartments = new ArrayList<Apartment>();
		for(Apartment iter : this.apartments.values()) {
			if(iter.isAppStatus()) {
				retApartments.add(iter);
			}
		}
		
		return retApartments;
	}
	
	public void modifyApartment(Apartment modifiedApartment, Long id) {
		// TODO Auto-generated method stub
		Apartment apartment = this.find(id);
		apartment = modifiedApartment;
		this.apartments.put(id, apartment);
		
		
	}

	public void deleteApartment(Long id) {
		// TODO Auto-generated method stub
		//this.apartments.get(id).setApartmentStatus(false);//postavljanje statusa apartmana na neaktivan
		
		this.apartments.remove(id);
		
	}
	
	public void modifyApartmentsWithAmenity(Amenities amenities) {
		// TODO Auto-generated method stub
		ArrayList<Apartment> list = (ArrayList<Apartment>) this.apartments.values();
		//lista svih apartmana
		for(Apartment a : list) {
			if(a.getAmenities() != null) {
				//lista svih sadrzaja apartmana za odredjeni apartman
				ArrayList<Amenities> iterAmenities = (ArrayList<Amenities>) a.getAmenities();
				for(Amenities amenIter : iterAmenities) {
					if(amenIter.getId() == amenities.getId()) {
						//ako se poklapaju samo modifikovati vrijednosti i prekinuti petlju,
						//jer znamo da postoji samo jedan sa tim id
						amenIter = amenities;
						this.apartments.put(a.getId(), a);
						break;
					}
				}
			}
		}
		
		
	}
	
	public void deleteAmenityFromApartment(Amenities amenity) {
		// TODO Auto-generated method stub
		ArrayList<Apartment> list = (ArrayList<Apartment>) this.apartments.values();
		//lista svih apartmana
		for(Apartment a : list) {
			if(a.getAmenities() != null) {
				//lista svih sadrzaja apartmana za odredjeni apartman
				ArrayList<Amenities> iterAmenities = (ArrayList<Amenities>) a.getAmenities();
				for(Amenities amenIter : iterAmenities) {
					if(amenIter.getId() == amenity.getId()) {
						//ako se poklapaju kljucevi treba ga obrisati
						//jer znamo da postoji samo jedan sa tim id
						iterAmenities.remove(amenIter);
						this.apartments.put(a.getId(), a);
						break;
					}
				}
			}
		}
	}
}
