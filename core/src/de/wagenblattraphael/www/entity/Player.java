package de.wagenblattraphael.www.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.wagenblattraphael.www.map.Field;
import de.wagenblattraphael.www.map.EPosition;

public class Player extends Racket {

    public Player(Texture texture, Vector2 position, Field field, EPosition side) {
        super(texture, position, field, side);
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            position.x -= field.getSpeed() * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            position.x += field.getSpeed() * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            position.y -= field.getSpeed() * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            position.y += field.getSpeed() * Gdx.graphics.getDeltaTime();

        super.update();
    }
}
