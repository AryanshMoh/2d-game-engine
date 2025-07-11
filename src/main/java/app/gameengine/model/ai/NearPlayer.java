package app.gameengine.model.ai;

import app.gameengine.Level;
import app.gameengine.model.gameobjects.DynamicGameObject;

public class NearPlayer extends Decision {
    private double acceptableDistance;

    public NearPlayer(String DecisionName,double acceptableDistance) {
        super(DecisionName);
        this.acceptableDistance = acceptableDistance;
    }
    @Override
    public boolean decide(DynamicGameObject object, Level level, double dt){
        double xDifference = object.getLocation().getX() - level.getPlayer().getLocation().getX();
        double yDifference = object.getLocation().getY() - level.getPlayer().getLocation().getY();
        double euclideanDistance = Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));
        if(euclideanDistance <= acceptableDistance){
            return true;
        } else{
            return false;
        }
    }

}
