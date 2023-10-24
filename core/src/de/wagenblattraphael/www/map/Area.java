package de.wagenblattraphael.www.map;

import com.badlogic.gdx.math.Vector2;
import de.wagenblattraphael.www.IConfig;

public class Area {

    private final Vector2 position;

    private final Vector2 size;

    public Area(Vector2 position) {
        this.position = position;
        this.size = new Vector2(240, IConfig.FIELD_HEIGHT);
    }

    public void border(Vector2 aPosition, Vector2 aSize) {
        if(position.x > aPosition.x)
            aPosition.x = position.x;
        if(position.x + size.x < aPosition.x + aSize.x)
            aPosition.x = position.x + size.x - aSize.x;

        if(position.y > aPosition.y)
            aPosition.y = position.y;
        if(position.y + size.y < aPosition.y + aSize.y)
            aPosition.y = position.y + size.y - aSize.y;
    }
}
