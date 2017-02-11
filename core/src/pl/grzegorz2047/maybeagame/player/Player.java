package pl.grzegorz2047.maybeagame.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import pl.grzegorz2047.maybeagame.GameRoot;
import pl.grzegorz2047.maybeagame.Location;
import pl.grzegorz2047.maybeagame.extension.event.EventManager;
import pl.grzegorz2047.maybeagame.extension.event.PlayerMoveEvent;

import java.util.logging.Level;

/**
 * Plik stworzony przez grzegorz2047 11.02.2017.
 */
public class Player {

    private final String name;
    private boolean leftMove;
    private boolean rightMove;
    private int i = 0;
    private ModelInstance mi;
    private Location location;
    private boolean moving = false;

    public Player(String name) {
        this.name = name;
        location = new Location(0, 2, 0);
        createPlayerBody();
    }

    public void update() {
        moving = false;
        if (leftMove) {
            location.setX(location.getX() - (5 * Gdx.graphics.getDeltaTime()));
            moving = true;
        }
        if (rightMove) {
            location.setX(location.getX() + (5 * Gdx.graphics.getDeltaTime()));
            moving = true;
        }
        if (moving) {
            PlayerMoveEvent event = new PlayerMoveEvent(location.getX(), location.getY(), location.getZ());
            EventManager.callEvent(event);
            mi.transform.setTranslation(location.toVector());
        }
    }

    public void setLeftMove(boolean t) {
        if (rightMove && t) rightMove = false;
        leftMove = t;
    }

    public void setRightMove(boolean t) {
        if (leftMove && t) leftMove = false;
        rightMove = t;
    }

    public String getName() {
        return name;
    }

    private void createPlayerBody() {
        ModelBuilder mb = new ModelBuilder();
        Model model = mb.createBox(1, 1, 1,
                new Material(ColorAttribute.createDiffuse(Color.WHITE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        mi = new ModelInstance(model);
        mi.transform.setTranslation(location.toVector());
    }

    public ModelInstance getBodyModel() {
        return this.mi;
    }
}
