package app.games.platformerobjects;

import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.gameobjects.StaticGameObject;

public class Spike extends StaticGameObject {

    public Spike(int x, int y) {
        super(x, y);
        this.spriteSheetFilename = "User Interface/UiIcons.png";
        this.defaultSpriteLocation = new SpriteLocation(2,10);
    }
    @Override
    public void collideWithDynamicObject(DynamicGameObject player){
        if (player.isPlayer() == true){
            player.destroy();
        }
    }

}
