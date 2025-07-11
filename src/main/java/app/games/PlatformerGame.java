package app.games;

import app.gameengine.Game;
import app.gameengine.Level;
import app.gameengine.model.physics.Vector2D;
import app.games.commonobjects.Goal;
import app.games.commonobjects.Potion;
import app.games.commonobjects.Wall;
import app.games.platformerobjects.PlatformerLevel;
import app.games.platformerobjects.PlatformerWall;
import app.games.topdownobjects.Enemy;
import app.games.topdownobjects.TopDownLevel;
import app.games.topdownobjects.Tower;

public class PlatformerGame extends Game {
    @Override
    public void init(){
        this.addLevel(levelZero());
        this.addLevel(levelOne());
        this.addLevel(levelTwo());
        this.loadLevel(levelZero());

    }
    public Level levelZero() {
        Level firstLevel = new PlatformerLevel(this, 12, 8, "level0");
        firstLevel.getStaticObjects().add(new PlatformerWall(4, 1));
        firstLevel.getStaticObjects().add(new PlatformerWall(4, 2));
        firstLevel.getStaticObjects().add(new PlatformerWall(4, 3));
        firstLevel.getStaticObjects().add(new PlatformerWall(4, 4));
        firstLevel.getStaticObjects().add(new PlatformerWall(5, 4));
        firstLevel.getStaticObjects().add(new PlatformerWall(6, 4));
        firstLevel.getStaticObjects().add(new PlatformerWall(7, 4));
        firstLevel.getStaticObjects().add(new PlatformerWall(8, 4));
        firstLevel.getStaticObjects().add(new Tower(10, 1));
        firstLevel.getStaticObjects().add(new Goal(6, 2, this));
        firstLevel.getPlayerStartLocation().setX(2.0);
        firstLevel.getPlayerStartLocation().setY(2.0);

        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(8.0, 1.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(9.0, 5.0)));

        firstLevel.getDynamicObjects().add(new Potion(new Vector2D(7.0,6.0),10));
        return firstLevel;
    }
    public Level levelOne() {
        Level firstLevel = new PlatformerLevel(this, 12, 9, "level1");
        firstLevel.getStaticObjects().add(new Goal(7, 4, this));
        firstLevel.getPlayerStartLocation().setX(2.0);
        firstLevel.getPlayerStartLocation().setY(4.0);

        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(5.0, 2.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(5.0, 3.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(5.0, 4.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(5.0, 5.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(5.0, 6.0)));

        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(6.0, 6.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(7.0, 6.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(8.0, 6.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(9.0, 6.0)));

        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(9.0, 5.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(9.0, 4.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(9.0, 3.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(9.0, 2.0)));

        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(8.0, 2.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(7.0, 2.0)));
        firstLevel.getDynamicObjects().add(new Enemy(new Vector2D(6.0, 2.0)));

//        firstLevel.getDynamicObjects().add(new Potion(new Vector2D(7.0,7.0),10));

        return firstLevel;
    }
    public Level levelTwo() {
        Level firstLevel = new PlatformerLevel(this, 17, 9, "level2");


        firstLevel.getStaticObjects().add(new PlatformerWall(2, 2));
        firstLevel.getStaticObjects().add(new PlatformerWall(2, 3));
        firstLevel.getStaticObjects().add(new PlatformerWall(2, 4));
        firstLevel.getStaticObjects().add(new PlatformerWall(2, 5));
        firstLevel.getStaticObjects().add(new PlatformerWall(2, 6));

        firstLevel.getStaticObjects().add(new PlatformerWall(3, 5));

        firstLevel.getStaticObjects().add(new PlatformerWall(4, 4));

        firstLevel.getStaticObjects().add(new PlatformerWall(5, 5));

        firstLevel.getStaticObjects().add(new PlatformerWall(6, 2));
        firstLevel.getStaticObjects().add(new PlatformerWall(6, 3));
        firstLevel.getStaticObjects().add(new PlatformerWall(6, 4));
        firstLevel.getStaticObjects().add(new PlatformerWall(6, 5));
        firstLevel.getStaticObjects().add(new PlatformerWall(6, 6));

        firstLevel.getStaticObjects().add(new PlatformerWall(8, 2));
        firstLevel.getStaticObjects().add(new PlatformerWall(8, 3));
        firstLevel.getStaticObjects().add(new PlatformerWall(8, 4));
        firstLevel.getStaticObjects().add(new PlatformerWall(8, 5));
        firstLevel.getStaticObjects().add(new PlatformerWall(8, 6));

        firstLevel.getStaticObjects().add(new PlatformerWall(10, 2));
        firstLevel.getStaticObjects().add(new PlatformerWall(10, 3));
        firstLevel.getStaticObjects().add(new PlatformerWall(10, 4));
        firstLevel.getStaticObjects().add(new PlatformerWall(10, 5));
        firstLevel.getStaticObjects().add(new PlatformerWall(10, 6));

        firstLevel.getStaticObjects().add(new PlatformerWall(11, 3));

        firstLevel.getStaticObjects().add(new PlatformerWall(12, 4));

        firstLevel.getStaticObjects().add(new PlatformerWall(13, 5));

        firstLevel.getStaticObjects().add(new PlatformerWall(14, 2));
        firstLevel.getStaticObjects().add(new PlatformerWall(14, 3));
        firstLevel.getStaticObjects().add(new PlatformerWall(14, 4));
        firstLevel.getStaticObjects().add(new PlatformerWall(14, 5));
        firstLevel.getStaticObjects().add(new PlatformerWall(14, 6));

        firstLevel.getPlayerStartLocation().setX(4.0);
        firstLevel.getPlayerStartLocation().setY(2.0);

        return firstLevel;
    }
}
