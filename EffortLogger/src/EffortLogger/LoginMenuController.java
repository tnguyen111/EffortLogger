package EffortLogger;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginMenuController {
	//setting all properties to use
	@FXML
	private Button loginButton = new Button();
	@FXML
	private Label Wronglogin = new Label();
	@FXML
	private TextField username = new TextField();
	@FXML
	private PasswordField password = new PasswordField();
	
	@FXML
	public void initialize() {
		loginButton.setOnAction(arg0 -> {
			try {
				checkLogin(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	//method to what happens when the login button is pressed

//checking the user login
public void checkLogin(ActionEvent event) throws IOException{
	if(username.getText().equals("UserName") && password.getText().equals("123")) {
		Wronglogin.setText("Sucess!");
	}
	else if(username.getText().isEmpty()&& password.getText().isEmpty()) {
		Wronglogin.setText("Please enter your data!");
	}
	else {
		Wronglogin.setText("Wrong username or password");
		}
	}	
}
