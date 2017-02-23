package pl.grzegorz2047.maybeagame.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import pl.grzegorz2047.maybeagame.Direction;
import pl.grzegorz2047.maybeagame.Location;
import pl.grzegorz2047.maybeagame.extension.event.EventManager;
import pl.grzegorz2047.maybeagame.extension.event.PlayerMoveEvent;

/**
 * Plik stworzony przez grzegorz2047 23.02.2017.
 */
public class PlayerMovement {

    private Direction direction;
    private Location currentLocation;
    private boolean moving = false;

    public PlayerMovement() {
        this.currentLocation = new Location(0, 2, 0);
    }

    public void calculatePlayerMovement() {
        Location newLocation = new Location(currentLocation);
        if (!moving) return;
        if (direction.equals(Direction.EAST)) {
            newLocation.setX(newLocation.getX() + (5 * Gdx.graphics.getDeltaTime()));
            moving = true;
        } else if (direction.equals(Direction.WEST)) {
            newLocation.setX(newLocation.getX() - (5 * Gdx.graphics.getDeltaTime()));
            moving = true;
        } else if (direction.equals(Direction.NORTH)) {
            newLocation.setZ(newLocation.getZ() - (5 * Gdx.graphics.getDeltaTime()));
            moving = true;
        } else if (direction.equals(Direction.SOUTH)) {
            newLocation.setZ(newLocation.getZ() + (5 * Gdx.graphics.getDeltaTime()));
            moving = true;
        }
        PlayerMoveEvent event = new PlayerMoveEvent(currentLocation, newLocation);
        EventManager.callEvent(event);
        this.currentLocation = newLocation;
    }

    public void setDirection(Direction direction, boolean moving) {
        this.direction = direction;
        this.moving = moving;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
}
