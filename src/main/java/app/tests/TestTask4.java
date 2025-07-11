package app.tests;

import app.gameengine.Game;
import app.gameengine.Level;
import app.gameengine.model.ai.Decision;
import app.gameengine.model.ai.DecisionTree;
import app.gameengine.model.datastructures.BinaryTreeNode;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.gameobjects.Player;
import app.gameengine.model.physics.PhysicsEngine;
import app.gameengine.model.physics.Vector2D;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.junit.Assert.fail;

public class TestTask4 {
    private DecisionTree tree;
    static final double EPSILON = 0.0001;
    public static void compareBinaryTreesOfDecisions(BinaryTreeNode<Decision> d1, BinaryTreeNode<Decision> d2){
        if(d1 == null && d2 == null){
            return;
        }
        if(d1 == null && d2 != null){
            assertEquals("Trees are not same",d1,d2);
        }
        if(d1 != null && d2 == null){
            assertEquals("Trees are not same",d1,d2);
        }
        String decision1 = d1.getValue().getName();
        String decision2 = d2.getValue().getName();
        assertEquals("Decision names are same",decision1,decision2);
        compareBinaryTreesOfDecisions(d1.getLeft(),d2.getLeft());
        compareBinaryTreesOfDecisions(d1.getRight(),d2.getRight());
    }

    @Test
    public void testReverse(){
        Decision rootdecision = new Decision("Root");
        BinaryTreeNode<Decision> rootNode = new BinaryTreeNode<>(rootdecision,null,null);
        BinaryTreeNode<Decision> expected = new BinaryTreeNode<>(rootdecision,null,null);
        DecisionTree tree = new DecisionTree(rootNode);
        DecisionTree expectedtree = new DecisionTree(expected);
        tree.reverse();
        compareBinaryTreesOfDecisions(expectedtree.getTree(),tree.getTree());
    }
    @Test
    public void testReverseBasicFunctionality(){
        Decision rootdecision = new Decision("Root");
        Decision leftdecision = new Decision("Left");
        Decision rightdecision = new Decision("Right");
        BinaryTreeNode<Decision> leftNode = new BinaryTreeNode<>(leftdecision,null,null);
        BinaryTreeNode<Decision> rightNode = new BinaryTreeNode<>(rightdecision,null,null);
        BinaryTreeNode<Decision> rootNode = new BinaryTreeNode<>(rootdecision,leftNode,rightNode);
        DecisionTree tree = new DecisionTree(rootNode);
        tree.reverse();
        BinaryTreeNode<Decision> expectedLeft = new BinaryTreeNode<>(rightdecision,null,null);
        BinaryTreeNode<Decision> expectedRight = new BinaryTreeNode<>(leftdecision,null,null);
        BinaryTreeNode<Decision> expectedRoot = new BinaryTreeNode<>(rootdecision,expectedLeft,expectedRight);
        DecisionTree expectedtree = new DecisionTree(expectedRoot);
        compareBinaryTreesOfDecisions(expectedtree.getTree(),tree.getTree());
    }

