package EffortLogger;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DefectConsole extends Application {
	
    // Create a table view to display defects
    private TableView<Defect> defectTable = new TableView<>();
    // Create an observable list to store defects
    private final ObservableList<Defect> defects = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
    	
    	
        primaryStage.setTitle("Defect Console");

        // Create table columns for defect data
        TableColumn<Defect, String> defectNameColumn = createTableColumn("Defect Name", "defectName");
        TableColumn<Defect, String> defectDescriptionColumn = createTableColumn("Defect Description", "defectDescription");
        TableColumn<Defect, String> defectCategoryColumn = createTableColumn("Defect Category", "defectCategory");

        // Add table columns to the defect table
        defectTable.getColumns().addAll(defectNameColumn, defectDescriptionColumn, defectCategoryColumn);
        defectTable.setItems(defects);

        Label projectLabel = new Label("Select the Project:");
        
        ComboBox<String> projectInput = new ComboBox<>();
        projectInput.getItems().addAll("Development Project", "Business Project");
        projectInput.setValue("Development Project");
        
        Label nameLabel = new Label("Defect Name:");
        Label descriptionLabel = new Label("Defect Description:");
        Label categoryLabel = new Label("Defect Category:");
        Label actionsLabel = new Label("Defect Actions:");
        
        // Create input fields for defect details
        TextField defectNameInput = new TextField();
        TextField defectDescriptionInput = new TextField();
        ComboBox<String> defectCategoryInput = new ComboBox<>();
        defectCategoryInput.getItems().addAll("High", "Moderate", "Low");
        defectCategoryInput.setValue("Low");

        // Create buttons for adding and removing defects
        Button addButton = new Button("Add Defect");
        addButton.setOnAction(e -> addDefect(defectNameInput, defectDescriptionInput, defectCategoryInput, projectInput));
        Button removeButton = new Button("Remove Defect");
        removeButton.setOnAction(e -> removeDefect());

        // Set preferred widths for text fields
        defectNameInput.setPrefWidth(150);
        defectDescriptionInput.setPrefWidth(200);

        // Create a vertical box to arrange elements
        VBox vBox = new VBox();
        vBox.setPrefWidth(500);
        
        HBox buttonBox = new HBox(10); // Adjust spacing as needed
        buttonBox.getChildren().addAll(addButton, removeButton);
        
        vBox.getChildren().addAll(projectLabel, projectInput, nameLabel, defectNameInput, descriptionLabel, 
        		defectDescriptionInput, categoryLabel, defectCategoryInput, actionsLabel, buttonBox, defectTable);

        // Create the main scene and display it
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to create a table column
    private TableColumn<Defect, String> createTableColumn(String columnName, String property) {
        TableColumn<Defect, String> column = new TableColumn<>(columnName);
        column.setCellValueFactory(data -> data.getValue().getPropertyByName(property));
        return column;
    }

    // Add a new defect to the list
    private void addDefect(TextField nameInput, TextField descriptionInput, ComboBox<String> categoryInput, ComboBox<String> projectInput) {
    	 String project = projectInput.getValue();
    	 String name = nameInput.getText();
    	 String description = descriptionInput.getText();
    	 String category = categoryInput.getValue();
    	 defects.add(new Defect(name, description, category, project));
    	 nameInput.clear();
    	 descriptionInput.clear();
    }

    // Remove the selected defect from the list
    private void removeDefect() {
        Defect selectedDefect = defectTable.getSelectionModel().getSelectedItem();
        if (selectedDefect != null) {
            defects.remove(selectedDefect);
        }
    }

    // Define a custom Defect class with properties
    public static class Defect {
        private final SimpleStringProperty defectName;
        private final SimpleStringProperty defectDescription;
        private final SimpleStringProperty defectCategory;
        private final SimpleStringProperty project;

        public Defect(String defectName, String defectDescription, String defectCategory, String project) {
            this.defectName = new SimpleStringProperty(defectName);
            this.defectDescription = new SimpleStringProperty(defectDescription);
            this.defectCategory = new SimpleStringProperty(defectCategory);
            this.project = new SimpleStringProperty(project);
        }

        // Retrieve property by name
        public SimpleStringProperty getPropertyByName(String propertyName) {
            switch (propertyName) {
                case "defectName":
                    return defectName;
                case "defectDescription":
                    return defectDescription;
                case "defectCategory":
                    return defectCategory;
                default:
                    return null;
            }
        }
    }
}
