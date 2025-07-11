package app.gameengine.model.ai;

import app.gameengine.Level;
import app.gameengine.model.gameobjects.DynamicGameObject;

public class Decision {
    private String DecisionName;

    public Decision(String DecisionName){
        this.DecisionName = DecisionName;
    }

    public String getName() {
        return this.DecisionName;
    }

    public void setName(String decisionName) {
        this.DecisionName = decisionName;
    }

    public boolean decide(DynamicGameObject object, Level level,double dt){
        return false;
    }

    public void doAction(DynamicGameObject object, Level level, double dt){
        return;
    }


}