    @Test
    public void testReverseMoreFunctionality(){
        Decision rootdecision = new Decision("Root");
        Decision leftdecision = new Decision("Left");
        Decision rightdecision = new Decision("Right");
        Decision leftandLeftdecision = new Decision("LeftandLeft");
        Decision leftandRightdecision = new Decision("LeftandRight");
        Decision rightandRightdecision = new Decision("RightandRight");
        Decision rightandLeftdecision = new Decision("RightandRight");
        BinaryTreeNode<Decision> leftandLeftNode = new BinaryTreeNode<>(leftandLeftdecision,null,null);
        BinaryTreeNode<Decision> leftandRightNode = new BinaryTreeNode<>(leftandRightdecision,null,null);
        BinaryTreeNode<Decision> rightandLeftNode = new BinaryTreeNode<>(rightandLeftdecision,null,null);
        BinaryTreeNode<Decision> rightandRightNode = new BinaryTreeNode<Decision>(rightandRightdecision,null,null);
        BinaryTreeNode<Decision> leftNode = new BinaryTreeNode<>(leftdecision,leftandLeftNode,leftandRightNode);
        BinaryTreeNode<Decision> rightNode = new BinaryTreeNode<>(rightdecision,rightandLeftNode,rightandRightNode);
        BinaryTreeNode<Decision> rootNode = new BinaryTreeNode<>(rootdecision,leftNode,rightNode);
        DecisionTree tree = new DecisionTree(rootNode);
        tree.reverse();
        BinaryTreeNode<Decision> expectedLeftandLeftNode = new BinaryTreeNode<>(rightandRightdecision,null,null);
        BinaryTreeNode<Decision> expectedLeftandRightNode = new BinaryTreeNode<>(rightandLeftdecision,null,null);
        BinaryTreeNode<Decision> expectedRightandRightNode = new BinaryTreeNode<>(leftandLeftdecision,null,null);
        BinaryTreeNode<Decision> expectedRightandLeftNode = new BinaryTreeNode<>(leftandRightdecision,null,null);
        BinaryTreeNode<Decision> expectedLeft = new BinaryTreeNode<>(rightdecision,expectedLeftandLeftNode,expectedLeftandRightNode);
        BinaryTreeNode<Decision> expectedRight = new BinaryTreeNode<>(leftdecision,expectedRightandLeftNode,expectedRightandRightNode);
        BinaryTreeNode<Decision> expectedRootNode = new BinaryTreeNode<>(rootdecision,expectedLeft,expectedRight);
        DecisionTree expectedTree = new DecisionTree(expectedRootNode);
        compareBinaryTreesOfDecisions(expectedTree.getTree(), tree.getTree());
    }

    @Test
    public void testTraverse(){
        TestingClass1 root = new TestingClass1("Root",true);
        TestingClass1 right = new TestingClass1("Right",true);
        TestingClass1 end = new TestingClass1("End",true);
        BinaryTreeNode<Decision> endNode = new BinaryTreeNode<>(end,null,null);
        BinaryTreeNode<Decision> rightNode = new BinaryTreeNode<>(right,null,endNode);
        BinaryTreeNode<Decision> rootNode = new BinaryTreeNode<>(root,null,rightNode);
        DecisionTree tree = new DecisionTree(rootNode);
        tree.traverse(null,null,0.0);
        assertEquals(true,end.isDoActionCall());
    }
    @Test
    public void testTraverseEmptyTree() {
        DecisionTree tree = new DecisionTree(null);
        tree.traverse(null, null, 0.0);
        assertEquals(true, true);
    }
    @Test
    public void testTraverseSingleNode() {
        TestingClass1 root = new TestingClass1("Root", true);
        BinaryTreeNode<Decision> rootNode = new BinaryTreeNode<>(root, null, null);
        DecisionTree tree = new DecisionTree(rootNode);
        tree.traverse(null, null, 0.0);
        assertTrue(root.isDoActionCall());
    }
    @Test
    public void testTraverse2(){
        TestingClass1 root = new TestingClass1("Root",true);
        TestingClass1 right = new TestingClass1("Right",true);
        TestingClass1 end = new TestingClass1("End",true);
        TestingClass1 leftEnd = new TestingClass1("Left End", true);
        BinaryTreeNode<Decision> leftEndNode = new BinaryTreeNode<>(leftEnd,null,null);
        BinaryTreeNode<Decision> endNode = new BinaryTreeNode<>(end,leftEndNode,null);
        BinaryTreeNode<Decision> rightNode = new BinaryTreeNode<>(right,null,endNode);
        BinaryTreeNode<Decision> rootNode = new BinaryTreeNode<>(root,null,rightNode);
        DecisionTree tree = new DecisionTree(rootNode);
        tree.traverse(null,null,0.0);
        assertEquals(false,end.isDoActionCall());
    }

}
