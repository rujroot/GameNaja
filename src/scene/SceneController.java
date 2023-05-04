package scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import drawing.GameScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.Main;
import logic.RenderableHolder;
import animation.AnimationController;
import data.DataEntity;
import data.Point;
import dungeon.GenerateDungeon;
import dungeon.Room;
import entity.Player;
import entity.Shopkeeper;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SceneController{
	private Stage stage;
	private Scene scene;
	private Parent root;
	public static GameLogic logic;
	public static GameScreen gameScreen;
	public ImageView myImageView;
	public Image dungeon = new Image(getClass().getResourceAsStream("Dungeon.jpg"));
	public Image gameOver = new Image(getClass().getResourceAsStream("GameOverImage.jpg"));
	private File directory;
	private File[] files;
	
	private ArrayList<File> songs;
	private int songNumber;
	
	private Media media;
	private MediaPlayer mediaPlayer; 
	
	
	public void switchToStartGameScene(ActionEvent event) throws IOException {
		
		root = (Parent) FXMLLoader.load(getClass().getResource("/scene/Start.fxml"));
		stage = Main.stage;
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Game Naja eiei");
		myImageView.setImage(dungeon);
		stage.show();
	}
	
	public void switchToGameOverScene(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/scene/GameOver.fxml"));
		stage = Main.stage;
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Game Naja eiei");
		stage.show();
	}
	
	public void switchToGameOverScene2() throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/scene/GameOver.fxml"));
		stage = Main.stage;
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Game Naja eiei");
		myImageView.setImage(gameOver);
		stage.show();
	}
	
	
	public void switchToBodyGameScene() throws CloneNotSupportedException {

		Player player = new Player("Player", 50, 50, new DataEntity(25, 10000, 10000, 10));
		logic = new GameLogic();
		GenerateDungeon dungeon = new GenerateDungeon(10);
		
		stage = Main.stage;
		gameScreen = new GameScreen(1400,800);
		StackPane root = new StackPane();
		root.getChildren().add(gameScreen);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Game Naja eiei");
		gameScreen.requestFocus();

		Room firstRoom = GenerateDungeon.getContainer().get(0).get(0);
		Shopkeeper shopkeeper = new Shopkeeper("Shopkeeper", 50, 50, new DataEntity(999999, 1, 1, 0));
		shopkeeper.setPosition(new Point(firstRoom.getPosition().getX() + 20, firstRoom.getPosition().getY() + 20 ));
		logic.addObject(shopkeeper);
		player.setPosition(new Point(firstRoom.getPosition().getX() + 100, firstRoom.getPosition().getY() + 100 ));
		player.initInventory();
		
		logic.addObject(player);
		
		//stage.show();
		AnimationController animationController = new AnimationController();
		
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				try {
					gameScreen.updatePlayer();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gameScreen.paintLevel();
				gameScreen.paintComponent();
				animationController.run();
				logic.logicUpdate();
				RenderableHolder.getInstance().update();
				InputUtility.updateInputState();
			}
		};
		RenderableHolder.sound.setVolume(0.1);// 0.0 to 1.0 (min to man volume)
		RenderableHolder.sound.play();
		animation.start();	
	}


	public static GameLogic getLogic() {
		return logic;
	}

	public static GameScreen getGameScreen() {
		return gameScreen;
	}

	

}

//@Override
//public void start(Stage stage) throws Exception {
//	
//	// Generate Dungeon first time
//	logic = new GameLogic();
//	GenerateDungeon dungeon = new GenerateDungeon(10);
//
//	// Create Player
//	Player player = new Player("Player", 50, 50, new DataEntity(1, 1, 1, 10));
//
//	// Add root and scene + set Title game
//	StackPane root = new StackPane();
//	Scene scene = new Scene(root);
//	stage.setScene(scene);
//	stage.setTitle("Game Naja eiei");
//	
//	//Create Gamelogic Class
//	gameScreen = new GameScreen(1400,800);
//	root.getChildren().add(gameScreen);
//	gameScreen.requestFocus();
//
//	
//	Room firstRoom = GenerateDungeon.getContainer().get(0).get(0);
//	// Shopkeeper for test
//	Shopkeeper shopkeeper = new Shopkeeper("Shopkeeper", 50, 50, new DataEntity(999999, 1, 1, 0));
//	shopkeeper.setPosition(new Point(firstRoom.getPosition().getX() + 20, firstRoom.getPosition().getY() + 20 ));
//	logic.addObject(shopkeeper);
//
//	// player initing
//	player.setPosition(new Point(firstRoom.getPosition().getX() + 100, firstRoom.getPosition().getY() + 100 ));
//	player.initInventory();
//
//	logic.addObject(player);
//
//	//Show windows
//	stage.show();
//
//	AnimationController animationController = new AnimationController();
//
//	//this function run every sec
//	AnimationTimer animation = new AnimationTimer() {
//		public void handle(long now) {
//			gameScreen.updatePlayer();
//			gameScreen.paintLevel();
//			gameScreen.paintComponent();
//			animationController.run();
//			logic.logicUpdate();
//			RenderableHolder.getInstance().update();
//			InputUtility.updateInputState();
//		}
//	};
//	animation.start();	
//}
