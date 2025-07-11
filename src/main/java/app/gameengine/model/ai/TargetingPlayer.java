package app.gameengine.model.ai;

import app.gameengine.Level;
import app.gameengine.model.datastructures.LinkedListNode;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.physics.Vector2D;

public class TargetingPlayer extends Decision{
    private double strayDistance;

    public TargetingPlayer(String DecisionName,double strayDistance) {
        super(DecisionName);
        this.strayDistance = strayDistance;
    }
    @Override
    public boolean decide(DynamicGameObject object, Level level, double dt){
        LinkedListNode<Vector2D> path = object.getPath();
        if(path == null){
            return false;
        } while (path.getNext() != null){
            path = path.getNext();
        }
        double xDifference = path.getValue().getX() - level.getPlayer().getLocation().getX();
        double yDifference = path.getValue().getY() - level.getPlayer().getLocation().getY();
        double euclideanDistance = Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));
        if(euclideanDistance <= strayDistance){
            return true;
        }
        return false;
    }
}
