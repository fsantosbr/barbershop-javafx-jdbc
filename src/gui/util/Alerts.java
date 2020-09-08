package gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// class to show some alert windows. Will notify something.
public class Alerts {

	public static void showAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}
}