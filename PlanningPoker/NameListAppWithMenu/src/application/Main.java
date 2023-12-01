package application;

import javafx.application.Application; 
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    private Map<String, Map<String, Integer>> userEstimations = new HashMap<>();
    private Map<String, String> activityNames = new HashMap<>();
    private VBox estimationList = new VBox(10);
    private Label averageLabel = new Label("");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
        primaryStage.setTitle("Planning Poker");

        Label activityLabel = new Label("Select Activity:");
        ComboBox<String> activityComboBox = new ComboBox<>();
        activityComboBox.setPromptText("Select an Activity");
        activityComboBox.setEditable(true);
        activityComboBox.getItems().addAll("ChatGPT", "Homework", "HrCSPROJECT", "NewIPHONEApp");


        Label estimateLabel = new Label("Select Estimate:");
        ComboBox<String> estimateComboBox = new ComboBox<>();
        estimateComboBox.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7");
        estimateComboBox.setValue("0");

        Label userLabel = new Label("User Name:");
        TextField userInput = new TextField();

        Button submitButton = new Button("Submit Estimate");
        Button averageButton = new Button("Calculate Average");

        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(activityLabel, activityComboBox, estimateLabel, estimateComboBox, userLabel, userInput, submitButton, averageButton);

        Label activityNameLabel = new Label("Add Activity:");
        TextField activityNameInput = new TextField();
        Button addActivityButton = new Button("Add Activity");

        addActivityButton.setOnAction(e -> {
            String activityName = activityNameInput.getText();
            if (!activityName.isEmpty()) {
                activityComboBox.getItems().add(activityName);
                activityNames.put(activityName, activityName);
                activityNameInput.clear();
            }
        });

        VBox inputBoxVBox = new VBox(10);
        inputBoxVBox.getChildren().addAll(inputBox, activityNameLabel, activityNameInput, addActivityButton);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(inputBoxVBox, estimationList, averageLabel);

        submitButton.setOnAction(e -> {
            String task = activityComboBox.getValue();
            String estimate = estimateComboBox.getValue();
            String userName = userInput.getText();
            String activity = activityComboBox.getValue();

            if (!task.isEmpty() && !userName.isEmpty() && !activity.isEmpty()) {
                storeEstimation(userName, task, Integer.parseInt(estimate));
                displayEstimation(userName, task, Integer.parseInt(estimate), activity);
                userInput.clear();
                estimateComboBox.setValue("0");
            }
        });

        averageButton.setOnAction(e -> calculateAverageScores());
        
        Pane imagePane = new Pane();
        Image backgroundImage = new Image("background1.jpg", true); // true for background loading
        if (backgroundImage.isError()) {
            System.out.println("Error loading the image.");
        }
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
                BackgroundRepeat.REPEAT, 
                BackgroundRepeat.REPEAT, 
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        imagePane.setBackground(new Background(backgroundImg));

        // Semi-transparent grey pane
        Pane greyPane = new Pane();
        BackgroundFill greyFill = new BackgroundFill(Color.rgb(128, 128, 128, 0.5), CornerRadii.EMPTY, Insets.EMPTY); // 50% transparent grey
        greyPane.setBackground(new Background(greyFill));

        // StackPane to layer the image and grey background
        StackPane root = new StackPane();
        root.getChildren().addAll(imagePane, greyPane, layout); // Add layout as the last child

        Scene scene = new Scene(root, 1000, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void storeEstimation(String userName, String task, int estimate) {
        userEstimations.computeIfAbsent(userName, k -> new HashMap<>()).put(task, estimate);
    }

    private void displayEstimation(String userName, String task, int estimate, String activity) {
        Label estimateLabel = new Label("Estimate: " + estimate);
        Label userLabel = new Label("User: " + userName);
        Label activityLabel = new Label("Activity: " + activity);

        VBox userEstimationBox = new VBox(5);
        userEstimationBox.getChildren().addAll(userLabel, estimateLabel, activityLabel);

        estimationList.getChildren().add(userEstimationBox);
    }

    private void calculateAverageScores() {
        StringBuilder averageText = new StringBuilder("Average Scores:\n");

        for (String task : userEstimations.values().iterator().next().keySet()) {
            int total = 0;
            int count = 0;
            for (Map<String, Integer> estimations : userEstimations.values()) {
                if (estimations.containsKey(task)) {
                    total += estimations.get(task);
                    count++;
                }
            }
            double average = (double) total / count;

            averageText.append("Activity: ").append(task).append(" | Average: ").append(String.format("%.2f", average)).append("\n");
        }

        averageLabel.setText(averageText.toString());
    }
}
