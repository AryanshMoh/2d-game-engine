package app.tests;

import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.gameobjects.Player;
import app.gameengine.model.physics.Vector2D;
import app.games.platformerobjects.PlatformerWall;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestTask3 {
    static final double EPSILON = 0.0001;

    @Test
    public void testcollideWithDynamicObject(){
        Player player = new Player(new Vector2D(0.5,0.0),10);
        PlatformerWall platformerWall1 = new PlatformerWall(1,0);
        PlatformerWall platformerWall2 = new PlatformerWall(0,1);
        PlatformerWall platformerWall3 = new PlatformerWall(-1,0);
        PlatformerWall platformerWall4 = new PlatformerWall(0,-1);
        PlatformerWall platformerWall5 = new PlatformerWall(5,2);

        player.getVelocity().setY(0.0);
        platformerWall1.collideWithDynamicObject(player);
        assertEquals(0.0, player.getVelocity().getY(), EPSILON);
        assertTrue("Player is not on the ground",player.isOnGround()==false);


        player = new Player(new Vector2D(0.5, 1.5), 10);
        player.getVelocity().setY(-1.0);
        platformerWall2.collideWithDynamicObject(player);
        assertEquals(0.0, player.getVelocity().getY(), EPSILON);
        assertTrue("Player is not on the ground",player.isOnGround()==false);

        player = new Player(new Vector2D(-0.5, 0), 10);
        player.getVelocity().setY(2.0);
        platformerWall3.collideWithDynamicObject(player);
        assertEquals(2.0, player.getVelocity().getY(), EPSILON);
        assertTrue("Player is not on the ground",player.isOnGround() == false);


        player = new Player(new Vector2D(0, -0.5), 10);
        player.getVelocity().setY(-0.5);
        platformerWall4.collideWithDynamicObject(player);
        assertEquals(0.0, player.getVelocity().getY(), EPSILON);
        assertTrue("Player is not on the ground",player.isOnGround()==false);


        player = new Player(new Vector2D(4.5, 1.2), 10);
        player.getVelocity().setY(1.2);
        platformerWall5.collideWithDynamicObject(player);
        assertEquals(0.0, player.getVelocity().getY(), EPSILON);
        assertTrue("Player is on the ground",player.isOnGround());

    }
}
