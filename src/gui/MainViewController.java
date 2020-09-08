package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {
	
	// Menu items from the scene builder (MainView.fxml)
	@FXML
	private MenuItem menuItemSignUp; // controller followed the name of the menuitem on the scene builder
	@FXML
	private MenuItem menuItemSignIn; 
	@FXML
	private MenuItem menuItemAbout; 
	
	
	// Event handler for each menu item
	@FXML
	public void onMenuItemSignUpAction() {
		System.out.println("onMenuItemSignUpAction() create account");
	}
	@FXML
	public void onMenuItemSignInAction() {
		System.out.println("onMenuItemSignInAction() login");
	}
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}
	
	
	// class to change a screen (load a new fxml view)
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane newPane = loader.load();
			Scene mainScene = Main.getMainScene();
			
			// Getting a reference of the second main Pane from our screen (MainView)
			Pane secondMainPane = (Pane) ((AnchorPane) mainScene.getRoot()).getChildren().get(1);
			
			// Getting a reference of the Menuitem (first child)
			//Node mainMenu = secondMainPane.getChildren().get(0); - REMOVER TO USER FOR THE FIRST PANE
			secondMainPane.getChildren().clear(); // clearing all children
			
			// Adding the children to the new scene
			//secondMainPane.getChildren().add(mainMenu); - REMOVER TO USER FOR THE FIRST PANE
			secondMainPane.getChildren().addAll(newPane.getChildren());
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
		
	}
}
