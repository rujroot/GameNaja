package logic;

import animation.AnimationController;
import data.DataEntity;
import data.Point;
import drawing.GameScreen;
import dungeon.GenerateDungeon;
import dungeon.Room;
import entity.Player;
import entity.Shopkeeper;
import input.InputUtility;
import inventory.Inventory;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import scene.SceneController;

public class Main extends Application {
	

	public static GameLogic logic;
	public static GameScreen gameScreen;
	public static StackPane root = new StackPane();
	public static Scene scene = new Scene(root);
	public static Stage stage;

	

	public static void main(String[] args) {
		launch(args);
	}
	
	// @Override
	// public void start(Stage stage) throws Exception {
	// 	// TODO Auto-generated method stub
	// 	try {
	// 		System.out.println("S");
	// 		Parent root = FXMLLoader.load(getClass().getResource("/scene/GameOver.fxml"));

	// 		System.out.println("S");
	// 		Scene startGame = new Scene(root);
	// 		stage.setScene(startGame);
	// 		stage.show();
	// 	}catch(Exception e) {
	// 		e.printStackTrace();
	// 	}
	// }

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
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
