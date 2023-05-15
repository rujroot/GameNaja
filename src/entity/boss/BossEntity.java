package entity.boss;

import data.DataEntity;
import entity.Entity;
import logic.Hitbox;

public abstract class BossEntity extends Entity{

    private Hitbox bossHitbox;

    public BossEntity(String name, DataEntity data, Hitbox bHitbox) {
        super(name, 0, 0, data);
        this.bossHitbox = bHitbox;
    }

    public Hitbox getBossHitbox() {
        return bossHitbox;
    }

    public void setBossHitbox(Hitbox bossHitbox) {
        this.bossHitbox = bossHitbox;
    }

    
    
}
