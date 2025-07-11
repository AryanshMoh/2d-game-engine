package app.games.commonobjects;

import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.physics.Vector2D;
import org.junit.Assert;
import org.junit.Test;

public class Potion extends DynamicGameObject {
    private int heal;
    public Potion(Vector2D location, int heal){

        super(location, 10);
        this.spriteSheetFilename = "User Interface/Icons-Essentials.png";
        if (heal >= 0){
            this.defaultSpriteLocation = new SpriteLocation(0,1);
        } else {
            this.defaultSpriteLocation = new SpriteLocation(1,1);
        }
        this.heal = heal;

    }
    @Override
    public void collideWithDynamicObject(DynamicGameObject player){
        if (player.isPlayer() == true){
            int playerHP = player.getHP() + this.heal;
            player.setHP(playerHP);
            this.destroy();
        } else {
            return;
        }

    }



}
