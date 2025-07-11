package app.gameengine.model.ai;

import app.gameengine.Level;
import app.gameengine.model.datastructures.LinkedListNode;
import app.gameengine.model.gameobjects.StaticGameObject;
import app.gameengine.model.physics.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Pathfinding {
    public static LinkedListNode<Vector2D> findPath(Vector2D object1, Vector2D object2) {
        Vector2D start = new Vector2D(Math.floor(object1.getX()), Math.floor(object1.getY()));
        Vector2D end = new Vector2D(Math.floor(object2.getX()), Math.floor(object2.getY()));
        return pathHelper(start, end);
    }
    private static LinkedListNode<Vector2D> pathHelper(Vector2D start, Vector2D end){
        if(start.equals(end)){
            return new LinkedListNode<>(start,null);
        }
        int changeX = (int) (end.getX() - start.getX());
        int changeY = (int) (end.getY() - start.getY());
        if (changeY != 0) {
            int move = 0;
            if (changeY < 0) {
                move = -1;
            } else {
                move = 1;
            }
            Vector2D nextMove = new Vector2D(start.getX(), start.getY() + move);
            return new LinkedListNode<>(start, pathHelper(nextMove, end));

        }
        if(changeX != 0) {
            int move = 0;
            if (changeX < 0) {
                move = -1;
            } else {
                move = 1;
            }
            Vector2D nextMove = new Vector2D(start.getX() + move, start.getY());
            return new LinkedListNode<>(start, pathHelper(nextMove,end));
        }
        return new LinkedListNode<>(start,null);
    }
    public static LinkedListNode<Vector2D> findPathAvoidWalls(Level level, Vector2D startingLocation, Vector2D endingLocation) {
        Vector2D start = new Vector2D(Math.floor(startingLocation.getX()), Math.floor(startingLocation.getY()));
        Vector2D end = new Vector2D(Math.floor(endingLocation.getX()), Math.floor(endingLocation.getY()));
        int row = level.getHeight();
        int column = level.getWidth();
        int Xstart = (int) start.getX();
        int Ystart = (int) start.getY();
        int Xend = (int) end.getX();
        int Yend = (int) end.getY();
        if (Xstart < 0 || Xstart >= column || Ystart < 0 || Ystart >= row ||
                Xend < 0 || Xend >= column || Yend < 0 || Yend >= row ||
                isWallTile(start, level) || isWallTile(end, level)) {
            return null;
        }
        if (start.equals(end)) {
            return new LinkedListNode<>(start, null);
        }
        Queue<Vector2D> queue = new LinkedList<>();
        Vector2D[][] previousTile = new Vector2D[row][column];
        boolean[][] visited = new boolean[row][column];
        queue.add(start);
        visited[Ystart][Xstart] = true;
        while (!queue.isEmpty()) {
            Vector2D current = queue.poll();
            for (Vector2D neighbor : getNeighbours(current)) {
                int x = (int) neighbor.getX();
                int y = (int) neighbor.getY();
                if (x >= 0 && x < column && y >= 0 && y < row && !visited[y][x] && !isWallTile(neighbor, level)) {
                    queue.add(neighbor);
                    visited[y][x] = true;
                    previousTile[y][x] = current;
                }
            }
        }
        if (!visited[Yend][Xend]) {
            return null;
        }
        return createPath(start, end, previousTile);
    }
    public static LinkedListNode<Vector2D> createPath(Vector2D start, Vector2D end, Vector2D[][] previousTile) {
        LinkedListNode<Vector2D> path = null;
        Vector2D current = end;
        int rows = previousTile.length;
        int cols = previousTile[0].length;
        while (!current.equals(start)) {
            int x = (int) current.getX();
            int y = (int) current.getY();
            if (x < 0 || x >= cols || y < 0 || y >= rows) {
                return null;
            }
            path = new LinkedListNode<>(current, path);
            current = previousTile[y][x];
            if (current == null) {
                return null;
            }
        }
        return new LinkedListNode<>(start, path);
    }
    public static ArrayList<Vector2D> getNeighbours(Vector2D tile) {
        ArrayList<Vector2D> neighbors = new ArrayList<>();
        neighbors.add(new Vector2D(tile.getX() + 1, tile.getY()));
        neighbors.add(new Vector2D(tile.getX() - 1, tile.getY()));
        neighbors.add(new Vector2D(tile.getX(), tile.getY() + 1));
        neighbors.add(new Vector2D(tile.getX(), tile.getY() - 1));
        return neighbors;
    }
    public static boolean isWallTile(Vector2D tile, Level level) {
        for (StaticGameObject wall : level.getStaticObjects()) {
            if (wall.getLocation().getX() == tile.getX() && wall.getLocation().getY() == tile.getY()) {
                return true;
            }
        }
        return false;
    }
}
// Citations:
// https://www.youtube.com/watch?v=-tgVpUgsQ5k&t=600s
// https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
// https://www.hackerearth.com/practice/algorithms/graphs/breadth-first-search/tutorial/