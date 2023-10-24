package de.wagenblattraphael.www.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import de.wagenblattraphael.www.*;
import de.wagenblattraphael.www.entity.AI;
import de.wagenblattraphael.www.map.EPosition;
import de.wagenblattraphael.www.map.Field;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private final Pong pong;

    /**
     * All elements to be updated in each call to the render() method
     */
    private final ArrayList<IUpdatable> updatables;

    /**
     * All elements to be drawn in each call to the render() method
     */
    private final ArrayList<IDrawable> drawables;

    /**
     * The screen's camera.
     */
    private final OrthographicCamera camera;

    public GameScreen(Pong pong, ELevel level) {
        this.pong = pong;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, IConfig.FIELD_WIDTH, IConfig.FIELD_HEIGHT);

        updatables = new ArrayList<>();
        drawables = new ArrayList<>();

        Field field = new Field(this, level);

		/*Player player = new Player(
				new Texture("racket.png"),
				new Vector2(0, (float) IConfig.FIELD_HEIGHT / 2),
				field,
				EPosition.LEFT
		);*/
        AI player = new AI(
                new Texture("racket.png"),
                new Vector2(0, (float) IConfig.FIELD_HEIGHT / 2),
                field,
                EPosition.LEFT
        );
        AI ai = new AI(
                new Texture("racket.png"),
                new Vector2(IConfig.FIELD_WIDTH - IConfig.RACKET_WIDTH, (float) IConfig.FIELD_HEIGHT / 2),
                field,
                EPosition.RIGHT
        );

        updatables.add(player);
        updatables.add(ai);

        drawables.add(player);
        drawables.add(ai);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        for(IUpdatable updatable : (ArrayList<IUpdatable>) updatables.clone())
            updatable.update();

        camera.update();
        ScreenUtils.clear(1, 1, 1, 1);

        pong.batch.begin();
        for(IDrawable drawable : drawables)
            drawable.draw(pong.batch);
        pong.batch.end();
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
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    /**
     * Adds an element to the list of elements to draw
     *
     * @param drawable The element to draw
     */
    public void addDrawable(IDrawable drawable) {
        drawables.add(drawable);
    }

    /**
     * Removes an element from the list of elements to draw
     *
     * @param drawable The element to remove
     */
    public void removeDrawable(IDrawable drawable) {
        drawables.remove(drawable);
    }

    /**
     * Adds an element to the list of elements to update
     *
     * @param updatable The element to update
     */
    public void addUpdateable(IUpdatable updatable) {
        updatables.add(updatable);
    }

    /**
     * Removes an element to the list of elements to update
     *
     * @param updatable The element to remove
     */
    public void removeUpdateable(IUpdatable updatable) {
        updatables.remove(updatable);
    }
}
