package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.AgendaDao;
import model.entities.Agenda;
import model.entities.Barber;
import model.entities.Client;

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
				Client cli = new Client();
				Barber bar = new Barber();
				cli.setId(rs.getInt("ClientId"));
				cli.setName(rs.getString("ClientName"));
				bar.setId(rs.getInt("BarberId"));
				bar.setName(rs.getString("BarberName"));
				
				Agenda obj = new Agenda();
				obj.setId(rs.getInt("Id"));
				obj.setTime(rs.getDate("Appointment"));
				obj.setClient(cli);
				obj.setBarber(bar);
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

	
	@Override
	public List<Agenda> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
