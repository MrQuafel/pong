package de.wagenblattraphael.www;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface IDrawable {

    /**
     *
     This function is called in every draw cycle.
     The class must be registered for this.
     *
     * @param batch The batch used by the game loop.
     */
    void draw(Batch batch);
}
