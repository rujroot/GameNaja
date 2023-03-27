package logic;

import drawing.GameScreen;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		// Add root and scene + set Title game
		StackPane root = new StackPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Game Naja eiei");
		
		//Create Gamelogic Class
		GameLogic logic = new GameLogic();
		GameScreen gameScreen = new GameScreen(640,480);
		root.getChildren().add(gameScreen);
		gameScreen.requestFocus();
		
		//Show windows
		stage.show();
		
		//this function run every sec
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				gameScreen.paintComponent();
				logic.logicUpdate();
				RenderableHolder.getInstance().update();
				InputUtility.updateInputState();
			}
		};
		animation.start();
		
		
		
		
	}

}
