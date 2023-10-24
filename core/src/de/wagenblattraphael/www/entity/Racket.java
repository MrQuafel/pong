package de.wagenblattraphael.www.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.wagenblattraphael.www.IConfig;
import de.wagenblattraphael.www.IUpdatable;
import de.wagenblattraphael.www.map.Field;
import de.wagenblattraphael.www.map.EPosition;

public class Racket extends Entity implements IUpdatable {

    private final Rectangle collider;

    private final EPosition side;

    protected final Field field;

    public Racket(Texture texture, Vector2 position, Field field, EPosition side) {
        super(texture, position);

        this.side = side;
        this.field = field;

        collider = new Rectangle(position.x, position.y, IConfig.RACKET_WIDTH, IConfig.RACKET_HEIGHT);
        field.addRacket(this);
    }

    @Override
    public void update() {
        field.getArea(side).border(position, new Vector2(IConfig.RACKET_WIDTH, IConfig.RACKET_HEIGHT));
        collider.setPosition(position.x, position.y);
    }

    public Rectangle getCollider() {
        return collider;
    }
}
