module EffortLogger {
	requires javafx.controls;
	requires javafx.fxml;
	
	exports EffortLogger;
	opens EffortLogger to javafx.graphics, javafx.fxml;
}
