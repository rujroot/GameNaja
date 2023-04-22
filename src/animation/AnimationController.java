package animation;

import java.util.ArrayList;

import logic.GameLogic;

public class AnimationController {
    
    public static ArrayList<AnimationObject> animations = new ArrayList<>();

    public void run(){
        try {
            for(int i = animations.size() - 1; i >= 0; --i){
                AnimationObject animation = animations.get(i);
                if(animation.getCurrTime() > animation.getEndTime()) {
                    animation.setDestroyed(true);
                    GameLogic.removeObj(animation);
                    animations.remove(animation);
                    continue;
                }
                animation.nextAnim();
                
            }
		} catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
		}
    }

}