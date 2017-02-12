package pl.grzegorz2047.maybeagame.extension.event;

import java.util.ArrayList;

/**
 * Plik stworzony przez grzegorz2047 11.02.2017.
 */
public abstract class Event {

    public static ArrayList<Listener> listeners = new ArrayList<Listener>();

    public static void unregisterAll() {
        listeners.clear();
    }

    public static void unregister(Listener listener) {
        listeners.remove(listener);
    }
    public static ArrayList<Listener> getListeners(){
        return listeners;
    }

}
