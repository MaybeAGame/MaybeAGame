package pl.grzegorz2047.maybeagame.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Plik stworzony przez grzegorz2047 04.02.2017.
 */
public class GameInputListener implements Input.TextInputListener {
    @Override
    public void input(String text) {
        Gdx.app.log("t", text);
    }

    @Override
    public void canceled() {

    }
}
