package app.gameengine;

import app.gameengine.graphics.SpriteGraphics;
import app.gameengine.model.datastructures.LinkedListNode;
import app.gameengine.model.gameobjects.GameObject;
import app.gameengine.model.gameobjects.Player;
import app.gameengine.model.physics.Vector2D;

import java.util.HashMap;

/**
 * Controls the UI and levels of the game.
 *
 * You can extend this class if you would like to build a different
 * game. The game that is played by default is `SampleTopDownGame.java`
 */
public class Game {
    protected LinkedListNode<Level> lvl = null;

    private Player player = new Player(new Vector2D(0.0, 0.0), 10);
    private Level currentLevel;
    private long lastUpdate = 0L;
    protected String displayString = "This is where UI information would go, like HP, number of keys, or inventory";

    public Game() {
        this.init();
    }


    public Player getPlayer() {
        return player;
    }
    
    public String getUI() {
        return this.getCurrentLevel().getName() + " - " + this.player.getHP() + "/" + this.player.getMaxHP() + " - "
        + this.displayString;
    }


    public Level getCurrentLevel() {
        return this.currentLevel;
    }
    
    public void loadLevel(Level level) {
        this.currentLevel = level;
        this.player.getLocation().setX(level.getPlayerStartLocation().getX());
        this.player.getLocation().setY(level.getPlayerStartLocation().getY());
        this.currentLevel.getDynamicObjects().removeIf(GameObject::isPlayer); // prevents the player from being in the level more than once
        this.currentLevel.getDynamicObjects().add(this.player);
    }

    public void update(long timestamp) {
        if (this.lastUpdate != 0) {
            double dt = (timestamp - this.lastUpdate) / 1000000000.0;
            this.currentLevel.update(dt);
            if (this.player.isDestroyed()) {
                this.player.setHP(this.player.getMaxHP());
                this.player.revive();
                this.loadLevel(this.currentLevel);
            }
        }
        this.lastUpdate = timestamp;
    }
    public LinkedListNode<Level> getLevelList(){
        return this.lvl;
    }
    public void setLevelList(LinkedListNode<Level> level){
        this.lvl = level;
    }
    public void addLevel(Level level){
        if (this.lvl == null){
            this.lvl = new LinkedListNode<>(level,null);
        } else {
            this.lvl.append(level);
        }
    }
    public void removeLevelByName(String name){
        if (lvl == null){
            return;
        }
        if(lvl.getValue().getName().equals(name)){
            lvl = lvl.getNext();
            return;
        }
        LinkedListNode<Level> present = lvl;
        while(present.getNext()!=null){
            if(present.getNext().getValue().getName().equals(name)){
                present.setNext(present.getNext().getNext());
                return;
            }
            present = present.getNext();
        }

    }
    public void advanceLevel(){
        LinkedListNode<Level> present = lvl;
        Level currentLevel = getCurrentLevel();
        while (present != null){
            if(present.getValue().getName().equals(currentLevel.getName())){
                if(present.getNext() != null){
                    Level nextlvl = present.getNext().getValue();
                    this.loadLevel(nextlvl);
                }
                return;
            }
            present = present.getNext();

        }



    }
    public void init(){}

}
