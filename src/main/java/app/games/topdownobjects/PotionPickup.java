package app.games.topdownobjects;

import app.gameengine.Level;
import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.gameobjects.CollectibleGameObject;
import app.gameengine.model.gameobjects.Player;
import app.gameengine.model.physics.Vector2D;
import app.games.commonobjects.Potion;

public class PotionPickup extends CollectibleGameObject {
    private int heal;
    public PotionPickup (Vector2D location, int heal){
        super(location,"Health Potion");
        this.heal = heal;
        this.spriteSheetFilename = "User Interface/Icons-Essentials.png";
        this.defaultSpriteLocation = new SpriteLocation(3,1);
    }
    @Override
    public void use(Level level){
        Player player = level.getPlayer();
        if(player == null){
            return;
        }
        player.setHP(player.getHP() + this.heal);
        player.removeActiveItem();
    }
}
