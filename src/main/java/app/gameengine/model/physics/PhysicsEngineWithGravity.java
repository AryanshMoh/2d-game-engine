package app.gameengine.model.physics;

import app.gameengine.model.gameobjects.DynamicGameObject;

public class PhysicsEngineWithGravity extends PhysicsEngine {
    private double gravity;
    public PhysicsEngineWithGravity(double gravity){
        this.gravity = gravity;
    }
    @Override
    public void updateObject(DynamicGameObject dynamicObject, double dt){
        Vector2D velocity = dynamicObject.getVelocity();
        double changeInVelocity = this.gravity * dt;
        double newVelocity = velocity.getY() + changeInVelocity;
        velocity.setY(newVelocity);
        super.updateObject(dynamicObject,dt);
    }
}
