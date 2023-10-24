package de.wagenblattraphael.www.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.wagenblattraphael.www.IConfig;
import de.wagenblattraphael.www.map.EPosition;
import de.wagenblattraphael.www.map.Field;

public class AI extends Racket {

    private final Vector2 target;

    public AI(Texture texture, Vector2 position, Field field, EPosition side) {
        super(texture, position, field, side);
        target = new Vector2();
    }

    @Override
    public void update() {
        if(field.getBalls().isEmpty())
            return;

        Ball ball = getNextBall();

        target.x = ball.getPosition().x + ball.getSpeed().x * Gdx.graphics.getDeltaTime();
        target.y = ball.getPosition().y + ball.getSpeed().y * Gdx.graphics.getDeltaTime()
                - (float) IConfig.RACKET_HEIGHT / 2;

        if(position.x < target.x)
            position.x += field.getSpeed() * Gdx.graphics.getDeltaTime();
        if(position.x > target.x)
            position.x -= field.getSpeed() * Gdx.graphics.getDeltaTime();

        if(position.y < target.y)
            position.y += field.getSpeed() * Gdx.graphics.getDeltaTime();
        if(position.y > target.y)
            position.y -= field.getSpeed() * Gdx.graphics.getDeltaTime();

        super.update();
    }

    private Ball getNextBall() {
        float dst = Float.MAX_VALUE;
        Ball best = null;
        Ball closest = null;
        for (Ball b : field.getBalls()) {
            float _dst = b.getPosition().dst(position);

            if (_dst < dst) {
                closest = b;
                if (isRightDirection(b)) {
                    dst = _dst;
                    best = b;
                }
            }
        }
        return best != null ? best : closest;
    }

    private boolean isRightDirection(Ball b) {
        return b.getPosition().x < position.x && b.getSpeed().x > 0
                || b.getPosition().x > position.x && b.getSpeed().x < 0;
    }
}
