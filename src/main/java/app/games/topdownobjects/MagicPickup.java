package app.games.topdownobjects;

import app.gameengine.Level;
import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.gameobjects.CollectibleGameObject;
import app.gameengine.model.gameobjects.Player;
import app.gameengine.model.physics.Vector2D;

public class MagicPickup extends CollectibleGameObject {
    public MagicPickup(Vector2D location){
        super(location,"Magic");
        this.spriteSheetFilename =  "User Interface/Icons-Essentials.png";
        this.defaultSpriteLocation = new SpriteLocation(1,0);
    }
    @Override
    public void use(Level level){
        Player player = level.getPlayer();
        if(player == null){
            return;
        }
        Projectile magicpickup = new PlayerMagicProjectile(new Vector2D(0,0));
        player.fireProjectile(magicpickup,10,level);
    }
}
