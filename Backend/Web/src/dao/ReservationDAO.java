package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Reservation;
import beans.ReservationStatus;

public class ReservationDAO {

	private HashMap<String, Reservation> reservations = new HashMap<>();
	
	private ReservationDAO() {
		
		
	}
	public ReservationDAO(String contextPath) {
		loadReservations(contextPath);
	}
	
//	public Reservation find(String reservationname,String password) {
//		if (!reservations.containsKey(reservationname)) {
//			return null;
//		}
//		Reservation reservation = reservations.get(reservationname);
//		if (!reservation.getPassword().equals(password)) {
//			return null;
//		}
//		return reservation;
//	}
	public HashMap<String, Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(HashMap<String, Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public boolean find(String reservationname) {
		if (!reservations.containsKey(reservationname)) {
			return false;
		}
		
		return true;
	}
	
	public Reservation searchReservations(String u) {
		if (!reservations.containsKey(u)) {
			return null;
		}
		Reservation reservation = reservations.get(u);
	
		return reservation;
	}
	
	public Collection<Reservation> findAll() {
		return reservations.values();
	}
	
	public void dodaj(Reservation u, String contextPath)
	{
				
		try
		{
			File file = new File(contextPath + "/reservations.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			ArrayList<Reservation> proba=new ArrayList<>();
			//proba.add(new reservation("pera","pera","Petar","Petrovic",0,"06055555","fasf@casc.com", "08-09-1995"));
			//proba.add(new reservation("jov","jov","jovana","jov",0,"0605020313","asfa@gmail.com", "06-06-1985"));
			//objectMapper.writeValue(new File(contextPath + "/reservations.json"), proba);
			
			Reservation[] car = objectMapper.readValue(file, Reservation[].class);
			System.out.println("register Reservation: "+car);
			
			//objectMapper.writeValue(new File(contextPath + "/proba.json"), new reservation("asfas","joasfasv","jov","jov",0,"jov","jov", "jov"));
			
			for(Reservation g : car)
			{
				proba.add(g);
			}
			proba.add(u);  //sve koji su bili + novi, mapa i fajl moraju biti uskladjeni
			objectMapper.writeValue(new File(contextPath + "/reservations.json"), proba);
			String str = String.valueOf(u.getId());
			Reservation r=reservations.put(str, u);
			
			System.out.println(reservations);
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
		String str = String.valueOf(u.getId());
		this.reservations.put(str,u);
		
	}
	
	
	private void loadReservations(String contextPath) {
		try
		{
			File file = new File(contextPath + "/reservations.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true); //nebitno, kopiramo
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			
			
			ArrayList<Reservation> proba=new ArrayList<>();
			
			//OVO JE ZA DEFAULT VREDNOSTI, napravi sve sama pred projekat i zakomentarisi ovo
			//proba.add(new reservation("pera","pera","Petar","Petrovic",0,"06055555","fasf@casc.com", "08-09-1995"));
			//proba.add(new reservation("jov","jov","jovana","jov",1,"0605020313","asfa@gmail.com", "06-06-1985"));
			//objectMapper.writeValue(new File(contextPath + "/reservations.json"), proba);
			
			
			Reservation[] car = objectMapper.readValue(file, Reservation[].class);
			System.out.println("load Reservation: "+car);
			
			//objectMapper.writeValue(new File(contextPath + "/proba.json"), new reservation("asfas","joasfasv","jov","jov",0,"jov","jov", "jov"));
			
			for(Reservation u : car)
			{
				String str = String.valueOf(u.getId());
				reservations.put(str,u);
			}
			
			System.out.println(reservations);
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
	}
	@Override
	public String toString() {
		return "ReservationDAO [reservations=" + reservations + "]";
	}
	
	public void stavi(Reservation restoran) {
		// TODO Auto-generated method stub
		String str = String.valueOf(restoran.getId());
		reservations.put(str, restoran);
		
	}
	
	public void dodajuFile(HashMap<String, Reservation> reservations, String contextPath)
	{
				
		try
		{
			File file = new File(contextPath + "/reservations.json");
			System.out.println(contextPath);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

			ArrayList<Reservation> proba=new ArrayList<>();
			
					
			for(Reservation g : reservations.values())
			{
				proba.add(g);
			}
			objectMapper.writeValue(new File(contextPath + "/reservations.json"), proba);
			
			System.out.println(reservations +" u file");
			
		}
		catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			
		}
	}
	
	
	
	
	
	public Reservation add(Reservation reservation) {
		
		if (!reservations.containsKey(reservation.getId())) {
			String str = String.valueOf(reservation.getId());
			reservations.put(str, reservation);
			return reservations.get(reservation.getId());
		}
		
		return null;
	}

	
	public void remove(Reservation reservation) {
		if (reservations.containsKey(reservation.getId())) {
			reservations.remove(reservation.getId(), reservation);
		}
	}
	
	public void change(Reservation reservation) {
		if(reservations.containsKey(reservation.getId())) {
			String str = String.valueOf(reservation.getId());
			reservations.put(str,reservation);
		}
	}
	
	public Collection<Reservation> getGuestReservations(String user) {
		Collection<Reservation> allReservations = this.reservations.values();
		Collection<Reservation> returnReservations = new ArrayList<Reservation>();
		if(allReservations != null) {
			for(Reservation reservation : allReservations) {
				if(reservation.getGuest().equals(user)) {
					returnReservations.add(reservation);
				}
			}
		}
		return returnReservations;
	}
	
	public Collection<Reservation> getHostReservations(String user) {
		Collection<Reservation> allReservations = this.reservations.values();
		Collection<Reservation> returnReservations = new ArrayList<Reservation>();
		if(allReservations != null) {
			for(Reservation reservation : allReservations) {
				if(reservation.getApartment().getHost().equals(user)) {
					returnReservations.add(reservation);
				}
			}
		}
		return returnReservations;
	}
	
	
	public void withdrawReservation(UUID id) {
		Collection<Reservation> allReservations = this.reservations.values();
		if(allReservations != null) {
			for(Reservation res : allReservations) {
				if(res.getId().equals(id)) {
					if(res.getStatusRes().equals(ReservationStatus.CREATED)  || res.getStatusRes().equals(ReservationStatus.ACCEPTED)) {
						res.setStatusRes(ReservationStatus.WITHDRAWAL);
						String str = String.valueOf(res.getId());
						this.reservations.put(str, res);
					}
				}
			}
		}
	}
	
	public void acceptReservation(UUID id) {
		Collection<Reservation> allReservations = this.reservations.values();
		if(allReservations != null) {
			for(Reservation res : allReservations) {
				if(res.getId().equals(id)) {
					if(res.getStatusRes().equals(ReservationStatus.CREATED)) {
						res.setStatusRes(ReservationStatus.ACCEPTED);
						String str = String.valueOf(res.getId());
						this.reservations.put(str, res);
					}
				}
			}
		}
	}
	
	
	public void denyReservation(UUID id) {
		Collection<Reservation> allReservations = this.reservations.values();
		if(allReservations != null) {
			for(Reservation res : allReservations) {
				if(res.getId().equals(id)) {
					if(res.getStatusRes().equals(ReservationStatus.CREATED)  || res.getStatusRes().equals(ReservationStatus.ACCEPTED)) {
						res.setStatusRes(ReservationStatus.DENIED);
						String str = String.valueOf(res.getId());
						this.reservations.put(str, res);
					}
				}
			}
		}
	}

//	public void save() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			objectMapper.writeValue(new File(this.path + "/reservations.json"), this.reservations);
//		} catch (IOException e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//}

}



