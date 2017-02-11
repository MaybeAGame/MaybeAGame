package pl.grzegorz2047.maybeagame.extension.event;

import pl.grzegorz2047.maybeagame.Location;

/**
 * Plik stworzony przez grzegorz2047 11.02.2017.
 */
public class PlayerMoveEvent extends Event {

    private Location location;

    public PlayerMoveEvent(float x, float y, float z) {
        location = new Location(x, y, z);
    }

    public Location getLocation() {
        return location;
    }
}
