package pl.grzegorz2047.maybeagame.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import pl.grzegorz2047.maybeagame.Direction;
import pl.grzegorz2047.maybeagame.GameRoot;
import pl.grzegorz2047.maybeagame.player.Player;

import java.util.logging.Level;

/**
 * Plik stworzony przez grzegorz2047 11.02.2017.
 */
public class PlayerInputProcessor extends InputAdapter {

    private final Player p;

    public PlayerInputProcessor(Player p) {
        this.p = p;
    }

    @Override
    public boolean keyDown(int keycode) {
        setMoveValue(keycode, true);
        GameRoot.LOGGER.log(Level.INFO, "key " + keycode + " pressed down");
        System.out.println("key " + keycode + " pressed down");
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        setMoveValue(keycode, false);
        GameRoot.LOGGER.log(Level.INFO, "key " + keycode + " pressed up");
        return true;
    }

    private void setMoveValue(int keycode, boolean moving) {
        switch (keycode) {
            case Keys.LEFT:
                System.out.println("Keycode left");
                p.setPlayerDirection(Direction.WEST, moving);
                break;
            case Keys.RIGHT:
                System.out.println("Keycode right");
                p.setPlayerDirection(Direction.EAST, moving);
                break;
            case Keys.UP:
                System.out.println("Keycode up");
                p.setPlayerDirection(Direction.NORTH, moving);
                break;
            case Keys.DOWN:
                System.out.println("Keycode down");
                p.setPlayerDirection(Direction.SOUTH, moving);
                break;
        }
    }
}
