package animation;

import java.util.ArrayList;

import drawing.GameScreen;
import javafx.scene.canvas.GraphicsContext;

public class AnimationController extends Thread {
    
    public static ArrayList<AnimationObject> animations = new ArrayList<>();

    public void run(){
        GraphicsContext gc = GameScreen.gc;
        try {
			while (true) {
				Thread.sleep(10);
				for(int i = animations.size() - 1; i >= 0; --i){
                    AnimationObject animation = animations.get(i);
                    animation.play(gc);
                    if(animation.getCurrTime() > animation.getEndTime()) animations.remove(animation);
                    else animation.nextAnim();
                    
                }
			}
		} catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
		}
    }

}
