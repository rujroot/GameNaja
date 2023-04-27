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

public class Main extends Application {
	
	public static GameLogic logic;
	public static GameScreen gameScreen;
	public static StackPane root = new StackPane();
	public static Scene scene = new Scene(root);
	

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println("S");
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("./scene/GameOver.fxml"));
			System.out.println("S");
			Scene startGame = new Scene(root);
			stage.setScene(startGame);
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

//	@Override
//	public void start(Stage stage) throws Exception {
//		
//		// Generate Dungeon first time
//		logic = new GameLogic();
//		GenerateDungeon dungeon = new GenerateDungeon(10);
//
//		// Create Player
//		Player player = new Player("Player", 50, 50, new DataEntity(1, 1, 1, 10));
//
//		// Add root and scene + set Title game
//		//StackPane root = new StackPane();
//		//Scene scene = new Scene(root);
//		stage.setScene(scene);
//		stage.setTitle("Game Naja eiei");
//		
//		//Create Gamelogic Class
//		gameScreen = new GameScreen(1400,800);
//		root.getChildren().add(gameScreen);
//		gameScreen.requestFocus();
//
//		
//		Room firstRoom = GenerateDungeon.getContainer().get(0).get(0);
//		// Shopkeeper for test
//		Shopkeeper shopkeeper = new Shopkeeper("Shopkeeper", 50, 50, new DataEntity(999999, 1, 1, 0));
//		shopkeeper.setPosition(new Point(firstRoom.getPosition().getX() + 20, firstRoom.getPosition().getY() + 20 ));
//		logic.addObject(shopkeeper);
//
//		// player initing
//		player.setPosition(new Point(firstRoom.getPosition().getX() + 100, firstRoom.getPosition().getY() + 100 ));
//		player.initInventory();
//
//		logic.addObject(player);
//
//		//Show windows
//		stage.show();
//
//		AnimationController animationController = new AnimationController();
//
//		//this function run every sec
//		AnimationTimer animation = new AnimationTimer() {
//			public void handle(long now) {
//				gameScreen.updatePlayer();
//				gameScreen.paintLevel();
//				gameScreen.paintComponent();
//				animationController.run();
//				logic.logicUpdate();
//				RenderableHolder.getInstance().update();
//				InputUtility.updateInputState();
//			}
//		};
//		animation.start();	
//	}
	
	public static GameLogic getLogic() {
		return logic;
	}

	public static GameScreen getGameScreen() {
		return gameScreen;
	}


	public static Scene getScene() {
		return scene;
	}

	public static void setScene(Scene scene) {
		Main.scene = scene;
	}
}
