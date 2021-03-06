package model.dao;

import db.DB;
import model.dao.impl.AgendaDaoJDBC;
import model.dao.impl.BarberDaoJDBC;
import model.dao.impl.ClientDaoJDBC;

public class DaoFactory {

	
	public static AgendaDao createAgendaDao() {
		return new AgendaDaoJDBC(DB.getConnection());
	}
	
	
	public static ClientDao createClientDao() {
		return new ClientDaoJDBC(DB.getConnection());
	}
	
	public static BarberDao createBarberDao() {
		return new BarberDaoJDBC(DB.getConnection());
	}
}
