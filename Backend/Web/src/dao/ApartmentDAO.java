//package dao;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import beans.Amenities;
//import beans.Apartment;
//import beans.ApartmentType;
//import beans.Host;
//
//public class ApartmentDAO {
//
//	private Map<Long , Apartment> apartments = new HashMap<Long, Apartment>();
//	private String contextPath;
//	
//	public ApartmentDAO() {
//		
//	}
//	public ApartmentDAO(String contextPath) {
//		this.contextPath = contextPath;
//		try {
//			loadApartments();
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	private void loadApartments() throws JsonParseException, JsonMappingException ,IOException {
//		// TODO Auto-generated method stub
//		ObjectMapper mapper = new ObjectMapper();
//		BufferedReader br = null;
//		File apartmentFile = new File(this.contextPath + "data/apartment.json");
//		StringBuilder json = new StringBuilder();
//		String line;
//		try {
//			 br  = new BufferedReader(
//					new FileReader(apartmentFile) );
//			while((line = br.readLine()) != null) {
//				json.append(line);
//			}
//					
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			br.close();
//		}
//		
//		List<Apartment> apartmentList = mapper.readValue(json.toString(),
//				new TypeReference<ArrayList<Apartment>>() {
//				});
//		this.apartments.clear();
//		for(Apartment iter : apartmentList) {
//			this.apartments.put(iter.getId(), iter);
//		}
//		
//		
//	}
//	public void saveApartments() throws Exception {
//		
//		ObjectMapper mapper = new ObjectMapper();
//		File apartmentFile = new File(this.contextPath + "data/apartment.json");
//		try {
//			mapper.writerWithDefaultPrettyPrinter().writeValue(apartmentFile,
//					this.apartments.values());
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			throw new Exception("Greska pri upisivanju apartmana u fajlove");
//		}
//	}
//	public Map<Long, Apartment> getApartments() {
//		return apartments;
//	}
//	public void setApartments(Map<Long, Apartment> apartments) {
//		this.apartments = apartments;
//	}
//	public String getContextPath() {
//		return contextPath;
//	}
//	public void setContextPath(String contextPath) {
//		this.contextPath = contextPath;
//	}
//	
//	public Apartment find(Long Id) {
//		Apartment apartment = this.apartments.get(Id);
//		if(apartment == null) {
//			return null;
//		}
//		return apartment;
//	}
//public void putApartment(Apartment apartment) {
//		
//		List<Apartment> allApartments = (List<Apartment>) this.apartments.values();
//		
//		/**
//		 * Java Lambda funckija za sortiranje liste apartmana po id-u 
//		 * u opadajucem redosljedu
//		 * */
//		allApartments.sort((a,b) -> Long.compare(b.getId(), a.getId()));
//		
//		Long maxId = allApartments.get(0).getId(); //id prvog u sortiranoj listi je najveci
//		apartment.setId(++maxId); //uvecavanjem za jedan dobijamo da se vrijednost kljuca nece ponoviti
//		
//		apartment.setAppStatus(false);
//		
//		this.apartments.put(apartment.getId(), apartment);
//	}
//	/**
//	 * Metoda za dobavljanje svih ucitanih apartmana mape
//	 * */
//	public Collection<Apartment> findAll(){
//		return this.apartments.values();
//	}
//	
//	public Collection<Apartment> findAllHostApartments(Host host){
//		ArrayList<Apartment> retApartments = new ArrayList<Apartment>();
//		for(Apartment a : this.apartments.values()) {
//			if(a.getHost().getapartmentname() == host.getapartmentname()) {
//				retApartments.add(a);
//			}
//		}
//		
//		return retApartments;
//		
//	}
//	/**
//	 * Metoda za pronalazenje svih apartmana odredjenog tipa
//	 * @param beans.ApartmentType type
//	 * */
//	public Collection<Apartment> findAllTypeApartments(ApartmentType type){
//		ArrayList<Apartment> retApartments = new ArrayList<Apartment>();
//		for(Apartment a : this.apartments.values()) {
//			if(a.getApartmentType() == type) {
//				retApartments.add(a);
//			}
//		}
//		
//		return retApartments;
//	}
//	
//	
//	/**
//	 * Metoda za pronalazenje svih aktivnih apartmana
//	 * */
//	public Collection<Apartment> getAllActive(){
//		ArrayList<Apartment> retApartments = new ArrayList<Apartment>();
//		for(Apartment iter : this.apartments.values()) {
//			if(iter.isAppStatus()) {
//				retApartments.add(iter);
//			}
//		}
//		
//		return retApartments;
//	}
//	
//	public void modifyApartment(Apartment modifiedApartment, Long id) {
//		// TODO Auto-generated method stub
//		Apartment apartment = this.find(id);
//		apartment = modifiedApartment;
//		this.apartments.put(id, apartment);
//		
//		
//	}
//
//	public void deleteApartment(Long id) {
//		// TODO Auto-generated method stub
//		//this.apartments.get(id).setApartmentStatus(false);//postavljanje statusa apartmana na neaktivan
//		
//		this.apartments.remove(id);
//		
//	}
//	
//	public void modifyApartmentsWithAmenity(Amenities amenities) {
//		// TODO Auto-generated method stub
//		ArrayList<Apartment> list = (ArrayList<Apartment>) this.apartments.values();
//		//lista svih apartmana
//		for(Apartment a : list) {
//			if(a.getAmenities() != null) {
//				//lista svih sadrzaja apartmana za odredjeni apartman
//				ArrayList<Amenities> iterAmenities = (ArrayList<Amenities>) a.getAmenities();
//				for(Amenities amenIter : iterAmenities) {
//					if(amenIter.getId() == amenities.getId()) {
//						//ako se poklapaju samo modifikovati vrijednosti i prekinuti petlju,
//						//jer znamo da postoji samo jedan sa tim id
//						amenIter = amenities;
//						this.apartments.put(a.getId(), a);
//						break;
//					}
//				}
//			}
//		}
//		
//		
//	}
//	
//	public void deleteAmenityFromApartment(Amenities amenity) {
//		// TODO Auto-generated method stub
//		ArrayList<Apartment> list = (ArrayList<Apartment>) this.apartments.values();
//		//lista svih apartmana
//		for(Apartment a : list) {
//			if(a.getAmenities() != null) {
//				//lista svih sadrzaja apartmana za odredjeni apartman
//				ArrayList<Amenities> iterAmenities = (ArrayList<Amenities>) a.getAmenities();
//				for(Amenities amenIter : iterAmenities) {
//					if(amenIter.getId() == amenity.getId()) {
//						//ako se poklapaju kljucevi treba ga obrisati
//						//jer znamo da postoji samo jedan sa tim id
//						iterAmenities.remove(amenIter);
//						this.apartments.put(a.getId(), a);
//						break;
//					}
//				}
//			}
//		}
//	}
//}



package dao;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Amenities;
import beans.Apartment;
import beans.AvailableDate;
import beans.AvailableEnum;
import beans.DayMonthYear;

public class ApartmentDAO {

	private HashMap<String, Apartment> apartments = new HashMap<>();
	
	private ApartmentDAO() {
		
		
	}
	public ApartmentDAO(String contextPath) {
		loadApartments(contextPath);
	}
	
//	public Apartment find(String apartmentname,String password) {
//		if (!apartments.containsKey(apartmentname)) {
//			return null;
//		}
//		Apartment apartment = apartments.get(apartmentname);
//		if (!apartment.getPassword().equals(password)) {
//			return null;
//		}
//		return apartment;
//	}
	public HashMap<String, Apartment> getApartments() {
		return apartments;
	}

	public void setApartments(HashMap<String, Apartment> apartments) {
		this.apartments = apartments;
	}
	
	public boolean find(String apartmentname) {
		if (!apartments.containsKey(apartmentname)) {
			return false;
		}
		
		return true;
	}
	
//	public Apartment searchApartments(String u) {
//		if (!apartments.containsKey(u)) {
//			return null;
//		}
//		Apartment apartment = apartments.get(u);
//	
//		return apartment;
//	}
	
	public Collection<Apartment> findAll() {
		return apartments.values();
	}
	
	public void dodaj(Apartment u, String contextPath)
	{
				
		try
		{
			File file = new File(contextPath + "/apartments.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Apartment> proba=new ArrayList<>();
			//proba.add(new apartment("pera","pera","Petar","Petrovic",0,"06055555","fasf@casc.com", "08-09-1995"));
			//proba.add(new apartment("jov","jov","jovana","jov",0,"0605020313","asfa@gmail.com", "06-06-1985"));
			//objectMapper.writeValue(new File(contextPath + "/apartments.json"), proba);
			
			Apartment[] car = objectMapper.readValue(file, Apartment[].class);
			System.out.println("register Apartment: "+car);
			
			//objectMapper.writeValue(new File(contextPath + "/proba.json"), new apartment("asfas","joasfasv","jov","jov",0,"jov","jov", "jov"));
			
			for(Apartment g : car)
			{
				proba.add(g);
			}
			proba.add(u);  //sve koji su bili + novi, mapa i fajl moraju biti uskladjeni
			objectMapper.writeValue(new File(contextPath + "/apartments.json"), proba);
			String str = String.valueOf(u.getId());
			Apartment r=apartments.put(str, u);
			
			System.out.println(apartments);
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
		String str = String.valueOf(u.getId());
		this.apartments.put(str,u);
		
	}
	
	
	private void loadApartments(String contextPath) {
		try
		{
			File file = new File(contextPath + "/apartments.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true); //nebitno, kopiramo
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			
			ArrayList<Apartment> proba=new ArrayList<>();
			
			//OVO JE ZA DEFAULT VREDNOSTI, napravi sve sama pred projekat i zakomentarisi ovo
			//proba.add(new apartment("pera","pera","Petar","Petrovic",0,"06055555","fasf@casc.com", "08-09-1995"));
			//proba.add(new apartment("jov","jov","jovana","jov",1,"0605020313","asfa@gmail.com", "06-06-1985"));
			//objectMapper.writeValue(new File(contextPath + "/apartments.json"), proba);
			
			
			Apartment[] car = objectMapper.readValue(file, Apartment[].class);
			System.out.println("load Apartment: "+car);
			
			//objectMapper.writeValue(new File(contextPath + "/proba.json"), new apartment("asfas","joasfasv","jov","jov",0,"jov","jov", "jov"));
			
			for(Apartment u : car)
			{
				String str = String.valueOf(u.getId());
				apartments.put(str,u);
			}
			
			System.out.println(apartments);
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
	}
	@Override
	public String toString() {
		return "ApartmentDAO [apartments=" + apartments + "]";
	}
	
	public void stavi(Apartment restoran) {
		// TODO Auto-generated method stub
		
		String str = String.valueOf(restoran.getId());
		apartments.put(str, restoran);
		
	}
	
	public void dodajuFile(HashMap<String, Apartment> apartments, String contextPath)
	{
				
		try
		{
			File file = new File(contextPath + "/apartments.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

			ArrayList<Apartment> proba=new ArrayList<>();
			
					
			for(Apartment g : apartments.values())
			{
				proba.add(g);
			}
			objectMapper.writeValue(new File(contextPath + "/apartments.json"), proba);
			
			System.out.println(apartments +" u file");
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
	}
	
	
	public Collection<Apartment> getAllActive(){
		ArrayList<Apartment> retApartments = new ArrayList<Apartment>();
		for(Apartment iter : this.apartments.values()) {
			if(iter.isAppStatus()) {
				retApartments.add(iter);
			}
		}
		
		return retApartments;
	}
	
	
	@SuppressWarnings("deprecation")
	public Collection<Apartment> searchApartments(Apartment apartment, String availableFrom, String availableTo) throws ParseException {
		Collection<Apartment> allApartments = getAllActive();
		Collection<Apartment> apartments = new ArrayList<Apartment>();
		for(Apartment iter : allApartments) {
			if(!(apartment.getNumberRooms() == null)) {
				if(!apartment.getNumberRooms().equals(iter.getNumberRooms())) {
					continue;
				}
			}
			if(!(apartment.getGuestNumber() == null)) {
				if(apartment.getGuestNumber() > iter.getGuestNumber()) {
					continue;
				}
			}
			if(!(apartment.getLocation() == null)) {
				if(!apartment.getLocation().equals(iter.getLocation())) {
					continue;
				}
			}
			
			if(!(availableFrom.equals("null") || availableTo.equals("null"))) {
//				if(apartment.getAppartmentDates()) {
					
//				}
//				List<List<AvailableDate>> availableDates;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				Date now = new Date();
				Date noww = new Date();
				noww.setDate(now.getDate()+1);
				Date availableFromDate = sdf.parse(availableFrom);
				Date availableToDate = sdf.parse(availableTo);
				HashMap<Date,AvailableEnum> availableDates;
				for(Apartment ap : allApartments) {
					availableDates = ap.getAppartmentDates();
					boolean before = true;
					Date afd;
					for(afd = availableFromDate; before; afd.setDate(afd.getDate()+1)) { //TODO mozes promeniti ove forove
						Date j = (Date) afd.clone();
						before = availableFromDate.before(availableToDate);
						for(Map.Entry<Date, AvailableEnum> date : availableDates.entrySet()) {
							Date datedate = date.getKey();
							if((datedate.getDay() == j.getDay()) && (datedate.getMonth() == j.getMonth()) && (datedate.getYear() == j.getYear())) {
								if(date.getValue().equals(AvailableEnum.TAKEN)) {
									continue;
								}
							}
							
						}
//						if(availableDates.get(j).equals(AvailableEnum.TAKEN)) {
//							continue;
//						}
					}
				}
			}
			
			if(!(apartment.getPriceNight() == null)) {
				if(apartment.getPriceNight() < iter.getPriceNight()) {
					continue;
				}
			}
//			if(!(apartment.getAmenities() == null)) {
//				if(!apartment.getAmenities().equals(iter.getAmenities())) {
//					continue;
//				}
//			}
			
			//TODO fali po datumima onim
			
			
			apartments.add(iter);
		}
		return apartments;
	}
	
	
	
//	public Apartment add(Apartment apartment) {
//		
//		if (!apartments.containsKey(apartment.getapartmentname())) {
//			apartments.put(apartment.getapartmentname(), apartment);
//			return apartments.get(apartment.getapartmentname());
//		}
//		
//		return null;
//	}

	
	public void remove(Apartment apartment) {
		if (apartments.containsKey(apartment.getId())) {
			apartments.remove(apartment.getId(), apartment);
		}
	}
	
	public void change(Apartment apartment) {
		if(apartments.containsKey(apartment.getId())) {
			String str = String.valueOf(apartment.getId());
			apartments.put(str,apartment);
		}
	}
	
	
	public void amenityApartments(String oldAmenity, String newAmenity) {
		ArrayList<Apartment> list = new ArrayList<Apartment>(this.findAll());
		for(Apartment iter : list) {
			if(iter.getAmenities() != null) {
				ArrayList<String> iterAmenities = (ArrayList<String>) iter.getAmenities();
				ArrayList<String> returnAmenities = (ArrayList<String>) iter.getAmenities();
				for(String amenIter : iterAmenities) {
					if(amenIter.equals(oldAmenity)) {
						returnAmenities.remove(oldAmenity);
						returnAmenities.add(newAmenity);
						iter.setAmenities(returnAmenities);
						String str = String.valueOf(iter.getId());
						this.apartments.put(str, iter);
						break;
					}
				}
			}
		}
		
		
	}

//	public void save() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			objectMapper.writeValue(new File(this.path + "/apartments.json"), this.apartments);
//		} catch (IOException e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//}

}




