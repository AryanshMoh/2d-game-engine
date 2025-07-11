package app.gameengine.model.gameobjects;

import app.gameengine.Level;
import app.gameengine.model.physics.Vector2D;

public abstract class CollectibleGameObject extends DynamicGameObject{
    private String Id;
    public CollectibleGameObject(Vector2D location,String Id) {
        super(location, 10);
        this.Id = Id;
    }
    public String getItemID(){
        return this.Id;
    }
    @Override
    public void takeDamage(int damage) {
        return;
//        if (damage >= 0){
//            HP = HP - damage;
//        } else{
//            HP = HP;
//        }
//
    }
    public abstract void use(Level level);

    @Override
    public void collideWithDynamicObject(DynamicGameObject otherObject) {
        if(otherObject.isPlayer()){
            otherObject.addInventoryItem(this);
            this.destroy();
        }
    }

}
