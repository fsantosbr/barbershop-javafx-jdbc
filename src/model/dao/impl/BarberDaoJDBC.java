package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.BarberDao;
import model.entities.Barber;
import model.entities.Client;

public class BarberDaoJDBC implements BarberDao {

	private Connection conn;
	
	public BarberDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void insert(Barber obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO barber "
					+ "(Name, Email, PhoneNumber) "
					+ "VALUES "
					+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getPhoneNumber());
			
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void update(Barber obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE barber "
					+ "SET Name = ?, Email = ?, PhoneNumber = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getPhoneNumber());
			st.setInt(4, obj.getId());
			st.executeUpdate();		
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}		
	}

	
	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM barber "
					+ "WHERE Id = ?");
			
			st.setInt(1, id);
			st.executeUpdate();			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public Barber findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM barber WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Barber bar = instantiateBarber(rs);
				return bar;
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
	
	
	// Class to instantiate automatically an object of Barber
		private Barber instantiateBarber(ResultSet rs) throws SQLException {
			Barber bar = new Barber();
			bar.setId(rs.getInt("Id"));
			bar.setName(rs.getString("Name"));
			bar.setEmail(rs.getNString("Email"));
			bar.setPhoneNumber(rs.getNString("PhoneNumber"));
			return bar;
		}

		
	@Override
	public List<Barber> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM barber ORDER BY Name");
			rs = st.executeQuery();
			
			List<Barber> list = new ArrayList<>();
			
			while (rs.next()) {
				Barber obj = instantiateBarber(rs);
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
