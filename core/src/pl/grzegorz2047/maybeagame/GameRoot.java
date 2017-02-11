package pl.grzegorz2047.maybeagame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import pl.grzegorz2047.maybeagame.extension.ExtensionLoader;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class GameRoot extends ApplicationAdapter {
    private Environment environment;
    private PerspectiveCamera cam;
    private CameraInputController camController;
    private ModelBatch modelBatch;
    private ArrayList<ModelInstance> blocks = new ArrayList<ModelInstance>();

    @Override
    public void create() {
        ExtensionLoader el = new ExtensionLoader();
        try {
            el.loadExtensions();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        environment.set(new ColorAttribute(ColorAttribute.Ambient, 1f, 1f, 1f, 1f));

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
        ModelBuilder mb = new ModelBuilder();


        for (int x = 1; x < 100; x += 2) {
            Model model = mb.createBox(1f, 1f, 1f,
                    new Material(ColorAttribute.createDiffuse(Color.GRAY)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
            ModelInstance mi = new ModelInstance(model);
            float y = getYFromFunction(x);
            mi.transform.setTranslation(x, y, 0);
            blocks.add(mi);
        }

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);
    }

    private float getYFromFunction(int x) {
        int y = (2*x) + 1;
        return y;
    }


    @Override
    public void render() {
        camController.update();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);
        for (ModelInstance mi : blocks) {
            modelBatch.render(mi, environment);
        }

        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        for(ModelInstance mi : blocks){
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