package app.tests;

import app.gameengine.Level;
import app.gameengine.model.ai.Decision;
import app.gameengine.model.datastructures.BinaryTreeNode;
import app.gameengine.model.gameobjects.DynamicGameObject;

public class TestingClass1 extends Decision {
    private boolean decideDirectionTraverse;
    private BinaryTreeNode<Decision> node;
    private boolean doActionCall = false;
    public TestingClass1(String DecisionName,boolean decideDirectionTraverse) {
        super(DecisionName);
        this.decideDirectionTraverse = decideDirectionTraverse;
    }
    @Override
    public boolean decide(DynamicGameObject object, Level level, double dt){
        return decideDirectionTraverse;
    }
    @Override
    public void doAction(DynamicGameObject object, Level level, double dt){
        doActionCall = true;
    }
    public void resetAction(){
        doActionCall = false;
        String message = "No action";
    }
    public boolean getDecideDirectionTraverse(){
        return decideDirectionTraverse;
    }
    public void setDecideDirectionTraverse(boolean decideDirectionTraverse) {
        this.decideDirectionTraverse = decideDirectionTraverse;
    }

    public boolean isDoActionCall(){
        return doActionCall;
    }
}
