package app.tests;

import app.gameengine.Level;
import app.gameengine.model.gameobjects.CollectibleGameObject;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.gameobjects.Player;
import app.gameengine.model.physics.Vector2D;
import app.games.topdownobjects.AxePickup;
import app.games.topdownobjects.MagicPickup;
import app.games.topdownobjects.PotionPickup;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestTask5 {
    @Test
    public void testAddInventoryItem(){
        //Make new objects regarding axepickup,magicpickup and potionpickup classes.
        Player player = new Player(new Vector2D(0,0),10);
        CollectibleGameObject axe = new AxePickup(new Vector2D(0,0));
        CollectibleGameObject magic = new MagicPickup(new Vector2D(2,0));
        CollectibleGameObject potion = new PotionPickup(new Vector2D(4,0),5);
        player.addInventoryItem(axe);
        player.addInventoryItem(magic);
        assertEquals("Axe",player.getActiveItemID());
        player.cycleInventory();
        assertEquals("Magic",player.getActiveItemID());
        player.addInventoryItem(potion);
        player.cycleInventory();
        assertEquals("Health Potion",player.getActiveItemID());
        player.cycleInventory();
        assertEquals("Axe",player.getActiveItemID());
    }
    @Test
    public void testRemoveActiveItem(){
        Player player = new Player(new Vector2D(0,0),10);
        CollectibleGameObject axe = new AxePickup(new Vector2D(0,0));
        CollectibleGameObject magic = new MagicPickup(new Vector2D(2,0));
        CollectibleGameObject potion = new PotionPickup(new Vector2D(4,0),5);
        player.addInventoryItem(axe);
        player.addInventoryItem(magic);
        assertEquals("Axe",player.getActiveItemID());
        player.removeActiveItem();
        assertEquals("Magic",player.getActiveItemID());
        player.addInventoryItem(potion);
        player.removeActiveItem();
        assertEquals("Health Potion",player.getActiveItemID());
    }
    @Test
    public void testRemoveActiveItem2(){
        Player player = new Player(new Vector2D(0,0),10);
        CollectibleGameObject axe = new AxePickup(new Vector2D(0,0));
        CollectibleGameObject magic = new MagicPickup(new Vector2D(2,0));
        CollectibleGameObject potion = new PotionPickup(new Vector2D(4,0),5);
        player.addInventoryItem(axe);
        player.addInventoryItem(potion);
        player.removeActiveItem();
        assertEquals("Health Potion",player.getActiveItemID());
    }
    @Test
    public void testRemoveActiveItem3(){
        Player player = new Player(new Vector2D(0,0),10);
        CollectibleGameObject axe = new AxePickup(new Vector2D(0,0));
        CollectibleGameObject magic = new MagicPickup(new Vector2D(2,0));
        CollectibleGameObject potion = new PotionPickup(new Vector2D(4,0),5);
        player.addInventoryItem(axe);
        player.addInventoryItem(potion);
        player.cycleInventory();
        player.removeActiveItem();
        assertEquals("Axe",player.getActiveItemID());
    }
    @Test
    public void testRemoveActiveItem4(){
        Player player = new Player(new Vector2D(0,0),10);
        CollectibleGameObject axe = new AxePickup(new Vector2D(0,0));
        CollectibleGameObject magic = new MagicPickup(new Vector2D(2,0));
        CollectibleGameObject potion = new PotionPickup(new Vector2D(4,0),5);
        player.addInventoryItem(potion);
        player.addInventoryItem(axe);
        player.addInventoryItem(magic);
        player.cycleInventory();
        player.removeActiveItem();
        assertEquals("Magic",player.getActiveItemID());
    }
    @Test
    public void testRemoveActiveItem5(){
        Player player = new Player(new Vector2D(0,0),10);
        CollectibleGameObject axe = new AxePickup(new Vector2D(0,0));
        CollectibleGameObject magic = new MagicPickup(new Vector2D(2,0));
        CollectibleGameObject potion = new PotionPickup(new Vector2D(4,0),5);
        player.addInventoryItem(potion);
        player.addInventoryItem(axe);
        player.addInventoryItem(magic);
        player.cycleInventory();
        player.removeActiveItem();
        player.cycleInventory();
        assertEquals("Health Potion",player.getActiveItemID());
    }
    @Test
    public void testAddInventoryItem2(){
        Player player = new Player(new Vector2D(0,0),10);
        CollectibleGameObject axe = new AxePickup(new Vector2D(0,0));
        CollectibleGameObject magic = new MagicPickup(new Vector2D(2,0));
        CollectibleGameObject potion = new PotionPickup(new Vector2D(4,0),5);
        player.addInventoryItem(axe);
        player.addInventoryItem(magic);
        player.addInventoryItem(potion);
        player.addInventoryItem(new AxePickup(new Vector2D(1,1)));
        player.addInventoryItem(new PotionPickup(new Vector2D(5,1),10));
        player.addInventoryItem(new MagicPickup(new Vector2D(5,6)));
        assertEquals("Axe",player.getActiveItemID());
        player.cycleInventory();
        assertEquals("Magic",player.getActiveItemID());
        player.removeActiveItem();
        player.removeActiveItem();
        assertEquals("Axe",player.getActiveItemID());
        player.cycleInventory();
        player.cycleInventory();
        assertEquals("Magic",player.getActiveItemID());

    }

}
