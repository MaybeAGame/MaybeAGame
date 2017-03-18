package pl.grzegorz2047.maybeagame.extension.event;

/**
 * Plik stworzony przez grzegorz2047 11.02.2017.
 */
public interface Listener<T extends Event> {

    void notify(T event);

}
