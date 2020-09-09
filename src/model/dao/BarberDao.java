package model.dao;

import java.util.List;

import model.entities.Barber;

public interface BarberDao {
	
		// To add a Barber
		void insert(Barber obj);
		void update(Barber obj);
		void deleteById(Integer id);
		Barber findById(Integer id);
		List<Barber> findAll();
		
	
}
