package model.entities;

import java.io.Serializable;

//Class to handle the Barber attributes
public class Barber extends Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// CONSTRUCTORS
	public Barber() {
		super();
	}

	public Barber(Integer id, String name, String email, Long phoneNumber) {
		super(id, name, email, phoneNumber);
	}
	
	
	// METHODS
	
}
