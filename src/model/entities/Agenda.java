package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Agenda {

	private Integer id;
	private Date appointment;	
	private Client client; // this attribute/connection must be the sub class in order to avoid mistakes from the developer
	private Barber barber; // this attribute/connection must be the sub class in order to avoid mistakes from the developer
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	// CONSTRUCTORS
	public Agenda() {
	}

	public Agenda(Integer id, Date appointment, Client client, Barber barber) {
		this.id = id;
		this.appointment = appointment;
		this.client = client;
		this.barber = barber;
	}

	// This overload constructor exists only to show available dates in the screen.
	public Agenda(Date appointment) {
		this.appointment = appointment;
	}
	
	
	// GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAppointment() {
		return appointment;
	}

	public void setAppointment(Date appointment) {
		this.appointment = appointment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Barber getBarber() {
		return barber;
	}

	public void setBarber(Barber barber) {
		this.barber = barber;
	}

	@Override
	public String toString() {
		return "Agenda [time=" + sdf.format(appointment) + ", client=" + client.getName() + ", barber=" + barber.getName() + "]";
	}

}
