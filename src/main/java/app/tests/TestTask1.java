package app.tests;

import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.gameobjects.Player;
import app.gameengine.model.physics.Hitbox;
import app.gameengine.model.physics.PhysicsEngine;
import app.gameengine.model.physics.Vector2D;
import app.games.commonobjects.Wall;
import javafx.geometry.Orientation;
import javafx.util.Pair;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TestTask1 {

    static final double EPSILON = 0.0001;

    // TODO: write your tests here

    public void comparePlayers(Player player1, Player player2){
        assertEquals(player1.getLocation().getX(), player2.getLocation().getX(),EPSILON);
        assertEquals(player1.getLocation().getY(), player2.getLocation().getY(),EPSILON);
        assertEquals(player1.getVelocity().getX(), player2.getVelocity().getX(),EPSILON);
        assertEquals(player1.getVelocity().getY(), player2.getVelocity().getY(),EPSILON);
        assertEquals(player1.getOrientation().getX(), player2.getOrientation().getX(),EPSILON);
        assertEquals(player1.getOrientation().getY(), player2.getOrientation().getY(),EPSILON);
        assertEquals(player1.getHP(), player2.getHP(),EPSILON);
        assertEquals(player1.getMaxHP(), player2.getMaxHP(),EPSILON);
    }

    @Test
    public void testDynamicGameObject(){
        int maxHP = 30;
        Vector2D orientation = new Vector2D(0.0,1.0);



        Player p1 = new Player(new Vector2D(2.0,2.0),30);
        p1.getVelocity().setX(1.0);
        p1.getVelocity().setY(1.0);

        assertEquals(2.0,p1.getLocation().getX(),EPSILON);
        assertEquals(2.0,p1.getLocation().getY(),EPSILON);
        assertEquals(0.0,p1.getOrientation().getX(),EPSILON);
        assertEquals(1.0,p1.getOrientation().getY(),EPSILON);
        assertEquals(maxHP,p1.getMaxHP(),EPSILON);
        assertEquals(maxHP,p1.getHP(),EPSILON);
        assertEquals(1.0,p1.getVelocity().getX(),EPSILON);
        assertEquals(1.0,p1.getVelocity().getY(),EPSILON);

        Player p2 = new Player(new Vector2D(4.0,4.0),50);
        maxHP = 50;
        p2.setHP(30);
        p2.getVelocity().setX(2.0);
        p2.getVelocity().setY(2.0);
        assertEquals(4.0,p2.getLocation().getX(),EPSILON);
        assertEquals(4.0,p2.getLocation().getY(),EPSILON);
        assertEquals(0.0,p2.getOrientation().getX(),EPSILON);
        assertEquals(1.0,p2.getOrientation().getY(),EPSILON);
        assertEquals(maxHP,p2.getMaxHP(),EPSILON);
        assertEquals(30,p2.getHP(),EPSILON);
        assertEquals(2.0,p2.getVelocity().getX(),EPSILON);
        assertEquals(2.0,p2.getVelocity().getY(),EPSILON);


        Player p3 = new Player(new Vector2D(5.55,0.95),0);
        maxHP = 0;
        p3.getVelocity().setX(0.43);
        p3.getVelocity().setY(0.57);
        p3.setHP(-10);
        assertEquals(5.55,p3.getLocation().getX(),EPSILON);
        assertEquals(0.95,p3.getLocation().getY(),EPSILON);
        assertEquals(0.0,p3.getOrientation().getX(),EPSILON);
        assertEquals(1.0,p3.getOrientation().getY(),EPSILON);
        assertEquals(maxHP,p3.getMaxHP(),EPSILON);
        assertEquals(-10,p3.getHP(),EPSILON);
        assertEquals(0.43,p3.getVelocity().getX(),EPSILON);
        assertEquals(0.57,p3.getVelocity().getY(),EPSILON);


        Player p4 = new Player(new Vector2D(-5.55,-0.95),-10);
        maxHP = -10;
        p4.getVelocity().setX(0.43);
        p4.getVelocity().setY(-0.57);
        p4.getOrientation().setX(1.0);
        p4.getOrientation().setY(2.0);
        assertEquals(-5.55,p4.getLocation().getX(),EPSILON);
        assertEquals(-0.95,p4.getLocation().getY(),EPSILON);
        assertEquals(1.0,p4.getOrientation().getX(),EPSILON);
        assertEquals(2.0,p4.getOrientation().getY(),EPSILON);
        assertEquals(maxHP,p4.getMaxHP(),EPSILON);
        assertEquals(maxHP,p4.getHP(),EPSILON);
        assertEquals(0.43,p4.getVelocity().getX(),EPSILON);
        assertEquals(-0.57,p4.getVelocity().getY(),EPSILON);


        Player p5 = new Player(new Vector2D(50,90),1000);
        maxHP = 1000;
        assertEquals(50,p5.getLocation().getX(),EPSILON);
        assertEquals(90,p5.getLocation().getY(),EPSILON);
        assertEquals(0.0,p5.getOrientation().getX(),EPSILON);
        assertEquals(1.0,p5.getOrientation().getY(),EPSILON);
        assertEquals(maxHP,p5.getMaxHP(),EPSILON);
        assertEquals(maxHP,p5.getHP(),EPSILON);
        assertEquals(0.0,p5.getVelocity().getX(),EPSILON);
        assertEquals(0.0,p5.getVelocity().getY(),EPSILON);




    }
    @Test
    public void testSetHP(){
        Player p1 = new Player(new Vector2D(2.0,2.0),30);
        p1.setHP(25);
        assertEquals(25,p1.getHP(),EPSILON);
        p1.setHP(50);
        assertEquals(p1.getMaxHP(), p1.getHP(), EPSILON);
    }

    @Test
    public void testTakeDamage(){
        Player p1 = new Player(new Vector2D(2.0,2.0),50);
        p1.takeDamage(20);
        assertEquals(30,p1.getHP(),EPSILON);

        p1.takeDamage(10);
        assertEquals(20,p1.getHP(),EPSILON);

        Player p2 = new Player(new Vector2D(3.0,3.0),50);
        p2.takeDamage(0);
        assertEquals(50,p2.getHP(),EPSILON);
        p2.takeDamage(70);
        assertEquals(-20,p2.getHP(),EPSILON);

        Player p3 = new Player(new Vector2D(2.55,1.55),80);
        p3.takeDamage(-10);
        assertEquals(80,p3.getHP(),EPSILON);
    }

    @Test
    public void testupdateObject(){
        PhysicsEngine obj = new PhysicsEngine();
        Player p1 = new Player(new Vector2D(2.0,2.0),50);
        p1.getVelocity().setX(1.0);
        p1.getVelocity().setY(1.0);
        obj.updateObject(p1,1.0);
        double expectX = 2.0 + (1.0 * 1.0);
        double expectY = 2.0 + (1.0 * 1.0);
        assertEquals(expectX,p1.getLocation().getX(),EPSILON);
        assertEquals(expectY,p1.getLocation().getY(),EPSILON);

        Player p2 = new Player(new Vector2D(-0.5,-1.5),50);
        p2.getVelocity().setX(0.0);
        p2.getVelocity().setY(0.0);
        obj.updateObject(p2,0.4);
        expectX = -0.5 + (0.0 * 0.4);
        expectY = -1.5 + (0.0 * 0.4);
        assertEquals(expectX,p2.getLocation().getX(),EPSILON);
        assertEquals(expectY,p2.getLocation().getY(),EPSILON);

        Player p3 = new Player(new Vector2D(0.57,1.57),50);
        p3.getVelocity().setX(1.89);
        p3.getVelocity().setY(2.11);
        obj.updateObject(p3,0.55);
        expectX = 0.57 + (1.89 * 0.55);
        expectY = 1.57 + (2.11 * 0.55);
        assertEquals(expectX,p3.getLocation().getX(),EPSILON);
        assertEquals(expectY,p3.getLocation().getY(),EPSILON);



    }

    @Test
    public void testdetectCollision(){
        PhysicsEngine obj = new PhysicsEngine();
        Hitbox hb1 = new Hitbox(new Vector2D(0,0), new Vector2D(4,4));
        Hitbox hb2 = new Hitbox(new Vector2D(1,1), new Vector2D(4,4));
        boolean result = obj.detectCollision(hb1,hb2);
        assertEquals(true,result);

        hb1.getLocation().setX(0);
        hb1.getLocation().setY(0);
        hb1.getDimensions().setX(4);
        hb1.getDimensions().setY(4);
        hb2.getLocation().setX(20);
        hb2.getLocation().setY(20);
        hb2.getDimensions().setX(4);
        hb2.getDimensions().setY(4);

        result = obj.detectCollision(hb1,hb2);
        assertEquals(false,result);

        hb1.getLocation().setX(0);
        hb1.getLocation().setY(0);
        hb1.getDimensions().setX(5);
        hb1.getDimensions().setY(5);
        hb2.getLocation().setX(20);
        hb2.getLocation().setY(20);
        hb2.getDimensions().setX(5);
        hb2.getDimensions().setY(5);

        result = obj.detectCollision(hb1,hb2);
        assertEquals(false,result);

        hb1.getLocation().setX(2);
        hb1.getLocation().setY(2);
        hb1.getDimensions().setX(4);
        hb1.getDimensions().setY(4);
        hb2.getLocation().setX(1);
        hb2.getLocation().setY(1);
        hb2.getDimensions().setX(3);
        hb2.getDimensions().setY(2);

        result = obj.detectCollision(hb1,hb2);
        assertEquals(true,result);

        hb1.getLocation().setX(2);
        hb1.getLocation().setY(2);
        hb1.getDimensions().setX(4);
        hb1.getDimensions().setY(4);
        hb2.getLocation().setX(2);
        hb2.getLocation().setY(2);
        hb2.getDimensions().setX(4);
        hb2.getDimensions().setY(4);

        result = obj.detectCollision(hb1,hb2);
        assertEquals(true,result);


//        Hitbox hitbox1 = new Hitbox(new Vector2D(0,0), new Vector2D(4,4));
//        Hitbox hitbox2 = new Hitbox(new Vector2D(50,50), new Vector2D(4,4));
//        result = PhysicsEngine.detectCollision(hitbox1,hitbox2);
//        assertEquals(result, false);



    }




    @Test
    public void testWallCollisionsSimple() {
        // we give you the tests for wall collisions. Don't change them
        //
        // However, you should read through these tests to better understand what you should
        // be testing for and how you should be testing
        Player player = new Player(new Vector2D(0, 0), 10);
        Wall w1 = new Wall(1, 0);
        Wall w2 = new Wall(0, 1);
        Wall w3 = new Wall(-1, 0);
        Wall w4 = new Wall(0, -1);

        // Move right
        player.getLocation().setX(0.5);
        player.getLocation().setY(0);
        w1.collideWithDynamicObject(player);
        assertEquals(0.0, player.getLocation().getX(), EPSILON);
        assertEquals(0.0, player.getLocation().getY(), EPSILON);

        // Move down
        player.getLocation().setX(0);
        player.getLocation().setY(0.5);
        w2.collideWithDynamicObject(player);
        assertEquals(0.0, player.getLocation().getX(), EPSILON);
        assertEquals(0.0, player.getLocation().getY(), EPSILON);

        // Move left
        player.getLocation().setX(-0.5);
        player.getLocation().setY(0);
        w3.collideWithDynamicObject(player);
        assertEquals(0.0, player.getLocation().getX(), EPSILON);
        assertEquals(0.0, player.getLocation().getY(), EPSILON);

        // Move up
        player.getLocation().setX(0);
        player.getLocation().setY(-0.5);
        w4.collideWithDynamicObject(player);
        assertEquals(0.0, player.getLocation().getX(), EPSILON);
        assertEquals(0.0, player.getLocation().getY(), EPSILON);
    }

    @Test
    public void testWallCollisionsComplex() {
        Player player = new Player(new Vector2D(0.0, 0.0), 10);
        Wall w1 = new Wall(5, 2);

        player.getLocation().setX(4.5);
        player.getLocation().setY(1.2);
        w1.collideWithDynamicObject(player);
        assertEquals(4.5, player.getLocation().getX(), EPSILON);
        assertEquals(1.0, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.0);
        player.getLocation().setY(1.2);
        w1.collideWithDynamicObject(player);
        assertEquals(5.0, player.getLocation().getX(), EPSILON);
        assertEquals(1.0, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.5);
        player.getLocation().setY(1.2);
        w1.collideWithDynamicObject(player);
        assertEquals(5.5, player.getLocation().getX(), EPSILON);
        assertEquals(1.0, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.9);
        player.getLocation().setY(1.2);
        w1.collideWithDynamicObject(player);
        assertEquals(6.0, player.getLocation().getX(), EPSILON);
        assertEquals(1.2, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.9);
        player.getLocation().setY(1.5);
        w1.collideWithDynamicObject(player);
        assertEquals(6.0, player.getLocation().getX(), EPSILON);
        assertEquals(1.5, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.9);
        player.getLocation().setY(2.5);
        w1.collideWithDynamicObject(player);
        assertEquals(6.0, player.getLocation().getX(), EPSILON);
        assertEquals(2.5, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.9);
        player.getLocation().setY(2.8);
        w1.collideWithDynamicObject(player);
        assertEquals(6.0, player.getLocation().getX(), EPSILON);
        assertEquals(2.8, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.2);
        player.getLocation().setY(2.8);
        w1.collideWithDynamicObject(player);
        assertEquals(5.2, player.getLocation().getX(), EPSILON);
        assertEquals(3.0, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(4.2);
        player.getLocation().setY(2.7);
        w1.collideWithDynamicObject(player);
        assertEquals(4.0, player.getLocation().getX(), EPSILON);
        assertEquals(2.7, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(4.2);
        player.getLocation().setY(2.0);
        w1.collideWithDynamicObject(player);
        assertEquals(4.0, player.getLocation().getX(), EPSILON);
        assertEquals(2.0, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(4.2);
        player.getLocation().setY(1.5);
        w1.collideWithDynamicObject(player);
        assertEquals(4.0, player.getLocation().getX(), EPSILON);
        assertEquals(1.5, player.getLocation().getY(), EPSILON);
    }

}
