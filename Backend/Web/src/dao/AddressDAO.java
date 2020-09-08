package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Address;

public class AddressDAO {

	private HashMap<String, Address> addresss = new HashMap<>();
	
	private AddressDAO() {
		
		
	}
	public AddressDAO(String contextPath) {
		loadAddresss(contextPath);
	}
	
//	public address find(String addressname,String password) {
//		if (!addresss.containsKey(addressname)) {
//			return null;
//		}
//		address address = addresss.get(addressname);
//		if (!address.getPassword().equals(password)) {
//			return null;
//		}
//		return address;
//	}
	public HashMap<String, Address> getAddresss() {
		return addresss;
	}

	public void setAddresss(HashMap<String, Address> addresss) {
		this.addresss = addresss;
	}
	
	public boolean find(String addressname) {
		if (!addresss.containsKey(addressname)) {
			return false;
		}
		
		return true;
	}
	
	public Address searchAddresss(String u) {
		if (!addresss.containsKey(u)) {
			return null;
		}
		Address address = addresss.get(u);
	
		return address;
	}
	
	public Collection<Address> findAll() {
		return addresss.values();
	}
	
	public void dodaj(Address u, String contextPath)
	{
				
		try
		{
			File file = new File(contextPath + "/addresss.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Address> proba=new ArrayList<>();
			//proba.add(new address("pera","pera","Petar","Petrovic",0,"06055555","fasf@casc.com", "08-09-1995"));
			//proba.add(new address("jov","jov","jovana","jov",0,"0605020313","asfa@gmail.com", "06-06-1985"));
			//objectMapper.writeValue(new File(contextPath + "/addresss.json"), proba);
			
			Address[] car = objectMapper.readValue(file, Address[].class);
			System.out.println("register Address: "+car);
			
			//objectMapper.writeValue(new File(contextPath + "/proba.json"), new address("asfas","joasfasv","jov","jov",0,"jov","jov", "jov"));
			
			for(Address g : car)
			{
				proba.add(g);
			}
			proba.add(u);  //sve koji su bili + novi, mapa i fajl moraju biti uskladjeni
			objectMapper.writeValue(new File(contextPath + "/addresss.json"), proba);
			String str = String.valueOf(u.getNumber());
			Address r=addresss.put(str, u);
			
			System.out.println(addresss);
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
		String str = String.valueOf(u.getNumber());
		this.addresss.put(str,u);
		
	}
	
	
	private void loadAddresss(String contextPath) {
		try
		{
			File file = new File(contextPath + "/addresss.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true); //nebitno, kopiramo
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			
			ArrayList<Address> proba=new ArrayList<>();
			
			//OVO JE ZA DEFAULT VREDNOSTI, napravi sve sama pred projekat i zakomentarisi ovo
			//proba.add(new address("pera","pera","Petar","Petrovic",0,"06055555","fasf@casc.com", "08-09-1995"));
			//proba.add(new address("jov","jov","jovana","jov",1,"0605020313","asfa@gmail.com", "06-06-1985"));
			//objectMapper.writeValue(new File(contextPath + "/addresss.json"), proba);
			
			
			Address[] car = objectMapper.readValue(file, Address[].class);
			System.out.println("load Address: "+car);
			
			//objectMapper.writeValue(new File(contextPath + "/proba.json"), new address("asfas","joasfasv","jov","jov",0,"jov","jov", "jov"));
			String str = new String("");
			for(Address u : car)
			{
				str = String.valueOf(u.getNumber());
				addresss.put(str,u);
			}
			
			System.out.println(addresss);
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
	}
	@Override
	public String toString() {
		return "AddressDAO [addresss=" + addresss + "]";
	}
	
//	public void stavi(address restoran) {
//		// TODO Auto-generated method stub
//		
//		addresss.put(restoran.getaddressname(), restoran);
//		
//	}
	
	public void dodajuFile(HashMap<String, Address> addresss, String contextPath)
	{
				
		try
		{
			File file = new File(contextPath + "/addresss.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

			ArrayList<Address> proba=new ArrayList<>();
			
					
			for(Address g : addresss.values())
			{
				proba.add(g);
			}
			objectMapper.writeValue(new File(contextPath + "/addresss.json"), proba);
			
			System.out.println(addresss +" u file");
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
	}
	
	
	
	
	
//	public Address add(Address address) {
//		
//		if (!addresss.containsKey(address.getaddressname())) {
//			addresss.put(address.getaddressname(), address);
//			return addresss.get(address.getaddressname());
//		}
//		
//		return null;
//	}

	
//	public void remove(address address) {
//		if (addresss.containsKey(address.getaddressname())) {
//			addresss.remove(address.getaddressname(), address);
//		}
//	}
//	
//	public void change(address address) {
//		if(addresss.containsKey(address.getaddressname())) {
//			addresss.put(address.getaddressname(),address);
//		}
//	}

//	public void save() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			objectMapper.writeValue(new File(this.path + "/addresss.json"), this.addresss);
//		} catch (IOException e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//}

}