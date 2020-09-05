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
import beans.Apartment;
import beans.ApartmentType;
import beans.Host;

public class AddressDAO {

	private Map<Long , Address> addresss = new HashMap<Long, Address>();
	private String contextPath;
	
	public AddressDAO() {
		
	}
	public AddressDAO(String contextPath) {
		this.contextPath = contextPath;
		try {
			loadAddresss();
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
	
	private void loadAddresss() throws JsonParseException, JsonMappingException ,IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		BufferedReader br = null;
		File addressFile = new File(this.contextPath + "data/address.json");
		StringBuilder json = new StringBuilder();
		String line;
		try {
			 br  = new BufferedReader(
					new FileReader(addressFile) );
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
		
		List<Address> addressList = mapper.readValue(json.toString(),
				new TypeReference<ArrayList<Address>>() {
				});
		this.addresss.clear();
		for(Address iter : addressList) {
			this.addresss.put(iter.getId(), iter);
		}
		
		
	}
	public void saveAddresss() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		File addressFile = new File(this.contextPath + "data/address.json");
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(addressFile,
					this.addresss.values());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Greska pri upisivanju apartmana u fajlove");
		}
	}
	public Map<Long, Address> getAddresss() {
		return addresss;
	}
	public void setAddresss(Map<Long, Address> addresss) {
		this.addresss = addresss;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	public Address find(Long Id) {
		Address address = this.addresss.get(Id);
		if(address == null) {
			return null;
		}
		return address;
	}
//public void putAddress(Address address) {
//		
//		List<Address> allAddresss = (List<Address>) this.addresss.values();
//		
//		/**
//		 * Java Lambda funckija za sortiranje liste apartmana po id-u 
//		 * u opadajucem redosljedu
//		 * */
//		allAddresss.sort((a,b) -> Long.compare(b.getId(), a.getId()));
//		
//		Long maxId = allAddresss.get(0).getId(); //id prvog u sortiranoj listi je najveci
//		address.setId(++maxId); //uvecavanjem za jedan dobijamo da se vrijednost kljuca nece ponoviti
//		
//		address.setAppStatus(false);
//		
//		this.addresss.put(address.getId(), address);
//	}
	/**
	 * Metoda za dobavljanje svih ucitanih apartmana mape
	 * */
	public Collection<Address> findAll(){
		return this.addresss.values();
	}
	
//	public Collection<Address> findAllHostAddresss(Host host){
//		ArrayList<Address> retAddresss = new ArrayList<Address>();
//		for(Address a : this.addresss.values()) {
//			if(a.getHost().getUsername() == host.getUsername()) {
//				retAddresss.add(a);
//			}
//		}
//		
//		return retAddresss;
//		
//	}
	/**
	 * Metoda za pronalazenje svih apartmana odredjenog tipa
	 * @param beans.AddressType type
	 * */
//	public Collection<Address> findAllTypeAddresss(AddressType type){
//		ArrayList<Address> retAddresss = new ArrayList<Address>();
//		for(Address a : this.addresss.values()) {
//			if(a.getAddressType() == type) {
//				retAddresss.add(a);
//			}
//		}
//		
//		return retAddresss;
//	}
	
	
	/**
	 * Metoda za pronalazenje svih aktivnih apartmana
	 * */
//	public Collection<Address> getAllActive(){
//		ArrayList<Address> retAddresss = new ArrayList<Address>();
//		for(Address iter : this.addresss.values()) {
//			if(iter.isAppStatus()) {
//				retAddresss.add(iter);
//			}
//		}
//		
//		return retAddresss;
//	}
	
	public void modifyAddress(Address modifiedAddress, Long id) {
		// TODO Auto-generated method stub
		Address address = this.find(id);
		address = modifiedAddress;
		this.addresss.put(id, address);
		
		
	}

	public void deleteAddress(Long id) {
		// TODO Auto-generated method stub
		//this.addresss.get(id).setaddressStatus(false);//postavljanje statusa apartmana na neaktivan
		
		this.addresss.remove(id);
		
	}
	
//	public void modifyAddresssWithAmenity(Amenities amenities) {
//		// TODO Auto-generated method stub
//		ArrayList<Address> list = (ArrayList<Address>) this.addresss.values();
//		//lista svih apartmana
//		for(Address a : list) {
//			if(a.getAmenities() != null) {
//				//lista svih sadrzaja apartmana za odredjeni apartman
//				ArrayList<Amenities> iterAmenities = (ArrayList<Amenities>) a.getAmenities();
//				for(Amenities amenIter : iterAmenities) {
//					if(amenIter.getId() == amenities.getId()) {
//						//ako se poklapaju samo modifikovati vrijednosti i prekinuti petlju,
//						//jer znamo da postoji samo jedan sa tim id
//						amenIter = amenities;
//						this.addresss.put(a.getId(), a);
//						break;
//					}
//				}
//			}
//		}
//		
//		
//	}
	
//	public void deleteAmenityFromAddress(Amenities amenity) {
//		// TODO Auto-generated method stub
//		ArrayList<Address> list = (ArrayList<Address>) this.addresss.values();
//		//lista svih apartmana
//		for(Address a : list) {
//			if(a.getAmenities() != null) {
//				//lista svih sadrzaja apartmana za odredjeni apartman
//				ArrayList<Amenities> iterAmenities = (ArrayList<Amenities>) a.getAmenities();
//				for(Amenities amenIter : iterAmenities) {
//					if(amenIter.getId() == amenity.getId()) {
//						//ako se poklapaju kljucevi treba ga obrisati
//						//jer znamo da postoji samo jedan sa tim id
//						iterAmenities.remove(amenIter);
//						this.addresss.put(a.getId(), a);
//						break;
//					}
//				}
//			}
//		}
//	}
}
