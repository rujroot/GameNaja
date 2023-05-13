package entity;

import java.util.ArrayList;

public enum MonsterType {
    DEMON, GOBLIN, SKELETON, SLIME, ZOMBIE;

    public static ArrayList<MonsterType> getAllType(){
        ArrayList<MonsterType> types = new ArrayList<>();
        types.add(MonsterType.DEMON);
        types.add(MonsterType.GOBLIN);
        types.add(MonsterType.SKELETON);
        types.add(MonsterType.SLIME);
        types.add(MonsterType.ZOMBIE);
        return types;
    }

}
