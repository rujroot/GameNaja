package logic;

import drawing.GameScreen;
import dungeon.GenerateDungeon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.SceneController;

public class Main extends Application {
	
	public static Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/scene/Start.fxml"));

			Scene startGame = new Scene(root);
			stage.setScene(startGame);
			stage.setTitle("Game Naja eiei");
			stage.show();
			Main.stage = stage;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static GameLogic getLogic() {
		return SceneController.getLogic();
	}

	public static GameScreen getGameScreen() {
		return SceneController.getGameScreen();
	}


}
