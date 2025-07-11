package app.gameengine.model.ai;

import app.gameengine.Level;
import app.gameengine.model.datastructures.LinkedListNode;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.physics.Vector2D;

public class MoveTowardsPlayer extends Decision{


    public MoveTowardsPlayer(String DecisionName) {
        super(DecisionName);
    }
    @Override
    public void doAction(DynamicGameObject object, Level level, double dt){
        LinkedListNode<Vector2D> path = object.getPath();
        Vector2D enemyLocation = object.getLocation();
        if (path == null) {
            Vector2D playerLocation = level.getPlayer().getLocation();
            path = Pathfinding.findPath(enemyLocation, playerLocation);
            object.setPath(path);
            return ;
        }
        Vector2D playerTile = path.getValue();
        double xDifference = playerTile.getX() - enemyLocation.getX();
        double yDifference = playerTile.getY() - enemyLocation.getY();
        double euclideanDistance = Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));
        if (euclideanDistance < 0.01) {
            object.getLocation().setX(playerTile.getX());
            object.getLocation().setY(playerTile.getY());
            object.setPath(path.getNext());
            return;
        }
        Vector2D velocity;
        if (Math.abs(xDifference) > Math.abs(yDifference)) {
            if (xDifference > 0) {
                velocity = new Vector2D(1.0, 0.0);
            } else {
                velocity = new Vector2D(-1.0, 0.0);
            }
        } else {
            if (yDifference > 0) {
                velocity = new Vector2D(0.0, 1.0);
            } else {
                velocity = new Vector2D(0.0, -1.0);
            }
        }
//        enemyLocation.setX(enemyLocation.getX() + (velocity.getX() * timeChange));
//        enemyLocation.setY(enemyLocation.getY() + (velocity.getY() * timeChange));
        object.getVelocity().setX(velocity.getX());
        object.getVelocity().setY(velocity.getY());
    }
}


