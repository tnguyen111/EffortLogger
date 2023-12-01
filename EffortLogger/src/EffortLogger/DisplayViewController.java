package application;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class DisplayViewController {
	
	private long startTime;
    private long stopTime;
    private Timer timer;
    private String selectedLanguage, selectedProject, selectedlifeCycleStep, selectedEffortCategory,
    selectedDomain;

    @FXML
    private ComboBox<String> project;

    @FXML
    private ComboBox<String> lifeCycleStep;

    @FXML
    private ComboBox<String> effortCategory;

    @FXML
    private ComboBox<String> domain;

    @FXML
    private ComboBox<String> language;

    @FXML
    private Button startActivity;

    @FXML
    private Button stopActivity;

    @FXML
    private TextField content;

    @FXML
    private Text time;

    @FXML
    private void initialize() {
        // Initialize the ComboBox for languages
        project.getItems().addAll("Bussines", "Development");
        lifeCycleStep.getItems().addAll("Planning", "Information Gathering");
        effortCategory.getItems().addAll("Defects", "Project Plan");
        language.getItems().addAll("c", "c++", "Java", "JavaScript", "Python");
        domain.getItems().addAll("A", "B");

        // Add event handlers for buttons if needed
        startActivity.setOnAction(event -> handleStartActivity());
        stopActivity.setOnAction(event -> handleStopActivity());
    }

    private void handleStartActivity() {
        // Implement logic for starting activity
    	 startTime = System.currentTimeMillis();
        // logTextArea.appendText("Started tracking time for " + selectedLanguage + "\n");
         timer = new Timer(true);
         timer.scheduleAtFixedRate(new TimerTask() {
             @Override
             public void run() {
                 long currentTime = System.currentTimeMillis();
                 long elapsedTime = currentTime - startTime;
                 String formattedTime = formatElapsedTime(elapsedTime);
                 time.setText("\n"+"Counting: " + formattedTime + "\n");
             }
         }, 0, 1000);
    }

    private void handleStopActivity() {
        // Implement logic for stopping activity
    	stopTime = System.currentTimeMillis();
        if (startTime != 0) {
        	timer.cancel();
            long elapsedTime = stopTime - startTime;
            String formattedTime = formatElapsedTime(elapsedTime);
            showPopUp(elapsedTime);
            DataBaseConnection.saveParametersToDatabase(selectedEffortCategory, selectedLanguage, selectedProject, selectedlifeCycleStep, selectedDomain, startTime, stopTime);
        }         
    }
    
    private void showPopUp(long elapsedTime) {
		// TODO Auto-generated method stub
    	
    	 Stage popupStage = new Stage();
         popupStage.setTitle("Logs Information");

         Label popupLabel = new Label("Information of this Activity!");
         selectedLanguage = language.getValue();
         selectedProject = project.getValue();
         selectedlifeCycleStep = lifeCycleStep.getValue();
         selectedEffortCategory = effortCategory.getValue();
         selectedDomain = domain.getValue();
         String text = "Stop Activity in: " + formatElapsedTime(elapsedTime)
        		 + "\n"
        		 + "Start: " + formatElapsedTime(startTime) + "\n"
        		 + "Stop: " + formatElapsedTime(stopTime) + "\n"
        		 + "Language: " + selectedLanguage + "\n"
        		 + "Project: " + selectedProject + "\n"
        		 + "LifeCycleStep: " + selectedlifeCycleStep + "\n"
        		 + "EffortCategory: " + selectedEffortCategory + "\n"
        		 + "Domain: " + selectedDomain + "\n";
         
         Text textSection = new Text(text);
        

         StackPane popupLayout = new StackPane();
         popupLayout.getChildren().addAll(textSection);
      

         popupStage.setScene(new Scene(popupLayout, 400, 200));
         popupStage.show();
		
	}

    
    private String formatElapsedTime(long elapsedTime) {
        // Convert milliseconds to seconds, minutes, and hours
        long seconds = (elapsedTime / 1000) % 60;
        long minutes = (elapsedTime / (1000 * 60)) % 60;
        long hours = (elapsedTime / (1000 * 60 * 60)) % 24;

        // Format the time as "hh:mm:ss"
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
