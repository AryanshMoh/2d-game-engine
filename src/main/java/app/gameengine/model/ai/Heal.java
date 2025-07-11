package app.gameengine.model.ai;

import app.gameengine.Level;
import app.gameengine.model.gameobjects.DynamicGameObject;

public class Heal extends Decision{
    private int hp;
    private double cooldown;
    private double timespent;


    public Heal(String DecisionName, int hp, double cooldown) {
        super(DecisionName);
        this.hp = hp;
        this.cooldown = cooldown;
        this.timespent = 0.0;
    }
    @Override
    public void doAction(DynamicGameObject object, Level level, double dt){
        object.getVelocity().setX(0.0);
        object.getVelocity().setY(0.0);
        timespent = timespent + dt;
        if (timespent >= cooldown){
            object.setHP(object.getHP() + hp);
            timespent = 0.0;
        }
    }
}
