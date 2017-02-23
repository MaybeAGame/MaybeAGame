package pl.grzegorz2047.maybeagame;

import com.badlogic.gdx.math.Vector3;

/**
 * Plik stworzony przez grzegorz2047 11.02.2017.
 */
public class Location {

    private Vector3 vector;

    public Location(float x, float y, float z) {
        vector = new Vector3(x, y, z);
    }

    public Location(Location location) {
        this.vector = location.toVector();
    }

    public float getX() {
        return vector.x;
    }

    public float getY() {
        return vector.y;
    }

    public float getZ() {
        return vector.z;
    }

    public void setX(float x) {
        vector.x = x;
    }

    public void setY(float y) {
        vector.y = y;
    }

    public void setZ(float z) {
        vector.z = z;
    }

    public Vector3 toVector() {
        return vector;
    }
}
