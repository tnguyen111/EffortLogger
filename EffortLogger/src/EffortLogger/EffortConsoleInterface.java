package effortConsole;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
 
public class EffortConsoleInterface extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
    	try {
        //root pane will be a tab pane that alternates between tabs
		TabPane tabPane = new TabPane();
		
		//load FXML file into effortConsolePane, then declare new tab, then set content of tab to the effortConsolePane
		FXMLLoader loader = new FXMLLoader(getClass().getResource("consolePrototypeGUI.fxml"));
		Parent effortConsolePane = loader.load();
		Tab tab1 = new Tab();
		tab1.setText("Effort Console");
		tab1.setContent(effortConsolePane);
		
		//load FXML file into definitionsPane, then declare new tab, then set content of tab to the definitionsPane
		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("definitionsPrototypeGUI.fxml"));
		Parent definitionsPane = loader2.load();
		Tab tab2 = new Tab();
		tab2.setText("Definitions");
		tab2.setContent(definitionsPane);
		
		//add tabs to tabPane
		tabPane.getTabs().addAll(tab1,tab2);
		
		//declare scene with tabPane, assign scene to primaryStage, show primaryStage
		Scene scene = new Scene(tabPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    	} catch(Exception e) {
			e.printStackTrace();
		}
    	

    }
    
    
    
    
}