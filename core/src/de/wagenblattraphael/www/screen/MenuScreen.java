package de.wagenblattraphael.www.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import de.wagenblattraphael.www.ELevel;
import de.wagenblattraphael.www.IConfig;
import de.wagenblattraphael.www.Pong;

public class MenuScreen implements Screen {

    private final Pong pong;

    private final OrthographicCamera camera;

    public MenuScreen(Pong pong) {
        this.pong = pong;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, IConfig.FIELD_WIDTH, IConfig.FIELD_HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear( 0, 0, 0.2f, 1);

        camera.update();
        pong.batch.setProjectionMatrix(camera.combined);

        pong.batch.begin();
        pong.font.draw(pong.batch, "Welcome to Pong!!! ", 100, 150);
        pong.font.draw(pong.batch, "Tap anywhere to begin!", 100, 100);
        pong.batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            pong.setScreen(new GameScreen(pong, ELevel.NORMAL));
            dispose();
        }
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
}
