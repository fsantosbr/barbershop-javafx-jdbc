package model.entities;

import java.io.Serializable;

// Class to handle the Client attributes
public class Client extends Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	// CONSTRUCTORS
	public Client() {
		super();
	}

	public Client(Integer id, String name, String email, String phoneNumber) {
		super(id, name, email, phoneNumber);
	}
	
}
