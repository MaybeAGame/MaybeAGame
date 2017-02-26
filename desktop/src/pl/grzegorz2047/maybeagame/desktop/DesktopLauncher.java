package pl.grzegorz2047.maybeagame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.grzegorz2047.maybeagame.GameRoot;
import pl.grzegorz2047.testlibgdxfeatures.networking.Networking;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Networking(), config);
	}
}
