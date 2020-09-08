package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Amenities;

public class AmenitiesDAO {

	private HashMap<String, Amenities> amenitiess = new HashMap<>();
	
	private AmenitiesDAO() {
		
		
	}
	public AmenitiesDAO(String contextPath) {
		loadAmenitiess(contextPath);
	}
	
//	public Amenities find(String amenitiesname,String password) {
//		if (!amenitiess.containsKey(amenitiesname)) {
//			return null;
//		}
//		Amenities amenities = amenitiess.get(amenitiesname);
//		if (!amenities.getPassword().equals(password)) {
//			return null;
//		}
//		return amenities;
//	}
	public HashMap<String, Amenities> getAmenitiess() {
		return amenitiess;
	}

	public void setAmenitiess(HashMap<String, Amenities> amenitiess) {
		this.amenitiess = amenitiess;
	}
	
	public boolean find(String amenitiesname) {
		if (!amenitiess.containsKey(amenitiesname)) {
			return false;
		}
		
		return true;
	}
	
	public Amenities searchAmenitiess(String u) {
		if (!amenitiess.containsKey(u)) {
			return null;
		}
		Amenities amenities = amenitiess.get(u);
	
		return amenities;
	}
	
	public Collection<Amenities> findAll() {
		return amenitiess.values();
	}
	
	public void dodaj(Amenities u, String contextPath)
	{
				
		try
		{
			File file = new File(contextPath + "/amenitiess.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Amenities> proba=new ArrayList<>();
			//proba.add(new amenities("pera","pera","Petar","Petrovic",0,"06055555","fasf@casc.com", "08-09-1995"));
			//proba.add(new amenities("jov","jov","jovana","jov",0,"0605020313","asfa@gmail.com", "06-06-1985"));
			//objectMapper.writeValue(new File(contextPath + "/amenitiess.json"), proba);
			
			Amenities[] car = objectMapper.readValue(file, Amenities[].class);
			System.out.println("register Amenities: "+car);
			
			//objectMapper.writeValue(new File(contextPath + "/proba.json"), new amenities("asfas","joasfasv","jov","jov",0,"jov","jov", "jov"));
			
			for(Amenities g : car)
			{
				proba.add(g);
			}
			proba.sort((a,b) -> Long.compare(b.getId(), a.getId()));
			Long maxId = proba.get(0).getId();
			u.setId(++maxId);
			proba.add(u);  //sve koji su bili + novi, mapa i fajl moraju biti uskladjeni
			objectMapper.writeValue(new File(contextPath + "/amenitiess.json"), proba);
			String str = String.valueOf(u.getId());
			Amenities r=amenitiess.put(str, u);
			
			System.out.println(amenitiess);
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
		String str = String.valueOf(u.getId());
		this.amenitiess.put(str,u);
		
	}
	
	
	private void loadAmenitiess(String contextPath) {
		try
		{
			File file = new File(contextPath + "/amenitiess.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true); //nebitno, kopiramo
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			
			ArrayList<Amenities> proba=new ArrayList<>();
			
			//OVO JE ZA DEFAULT VREDNOSTI, napravi sve sama pred projekat i zakomentarisi ovo
			//proba.add(new amenities("pera","pera","Petar","Petrovic",0,"06055555","fasf@casc.com", "08-09-1995"));
			//proba.add(new amenities("jov","jov","jovana","jov",1,"0605020313","asfa@gmail.com", "06-06-1985"));
			//objectMapper.writeValue(new File(contextPath + "/amenitiess.json"), proba);
			
			
			Amenities[] car = objectMapper.readValue(file, Amenities[].class);
			System.out.println("load Amenities: "+car);
			
			//objectMapper.writeValue(new File(contextPath + "/proba.json"), new amenities("asfas","joasfasv","jov","jov",0,"jov","jov", "jov"));
			
			for(Amenities u : car)
			{
				String str = String.valueOf(u.getId());
				amenitiess.put(str,u);
			}
			
			System.out.println(amenitiess);
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
	}
	@Override
	public String toString() {
		return "AmenitiesDAO [amenitiess=" + amenitiess + "]";
	}
	
	public void stavi(Amenities restoran) {
		// TODO Auto-generated method stub
		String str = String.valueOf(restoran.getId());
		amenitiess.put(str, restoran);
		
	}
	
	public void dodajuFile(HashMap<String, Amenities> amenitiess, String contextPath)
	{
				
		try
		{
			File file = new File(contextPath + "/amenitiess.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

			ArrayList<Amenities> proba=new ArrayList<>();
			
					
			for(Amenities g : amenitiess.values())
			{
				proba.add(g);
			}
			objectMapper.writeValue(new File(contextPath + "/amenitiess.json"), proba);
			
			System.out.println(amenitiess +" u file");
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
	}
	
	
	
	
	
	public Amenities add(Amenities amenities) {
		
		if (!amenitiess.containsKey(amenities.getId())) {
			String str = String.valueOf(amenities.getId());
			amenitiess.put(str, amenities);
			return amenitiess.get(amenities.getId());
		}
		
		return null;
	}

	
	public void remove(Amenities amenities) {
		if (amenitiess.containsKey(amenities.getId())) {
			amenitiess.remove(amenities.getId(), amenities);
		}
	}
	
	public void change(Amenities amenities) {
		if(amenitiess.containsKey(amenities.getId())) {
			String str = String.valueOf(amenities.getId());
			amenitiess.put(str,amenities);
		}
	}

//	public void save() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			objectMapper.writeValue(new File(this.path + "/amenitiess.json"), this.amenitiess);
//		} catch (IOException e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//}

}



