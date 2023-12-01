package effortConsole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class EffortConsoleController implements Initializable {
	private boolean clockIsRunning;
	//SceneBuilder
	@FXML private Rectangle coloredClockRectangle;
	@FXML private Button startActivityButton;
	@FXML private Button stopButton;
	@FXML private Button editorButton;
	@FXML private Button defectLogButton;
	@FXML private Button definitionsButton;
	@FXML private Button logsButton;
	@FXML private ChoiceBox<String> projectCB;
	@FXML private ChoiceBox<String> lifeCycleStepCB;
	@FXML private ChoiceBox<String> effortCategoryCB;
	@FXML private ChoiceBox<String> planCB;
	@FXML private Text clockText;
	
	private long startTime;
	private long endTime;
	private long elapsedTime;
	
	private ObservableList<String> projectTypeList = FXCollections.observableArrayList("Development Project", "Business Project");
	private ObservableList<String> lifeCycleItems1 = FXCollections.observableArrayList("Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan", "Planning");
	private ObservableList<String> lifeCycleItems2 = FXCollections.observableArrayList("Problem Understanding", "Requirements", "Conceptual Design", "Conceptual Design Review",
			"Detailed Design/Prototype", "Detailed Design Review", "Test Case Generation", "Solution Specification", "Solution Review", "Solution Implementation", "Unit/System Test",
			"Reflection", "Repository Update", "Information", "Gathering Information", "Understanding", "Verifying", "Outlining", "Drafting", "Finalizing", "Team Meeting",
			"Coach Meeting", "Stakeholder Meeting");
	private ObservableList<String> effortCategoryItems = FXCollections.observableArrayList("Plans", "Deliverables", "Interruptions", "Defects", "Others");
	
	public void initialize(URL url, ResourceBundle rb) {
		//alert
		Alert alert1 = new Alert(Alert.AlertType.WARNING);
		alert1.setTitle("Start Ignored");
		alert1.setContentText("The clock is already running.");
		
		//start timer
		startActivityButton.setOnAction(e -> {
			if(!clockIsRunning) {
				coloredClockRectangle.setFill(Color.GREEN);
				clockIsRunning = true;
				startTime = System.currentTimeMillis();
				clockText.setText("Clock has started");

			}
			else
				alert1.show();
			}
		);
		
		//alert
		Alert alert2 = new Alert(Alert.AlertType.WARNING);
		alert2.setTitle("Stop Ignored");
		alert2.setContentText("The clock is not running.");
		
		
		//stop button
		stopButton.setOnAction(e -> {
			if(clockIsRunning) {
				coloredClockRectangle.setFill(Color.RED);
				clockText.setText("Clock is stopped");
				clockIsRunning = false;
				endTime = System.currentTimeMillis();
				elapsedTime = System.currentTimeMillis() - startTime;
				System.out.println("Elapsed time: " + elapsedTime + " milliseconds");
				System.out.println("Project: " + projectCB.getValue());
				System.out.println("Life Cycle Step: " + lifeCycleStepCB.getValue());
				System.out.println("Effort Category: " + effortCategoryCB.getValue());
				System.out.println("Plan: " + planCB.getValue() + "\n");
			}
			else
				alert2.show();
			}
			
		);
		
		//Project Choice Box
		projectCB.setItems(projectTypeList);
		projectCB.setOnAction(event -> {
			if(projectCB.getValue().equals("Business Project")) {
				lifeCycleStepCB.setItems(lifeCycleItems1);
			} else if (projectCB.getValue().equals("Development Project"))
				lifeCycleStepCB.setItems(lifeCycleItems2);
		});
		
		effortCategoryCB.setItems(FXCollections.observableArrayList("Plans"));
		planCB.setItems(FXCollections.observableArrayList("Project Plan"));
		lifeCycleStepCB.setOnAction(e -> {
			effortCategoryCB.setValue("Plans");
			planCB.setValue("Project Plan");
		});
		
		//navigation buttons
		editorButton.setOnAction(e -> System.out.println("navigate to effort log editor"));
		defectLogButton.setOnAction(e -> System.out.println("navigate to defect log console"));
		defectLogButton.setOnAction(e -> System.out.println("navigate to defect log console"));
		definitionsButton.setOnAction(e -> System.out.println("navigate to definitions tab"));
		logsButton.setOnAction(e -> System.out.println("navigate to log manager"));
	}
}
