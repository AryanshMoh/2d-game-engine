package app.gameengine.model.ai;

import app.gameengine.Level;
import app.gameengine.model.gameobjects.DynamicGameObject;

public class LowHP extends Decision{
    private int hpThreshold;


    public LowHP(String DecisionName, int hpThreshold) {
        super(DecisionName);
        this.hpThreshold = hpThreshold;
    }
    @Override
    public boolean decide(DynamicGameObject object, Level level, double dt){
        if(object.getHP() <= hpThreshold){
            return true;
        } else{
            return false;
        }
    }

}
