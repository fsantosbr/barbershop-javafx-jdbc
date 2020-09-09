package model.dao;

import model.dao.impl.ClientDaoJDBC;

public class DaoFactory {

	public static ClientDao createClientDao() {
		return new ClientDaoJDBC();
	}
}
