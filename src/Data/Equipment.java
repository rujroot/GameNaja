package data;

import java.lang.reflect.Array;
import java.util.ArrayList;

import equipment.BaseWeapon;

public class Equipment {
    public static ArrayList<BaseWeapon> equipment;

    public void add(BaseWeapon weapon){
        equipment.add(weapon);
    }

    public static ArrayList<BaseWeapon> getEquipment() {
        return equipment;
    }

}
 