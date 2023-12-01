package application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;

public class LoginController {

	@FXML
	private Button cancelButton;
	@FXML
	private Label loginMessageLabel;
	@FXML
	private TextField usernametxtfield;
	@FXML
	private PasswordField enterpassword;
	@FXML
	private Button signupButton;
	
	//
	public void loginButtonOnAction(ActionEvent event) {
		loginMessageLabel.setText("Login");
		if (usernametxtfield.getText().isBlank() == false && enterpassword.getText().isBlank() == false ) {
			validateLogin();
		} else {
			loginMessageLabel.setText("Please Enter a Username and password");
		}
	}
	// function to switch to signup screen
	public void signupButtonOnAction(ActionEvent event) throws Exception {
		try {
            // Load the Register screen FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registerscr.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
		 }
	}
	
	//function to full close the app with the exit button
	public void cancelButtonOnAction(ActionEvent event) {
		Stage stage = (Stage)cancelButton.getScene().getWindow();
		stage.close();
	}
	//making sure that the username and password is in the database and connecting to database
	public void validateLogin() {
		DataBaseConnection connectNow = new DataBaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String veryifylogin = "SELECT count(1) FROM user_accountinfo WHERE username ='" + usernametxtfield.getText() + "' And password ='" + enterpassword .getText();
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(veryifylogin);
			
			while(queryResult.next()) {
				if (queryResult.getInt(1)== 1) {
					//loginMessageLabel.setText("congrats");
					createAccountForm();
				}else {
					loginMessageLabel.setText("invalid login. Try again");
				}
			}
			
;		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		
	}
	// if the login info is correct then switch to the next appropriate screen
	public void createAccountForm() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("registerscr.fxml"));
			Stage registerstage = new Stage();
			registerstage.setTitle("EffortLogger");
			registerstage.setScene(new Scene(root,600,400));
			registerstage.show();
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
			
	}
	
	
}
