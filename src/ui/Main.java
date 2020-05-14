package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
	
	private RaceGUI raceGUI;
	
	public static void main(String[] args){
		launch(args);
	}
	public Main() {
		raceGUI = new RaceGUI();
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Algorithms-Race.fxml"));
		fxmlLoader.setController(raceGUI);
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.setTitle("Basic Algorithms race");
		stage.setScene(scene);
		stage.show();
	}
}