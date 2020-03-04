package surgo.celldefender.core;

import surgo.celldefender.core.interfaces.IRenderNodeSystem;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import surgo.celldefender.common.GameData;
import surgo.celldefender.common.World;
import surgo.celldefender.common.contracts.*;
import surgo.celldefender.common.nodes.GameNode;

public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;

    private final GameData gameData = new GameData();
    private World world = new World();
    
    private List<INodeSystem> nodeSystems = new ArrayList<>();
    private List<INodePlugin> nodePlugins = new ArrayList<>();
    private List<IPostNodeSystem> postSystems = new ArrayList<>();
    private List<IRenderNodeSystem> renderSystems = new ArrayList<>();
    
    @Override
    public void create() {

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );
        
        Lookup whiteboard = Lookup.getDefault();
        
        for (INodeSystem system : whiteboard.lookupAll(INodeSystem.class)) {
            nodeSystems.add(system);
        }
        
        for (INodePlugin plugin : whiteboard.lookupAll(INodePlugin.class)) {
            nodePlugins.add(plugin);
        }
        
        for(IPostNodeSystem system : whiteboard.lookupAll(IPostNodeSystem.class)) {
            postSystems.add(system);
        }
        
        for(IRenderNodeSystem system : whiteboard.lookupAll(IRenderNodeSystem.class)) {
            renderSystems.add(system);
        }
        
        nodePlugins.forEach(e -> e.start(gameData, world));
    }

    @Override
    public void render() {
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();
        
        post();
        
        gameData.getKeys().update();
    }

    private void update() {
        nodeSystems.forEach(e -> e.process(gameData, world));
    }

    private void post() {
        postSystems.forEach(e -> e.process(gameData, world));
    }
    
    private void draw() {
        renderSystems.forEach(e -> e.process(gameData, world, sr));
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

    @Override
    public void dispose() {
    }
}
