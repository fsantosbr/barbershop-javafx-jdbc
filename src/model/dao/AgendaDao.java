package model.dao;

import java.util.List;

import model.entities.Agenda;

public interface AgendaDao {

	void insert(Agenda obj); // To add a Agenda
	void update(Agenda obj); 
	void deleteById(Integer id);
	Agenda findById(Integer id);
	List<Agenda> findAll();
}
