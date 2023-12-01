package application;

import java.sql.Connection;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegisterController {
	
	@FXML
	private Button CloseButton;
	@FXML
	private Label RegistrationMssgeLabel;
	@FXML
	private PasswordField SetpasswordField;
	@FXML
	private PasswordField ConfirmPasswordField;
	@FXML
	private Label confirmpasswordLabel;
	@FXML
	private TextField usernameTextField;
	// when they click close it will just take them back to the loginscreen
	public void closeButtonOnAction(ActionEvent event) {
		try {
            // Load the Register screen FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Loginscr.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
		 }
	}
	
	// once the fields of registration is filled in correctly
	// they will be taken bakc after pressing the register button
	public void registerButtonOnAction(ActionEvent event) {
		
		if(SetpasswordField.getText().equals(ConfirmPasswordField.getText())) {
			registerUser();
			confirmpasswordLabel.setText("");
		}else {
			confirmpasswordLabel.setText("Password does not Match!");
		}
	}
	// connecting to database and then adding the appropriate
	// username and password user indicate for their account. For 
	// it to be used as their usernmane and password and then adding to database.
	public void registerUser() {
		DataBaseConnection connectNow = new DataBaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String username = usernameTextField.getText();
		String password = SetpasswordField.getText();
		
		String insertFields = "INSERT INTO user_accountinfo(username, password) VALUES('";
		String insertValues = username +"','" + password + "',')";
		String insertToRegister = insertFields + insertValues;
		
		try {
			Statement statement = connectDB.createStatement();
			statement.executeUpdate(insertToRegister);
			
			RegistrationMssgeLabel.setText("User has been registered!");
		}catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		
	}
	
}
