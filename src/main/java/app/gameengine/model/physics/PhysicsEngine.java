package app.gameengine.model.physics;

import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.Level;
import app.gameengine.model.gameobjects.StaticGameObject;


public class PhysicsEngine {

    public PhysicsEngine() {}

    public void updateLevel(Level level, double dt) {

        // Update the location of each dynamic object based on its velocity
        for(DynamicGameObject dynamicObject : level.getDynamicObjects()){
            updateObject(dynamicObject, dt);
        }

        // detect all collisions for each dynamic object
        for (int i = 0; i < level.getDynamicObjects().size(); i++) {
            DynamicGameObject dynamicObject1 = level.getDynamicObjects().get(i);

            // check for collisions with other dynamic objects
            // start at i+1 to avoid reporting collision multiple times
            for (int j = i+1; j < level.getDynamicObjects().size(); j++) {
                DynamicGameObject dynamicObject2 = level.getDynamicObjects().get(j);
                if(detectCollision(dynamicObject1.getHitBox(), dynamicObject2.getHitBox())){
                    dynamicObject1.collideWithDynamicObject(dynamicObject2);
                    dynamicObject2.collideWithDynamicObject(dynamicObject1);
                }
            }

            // check for collisions with static objects
            for(StaticGameObject so : level.getStaticObjects()){
                if(detectCollision(so.getHitBox(), dynamicObject1.getHitBox())){
                    so.collideWithDynamicObject(dynamicObject1);
                    dynamicObject1.collideWithStaticObject(so);
                }
            }
        }

    }

    public void updateObject(DynamicGameObject dynamicObject, double dt){
        // TODO: update location based on velocity
        Vector2D velocity = dynamicObject.getVelocity();
        Vector2D location = dynamicObject.getLocation();

        location.setX(location.getX() + (velocity.getX() * dt));
        location.setY(location.getY() + (velocity.getY() * dt));

    }

    public boolean detectCollision(Hitbox hb1, Hitbox hb2){
        double x = hb1.getLocation().getX();
        double y = hb1.getLocation().getY();
        double width = hb1.getDimensions().getX();
        double height = hb1.getDimensions().getY();
        double x2 = hb2.getLocation().getX();
        double y2 = hb2.getLocation().getY();
        double width2 = hb2.getDimensions().getX();
        double height2 = hb2.getDimensions().getY();
        return (x < x2 + width2) && (y < y2 + height2) && (x2 < x + width) && (y2 < y + height);

        // TODO: return true if the 2 hitboxes overlap; false otherwise
    }



}
