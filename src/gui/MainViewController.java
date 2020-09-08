package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
		System.out.println("onMenuItemAboutAction()");
	}
	
	
	
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
}
