package EffortLogger;

import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LogEditorController {

	private String[] projectTypeList = {"Business Project","Development Project"};
	private String[] categoryTypeList = {"Plans", "Deliverable", "Interuption", "Defects", "Others"};
	private String[] employeeRoleList = {"Developer", "Supervisor", "Customer"}; 
	private String[] lifeCycleStepList = {"Planning", "Information Gathering", "Information Understanding",
			"Verifying", "Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting"};
	
	private ObservableList<String> planList = FXCollections.observableArrayList("Project Plan", "Risk Management Plan", "Conceptional Design Plan"
			, "Detailed Desgin Plan", "Implementation Plan");
	private ObservableList<String> deliverableList = FXCollections.observableArrayList("Conceptional Design", "Detailed Design", "Test Cases", "Solution", 
			"Reflection", "Outline", "Draft", "Report", "User Defined", "Other");
	private ObservableList<String> interuptionList = FXCollections.observableArrayList("Break", "Phone", "Teammate", "Visitor", "Other");
	private ObservableList<String> defectList = FXCollections.observableArrayList();
	
	String date;
	String startTime;
	String stopTime;
	String employeeName;
	String employeeRole;
	String lifeCycle;
	String category;
	String categoryDetail;
	
	@FXML
	private Label categoryLabel = new Label();
	
	@FXML
	private Label otherCategoryLabelDetail = new Label();
	
	@FXML
	private TextField dateBox = new TextField();
	
	@FXML
	private TextField startTimeBox = new TextField();
	
	@FXML
	private TextField stopTimeBox = new TextField();
	
	@FXML
	private TextField employeeNameBox = new TextField();
	
	@FXML
	private TextField otherCategoryDetailBox = new TextField();
	
	@FXML
	private TextField otherCategoryLabelDetailBox = new TextField();
	
	@FXML
	private ChoiceBox<String> projectTypeBox = new ChoiceBox<>();
	
	@FXML
	private ChoiceBox<String> logEntryBox = new ChoiceBox<>();
	
	@FXML
	private ChoiceBox<String> employeeRoleBox = new ChoiceBox<>();
	
	@FXML
	private ChoiceBox<String> lifeCycleStepTypeBox = new ChoiceBox<>();
	
	@FXML
	private ChoiceBox<String> categoryTypeBox = new ChoiceBox<>();
	
	@FXML
	private ChoiceBox<String> categoryDetailBox = new ChoiceBox<>();
	
	@FXML
	private Button updateButton = new Button();
	
	@FXML
	private Button clearButton = new Button();
	
	@FXML
	private Button deleteButton = new Button();
	
	@FXML
	private Button splitButton = new Button();
	
	@FXML
	private Button proceedToConsoleButton = new Button();
	
	@FXML
	public void initialize() {
		projectTypeBox.getItems().addAll(projectTypeList);
		categoryTypeBox.getItems().addAll(categoryTypeList);
		employeeRoleBox.getItems().addAll(employeeRoleList);
		lifeCycleStepTypeBox.getItems().addAll(lifeCycleStepList);
		categoryTypeBox.setOnAction(this::getCategory);
		updateButton.setOnAction(this::update);
		clearButton.setOnAction(this::clear);
		deleteButton.setOnAction(this::delete);
	}
	
	public void getCategory(ActionEvent event) {
		String category = categoryTypeBox.getValue();
		categoryLabel.setText(category +":");
		
		otherCategoryDetailBox.setDisable(true);
		otherCategoryDetailBox.setVisible(false);
		otherCategoryLabelDetail.setVisible(false);
		otherCategoryLabelDetailBox.setDisable(true);
		otherCategoryLabelDetailBox.setVisible(false);
		
		if(categoryTypeBox.getValue().equals("Plans")) {
			categoryDetailBox.setValue("");
			categoryDetailBox.setItems(planList);
		} else if(categoryTypeBox.getValue().equals("Deliverable")) {
			categoryDetailBox.setValue("");
			categoryDetailBox.setItems(deliverableList);
			categoryDetailBox.setOnAction(this::getOtherCategoryDetail);
		} else if(categoryTypeBox.getValue().equals("Interuption")) {
			categoryDetailBox.setValue("");
			categoryDetailBox.setItems(interuptionList);
			categoryDetailBox.setOnAction(this::getOtherCategoryDetail);
		} else if(categoryTypeBox.getValue().equals("Defects")) {
			categoryDetailBox.setValue("");
			categoryDetailBox.setItems(defectList);
		} else {
			categoryDetailBox.setValue("");
			otherCategoryDetailBox.setDisable(false);
			otherCategoryDetailBox.setVisible(true);
		}
	}
	
	public void getOtherCategoryDetail(ActionEvent event) {
		otherCategoryLabelDetail.setVisible(false);
		otherCategoryLabelDetailBox.setDisable(true);
		otherCategoryLabelDetailBox.setVisible(false);
		if(categoryDetailBox.getValue().equals("User Defined") || categoryDetailBox.getValue().equals("Other")) {
			otherCategoryLabelDetail.setVisible(true);
			otherCategoryLabelDetail.setText("Enter " + categoryDetailBox.getValue() + " details here:");
			otherCategoryLabelDetailBox.setDisable(false);
			otherCategoryLabelDetailBox.setVisible(true);
		}
	}

	
	public void update(ActionEvent event) {
		date = dateBox.getText();
		startTime = startTimeBox.getText();
		stopTime = stopTimeBox.getText();
		employeeName = employeeNameBox.getText();
		employeeRole = employeeRoleBox.getValue();
		lifeCycle = lifeCycleStepTypeBox.getValue();
		if(categoryTypeBox.getValue().equals("Others")) {
			String detail = otherCategoryDetailBox.getText();
			categoryDetailBox.setValue(detail);
		}
		if((categoryDetailBox.getValue().equals("User Defined") || categoryDetailBox.getValue().equals("Other")) && !categoryTypeBox.getValue().equals("Others")) {
			categoryDetail = categoryDetailBox.getValue() + ": " + otherCategoryLabelDetailBox.getText();
		} else {
			categoryDetail = categoryDetailBox.getValue();
		}
		category = categoryTypeBox.getValue();
		System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",date,startTime,stopTime,employeeName,employeeRole,
				lifeCycle,category,categoryDetail);
	}
	
	public void clear(ActionEvent event) {
		date = "";
		startTime = "";
		stopTime = "";
		employeeName = "";
		employeeRole = "";
		lifeCycle = "";
		category = "";
		categoryDetail = "";
		System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",date,startTime,stopTime,employeeName,employeeRole,
				lifeCycle,category,categoryDetail);
	}
	
	public void delete(ActionEvent event) {
		date = "";
		startTime = "";
		stopTime = "";
		employeeName = "";
		employeeRole = "";
		lifeCycle = "";
		category = "";
		categoryDetail = "";
		System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",date,startTime,stopTime,employeeName,employeeRole,
				lifeCycle,category,categoryDetail);
	}
}
