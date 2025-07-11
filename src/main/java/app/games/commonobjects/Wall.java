package app.games.commonobjects;

import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.gameobjects.StaticGameObject;
import app.gameengine.model.physics.Vector2D;

/**
 * A wall object the player can collide with, serves as a building block
 * for your levels.
 */
public class Wall extends StaticGameObject {

    public Wall(int x, int y) {
        super(x, y);
        this.spriteSheetFilename = "Ground/Cliff.png";
        this.defaultSpriteLocation = new SpriteLocation(3, 0);
    }
    public void collideWithDynamicObject(DynamicGameObject player){
        Vector2D wallLocation = this.getLocation();
        Vector2D wallDimension = this.getDimensions();
        Vector2D playerLocation = player.getLocation();
        Vector2D playerDimension = player.getDimensions();
        double XMove = 0.0;
        double YMove = 0.0;
        double collideXRightWall = (wallLocation.getX() + wallDimension.getX()) - playerLocation.getX();
        double collideXLeftWall = (playerLocation.getX() + playerDimension.getX()) - wallLocation.getX();
        double collideYTopWall = (playerLocation.getY() + playerDimension.getY()) - wallLocation.getY();
        double collideYBottomWall = (wallLocation.getY() + wallDimension.getY()) - playerLocation.getY();

        if(collideXRightWall < collideXLeftWall){
            XMove = collideXRightWall;
        } else {
            XMove = collideXLeftWall;
        }
        if(collideYBottomWall < collideYTopWall){
            YMove = collideYBottomWall;
        } else{
            YMove = collideYTopWall;
        }
        if(XMove < YMove){
            if(XMove == collideXRightWall){
                playerLocation.setX(playerLocation.getX() + XMove); //Move Right
            } else {
                playerLocation.setX(playerLocation.getX() - XMove); //Move Left
            }
        }
        if(YMove < XMove){
            if(YMove == collideYBottomWall){
                playerLocation.setY(playerLocation.getY() + YMove); //Move Down
            } else {
                playerLocation.setY(playerLocation.getY() - YMove); //Move Up
            }
        }


    }


}
