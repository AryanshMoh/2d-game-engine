package app.gameengine.model.ai;

import app.gameengine.Level;
import app.gameengine.model.datastructures.LinkedListNode;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.physics.Vector2D;

public class RecalculatePath extends Decision{
    public RecalculatePath(String DecisionName) {
        super(DecisionName);
    }
    @Override
    public void doAction(DynamicGameObject object, Level level, double dt){
        object.getVelocity().setX(0.0);
        object.getVelocity().setY(0.0);
        Vector2D playerLocation = level.getPlayer().getLocation();
        Vector2D enemyLocation = object.getLocation();
        LinkedListNode<Vector2D> path = Pathfinding.findPath(enemyLocation,playerLocation);
        object.setPath(path);
    }
}
