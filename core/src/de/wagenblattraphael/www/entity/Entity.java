package de.wagenblattraphael.www.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import de.wagenblattraphael.www.IDrawable;

public abstract class Entity implements IDrawable {

    /**
     * The texture of the entity displayed on the screen.
     */
    private final Texture texture;

    /**
     * The entity's position on the field.
     */
    protected final Vector2 position;

    public Entity(Texture texture, Vector2 position) {
        this.texture = texture;
        this.position = position;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public Vector2 getPosition() {
        return position;
    }
}
