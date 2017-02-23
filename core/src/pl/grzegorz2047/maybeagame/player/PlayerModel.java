package pl.grzegorz2047.maybeagame.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import pl.grzegorz2047.maybeagame.Location;

/**
 * Plik stworzony przez grzegorz2047 23.02.2017.
 */

public class PlayerModel extends ModelInstance {


    public PlayerModel(Location startLocation) {
        super(new ModelBuilder().createBox(1, 1, 1,
                new Material(ColorAttribute.createDiffuse(Color.WHITE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal));
        setTranslation(startLocation);
    }

    public void setTranslation(Location location) {
        this.transform.setTranslation(location.toVector());
    }
}

