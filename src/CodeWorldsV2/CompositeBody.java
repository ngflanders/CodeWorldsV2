package CodeWorldsV2;

import java.awt.*;
import java.util.Iterator;

public class CompositeBody implements Body, Displayable {

    // Copy constructor
    public CompositeBody(CompositeBody other) {

    }

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
        return null; // maybe this should be in the child class???
        //    return new CompositeBody(this); // needs offset still!
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

    @Override
    public Vector getLoc() {
        return null;
    }

    @Override
    public Vector getVlc() {
        return null;
    }

    @Override
    public Image getImage(int size) {
        return null;
    }
}
