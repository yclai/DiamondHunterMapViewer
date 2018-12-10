package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {

	private AnchorPane root;

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();//fxml variable
			loader.setLocation(Main.class.getResource("mapViewer.fxml"));
			root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	

}