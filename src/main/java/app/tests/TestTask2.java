package app.tests;

import app.gameengine.model.ai.Pathfinding;
import app.gameengine.model.datastructures.LinkedListNode;
import app.gameengine.model.physics.Vector2D;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestTask2 {
    static final double EPSILON = 0.0001;

    @Test
    public void testfindPath() {
        Vector2D start = new Vector2D(0.0, 0.0);
        Vector2D end = new Vector2D(1.0, 0.0);
        LinkedListNode<Vector2D> path = Pathfinding.findPath(start, end);
        validatePath(path);
        double distance = getLength(start, end);
        assertEquals(2.0,distance,EPSILON);

        start = new Vector2D(0.0, 0.0);
        end = new Vector2D(2.0, 3.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(6.0,distance,EPSILON);

        start = new Vector2D(0.0, 0.0);
        end = new Vector2D(1.0, 0.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(2.0,distance,EPSILON);

        start = new Vector2D(1.0, 0.0);
        end = new Vector2D(0.0, 0.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(2.0,distance,EPSILON);

        start = new Vector2D(0.0, 1.0);
        end = new Vector2D(0.0, 0.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(2.0,distance,EPSILON);

        start = new Vector2D(0.0, 0.0);
        end = new Vector2D(0.0, 1.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(2.0,distance,EPSILON);

        start = new Vector2D(-1.0, 0.0);
        end = new Vector2D(0.0, 0.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(2.0,distance,EPSILON);

        start = new Vector2D(0.0, -1.0);
        end = new Vector2D(0.0, 0.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(2.0,distance,EPSILON);

        start = new Vector2D(0.0, 0.0);
        end = new Vector2D(0.0, -1.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(2.0,distance,EPSILON);

        start = new Vector2D(0.0, 0.0);
        end = new Vector2D(-1.0, 0.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(2.0,distance,EPSILON);

        start = new Vector2D(4.0, 5.0);
        end = new Vector2D(4.0, 5.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(1.0,distance,EPSILON);

        start = new Vector2D(0.0, 0.0);
        end = new Vector2D(-1.0, 0.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(2.0,distance,EPSILON);

        start = new Vector2D(0.0, 4.0);
        end = new Vector2D(0.0, 7.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(4.0,distance,EPSILON);

        start = new Vector2D(0.0, 7.0);
        end = new Vector2D(0.0, 4.0);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(4.0,distance,EPSILON);

        start = new Vector2D(-5.67, -7.06);
        end = new Vector2D(1.74, 4.93);
        path = Pathfinding.findPath(start, end);
        validatePath(path);
        distance = getLength(start, end);
        assertEquals(20.4,distance,EPSILON);


        Vector2D singleNode = new Vector2D(1.0,1.0);
        LinkedListNode<Vector2D> singleNodePath = new LinkedListNode<>(singleNode,null);
        validatePath(singleNodePath);
        distance = getLength(singleNode,singleNode);
        assertEquals(1.0,distance,EPSILON);
        PathStartandEnd(singleNodePath,singleNode,singleNode);

        Vector2D start1 = new Vector2D(0.0, 0.0);
        Vector2D end1 = new Vector2D(5.0, 5.0);
        LinkedListNode<Vector2D> path1 = Pathfinding.findPath(start1, end1);
        validatePath(path1);
        double distance1 = getLength(start1, end1);
        assertEquals(11.0,distance1,EPSILON);
        PathStartandEnd(path1,start1,end1);

        Vector2D start2 = new Vector2D(-7.572, -11.54);
        Vector2D end2 = new Vector2D(-18.02, -5.90);
        LinkedListNode<Vector2D> path2 = Pathfinding.findPath(start2, end2);
        validatePath(path2);
        double distance2 = getLength(start2, end2);
        assertEquals(17.088,distance2,EPSILON);


        Vector2D start3 = new Vector2D(20.50, 30.49);
        Vector2D end3 = new Vector2D(-18.02, -5.90);
        LinkedListNode<Vector2D> path3 = Pathfinding.findPath(start3, end3);
        validatePath(path3);
        double distance3 = getLength(start3, end3);
        assertEquals(75.91,distance3,EPSILON);

    }



    public void validatePath(LinkedListNode<Vector2D> path){
        if (path == null){
            return;
        }
        Vector2D singleNode = path.getValue();
        if (isaWholeNumber(singleNode.getX()) && isaWholeNumber(singleNode.getY())){//checks if single node x and y is a whole number
            Assert.assertTrue("Single node path is valid",true);
        }
        if (path.getNext() == null){
            return;
        }

        LinkedListNode<Vector2D> currentPath = path;
        while (currentPath.getNext() != null){
            Vector2D currentVector = currentPath.getValue();
            Vector2D nextVector = currentPath.getNext().getValue();

            if (currentVector != null && nextVector != null){ //This will check that the current and next vectors are adjacent to each other.
                double nextVectorX = nextVector.getX();
                double nextVectorY = nextVector.getY();
                double currentVectorX = currentVector.getX();
                double currentVectorY = currentVector.getY();
                double xDifference = Math.abs(nextVectorX - currentVectorX);
                double yDifference = Math.abs(nextVectorY - currentVectorY);
                if ((xDifference != 0.0 || yDifference != 1.0) && (xDifference != 1.0 || yDifference != 0.0)){
                    Assert.fail("This is an invalid path. They are not horizontally or vertically adjacent.");
                }
                if((isaWholeNumber(currentVectorX) == false) || (isaWholeNumber(currentVectorY) == false)
                || (isaWholeNumber(nextVectorX) == false) || (isaWholeNumber(nextVectorY) == false)){
                    //This will check if x and y are whole numbers.
                    Assert.fail("This is an invalid path. The x and y coordinates are not whole numbers");
                }
            }
            currentPath = currentPath.getNext();
        }


    }


    private static boolean isaWholeNumber(double num){
        return num == Math.floor(num);
    }

    private static double getLength(Vector2D start, Vector2D end){
        double length = Math.abs(end.getX() - start.getX()) + Math.abs(end.getY() - start.getY()) + 1;
        return length;
    }
    private void PathStartandEnd(LinkedListNode<Vector2D> path, Vector2D start, Vector2D end){
        if (path == null){
        }
        LinkedListNode<Vector2D> endNode = path;
        assertEquals(start.getX(),path.getValue().getX(),EPSILON);
        assertEquals(start.getY(),path.getValue().getY(),EPSILON);
        while (endNode.getNext() != null){
            endNode = endNode.getNext();
        }
        assertEquals(end.getX(),endNode.getValue().getX(),EPSILON);
        assertEquals(end.getY(),endNode.getValue().getY(),EPSILON);

    }
}



