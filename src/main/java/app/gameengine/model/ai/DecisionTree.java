package app.gameengine.model.ai;

import app.gameengine.Level;
import app.gameengine.model.datastructures.BinaryTreeNode;
import app.gameengine.model.gameobjects.DynamicGameObject;

public class DecisionTree {
    private BinaryTreeNode<Decision> treeNode;

    public DecisionTree(BinaryTreeNode<Decision> decisionBinaryTreeNode){
        this.treeNode = decisionBinaryTreeNode;
    }

    public BinaryTreeNode<Decision> getTree() {
        return treeNode;
    }

    public void setTree(BinaryTreeNode<Decision> treeNode) {
        this.treeNode = treeNode;
    }

    public Decision traverse(BinaryTreeNode<Decision> treeNode, DynamicGameObject object, Level level, double dt) {
        BinaryTreeNode<Decision> traversalNode = treeNode;
        if (treeNode == null) {
            return null;
        }
        if (traversalNode.getLeft() == null && traversalNode.getRight() == null) {
            return traversalNode.getValue();
        }
        boolean getDecision = traversalNode.getValue().decide(object, level, dt); // if true then goes right else left

        if (getDecision == true) {
            if (traversalNode.getRight() == null) {
                return null;
            }
            return traverse(traversalNode.getRight(), object, level, dt);
        } else {
            if (traversalNode.getLeft() == null) {
                return null;
            }
            return traverse(traversalNode.getLeft(), object, level, dt);
        }

    }

    public void traverse(DynamicGameObject object, Level level, double dt) {
        Decision newTraverse = traverse(treeNode, object, level, dt);
        if (newTraverse == null){
            return;
        }
        newTraverse.doAction(object, level, dt);
    }

    public void reverse(BinaryTreeNode<Decision> treeNode){
        if (treeNode == null){
            return;
        }
        BinaryTreeNode<Decision> traversalNode = treeNode.getRight();
        treeNode.setRight(treeNode.getLeft());
        treeNode.setLeft(traversalNode);
        reverse(treeNode.getRight());
        reverse(treeNode.getLeft());
    }
    public void reverse(){
        reverse(treeNode);
    }
}








