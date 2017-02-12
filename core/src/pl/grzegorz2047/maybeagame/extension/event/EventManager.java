package pl.grzegorz2047.maybeagame.extension.event;

import pl.grzegorz2047.maybeagame.player.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Plik stworzony przez grzegorz2047 11.02.2017.
 */
public class EventManager {

    private static ConcurrentHashMap<String, Event> events = new ConcurrentHashMap<String, Event>();

    public static void callEvent(Event e) {
        String className = e.getClass().getName();
        System.out.println("Wywoluje probe event dla " + className);
        try {
            Class<?> aClass = Class.forName(className);
            Field f = aClass.getField("listeners");
            Class<?> t = f.getType();
            System.out.println("Probuj!");
            if (t == ArrayList.class) {
                System.out.print("ArrayList ma ten event " + className);
                ArrayList<Listener> listeners = (ArrayList<Listener>) f.get(null);
                for (Listener l : listeners) {
                    System.out.println("Notikowany jest " + l.getClass().getName());
                    l.notify(e);
                    System.out.println("Notyfikuje!");
                }
            }
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

    }

    public static void registerListener(Listener listener, String className) {
        try {
            Class<?> aClass = Class.forName(className);

            Field f = aClass.getField("listeners");
            Class<?> t = f.getType();
            if (t == ArrayList.class) {
                System.out.print("ArrayList ma ten event " + className);
                ArrayList<Listener> listeners = (ArrayList<Listener>) f.get(null);
                listeners.add(listener);
                System.out.print("Event move ma " +
                        PlayerMoveEvent.listeners.size());
            } else {
                System.out.print("DWADAWDAWDA");
            }
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
