package app.games.platformerobjects;

import app.gameengine.Game;
import app.gameengine.Level;
import app.gameengine.controller.PlatformerMovement;
import app.gameengine.model.physics.PhysicsEngine;
import app.gameengine.model.physics.PhysicsEngineWithGravity;
import app.games.commonobjects.Wall;

public class PlatformerLevel extends Level {
    @Override
    public void wallOffBoundary() {
        for (int i = 0; i < this.getWidth(); i++) {

            this.getStaticObjects().add(new PlatformerWall(i, 0));
            this.getStaticObjects().add(new PlatformerWall(i, this.getHeight() - 1));
        }
        for (int i = 1; i < this.getHeight() - 1; i++) {
            this.getStaticObjects().add(new PlatformerWall(0, i));
            this.getStaticObjects().add(new PlatformerWall(this.getWidth() - 1, i));
        }
    }

    public PlatformerLevel(Game game, int width, int height, String name) {
        super(game,new PhysicsEngineWithGravity(25.0), width, height, name);
        this.gameControls = new PlatformerMovement(game,5.0);
        wallOffBoundary();
    }
    @Override
    public void jumpButtonPressed(){
        if (getPlayer().isOnGround() == true){
            getPlayer().getVelocity().setY(-14.0);
            getPlayer().setOnGround(false);
        } else if (getPlayer().isOnGround() == false) {}
    }
}
