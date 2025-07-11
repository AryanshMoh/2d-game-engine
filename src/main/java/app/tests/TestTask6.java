package app.tests;

import app.gameengine.Game;
import app.gameengine.Level;
import app.gameengine.model.ai.Pathfinding;
import app.gameengine.model.datastructures.LinkedListNode;
import app.gameengine.model.gameobjects.StaticGameObject;
import app.gameengine.model.physics.Vector2D;
import app.games.commonobjects.Wall;
import app.games.platformerobjects.PlatformerLevel;
import app.games.platformerobjects.PlatformerWall;
import app.games.topdownobjects.SmartEnemy;
import app.games.topdownobjects.TopDownLevel;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestTask6 {
    static final double EPSILON = 0.0001;
    @Test
    public void testfindPathAvoidWalls(){
        Game game = new Game();
        Level level = new TopDownLevel(game,6,6,"TestLevel1");
        level.getStaticObjects().add(new Wall(2,3));
        level.getStaticObjects().add(new Wall(2,2));
        SmartEnemy enemy = new SmartEnemy(new Vector2D(0,0));
        level.getDynamicObjects().add(enemy);
        Vector2D start = new Vector2D(1,1);
        Vector2D end = new Vector2D(3,3);
        LinkedListNode<Vector2D> path = Pathfinding.findPathAvoidWalls(level,start,end);
        int count = 0;
        boolean touchWall = false;
        LinkedListNode<Vector2D> present = path;
        while (present != null){
            Vector2D presentLoc = present.getValue();
            for(StaticGameObject wall : level.getStaticObjects()){
                if(presentLoc.equals(wall.getLocation())){
                    touchWall = true;
                }
            }
            count++;
            present = present.getNext();
        }
        int expectedLength = 5;
        assertEquals("Shortest Path Length", expectedLength, count);
        assertEquals("No wall touch", false, touchWall);
    }
    @Test
    public void testFindPathAvoidWallsNoPath(){
        Game game = new Game();
        Level level = new TopDownLevel(game,6,6,"TestLevel2");
        SmartEnemy enemy = new SmartEnemy(new Vector2D(0,0));
        level.getDynamicObjects().add(enemy);
        level.getStaticObjects().add(new Wall(1, 0));
        level.getStaticObjects().add(new Wall(1, 2));
        level.getStaticObjects().add(new Wall(0, 1));
        level.getStaticObjects().add(new Wall(2, 1));
        Vector2D start = new Vector2D(1, 1);
        Vector2D end = new Vector2D(2, 2);
        LinkedListNode<Vector2D> path = Pathfinding.findPathAvoidWalls(level, start, end);
        assertEquals("No Path", null, path);
    }
    @Test
    public void testFindPathAvoidWallsLengthOne(){
        Game game = new Game();
        Level level = new TopDownLevel(game,6,6,"LengthOne");
        Vector2D start = new Vector2D(3,3);
        SmartEnemy enemy = new SmartEnemy(new Vector2D(0,0));
        level.getDynamicObjects().add(enemy);
        LinkedListNode<Vector2D> path = Pathfinding.findPathAvoidWalls(level, start, start);
        int count = 0;
        while (path != null) {
            count++;
            path = path.getNext();
        }
        assertEquals("Path Length one", 1, count);
    }
    @Test
    public void testFindPathAvoidWallsLongerThanEightSteps() {
        Game game = new Game();
        Level level = new TopDownLevel(game, 10, 10, "TestLevel5");
        Vector2D start = new Vector2D(1, 1);
        Vector2D end = new Vector2D(6, 4);
        SmartEnemy enemy = new SmartEnemy(new Vector2D(0,0));
        level.getDynamicObjects().add(enemy);
        LinkedListNode<Vector2D> path = Pathfinding.findPathAvoidWalls(level, start, end);
        int count = 0;
        while (path != null) {
            count++;
            path = path.getNext();
        }
        assertTrue("Path should include more than 8 tiles", count > 8);
    }
    @Test
    public void testValidPathWithMultipleTurnsAndDirections() {
        Game game = new Game();
        Level level = new TopDownLevel(game, 7, 7, "ValidTurns");
        SmartEnemy enemy = new SmartEnemy(new Vector2D(0,0));
        level.getDynamicObjects().add(enemy);
        level.getStaticObjects().add(new Wall(2, 2));
        level.getStaticObjects().add(new Wall(2, 3));
        level.getStaticObjects().add(new Wall(2, 4));
        Vector2D start = new Vector2D(1, 3);
        Vector2D end = new Vector2D(4, 3);
        LinkedListNode<Vector2D> path = Pathfinding.findPathAvoidWalls(level, start, end);
        int count = 0;
        while (path != null && path.getNext() != null) {
            Vector2D a = path.getValue();
            Vector2D b = path.getNext().getValue();
            double changex = Math.abs(a.getX() - b.getX());
            double changey = Math.abs(a.getY() - b.getY());
            assertTrue("Path must move one tile in one direction only", changex + changey == 1);
            count++;
            path = path.getNext();
        }
        assertTrue("Path must take multiple valid steps", count >= 5);
    }
    @Test
    public void testValidPathUsesAllDirections() {
        Game game = new Game();
        Level level = new TopDownLevel(game, 6, 6, "AllDirections");
        SmartEnemy enemy = new SmartEnemy(new Vector2D(0,0));
        level.getDynamicObjects().add(enemy);
        level.getStaticObjects().add(new Wall(2, 1));
        level.getStaticObjects().add(new Wall(2, 3));
        level.getStaticObjects().add(new Wall(1, 2));
        level.getStaticObjects().add(new Wall(3, 2));
        Vector2D start = new Vector2D(0, 2);
        Vector2D end = new Vector2D(4, 2);
        LinkedListNode<Vector2D> path = Pathfinding.findPathAvoidWalls(level, start, end);
        int count = 0;
        while (path != null && path.getNext() != null) {
            Vector2D current = path.getValue();
            Vector2D next = path.getNext().getValue();
            double changex = Math.abs(current.getX() - next.getX());
            double changey = Math.abs(current.getY() - next.getY());
            assertTrue("Each step must move exactly 1 tile in a single direction", changex + changey == 1);
            count++;
            path = path.getNext();
        }
        assertTrue("Should require at least 5 valid steps", count <= 5);
    }
    @Test
    public void testFindPathAvoidWallsWithDecimalPositions() {
        Game game = new Game();
        Level level = new TopDownLevel(game, 6, 6, "TestLevel7");
        SmartEnemy enemy = new SmartEnemy(new Vector2D(0,0));
        level.getDynamicObjects().add(enemy);
        level.getStaticObjects().add(new Wall(2, 2));
        level.getStaticObjects().add(new Wall(2, 3));
        Vector2D start = new Vector2D(1.7, 1.9);
        Vector2D end = new Vector2D(4.2, 3.6);
        LinkedListNode<Vector2D> path = Pathfinding.findPathAvoidWalls(level, start, end);
        while (path != null && path.getNext() != null) {
            Vector2D current = path.getValue();
            Vector2D next = path.getNext().getValue();
            double changex = Math.abs(current.getX() - next.getX());
            double changey = Math.abs(current.getY() - next.getY());
            assertTrue("Each step 1 direction", changex + changey == 1);
            assertEquals("X must be whole number", current.getX(), Math.floor(current.getX()), EPSILON);
            assertEquals("Y must be whole number", current.getY(), Math.floor(current.getY()), EPSILON);
            path = path.getNext();
        }
    }
}
