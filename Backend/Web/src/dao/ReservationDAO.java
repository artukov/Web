package dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Reservation;

public class ReservationDAO {

	private HashMap<String, Reservation> reservations;
	
	private String path;
	
	public ReservationDAO() {
		super();
		// TODO Auto-generated constructor stub
		this.reservations = new HashMap<String, Reservation>();
	}

	public ReservationDAO(HashMap<String, Reservation> reservations, String path) {
		super();
		this.reservations = reservations;
		this.path = path;
	}
	
	public ReservationDAO(String path) {
		this();
		this.path = path;
//		this.load();
	}

	public HashMap<String, Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(HashMap<String, Reservation> reservations) {
		this.reservations = reservations;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	//methods
//	public List<Reservation> findAllReservationsByDeleted(boolean deleted) {
//		List<Reservation> allReservations = new ArrayList<Reservation>();
//		for(Reservation u: this.getReservations().values()) {
//			if(u.isDeleted() == deleted) {
//				allReservations.add(u);
//			}
//		}
//		return allReservations;
//	}
//	
//	public List<Reservation> findAllReservationsByDeletedAndBlocked(boolean deleted, boolean blocked) {
//		List<Reservation> allReservations = new ArrayList<Reservation>();
//		for(Reservation u: this.getReservations().values()) {
//			if(u.isDeleted() == deleted && u.isBlocked() == blocked) {
//				allReservations.add(u);
//			}
//		}
//		return allReservations;
//	}
//	
//	public List<Reservation> findAllReservationsByDeletedAndReservationType(boolean deleted, ReservationType reservationType) {
//		List<Reservation> allReservations = new ArrayList<Reservation>();
//		for(Reservation u: this.getReservations().values()) {
//			if(u.isDeleted() == deleted && u.getReservationType().equals(reservationType)) {
//				allReservations.add(u);
//			}
//		}
//		return allReservations;
//	}
//	
//	public List<Reservation> findAllReservationsByDeletedAndReservationTypeAndBlocked(boolean deleted, ReservationType reservationType, boolean blocked) {
//		List<Reservation> allReservations = new ArrayList<Reservation>();
//		for(Reservation u: this.getReservations().values()) {
//			if(u.isDeleted() == deleted && u.getReservationType().equals(reservationType) && u.isBlocked() == blocked) {
//				allReservations.add(u);
//			}
//		}
//		return allReservations;
//	}
//	
//	public Reservation findOneByReservationname(String reservationname) {
//		return reservations.get(reservationname);
//	}
//	
//	public boolean addReservation(Reservation reservation) {
//		if(reservations.containsKey(reservation.getReservationname())) {
//			return false;
//		}
//		reservations.put(reservation.getReservationname(), reservation);
//		this.save();
//		return true;
//	}
//	
//	public boolean updateReservation(Reservation reservation) {
//		if(!reservations.containsKey(reservation.getReservationname())) {
//			return false;
//		}
//		reservations.put(reservation.getReservationname(), reservation);
//		this.save();
//		return true;
//	}
//	
//	public void deleteReservation(String reservationname) {
//		Reservation reservation = findOneByReservationname(reservationname);
//		reservation.setDelted(true);
//		this.save();
//	}
//	
//	public void blockReservation(String reservationname) {
//		Reservation reservation = findOneByReservationname(reservationname);
//		reservation.setBlocked(true);
//		this.save();
//	}
//	
//	public void unblockReservation(String reservationname) {
//		//maybe check if reservation was blocked? if(isn't blocked) threw new Exception
//		Reservation reservation = findOneByReservationname(reservationname);
//		reservation.setBlocked(false);
//		this.save();
//	}
	
//	public void load() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			this.reservations = objectMapper.readValue(new File(this.path+"/reservations.json"), new TypeReference<HashMap<String, Reservation>>() {});
//			System.out.println("Ucitavam reservatione");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("Error - Ucitavam reservatione");
//		}
//	}
	
	public void save() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(this.path + "/reservations.json"), this.reservations);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}