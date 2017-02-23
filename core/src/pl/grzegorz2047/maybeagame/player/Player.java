package pl.grzegorz2047.maybeagame.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import pl.grzegorz2047.maybeagame.Direction;
import pl.grzegorz2047.maybeagame.Location;

import java.util.Locale;

/**
 * Plik stworzony przez grzegorz2047 11.02.2017.
 */
public class Player {

    private final String name;

    private PlayerMovement movement = new PlayerMovement();
    private PlayerModel playerModel;

    public Player(String name) {
        this.name = name;
        createPlayerBody();
    }

    public void update() {
        movement.calculatePlayerMovement();
        playerModel.setTranslation(movement.getCurrentLocation());
    }

    public void setPlayerDirection(Direction direction, boolean moving) {
        this.movement.setDirection(direction, moving);
    }

    public String getName() {
        return name;
    }

    private void createPlayerBody() {
        playerModel = new PlayerModel(movement.getCurrentLocation());
    }

    public ModelInstance getBodyModel() {
        return this.playerModel;
    }

    public void setDirection(Direction direction) {

    }


}
