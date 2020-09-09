package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.AgendaDao;
import model.entities.Agenda;
import model.entities.Barber;
import model.entities.Client;
import model.entities.Person;

// The class that receives the interface and the methods created there
public class AgendaDaoJDBC implements AgendaDao {

	private Connection conn;
	
	public AgendaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void insert(Agenda obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Agenda obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Agenda findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT agenda.*,client.Name as ClientName, barber.Name as BarberName "
					+ "FROM agenda INNER JOIN client "
					+ "ON agenda.ClientId = client.Id "
					+ "INNER JOIN barber "
					+ "ON agenda.BarberId = barber.Id "
					+ "WHERE agenda.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Client cli = instantiateClient(rs);
				Barber bar = intantiateBarber(rs);						
				Agenda obj = instantiateAgenda(rs, cli, bar);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	
	// Class to instantiate automatically an object of Agenda
	private Agenda instantiateAgenda(ResultSet rs, Client cli, Barber bar) throws SQLException {
		Agenda obj = new Agenda();
		obj.setId(rs.getInt("Id"));
		obj.setTime(rs.getDate("Appointment"));
		obj.setClient(cli);
		obj.setBarber(bar);
		return obj;
	}


	// Class to instantiate automatically an object of Barber
	private Barber intantiateBarber(ResultSet rs) throws SQLException {
		Barber bar = new Barber();
		bar.setId(rs.getInt("BarberId"));
		bar.setName(rs.getString("BarberName"));
		return bar;
	}

	
	// Class to instantiate automatically an object of Client
	private Client instantiateClient(ResultSet rs) throws SQLException {
		Client cli = new Client();
		cli.setId(rs.getInt("ClientId"));
		cli.setName(rs.getString("ClientName"));
		return cli;
	}


	@Override
	public List<Agenda> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Agenda> findByClient(Client client) {
		String command = "ClientId";
		List<Agenda> obj = findByClientOrBarber(client, command);
		return obj;
	}
	
	@Override
	public List<Agenda> findByBarber(Barber barber){
		String command = "BarberId";
		List<Agenda> obj = findByClientOrBarber(barber, command);
		return obj;
	}
	
	
	public List<Agenda> findByClientOrBarber(Person person, String command) {
		/*
		 * This method was created to avoid the repetition in the findByClient and findByBarber methods
		 * It's only possible cause we have a super class for both classes
		 */
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT agenda.*,client.Name as ClientName, barber.Name as BarberName "
					+ "FROM agenda INNER JOIN client "
					+ "ON agenda.ClientId = client.Id "
					+ "INNER JOIN barber "
					+ "ON agenda.barberId = barber.Id "
					+ "WHERE " + command + " = ?");
			
			st.setInt(1, person.getId());
			rs = st.executeQuery();
			
			List<Agenda> list = new ArrayList<>();
			Map<Integer, Client> mapCli = new HashMap<>();
			Map<Integer, Barber> mapBar = new HashMap<>();
			
			while (rs.next()) {
				
				// Using Map<T, T> we're certifying that our Client and Barber are not instantiate twice for cases that we do have the same Client or same Barber
				Client cli = mapCli.get(rs.getInt("ClientId"));
				Barber bar = mapBar.get(rs.getInt("BarberId"));
				if (cli == null) {
					cli = instantiateClient(rs);
					mapCli.put(rs.getInt("ClientId"), cli);
				}
				if (bar == null) {
					bar = intantiateBarber(rs);
					mapBar.put(rs.getInt("BarberId"), bar);
				}
				
				Agenda obj = instantiateAgenda(rs, cli, bar);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}	
	
	
}
