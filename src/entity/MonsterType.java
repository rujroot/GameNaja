package entity;

import java.util.ArrayList;

import data.DataEntity;

public enum MonsterType {
    DEMON, GOBLIN, SKELETON, SLIME, ZOMBIE;

    public static ArrayList<MonsterType> getAllType(){
        ArrayList<MonsterType> types = new ArrayList<>();
        types.add(MonsterType.SLIME);
        types.add(MonsterType.ZOMBIE);
        types.add(MonsterType.SKELETON);
        types.add(MonsterType.GOBLIN);
        types.add(MonsterType.DEMON);
        return types;
    }

    public static Monster getRandomMonster(){
        ArrayList<MonsterType> types = MonsterType.getAllType();

        int chooseType = (int)(Math.random() * types.size());
        MonsterType type = types.get(chooseType);

        Monster monster;
        if(type.equals(MonsterType.DEMON)){
            monster = new Demon("Demon", 0, 0, new DataEntity(20, 5, 5, 10));
        }else if(type.equals(MonsterType.GOBLIN)){
            monster = new Goblin("Goblin", 0, 0, new DataEntity(20, 5, 5, 10));
        }else if(type.equals(MonsterType.SKELETON)){
            monster = new Skeleton("Skeleton", 0, 0, new DataEntity(20, 5, 5, 10));
        }else if(type.equals(MonsterType.SLIME)){
            monster = new Slime("Slime", 0, 0, new DataEntity(20, 5, 5, 10));
        }else{
            monster = new Zombie("Zombie", 0, 0, new DataEntity(20, 5, 5, 10));
        }

        return monster;
		
    }

}
