package scene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToStartGameScene(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/scene/Start.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToGameOverScene(ActionEvent event) throws IOException {
		root = (Parent) FXMLLoader.load(getClass().getResource("/scene/GameOver.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToBodyGameScene() {
		
	}
}
