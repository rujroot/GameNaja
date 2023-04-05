package logic;

import Dungeon.GenerateDungeon;
import Dungeon.Room;
import Math.DataEntity;
import Math.Point;
import drawing.GameScreen;
import entity.Player;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static GameLogic logic;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		// Generate Dungeon first time
		GenerateDungeon dungeon = new GenerateDungeon(10);
		logic = new GameLogic();
		
		// Create Player
		Player player = new Player("Player", 50, 50, new DataEntity(1, 1, 1, 5));
		Room firstRoom = GenerateDungeon.getContainer().get(0).get(0);
		player.setPosition(new Point(firstRoom.getPosition().getX() + 10, firstRoom.getPosition().getY() + 10 ));
		logic.addNewObject(player);
		
		// Add root and scene + set Title game
		StackPane root = new StackPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Game Naja eiei");
		
		//Create Gamelogic Class
		GameScreen gameScreen = new GameScreen(1400,800);
		root.getChildren().add(gameScreen);
		gameScreen.requestFocus();
		
		//Show windows
		stage.show();
		
//		Zombie zombie1 = new Zombie("Zombie", 10, 1, 1, 0.5, 0, 0);
//		logic.addNewObject(zombie1);
//		Zombie zombie2 = new Zombie("Zombie", 10, 1, 1, 0.7, 0, 0);
//		logic.addNewObject(zombie2);
//		Zombie zombie3 = new Zombie("Zombie", 10, 1, 1, 0.6, 0, 0);
//		logic.addNewObject(zombie3);

		//this function run every sec
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				gameScreen.updatePlayer();
				gameScreen.paintLevel();
				gameScreen.paintComponent();
				logic.logicUpdate();
				RenderableHolder.getInstance().update();
				InputUtility.updateInputState();
			}
		};
		animation.start();	
	}
	
	public static GameLogic getLogic() {
		return logic;
	}

}
