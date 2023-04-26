package logic;

import Data.DataEntity;
import Data.Point;
import Dungeon.GenerateDungeon;
import Dungeon.Room;
import animation.AnimationController;
import drawing.GameScreen;
import entity.Player;
import entity.Shopkeeper;
import input.InputUtility;
import inventory.Inventory;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static GameLogic logic;
	public static GameScreen gameScreen;
	public static Inventory inventory;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		// Generate Dungeon first time
		logic = new GameLogic();
		GenerateDungeon dungeon = new GenerateDungeon(10);
		
		// Create Player
		Player player = new Player("Player", 50, 50, new DataEntity(1, 1, 1, 10));
		Room firstRoom = GenerateDungeon.getContainer().get(0).get(0);
		player.setPosition(new Point(firstRoom.getPosition().getX() + 100, firstRoom.getPosition().getY() + 100 ));
		
		logic.addObject(player);

		// Shopkeeper for test
		Shopkeeper shopkeeper = new Shopkeeper("Shopkeeper", 50, 50, new DataEntity(999999, 1, 1, 0));
		shopkeeper.setPosition(new Point(firstRoom.getPosition().getX() + 20, firstRoom.getPosition().getY() + 20 ));
		logic.addObject(shopkeeper);

		// Add root and scene + set Title game
		StackPane root = new StackPane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Game Naja eiei");
		
		//Create Gamelogic Class
		gameScreen = new GameScreen(1400,800);
		root.getChildren().add(gameScreen);
		gameScreen.requestFocus();
		
		//Show windows
		stage.show();

		AnimationController animationController = new AnimationController();

		inventory = new Inventory();
		logic.addObject(inventory);

		//this function run every sec
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				gameScreen.updatePlayer();
				gameScreen.paintLevel();
				gameScreen.paintComponent();
				animationController.run();
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

	public static GameScreen getGameScreen() {
		return gameScreen;
	}
}
