package de.wagenblattraphael.www.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import de.wagenblattraphael.www.ELevel;
import de.wagenblattraphael.www.IConfig;
import de.wagenblattraphael.www.IUpdatable;
import de.wagenblattraphael.www.entity.Ball;
import de.wagenblattraphael.www.entity.Racket;
import de.wagenblattraphael.www.screen.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class Field implements IUpdatable {

    private final GameScreen screen;

    private final ELevel level;

    private final Area[] areas;

    private final List<Ball> balls;

    private final List<Racket> rackets;

    private final long creationTime;

    private float speed;

    public Field(GameScreen screen, ELevel level) {
        this.screen = screen;
        this.level = level;
        speed = level.speed;

        areas = new Area[2];

        areas[EPosition.LEFT.index] = new Area(new Vector2(0, 0));
        areas[EPosition.RIGHT.index] = new Area(new Vector2(IConfig.FIELD_WIDTH - 240, 0));

        balls = new ArrayList<>();
        rackets = new ArrayList<>();
        creationTime = TimeUtils.millis();

        screen.addUpdateable(this);
    }

    @Override
    public void update() {
        speed = calcAcceleration();

        if(((TimeUtils.millis() - creationTime) / 10000) > balls.size())
            createBall(new Texture("ball.png"),
                    new Vector2((float) IConfig.FIELD_WIDTH / 2, (float) IConfig.FIELD_HEIGHT / 2));

    }

    private float calcAcceleration() {
        return level.speed + level.acceleration * (TimeUtils.millis() - creationTime) / 1000;
    }

    private void createBall(Texture texture, Vector2 position) {
        Ball ball = new Ball(this, texture, position);

        for(Ball b : balls)
            ball.addTrigger(b.getEasyCollider());

        for(Racket r : rackets)
            ball.addTrigger(r.getCollider());

        balls.add(ball);
        screen.addUpdateable(ball);
        screen.addDrawable(ball);
    }

    public void destroyBall(Ball ball) {
        screen.removeDrawable(ball);
        screen.removeUpdateable(ball);
        balls.remove(ball);
    }

    public void addRacket(Racket racket) {
        rackets.add(racket);
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public Area getArea(EPosition position) {
        return areas[position.index];
    }

    public float getSpeed() {
        return speed;
    }
}
