package de.wagenblattraphael.www;

public enum ELevel {

    EASY(250f, 0.5f),
    NORMAL(300f, 1f),
    HARD(350f, 1.5f);

    public final float speed;

    public final float acceleration;

    ELevel(float speed, float acceleration) {
        this.speed = speed;
        this.acceleration = acceleration;
    }
}
