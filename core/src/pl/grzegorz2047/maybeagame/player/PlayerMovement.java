package pl.grzegorz2047.maybeagame.player;

import com.badlogic.gdx.Gdx;
import pl.grzegorz2047.maybeagame.Direction;
import pl.grzegorz2047.maybeagame.Location;
import pl.grzegorz2047.maybeagame.extension.event.EventManager;
import pl.grzegorz2047.maybeagame.extension.event.PlayerMoveEvent;

/**
 * Plik stworzony przez grzegorz2047 23.02.2017.
 */
public class PlayerMovement {


    public Location calculatePlayerLocation(Location oldLocation, Direction direction) {
        Location newLocation = new Location(oldLocation);
        if (direction.equals(Direction.EAST)) {
            newLocation.setX(newLocation.getX() - (5 * Gdx.graphics.getDeltaTime()));
        }
        if (direction.equals(Direction.WEST)) {
            newLocation.setX(newLocation.getX() + (5 * Gdx.graphics.getDeltaTime()));
        }
        return newLocation;
    }

}
