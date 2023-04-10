package logic;

import Data.DataEntity;
import Data.DataOre;
import Data.Point;
import Dungeon.GenerateDungeon;
import Dungeon.Room;
import drawing.GameScreen;
import entity.Player;
import input.InputUtility;
import item.Stone;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ore.StoneOre;

public class Main extends Application {
	
	public static GameLogic logic;
	public static GameScreen gameScreen;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		// Generate Dungeon first time
		GenerateDungeon dungeon = new GenerateDungeon(10);
		logic = new GameLogic();
		
		// Create Player
		Player player = new Player("Player", 50, 50, new DataEntity(1, 1, 1, 10));
		Room firstRoom = GenerateDungeon.getContainer().get(0).get(0);
		player.setPosition(new Point(firstRoom.getPosition().getX() + 10, firstRoom.getPosition().getY() + 10 ));
		
		logic.addObject(player);

		StoneOre stone = new StoneOre(new Point(70, 70), 
									40, 
									40, 
									new DataOre(1, 1));
		logic.addObject(stone);
		
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

	public static GameScreen getGameScreen() {
		return gameScreen;
	}
}
