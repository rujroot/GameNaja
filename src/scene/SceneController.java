package scene;

import java.io.IOException;

import drawing.GameScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.Hitbox;
import logic.Main;
import logic.RenderableHolder;
import animation.AnimationController;
import data.DataEntity;
import data.Point;
import dungeon.GenerateDungeon;
import dungeon.Ladder;
import dungeon.Room;
import entity.Npc;
import entity.Player;
import entity.Shopkeeper;
import entity.boss.FrostGuardian;
import entity.boss.PheuFire;
import entity.miniBoss.DarkSpirit;
import entity.miniBoss.GiantGoblin;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.StackPane;

public class SceneController{

	public static GameLogic logic;
	public static GameScreen gameScreen;
	public static GenerateDungeon dungeon; 
	public static boolean gameStop = false;

	private ImageView myImageView;
	private Image dungeonImage = new Image(getClass().getResourceAsStream("Dungeon.jpg"));
	private Image gameOver = new Image(getClass().getResourceAsStream("GameOverImage.jpg"));
	
	
	public void switchToStartGameScene(ActionEvent event) throws IOException {
		
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("/scene/Start.fxml"));
		Scene scene = new Scene(root);
		Stage stage = Main.stage;

		stage.setScene(scene);
		stage.setTitle("Game Naja eiei");
		myImageView.setImage(dungeonImage);
		stage.show();
	}
	
	public void switchToGameOverScene() throws IOException {

		gameStop = true;
		
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("/scene/GameOver.fxml"));
		Scene scene = new Scene(root);
		Stage stage = Main.stage;

		stage.setScene(scene);
		stage.setTitle("Game Naja eiei");
		stage.show();

	}

	public void restartGame() throws CloneNotSupportedException{
		dungeon = new GenerateDungeon(1);

		Room firstRoom = GenerateDungeon.getContainer().get(0).get(0);
		Shopkeeper shopkeeper = new Shopkeeper("Shopkeeper", 50, 50, new DataEntity(999999, 1, 1, 0));
		shopkeeper.setPosition(new Point(firstRoom.getPosition().getX() + 20, firstRoom.getPosition().getY() + 20 ));
		logic.addObject(shopkeeper);

		Player player = new Player("Player", 50, 50, new DataEntity(25, 10000, 10000, 10));
		player.setPosition(new Point(firstRoom.getPosition().getX() + 100, firstRoom.getPosition().getY() + 100 ));
		player.initInventory();
		logic.addObject(player);

		Stage stage = Main.stage;
		StackPane root = new StackPane();
		root.getChildren().add(gameScreen);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Game Naja eiei");
		gameScreen.requestFocus();

		InputUtility.reset();
		gameStop = false;

	}
	
	public void switchToBodyGameScene() throws CloneNotSupportedException {

		Player player = new Player("Player", 50, 50, new DataEntity(25, 10000, 10000, 10));

		logic = new GameLogic();
		dungeon = new GenerateDungeon(1);

		Stage stage = Main.stage;
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
		
		// Npc npc = new Npc("NPC", 10, 10, new DataEntity(100, 1, 1, 12));
		// npc.setPosition(new Point(firstRoom.getPosition().getX() + 200, firstRoom.getPosition().getY() + 200 ));
		// logic.addObject(npc);
		
		player.setPosition(new Point(firstRoom.getPosition().getX() + 100, firstRoom.getPosition().getY() + 100 ));
		player.initInventory();
		logic.addObject(player);

		// PheuFire demonslime = new PheuFire("PheuFire", new DataEntity(100, 1, 1, 12));
		// demonslime.setPosition(new Point(firstRoom.getPosition().getX() + 200, firstRoom.getPosition().getY() + 200 ));
		// logic.addObject(demonslime);

		// GiantGoblin goblin = new GiantGoblin("GiantGoblin", 0, 0, new DataEntity(100, 1, 1, 12));
		// goblin.setPosition(new Point(firstRoom.getPosition().getX() + 300, firstRoom.getPosition().getY() + 300 ));
		// logic.addObject(goblin);

		// DarkSpirit darkSpirit = new DarkSpirit("DarkSpirit", 0, 0, new DataEntity(100, 1, 1, 12));
		// darkSpirit.setPosition(new Point(firstRoom.getPosition().getX() + 200, firstRoom.getPosition().getY() + 200 ));
		// logic.addObject(darkSpirit);

		// FrostGuardain frostGuardain = new FrostGuardain("FrostGuardain", new DataEntity(100, 1, 1, 12));
		// frostGuardain.setPosition(new Point(firstRoom.getPosition().getX() + 300, firstRoom.getPosition().getY() + 300 ));
		// logic.addObject(frostGuardain);

		// Ladder ladder = new Ladder(null);
		// ladder.setPosition(new Point(firstRoom.getPosition().getX() + 300, firstRoom.getPosition().getY() + 300 ));
		// logic.addObject(ladder);

		AnimationController animationController = new AnimationController();
		
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				if(SceneController.gameStop) return;

				try {
					gameScreen.updatePlayer();
				} catch (CloneNotSupportedException e) {
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