package EffortLogger;

import javafx.application.Application;
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

        // Create input fields for defect details
        TextField defectNameInput = new TextField();
        TextField defectDescriptionInput = new TextField();
        ComboBox<String> defectCategoryInput = new ComboBox<>();
        defectCategoryInput.getItems().addAll("High", "Moderate", "Low");
        defectCategoryInput.setValue("Low");

        // Create buttons for adding and removing defects
        Button addButton = new Button("Add Defect");
        addButton.setOnAction(e -> addDefect(defectNameInput, defectDescriptionInput, defectCategoryInput));
        Button removeButton = new Button("Remove Defect");
        removeButton.setOnAction(e -> removeDefect());

        // Set preferred widths for text fields
        defectNameInput.setPrefWidth(200);
        defectDescriptionInput.setPrefWidth(300);

        // Create a vertical box to arrange elements
        VBox vBox = new VBox();
        vBox.setPrefWidth(600);
        vBox.getChildren().addAll(defectTable, defectNameInput, defectDescriptionInput, defectCategoryInput, addButton, removeButton);

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
    private void addDefect(TextField nameInput, TextField descriptionInput, ComboBox<String> categoryInput) {
        String name = nameInput.getText();
        String description = descriptionInput.getText();
        String category = categoryInput.getValue();
        defects.add(new Defect(name, description, category));
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

        public Defect(String defectName, String defectDescription, String defectCategory) {
            this.defectName = new SimpleStringProperty(defectName);
            this.defectDescription = new SimpleStringProperty(defectDescription);
            this.defectCategory = new SimpleStringProperty(defectCategory);
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