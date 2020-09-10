package application;
	
import java.io.IOException;
import java.sql.SQLData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.dao.AgendaDao;
import model.dao.BarberDao;
import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Agenda;
import model.entities.Barber;
import model.entities.Client;
import model.entities.Person;


public class Main extends Application {
	
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			// Parent parent = loader.load();
			AnchorPane anchorPane = loader.load();
			
			mainScene = new Scene(anchorPane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Barber Shop - By @fsantosbr");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// method get of the of the attribute Scene
	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static void main(String[] args) {
		//launch(args);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("New client? New Barber?");
		System.out.println("Inform who you are!!");
		System.out.println("Email and Phone number: ");
		
		// User inform his/her email and phone number to validate
		String email = "nicolas@gmail.com";
		String phoneNumber = "1159322105";
		
		
		// allocation only to test - 
		Person unknowClient = new Client(null, null, email, phoneNumber);
		Person unknowBarber = new Barber(null, null, email, phoneNumber);
		
		
		// data captured from database (MOCK)
		Client p1 = new Client(1, "Nicolas Santos", "nicolas@gmail.com", "1159322105");		
		Barber p2 = new Barber(2, "Fabio Alves", "fabio@barber.com", "11965398826");
		
	
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
		
		
//		Agenda agenda = new Agenda(1, new Date(), p1, p2);
//		System.out.println(agenda);
		
		AgendaDao agendaDao = DaoFactory.createAgendaDao();
		Agenda ag = agendaDao.findById(3);
		System.out.println(ag);
		
		System.out.println("by client");
		Client client = new Client(1, null, null, null);
		List<Agenda> list3 = agendaDao.findByClient(client);
		for (Agenda obj : list3) {
			System.out.println(obj);
		}
		
		System.out.println("by barber");
		Barber barber = new Barber(4, null, null, null);
		List<Agenda> list4 = agendaDao.findByBarber(barber);
		for (Agenda obj : list4) {
			System.out.println(obj);
		}
		
		System.out.println("find all");
		list4 = agendaDao.findAll();
		for (Agenda obj : list4) {
			System.out.println(obj);
		}
		
	
//		System.out.println("insert");
//		Client c1 = new Client(5, "Pedro", "p@gmail.", "1159322105");	
//		Agenda newAgenda = new Agenda(null, new Date(), c1, barber);
//		agendaDao.insert(newAgenda);
//		System.out.println("New Id created = " + newAgenda.getId());
		
	
//		System.out.println("update");
//		Client c2 = new Client(5, "Pedro", "p@gmail.", "1159322105");
//		Barber b1 = new Barber(4, "M", "email", "12345");
//		Agenda ag1 = new Agenda(12, new Date(), c2, b1);
//		ag1 = agendaDao.findById(12);
//		ag1.setClient(c2);
//		agendaDao.update(ag1);
		
		
//		System.out.println("delete");
//		agendaDao.deleteById(10);
		
		
		ClientDao clientDao = DaoFactory.createClientDao();
		BarberDao barberDao = DaoFactory.createBarberDao();
//		Client c1 = new Client(null, "Pedro", "p@gmail.", "1159322105");
//		clientDao.insert(c1);		

//		Barber b1 = new Barber(null, "Pedro", "p@gmail.", "1159322105");
//		barberDao.insert(b1);
		
//		Client c1 = new Client(9, "Pedro", "p@gmail.", "1159322105");
//		c1.setName("Pedro 2");
//		clientDao.update(c1);
//		
//		Barber b1 = new Barber(5, "Pedro", "p@gmail.", "1159322105");
//		b1.setName("Pedro 2");
//		barberDao.update(b1);
		
		clientDao.deleteById(10);
		barberDao.deleteById(5);
		
		System.out.println("client");
		Client c2 = clientDao.findById(1);
		System.out.println(c2);
		
		System.out.println("barber");
		Barber b2 = barberDao.findById(1);
		System.out.println(b2);
		
		System.out.println("find all");
		List<Client> list5 = new ArrayList<>();
		list5 = clientDao.findAll();
		for (Client obj : list5) {
			System.out.println(obj);
		}
		
		System.out.println("find all");
		List<Barber> list6 = new ArrayList<>();
		list6 = barberDao.findAll();
		for (Barber obj : list6) {
			System.out.println(obj);
		}
		
	}
}
