package app.gameengine.model.gameobjects;

import app.gameengine.Level;
import app.gameengine.model.ai.Decision;
import app.gameengine.model.ai.DecisionTree;
import app.gameengine.model.datastructures.LinkedListNode;
import app.gameengine.model.physics.Vector2D;
import app.games.topdownobjects.Projectile;

import java.util.ArrayList;

public abstract class DynamicGameObject extends GameObject {
    private DecisionTree decisionTree;
    private LinkedListNode<Vector2D> path = null;
    private double remainingtime = 0.0;
    private boolean isOnGround = false;
    protected Vector2D velocity = new Vector2D(0.0, 0.0);
    private int maxHP;
    private int HP;
    private Vector2D orient;
    private ArrayList<CollectibleGameObject> inventory;
    private CollectibleGameObject currentItem;


    public DynamicGameObject(Vector2D location, int maxHP) {
        super(location);
        this.maxHP = maxHP;
        this.HP = maxHP;
        this.orient = new Vector2D(0.0, 1.0);
        this.inventory = new ArrayList<>();
    }

    public LinkedListNode<Vector2D> getPath() {
        return this.path;
    }

    public void setPath(LinkedListNode<Vector2D> path) {
        this.path = path;
    }

    public DecisionTree getDecisionTree() {
        return this.decisionTree;
    }

    public void setDecisionTree(DecisionTree decisionTree) {
        this.decisionTree = decisionTree;
    }


    public double getInvincibilityFrames() {
        return remainingtime;
    }

    public void setInvincibilityFrames(double remainingtime) {
        this.remainingtime = remainingtime;
    }


    public int getMaxHP() {
        return this.maxHP;
    }

    public int getHP() {
        return this.HP;
    }

    public void setHP(int hp) {
        if (hp > maxHP) {
            this.HP = maxHP;
        } else {
            this.HP = hp;
        }


    }

    public Vector2D getOrientation() {
        return this.orient;
    }

    public void takeDamage(int damage) {
        if (damage >= 0) {
            HP = HP - damage;
        } else {
            HP = HP;
        }

    }

    public boolean isOnGround() {
        return this.isOnGround;
    }

    public void setOnGround(boolean isOnGround) {
        this.isOnGround = isOnGround;
    }


    public Vector2D getVelocity() {
        return this.velocity;
    }

    @Override
    public void update(double dt, Level level) {
        if (decisionTree != null) {
            decisionTree.traverse(this, level, dt);
        }
        super.update(dt, level);
        double v = this.getInvincibilityFrames() - dt;//Review Later
        if (v < 0.0) {
            v = 0.0;
        }
        this.setInvincibilityFrames(v);
        if (this.getHP() <= 0) {
            this.destroy();
        }
    }


    @Override
    public boolean isDestroyed() {
        return super.isDestroyed();
    }

    @Override
    public void revive() {
        super.revive();
    }

    @Override
    public void collideWithStaticObject(StaticGameObject otherObject) {

    }

    @Override
    public void collideWithDynamicObject(DynamicGameObject otherObject) {
    }

    public void addInventoryItem(CollectibleGameObject collectibleGameObject) {
        inventory.add(collectibleGameObject);
        if(inventory.size() == 1){
            currentItem = collectibleGameObject;
        }
    }

    public void removeActiveItem() {
        if(inventory.size() == 0 || currentItem == null){
            currentItem = null;
            return;
        }
        int itemIndex = inventory.indexOf(currentItem);
        inventory.remove(currentItem);

        if(inventory.size() == 0){
            currentItem = null;
        }
        else {
            itemIndex = itemIndex % inventory.size();
            currentItem = inventory.get(itemIndex);
        }

//        if (inventory.size() == 0){
//            currentItem = null;
//        }
//        else if (itemIndex >= inventory.size()){ //restart loop
//            itemIndex = 0;
//        }
//        currentItem = inventory.get(itemIndex);


    }

    public CollectibleGameObject getActiveItem() {
        if(inventory.size() == 0){
            return null;
        }
        if(inventory.size() == 1){
            return inventory.getFirst();
        }
        return currentItem;
    }

    public String getActiveItemID(){
        if (inventory.size() == 0){
            return "No item equipped";
        }
        if(currentItem == null){
            return "No item equipped";
        }
        return currentItem.getItemID();
    }

    public void cycleInventory(){
        int inventorySize = inventory.size();
        if(inventorySize == 0){
            return;
        }
        if(currentItem == null){
            return;
        }
        int index = inventory.indexOf(currentItem);
        int nextIndex = (index + 1) % inventorySize;
        currentItem = inventory.get(nextIndex);
    }

    public void fireProjectile(Projectile projectile, double speed, Level level){
        if (projectile == null){
            return;
        }
        if(level == null){
            return;
        }
        projectile.getLocation().setX(this.getLocation().getX());
        projectile.getLocation().setY(this.getLocation().getY());
        projectile.getVelocity().setX(this.getOrientation().getX() * speed);
        projectile.getVelocity().setY(this.getOrientation().getY() * speed);
        level.getDynamicObjects().add(projectile);



    }
}