package application;
	
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import model.entities.Agenda;
import model.entities.Barber;
import model.entities.Client;
import model.entities.Person;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		
		System.out.println("New client? New Barber?");
		System.out.println("Inform who you are!!");
		System.out.println("Email and Phone number: ");
		
		// User inform his/her email and phone number to validate
		String email = "nicolas@gmail.com";
		Long phoneNumber = 1159322105L;
		
		
		// allocation only to test - 
		Person unknowClient = new Client(null, null, email, phoneNumber);
		Person unknowBarber = new Barber(null, null, email, phoneNumber);
		
		
		// data captured from database (MOCK)
		Client p1 = new Client(1, "Nicolas Santos", "nicolas@gmail.com", 1159322105L);		
		Barber p2 = new Barber(2, "Fabio Alves", "fabio@barber.com", 11965398826L);
		
	
		// future method to get a collection of Person
		List<Person> list = new ArrayList<>(); // list to collect all Person from database
		list.add(p1);
		list.add(p2);
		
		List<Person> list2 = new ArrayList<>(); // list to collect the result of the equals using a for each
		
		for (Person ps : list) {
			if (unknowClient.equals(ps) == true) {
				list2.add(ps);
			}
			if (unknowBarber.equals(ps) == true) {
				list2.add(ps);
			}
		}
		
		if (list2.isEmpty()) {
			System.out.println("User NOT identified!");
		}
		else {
			for (Person p : list2) {
				System.out.println("You're logged as: " + p);
				System.out.println(p.getClass().getName()); // Maybe later, we'll use this info to check the permission might have
			}
		}
		
		// change later the collections. try to use Map ou Set
		// for now, let's suppose the system knows what kind of user is placed
		
		
		Agenda agenda = new Agenda(1, new Date(), p1, p2);
		System.out.println(agenda);
	}
}
