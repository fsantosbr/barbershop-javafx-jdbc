package model.dao;

import model.dao.impl.AgendaDaoJDBC;
import model.dao.impl.ClientDaoJDBC;

public class DaoFactory {

	
	public static AgendaDao createAgendaDao() {
		return new AgendaDaoJDBC();
	}
	
	
	public static ClientDao createClientDao() {
		return new ClientDaoJDBC();
	}
}
