package app.games.platformerobjects;

import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.physics.Vector2D;
import app.games.commonobjects.Wall;

public class PlatformerWall extends Wall {

    public PlatformerWall(int x, int y) {
        super(x, y);
        this.spriteSheetFilename = "Ground/Cliff.png";
        this.defaultSpriteLocation = new SpriteLocation(4,0);
    }
    @Override
    public void collideWithDynamicObject(DynamicGameObject player){
        super.collideWithDynamicObject(player);
        Vector2D PlatformerWallLocation = this.getLocation();
        Vector2D PlatformerWallDimension = this.getDimensions();
        Vector2D playerLocation = player.getLocation();
        Vector2D playerDimension = player.getDimensions();
        if ((playerLocation.getX() < (PlatformerWallLocation.getX() + PlatformerWallDimension.getX())) && (PlatformerWallLocation.getX() < playerLocation.getX() + playerDimension.getX())) {
            player.getVelocity().setY(0.0);
        } if ((playerLocation.getY() + playerDimension.getY()) <= PlatformerWallLocation.getY()){
            player.setOnGround(true);
//            player.getVelocity().setY(0.0);
        }
        else if ((playerLocation.getY() >= (PlatformerWallLocation.getY() + PlatformerWallDimension.getY()))) {
            player.setOnGround(false);
        }



        }
    }



