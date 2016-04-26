package CodeWorldsV2;

import java.util.Iterator;

public class CompositeBody implements Body {

    Body RefHolder;

    @Override
    public Rectangle getBounds() {
        /**
         * Refer to Tom's email on 4/24/2016
         */

        return null;
    }

    @Override
    public Body clone(Vector offset) {
        return null;
    }

    @Override
    public Iterator<Brick> iterator() {
        return new Iterator<Brick>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Brick next() {
                return null;
            }
        };
    }
}
