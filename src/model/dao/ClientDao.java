package model.dao;

import java.util.List;

import model.entities.Client;

public interface ClientDao {

	void insert(Client obj); // To add a client
	void update(Client obj); 
	void deleteById(Integer id);
	Client findById(Integer id);
	List<Client> findAll();
	
}
