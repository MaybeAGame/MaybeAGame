package pl.grzegorz2047.maybeagame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import pl.grzegorz2047.maybeagame.extension.ExtensionLoader;
import pl.grzegorz2047.maybeagame.input.PlayerInputProcessor;
import pl.grzegorz2047.maybeagame.player.Player;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class GameRoot extends ApplicationAdapter {
    private Environment environment;
    private PerspectiveCamera cam;
    //    private CameraInputController camController;
    private ModelBatch modelBatch;
    private ArrayList<ModelInstance> blocks = new ArrayList<ModelInstance>();
    public static final Logger LOGGER = Logger.getLogger(GameRoot.class.getName());
    private Player p;
    private Sound sound;

    public GameRoot() {
    }

    @Override
    public void create() {
        initExtensions();
        createEnvironment();
        createCamera();
        createFloor();
        modelBatch = new ModelBatch();
        p = new Player("Grzegorz");
        initInputProcessors();
        //camController = new CameraInputController(cam);
        //Gdx.input.setInputProcessor(camController);

        sound = Gdx.audio.newSound(Gdx.files.internal("theme.mp3"));
        sound.setLooping(1, true);
        sound.setPitch(1, 1.5f);
        sound.play();
    }

    private void initInputProcessors() {
        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(new PlayerInputProcessor(p));
        Gdx.input.setInputProcessor(im);
    }

    private void initExtensions() {
        ExtensionLoader el = new ExtensionLoader();
        try {
            el.loadExtensions();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void createEnvironment() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        environment.set(new ColorAttribute(ColorAttribute.Ambient, 1f, 1f, 1f, 1f));
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
    }

    private void createCamera() {
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0, 60f, 0);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
    }

    private void createFloor() {
        ModelBuilder mb = new ModelBuilder();
        Model model = mb.createBox(100f, 1, 100f,
                new Material(ColorAttribute.createDiffuse(Color.BLACK)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        ModelInstance mi = new ModelInstance(model);
        mi.transform.setTranslation(0, 0, 0);
        blocks.add(mi);
    }

    @Override
    public void render() {
//        camController.update();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);

        for (ModelInstance mi : blocks) {
            modelBatch.render(mi, environment);
        }
        modelBatch.render(p.getBodyModel(), environment);
        p.update();
        modelBatch.end();

    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        for (ModelInstance mi : blocks) {
            mi.model.dispose();
        }
        blocks.clear();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}