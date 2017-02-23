package pl.grzegorz2047.maybeagame.extension.event;

import pl.grzegorz2047.maybeagame.Location;

/**
 * Plik stworzony przez grzegorz2047 11.02.2017.
 */
public class PlayerMoveEvent extends Event {

    private Location oldLocation;
    private Location newLocation;

    public PlayerMoveEvent(Location oldLocation, Location newLocation) {
        this.oldLocation = oldLocation;
        this.newLocation = newLocation;
    }

    public Location getFrom() {
        return oldLocation;
    }

    public Location getTo() {
        return newLocation;
    }

    public void setTo(Location newLocation) {
        this.newLocation = newLocation;
    }

}
