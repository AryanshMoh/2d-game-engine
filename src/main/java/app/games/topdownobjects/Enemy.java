package app.games.topdownobjects;

import app.gameengine.Level;
import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.ai.Decision;
import app.gameengine.model.ai.DecisionTree;
import app.gameengine.model.ai.MoveTowardsPlayer;
import app.gameengine.model.ai.Pathfinding;
import app.gameengine.model.datastructures.BinaryTreeNode;
import app.gameengine.model.datastructures.LinkedListNode;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.physics.Vector2D;

/**
 * Basic enemy class.
 *
 * In future tasks, you will develop tools to give enemies more features:
 * like pathfinding, AI, etc.
 */
public class Enemy extends DynamicGameObject {
    private LinkedListNode<Vector2D> path = null;

    private int strength;

    public Enemy(Vector2D location) {
        this(location, 3);
    }

    public Enemy(Vector2D location, int strength) {
        super(location, 10);
        this.strength = strength;
        this.spriteSheetFilename = "Characters/Monsters/Demons/ArmouredRedDemon.png";
        this.defaultSpriteLocation = new SpriteLocation(0, 2);
        MoveTowardsPlayer obj = new MoveTowardsPlayer("Move towards player");
        BinaryTreeNode<Decision> node = new BinaryTreeNode<Decision>(obj,null,null);
        this.setDecisionTree(new DecisionTree(node));
    }

    @Override
    public void collideWithDynamicObject(DynamicGameObject otherObject) {
        if(otherObject.isPlayer()){
            if(otherObject.getInvincibilityFrames() > 0.0){
                return;
            }
            otherObject.takeDamage(this.strength);
            otherObject.setInvincibilityFrames(0.5);
        }
    }
    public void setPath(LinkedListNode<Vector2D> path){
        this.path = path;
    }
    public LinkedListNode<Vector2D> getPath(){
        return this.path;
    }

//    public void update(double timeChange, Level level) {
//        Vector2D enemyLocation = this.getLocation();
//        if (path == null) {
//            Vector2D playerLocation = level.getPlayer().getLocation();
//            this.path = Pathfinding.findPath(enemyLocation, playerLocation);
//            return;
//        }
//
//        Vector2D playerTile = this.getPath().getValue();
//        double xDifference = playerTile.getX() - enemyLocation.getX();
//        double yDifference = playerTile.getY() - enemyLocation.getY();
//        double euclideanDistance = Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));
//        if (euclideanDistance < 0.01) {
//            this.getLocation().setX(playerTile.getX());
//            this.getLocation().setY(playerTile.getY());
//            path = this.getPath().getNext();
//            return;
//        }
//
//        Vector2D velocity;
//        if (Math.abs(xDifference) > Math.abs(yDifference)) {
//            if (xDifference > 0) {
//                velocity = new Vector2D(1.0, 0.0);
//            } else {
//                velocity = new Vector2D(-1.0, 0.0);
//            }
//        } else {
//            if (yDifference > 0) {
//                velocity = new Vector2D(0.0, 1.0);
//            } else {
//                velocity = new Vector2D(0.0, -1.0);
//            }
////            enemyLocation.setX(enemyLocation.getX() + (velocity.getX() * timeChange));
////            enemyLocation.setY(enemyLocation.getY() + (velocity.getY() * timeChange));
////            this.getVelocity().setX(velocity.getX());
////            this.getVelocity().setY(velocity.getY());
//        }
////        enemyLocation.setX(enemyLocation.getX() + (velocity.getX() * timeChange));
////        enemyLocation.setY(enemyLocation.getY() + (velocity.getY() * timeChange));
//        this.getVelocity().setX(velocity.getX());
//        this.getVelocity().setY(velocity.getY());
//
//    }

}


