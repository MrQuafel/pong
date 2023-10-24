package de.wagenblattraphael.www.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.wagenblattraphael.www.IConfig;
import de.wagenblattraphael.www.IUpdatable;
import de.wagenblattraphael.www.map.Field;

import java.util.ArrayList;

public class Ball extends Entity implements IUpdatable {

    /**
     * The field to which the ball is assigned
     */
    private final Field field;

    /**
     * The current horizontal (x) and vertical (y) speed of the ball
     */
    private final Vector2 speed;

    /**
     * A rectangular collider to check possible collisions in a resource-saving manner.
     */
    private final Rectangle easyCollider;

    /**
     * A collider that accurately represents the ball in order to check pixel-by-pixel whether a
     * collision has occurred in the event of a possible collision.
     */
    private final Circle collider;

    /**
     * All other colliders that the ball can collide with, for example other balls or rackets.
     */
    private final ArrayList<Rectangle> triggers;

    /**
     * The last collider the ball collided with. This auxiliary variable prevents a collision from
     * occurring more often at the same time, thereby creating a bouncing effect
     */
    private Rectangle lastCollision;

    public Ball(Field field, Texture texture, Vector2 position) {
        super(texture, position);

        this.field = field;
        triggers = new ArrayList<>();

        speed = new Vector2(field.getSpeed(), field.getSpeed());
        lastCollision = new Rectangle();
        easyCollider = new Rectangle(position.x, position.y, IConfig.BALL_SIZE, IConfig.BALL_SIZE);
        collider = new Circle(position.x + IConfig.BALL_RADIUS, position.y + IConfig.BALL_RADIUS,
                IConfig.BALL_RADIUS);
    }

    @Override
    public void update() {
        for(Rectangle rc : triggers) {
            if(easyCollider.overlaps(rc) && !lastCollision.equals(rc)) {
                if (Intersector.overlaps(collider, rc)) {
                    lastCollision = rc;
                    Rectangle intersection = new Rectangle();
                    Intersector.intersectRectangles(rc, easyCollider, intersection);
                    if (intersection.x > easyCollider.x
                            || intersection.x + intersection.width < easyCollider.x + easyCollider.width) {
                        speed.x = -speed.x;
                        position.x = easyCollider.x;
                    }
                    else if (intersection.y > easyCollider.y
                            || intersection.y + intersection.height < easyCollider.y + easyCollider.height) {
                        speed.y = -speed.y;
                        speed.x = -speed.x;
                        position.x = easyCollider.x;
                        position.y = easyCollider.y;
                    }
                }
            }
        }

        speed.x = speed.x < 0 ? -field.getSpeed() : field.getSpeed();
        speed.y = speed.y < 0 ? -field.getSpeed() : field.getSpeed();

        position.x += speed.x * Gdx.graphics.getDeltaTime();
        position.y += speed.y * Gdx.graphics.getDeltaTime();

        if((position.x + IConfig.BALL_SIZE) > IConfig.FIELD_WIDTH) {
            field.destroyBall(this);
        }
        if(position.x < 0) {
            field.destroyBall(this);
        }

        if((position.y + IConfig.BALL_SIZE) > IConfig.FIELD_HEIGHT) {
            speed.y = -speed.y;
            position.y = IConfig.FIELD_HEIGHT - IConfig.BALL_SIZE;
        }
        if(position.y < 0) {
            speed.y = -speed.y;
            position.y = 0;
        }

        collider.setPosition(position.x + IConfig.BALL_RADIUS, position.y + IConfig.BALL_RADIUS);
        easyCollider.setPosition(position);
    }

    /**
     * Adds a trigger to the ball that the latter can interact with.
     *
     * @param trigger The trigger to add.
     */
    public void addTrigger(Rectangle trigger) {
        triggers.add(trigger);
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public Rectangle getEasyCollider() {
        return easyCollider;
    }
}
